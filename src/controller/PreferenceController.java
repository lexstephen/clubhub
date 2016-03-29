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
		String prefID = request.getParameter("prefid");
		String option = request.getParameter("option");
		if (option == null) {
			option = (prefID.equals("000"))?"add":"edit";
		}
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
						address = "/admin/SetPreferences.jsp";
					// the form is not filled out correctly. send an error back and send them 
					// back to the registration form
				} else {
					request.setAttribute("errorString", "Please correct highlighted fields");
					address = "/admin/Preferences.jsp";
				}
				break;
			case "edit":
			if (ValidationUtilities.isValidPreference(request)) {
				// successful edit
				dao.editPreference(request, response, prefID);
				address = "/admin/SetPreferences.jsp";
			} else {
				// unsuccessful edit
				request.setAttribute("errorString", "Please correct highlighted fields");
				address = "/admin/Preferences.jsp";
			}
				break;
			case "setPref":

					dao.setPreference(request);
					address = "/admin/SetPreferences.jsp";

				break;
				
/*	    	case "delete":
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
