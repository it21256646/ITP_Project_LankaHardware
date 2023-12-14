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

public class ChartServiceImpl implements IChartService {
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ChartServiceImpl.class.getName());

	@Override
	public ArrayList<Item> getAllItems() {
		// TODO Auto-generated method stub
		ArrayList<Item> items = new ArrayList<>();

		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_ALL_ITEMS);

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */

			try {
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

		return items;
	}
}
