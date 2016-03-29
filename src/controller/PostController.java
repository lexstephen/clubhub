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
		String address = "";
		String errorChecker = "errorChecker is null";
		
		System.out.println("option: " + option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		if (ValidationUtilities.isValidPost(request)) {
		    			dao.addToDatabase(request, response);
						request.setAttribute("errorString", "Post added successfully.");
		    			address = "admin/BatchPosts.jsp";
		    		} else {
		    			//
						request.setAttribute("errorString", "Please correct highlighted fields");
		    			address = "admin/AddPost.jsp";
		    		}
	    		break;
		    	case "edit":
		    		if (ValidationUtilities.isValidPost(request)) {
		    			dao.editPost(request, response);
						request.setAttribute("errorString", "Post edited successfully.");
		    		} else {
						request.setAttribute("errorString", "Please correct highlighted fields");
		    		}
		    		address = "admin/EditPost.jsp?postID=" + request.getParameter("postID");
	    		break;
		    	case "batchEdit":
		    		try {
		    			dao.batchEdit(request, response);
						request.setAttribute("errorString", "Post edited successfully.");
		    		} catch (Exception e){
		    			e.printStackTrace();
						request.setAttribute("errorString", "Please correct highlighted fields");
		    		}
		    		address = "admin/BatchPosts.jsp";
	    		break;
		    	case "delete":
		    		try {
						dao.deletePost(request, response);
						request.setAttribute("errorString", "Post deleted successfully.");
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("errorString", "Please correct highlighted fields");
					}
		    		address = "admin/BatchPosts.jsp";
	    		break;
		    	case "batchDelete":
		    		try {
						dao.batchDelete(request, response);
						request.setAttribute("errorString", "Post deleted successfully.");
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("errorString", "Please correct highlighted fields");
					}
		    		address = "admin/BatchPosts.jsp";
	    		break;
		    	case "first":
		    	case "previous":
		    	case "next":
		    	case "last":		    			
		    		try {
		    			request.setAttribute("pageNav", option);
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}
		    		address = "/Main.jsp";
		    		break;
		    	case "search":
		    		try {
						dao.searchPosts(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "/Search.jsp";
	    		break;
	    		default:
					request.setAttribute("errorString", "Something went wrong!");
				break;
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
