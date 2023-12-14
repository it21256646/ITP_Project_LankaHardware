package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Checkout;
import service.CheckoutServiceImpl;
import service.ICheckoutService;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		Checkout checkout = new Checkout();
		ICheckoutService iCheckoutService = new CheckoutServiceImpl();

		checkout.setName(request.getParameter("fname") +" "+ request.getParameter("lname"));
		checkout.setEmail(request.getParameter("email"));
		checkout.setPcode(request.getParameter("pcode"));
		checkout.setPhone(request.getParameter("phone"));
		checkout.setAddress(request.getParameter("address"));
		
		iCheckoutService.checkout(checkout);
	}

}
