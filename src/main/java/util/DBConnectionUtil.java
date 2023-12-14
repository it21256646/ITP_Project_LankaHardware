package util;

import java.sql.*;

public class DBConnectionUtil extends CommonUtil {

	private static Connection con;

	// This works according to singleton pattern
	private DBConnectionUtil() {
	}

	public static Connection getDBConnection() {
		try {
			if (con == null || con.isClosed()) {

				Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));
				con = DriverManager.getConnection(properties.getProperty(CommonConstants.URL), properties.getProperty(CommonConstants.USERNAME), properties.getProperty(CommonConstants.PASSWORD));

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
