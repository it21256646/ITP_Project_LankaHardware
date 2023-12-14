package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.Checkout;
import util.CommonConstants;
import util.DBConnectionUtil;

public class CheckoutServiceImpl implements ICheckoutService {
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public void checkout(Checkout checkout) {
		// TODO Auto-generated method stub

		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CHECKOUT);
			pst.setString(1, checkout.getName());
			pst.setString(2, checkout.getEmail());
			pst.setString(3, checkout.getAddress());
			pst.setString(4, checkout.getPhone());
			pst.setString(5, checkout.getPcode());
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
