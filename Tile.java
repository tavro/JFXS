import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

/**
 * Tile.java
 * The tile class is used for drawing materials to a JFrame.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Tile extends JComponent{
	
	private TileType _tileType;        
	private Vector2 _position;  
	private Image _image;  
	
	private boolean _isSolid; 
	
	/**
     * Constructor      
     * @param Vector2 position, world position of tile      
     * @param boolean isSolid, determines whether creatures can walk though tile or not      
     * @param TileType tileType, type of tile      
     * @param Image image, image rendered to JFrame      
     */
	public Tile(Vector2 position, boolean isSolid, TileType tileType, Image image) {
		_tileType = tileType;
		_position = position;
		_isSolid = isSolid;
		_image = image;
	}
	
	/**
     * Getter getTileType
     * gets tile type
     */
	public TileType getTileType() {
		return _tileType;
	}
	
	/**
     * Getter getPosition
     * gets position of tile in pixels
     */
	public Vector2 getPosition() {
		return _position;
	}
	
	/**
     * Getter getIfSolid
     * gets if the tile is solid or not 
     */
	public boolean getIfSolid() {
		return _isSolid;
	}
	
	/**
     * Getter getImage
     * gets image
     */
	public Image getImage() {
		return _image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(_image, (int)_position.getX()*32, (int)_position.getY()*32, getFocusCycleRootAncestor());
	}
}
