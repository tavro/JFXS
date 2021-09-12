import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Slot.java
 * The Slot class is used for rendering
 * a inventory hotbar in a JFrame.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Slot extends JComponent {
	private Vector2 _position;
	private int _width;     
	private int _height;
	private Color _color;      
	private char[] _symbols = new char[2];        //limited to a size of 2 since there can never be more than 99 items
	
	private Image _img;        
	private boolean _isSelected;       
	
	/**
     * Constructor      
     * @param Vector2 position, position of slot in JFrame      
     * @param int width, width of slot in pixels 
     * @param int height, height of slot in pixels    
     * @param char[] symbols, amount of items in slot
     * @param Color color, color of outline
     * @param Image image, image representing item
     * @param boolean isSelected, tells if the slot is selected or not  
     */
	public Slot(Vector2 position, int width, int height, char[] symbols, Color color, Image image, boolean isSelected) {
		_position = position;
		_width = width;
		_height = height;
		_color = color;
		_symbols = symbols;
		_isSelected = isSelected;
		
		if(image != null) {
			_img = image;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(_color);
		
		if(_img != null) {
			g.drawImage(_img, _position.getX(), _position.getY(), getFocusCycleRootAncestor());
		}
		
		if(_isSelected) {
			g.setColor(Color.RED);
		}
		g.drawRect(_position.getX(), _position.getY(), _width, _height);
		g.setColor(Color.BLACK);
		g.drawChars(_symbols, 0, _symbols.length, _position.getX()+24, _position.getY()+12);
	}
}