package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.IVoucherService;
import service.IVoucherServiceImpl ;




@WebServlet("/UpdateVoucher")
public class UpdateVoucher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVoucher() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("this is the constructor updatestock");
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
		
		String id = request.getParameter("idM");
		String code = request.getParameter("codeM");
		int amount = Integer.parseInt(request.getParameter("amountM"));
		String exp = request.getParameter("expM");
		
	
		//System.out.println(id+name+email+designation+phoneNum+address);
		
		IVoucherService iVoucherService = new IVoucherServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iVoucherService.updateVoucher(id, code, amount, exp));

		out.print(resp);
	}

}
