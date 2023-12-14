package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import model.Customer;
import model.Item;
import model.WishlistChart;

public interface IWishlistService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ICartService.class.getName());

	/**
	 * get wishlist id by email
	 * 
	 * @param email
	 */
	public String getWishlistIdByEmail(String email);
	
	/**
	 * create wishlist
	 * 
	 * @param email
	 */
	public void createWishlist(String email);
	
	/**
	 * add to wishlist
	 * 
	 * @param customer
	 * @param item
	 * 
	 */
	public String addToWishlist(Customer customer, Item item);
	
	/**
	 * remove from wishlist
	 * 
	 * @param customer
	 * @param item
	 * 
	 */
	public String removeFromWishlist(Customer customer, Item item);
	
	/**
	 * get wishlist items
	 * 
	 * @param customer
	 */
	public ArrayList<Item> getWishlist(Customer customer);
	
	/**
	 * send back in stock email to customers
	 * 
	 * @param itemID
	 */
	public String sendBackInStockEmail(String itemID);
	
	/**
	 * send back in stock email to customers
	 * 
	 * @param customer
	 * @param item
	 * 
	 */
	public LinkedHashMap<String, Boolean> checkIfItemIsInWishlist(Customer customer, Item item);
	
	/**
	 * get chart details
	 * @param itemID
	 */
	public WishlistChart getChartDetails(String itemID);
	
}
