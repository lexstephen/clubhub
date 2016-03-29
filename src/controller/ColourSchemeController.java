package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.ColourSchemeDao;
import utilities.ValidationUtilities;

/**
 * Servlet implementation class ColourSchemeController
 */
@WebServlet("/ColourSchemeController")
public class ColourSchemeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColourSchemeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		ColourSchemeDao dao = new ColourSchemeDao();
		String address = null;
		HttpSession session = request.getSession();
		try {
			switch(option) {
			case "add":
				// they want to register.  is the form filled out correctly?
				if (ValidationUtilities.isValidColourScheme(request)) {
					// yes it is! but are they already in the database? 
						// ColourScheme was entered successfully and is new!
						// Add ColourScheme to database then redirect to login form
						request.setAttribute("errorString", "ColourScheme added!");
						System.out.println("CS ID is " + request.getParameter("csid"));
						dao.addToDatabase(request, response);
						address = "/admin/ColourSchemes.jsp";
					// the form is not filled out correctly. send an error back and send them 
					// back to the registration form
				} else {
					request.setAttribute("errorString", "Please check your input");
					address = "/admin/ColourSchemes.jsp";
				}
				break;
				/*case "edit":
				if (ValidationUtilities.isValidColourScheme(request)) {
					// successful edit
					String ColourSchemeID = request.getParameter("ColourSchemeID");
					dao.editColourScheme(request, response, ColourSchemeID);
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
					dao.getColourSchemeId(request,option);
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
						// wrong ColourSchemename or password, send back to login form
						request.setAttribute("errorString", "Wrong ColourSchemename or password.");
						address = "/Login.jsp";
					}
				} else {
					// the form was not filled in correctly, send them back
					request.setAttribute("errorString", "Wrong ColourSchemename or password.");
					address = "/Login.jsp";
				}
				break;
			case "batchEdit":
	    		try {
	    			dao.batchEdit(request, response);
	    		} catch (Exception e){
	    			e.printStackTrace();
	    		}
	    		address = "admin/BatchColourSchemes.jsp";
				break;
	    	case "delete":
	    		try {
					dao.deleteColourScheme(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		address = "admin/BatchColourSchemes.jsp";
    		break;
	    	case "batchDelete":
	    		try {
					dao.batchDelete(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		address = "admin/BatchColourSchemes.jsp";
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
