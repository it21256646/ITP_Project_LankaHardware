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

import model.Employee;
import service.EmployeeServiceImpl;
import service.IEmployeeService;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployeeServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
maxFileSize = 1024 * 1024 * 10, // 10 MB
maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Employee employee = new Employee();
		
		employee.setName(request.getParameter("name"));
		employee.setEmail(request.getParameter("email"));
		employee.setDesignation(request.getParameter("designation"));
		employee.setPhoneNum(request.getParameter("phoneNum"));
		employee.setAddress(request.getParameter("address"));
		employee.setGender(request.getParameter("gender"));
		employee.setDate(request.getParameter("date"));
		employee.setWage(request.getParameter("wage"));
		employee.setSalary(Double.parseDouble(request.getParameter("salary")));
		
		//Collection<Part> parts = request.getParts();
		
		IEmployeeService iEmployeeService = new EmployeeServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iEmployeeService.addEmployees( employee ));

		out.print(resp);
	}

}
