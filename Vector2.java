/**
 * Vector2.java
 * The Vector2 class is representing a position
 * on the screen.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Vector2 {
	private int _x;        
	private int _y;        
	
	/**
     * Constructor      
     * @param int x, x-position      
     * @param int y, y-position      
     */
	public Vector2(int x, int y) {
		_x = x;
		_y = y;
	}
	
	/**
     * Method addX
     * @param int amount, adds amount to x-axis
     */
	public void addX(int amount) {
		_x += amount;
	}
	
	/**
     * Method addY
     * @param int amount, adds amount to y-axis
     */
	public void addY(int amount) {
		_y += amount;
	}
	
	/**
     * Getter getX
     * gets x-position     
     */
	public int getX() {
		return _x;
	}
	
	/**
     * Getter getY
     * gets y-position
     */
	public int getY() {
		return _y;
	}
	
	/**
     * Setter setX
     * @param int x, sets x-position   
     */
	public void setX(int x) {
		_x = x;
	}
	
	/**
     * Setter setY
     * @param int y, sets y-position  
     */
	public void setY(int y) {
		_y = y;
	}
	
	/**
     * Setter setPosition
     * @param Vector2 pos, sets x- and y-position
     */
	public void setPosition(Vector2 pos) {
		_x = pos.getX();
		_y = pos.getY();
	}
}
