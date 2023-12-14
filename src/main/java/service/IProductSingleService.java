package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import model.Item;

public interface IProductSingleService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IProductSingleService.class.getName());
	
	/**
	 * for product-single page
	 * @param itemID
	 */
	public Item getProduct(String itemID);
	
	/**
	 * get product size list for product-single page
	 * @param itemID
	 */
	public LinkedHashMap<String, Double> getProductSizeAndPriceList(String itemID);
	
	/**
	 * get product size list for product-single page
	 * @param itemID
	 */
	public ArrayList<Item> getRelatedProducts(String itemID);
}
