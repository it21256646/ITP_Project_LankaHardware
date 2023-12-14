package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Admin;
import model.Customer;
import model.Item;
import model.Question;
import service.IQuestionService;
import service.QuestionServiceImpl;

/**
 * Servlet implementation class AnswerQuestionServlet
 */
@WebServlet("/AnswerQuestionServlet")
public class AnswerQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerQuestionServlet() {
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
		
		Question question = new Question();
		Admin admin = new Admin();
		Customer customer = new Customer();
		Item item = new Item();
		String email = "b@g.m";
		admin.setEmail(email);
		customer.setEmail(request.getParameter("customerEmail"));
		item.setName(request.getParameter("itemName"));
		item.setMainImg(request.getParameter("mainImg"));
		
		question.setAdmin(admin);
		question.setCustomer(customer);
		question.setItem(item);
		question.setAnswer(request.getParameter("answer"));
		question.setQuestionID(request.getParameter("questionID"));
		question.setQuestion(request.getParameter("question"));
		IQuestionService iQuestionService = new QuestionServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iQuestionService.answerQuestion(question));

		out.print(resp);
	}

}
