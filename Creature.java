/**
 * Player.java
 * The Player class represents a simplified real world creature
 * that could be manipulated in different ways in order to
 * create an illusion of life.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Creature {
	
	private Vector2 _lastPosition;      //last frame position, used for handling collisions
	private Vector2 _position;
	
	private String _name;
	
	private int _maxHealth;
	private int _health;
	
	private float _maxHunger;
	private float _hunger;
	
	private boolean _isAlive;
	private boolean _isStarving;
	
	/**
     * Constructor      
     * @param String name, string showing up on screen    
     * @param Vector2 startPosition, world position of creature      
     * @param int health, max hit points of creature  
     * @param int hunger, max hunger of creature  
     */
	public Creature(String name, Vector2 position, int health, float hunger) {
		_maxHealth = health;
		_health = _maxHealth;
		
		_maxHunger = hunger;
		_hunger = 0.0f;
		
		_position = position;
		_lastPosition = new Vector2(_position.getX(), _position.getY());
		
		_name = name;
		
		_isAlive = true;
		_isStarving = false;
	}
	
	/**
     * Getter getPosition
     * gets creature position      
     */
	public Vector2 getPosition() {
		return _position;
	}
	
	/**
     * Getter getLastPosition
     * gets creatures last position 
     */
	public Vector2 getLastPosition() {
		return _lastPosition;
	}
	
	/**
     * Getter getName
     * gets name of creature      
     */
	public String getName() {
		return _name;
	}
	
	/**
     * Getter getIfAlive
     * gets if creature is currently alive      
     */
	public boolean getIfAlive() {
		return _isAlive;
	}
	
	/**
     * Getter getIfStarving
     * gets if creature is currently starving
     */
	public boolean getIfStarving() {
		return _isStarving;
	}
	
	/**
     * Setter setName
     * @param String name, sets creatures name 
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

	/**
     * Method heal
     * @param int amount, amount to heal      
     */
	public void heal(int amount) {
		_health += amount;
		if(_health > _maxHealth) {
			_health = _maxHealth;
		}
	}
	
	/**
     * Method eat
     * @param int amount, amount to eat      
     */
	public void eat(float amount) {
		_hunger -= amount;
		if(_hunger < 0) {
			_hunger = 0;
		}
		else if(_hunger < 20 && _isStarving) {
			_isStarving = false;
		}
	}
	
	/**
     * Method takeDamage
     * @param int amount, amount of damage to take      
     */
	public void takeDamage(int amount) {
		_health -= amount;
		if(_health < 0) {
			_isAlive = false;
		}
	}
	
	/**
     * Method getHunger
     * @param float amount, amount of hunger to get    
     */
	public void getHunger(float amount) {
		_hunger += amount;
		if(_hunger >= _maxHunger) {
			_hunger = _maxHunger;
			_isStarving = true;
		}
	}
	
}
