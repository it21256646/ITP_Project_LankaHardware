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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import model.CartChart;
import model.Customer;
import model.Review;
import model.ReviewChart;
import util.CommonConstants;
import util.CommonUtil;
import util.DBConnectionUtil;

public class ReviewServiceImpl implements IReviewService {
	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst, pst2;

	private static ResultSet rs, rs2;

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ReviewServiceImpl.class.getName());

	@Override
	public double getAverageRating(String itemID) {
		// TODO Auto-generated method stub

		double avgRating = 0;
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_AVERAGE_RATING);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();
			rs.next();

			avgRating = rs.getDouble(CommonConstants.COLUMN_INDEX_ONE);

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

		return avgRating;
	}

	@Override
	public int getItemRatingCount(String itemID) {
		// TODO Auto-generated method stub
		int count = 0;

		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_RATING_COUNT);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();
			rs.next();

			count = rs.getInt(CommonConstants.COLUMN_INDEX_ONE);

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

		return count;
	}

	@Override
	public double[][] calculateItemRatingPercentage(String itemID) {
		// TODO Auto-generated method stub

		double[][] ratingPercentageList = new double[5][2];
		ArrayList<Double> percentages = new ArrayList<>();
		ArrayList<Double> starNumbers = new ArrayList<>();
		int oneStar = 0, twoStar = 0, threeStar = 0, fourStar = 0, fiveStar = 0, itemRatingCount;
		double percentage;
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_DETAILS_FOR_ITEM_RATING_PERCENTAGE);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();

			while (rs.next()) {
				int star = rs.getInt(CommonConstants.COLUMN_INDEX_ONE);

				if (star == 1) {
					oneStar++;
				} else if (star == 2) {
					twoStar++;
				} else if (star == 3) {
					threeStar++;
				} else if (star == 4) {
					fourStar++;
				} else if (star == 5) {
					fiveStar++;
				}
			}

			itemRatingCount = getItemRatingCount(itemID);

			percentage = (oneStar / (double) itemRatingCount) * 100;
			percentages.add(percentage);

			percentage = (twoStar / (double) itemRatingCount) * 100;
			percentages.add(percentage);

			percentage = (threeStar / (double) itemRatingCount) * 100;
			percentages.add(percentage);

			percentage = (fourStar / (double) itemRatingCount) * 100;
			percentages.add(percentage);

			percentage = (fiveStar / (double) itemRatingCount) * 100;
			percentages.add(percentage);

			starNumbers.add((double) oneStar);
			starNumbers.add((double) twoStar);
			starNumbers.add((double) threeStar);
			starNumbers.add((double) fourStar);
			starNumbers.add((double) fiveStar);

			for (int i = 0; i < ratingPercentageList.length; i++) {
				ratingPercentageList[i][0] = percentages.get(i);
				ratingPercentageList[i][1] = starNumbers.get(i);
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

		return ratingPercentageList;
	}

	@Override
	public ArrayList<Review> getItemRatings(String itemID) {
		// TODO Auto-generated method stub

		ArrayList<Review> reviews = new ArrayList<>();
		con = DBConnectionUtil.getDBConnection();

		try {
			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ALL_RATINGS_FOR_AN_ITEM);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
			rs = pst.executeQuery();

			while (rs.next()) {
				Review review = new Review();
				Customer customer = new Customer();

				review.setReviewID(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				customer.setEmail(rs.getString(CommonConstants.COLUMN_INDEX_TWO));
				review.setCustomer(customer);
				review.setReviewDescription(rs.getString(CommonConstants.COLUMN_INDEX_THREE));
				review.setStars(rs.getInt(CommonConstants.COLUMN_INDEX_FOUR));
				review.setReviewDate(rs.getString(CommonConstants.COLUMN_INDEX_FIVE));

				reviews.add(review);
			}

			for (Review review : reviews) {
				ArrayList<String> reviewImages = new ArrayList<>();

				pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_RATING_IMAGES_FOR_AN_ITEM);
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, review.getReviewID());
				rs = pst.executeQuery();

				while (rs.next()) {
					reviewImages.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
				}

				review.setReviewImages(reviewImages);
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

		return reviews;
	}

	@Override
	public void addReview(String email, String itemID, String reviewDescription, int stars,
			Collection<Part> reviewImages) {
		// TODO Auto-generated method stub

		ArrayList<String> ids = new ArrayList<>();
		Review review = new Review();
		ArrayList<String> imagePathArrayList = new ArrayList<>();

		con = DBConnectionUtil.getDBConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery(CommonConstants.QUERY_ID_SELECT_REVIEW_IDS);

			while (rs.next()) {
				ids.add(rs.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

			review.setReviewID(CommonUtil.generateIDs(ids, "review"));

			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_REVIEW);
			pst.setString(CommonConstants.COLUMN_INDEX_ONE, review.getReviewID());
			pst.setString(CommonConstants.COLUMN_INDEX_TWO, email);
			pst.setString(CommonConstants.COLUMN_INDEX_THREE, itemID);
			pst.setString(CommonConstants.COLUMN_INDEX_FOUR, reviewDescription);
			pst.setInt(CommonConstants.COLUMN_INDEX_FIVE, stars);
			pst.executeUpdate();

			// Configure to upload to cloudinary
			Map config = new HashMap();
			config.put("cloud_name", "dqgiitni2");
			config.put("api_key", "987952682616387");
			config.put("api_secret", "0Zw3qi4VX6XjfMh9LYSDYVdyOns");
			Cloudinary cloudinary = new Cloudinary(config);

			for (Part part : reviewImages) {
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

			System.out.println(imagePathArrayList);
			review.setReviewImages(imagePathArrayList);

			pst = con.prepareStatement(CommonConstants.QUERY_ID_ADD_REVIEW_IMAGES);

			for (String image : imagePathArrayList) {
				pst.setString(CommonConstants.COLUMN_INDEX_ONE, review.getReviewID());
				pst.setString(CommonConstants.COLUMN_INDEX_TWO, image);
				pst.executeUpdate();
			}

			System.out.println("success");

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

	}

	@Override
	public ReviewChart getChartDetails(String itemID) {
		// TODO Auto-generated method stub
		
		double[][] ratingPercentageList = calculateItemRatingPercentage(itemID);
		ReviewChart reviewChart = new ReviewChart();
//		ArrayList<Integer> counts = new ArrayList<>();
//		con = DBConnectionUtil.getDBConnection();
//		
//		try {
//			pst = con.prepareStatement(CommonConstants.QUERY_ID_GET_ITEM_REVIEW_COUNT);
//			pst.setString(CommonConstants.COLUMN_INDEX_ONE, itemID);
//
//			for (int i = 1; i < 6; i++) {
//				pst.setInt(CommonConstants.COLUMN_INDEX_TWO, i);
//				rs = pst.executeQuery();
//				rs.next();
//				
//				counts.add(rs.getInt(CommonConstants.COLUMN_INDEX_ONE));
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			/*
//			 * Close prepared statement and database connectivity at the end of transaction
//			 */
//
//			try {
//				if (pst != null) {
//					pst.close();
//				}
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				log.log(Level.SEVERE, e.getMessage());
//			}
//		}

		reviewChart.setRatingPercentageList(ratingPercentageList);
		
		return reviewChart;
	}

	public static void main(String[] args) {
		IReviewService iReviewService = new ReviewServiceImpl();
		System.out.println(iReviewService.calculateItemRatingPercentage("i100")[4][1]);

	}
}
