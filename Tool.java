
public class Tool extends Item {

	private int _usesLeft;
	
	public Tool(String name, String description, String imgPath) {
		super(name, description, imgPath);
		_usesLeft = 128;
	}
	
	public int getUsesLeft() {
		return _usesLeft;
	}
	
	public void use() {
		_usesLeft--;
	}

}
