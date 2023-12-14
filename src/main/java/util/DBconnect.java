package util;


import java.sql.*;

public class DBconnect {
	
	private static Connection connection;

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Auctioner?characterEncoding=latin1", "root", "kasun@34");
		}
		
		return connection;
	}

}

