package servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Voucher;
import service.IVoucherService;
import service.IVoucherServiceImpl;


@WebServlet("/AddVoucherServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
maxFileSize = 1024 * 1024 * 10, // 10 MB
maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddVoucherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public AddVoucherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  
		doGet(request, response);
			
		Voucher voucher = new Voucher();		
		
		
		String code = request.getParameter("code");	
		int amount = Integer.parseInt(request.getParameter("amount"));
		String exp = request.getParameter("exp");

		if(exp == null || exp == "") {
			exp = null;
		}
	
		
		voucher.setCode(code);
		voucher.setAmount(amount);
		voucher.setExp(exp);
		
		
		
		//Collection<Part> parts = request.getParts();
		
		IVoucherService iVoucherService = new IVoucherServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Data saved successfully.");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iVoucherService.addStockVoucher(voucher));

		out.print(resp);
		
		
	}
}	
	

