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

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CalendarController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String option = request.getParameter("option");
		String address = "Main.jsp";
		int month, year;
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "prev":
		    		
		    		month = Integer.parseInt(session.getAttribute("selectedMonth").toString());
		    		year = Integer.parseInt(session.getAttribute("selectedYear").toString());
		    		if (month == 1){
		    			session.setAttribute("selectedMonth", 12);
		    			session.setAttribute("selectedYear", year - 1);
		    		} else {
		    			session.setAttribute("selectedMonth", month - 1);
		    		}		
		    	break;
		    	case "next":
		    		month = Integer.parseInt(session.getAttribute("selectedMonth").toString());
		    		year = Integer.parseInt(session.getAttribute("selectedYear").toString());
		    		if (month == 12){
		    			session.setAttribute("selectedMonth", 1);
		    			session.setAttribute("selectedYear", year + 1);
		    		} else {
		    			session.setAttribute("selectedMonth", month + 1);
		    		}		    			
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