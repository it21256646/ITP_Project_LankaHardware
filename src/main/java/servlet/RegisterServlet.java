package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import service.IRegisterWithEmailOTPService;
import service.IUserService;


public class RegisterServlet extends HttpServlet {
    
    private IRegisterWithEmailOTPService registerService;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

      
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

       
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone =request.getParameter("phone");
        String name = request.getParameter("name");
        String address =request.getParameter("address");
        
        // register user
        boolean success = registerService.registerUser( email, password,phone,name,address);
        //if (success) {
            // show OTP form
//            request.setAttribute("email", email);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
//            dispatcher.forward(request, response);
//        }
    }
}
