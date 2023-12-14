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

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;




import model.Feedback;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;

public class FeedbackServiceImpl implements IFeedbackService {
	
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public ArrayList<Feedback> getAllFeedbacks() {
		// TODO Auto-generated method stub

		ArrayList<Feedback> feedbacks = new ArrayList();
		con = DBConnectionUtil.getDBConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_FEEDBACKS);

			while (rs.next()) {
				Feedback feedback = new Feedback();

				feedback.setFeedid(rs.getString(1));
				feedback.setEmail(rs.getString(2));
				feedback.setSubject(rs.getString(3));
				feedback.setFeedback(rs.getString(4));
				

				feedbacks.add(feedback);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return feedbacks;
	}

	@Override
	public String addFeedbacks(Feedback feedback) {
		// TODO Auto-generated method stub

		String status = "There was something wrong";
		ArrayList<String> feedIds = new ArrayList<String>();

		con = DBConnectionUtil.getDBConnection();

		
		try {
			st = con.createStatement();
			rs= st.executeQuery(CommonConstants.QUERY_ID_SELECT_ALL_FEEDBACK_IDS);
			
			while (rs.next()) {
				feedIds.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
			
			feedback.setFeedid(CommonUtil.generateIDs(feedIds, "feedback"));
			
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_TO_FEEDBACKS);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, feedback.getFeedid());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, feedback.getEmail());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, feedback.getSubject());
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, feedback.getFeedback());
		


			pst.executeUpdate();

			status = "Feedback Added";

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



	@Override
	public String removeFeedbacks(String feedid) {
		// TODO Auto-generated method stub
		
		Feedback feedback = new Feedback();
		feedback.setFeedid(feedid);
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_CLEAR_FEEDBACKS);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, feedback.getFeedid());

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

		return "Feedback removed";
	}

	public static void main(String[] args) {
		IFeedbackService feedbackService = new FeedbackServiceImpl();
		Feedback feedback = new Feedback();
		
		feedback.setFeedback("dsvvsv");
		feedback.setSubject("vdsvsvd");
		
		System.out.println(feedbackService.addFeedbacks(feedback));
	}
	

}
