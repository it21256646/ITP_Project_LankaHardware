package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionIsuru {
	private static String url = "jdbc:mysql://localhost:3306/lanka_hardware";
	private static String userName = "root";
	private static String password = "1234";
	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
			
		}catch(Exception e) {
			System.out.println("Database connection is not Success!!");
		}
		
		return con;
	}
	

}
