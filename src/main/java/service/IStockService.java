package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import model.Item;

public interface IStockService {
	public static final Logger log = Logger.getLogger(IStockService.class.getName());

	public ArrayList<Item> getAllStockItems();
	
	public ArrayList<Item> GetAllStoreItemSortBy(int sort);
	
	public ArrayList<Item> getSearchedItems(String searchDetails);
	
	public String addStockItems(Item item); 

	public String removeStockItems(String itemID);

	public String updateStockItems(String id, String name, String cat, String Bra, double pr, int quan, String Des, String mf,String exp, String wt, String wn, String wp);

	
	

}
