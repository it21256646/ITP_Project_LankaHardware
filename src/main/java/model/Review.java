package model;

import java.util.ArrayList;

public class Review {

	private String reviewID;
	private String reviewDescription;
	private String reviewDate;
	private int stars;
	private Customer customer;
	private ArrayList<String> reviewImages;

	public String getReviewID() {
		return reviewID;
	}

	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<String> getReviewImages() {
		return reviewImages;
	}

	public void setReviewImages(ArrayList<String> reviewImages) {
		this.reviewImages = reviewImages;
	}

}
