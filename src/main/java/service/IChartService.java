package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Item;

public interface IChartService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IChartService.class.getName());
	
	/**
	 * get all the items
	 */
	public ArrayList<Item> getAllItems();
}
