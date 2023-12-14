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
@WebServlet("/GetSearchedVoucher")
public class GetSearchedVoucher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSearchedVoucher() {
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
	
		
		ArrayList<Voucher> item = new ArrayList<>();
		IVoucherService iVoucherService = new IVoucherServiceImpl();
		
		String SearchDetails = request.getParameter("SearchDetails");
		System.out.println(SearchDetails + " onServlet");
		
		item = iVoucherService.getSearchedgetAllVouchers(SearchDetails);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(item);
		
		out.print(resp);
	}

}
