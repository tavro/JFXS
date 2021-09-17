import java.util.Random;

public class Biome {
	
	private BiomeType type;
	private float _water;
	private float _trees;
	private float _mountains;
	
	public Biome() {
		type = getRandomBiomeType();
		_water = type.getWaterAmount();
		_trees = type.getTreeAmount();
		_mountains = type.getMountainAmount();
	}
	
	public static BiomeType getRandomBiomeType() {
		return BiomeType.values()[new Random().nextInt(BiomeType.values().length)];
	}
	
    public float getWaterAmount() {
    	return _water;
    }
    
    public float getMountainAmount() {
    	return _mountains;
    }
    
	public float getTreeAmount() {
		return _trees;
	}
	
}
