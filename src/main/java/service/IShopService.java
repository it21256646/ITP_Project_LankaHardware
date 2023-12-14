package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Item;
import model.Shop;

public interface IShopService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IShopService.class.getName());

	/**
	 * get items to shop page
	 * 
	 * @param itemName
	 */
	public Shop getShop(String itemName);
	
	/**
	 * get items to shop page
	 * 
	 * @param main category
	 * @param lowerPrice
	 * @param higherPrice
	 * @param sortByValue
	 * @param itemName
	 * @param brand
	 * @param subType
	 * @param includeOutOfStock
	 * 
	 */
	public Shop getCustomizedItemList(String mainCategory, double lowerPrice, double higherPrice, String sortByValue, String itemName, String brand, String subType, boolean includeOutOfStock);
	
	/**
	 * get item size list to shop page
	 * 
	 * 
	 */
	public ArrayList<String> getItemSizeList(String itemID);
}
