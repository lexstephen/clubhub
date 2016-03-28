package controller;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: GameController - routes requests to proper view
****************************************************************************************************/
import java.io.IOException;
import java.util.Arrays;

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
		String address = "", seasonID, gameMonth;
		//
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    			seasonID = request.getParameter("seasonID");
		    		
		    		System.out.println("The Id is: "+ seasonID);
		    		dao.addToDatabase(request, response, seasonID);
		    			
		    			
		    			errorChecker = "Games Created";
		    			
		    			address = "admin/PopulateGames.jsp";
		    			
		    	break;
		    			
		    	case "close":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    				dao.closeSlot(request, "1, 2, 3, 4, 6, 7, 8");
		    			
		    			errorChecker = "Games Created";
		    			
		    			address = "admin/PopulateGames.jsp";
		    			
		    	break;
		    	
		    	/*case "switchPlayers":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    			String seasonID = request.getParameter("seasonID");
		    		
		    		System.out.println("The Id is: "+ seasonID);
		    		dao.addToDatabase(request, response, seasonID);
		    			
		    			
		    			errorChecker = "Games Created";
		    			
		    			address = "admin/PopulateGames.jsp";
		    			
		    	break;*/
		    	
		    	case "players":
		    		HttpSession session = request.getSession();
		    		String [] ID =request.getParameterValues("slots");
		    		
		    		StringBuilder builder = new StringBuilder();
		    		if (ID.length >= 1) {
		    			builder.append(ID[0]);
		    		}

		    		for (int i = 1; i < ID.length; i++) { 
		    			builder.append(", ");
		    			builder.append(ID[i]);
		    		}
		    		
		    		
		    		String slotIDs = builder.toString();
		    		Object id = session.getAttribute("loggedInUserID");
		    		String userID = id.toString();

		    		System.out.println("The current user ID is: " + userID);
		    		
		    		//String slots = ID.toString();
		    		System.out.println(slotIDs);
		    		
		    		dao.playersAvailable(request, userID, slotIDs);
		    		
		    		errorChecker = "Dun";
	    		break;
		    	case "displayGames":
	    			
	    			seasonID = request.getParameter("seasonID");
	    			gameMonth = request.getParameter("gameMonth");  			
		    		request.setAttribute("seasonID", seasonID);
		    		request.setAttribute("gameMonth", gameMonth);
		    		
		    		System.out.println("The Id is: "+ seasonID);
		    		System.out.println("The month is: " + gameMonth);
		    		
	    			errorChecker = "Redirect to listgames";
	    			
	    			address = "admin/ListGames.jsp";
		    			
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