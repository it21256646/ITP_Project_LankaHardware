package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



import model.Item;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionIsuru;
import util.DBConnectionUtil;

public class IStockServiceImpl implements IStockService {
	private static Connection con;

	private static Statement st;
	private static PreparedStatement pst;
	
	

	private static ResultSet rs;



	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IStockServiceImpl.class.getName());

	@Override
	public ArrayList<Item> getAllStockItems() {
		// TODO Auto-generated method stub

		ArrayList<Item> items = new ArrayList<>();
		//ArrayList<String> images = new ArrayList<>();
		
		con = DBConnectionIsuru.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_Stock_ITEMS);


			
			while (rs.next()) {
				Item item = new Item();
				
				 item.setItemID(rs.getString(1));
				 item.setName(rs.getString(2));
				 item.setType(rs.getString(3));
				 item.setBrand(rs.getString(4));
				 item.setQuantity(rs.getInt(5));
				 item.setPrice(rs.getDouble(6));
				 item.setDescription(rs.getString(7));
				 item.setMfDate(rs.getString(8));
				 item.setExpDate(rs.getString(9));
				 //item.setSize(rs.getString(9));
				// item.setMainImg(rs.getString(12));			
				 item.setWarrentyType(rs.getString(10));
				 item.setWarrentyNumber(rs.getInt(11));
				 item.setWarrantyPeriod(rs.getString(12));


				items.add(item);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public String addStockItems(Item  item) {
		// TODO Auto-generated method stub

		String status = "failed";
		String newItemID;
		ArrayList<String> imagePathArrayList = new ArrayList<String>();
		ArrayList<String> stockIds = new ArrayList<String>();
		
		/*
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", "dqgiitni2");
		config.put("api_key", "987952682616387");
		config.put("api_secret", "0Zw3qi4VX6XjfMh9LYSDYVdyOns");
		Cloudinary cloudinary = new Cloudinary(config);

		for (Part part : parts) {
			if (part.getSubmittedFileName() != null) {

				try {
					InputStream is = part.getInputStream();

					File tempFile = File.createTempFile("javaMyfile", ".xls");
					FileUtils.copyToFile(is, tempFile);

					System.out.println(tempFile.getName());
					System.out.println(tempFile.exists());

					// Upload to cloudinary
					try {
						Map<String, String> map = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap());
						imagePathArrayList.add(map.get("url"));
					} catch (IOException exception) {
						System.out.println(exception.getMessage());
					}

					System.out.println("deleting " + tempFile.getAbsolutePath() + " " + tempFile.delete());
					System.out.println(tempFile.exists());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} */
		
		con = DBConnectionIsuru.getConnection();

		try {
			st = con.createStatement();
			rs= st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_Stock_IDS);
			
			while (rs.next()) {
				stockIds.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
			
			item.setItemID(CommonUtil.generateIDs(stockIds, "item"));
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_stock_item);
			
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, item.getName());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, item.getType());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, item.getDescription());
			pst.setString(CommonConstants.COLUMN_INDEX_FIVE, item.getBrand());
			pst.setDouble(6, item.getPrice());
			pst.setInt(7, item.getQuantity());
			pst.setString(8, item.getMfDate());
			pst.setString(9, item.getExpDate());
			//pstI.setString(CommonConstants.COLUMN_INDEX_EIGHT,item.getSubType());
			
			
			/*pstS = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_stock_item_size);
			pstS.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
			pstS.setString(CommonConstants.COLUMN_INDEX_TWO, item.getSize());
			pstS.setDouble(CommonConstants.COLUMN_INDEX_THREE, item.getPrice());
			pstS.setInt(CommonConstants.COLUMN_INDEX_FOUR, item.getQuantity());*/
			
			
			//pstW = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_stock_item_War);
			//pst.setString(8, item.getItemID());
			pst.setString(10, item.getWarrentyType());
			pst.setInt(11, item.getWarrentyNumber());
			pst.setString(12, item.getWarrantyPeriod());
	
			
			
			
			/*for (String string : imagePathArrayList) {
				pstImg.setString(CommonConstants.COLUMN_INDEX_ELEVEN, string);
			}*/

			pst.executeUpdate();
			
			status = "Stock Item Added";
			newItemID = item.getItemID();
			System.out.println("IstockImpl generated id if success: " + newItemID );
			System.out.println("Status in Istockimpl: " + status);
			
			return newItemID;
			
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
		System.out.println("This is the status: "+ status);
		return status;

	}




	@Override
	public String removeStockItems(String stockId) {
		// TODO Auto-generated method stub
		
		Item item = new Item();
		item.setItemID(stockId);
		
		con = DBConnectionIsuru.getConnection();
		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_StockItem);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());

			pst.executeUpdate();
			
			System.out.println("Delete record done : " + stockId);

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

		return "Stock Item " + stockId + " is removed";
	}

	
	@Override
	public String updateStockItems(String id, String name, String cat, String Bra, double pr, int quan, String Des, String mf,String exp, String wt, String wn, String wp) {
		// TODO Auto-generated method stub

		String status = "There was a problem";
		
		con = DBConnectionUtil.getDBConnection();

		try {
			if(!name.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_NAME);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, name);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			if(!cat.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_CAT);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, cat);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			if(!Bra.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_BRAND);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, Bra);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			String price = Double.toString(pr);
			
			if(!price.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_PRICE);
				pst.setDouble(CommonConstants.COLUMN_INDEX_ONE, pr);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			if(quan > 0) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_QUANTITY);
				pst.setInt(CommonConstants.COLUMN_INDEX_ONE, quan);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
		
			if(!Des.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_DESCRIPTION);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, Des);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
		
			if(!mf.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_MF);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, mf);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			if(!exp.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_EXP);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, exp);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			if(!WarrantyType.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_WARTYPE);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, WarrantyType);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			if(warrentyNum != 0) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_WARNUM);
				pst.setInt(CommonConstants.COLUMN_INDEX_ONE, warrentyNum);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			if(!warPeriod.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_ITEM_WARPERIOD);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, warPeriod);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
	

			status = "Stock Item Updated";

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
		System.out.println("This is the status on update: " + status);
		return status;
	}

	@Override
	public ArrayList<Item> getSearchedItems(String searchDetails) {
		ArrayList<Item> items = new ArrayList<>();
		
		System.out.println("This is impl");
		String sql = "SELECT id, name, category, description, brand, price, quantity, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod FROM item_m where id LIKE '%"+ searchDetails +"%' or name LIKE '%"+ searchDetails +"%' or category LIKE '%"+ searchDetails +"%' or brand LIKE '%"+ searchDetails +"%' or description LIKE '%"+ searchDetails +"%' group by id;";

		con = DBConnectionIsuru.getConnection();
		try {
			st = con.createStatement();
		
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Item item = new Item();
				
				 item.setItemID(rs.getString(1));
				 item.setName(rs.getString(2));
				 item.setType(rs.getString(3));
				 item.setDescription(rs.getString(4));
				 item.setBrand(rs.getString(5));
				 item.setPrice(rs.getDouble(6));
				 item.setQuantity(rs.getInt(7));
				 item.setMfDate(rs.getString(8));
				 item.setExpDate(rs.getString(9));
				 item.setWarrentyType(rs.getString(10));
				 item.setWarrentyNumber(rs.getInt(11));
				 item.setWarrantyPeriod(rs.getString(12));


				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	@Override
	public ArrayList<Item> GetAllStoreItemSortBy(int sort) {
		// TODO Auto-generated method stub
		System.out.println("IstockImpl before connection is done");
		ArrayList<Item> items = new ArrayList<>();
		con = DBConnectionIsuru.getConnection();
		try {
			st = con.createStatement();
			
			if(sort == 1) {
				System.out.println("IstockImpl if sortby == 1 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_ID);
			}
			else if(sort == 2) {
				System.out.println("IstockImpl if sortby == 2 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_NAME);
			}
			else if(sort == 3) {
				System.out.println("IstockImpl if sortby == 3 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_Cat);			
			}
			else if(sort == 4) {
				System.out.println("IstockImpl if sortby == 4 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_MF);
			}
			else if(sort == 5) {
				System.out.println("IstockImpl if sortby == 5 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_EXP);
			}
			else if(sort == 6) {
				System.out.println("IstockImpl if sortby == 6 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_PRICE);
			}
			
			else {
				rs = st.executeQuery(CommonConstants.QUERY_ID_GET_Stock_ITEMS);
			}

			

			while (rs.next()) {
				Item item = new Item();
				
				 item.setItemID(rs.getString(1));
				 item.setName(rs.getString(2));
				 item.setType(rs.getString(3));
				// item.setSubType(rs.getString(4));
				 item.setDescription(rs.getString(4));
				 item.setBrand(rs.getString(5));
				 item.setQuantity(rs.getInt(6));
				 item.setPrice(rs.getDouble(7));
				 item.setMfDate(rs.getString(8));
				 item.setExpDate(rs.getString(9));
			    // item.setSize(rs.getString(9));
				
				 //item.setMainImg(rs.getString(12));			
				 item.setWarrentyType(rs.getString(10));
				 item.setWarrentyNumber(rs.getInt(11));
				 item.setWarrantyPeriod(rs.getString(12));

				items.add(item);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
		
		
	}



	


}

	
