import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Notification extends JComponent {
	
	private char[] _title;
	private char[] _content;
	
	private Color _textColor;
	private Color _backgroundColor;
	
	private Vector2 _position;
	
	public Notification(Vector2 position, String title, String content) {
		_position = position;
		
		_title = title.toCharArray();
		_content = content.toCharArray();
		
		_textColor = Color.WHITE;
		_backgroundColor = Color.DARK_GRAY;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(_position.getX()-1,_position.getY()-1,_content.length*8+1,48+1);
		g.setColor(_backgroundColor);
		g.fillRect(_position.getX(),_position.getY(),_content.length*8,48);
		g.setColor(_textColor);
		g.drawChars(_title, 0, _title.length, _position.getX() + 8, _position.getY() + 16);
		g.drawChars(_content, 0, _content.length, _position.getX() + 8, _position.getY() + 32);
	}
}
