package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.WishlistChart;
import service.IWishlistService;
import service.WishlistServiceImpl;

/**
 * Servlet implementation class GetCartChartServlet
 */
@WebServlet("/GetWishlistChartServlet")
public class GetWishlistChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWishlistChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		IWishlistService iWishlistService = new WishlistServiceImpl();
		String itemID = request.getParameter("itemID");
		
		WishlistChart wishlistChart = iWishlistService.getChartDetails(itemID);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(wishlistChart.getSizes());
		String resp2 = new Gson().toJson(wishlistChart.getCounts());
		String bothJson = "["+resp+","+resp2+"]";
		
		out.print(bothJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
