package service;

import java.util.ArrayList;

import model.Feedback;

public interface IFeedbackService {

	ArrayList<Feedback> getAllFeedbacks();

	String addFeedbacks(Feedback feedback);

	String removeFeedbacks(String feedid);
	
	

}
