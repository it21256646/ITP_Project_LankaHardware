package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Item;

public interface IMainProductSearch {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IMainProductSearch.class.getName());
	
	/**
	 * for search items
	 * @param itemName
	 */
	public ArrayList<Item> getSearchResults(String itemName);
}
