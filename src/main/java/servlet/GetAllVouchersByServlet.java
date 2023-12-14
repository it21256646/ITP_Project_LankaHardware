package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Voucher;
import service.IVoucherService;
import service.IVoucherServiceImpl;


/**
 * Servlet implementation class GetAllItemsServlet
 */
@WebServlet("/GetAllVouchersByServlet")
public class GetAllVouchersByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllVouchersByServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sort = Integer.parseInt(request.getParameter("sort"));
		System.out.println("This is the sort receive in servlet: " + sort);
		
		ArrayList<Voucher> voucher = new ArrayList<Voucher>();
		IVoucherService iVoucherService = new IVoucherServiceImpl();
		System.out.println("This is the sort receive in doGet: " + sort);
		
		voucher = iVoucherService.GetAllgetAllVouchersSortBy(sort);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(voucher);
		
		out.print(resp);
	
	}

}
