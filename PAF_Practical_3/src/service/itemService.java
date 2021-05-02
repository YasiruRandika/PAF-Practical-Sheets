package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Item;

/* 
 * @author W.G. YASIRU RANDIKA 
 * IT19131184
 * 
 * */

public interface itemService {
	// Initializing logger
	public static final Logger logger = Logger.getLogger(itemService.class.getName());
	
	/**
	 * InsertItem
	 * 
	 * This method will insert an item to the mysql database
	 * 
	 * @return string 
	 * @param code String
	 * @param name String
	 * @param price double
	 * @param desc String
	 */
	public String insertItem(String code, String name, String price, String desc);
	
	/**
	 * Get Items
	 * 
	 * This method will get the items from  mysql database
	 * @return ArrayList of Items
	 */
	public ArrayList<Item> getItems();
	
	/**
	 * Delete Item
	 * 
	 * This method will delete an item from  mysql database
	 * @param item Id
	 *
	 * @return status
	 */
	public String deleteItem(int id);
	
	/**
	 * Update Item
	 * 
	 * This method will get the items from  mysql database
	 * @param code String
	 * @param name String
	 * @param price double
	 * @param desc String
	 * @return status
	 */
	public String updateItem(int id, String code, String name, String price, String desc);
	
	
	/**
	 * Get Item By Id
	 * 
	 * This method will get an item from  mysql database
	 * @param id Integer
	 * @return Item
	 */
	public Item getItemById(int id);

}
