package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.EmployeeServiceImpl;
import service.IEmployeeService;
import service.IQuestionService;
import service.QuestionServiceImpl;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployee() {
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
		
		String empNo = request.getParameter("empNoModal");
		String name = request.getParameter("nameModal");
		String email = request.getParameter("emailModal");
		String designation = request.getParameter("designationModal");
		String phoneNum = request.getParameter("phoneNumModal");
		String address = request.getParameter("addressModal");
		
		String date = request.getParameter("dateModal");
		String salary = request.getParameter("salaryModal");
		
		System.out.println(empNo+name+email+designation+phoneNum+address);
		
		IEmployeeService IEmployeeService = new EmployeeServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(IEmployeeService.updateEmployees(empNo,name,email,designation,phoneNum,address,date,salary));

		out.print(resp);
	}

}
