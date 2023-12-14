package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.IStockService;
import service.IStockServiceImpl;




@WebServlet("/UpdateStock")
public class UpdateStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStock() {
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
		
		String id = request.getParameter("StockIDModal");
		String name = request.getParameter("nameModal");
		String category = request.getParameter("categoryModal");
		String brand = request.getParameter("brandModal");
		double price = Double.valueOf(request.getParameter("unit_priceModal"));
		int quantity = Integer.parseInt(request.getParameter("quantityModal"));
		String description = request.getParameter("DescriptionModal");
		String mf = request.getParameter("mfModal");
		String exp = request.getParameter("expModal");
		
		String warrentyNone = request.getParameter("WorNoneModal");
		String warrentyAva = request.getParameter("WorAvailModal");
		
		int warNum = Integer.parseInt(request.getParameter("warrentyNumModal"));
		String warPeriod = request.getParameter("warrentyPeriodModal");
	
		String warrentyType = "Available";
		String size = request.getParameter("size");
		String img = request.getParameter("img");
		String sub = request.getParameter("subType");
		
		
		if(warrentyNone == "" || warrentyNone == "None" || warrentyNone == null || warrentyNone == "undefined") {
			warrentyType = "None";
			warNum = 0;
			warPeriod = "None";
		}
		System.out.println("warrenty Type in update servlet: " + warrentyType);
		
		//System.out.println(id+name+email+designation+phoneNum+address);
		
		IStockService iStockService = new IStockServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iStockService.updateStockItems(id, name, category, brand, price, quantity, description, mf, exp, warrentyType,warNum, warPeriod, size, img, sub));

		out.print(resp);
	}

}
