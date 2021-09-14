/**
 * World.java
 * The World class is a container of chunks.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class World {
	
	public static final int  WIDTH = 10;        //Amount of chunks on the y-axis  (World width)
	public static final int HEIGHT = 10;        //Amount of chunks on the x-axis (World height)
	
	private Vector2 _currentChunkPosition;      //Position of active chunk in chunks-array
	
	private Chunk[][] chunks;                   //A 2D array of chunks
	
	/**
     * Constructor      
     * @param Vector2 startPosition, position of the active chunk in 2D array      
     */
	public World(Vector2 startPosition) {
		chunks = new Chunk[WIDTH][HEIGHT];
		_currentChunkPosition = startPosition;
		
		/* instantiate & generate all chunks */
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				chunks[x][y] = new Chunk(x,y);
			}
		}
	}
	
	public boolean checkIfOutOfCurrentChunkX(Direction direction) {
		if(direction == Direction.RIGHT) {
			if(getCurrentChunkPosition().getX() + 1 < WIDTH) {
				return true;
			}
		}
		else if(direction == Direction.LEFT) {
			if(getCurrentChunkPosition().getX() - 1 > -1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfOutOfCurrentChunkY(Direction direction) {
		if(direction==Direction.UP) {
			if(getCurrentChunkPosition().getY() + 1 < HEIGHT) {
				return true;
			}
		}
		else if(direction==Direction.DOWN) {
			if(getCurrentChunkPosition().getY() - 1 > -1) {
				return true;
			}
		}
		return false;
	}
	
	public void updateCurrentChunkX(Direction direction) {
		Player player = getCurrentChunk().getPlayer();
		if(direction == Direction.RIGHT) {
			player.getPosition().setX(0);
			getCurrentChunk().setPlayer(null);
			increaseCurrentChunkPositionX();
		}
		else if(direction == Direction.LEFT) {
			getCurrentChunk().getPlayer().getPosition().setX(Chunk.WIDTH-1);
			getCurrentChunk().setPlayer(null);
			decreaseCurrentChunkPositionX();
		}
		getCurrentChunk().setPlayer(player);
	}
	
	public void updateCurrentChunkY(Direction direction) {
		Player player = getCurrentChunk().getPlayer();
		if(direction==Direction.UP) {
			player.getPosition().setY(0);
			getCurrentChunk().setPlayer(null);
			increaseCurrentChunkPositionY();
		}
		else if(direction==Direction.DOWN) {
			getCurrentChunk().getPlayer().getPosition().setY(Chunk.HEIGHT-1);
			getCurrentChunk().setPlayer(null);
			decreaseCurrentChunkPositionY();
		}
		getCurrentChunk().setPlayer(player);
	}
	
	/**
     * Getter getChunk
     * @param Vector2 chunkPosition, gets chunk on given chunk position      
     */
	public Chunk getChunk(Vector2 chunkPosition) {
		return chunks[chunkPosition.getX()][chunkPosition.getY()];
	}
	
	public Chunk getCurrentChunk() {
		return chunks[getCurrentChunkPosition().getX()][getCurrentChunkPosition().getY()];
	}
	
	/**
     * Getter getCurrentChunkPosition
     * gets active chunk position      
     */
	public Vector2 getCurrentChunkPosition() {
		return _currentChunkPosition;
	}
	
	/**
     * Method increaseCurrentChunkPositionX
     * increases active chunk x-position with one (1)    
     */
	public void increaseCurrentChunkPositionX() {
		_currentChunkPosition.addX(1);
	}
	
	/**
     * Method decreaseCurrentChunkPositionX
     * decreases active chunk x-position with one (1)    
     */
	public void decreaseCurrentChunkPositionX() {
		_currentChunkPosition.addX(-1);
	}
	
	/**
     * Method increaseCurrentChunkPositionY
     * increases active chunk y-position with one (1)    
     */
	public void increaseCurrentChunkPositionY() {
		_currentChunkPosition.addY(1);
	}
	
	/**
     * Method decreaseCurrentChunkPositionY
     * decreases active chunk y-position with one (1)    
     */
	public void decreaseCurrentChunkPositionY() {
		_currentChunkPosition.addY(-1);
	}
}
