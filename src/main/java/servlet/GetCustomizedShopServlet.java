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
 * Servlet implementation class GetCustomizedShopServlet
 */
@WebServlet("/GetCustomizedShopServlet")
public class GetCustomizedShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomizedShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mainCategory = request.getParameter("mainCategory");
		String brand = request.getParameter("brand");
		double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
		double higherPrice = Double.parseDouble(request.getParameter("higherPrice"));
		String sortByValue = request.getParameter("sortByValue");
		String itemName = request.getParameter("itemName");
		String subType = request.getParameter("subType");
		boolean includeOutOfStock = Boolean.parseBoolean(request.getParameter("includeOutOfStock"));
		
		IShopService iShopService = new ShopServiceImpl();
		Shop shop = iShopService.getCustomizedItemList(mainCategory, lowerPrice, higherPrice, sortByValue, itemName, brand, subType, includeOutOfStock);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(shop.getItems());
		String resp2 = new Gson().toJson(shop.getBrandList());
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
