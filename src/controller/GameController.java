package controller;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: GameController - routes requests to proper view
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

import utilities.GameDao;
import utilities.ValidationUtilities;

@WebServlet("/GameController")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GameController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String option = request.getParameter("option");
		GameDao dao = new GameDao();
		String address = "";
		//
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    			String seasonID = request.getParameter("seasonID");
		    		
		    		System.out.println("The Id is: "+ seasonID);
		    		dao.addToDatabase(request, response, seasonID);
		    			
		    			
		    			errorChecker = "Games Created";
		    			
		    			address = "admin/PopulateGames.jsp";
		    			
		    		
		    			//
			    		/*errorChecker = "Season Not Created!!";
		    			address = "/CreateSeason.jsp";*/
		    		
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