package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import model.Review;
import model.ReviewChart;
import model.WishlistChart;

public interface IReviewService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IReviewService.class.getName());

	/**
	 * add a rating for an item
	 * 
	 *  @param email
	 *  @param itemID
	 *  @param reviewDescription
	 *  @param stars
	 *  @param reviewImages
	 *  
	 */
	public void addReview(String email, String itemID, String reviewDescription, int stars, Collection<Part> reviewImages);
	
	/**
	 * get average rating for an item
	 * 
	 *  @param itemID
	 */
	public double getAverageRating(String itemID);
	
	/**
	 * get rating count for an item
	 * 
	 *  @param itemID
	 */
	public int getItemRatingCount(String itemID);
	
	/**
	 * calculate rating percentage for an item
	 * 
	 *  @param itemID
	 */
	public double[][] calculateItemRatingPercentage(String itemID);
	
	/**
	 * get all ratings for an item
	 * 
	 *  @param itemID
	 */
	public ArrayList<Review> getItemRatings(String itemID);
	
	/**
	 * get chart details
	 * @param itemID
	 */
	public ReviewChart getChartDetails(String itemID);
}
