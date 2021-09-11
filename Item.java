import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Item.java
 * The Item class represents an item
 * with the help of text and pixels.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Item {
	private String _name;        
	private String _description;        
	private Image _img;      
	
	/**
     * Constructor      
     * @param String name, string showing up on screen when hovering over item     
     * @param String description, string showing up on screen when hovering over item   
     * @param String imgPath, path to image location on disc    
     */
	public Item(String name, String description, String imgPath) {
		_name = name;
		_description = description;
		
		/* tries to read Image from imgPath */
		if(imgPath != "") {
			try {
				_img = ImageIO.read(new File(imgPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
     * Getter getName
     * gets item name     
     */
	public String getName() {
		return _name;
	}
	
	/**
     * Getter getDescription
     * gets item description    
     */
	public String getDescription() {
		return _description;
	}
	
	/**
     * Getter getImage
     * gets item image
     */
	public Image getImage() {
		return _img;
	}
	
	/**
     * Setter setName
     * @param String name, sets item name
     */
	public void setName(String name) {
		_name = name;
	}
	
	/**
     * Setter setDescription
     * @param String description, sets item description
     */
	public void setDescription(String description) {
		_description = description;
	}
}
