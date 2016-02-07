package utilities;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 07, 2016
* Description: PostDao - prepares a database access object for the game model
****************************************************************************************************/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.Season;
import model.User;
import utilities.DatabaseAccess;

public class SeasonDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public SeasonDao() {
		try {
			connect = DatabaseAccess.connectDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {
			String id = request.getParameter("id");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_season where id = \"" + id + "\""); 
			// if there result set is before the first item, there are entries
			// if it is not, there are not
			if (!resultSet.isBeforeFirst() ) {    
				return false;
			} else {
				return true;
			}
	    } catch (Exception e) {
	    	throw e;
	    } 
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {
			HttpSession session = request.getSession();
			
	      statement = connect.createStatement();
	      preparedStatement = connect.prepareStatement("insert into ch_season values (default, ?, ?, ?, ?, ?, ?, ?)");
	   
	      
	      preparedStatement.setString(1, request.getParameter("year"));	//year
	      preparedStatement.setString(2, request.getParameter("season")); // season
	      preparedStatement.setString(3, request.getParameter("gender")); // gender
	      preparedStatement.setString(4, request.getParameter("startDate")); // startDate
	      preparedStatement.setString(5, request.getParameter("startTime")); // startTime
	      preparedStatement.setString(6, request.getParameter("dayOfWeek")); // dayOfWeek
	      preparedStatement.setString(7, request.getParameter("duration")); // duration
	      preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void listAll(HttpServletRequest request) throws Exception {
		  List<Season> seasons = new ArrayList<Season>();
		  	try{  		
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT year, season, "
			    		+ "gender, startDate, startTime, "
			    		+ "dayOfWeek, duration" 
				+ "FROM clubhub.ch_season");
			      
			    while (resultSet.next()) {
			    	  Season season = new Season();
			    	  season.setYear(resultSet.getString("year"));
			    	  season.setSeason(resultSet.getString("season"));
			    	  season.setId(resultSet.getString("id"));
			    	  season.setGender(resultSet.getString("gender"));
			    	  season.setStartDate(resultSet.getString("startDate"));
			    	  season.setStartTime(resultSet.getString("startTime"));
			    	  season.setDayOfWeek(resultSet.getString("dayOfWeek"));
			    	  season.setDuration(resultSet.getString("duration"));
			    	  
			    	  request.setAttribute("seasonID", season.getId());

			    	  seasons.add(season);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("seasons", seasons);
	} 
	
	public void deleteSeason(HttpServletRequest request, HttpServletResponse response, String seasonID) throws Exception {

		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_season where id =" + seasonID); 
		  } catch (SQLException e) {
		      throw e;
		  }
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String [] markedForDeletion = request.getParameterValues("seasonsSelected");
		for (String x : markedForDeletion) {
			deleteSeason(request, response, x);
		}		
	}
	
	public void findSeason(HttpServletRequest request, String seasonID) throws Exception {
		  Season season = new Season();
		  
		  	try{
			    statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT season.year, season.season, season.gender, season.startDate, season.startDate, season.startTime, season.dayOfWeek, season.duration" 
				+ "FROM clubhub.ch_season"
				+ "WHERE season.id = " + seasonID);
			    
			    while (resultSet.next()) {
			    	  season.setYear(resultSet.getString("year"));
			    	  season.setSeason(resultSet.getString("season"));
			    	  season.setId(resultSet.getString("id"));
			    	  season.setGender(resultSet.getString("gender"));
			    	  season.setStartDate(resultSet.getString("startDate"));
			    	  season.setStartTime(resultSet.getString("startTime"));
			    	  season.setDayOfWeek(resultSet.getString("dayOfWeek"));
			    	  season.setDuration(resultSet.getString("duration"));
			    }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("season", season);
	} 
	
	public void editSeason(HttpServletRequest request, HttpServletResponse response, String _seasonID) throws Exception {
	    try {			
			String seasonID = request.getParameter("seasonID");
		    String year = request.getParameter("year");	// year
		    String season = request.getParameter("season"); // season
		    String gender = request.getParameter("gender"); // gender
		    String startDate = request.getParameter("startDate"); // StartDate
		    String startTime = request.getParameter("startTime"); // startTime
		    String dayOfWeek = request.getParameter("dayOfWeek"); // DOW
		    String duration = request.getParameter("duration"); // duration
		    
		    
	      
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_season SET year='" + year + "', season='" + season + "', gender='" + gender + 
					"', startDate='" + startDate + "', startTime='" + startTime + "' dayOfWeek='" + dayOfWeek + "'duration='" 
					+ duration + "' WHERE id='" + seasonID + "'");
	      
	      //preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

}

