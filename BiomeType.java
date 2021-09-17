
public enum BiomeType {
	HILLS(-1.0f, 0.3f, 1.0f),
	PLAINS(-0.55f, 0.85f, 0.75f),
	FORREST(-1.5f, 1.4f, 0.45f),
	OCEAN(-0.35f, 2.0f, 1.5f);
	
	private final float water;
	private final float mountains;
	private final float trees;

    private BiomeType(float water, float mountains, float trees) {
        this.water = water;
        this.mountains = mountains;
        this.trees = trees;
    }
    
    public float getWaterAmount() {
    	return water;
    }
    
    public float getMountainAmount() {
    	return mountains;
    }
    
	public float getTreeAmount() {
		return trees;
	}
}
