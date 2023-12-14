package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cart;
import model.CartChart;
import model.Item;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;

public class CartServiceImpl implements ICartService {

	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst, pst2, pst3, pst4, pst5, pst6;

	private static ResultSet rs, rs2, rs3, rs4, rs5;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public String getCartIdByEmail(String email) {
		// TODO Auto-generated method stub

		con = DBConnectionUtil.getDBConnection();
		Cart cart = new Cart();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_SPECIFIC_CART_ID);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, email);
			rs = pst.executeQuery();
			rs.next();

			cart.setCartID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));

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
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return cart.getCartID();
	}

	@Override
	public void createCart(String email) {
		// TODO Auto-generated method stub

		con = DBConnectionUtil.getDBConnection();
		ArrayList<String> ids = new ArrayList<>();
		Cart cart = new Cart();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_CART_IDS);

			while (rs.next()) {
				ids.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

			cart.setCartID(CommonUtil.generateIDs(ids, "cart"));

			pst = con.prepareStatement(CommonConstants.QUERY_ID_CREATE_CART);

			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, email);
			pst.executeUpdate();

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
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	@Override
	public String addToCart(String email, String itemID, int quantity, String size) {
		// TODO Auto-generated method stub

		Cart cart = getCart(email);
		Item item = new Item();
		int stock;
		String status = "There is a problem";

		item.setItemID(itemID);
		item.setQuantity(quantity);
		item.setSize(size);

		if (item.getSize().equals("notSpecified")) {
			item.setSize(getDefaultSizeAndPrice(item.getItemID()));
		}

		con = DBConnectionUtil.getDBConnection();

		try {

			stock = getItemStock(item);

			if (quantity > stock) {
				status = "Only " + stock + " items are available";
				return status;
			}

			stock = stock - quantity;

			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_CART);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setInt(CommonConstants.COLUMN_INDEX_THREE, item.getQuantity());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, item.getSize());
			pst.executeUpdate();

			pst = con.prepareStatement(CommonConstants.QUERY_ID_EDIT_STOCK);
			pst.setInt(CommonConstants.COLUMN_INDEX_ONE, stock);
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
			pst.executeUpdate();

			setTotal(email);

			status = "Added to cart";

		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO: handle exception

			ArrayList<Item> items = cart.getItems();

			for (Item currentItem : items) {
				if (currentItem.getItemID().equals(itemID) && currentItem.getSize().equals(size)) {
					quantity += currentItem.getQuantity();

					break;
				}
			}

			changeQuantity(email, itemID, quantity, size);

			status = "Added to cart";
		}

		catch (SQLException e) {
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

		return status;
	}

	@Override
	public void changeQuantity(String email, String itemID, int quantity, String size) {
		// TODO Auto-generated method stub
		Cart cart = new Cart();
		cart.setCartID(getCartIdByEmail(email));
		Item item = new Item();
		int stock;
		int previousQuantity;

		item.setItemID(itemID);
		item.setQuantity(quantity);
		item.setSize(size);

		con = DBConnectionUtil.getDBConnection();

		try {
			stock = getItemStock(item);

			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_QUANTITY);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
			rs = pst.executeQuery();
			rs.next();

			previousQuantity = rs.getInt(CommonConstants.COLUMN_INDEX_ONE);

			stock = stock - (quantity - previousQuantity);

			pst = con.prepareStatement(CommonConstants.QUERY_ID_EDIT_STOCK);
			pst.setInt(CommonConstants.COLUMN_INDEX_ONE, stock);
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
			pst.executeUpdate();

			pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_QUANTITY);
			pst.setInt(CommonConstants.COLUMN_INDEX_ONE, item.getQuantity());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, item.getSize());
			pst.executeUpdate();

			setTotal(email);

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
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	@Override
	public String clearOneItemFromCart(String email, String itemID, String size) {
		// TODO Auto-generated method stub

		Cart cart = new Cart();
		Item item = new Item();
		cart.setCartID(getCartIdByEmail(email));
		String status = "There is a problem";
		int stock;
		int quantity;
		item.setItemID(itemID);
		item.setSize(size);

		con = DBConnectionUtil.getDBConnection();

		try {
			stock = getItemStock(item);

			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_QUANTITY);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
			rs = pst.executeQuery();
			rs.next();

			quantity = rs.getInt(CommonConstants.COLUMN_INDEX_ONE);

			stock = stock + quantity;

			pst = con.prepareStatement(CommonConstants.QUERY_ID_EDIT_STOCK);
			pst.setInt(CommonConstants.COLUMN_INDEX_ONE, stock);
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
			pst.executeUpdate();

			pst.close();

			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_SPECIFIC_ITEM_FROM_CART);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, itemID);
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, size);
			pst.executeUpdate();

			setTotal(email);

			status = "Removed from cart";

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
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return status;
	}

	@Override
	public String clearCart(String email) {
		// TODO Auto-generated method stub

		Cart cart = new Cart();
		cart.setCartID(getCartIdByEmail(email));
		String status = "There is a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_CART);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			pst.executeUpdate();

			setTotal(email);

			status = "Cart cleared";

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
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return status;
	}

	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub

		Cart cart = new Cart();
		cart.setCartID(getCartIdByEmail(email));

		con = DBConnectionUtil.getDBConnection();
		ArrayList<Item> items = new ArrayList<>();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_CART);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, cart.getCartID());
			rs2 = pst.executeQuery();

			while (rs2.next()) {
				Item item = new Item();

				item.setItemID(rs2.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setQuantity(rs2.getInt(CommonConstants.COLUMN_INDEX_TWO));
				item.setSize(rs2.getString(CommonConstants.COLUMN_INDEX_THREE));

				items.add(item);
			}

			for (Item item : items) {
				pst2 = con.prepareStatement(CommonConstants.QUERY_ID_GET_OTHER_ITEM_DETAILS_FOR_CART);
				pst2.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
				pst2.setString(CommonConstants.COLUMN_INDEX_TWO, item.getItemID());
				pst2.setString(CommonConstants.COLUMN_INDEX_THREE, item.getSize());
				pst2.setString(CommonConstants.COLUMN_INDEX_FOUR, item.getItemID());
				pst2.setString(CommonConstants.COLUMN_INDEX_FIVE, item.getSize());
				pst2.setString(CommonConstants.COLUMN_INDEX_SIX, item.getItemID());
				rs4 = pst2.executeQuery();
				rs4.next();

				item.setName(rs4.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setBrand(rs4.getString(CommonConstants.COLUMN_INDEX_TWO));
				item.setDescription(rs4.getString(CommonConstants.COLUMN_INDEX_THREE));
				item.setMainImg(rs4.getString(CommonConstants.COLUMN_INDEX_FOUR));
				item.setPrice(rs4.getDouble(CommonConstants.COLUMN_INDEX_FIVE));
				item.setStock(rs4.getInt(CommonConstants.COLUMN_INDEX_SIX));
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
				if (pst2 != null) {
					pst2.close();
				}
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		cart.setItems(items);
		cart.setTotal(calculateTotal(items));

		return cart;
	}

	@Override
	public double calculateTotal(ArrayList<Item> items) {
		// TODO Auto-generated method stub

		double total = CommonConstants.DELIVERY_FEE;

		for (Item item : items) {
			total += item.getPrice() * item.getQuantity();
		}

		return total;
	}

	@Override
	public void setTotal(String email) {
		// TODO Auto-generated method stub
		Cart cart = getCart(email);
		con = DBConnectionUtil.getDBConnection();

		try {
			pst4 = con.prepareStatement(CommonConstants.QUERY_ID_SET_CART_TOTAL);
			pst4.setDouble(CommonConstants.COLUMN_INDEX_ONE, cart.getTotal());
			pst4.setString(CommonConstants.COLUMN_INDEX_TWO, cart.getCartID());
			pst4.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst4 != null) {
					pst4.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	@Override
	public String getDefaultSizeAndPrice(String itemID) {
		// TODO Auto-generated method stub

		String size = null;
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_DEFAULT_SIZE);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();
			rs.next();

			size = rs.getString(CommonConstants.COLUMN_INDEX_ONE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, e.getMessage());
		}

		return size;
	}

	@Override
	public HashMap<String, Integer> getSizesAndStock(String itemID) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> sizesAndStock = new HashMap<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_SIZES_AND_STOCK);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();

			while (rs.next()) {
				sizesAndStock.put(rs.getString(CommonConstants.COLUMN_INDEX_ONE),
						rs.getInt(CommonConstants.COLUMN_INDEX_TWO));
			}

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
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return sizesAndStock;
	}

	@Override
	public int getItemStock(Item item) {
		// TODO Auto-generated method stub
		int stock = 0;

		try {
			pst3 = con.prepareStatement(CommonConstants.QUERY_ID_GET_STOCK);
			pst3.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
			pst3.setString(CommonConstants.COLUMN_INDEX_TWO, item.getSize());
			rs3 = pst3.executeQuery();
			rs3.next();

			stock = rs3.getInt(CommonConstants.COLUMN_INDEX_ONE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst3 != null) {
					pst3.close();
				}
				if (rs3 != null) {
					rs3.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return stock;
	}

	@Override
	public CartChart getChartDetails(String itemID) {
		// TODO Auto-generated method stub

		ArrayList<String> sizes = new ArrayList<>();
		LinkedHashMap<String, ArrayList<Integer>> counts = new LinkedHashMap<>();
		CartChart cartChart = new CartChart();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst5 = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_SIZES);
			pst5.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs5 = pst5.executeQuery();

			while (rs5.next()) {
				sizes.add(rs5.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

			pst6 = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_COUNT);
			pst6.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);

			for (String size : sizes) {
				ArrayList<Integer> count = new ArrayList<>();
				pst6.setString(CommonConstants.COLUMN_INDEX_TWO, size);
				count.add(0);
				
				for (int i = 1; i < 13; i++) {
					pst6.setInt(CommonConstants.COLUMN_INDEX_THREE, i);
					rs5 = pst6.executeQuery();
					rs5.next();

					count.add(rs5.getInt(CommonConstants.COLUMN_INDEX_ONE));
				}

				counts.put(size, count);
			}

			cartChart.setSizes(sizes);
			cartChart.setCounts(counts);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
				if (pst5 != null) {
					pst5.close();
				}
				if (pst6 != null) {
					pst6.close();
				}
				if (rs5 != null) {
					rs5.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return cartChart;
	}
}