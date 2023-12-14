package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.JsonElement;

import model.Admin;
import model.Cart;
import model.Employee;
import model.Item;
import model.Wishlist;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;
import util.DBconnect;

public class AdminServiceImpl implements IAdminService {
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public ArrayList<Admin> getAllAdmin() {
		// TODO Auto-generated method stub

		ArrayList<Admin> admins = new ArrayList();
		con = DBConnectionUtil.getDBConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_ADMIN);

			while (rs.next()) {
				Admin admin = new Admin();

				admin.setEmail(rs.getString(1));
				admin.setPassword(rs.getString(2));
				admin.setPhone(rs.getString(3));
				admin.setName(rs.getString(4));
				admin.setAddress(rs.getString(5));
				admin.setRole(rs.getString(6));
				

				admins.add(admin);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return admins;
	}

	@Override
	public String addAdmin(Admin admin, Collection<Part> parts) {
		// TODO Auto-generated method stub

		String status = "There was something wrong";
		ArrayList<String> imagePathArrayList = new ArrayList<String>();
		ArrayList<String> Email = new ArrayList<String>();

		con = DBConnectionUtil.getDBConnection();

		// Configure to upload to cloudinary
		Map config = new HashMap();
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
		}

		try {
			st = con.createStatement();
			rs= st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_ADMIN);
			
			while (rs.next()) {
				Email.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
			
		    admin.setEmail(CommonUtil.generateIDs(Email, "admin"));
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_ADMIN);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, admin.getEmail());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, admin.getPassword());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, admin.getPhone());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, admin.getName());
			pst.setString(CommonConstants.COLUMN_INDEX_FIVE, admin.getAddress());
			pst.setString(CommonConstants.COLUMN_INDEX_SIX, admin.getRole());
			

			for (String string : imagePathArrayList) {
				pst.setString(CommonConstants.COLUMN_INDEX_ELEVEN, string);
			}

			pst.executeUpdate();

			status = "Admin Added";

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


	public static void main(String[] args) {
		IAdminService iAdminService = new AdminServiceImpl();
		System.out.println(iAdminService.removeAdmin("emp9"));
	}

	@Override
	public String removeAdmin(String Email) {
		// TODO Auto-generated method stub
		
		Admin admin = new Admin();
		admin.setEmail(Email);
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_ADMIN);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, admin.getEmail());

			pst.executeUpdate();
			
			System.out.println("done");

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

		return "Admin removed";
	}

	
	
	public String updateAdmin(String Email, String password, String phone, String name, String Address, String Role) {
		// TODO Auto-generated method stub

		String status = "There was a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			if(!name.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_EMAIL);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, Email);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
			if(!password.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_EMAIL);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, password);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
			if(!phone.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_DESIGNATION);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, phone);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
			if(!name.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_PHONENUM);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, name);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
			if(!Address.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_ADDRESS);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, Address);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
		
			if(!Role.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_EMPLOYEES_DATE);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, Role);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, Email);
				pst.executeUpdate();
			}
		
			
			
		
			

			status = "Admin Updated";

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

		return status;
	}

	



	
	
	
}
