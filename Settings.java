
public class Settings {
	public String SEED;
	
	public int CHUNK_WIDTH;
	public int CHUNK_HEIGHT;
	
	public int WORLD_WIDTH;
	public int WORLD_HEIGHT;
	
	public Settings(String seed, int c_width, int c_height, int w_width, int w_height) {
		SEED = seed;
		
		CHUNK_WIDTH = c_width;
		CHUNK_HEIGHT = c_height;
		
		WORLD_WIDTH = w_width;
		WORLD_HEIGHT = w_height;
	}
}
