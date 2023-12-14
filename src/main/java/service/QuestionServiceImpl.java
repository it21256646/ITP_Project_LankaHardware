package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.Admin;
import model.Customer;
import model.Item;
import model.Question;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;

public class QuestionServiceImpl implements IQuestionService {
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public String askQuestion(String email, String question, String itemID) {
		// TODO Auto-generated method stub

		Question questionObj = new Question();
		ArrayList<String> ids = new ArrayList<>();
		String status = "There was a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_QUESTION_IDS);

			while (rs.next()) {
				ids.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

			questionObj.setQuestionID(CommonUtil.generateIDs(ids, "question"));

			pst = con.prepareStatement(CommonConstants.QUERY_ID_CREATE_QUESTION);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, questionObj.getQuestionID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, question);
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, itemID);
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, email);
			pst.executeUpdate();

			status = "Question submitted";

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

	@Override
	public String answerQuestion(Question question) {
		// TODO Auto-generated method stub

		String status = "There was a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_ANSWER_QUESTION);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, question.getAnswer());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, question.getAdmin().getEmail());
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, question.getQuestionID());
			pst.executeUpdate();

			status = "Answered";
			
			sendAnsweredEmail(question);

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

	@Override
	public ArrayList<Question> getAllQuestionsAndAnswersByItemID(String itemID) {
		// TODO Auto-generated method stub

		ArrayList<Question> questions = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_QST_AND_ANS_BY_ITEMID);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();

			while (rs.next()) {
				Question question = new Question();
				Customer customer = new Customer();
				Admin admin = new Admin();

				question.setQuestionID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				question.setQuestion(rs.getString(CommonConstants.COLUMN_INDEX_TWO));
				question.setAnswer(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				question.setQuestionDate(rs.getString(CommonConstants.COLUMN_INDEX_FOUR));
				question.setAnswerDate(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));
				customer.setEmail(rs.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				admin.setEmail(rs.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				question.setCustomer(customer);
				question.setAdmin(admin);

				questions.add(question);
			}

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

		return questions;
	}

	@Override
	public ArrayList<Question> getNewQuestions() {
		// TODO Auto-generated method stub

		ArrayList<Question> questions = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_NEW_QUESTIONS);

			while (rs.next()) {
				Question question = new Question();
				Item item = new Item();
				Customer customer = new Customer();

				question.setQuestionID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				question.setQuestion(rs.getString(CommonConstants.COLUMN_INDEX_TWO));
				question.setQuestionDate(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				item.setItemID(rs.getString(CommonConstants.COLUMN_INDEX_FOUR));
				item.setName("testing");
				item.setMainImg("sdad");
				question.setItem(item);
				customer.setEmail(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));
				question.setCustomer(customer);

				questions.add(question);
			}
			
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_OTHER_ITEM_DETAILS_FOR_WISHLIST);
			
			for (Question question : questions) {
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, question.getItem().getItemID());
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, question.getItem().getItemID());
				rs = pst.executeQuery();
				rs.next();
				
				question.getItem().setName(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				question.getItem().setMainImg(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
			}

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

		return questions;
	}

	@Override
	public ArrayList<Question> getAnsweredQuestions() {
		// TODO Auto-generated method stub

		ArrayList<Question> questions = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_GET_ANSWERED_QUESTIONS);

			while (rs.next()) {
				Question question = new Question();
				Item item = new Item();
				Customer customer = new Customer();

				question.setQuestionID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				question.setQuestion(rs.getString(CommonConstants.COLUMN_INDEX_TWO));
				question.setAnswer(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				question.setQuestionDate(rs.getString(CommonConstants.COLUMN_INDEX_FOUR));
				question.setAnswerDate(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));
				item.setItemID(rs.getString(CommonConstants.COLUMN_INDEX_SIX));
				question.setItem(item);
				customer.setEmail(rs.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				question.setCustomer(customer);

				questions.add(question);
			}

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

		return questions;
	}

	@Override
	public String editAnsweredQuestions(String questionID, String answer) {
		// TODO Auto-generated method stub

		String status = "There was a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_EDIT_ANSWERED_QUESTIONS);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, answer);
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, questionID);
			pst.executeUpdate();

			status = "Answer Updated";

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

	@Override
	public String deleteQuestion(String questionID) {
		// TODO Auto-generated method stub

		String status = "There was a problem";
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_DELETE_QUESTION);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, questionID);
			pst.executeUpdate();

			status = "Question deleted";

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

	@Override
	public void sendAnsweredEmail(Question question) {
		// TODO Auto-generated method stub

		String from = "regularpizza17@gmail.com";
		String to = question.getCustomer().getEmail();
		String subject = "Your question about " + question.getItem().getName() + " has been answered.";
		String content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie\"><![endif]--><!--[if !IE]><!--> <html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title></title> <!--[if !mso]><!--> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><!--<![endif]--> <meta name=\"viewport\" content=\"width=device-width\" /> <style type=\"text/css\"> @media only screen and (min-width: 620px) { .wrapper { min-width: 600px !important } .wrapper h1 {} .wrapper h1 { font-size: 48px !important; line-height: 54px !important } .wrapper h2 {} .wrapper h2 { font-size: 24px !important; line-height: 32px !important } .wrapper h3 {} .wrapper h3 { font-size: 18px !important; line-height: 26px !important } .column {} .wrapper .size-8 { font-size: 8px !important; line-height: 14px !important } .wrapper .size-9 { font-size: 9px !important; line-height: 16px !important } .wrapper .size-10 { font-size: 10px !important; line-height: 18px !important } .wrapper .size-11 { font-size: 11px !important; line-height: 19px !important } .wrapper .size-12 { font-size: 12px !important; line-height: 19px !important } .wrapper .size-13 { font-size: 13px !important; line-height: 21px !important } .wrapper .size-14 { font-size: 14px !important; line-height: 21px !important } .wrapper .size-15 { font-size: 15px !important; line-height: 23px !important } .wrapper .size-16 { font-size: 16px !important; line-height: 24px !important } .wrapper .size-17 { font-size: 17px !important; line-height: 26px !important } .wrapper .size-18 { font-size: 18px !important; line-height: 26px !important } .wrapper .size-20 { font-size: 20px !important; line-height: 28px !important } .wrapper .size-22 { font-size: 22px !important; line-height: 31px !important } .wrapper .size-24 { font-size: 24px !important; line-height: 32px !important } .wrapper .size-26 { font-size: 26px !important; line-height: 34px !important } .wrapper .size-28 { font-size: 28px !important; line-height: 36px !important } .wrapper .size-30 { font-size: 30px !important; line-height: 38px !important } .wrapper .size-32 { font-size: 32px !important; line-height: 40px !important } .wrapper .size-34 { font-size: 34px !important; line-height: 43px !important } .wrapper .size-36 { font-size: 36px !important; line-height: 43px !important } .wrapper .size-40 { font-size: 40px !important; line-height: 47px !important } .wrapper .size-44 { font-size: 44px !important; line-height: 50px !important } .wrapper .size-48 { font-size: 48px !important; line-height: 54px !important } .wrapper .size-56 { font-size: 56px !important; line-height: 60px !important } .wrapper .size-64 { font-size: 64px !important; line-height: 68px !important } .wrapper .size-72 { font-size: 72px !important; line-height: 76px !important } .wrapper .size-80 { font-size: 80px !important; line-height: 84px !important } .wrapper .size-96 { font-size: 96px !important; line-height: 100px !important } .wrapper .size-112 { font-size: 112px !important; line-height: 116px !important } .wrapper .size-128 { font-size: 128px !important; line-height: 132px !important } .wrapper .size-144 { font-size: 144px !important; line-height: 148px !important } } </style> <meta name=\"x-apple-disable-message-reformatting\" /> <style type=\"text/css\"> .main, .mso { margin: 0; padding: 0; } table { border-collapse: collapse; table-layout: fixed; } * { line-height: inherit; } [x-apple-data-detectors] { color: inherit !important; text-decoration: none !important; } .wrapper .footer__share-button a:hover, .wrapper .footer__share-button a:focus { color: #ffffff !important; } .wrapper .footer__share-button a.icon-white:hover, .wrapper .footer__share-button a.icon-white:focus { color: #ffffff !important; } .wrapper .footer__share-button a.icon-black:hover, .wrapper .footer__share-button a.icon-black:focus { color: #000000 !important; } .btn a:hover, .btn a:focus, .footer__share-button a:hover, .footer__share-button a:focus, .email-footer__links a:hover, .email-footer__links a:focus { opacity: 0.8; } .preheader, .header, .layout, .column { transition: width 0.25s ease-in-out, max-width 0.25s ease-in-out; } .preheader td { padding-bottom: 8px; } .layout, div.header { max-width: 400px !important; -fallback-width: 95% !important; width: calc(100% - 20px) !important; } div.preheader { max-width: 360px !important; -fallback-width: 90% !important; width: calc(100% - 60px) !important; } .snippet, .webversion { Float: none !important; } .stack .column { max-width: 400px !important; width: 100% !important; } .fixed-width.has-border { max-width: 402px !important; } .fixed-width.has-border .layout__inner { box-sizing: border-box; } .snippet, .webversion { width: 50% !important; } .ie .btn { width: 100%; } .ie .stack .column, .ie .stack .gutter { display: table-cell; float: none !important; } .ie div.preheader, .ie .email-footer { max-width: 560px !important; width: 560px !important; } .ie .snippet, .ie .webversion { width: 280px !important; } .ie div.header, .ie .layout { max-width: 600px !important; width: 600px !important; } .ie .two-col .column { max-width: 300px !important; width: 300px !important; } .ie .three-col .column, .ie .narrow { max-width: 200px !important; width: 200px !important; } .ie .wide { width: 400px !important; } .ie .stack.fixed-width.has-border, .ie .stack.has-gutter.has-border { max-width: 602px !important; width: 602px !important; } .ie .stack.two-col.has-gutter .column { max-width: 290px !important; width: 290px !important; } .ie .stack.three-col.has-gutter .column, .ie .stack.has-gutter .narrow { max-width: 188px !important; width: 188px !important; } .ie .stack.has-gutter .wide { max-width: 394px !important; width: 394px !important; } .ie .stack.two-col.has-gutter.has-border .column { max-width: 292px !important; width: 292px !important; } .ie .stack.three-col.has-gutter.has-border .column, .ie .stack.has-gutter.has-border .narrow { max-width: 190px !important; width: 190px !important; } .ie .stack.has-gutter.has-border .wide { max-width: 396px !important; width: 396px !important; } .ie .fixed-width .layout__inner { border-left: 0 none white !important; border-right: 0 none white !important; } .ie .layout__edges { display: none; } .mso .layout__edges { font-size: 0; } .layout-fixed-width, .mso .layout-full-width { background-color: #ffffff; } @media only screen and (min-width: 620px) { .column, .gutter { display: table-cell; Float: none !important; vertical-align: top; } div.preheader, .email-footer { max-width: 560px !important; width: 560px !important; } .snippet, .webversion { width: 280px !important; } div.header, .layout, .one-col .column { max-width: 600px !important; width: 600px !important; } .fixed-width.has-border, .fixed-width.x_has-border, .has-gutter.has-border, .has-gutter.x_has-border { max-width: 602px !important; width: 602px !important; } .two-col .column { max-width: 300px !important; width: 300px !important; } .three-col .column, .column.narrow, .column.x_narrow { max-width: 200px !important; width: 200px !important; } .column.wide, .column.x_wide { width: 400px !important; } .two-col.has-gutter .column, .two-col.x_has-gutter .column { max-width: 290px !important; width: 290px !important; } .three-col.has-gutter .column, .three-col.x_has-gutter .column, .has-gutter .narrow { max-width: 188px !important; width: 188px !important; } .has-gutter .wide { max-width: 394px !important; width: 394px !important; } .two-col.has-gutter.has-border .column, .two-col.x_has-gutter.x_has-border .column { max-width: 292px !important; width: 292px !important; } .three-col.has-gutter.has-border .column, .three-col.x_has-gutter.x_has-border .column, .has-gutter.has-border .narrow, .has-gutter.x_has-border .narrow { max-width: 190px !important; width: 190px !important; } .has-gutter.has-border .wide, .has-gutter.x_has-border .wide { max-width: 396px !important; width: 396px !important; } } @supports (display: flex) { @media only screen and (min-width: 620px) { .fixed-width.has-border .layout__inner { display: flex !important; } } } /*** * Mobile Styles * * 1. Overriding inline styles */ @media(max-width: 619px) { .email-flexible-footer .left-aligned-footer .column, .email-flexible-footer .center-aligned-footer, .email-flexible-footer .right-aligned-footer .column { max-width: 100% !important; /* [1] */ text-align: center !important; /* [1] */ width: 100% !important; /* [1] */ } .flexible-footer-logo { margin-left: 0px !important; /* [1] */ margin-right: 0px !important; /* [1] */ } .email-flexible-footer .left-aligned-footer .flexible-footer__share-button__container, .email-flexible-footer .center-aligned-footer .flexible-footer__share-button__container, .email-flexible-footer .right-aligned-footer .flexible-footer__share-button__container { display: inline-block; margin-left: 5px !important; /* [1] */ margin-right: 5px !important; /* [1] */ } .email-flexible-footer__additionalinfo--center { text-align: center !important; /* [1] */ } .email-flexible-footer .left-aligned-footer table, .email-flexible-footer .center-aligned-footer table, .email-flexible-footer .right-aligned-footer table { display: table !important; /* [1] */ width: 100% !important; /* [1] */ } .email-flexible-footer .footer__share-button, .email-flexible-footer .email-footer__additional-info { margin-left: 20px; margin-right: 20px; } } @media only screen and (-webkit-min-device-pixel-ratio: 2), only screen and (min--moz-device-pixel-ratio: 2), only screen and (-o-min-device-pixel-ratio: 2/1), only screen and (min-device-pixel-ratio: 2), only screen and (min-resolution: 192dpi), only screen and (min-resolution: 2dppx) { .fblike { background-image: url(https://i7.createsend1.com/static/eb/master/13-the-blueprint-3/images/fblike@2x.png) !important; } .tweet { background-image: url(https://i8.createsend1.com/static/eb/master/13-the-blueprint-3/images/tweet@2x.png) !important; } .linkedinshare { background-image: url(https://i9.createsend1.com/static/eb/master/13-the-blueprint-3/images/lishare@2x.png) !important; } .forwardtoafriend { background-image: url(https://i10.createsend1.com/static/eb/master/13-the-blueprint-3/images/forward@2x.png) !important; } } @media (max-width: 321px) { .fixed-width.has-border .layout__inner { border-width: 1px 0 !important; } .layout, .stack .column { min-width: 320px !important; width: 320px !important; } .border { display: none; } .has-gutter .border { display: table-cell; } } .mso div { border: 0 none white !important; } .mso .w560 .divider { Margin-left: 260px !important; Margin-right: 260px !important; } .mso .w360 .divider { Margin-left: 160px !important; Margin-right: 160px !important; } .mso .w260 .divider { Margin-left: 110px !important; Margin-right: 110px !important; } .mso .w160 .divider { Margin-left: 60px !important; Margin-right: 60px !important; } .mso .w354 .divider { Margin-left: 157px !important; Margin-right: 157px !important; } .mso .w250 .divider { Margin-left: 105px !important; Margin-right: 105px !important; } .mso .w148 .divider { Margin-left: 54px !important; Margin-right: 54px !important; } .mso .size-8, .ie .size-8 { font-size: 8px !important; line-height: 14px !important; } .mso .size-9, .ie .size-9 { font-size: 9px !important; line-height: 16px !important; } .mso .size-10, .ie .size-10 { font-size: 10px !important; line-height: 18px !important; } .mso .size-11, .ie .size-11 { font-size: 11px !important; line-height: 19px !important; } .mso .size-12, .ie .size-12 { font-size: 12px !important; line-height: 19px !important; } .mso .size-13, .ie .size-13 { font-size: 13px !important; line-height: 21px !important; } .mso .size-14, .ie .size-14 { font-size: 14px !important; line-height: 21px !important; } .mso .size-15, .ie .size-15 { font-size: 15px !important; line-height: 23px !important; } .mso .size-16, .ie .size-16 { font-size: 16px !important; line-height: 24px !important; } .mso .size-17, .ie .size-17 { font-size: 17px !important; line-height: 26px !important; } .mso .size-18, .ie .size-18 { font-size: 18px !important; line-height: 26px !important; } .mso .size-20, .ie .size-20 { font-size: 20px !important; line-height: 28px !important; } .mso .size-22, .ie .size-22 { font-size: 22px !important; line-height: 31px !important; } .mso .size-24, .ie .size-24 { font-size: 24px !important; line-height: 32px !important; } .mso .size-26, .ie .size-26 { font-size: 26px !important; line-height: 34px !important; } .mso .size-28, .ie .size-28 { font-size: 28px !important; line-height: 36px !important; } .mso .size-30, .ie .size-30 { font-size: 30px !important; line-height: 38px !important; } .mso .size-32, .ie .size-32 { font-size: 32px !important; line-height: 40px !important; } .mso .size-34, .ie .size-34 { font-size: 34px !important; line-height: 43px !important; } .mso .size-36, .ie .size-36 { font-size: 36px !important; line-height: 43px !important; } .mso .size-40, .ie .size-40 { font-size: 40px !important; line-height: 47px !important; } .mso .size-44, .ie .size-44 { font-size: 44px !important; line-height: 50px !important; } .mso .size-48, .ie .size-48 { font-size: 48px !important; line-height: 54px !important; } .mso .size-56, .ie .size-56 { font-size: 56px !important; line-height: 60px !important; } .mso .size-64, .ie .size-64 { font-size: 64px !important; line-height: 68px !important; } .mso .size-72, .ie .size-72 { font-size: 72px !important; line-height: 76px !important; } .mso .size-80, .ie .size-80 { font-size: 80px !important; line-height: 84px !important; } .mso .size-96, .ie .size-96 { font-size: 96px !important; line-height: 100px !important; } .mso .size-112, .ie .size-112 { font-size: 112px !important; line-height: 116px !important; } .mso .size-128, .ie .size-128 { font-size: 128px !important; line-height: 132px !important; } .mso .size-144, .ie .size-144 { font-size: 144px !important; line-height: 148px !important; } /*** * Online store block styles * * 1. maintaining right and left margins in outlook * 2. respecting line-height for tds in outlook */ .mso .cmctbl table td, .mso .cmctbl table th { Margin-left: 20px !important; /* [1] */ Margin-right: 20px !important; /* [1] */ } .cmctbl--inline table { border-collapse: collapse; } .mso .cmctbl--inline table, .mso .cmctbl table { mso-table-lspace: 0pt; mso-table-rspace: 0pt; mso-line-height-rule: exactly; /* [2] */ } </style> <!--[if !mso]><!--> <style type=\"text/css\"> @import url(https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic|Roboto:400,700,400italic,700italic); </style> <link href=\"https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic|Roboto:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\" /><!--<![endif]--> <style type=\"text/css\"> .main, .mso { background-color: #fff } .logo a:hover, .logo a:focus { color: #1e2e3b !important } .footer-logo a:hover, .footer-logo a:focus { color: #372d1b !important } .mso .layout-has-border { border-top: 1px solid #ccc; border-bottom: 1px solid #ccc } .mso .layout-has-bottom-border { border-bottom: 1px solid #ccc } .mso .border, .ie .border { background-color: #ccc } .mso h1, .ie h1 {} .mso h1, .ie h1 { font-size: 48px !important; line-height: 54px !important } .mso h2, .ie h2 {} .mso h2, .ie h2 { font-size: 24px !important; line-height: 32px !important } .mso h3, .ie h3 {} .mso h3, .ie h3 { font-size: 18px !important; line-height: 26px !important } .mso .layout__inner, .ie .layout__inner {} .mso .footer__share-button p {} .mso .footer__share-button p { font-family: sans-serif } </style> <meta name=\"robots\" content=\"noindex,nofollow\" /> <meta property=\"og:title\" content=\"My First Campaign\" /> </head> <!--[if mso]> <body class=\"mso\"> <![endif]--> <!--[if !mso]><!--> <body class=\"main full-padding\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;\"> <!--<![endif]--> <table class=\"wrapper\" style=\"border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;background-color: #fff;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"> <tbody> <tr> <td> <div role=\"banner\"> <div class=\"preheader\" style=\"Margin: 0 auto;max-width: 560px;min-width: 280px; width: 280px;width: calc(28000% - 167440px);\"> <div style=\"border-collapse: collapse;display: table;width: 100%;\"> <!--[if (mso)|(IE)]><table align=\"center\" class=\"preheader\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 280px\" valign=\"top\"><![endif]--> <div class=\"snippet\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 140px; width: 140px;width: calc(14000% - 78120px);padding: 10px 0 5px 0;color: #37615a;font-family: sans-serif;\"> </div> <!--[if (mso)|(IE)]></td><td style=\"width: 280px\" valign=\"top\"><![endif]--> <div class=\"webversion\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 139px; width: 139px;width: calc(14100% - 78680px);padding: 10px 0 5px 0;text-align: right;color: #37615a;font-family: sans-serif;\"> </div> <!--[if (mso)|(IE)]></td></tr></table><![endif]--> </div> </div> </div> <div> <div class=\"layout one-col fixed-width stack\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\"> <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #f9f1ef;\"> <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" style=\"background-color: #f9f1ef;\"><td style=\"width: 600px\" class=\"w560\"><![endif]--> <div class=\"column\" style=\"text-align: left;color: #37615a;font-size: 16px;line-height: 24px;font-family: sans-serif;\"> <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;\"> <div style=\"mso-line-height-rule: exactly;line-height: 21px;font-size: 1px;\">&nbsp;</div> </div> <div style=\"Margin-left: 20px;Margin-right: 20px;\"> <div style=\"mso-line-height-rule: exactly;mso-text-raise: 11px;vertical-align: middle;\"> <h1 style=\"Margin-top: 0;Margin-bottom: 20px;font-style: normal;font-weight: normal;color: #000000;font-size: 36px;line-height: 43px;font-family: Roboto,Tahoma,sans-serif;text-align: center;\"> <span class=\"font-roboto\" style=\"text-decoration: inherit;\">Your Question Is Answered</span></h1> </div> </div> <div style=\"Margin-left: 20px;Margin-right: 20px;\"> <div style=\"mso-line-height-rule: exactly;mso-text-raise: 11px;vertical-align: middle;\"> <p style=\"Margin-top: 0;Margin-bottom: 20px;font-family: Roboto,Tahoma,sans-serif;text-align: left;\"> <span class=\"font-roboto\" style=\"text-decoration: inherit; color: gray;\">Your Question: "+ question.getQuestion() +"</span> <br><br> <span class=\"font-roboto\" style=\"text-decoration: inherit; color: gray;\">Answer: "+ question.getAnswer() +"</span></p> </div> </div> <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\"> <a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;\" href=\"http://www.example.com\"><img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 551px;\" alt=\"Green shoe\" width=\"551\" src=\""+ question.getItem().getMainImg() +"\" /></a> </div> <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 20px;Margin-bottom: 24px;\"> <div class=\"btn btn--flat btn--large\" style=\"text-align:center;\"> <!--[if !mso]><!--><a style=\"border-radius: 100vh;display: inline-block;font-size: 14px;font-weight: bold;line-height: 24px;padding: 12px 24px;text-align: center;text-decoration: none !important;transition: opacity 0.1s ease-in;color: #f9f1ef !important;background-color: #000000;font-family: Roboto, Tahoma, sans-serif;\" href=\"https://example.com/\">View Product</a><!--<![endif]--> <!--[if mso]><p style=\"line-height:0;margin:0;\">&nbsp;</p><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" href=\"https://example.com/\" style=\"width:99pt\" arcsize=\"9%\" fillcolor=\"#22443F\" stroke=\"f\"><v:textbox style=\"mso-fit-shape-to-text:t\" inset=\"0pt,8.25pt,0pt,8.25pt\"><center style=\"font-size:14px;line-height:24px;color:#F9F1EF;font-family:Roboto,Tahoma,sans-serif;font-weight:bold;mso-line-height-rule:exactly;mso-text-raise:1.5px\">View Product</center></v:textbox></v:roundrect><![endif]--> </div> </div> </div> <!--[if (mso)|(IE)]></td></tr></table><![endif]--> </div> </div> <div class=\"layout one-col fixed-width stack\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\"> <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #f9f1ef;\"> <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" style=\"background-color: #f9f1ef;\"><td style=\"width: 600px\" class=\"w560\"><![endif]--> <div class=\"column\" style=\"text-align: left;color: #37615a;font-size: 16px;line-height: 24px;font-family: sans-serif;\"> <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;Margin-bottom: 24px;\"> <div class=\"divider\" style=\"display: block;font-size: 2px;line-height: 2px;Margin-left: auto;Margin-right: auto;width: 40px;background-color: #ccc;\"> &nbsp;</div> </div> </div> <!--[if (mso)|(IE)]></td></tr></table><![endif]--> </div> </div> <div class=\"layout one-col fixed-width stack\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\"> <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #f9f1ef;\"> <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" style=\"background-color: #f9f1ef;\"><td style=\"width: 600px\" class=\"w560\"><![endif]--> <div class=\"column\" style=\"text-align: left;color: #37615a;font-size: 16px;line-height: 24px;font-family: sans-serif;\"> <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;Margin-bottom: 24px;\"> <div> <div style=\"padding-top: 8px; text-align: center\"><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i1.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/facebook-white-small.png\" alt=\"Facebook\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i4.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/twitter-white-small.png\" alt=\"Twitter\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i5.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/website-white-small.png\" alt=\"Website\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i10.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/instagram-white-small.png\" alt=\"Instagram\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i2.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/linkedin-white-small.png\" alt=\"LinkedIn\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i3.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/pinterest-white-small.png\" alt=\"Pinterest\" height=\"24\" width=\"24\" /></a></span><span style=\"padding-right: 4px;\"><a style=\"text-decoration: underline;transition: opacity 0.1s ease-in;color: #22443f;display: inline-block;border-radius: 50%;width: 24px;height: 24px;max-height: 24px;background-color: #000000;\" href=\"https://example.com/\"><img style=\"border: 0;\" src=\"https://i6.createsend1.com/static/eb/master/13-the-blueprint-3/images/socialmedia/youtube-white-small.png\" alt=\"YouTube\" height=\"24\" width=\"24\" /></a></span></div> </div> </div> </div> <!--[if (mso)|(IE)]></td></tr></table><![endif]--> </div> </div> <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 20px;\">&nbsp;</div> </div> <div role=\"contentinfo\"> <div style=\"line-height:4px;font-size:4px;\" id=\"footer-top-spacing\">&nbsp;</div> <div class=\"layout email-flexible-footer email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\" id=\"footer-content\"> <div class=\"layout__inner center-aligned-footer\" style=\"border-collapse: collapse;display: table;width: 100%;\"> <!--[if (mso)|(IE)]><table style=\"width: 600px;\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><![endif]--> <!--[if (mso)|(IE)]><tr class=\"layout-email-footer\"><![endif]--> <div class=\"column\" style=\"text-align: center;font-size: 12px;line-height: 19px;color: #37615a;font-family: sans-serif;display: none;\" align=\"center\"> <div class=\"footer-logo emb-logo-margin-box\" style=\"font-size: 26px;line-height: 32px;Margin-top: 10px;Margin-bottom: 20px;color: #7b663d;font-family: Roboto,Tahoma,sans-serif;\" align=\"center\"> <div emb-flexible-footer-logo align=\"center\"></div> </div> </div> <!--[if (mso)|(IE)]></tr><![endif]--> <!--[if (mso)|(IE)]><tr class=\"layout-email-footer\"><![endif]--> <div class=\"column\" style=\"text-align: center;font-size: 12px;line-height: 19px;color: #37615a;font-family: sans-serif;display: none;\" align=\"center\"> </div> <!--[if (mso)|(IE)]></tr><![endif]--> <!--[if (mso)|(IE)]><tr class=\"layout-email-footer\"><![endif]--> <table style=\"border-collapse: collapse;table-layout: fixed;width: 100%;\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div class=\"column js-footer-additional-info\" style=\"text-align: center;font-size: 12px;line-height: 19px;color: #37615a;font-family: sans-serif;display: inline;width: 100%;\" align=\"center\"> <div style=\"margin-left: 0;margin-right: 0;Margin-top: 10px;Margin-bottom: 10px; color: grey;\"> <div class=\"email-footer__additional-info\" style=\"font-size: 12px;line-height: 19px;margin-bottom: 18px;margin-top: 0px;\"> <div bind-to=\"address\"> <p class=\"email-flexible-footer__additionalinfo--center\" style=\"Margin-top: 0;Margin-bottom: 0;font-family: Roboto,Tahoma,sans-serif;\"><span class=\"font-roboto\" style=\"text-decoration: inherit;\">LankaHardwares</span></p> </div> </div> <div class=\"email-footer__additional-info\" style=\"font-size: 12px;line-height: 19px;margin-bottom: 18px;margin-top: 0px;\"> <div> <p class=\"email-flexible-footer__additionalinfo--center\" style=\"Margin-top: 0;Margin-bottom: 0;font-family: Roboto,Tahoma,sans-serif;\"><span class=\"font-roboto\" style=\"text-decoration: inherit;\">4&nbsp;February&nbsp;2023</span></p> </div> </div> <div class=\"email-footer__additional-info\" style=\"font-size: 12px;line-height: 19px;margin-bottom: 15px;\"> <div bind-to=\"permission\"> <p class=\"email-flexible-footer__additionalinfo--center\" style=\"Margin-top: 0;Margin-bottom: 0;font-family: Roboto,Tahoma,sans-serif;\"><span class=\"font-roboto\" style=\"text-decoration: inherit;\">Question is answered.</span></p> </div> </div> <div class=\"email-footer__additional-info\" style=\"font-size: 12px;line-height: 19px;margin-bottom: 15px;\"> <span> <preferences style=\"text-decoration: underline;\" lang=\"en\">Preferences</preferences> &nbsp;&nbsp;|&nbsp;&nbsp; </span> <unsubscribe style=\"text-decoration: underline;\">Unsubscribe</unsubscribe> </div> <!--[if mso]>&nbsp;<![endif]--> </div> </div> </td> </tr> </tbody> </table> <!--[if (mso)|(IE)]></tr></table><![endif]--> </div> </div> <div style=\"line-height:40px;font-size:40px;\" id=\"footer-bottom-spacing\">&nbsp;</div> </div> </td> </tr> </tbody> </table> <style type=\"text/css\"> @media (max-width:619px) { .email-flexible-footer .left-aligned-footer .column, .email-flexible-footer .center-aligned-footer, .email-flexible-footer .right-aligned-footer .column { max-width: 100% !important; text-align: center !important; width: 100% !important } .flexible-footer-logo { margin-left: 0px !important; margin-right: 0px !important } .email-flexible-footer .left-aligned-footer .flexible-footer__share-button__container, .email-flexible-footer .center-aligned-footer .flexible-footer__share-button__container, .email-flexible-footer .right-aligned-footer .flexible-footer__share-button__container { display: inline-block; margin-left: 5px !important; margin-right: 5px !important } .email-flexible-footer__additionalinfo--center { text-align: center !important } .email-flexible-footer .left-aligned-footer table, .email-flexible-footer .center-aligned-footer table, .email-flexible-footer .right-aligned-footer table { display: table !important; width: 100% !important } .email-flexible-footer .footer__share-button, .email-flexible-footer .email-footer__additional-info { margin-left: 20px; margin-right: 20px } } </style> </body> </html>";
		
		// smtp properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		final String username = "regularpizza17@gmail.com";
		final String password = "cajxxmputrxlxyqv";

		// session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(content, "text/html");
			Transport.send(message);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		IQuestionService iQuestionService = new QuestionServiceImpl();
		Question question = new Question();
		Admin admin = new Admin();
		Customer customer = new Customer();
		Item item = new Item();
		
		admin.setEmail("b@g.m");
		
		question.setAdmin(admin);
		question.setQuestionID("Q3005");
		question.setAnswer("main answer");
		
		System.out.println(iQuestionService.answerQuestion(question));
	}
}
