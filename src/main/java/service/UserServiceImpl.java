package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.Admin;
import model.User;
import util.CommonConstants;
import util.DBConnectionUtil;

public class UserServiceImpl implements IUserService {

	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		con = DBConnectionUtil.getDBConnection();
		
		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_LOGIN);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				return "customer";
			}
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_LOGIN_ADMIN);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Admin admin = new Admin();
				
				admin.setRole(rs.getString(CommonConstants.COLUMN_INDEX_SIX));
				
				return admin.getRole();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "Email or Password is incorrect";
	}

	
	public static void main(String[] args) {
		IUserService iUserService = new UserServiceImpl();
		System.out.println(iUserService.login("kasun@gmail.com", "2020kasun"));
	}


	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
