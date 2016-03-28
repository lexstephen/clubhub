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

import utilities.GameDao;
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
		GameDao gameDao = new GameDao();
		String address = "";
		HttpSession session = request.getSession();
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			String id = dao.addToDatabase(request, response);
		    			int ID = Integer.parseInt(id);
		    			System.out.println(id+"In controller");
		    			errorChecker = "Season Created";
		    			//HttpSession session = request.getSession();
		    			session.setAttribute("seasonID", ID);
		    			address = "admin/CreateGames.jsp";
		    			
		    		
		    			//
			    		/*errorChecker = "Season Not Created!!";
		    			address = "/CreateSeason.jsp";*/
		    		
	    		break;
		    	case "delete":
		    		System.out.println("I'm in case delete");
		    		String seasonID = request.getParameter("seasonID");
	    			System.out.println("Delete seasonID = " + seasonID);
					dao.deleteSeason(request, response, seasonID);
					
					errorChecker = "Season deleted";

						System.out.println("You have canceled the creation of you season and will be taken back to create a new one, Sucker!!");
					
		    		address = "admin/CreateSeason.jsp";
	    		break;
		    	case "confirm":

		    		System.out.println("I'm in case confirm");
		    		Object id1 = session.getAttribute("seasonID");
		    		String sID = id1.toString();
		    		//String id1 =request.getParameter("seasonID");
		    		System.out.println("The ID is:" + sID);
					gameDao.addToDatabase(request, response, sID);
					
					errorChecker = "Games Created";
		    		address ="admin/PopulateGames.jsp";;
	    		break;
		    	/*case "ListSeasons":

		    		System.out.println("I'm in case list seasons");
		    		dao.listSeasonWithStatus(request);
		    		Object id2 = session.getAttribute("seasonID");
		    		String ssnID = id2.toString();
		    		//String id2 =request.getParameter("seasonID");
		    		System.out.println("The ID is:" + ssnID);
					
					
				*/	
		    		//address ="admin/PopulateGames.jsp";;
	    		
	    		
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