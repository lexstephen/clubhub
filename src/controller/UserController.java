package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.ValidationUtilities;
import utilities.SendEmail;
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
						request.setAttribute("errorString", "Username already exists");
						address = "Register.jsp";
					} else {
						// User was entered successfully and is new!
						// Add user to database then redirect to login form
						request.setAttribute("successString", "You are now registered! Please log in.");
						dao.addToDatabase(request, response);
						address = "Login.jsp";
					}
					// the form is not filled out correctly. send an error back and send them 
					// back to the registration form
				} else {
					request.setAttribute("errorString", "Please correct highlighted fields");
					address = "Register.jsp";
				}
				break;
			case "edit":
				if (ValidationUtilities.isValidUser(request)) {
					// successful edit
					String userID = request.getParameter("userID");
					dao.editUser(request, response, userID);
					request.setAttribute("successString", "Profile updated!");
					address = "/admin/EditProfile.jsp";
				} else {
					// unsuccessful edit
					request.setAttribute("errorString", "Please correct highlighted fields");
					address = "/admin/EditProfile.jsp";
				}
				break;
			case "login":
				// valid input?
				if (ValidationUtilities.isValidLogin(request) && dao.isInDatabase(request, response)) {
					dao.getUserId(request,option);
					dao.getName(request,option);
					dao.isAdmin(request);
					boolean setCookie = (request.getParameter("setCookie") == null)?false:true;
					// yes it is! and are they in the database?	    					    				
					if (session.getAttribute("isAdmin").equals(true)) {
						// they are admins! send them to AdminController
						session.setAttribute("isLoggedIn", true);
						request.setAttribute("errorString", null);
						/* if (setCookie) {
							Cookie adminCookie = new Cookie("isAdmin", "true");
							Cookie loggedInCookie = new Cookie("isLoggedIn", "true");
							Cookie loggedInUserIDCookie = new Cookie("loggedInUserID", (String)session.getAttribute("loggedInUserID"));
							adminCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
							response.addCookie(adminCookie);
							loggedInCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year		
							response.addCookie(loggedInCookie);
							loggedInUserIDCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
							response.addCookie(loggedInUserIDCookie);
						}

						} */

						address = "/admin/index.jsp";
					} else if (dao.isInDatabase(request, response)) {
						// yes they are, let's log them in
						session.setAttribute("isLoggedIn", true);
						request.setAttribute("errorString", null);
						//session.setAttribute("loggedInUserID", request.getAttribute("id")); 
						/* if (setCookie) {
							Cookie adminCookie = new Cookie("isAdmin", "false");
							Cookie loggedInCookie = new Cookie("isLoggedIn", "true");
							Cookie loggedInUserIDCookie = new Cookie("loggedInUserID", (String)session.getAttribute("loggedInUserID"));
							adminCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
							response.addCookie(adminCookie);
							loggedInCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
							response.addCookie(loggedInCookie);
							loggedInUserIDCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
							response.addCookie(loggedInUserIDCookie);
						} */
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
			case "batchEdit":
				try {
					dao.batchEdit(request, response);
				} catch (Exception e){
					e.printStackTrace();
				}
				address = "admin/BatchUsers.jsp";
				break;
			case "delete":
				try {
					String userID = request.getParameter("userID");
					dao.deleteUser(request, response, userID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				address = "admin/BatchUsers.jsp";
				break;
			case "batchDelete":
				try {
					dao.batchDelete(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				address = "admin/BatchUsers.jsp";
				break;
			case "email":
				String emailType = request.getParameter("emailType");
				switch(emailType) {
				case "conflict":

					try {
						SendEmail email = new SendEmail();
						String playerInConflict = "2";
						String gameInConflict = "42";
						String[] theseUsers = {"1", "2", "3", "4"};
						email.sendConflictEmail(request, playerInConflict, gameInConflict, theseUsers);

					} catch (MessagingException mex) {
						System.out.println("send failed, exception: " + mex);
					}
					address = "/admin/index.jsp";
					break;
				case "availability":
					try {
						SendEmail email = new SendEmail();
						String seasonID = request.getParameter("seasonID");
						email.sendAvailabiltyOpenEmail(request, response, seasonID);

					} catch (MessagingException mex) {
						System.out.println("send failed, exception: " + mex);
					}
					address = "/admin/index.jsp";
					break;
				case "registration":
					try {
						SendEmail email = new SendEmail();
						String userID = "4";
						email.sendNewRegistrationEmail(request, response, userID);

					} catch (MessagingException mex) {
						System.out.println("send failed, exception: " + mex);
					}
					address = "/admin/index.jsp";
					break;
				}
			default:
				// something went wrong, display main page
				address = "/Main.jsp";
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