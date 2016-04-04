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
	    	case "delete":
	    		try {
					dao.deleteColourScheme(request, response);
					request.setAttribute("errorString", "Scheme successfully deleted.");
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		address = "admin/ColourSchemes.jsp";
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
