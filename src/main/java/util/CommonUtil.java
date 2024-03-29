package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cart;

public class CommonUtil {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(Cart.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {

			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));

		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Generate a new ID
	 * 
	 * @param arrayList
	 * @param idType
	 * @return
	 */
	public static String generateIDs(ArrayList<String> arrayList, String idType) {

		String id = null;
		int next = arrayList.size() ;
		next++;

		if (idType.equals("cart")) {
			id = CommonConstants.CART_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.CART_ID_PREFIX + next;
			}
		} else if (idType.equals("wishlist")) {
			id = CommonConstants.WISHLIST_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.WISHLIST_ID_PREFIX + next;
			}
		} else if (idType.equals("item")) {
			next = next + 100;
			id = CommonConstants.ITEM_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.ITEM_ID_PREFIX + next;
			}
		} else if (idType.equals("review")) {
			id = CommonConstants.REVIEW_ID_PREFIX + next;
			if (arrayList.contains(id)) {
				next++;
				id = CommonConstants.REVIEW_ID_PREFIX + next;
			}
		} else if (idType.equals("question")) {
			id = CommonConstants.QUESTION_ID_PREFIX + next;
			while (arrayList.contains(id)) {
				next++;
				id = CommonConstants.QUESTION_ID_PREFIX + next;
			}
		} else if (idType.equals("employee")) {
			id = CommonConstants.EMPLOYEE_ID_PREFIX + next;
			while (arrayList.contains(id)) {
				next++;
				id = CommonConstants.EMPLOYEE_ID_PREFIX + next;
			}
		} else if (idType.equals("feedback")) {
			id = CommonConstants.FEEDBACK_ID_PREFIX + next;
			while (arrayList.contains(id)) {
				next++;
				id = CommonConstants.FEEDBACK_ID_PREFIX + next;
			}
		}else if (idType.equals("supplier")) {
			id = CommonConstants.SUPPLIER_ID_PREFIX + next;
			while(arrayList.contains(id)) {
				next++;
				id = CommonConstants.SUPPLIER_ID_PREFIX + next;
			}
		 }else if (idType.equals("voucher")) {
			 	next = next + 100;
				id = CommonConstants.VOUCHER_ID_PREFIX + next;
				while (arrayList.contains(id)) {
					next++;
					id = CommonConstants.VOUCHER_ID_PREFIX + next;
				}
	
		 }
		return id;
		
		}
	}
