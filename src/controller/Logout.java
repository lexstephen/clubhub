package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String userError = "You have been successfully logged out.";
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logoutAddress = null;
		HttpSession session = request.getSession(false);
		if (request.isRequestedSessionIdValid() && session != null) {
			session.invalidate();
		}
		//HttpSession newsession = request.getSession();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("isAdmin")) {
				//newsession.setAttribute("isAdmin", false);
				cookie.setValue("false");
			} else if (cookie.getName().equals("isLoggedIn")) {
				//newsession.setAttribute("isLoggedIn", false);
				cookie.setValue("false");
			}
			response.addCookie(cookie);
			logoutAddress  = "/Main.jsp";
		}
		//}
		//Cookie errorString = new Cookie("errorString", userError);
		//response.addCookie(errorString);
		//response.sendRedirect("/clubhub/Main.jsp");
		
		// we've done what we needed to do, where should we send them?
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(logoutAddress);
		// okay then
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
