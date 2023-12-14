package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import com.google.gson.JsonElement;

import model.Customer;


public interface ICustomerService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ICustomerService.class.getName());

	public ArrayList<Customer> getAllCustomers();

	public String register(Customer customer);

	public String removeCustomers(String email);

	public String updateCustomers(String email, String PAssword,  String phone,String name, String address);

	public String SendCustomeremail(String email);
	
}
