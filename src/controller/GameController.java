package controller;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: GameController - routes requests to proper view
****************************************************************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		    	
		    	case "switchPlayers":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    			String gameID = request.getParameter("gameID");
		    		System.out.println("The GameID is: "+gameID);
	    			
		    		dao.playersToSwitch(request, gameID);
		    		address = "admin/EditGame.jsp";
		    			
		    	break;

		    	case "switchThem":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			
	    			String currentPlayer = request.getParameter("currentPlayer");
	    			String newPlayer = request.getParameter("newPlayer");
	    			String theGameID = request.getParameter("gameID");
	    			
		    		System.out.println("The Current Player is: "+currentPlayer);
		    		System.out.println("The New Player is: "+newPlayer);
		    		System.out.println("The gameID is: "+ theGameID);
		    		dao.switchThem(request, currentPlayer, newPlayer, theGameID);
		    		address = "admin/ListGames.jsp";
		    			
		    	break;
		    	case "availability":
		    		HttpSession session = request.getSession();
		    		String [] ID = request.getParameterValues("slots");
		    		List<String> desiredSlots = new ArrayList<String>();
		    		if (ID != null) {
			    		for (String i : ID) {
			    			desiredSlots.add(i);
			    		}
		    		}
		    		request.setAttribute("desiredSlotIDs", desiredSlots);
		    		request.setAttribute("playerToAdd", session.getAttribute("loggedInUserID"));
		    		dao.addUserSlot(request);
					request.setAttribute("successString", "Availability has been updated.");
	    			address = "admin/Availability.jsp";
	    		break;
		    	case "displayGames":
	    			
	    			seasonID = request.getParameter("seasonID");
	    			gameMonth = request.getParameter("gameMonth");  			
		    		request.setAttribute("seasonID", seasonID);
		    		request.setAttribute("gameMonth", gameMonth);
	    			errorChecker = "Redirect to listgames";
	    			address = "admin/ListGames.jsp";
		    			
		    	break;
		    	
		    	default:
		    		errorChecker = "Something has gone horribly wrong";
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