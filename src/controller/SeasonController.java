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
			
	    try {
	    	switch(option) {
		    	case "add":
		    		//if (ValidationUtilities.isValidSeason(request)) {
		    			String id = dao.addToDatabase(request, response);
		    			errorChecker = "Season Created";
		    			//HttpSession session = request.getSession();
		    			request.setAttribute("seasonID", id);
		    			address = "admin/CreateGames.jsp";
		    			
		    		
		    			//
			    		/*errorChecker = "Season Not Created!!";
		    			address = "/CreateSeason.jsp";*/
		    		
	    		break;
		    	case "delete":
		    		String seasonID = request.getParameter("seasonID");
					dao.deleteSeason(request, response, seasonID);
					errorChecker = "Season deleted";
					address = "admin/CreateSeason.jsp";
	    		break;
		    	case "confirm":
		    		String sID = request.getParameter("sID");
		    		gameDao.addToDatabase(request, response, sID);
					request.setAttribute("seasonID", sID);
					errorChecker = "Games Created";
		    		address ="admin/PopulateGames.jsp";;
	    		break;
		    	case "close":
		    		request.setAttribute("seasonID", request.getParameter("seasonID"));
		    		dao.closeSeason(request, response);
		    		address="admin/ListSeasons.jsp";		    		
	    		break;
		    	default:
	    		errorChecker = "Something has gone borribly wrong";
	    	}
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