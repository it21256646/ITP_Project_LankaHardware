package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;
import model.Item;
import model.Shop;
import util.CommonConstants;
import util.DBConnectionUtil;

public class ShopServiceImpl implements IShopService {

	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IndexServiceImpl.class.getName());

	@Override
	public Shop getShop(String itemName) {
		// TODO Auto-generated method stub

		Shop shop = new Shop();
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<String> mainCategories = new ArrayList<>();
		Map<String, ArrayList<String>> subCategories = new LinkedHashMap<>();
		IReviewService iReviewService = new ReviewServiceImpl();
		IProductSingleService iProductSingleService = new ProductSingleServiceImpl();
		IWishlistService iWishlistService = new WishlistServiceImpl();
		ICartService iCartService = new CartServiceImpl();
		Customer customer = new Customer();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_DETAILS_FOR_SHOP);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemName);
			rs = pst.executeQuery();

			while (rs.next()) {
				Item item = new Item();

				item.setItemID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setPrice(rs.getDouble(CommonConstants.COLUMN_INDEX_TWO));
				item.setName(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				item.setBrand(rs.getString(CommonConstants.COLUMN_INDEX_FOUR));
				item.setMainImg(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));
				item.setAvgRating(iReviewService.getAverageRating(item.getItemID()));
				item.setDescription(rs.getString(CommonConstants.COLUMN_INDEX_SIX));
				item.setStock(rs.getInt(CommonConstants.COLUMN_INDEX_SEVEN));

				items.add(item);
			}

			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_RELEVANT_ITEM_SIZE_FOR_SHOP);

			for (Item item : items) {
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
				pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, item.getPrice());
				rs = pst.executeQuery();
				rs.next();

				item.setSize(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setSizesAndPrizes(iProductSingleService.getProductSizeAndPriceList(item.getItemID()));
			}

			customer.setEmail("a@g.m");

			for (Item item : items) {
				item.setIsInWishlist(iWishlistService.checkIfItemIsInWishlist(customer, item));
			}

			for (Item item : items) {
				item.setSizesAndStock(iCartService.getSizesAndStock(item.getItemID()));
			}
			
			shop.setItems(items);

			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_MAIN_CATEGORIES_FOR_SHOP);
			rs.next();

			while (rs.next()) {
				mainCategories.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

			shop.setMainCategories(mainCategories);

			for (String main : mainCategories) {
				ArrayList<String> subList = new ArrayList<>();
				pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_SUB_CATEGORIES_FOR_SHOP);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, main);
				rs = pst.executeQuery();

				while (rs.next()) {
					subList.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				}

				subCategories.put(main, subList);
			}

			shop.setSubCategories(subCategories);

			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_MAX_AND_MIN_ITEM_PRICE_FOR_SHOP);
			rs.next();

			shop.setHighestPrice(rs.getDouble(CommonConstants.COLUMN_INDEX_ONE));
			shop.setLowestPrice(rs.getDouble(CommonConstants.COLUMN_INDEX_TWO));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return shop;
	}

	@Override
	public Shop getCustomizedItemList(String mainCategory, double lowerPrice, double higherPrice, String sortByValue,
			String itemName, String brand, String subType, boolean includeOutOfStock) {
		// TODO Auto-generated method stub

		Shop shop = new Shop();
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<String> brandList = new ArrayList<>();
		IReviewService iReviewService = new ReviewServiceImpl();
		IProductSingleService iProductSingleService = new ProductSingleServiceImpl();
		ICartService iCartService = new CartServiceImpl();
		con = DBConnectionUtil.getDBConnection();

		try {
			if (sortByValue.equals("Price: Low To High")) {
				if (includeOutOfStock == true) {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_ASC_INCLUDING_OUTOFSTOCK);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				} else {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_ASC);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				}

			} else if (sortByValue.equals("Price: High To Low")) {
				if (includeOutOfStock == true) {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_DESC_INCLUDING_OUTOFSTOCK);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				} else {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_DESC);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				}

			} else if (sortByValue.equals("Avg. Customer Review")) {
				if (includeOutOfStock == true) {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_RATING_DESC_INCLUDING_OUTOFSTOCK);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				} else {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_RATING_DESC);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				}

			} else if (sortByValue.equals("Newest Arrivals")) {
				if (includeOutOfStock == true) {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_NEWEST_ARRIVALS_INCLUDING_OUTOFSTOCK);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				} else {
					pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_NEWEST_ARRIVALS);
					pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
					pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, lowerPrice);
					pst.setDouble(CommonConstants.COLUMN_INDEX_THREE, higherPrice);
					pst.setString(CommonConstants.COLUMN_INDEX_FOUR, itemName);
					pst.setString(CommonConstants.COLUMN_INDEX_FIVE, brand);
					pst.setString(CommonConstants.COLUMN_INDEX_SIX, subType);
				}

			}

			rs = pst.executeQuery();

			while (rs.next()) {
				Item item = new Item();

				item.setItemID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setPrice(rs.getDouble(CommonConstants.COLUMN_INDEX_TWO));
				item.setName(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				item.setBrand(rs.getString(CommonConstants.COLUMN_INDEX_FOUR));
				item.setMainImg(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));
				item.setDescription(rs.getString(CommonConstants.COLUMN_INDEX_SIX));
				item.setStock(rs.getInt(CommonConstants.COLUMN_INDEX_SEVEN));

				items.add(item);
			}

			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_RELEVANT_ITEM_SIZE_FOR_SHOP);

			for (Item item : items) {
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
				pst.setDouble(CommonConstants.COLUMN_INDEX_TWO, item.getPrice());
				rs = pst.executeQuery();
				rs.next();

				item.setSize(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setAvgRating(iReviewService.getAverageRating(item.getItemID()));
				item.setSizesAndPrizes(iProductSingleService.getProductSizeAndPriceList(item.getItemID()));
			}

			for (Item item : items) {
				item.setSizesAndStock(iCartService.getSizesAndStock(item.getItemID()));
			}
			
			if (!mainCategory.equals("%%")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_BRAND_LIST_FOR_SHOP);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, mainCategory);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, subType);
				rs = pst.executeQuery();

				while (rs.next()) {
					brandList.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				}

				shop.setBrandList(brandList);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		shop.setItems(items);

		return shop;
	}

	@Override
	public ArrayList<String> getItemSizeList(String itemID) {
		// TODO Auto-generated method stub

		ArrayList<String> sizeList = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_SIZE_LIST_FOR_SHOP);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();

			while (rs.next()) {
				String temp = rs.getString(CommonConstants.COLUMN_INDEX_ONE);

				if (Character.isDigit(temp.charAt(0))) {
					if (temp.length() < 2) {
						sizeList.add(temp);
					} else {
						sizeList.add(temp.substring(0, 2));
					}
				} else {
					if (temp.length() < 1) {
						sizeList.add(temp);
					} else {
						sizeList.add(temp.substring(0, 1));
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return sizeList;
	}

	public static void main(String[] args) {
		IShopService iShopService = new ShopServiceImpl();
		Shop shop = iShopService.getShop("%%");

		System.out.println(shop.getSubCategories());
	}
}