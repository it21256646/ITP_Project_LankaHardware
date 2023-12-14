package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


import model.Supplier;
import service.SupplierServiceImpl;
import service.ISupplierService;

/**
 * Servlet implementation class AddSupplierServlet
 */
@WebServlet("/AddSupplierServlet")
public class AddSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSupplierServlet() {
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
		

		Supplier supplier = new Supplier();
		
		supplier.setName(request.getParameter("name"));
		supplier.setEmail(request.getParameter("email"));
		supplier.setPhoneNum(request.getParameter("phoneNum"));
		supplier.setDescription(request.getParameter("description"));
		supplier.setDebit(request.getParameter("supplier_type"));
		
		
		System.out.println(supplier.getDebit()+supplier.getDescription()+supplier.getEmail()+supplier.getPhoneNum()+supplier.getName());
		
		ISupplierService iSupplierService = new SupplierServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iSupplierService.addSuppliers(supplier));

		out.print(resp);
	
	}

}
