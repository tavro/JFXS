import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Chunk.java
 * The Chunk class represents a 2D array of tiles
 * that are generated with the help of simplex noise.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Chunk {
	public static final int WIDTH = 10;        			//chunk width (in tiles)
	public static final int HEIGHT = 10;        		//chunk height (in tiles)
	
	private static final double TREE_HEIGHT = 0.75;      //where in noise trees start to appear
	private static final double WALL_HEIGHT = 0.85;      //where in noise walls start to appear
	private static final double WATER_HEIGHT = -0.55;   //where in noise water start to appear
	
	public static final String PATH_PREFIX = "src/";        
	public static final String PATH_SUFFIX = ".png";        //used for getting png images
	
	/* file names */
	public static final String WALL_IMG = PATH_PREFIX + "wall" + PATH_SUFFIX;        	
	public static final String GROUND_IMG = PATH_PREFIX + "ground" + PATH_SUFFIX;      
	public static final String WATER_IMG = PATH_PREFIX + "water" + PATH_SUFFIX; 
	public static final String TREE_IMG = PATH_PREFIX + "tree" + PATH_SUFFIX;
	
	Tile[][] map = new Tile[WIDTH][HEIGHT];      
	
	private Player player;      
	private Biome biome;
	
	/**
     * Constructor      
     * @param int x, chunk x-position      
     * @param int y, chunk y-position      
     */
	public Chunk(int x, int y) {
		biome = new Biome();
		generateChunk(x,y);
	}
	
	/**
     * Method generateChunk
     * @param int xOffset, x offset used for generating terrain
     * @param int yOffset, y offset used for generating terrain
     */
	public void generateChunk(int xOffset, int yOffset) {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				Vector2 pos = new Vector2(x,y);
				double noise = SimplexNoise.noise(x+xOffset*10, y+yOffset*10);
				if(noise <= biome.getWaterAmount()) {
					Image img = getImage(WATER_IMG);
					map[x][y] = new Tile(pos, true, TileType.WATER, img);
				}
				else if(noise >= biome.getMountainAmount()) {
					Image img = getImage(WALL_IMG);
					map[x][y] = new Tile(pos, true, TileType.WALL, img);
				}
				else if(noise >= biome.getTreeAmount()) {
					Image img = getImage(TREE_IMG);
					map[x][y] = new Tile(pos, true, TileType.TREE, img);
				}
				else {
					Image img = getImage(GROUND_IMG);
					map[x][y] = new Tile(pos, false, TileType.GROUND, img);
				}
			}
		}
	}

	/**
     * Getter getImage
     * @param String path, gets image using given path 
     */
	public Image getImage(String path) {
		Image img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	/**
     * Method updateMap
     * updates player positions
     */
	public void updateMap() {
		updatePlayerPosition();
	}
	
	/**
     * Getter getTile
     * @param int x, x position of tile to get      
     * @param int y, y position of tile to get   
     */
	public Tile getTile(int x, int y) {
		return map[x][y];
	}
	
	/**
     * Setter setTile
     * @param Tile tile, puts tile in map array  
     */
	public void setTile(Tile tile) {
		int xPos = (int)tile.getPosition().getX();
		int yPos = (int)tile.getPosition().getY();
		if(isAvailable(xPos, yPos)) {
			removeTile(xPos, yPos);
			map[xPos][yPos] = tile;
		}
		else {
			if(tile.getTileType() == TileType.PLAYER) {
				Container inventory = player.getInventory();
				if(inventory.getSelectedItem() == "Pickaxe") {
					Tile t = getTile(xPos, yPos);
					if(t.getTileType() == TileType.WALL) {
						removeTile(xPos, yPos);
						
						Item item = inventory.realItems.get(inventory.getSelectedItemIndex());
						player.useItem(item);
						
						inventory.addItem(new Item("Stone", "Used for crafting.", "src/stone.png"));
					}
				}
				else if(inventory.getSelectedItem() == "Axe") {
					Tile t = getTile(xPos, yPos);
					if(t.getTileType() == TileType.TREE) {
						removeTile(xPos, yPos);
						
						Item item = inventory.realItems.get(inventory.getSelectedItemIndex());
						player.useItem(item);
						
						inventory.addItem(new Item("Wood", "Used for building.", "src/wood.png"));
					}
				}
				removeTile(player.getLastPosition().getX(), player.getLastPosition().getY());
				player.move(player.getLastPosition().getX() - xPos, player.getLastPosition().getY() - yPos);
				updatePlayerPosition();
			}
		}
	}
	
	/**
     * Method removeTile
     * @param int x, x position of tile to remove      
     * @param int y, y position of tile to remove
     */
	public void removeTile(int x, int y) {
		Vector2 pos = new Vector2(x, y);
		map[x][y] = new Tile(pos, false, TileType.GROUND, getImage(GROUND_IMG));
	}
	
	/**
     * Getter isAvailable
     * @param int x      
     * @param int y
     * checks if area on given (x, y) position is available    
     */
	public boolean isAvailable(int x, int y) {
		if (map[x][y].getIfSolid()) {
			return false;
		}
		return true;
	}
	
	/**
     * Setter setPlayer
     * @param Player p, adds player to chunk
     */
	public void setPlayer(Player p) {
		player = p;
	}
	
	/**
     * Getter getPlayer
     * gets player
     */
	public Player getPlayer() {
		return player;
	}
	
	/**
     * Method updatePlayerPositions
     * updates player positions
     */
	public void updatePlayerPosition() {
		if (player != null) {
			removeTilesFromMap(TileType.PLAYER);
			int xPos = (int)player.getPosition().getX();
			int yPos = (int)player.getPosition().getY();
			Vector2 pos = new Vector2(xPos, yPos);
				
			Image image = getImage(PATH_PREFIX + "player" + PATH_SUFFIX);
			Tile tile = new Tile(pos, true, TileType.PLAYER, image);
			setTile(tile);
		}
	}
	
	/**
     * Method removeTilesFromMap
     * @param TileType tileType, removes all tiles with given tileType from map
     */
	public void removeTilesFromMap(TileType tileType) {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				if(map[x][y].getTileType() == tileType) {
					removeTile(x, y);
				}
			}
		}
	}
}