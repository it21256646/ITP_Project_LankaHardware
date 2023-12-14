package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Shop;
import service.IShopService;
import service.ShopServiceImpl;

/**
 * Servlet implementation class GetShopServlet
 */
@WebServlet("/GetShopServlet")
public class GetShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetShopServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemName = request.getParameter("itemName");
		IShopService iShopService = new ShopServiceImpl();
		Shop shop = iShopService.getShop(itemName);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(shop.getItems());
		String resp2 = new Gson().toJson(shop.getMainCategories());
		String resp3 = new Gson().toJson(shop.getHighestPrice());
		String resp4 = new Gson().toJson(shop.getLowestPrice());
		String resp5 = new Gson().toJson(shop.getSubCategories());
		String allJson = "[" + resp + "," + resp2 + ", " + resp3 + "," + resp4 + ", " + resp5 + "]";

		out.print(allJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
