package utilities;
import java.awt.Component;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


import model.Season;

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
	
	public String addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String season_id = null;
		try {
			HttpSession session = request.getSession();
			
			String theDate = request.getParameter("theDate");
			
			Calendar c = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-mm-dd").parse(theDate);
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
	      statement = connect.createStatement();
	      PreparedStatement preparedStatement = connect.prepareStatement("insert into ch_season values (default, ?, ?, ?, ?, ?, ?, ?)");
	   
	      
	      preparedStatement.setInt(1, year);	//year
	      preparedStatement.setString(2, request.getParameter("season")); // season
	      preparedStatement.setString(3, request.getParameter("gender")); // gender
	      preparedStatement.setString(4, request.getParameter("theDate")); // startDate
	      preparedStatement.setString(5, request.getParameter("startTime")); // startTime
	      preparedStatement.setInt(6, dayOfWeek); // dayOfWeek
	      preparedStatement.setString(7, request.getParameter("duration")); // duration
	      preparedStatement.executeUpdate();
	      
	     
	      ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();");
	      
	      
	      while(rs.next()){
	    	  season_id = rs.getString("LAST_INSERT_ID()");
	    	  
	      }
	      
	      System.out.println(season_id+"In seasonDao");
	      
	      
	    }catch (Exception e) {
		      throw e;
		}
	    
	    return season_id;
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
			    resultSet = statement.executeQuery("SELECT * FROM ch_season WHERE id= " + seasonID);
			    
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