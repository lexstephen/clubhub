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
		    		
		    			
		    	case "players":
		    		
		    		String [] ID =request.getParameterValues("slots");
		    		
		    		StringBuilder builder = new StringBuilder();
		    		if (ID.length >= 1) {
		    			builder.append(ID[0]);
		    		}

		    		for (int i = 1; i < ID.length; i++) { 
		    			builder.append(", ");
		    			builder.append(ID[i]);
		    		}
		    		// note that i starts at 1, since we already printed the element at index 0
		    		/*for (int i = 1; i < arrayListWords.length, i++) { 
		    		     System.out.print(", " + arrayListWords[i]);
		    		}
		    		for (String value : ID) {
		    		    builder.append(value);
		    		    builder.append(", ");
		    		}*/
		    		String text = builder.toString();
		    		
		    		
		    		//String slots = ID.toString();
		    		System.out.println(text);
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