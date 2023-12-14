package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ForgotPasswordService;
import service.ForgotPasswordServiceImpl;
import service.UserServiceImpl;

@WebServlet("/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {

    private final ForgotPasswordService forgotPasswordService = new ForgotPasswordServiceImpl(new UserServiceImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean success = forgotPasswordService.sendPasswordResetLink(email);
        if (success) {
            request.setAttribute("message", "Password reset link sent to " + email);
        } else {
            request.setAttribute("error", "Email not found");
        }
        request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
    }
}

