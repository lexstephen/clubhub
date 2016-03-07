package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.PreferenceDao;
import utilities.ValidationUtilities;

/**
 * Servlet implementation class PreferenceController
 */
@WebServlet("/PreferenceController")
public class PreferenceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		PreferenceDao dao = new PreferenceDao();
		String address = null;
		HttpSession session = request.getSession();
		try {
			switch(option) {
			case "add":
				// they want to register.  is the form filled out correctly?
				if (ValidationUtilities.isValidPreference(request)) {
					// yes it is! but are they already in the database? 
						// preference was entered successfully and is new!
						// Add preference to database then redirect to login form
						request.setAttribute("errorString", "Preference added!");
						dao.addToDatabase(request, response);
						address = "/admin/Preferences.jsp";
					// the form is not filled out correctly. send an error back and send them 
					// back to the registration form
				} else {
					request.setAttribute("errorString", "Error: please correct highlighted fields");
					address = "Register.jsp";
				}
				break;
				/*case "edit":
				if (ValidationUtilities.isValidpreference(request)) {
					// successful edit
					String preferenceID = request.getParameter("preferenceID");
					dao.editpreference(request, response, preferenceID);
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
					dao.getpreferenceId(request,option);
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
						// wrong preferencename or password, send back to login form
						request.setAttribute("errorString", "Wrong preferencename or password.");
						address = "/Login.jsp";
					}
				} else {
					// the form was not filled in correctly, send them back
					request.setAttribute("errorString", "Wrong preferencename or password.");
					address = "/Login.jsp";
				}
				break;
			case "batchEdit":
	    		try {
	    			dao.batchEdit(request, response);
	    		} catch (Exception e){
	    			e.printStackTrace();
	    		}
	    		address = "admin/Batchpreferences.jsp";
				break;
	    	case "delete":
	    		try {
					dao.deletepreference(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		address = "admin/Batchpreferences.jsp";
    		break;
	    	case "batchDelete":
	    		try {
					dao.batchDelete(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		address = "admin/BatchPreferences.jsp";
    		break; */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
