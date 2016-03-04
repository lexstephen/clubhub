package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.ValidationUtilities;
import utilities.UserDao;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		UserDao dao = new UserDao();
		String address = null;
		HttpSession session = request.getSession();
		try {
			switch(option) {
			case "register":
				// they want to register.  is the form filled out correctly?
				if (ValidationUtilities.isValidRegistration(request)) {
					// yes it is! but are they already in the database? 
					if(dao.isInDatabase(request, response)) {
						// Yes - User already exists, send error, redisplay registration form
						request.setAttribute("errorString", "Error: username already exists");
						address = "/Register.jsp";
					} else {
						// User was entered successfully and is new!
						// Add user to database then redirect to login form
						request.setAttribute("errorString", "You are now registered! Please log in.");
						dao.addToDatabase(request, response);
						address = "/Login.jsp";
					}
					// the form is not filled out correctly. send an error back and send them 
					// back to the registration form
				} else {
					request.setAttribute("errorString", "Error: please correct highlighted fields");
					address = "Register.jsp";
				}
				break;
			case "edit":
				if (ValidationUtilities.isValidUser(request)) {
					// successful edit
					String userID = request.getParameter("userID");
					dao.editUser(request, response, userID);
					address = "/admin/EditProfile.jsp";
				} else {
					// unsuccessful edit
					request.setAttribute("errorString", "Error: please correct highlighted fields");
					address = "/admin/EditProfile.jsp";
				}
				break;
			case "login":
				// valid input?
				if (ValidationUtilities.isValidLogin(request)) {
					dao.getUserId(request,option);
					dao.getName(request,option);
					dao.isAdmin(request);
					// yes it is! and are they in the database?	    					    				
					if (session.getAttribute("isAdmin").equals(true)) {
						// they are admins! send them to AdminController
						session.setAttribute("isLoggedIn", true);
						request.setAttribute("errorString", null);
						address = "/admin/index.jsp";
					} else if (dao.isInDatabase(request, response)) {
						// yes they are, let's log them in
						session.setAttribute("isLoggedIn", true);
						request.setAttribute("errorString", null);
						address = "/admin/index.jsp";
					} else {
						// wrong username or password, send back to login form
						request.setAttribute("errorString", "Wrong username or password.");
						address = "/Login.jsp";
					}
				} else {
					// the form was not filled in correctly, send them back
					request.setAttribute("errorString", "Wrong username or password.");
					address = "/Login.jsp";
				}
				break;
			default:
				// something went wrong, display main page
				address = "/index.jsp";
				break;
			}
			// we've done what we needed to do, where should we send them?
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			// okay then
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}