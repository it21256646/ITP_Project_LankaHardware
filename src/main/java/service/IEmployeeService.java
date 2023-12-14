package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import com.google.gson.JsonElement;

import model.Employee;

public interface IEmployeeService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IEmployeeService.class.getName());

	public ArrayList<Employee> getAllEmployees();

	public String addEmployees(Employee employee);

	public String removeEmployees(String empNo);

	public String updateEmployees(String empNo, String name, String email, String designation, String phoneNum, String address, String date,  String salary);
	
}
