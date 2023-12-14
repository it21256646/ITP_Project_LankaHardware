package servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Item;
import service.IStockService;
import service.IStockServiceImpl;


@WebServlet("/AddStoreItemsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
maxFileSize = 1024 * 1024 * 10, // 10 MB
maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddStoreItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public AddStoreItemsServlet() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("Addservlet constructor");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   System.out.println("This is addstore servlet");
		doGet(request, response);
			
		Item item = new Item();		
		
		
		String name = request.getParameter("name");	
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String description = request.getParameter("description");
		String mf_date = request.getParameter("mf_date");
		String exp_date = request.getParameter("exp_date");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String warrentyType = request.getParameter("warrentyType");
		int warNum = Integer.parseInt(request.getParameter("warNum"));
		String warPeriod = request.getParameter("warPeriod");


		
		
		if(mf_date == null || mf_date == "") {
			mf_date = null;
		}
		
		if(exp_date == null || exp_date == "") {
			exp_date = null;
		}
		
		item.setName(name);
		item.setType(category);
		item.setBrand(brand);

		item.setPrice(price);
		item.setQuantity(quantity);
		item.setDescription(description);
		
		item.setMfDate(mf_date);
		item.setExpDate(exp_date);

		item.setWarrentyType(warrentyType);
		item.setWarrentyNumber(warNum);
		item.setWarrantyPeriod(warPeriod);

	
		//item.setSize(size);
		//item.setMainImg(img);

		
		System.out.println("add servlet date: " + mf_date);
		
		//Collection<Part> parts = request.getParts();
		
		IStockService iStockService = new IStockServiceImpl();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Data saved successfully.");
		PrintWriter out = response.getWriter();

		String resp = new Gson().toJson(iStockService.addStockItems(item));

		out.print(resp);
		
		
		/*
		// Validate the input fields further if needed
	    if (!isValidName(name)) {
	      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Item Name.");
	    }
	    
	    if (!isValidBrand(brand)) {
		      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Brand Name.");
		   
		    }
	    if (!isValidPrice(price)) {
		      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Price.");
		
		    }
	    
	    if (!isValidQuantity(quantity)) {
		      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Quantity.");
		   
		    }
	    
	    if (!isValidMf(mf)) {
		     response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Modify date.");
		   
		    }
	    */
	  
		
	}

	/*private boolean isValidMf(String mf) {
		int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
		int CurrentMonth = Calendar.getInstance().get(Calendar.MONTH);
		String curr = "" + CurrentYear + CurrentMonth;
		
		int year = Integer.parseInt(mf.substring(0, 3));
		int month = Integer.parseInt(mf.substring(5, 6));
		String user = ""+ year + month;
		
		
		return Integer.parseInt(curr) > Integer.parseInt(user);
		
		
	}


	private boolean isValidQuantity(int quantity) {
		// TODO Auto-generated method stub
		return quantity > 0;
	}


	private boolean isValidPrice(double price) {
		// TODO Auto-generated method stub
		return price > 0 && price < 10000000;
	}


	private boolean isValidName(String name) {
		// TODO Auto-generated method stub
		return name != null && !name.isEmpty() && name.length() <= 1;
	}

	private boolean isValidBrand(String brand) {
		// TODO Auto-generated method stub
		return brand != null && !brand.isEmpty() && brand.length() <= 1;
	}*/
	
	
}	
	

