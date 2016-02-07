package controller;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: PostController - routes requests to proper view
****************************************************************************************************/
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.SeasonDao;
import utilities.ValidationUtilities;

@WebServlet("/SeasonController")
public class SeasonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SeasonController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		SeasonDao dao = new SeasonDao();
		String address = "";
		//
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		if (ValidationUtilities.isValidPost(request)) {
		    			dao.addToDatabase(request, response);
		    			//
		    			errorChecker = "Season Created";
		    			address = "admin/PopulateGames.jsp";
		    		} else {
		    			//
			    		errorChecker = "Season Not Created!!";
		    			address = "/CreateSeason.jsp";
		    		}
	    		break;
		    	/*case "edit":
		    		if (ValidationUtilities.isValidPost(request)) {
		    			String postID = request.getParameter("postID");
		    			dao.editPost(request, response, postID);
		    			//
		    			errorChecker = "Post edited";
		    			address = "/Main.jsp";
		    		} else {
		    			//
			    		errorChecker = "Post failed to edit";
		    			address = "/Main.jsp";
		    		}
	    		break;
		    	case "batchEdit":
		    		try {
		    			dao.batchEdit(request, response);
		    			errorChecker = "Posts edited";
		    		} catch (Exception e){
		    			e.printStackTrace();
		    		}
		    		address = "admin/BatchPosts.jsp";
	    		break;
		    	case "delete":
		    		try {
		    			String postID = request.getParameter("postID");
		    			System.out.println("Delete postID = " + postID);
						dao.deletePost(request, response, postID);
						//
						errorChecker = "Post deleted";
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "admin/BatchPosts.jsp";
	    		break;
		    	case "batchDelete":
		    		try {
						dao.batchDelete(request, response);
						//
						errorChecker = "Posts deleted";
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "admin/BatchPosts.jsp";
	    		break;*/
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
