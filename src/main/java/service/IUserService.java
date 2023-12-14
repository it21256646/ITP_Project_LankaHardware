package service;

import java.util.logging.Logger;

import model.User;

public interface IUserService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IUserService.class.getName());
	
	/**
	 * get cart id by email
	 * @param email
	 */
	public String login(String email,String password);

	public User getUserByEmail(String email);

	public void saveUser(User user);
	
}
