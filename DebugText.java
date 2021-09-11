import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

/**
 * DebugText.java
 * The Debug Text class is used for rendering
 * text in a JFrame.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class DebugText extends JComponent {
	
	private int _x;   
	private int _y;     
	private char[] _symbols;        //Basically a String
	private Color _color;    
	
	/**
     * Constructor      
     * @param String text, text to print in JFrame      
     * @param int x, pixel x-position of text in JFrame      
     * @param int y, pixel y-position of text in JFrame  
     * @param Color color, color of text    
     */
	public DebugText(String text, int x, int y, Color color) {
		_x = x;
		_y = y;
		_color = color;
		_symbols = text.toCharArray();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(_color);
		g.drawChars(_symbols, 0, _symbols.length, _x, _y+12);
	}
}
