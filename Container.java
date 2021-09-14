import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container.java
 * The Container class represents list of item 
 * names mapped to the amount of those items.
 *
 * @version 1.0 11 Sep 2021  
 * @author Isak Horvath  
 */
public class Container {
	private int _maxAmount;        
	private int _selectedItemIndex;        //position of active item
	
	List<Item> realItems = new ArrayList<Item>();
	Map<String, Integer> items;            //map item names to amount of specified item
	
	/**
     * Constructor      
     * @param in maxAmount, amount of unique items allowed in container
     */
	public Container(int maxAmount) {
		_maxAmount = maxAmount;
		_selectedItemIndex = 0;
		items = new HashMap<String, Integer>();
	}
	
	/**
     * Method addItem
     * @param Item item, adds item to container
     */
	public void addItem(Item item) {
		if(items.containsKey(item.getName())) {
			int newValue = items.get(item.getName()) + 1;
			items.replace(item.getName(), items.get(item.getName()), newValue);
		}
		else {
			if(items.size() < _maxAmount) {
				realItems.add(item);
				items.put(item.getName(), 1);
			}
		}
	}
	
	/**
     * Getter getChunk
     * gets amount of slots     
     */
	public int getAmountOfSlots() {
		return _maxAmount;
	}
	
	/**
     * Getter getChunk
     * gets index of selected item      
     */
	public int getSelectedItemIndex() {
		return _selectedItemIndex;
	}
	
	/**
     * Method selectItem
     * @param int index, selects item on given index
     */
	public void selectItem(int index) {
		_selectedItemIndex = index;
		if(_selectedItemIndex < 0) {
			_selectedItemIndex = _maxAmount-1;
		}
		else if(_selectedItemIndex >= _maxAmount) {
			_selectedItemIndex = 0;
		}
	}
	
	/**
     * Getter getSelectedItem
     * gets name of item in selected slot
     */
	public String getSelectedItem() {
		int x = 0;
		for (Map.Entry<String, Integer> pair : items.entrySet()) {
			if(x == _selectedItemIndex) {
				return pair.getKey();
			}
			x++;
		}
		return null;
	}
}
