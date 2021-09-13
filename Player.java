/**
 * Player.java
 * The Player class represents a simplified real world player
 * that could be manipulated in different ways in order to
 * create an illusion of life.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Player extends Creature {
	
	public Container _inventory;        

	/**
     * Constructor      
     * @param String name, string showing up on screen    
     * @param Vector2 startPosition, world position of player      
     */
	public Player(String name, Vector2 position) {
		super(name, position, 20, 20.0f);
		
		/* instantiate & populate hotbar */
		_inventory = new Container(10);
		_inventory.addItem(new Item("Pickaxe", "Used for mining stone.", "src/pickaxe.png"));
		_inventory.addItem(new Item("Axe", "Used for cutting wood.", "src/axe.png"));
		_inventory.addItem(new Item("Sword", "Used for fighting enemies.", "src/sword.png"));
		_inventory.addItem(new Item("Shovel", "Used for digging dirt.", "src/shovel.png"));
	}
	
}
