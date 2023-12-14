package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Item;
import service.IStockService;
import service.IStockServiceImpl;


/**
 * Servlet implementation class GetAllItemsServlet
 */
@WebServlet("/GetAllStoreItemSortByServlet")
public class GetAllStoreItemSortByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllStoreItemSortByServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int sort = Integer.parseInt(request.getParameter("sort"));
		System.out.println("This is the sort receive in servlet: " + sort);
		
		ArrayList<Item> item = new ArrayList<Item>();
		IStockService iStockService = new IStockServiceImpl();
		System.out.println("This is the sort receive in doGet: " + sort);
		
		item = iStockService.GetAllStoreItemSortBy(sort);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(item);
		
		out.print(resp);
	
	}

}
