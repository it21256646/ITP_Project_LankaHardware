package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import service.ISupplierService;
import service.SupplierServiceImpl;

/**
 * Servlet implementation class UpdateSupplier
 */
@WebServlet("/UpdateSupplier")
public class UpdateSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSupplier() {
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
		
		String supNo = request.getParameter("supNo");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String description = request.getParameter("description");
		String debit = request.getParameter("supplier_type");
		
		
		ISupplierService ISupplierService = new SupplierServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(ISupplierService.updateSuppliers(supNo,name,email,description,debit));

		out.print(resp);
		
	 System.out.println(supNo);
	 System.out.println(name);
	 System.out.println(email);
	 System.out.println(description);
	 System.out.println(debit);
	}

}
