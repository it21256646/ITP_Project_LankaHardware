package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Voucher;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionIsuru;
import util.DBConnectionUtil;

public class IVoucherServiceImpl implements IVoucherService {
	
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;
	
	public static final Logger log = Logger.getLogger(IVoucherServiceImpl.class.getName());

	@Override
	public ArrayList<Voucher> getAllVouchers() {
		ArrayList<Voucher> vouchers = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_Voucher);

			while (rs.next()) {
				Voucher voucher = new Voucher();
				
				voucher.setId(rs.getString(1));
				voucher.setCode(rs.getString(2));
				voucher.setAmount(rs.getInt(3));
				voucher.setExp(rs.getString(4));
			

				vouchers.add(voucher);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vouchers;
	}

	@Override
	public ArrayList<Voucher> GetAllgetAllVouchersSortBy(int sort) {
		ArrayList<Voucher> vouchers = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();
		try {
			st = con.createStatement();
			

			if(sort == 1) {
				System.out.println("IstockImpl if sortby == 1 is done");
				rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_Voucher);
			}
			else if(sort == 2) {
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_Code);
			}
			else if(sort == 3) {
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_Amount);			
			}
			else if(sort == 4) {
				rs = st.executeQuery(CommonConstants.QUERY_ID_SORTBY_Exp);
			}
			
			else {
				rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_Voucher);
			}

			

			while (rs.next()) {
				Voucher voucher = new Voucher();
				
				voucher.setId(rs.getString(1));
				voucher.setCode(rs.getString(2));
				voucher.setAmount(rs.getInt(3));
				voucher.setExp(rs.getString(4));


				vouchers.add(voucher);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vouchers;
		
		
	}
		

	@Override
	public ArrayList<Voucher> getSearchedgetAllVouchers(String searchDetails) {
		ArrayList<Voucher> vouchers = new ArrayList<>();
		
		System.out.println("This is impl");
		String sql = "SELECT id, code, amount, exp where id LIKE '%"+ searchDetails +"%' or code LIKE '%"+ searchDetails +"%' or amount LIKE '%"+ searchDetails +"%';";

		con = DBConnectionUtil.getDBConnection();
		try {
			st = con.createStatement();
		
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Voucher voucher= new Voucher();
				
				 voucher.setId(rs.getString(1));
				 voucher.setCode(rs.getString(2));
				 voucher.setAmount(rs.getInt(3));
				 voucher.setExp(rs.getString(4));


				vouchers.add(voucher);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vouchers;
	}

	
	
	@Override
	public String addStockVoucher(Voucher voucher) {
		String status = "failed";
		String id;
	
		ArrayList<String> Vid = new ArrayList<String>();
		
		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs= st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_Stock_IDS);
			
			while (rs.next()) {
				Vid.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
			
			voucher.setId(CommonUtil.generateIDs(Vid, "item"));
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_voucher);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, voucher.getId());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, voucher.getCode());
			pst.setInt(CommonConstants.COLUMN_INDEX_THREE, voucher.getAmount());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, voucher.getExp());
			
			pst.executeUpdate();

			status = "Stock Item Added";
			id = voucher.getId();
			System.out.println("IVoucherkImpl generated id if success: " + id);
			System.out.println("Status in Ivoucherimpl: " + status);
			
			return id;
		
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
		System.out.println("This should not print");
		return status;
	
	}
	
	@Override
	public String removeVoucher(String id) {
		Voucher voucher = new Voucher();
		voucher.setId(id);
		
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_Voucher);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, voucher.getId());

			pst.executeUpdate();
			
			System.out.println("Delete record done : " + id);

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

		return "Voucher" + id + " is removed";
	}

	@Override
	public String updateVoucher(String id, String code, int amount, String exp) {
String status = "There was a problem";
		
		con = DBConnectionUtil.getDBConnection();

		try {
			if(!code.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_VOUCHER_code);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, code);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
			String amountC = Double.toString(amount);
			
			if(!amountC.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_VOUCHER_amount);
				pst.setDouble(CommonConstants.COLUMN_INDEX_ONE, amount);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
		
			
			if(!exp.equals("null")) {
				pst = con.prepareStatement(CommonConstants.QUERY_ID_UPDATE_VOUCHER_EXP);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, exp);
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, id);
				pst.executeUpdate();
			}
			
	

			status = "Voucher Updated";

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

}
