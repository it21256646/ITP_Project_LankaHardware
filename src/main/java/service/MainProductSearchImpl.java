package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Item;
import util.CommonConstants;
import util.DBConnectionUtil;

public class MainProductSearchImpl implements IMainProductSearch {

	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(MainProductSearchImpl.class.getName());

	@Override
	public ArrayList<Item> getSearchResults(String itemName) {
		// TODO Auto-generated method stub

		ArrayList<Item> items = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_MAIN_SEARCH_RESULTS);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemName);
			rs = pst.executeQuery();

			while (rs.next()) {
				Item item = new Item();

				item.setItemID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				item.setName(rs.getString(CommonConstants.COLUMN_INDEX_TWO));
				item.setDescription(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				
				items.add(item);
			}
			
			for (Item item : items) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_MAIN_SEARCH_RESULTS_IMAGES);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, item.getItemID());
				rs = pst.executeQuery();
				rs.next();
				
				item.setMainImg(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return items ;
	}
}
