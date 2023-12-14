package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Customer;
import model.Item;
import model.Question;

public interface IQuestionService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IQuestionService.class.getName());
	
	/**
	 * ask a question
	 * @param email
	 * @param question
	 * @param itemID
	 * 
	 */
	public String askQuestion(String email, String question, String itemID);
	
	/**
	 * answer a question
	 * @param question
	 * 
	 */
	public String answerQuestion(Question question);
	
	/**
	 * get all questions and answers by itemID
	 * @param itemID
	 * 
	 */
	public ArrayList<Question> getAllQuestionsAndAnswersByItemID(String itemID);
	
	/**
	 * get new questions
	 * 
	 */
	public ArrayList<Question> getNewQuestions();
	
	/**
	 * get answered questions
	 * 
	 */
	public ArrayList<Question> getAnsweredQuestions();
	
	/**
	 * edit answered questions
	 * @param questionID
	 * @param answer
	 */
	public String editAnsweredQuestions(String questionID, String answer);
	
	/**
	 * delete a question
	 * @param questionID
	 * @param answer
	 */
	public String deleteQuestion(String questionID);
	
	/**
	 * send an email to the customer
	 * @param question
	 * 
	 */
	public void sendAnsweredEmail(Question question);
}
