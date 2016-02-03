package controller;
/****************************************************************************************************
* Project: ClubHub
* Assignment: Capstone
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: PostController - routes requests to proper view
****************************************************************************************************/
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.PostDao;
import utilities.ValidationUtilities;

@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		PostDao dao = new PostDao();
		String address = null;
		//
		String errorChecker = "poo";
	    try {
	    	switch(option) {
		    	case "add":
		    		if (ValidationUtilities.isValidPost(request)) {
		    			dao.addToDatabase(request, response);
		    			//
		    			errorChecker = "Post successful";
		    			address = "/Main.jsp";
		    		} else {
		    			//
			    		errorChecker = "Post fail";
		    			address = "/Main.jsp";
		    		}
	    		break;
		    	case "delete":
		    		try {
						dao.deletePost(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "/Posts.jsp";
	    		break;
	    		default:
	    			errorChecker = "Something has gone horribly wrong";
	    	}
	    	System.out.println(errorChecker);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    	dispatcher.forward(request, response);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
