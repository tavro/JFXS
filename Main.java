import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static final int SCALE_FACTOR = 32;
    public static final Color TEXT_COLOR = Color.BLACK;
	
	public static void main(String[] args) {
		
		Vector2 startPosition = new Vector2(2, 2);
		Player player = new Player("Player", startPosition);
		
		Vector2 startWorldPosition = new Vector2(0,0);
		
		World world = new World(startWorldPosition);
		world.getChunk(world.getCurrentChunkPosition()).setPlayer(player);
		
		JFrame frame = new JFrame("Sandbox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(512, 512));
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		world.getChunk(world.getCurrentChunkPosition()).updateMap();
		printDebug(frame, world);
		
		KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
            	
            	/* Handle Keyboard Input */
            	switch(event.getKeyChar()) {
            	  case 'w':
              		if(player.getPosition().getY() - 1 > -1)
            			player.move(0, -1);
            		else if(world.checkIfOutOfCurrentChunkY(Direction.DOWN))
            			world.updateCurrentChunkY(Direction.DOWN);
            	    break;
            	  case 's':
  					if(player.getPosition().getY() + 1 < Chunk.HEIGHT)
            			player.move(0, 1);
            		else if(world.checkIfOutOfCurrentChunkY(Direction.UP))
            			world.updateCurrentChunkY(Direction.UP);
            	    break;
            	  case 'a':
              		if(player.getPosition().getX() - 1 > -1)
            			player.move(-1, 0);
            		else if(world.checkIfOutOfCurrentChunkX(Direction.LEFT))
            			world.updateCurrentChunkX(Direction.LEFT);
              	    break;
              	  case 'd':
  					if(player.getPosition().getX() + 1 < Chunk.WIDTH)
            			player.move(1, 0);
            		else if(world.checkIfOutOfCurrentChunkX(Direction.RIGHT))
            			world.updateCurrentChunkX(Direction.RIGHT);
              	    break;
            	  default:
            	    System.out.println(event.getKeyChar());
            	}
            	
            	Container inventory = player.getInventory();
            	if(event.getKeyCode()==37) {
            		inventory.selectItem(inventory.getSelectedItemIndex() - 1);
            	}
            	else if(event.getKeyCode()==39) {
            		inventory.selectItem(inventory.getSelectedItemIndex() + 1);
            	}
            	
                world.getChunk(world.getCurrentChunkPosition()).updateMap();
                printDebug(frame, world);
            }
 
            @Override
            public void keyReleased(KeyEvent event) {
            	
            }
 
            @Override
            public void keyTyped(KeyEvent event) {
                
            }
        };
        
	    frame.addKeyListener(listener);
	    frame.pack();
	    
	    Notification notis = null;
	    int currentInventorySlot = -1;
	    
	    int lastMouseX = -1;
	    int lastMouseY = -1;
	    
	    while(true) {
	    	
	        PointerInfo a = MouseInfo.getPointerInfo();
	        Point b = a.getLocation();
	        SwingUtilities.convertPointFromScreen(b, frame.getContentPane());
	        int x = (int) b.getX();
	        int y = (int) b.getY();
	        
	        Container inventory = world.getCurrentChunk().getPlayer().getInventory();
	        if (inventory.isOver(x,y)) {
	        	currentInventorySlot = inventory.getSlotIndex(x);
	        	if(lastMouseX != x || lastMouseY != y) {
	        		if(notis != null) {	        			
		        		frame.remove(notis);
		        		notis = null;
	        		}
	        	}
	        	if(notis == null) {
	        		int inventorySize = inventory.items.size();
	        		if(inventorySize >= currentInventorySlot + 1) {
	        			Item item = inventory.realItems.get(inventorySize - 1 - currentInventorySlot);
		        		notis = new Notification(new Vector2(x,y),  item.getName(), item.getDescription());
		        		
		        		lastMouseX = x;
		        		lastMouseY = y;
			        	
		        		frame.add(notis);
			        	frame.pack();
	        		}
	        	}
	        } else {
	        	if(notis != null) {
	        		frame.remove(notis);
	        		frame.pack();
	        		frame.repaint();
	        		notis = null;
	        	}
	        }
	    }
	}
	
	public static void drawInventory(JFrame frame, Player player) {
		int x = 0;
		Container inventory = player.getInventory();
		for (Map.Entry<String, Integer> pair : inventory.items.entrySet()) {
			boolean isSelected = (inventory.getSelectedItemIndex() == x);
			
			Image img = null;
			try {
				img = ImageIO.read(new File("src/" +pair.getKey().toLowerCase() +".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Vector2 position = new Vector2(SCALE_FACTOR*x, SCALE_FACTOR*10);
			Slot slot = new Slot(position,SCALE_FACTOR,SCALE_FACTOR,pair.getValue().toString().toCharArray(),TEXT_COLOR, img, isSelected);
			frame.add(slot);
		    frame.pack();
		    x++;
		}
		for(int i = inventory.items.size(); i < inventory.getAmountOfSlots(); i++) {
			boolean isSelected = (inventory.getSelectedItemIndex() == i);
			Vector2 position = new Vector2(SCALE_FACTOR*i, SCALE_FACTOR*10);
			Slot slot = new Slot(position,SCALE_FACTOR,SCALE_FACTOR,"0".toCharArray(),TEXT_COLOR,null,isSelected);
    		frame.add(slot);
    	    frame.pack();
		}
	}

	public static void printDebug(JFrame frame, World world) {
		frame.getContentPane().removeAll();

		Chunk chunk = world.getCurrentChunk();
	    final int UI_SAFEZONE = 11;
		
	    DebugText debugLastPosX = new DebugText("Last Player X Pos:" + chunk.getPlayer().getLastPosition().getX(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,16),TEXT_COLOR);
	    DebugText debugLastPosY = new DebugText("Last Player Y Pos:" + chunk.getPlayer().getLastPosition().getY(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,32),TEXT_COLOR);
	    
	    DebugText debugPosX = new DebugText("Player X Pos:" + chunk.getPlayer().getPosition().getX(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,48),TEXT_COLOR);
	    DebugText debugPosY = new DebugText("Player Y Pos:" + chunk.getPlayer().getPosition().getY(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,64),TEXT_COLOR);
	    
	    DebugText debugChunkPosX = new DebugText("Chunk X Pos:" + world.getCurrentChunkPosition().getX(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,80),TEXT_COLOR);
	    DebugText debugChunkPosY = new DebugText("Chunk Y Pos:" + world.getCurrentChunkPosition().getY(), new Vector2(SCALE_FACTOR*UI_SAFEZONE,96),TEXT_COLOR);
	    
	    drawChunk(frame, chunk);
	    
	    addDebugText(frame, debugPosX);
	    addDebugText(frame, debugPosY);
	    addDebugText(frame, debugLastPosX);
	    addDebugText(frame, debugLastPosY);
	    addDebugText(frame, debugChunkPosX);
	    addDebugText(frame, debugChunkPosY);
	    
		frame.repaint();
	}
	
	public static void drawChunk(JFrame frame, Chunk chunk) {
	    for(int i = 0; i < 10; i++) {
	    	for(int j = 0; j < 10; j++) {
	    		Tile tile = chunk.map[i][j];
	    		frame.add(tile);
	    	    frame.pack();
	    	}
	    }
	    
	    drawInventory(frame, chunk.getPlayer());
	}
	
	public static void addDebugText(JFrame frame, DebugText text) {
	    frame.add(text);
	    frame.pack();
	}
	
}
