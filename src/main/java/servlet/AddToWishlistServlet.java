package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Customer;
import model.Item;
import service.IWishlistService;
import service.WishlistServiceImpl;

/**
 * Servlet implementation class AddToWishlistServlet
 */
@WebServlet("/AddToWishlistServlet")
public class AddToWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToWishlistServlet() {
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
		
		String email = "a@g.m";
		String itemID = request.getParameter("itemID");
		String size = request.getParameter("size");
		Customer customer = new Customer();
		Item item = new Item();
		IWishlistService iWishlistService = new WishlistServiceImpl();
		
		customer.setEmail(email);
		item.setItemID(itemID);
		item.setSize(size);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iWishlistService.addToWishlist(customer, item));

		out.print(resp);
	}

}
