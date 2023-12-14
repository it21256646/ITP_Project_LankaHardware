package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Item;

public interface IIndexService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IIndexService.class.getName());
	
	/**
	 * get new arrivals
	 */
	public ArrayList<Item> getNewArrivals();
}
