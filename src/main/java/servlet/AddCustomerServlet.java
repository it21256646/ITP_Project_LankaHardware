package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import model.Customer;
import service.CustomerServiceImpl;
import service.ICustomerService;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Customer customer = new Customer();
		
		System.out.println("here");
		
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		customer.setPhone(request.getParameter("phone"));
		customer.setName(request.getParameter("name"));
		customer.setAddress(request.getParameter("address"));
		
		System.out.println(customer.getName());
		
		ICustomerService iCustomerService = new CustomerServiceImpl();
		iCustomerService.register( customer);
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//
//		String resp = new Gson().toJson(iCustomerService.register( customer));
//
//		out.print(resp);
	}

}
