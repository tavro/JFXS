/**
 * Player.java
 * The Player class represents a simplified real world player
 * that could be manipulated in different ways in order to
 * create an illusion of life.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Player {
	
	public Container _inventory;        
	
	private Vector2 _lastPosition;      //last frame position, used for handling collisions
	private Vector2 _position;          
	
	private String _name;               
	
	/*
	public static final int MAX_HEALTH = 20;
	public static final float MAX_HUNGER = 20.0f;
	
	private int _health;
	private float _hunger;
	
	private boolean _isAlive;
	private boolean _isStarving;
	*/
	
	/**
     * Constructor      
     * @param String name, string showing up on screen    
     * @param Vector2 startPosition, world position of player      
     */
	public Player(String name, Vector2 position) {
		_position = position;
		_lastPosition = new Vector2(_position.getX(), _position.getY());
		_name = name;
		
		/*
		_isAlive = true;
		_isStarving = false;
		
		_health = MAX_HEALTH;
		_hunger = MAX_HUNGER;
		*/
		
		/* instantiate & populate hotbar */
		_inventory = new Container(10);
		_inventory.addItem(new Item("Pickaxe", "Used for mining stone.", "src/pickaxe.png"));
		_inventory.addItem(new Item("Axe", "Used for cutting wood.", "src/axe.png"));
		_inventory.addItem(new Item("Sword", "Used for fighting enemies.", "src/sword.png"));
		_inventory.addItem(new Item("Shovel", "Used for digging dirt.", "src/shovel.png"));
	}
	
	/**
     * Getter getPosition
     * gets player position      
     */
	public Vector2 getPosition() {
		return _position;
	}
	
	/**
     * Getter getLastPosition
     * gets players last position 
     */
	public Vector2 getLastPosition() {
		return _lastPosition;
	}
	
	/**
     * Getter getName
     * gets name of player      
     */
	public String getName() {
		return _name;
	}
	
	/**
     * Setter setName
     * @param String name, sets player name 
     */
	public void setName(String name) {
		_name = name;
	}
	
	/**
     * Setter setPositionToLastPosition
     * sets current position to last position    
     */
	public void setPositionToLastPosition() {
		_position = _lastPosition;
	}
	
	/**
     * Method move
     * @param int xAmount, amount to move on x-axis 
     * @param int yAmount, amount to move on y-axis     
     */
	public void move(int xAmount, int yAmount) {
		_lastPosition.setPosition(_position);	
		_position.addX(xAmount);
		_position.addY(yAmount);
	}
	
	/*
	public void heal(int amount) {
		_health += amount;
		if(_health > MAX_HEALTH) {
			_health = MAX_HEALTH;
		}
	}
	
	public void eat(float amount) {
		_hunger += amount;
		if(_hunger > MAX_HUNGER) {
			_hunger = MAX_HUNGER;
		}
	}
	
	public void takeDamage(int amount) {
		_health -= amount;
		if(_health < 0) {
			_isAlive = false;
		}
	}
	
	public void loseHunger(float amount) {
		_hunger -= amount;
		if(_hunger < 0) {
			_isStarving = true;
		}
	}
	
	public boolean getIfAlive() {
		return _isAlive;
	}
	
	public boolean getIfStarving() {
		return _isStarving;
	}
	*/

}
