package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartServiceImpl;
import service.ICartService;

/**
 * Servlet implementation class ChangeQuantityServlet
 */
@WebServlet("/ChangeQuantityServlet")
public class ChangeQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeQuantityServlet() {
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
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String size = request.getParameter("size");
		ICartService iCartService = new CartServiceImpl();
		
		iCartService.changeQuantity(email, itemID, quantity, size);
	}
}
