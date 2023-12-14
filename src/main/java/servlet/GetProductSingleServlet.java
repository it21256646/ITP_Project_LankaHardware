package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Item;
import service.IProductSingleService;
import service.IQuestionService;
import service.IReviewService;
import service.ProductSingleServiceImpl;
import service.QuestionServiceImpl;
import service.ReviewServiceImpl;

/**
 * Servlet implementation class GetProductSingleServlet
 */
@WebServlet("/GetProductSingleServlet")
public class GetProductSingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProductSingleServlet() {
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

		String itemID = request.getParameter("itemID");
		IProductSingleService iProductSingleService = new ProductSingleServiceImpl();
		IReviewService iReviewService = new ReviewServiceImpl();
		IQuestionService iQuestionService = new QuestionServiceImpl();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iProductSingleService.getProduct(itemID));
		String resp2 = new Gson().toJson(iProductSingleService.getProductSizeAndPriceList(itemID));
		String resp3 = new Gson().toJson(iReviewService.getItemRatings(itemID));
		String resp4 = new Gson().toJson(iProductSingleService.getRelatedProducts(itemID));
		String resp5 = new Gson().toJson(iQuestionService.getAllQuestionsAndAnswersByItemID(itemID));
		String allJson = "["+resp+","+resp2+", "+resp3+", "+resp4+", "+resp5+"]";
		
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
