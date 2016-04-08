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

import model.Game;
import model.Season;
import model.Slot;
import model.UserGame;
import model.UserSeason;
import utilities.DatabaseAccess;

public class SeasonDao {
	private Connection connect = null;
	private Statement statement = null;
	private Statement statement1 = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet resultSet1 = null;

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
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(theDate);
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

	public void getUserSeasons(HttpServletRequest request) throws Exception {

		List<UserSeason> seasons = new ArrayList<UserSeason>();
		List<String> seasonIDs = new ArrayList<String>();
		String userID = (String) request.getAttribute("userID");

		try {

			statement = connect.createStatement();

			ResultSet results = statement.executeQuery("SELECT DISTINCT Seasonid FROM clubhub.ch_user_game ug "
					+ "JOIN ch_game game ON ug.Gameid = game.id WHERE Userid = " + userID  + " ORDER BY Seasonid DESC");

			while(results.next()) {				
				seasonIDs.add(results.getString("Seasonid"));				
			}

			for (String i : seasonIDs) {

				UserSeason season = new UserSeason();
				season.setSeasonID(i);

				results = statement.executeQuery("SELECT outcome, COUNT(*) wins FROM ch_user_game ug JOIN ch_game game ON game.id = ug.Gameid "
						+ "WHERE ug.outcome = 'W' AND game.Seasonid = " + i + " AND ug.Userid = " + userID);

				while(results.next()) {
					season.setWins(results.getInt("wins"));					
				}

				results = statement.executeQuery("SELECT outcome, COUNT(*) losses FROM ch_user_game ug JOIN ch_game game ON game.id = ug.Gameid "
						+ "WHERE ug.outcome = 'L' AND game.Seasonid = " + i + " AND ug.Userid = " + userID);

				while(results.next()) {
					season.setLosses(results.getInt("losses"));					
				}

				season.setGames(season.getWins() + season.getLosses());

				seasons.add(season);
			}

		} catch (Exception e) {
			throw e;
		}

		request.setAttribute("seasons", seasons);

	}

	public void listSeasonIDs(HttpServletRequest request) throws Exception {
		List<String> seasons = new ArrayList<String>();

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT id from ch_season");

			while (resultSet.next()) {
				seasons.add(resultSet.getString("id"));
			}

		} catch (SQLException e) {
			throw e;
		}

		request.setAttribute("seasons", seasons);

	}

	public void closeSeason(HttpServletRequest request) throws Exception {

		String seasonID = (String) request.getAttribute("seasonID");
		List <String> slotIDs = new ArrayList<String>();
		GameDao game = new GameDao();

		System.out.println("In closeSeason() with seasonID " + seasonID);

		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT slot.id from ch_game game JOIN ch_season seas ON game.Seasonid = seas.id "
					+ "JOIN ch_slot slot ON game.id = slot.gameID WHERE seas.id = " + seasonID + " AND slot.status = 1");

			while (resultSet.next()) {
				slotIDs.add(resultSet.getString("slot.id"));
				System.out.println("Adding slot.ids");
			}

			for (String k : slotIDs) {
				game.closeSlot(request, k);
				System.out.println("closed slot at k = " + k);
			}

		} catch (SQLException e){
			throw e;
		}

	}

	public void listOpenSeasons(HttpServletRequest request) throws Exception {
		List<Season> seasons = new ArrayList<Season>();
		boolean hasOpenSlots;

		try {  		
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * from ch_season");
			while (resultSet.next()) {
				Season season = new Season();
				hasOpenSlots = false;
				season.setId(resultSet.getString("id"));
				season.setYear(resultSet.getString("year"));
				season.setSeason(resultSet.getString("season"));
				season.setGender(ValidationUtilities.genderName(resultSet.getString("gender")));
				season.setStartDate(resultSet.getString("startDate"));
				season.setStartTime(ValidationUtilities.toTime(resultSet.getInt("startTime")));
				season.setDayOfWeek(ValidationUtilities.numberToDay(resultSet.getInt("dayOfWeek")));
				season.setDuration(resultSet.getString("duration"));
				season.setStartDateFullYear(ValidationUtilities.dateFullYear(resultSet.getString("startDate")));
				statement = connect.createStatement();
				resultSet1 = statement.executeQuery("SELECT * from ch_slot slot join ch_game game ON slot.gameID = game.id "
						+ "WHERE Seasonid = " + resultSet.getString("id") + " AND status = 1");
				while (resultSet1.next()) {			    	  
					hasOpenSlots = true;
				}

				if (hasOpenSlots) {
					seasons.add(season);
				} 

			}
		} catch (SQLException e) {
			throw e;
		}
		request.setAttribute("seasons", seasons);
	} 


	public void listAll(HttpServletRequest request) throws Exception {
		List<Season> seasons = new ArrayList<Season>();
		try{  		
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM ch_season");
			while (resultSet.next()) {
				Season ssn = new Season();
				ssn.setId(resultSet.getString("id"));
				ssn.setDayOfWeek(resultSet.getString("dayOfWeek"));
				ssn.setDuration(resultSet.getString("duration"));
				ssn.setGender(resultSet.getString("gender"));
				ssn.setSeason(resultSet.getString("season"));
				ssn.setStartDate(resultSet.getString("startDate"));
				ssn.setStartTime(resultSet.getString("startTime"));
				ssn.setYear(resultSet.getString("year"));
				seasons.add(ssn);
			}
		} catch (SQLException e) {
			throw e;
		}
		request.setAttribute("seasons", seasons);
	}

	/* 
	public void listSeasonWithGames(HttpServletRequest request, String seasonID) throws Exception {
		  List<Slot> slots = new ArrayList<Slot>();
		  	try{  		 
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_season where id = " + seasonID);

			    while (resultSet.next()) {
			    	  Season season = new Season();
			    	  season.setYear(resultSet.getString("year"));
			    	  season.setSeason(resultSet.getString("season"));
			    	  season.setId(resultSet.getString("id"));
			    	  season.setGender(resultSet.getString("gender"));
			    	  season.setStartDate(resultSet.getString("startDate"));
			    	  season.setStartTime(utilities.ValidationUtilities.toTime(resultSet.getInt("startTime")));
			    	  season.setDayOfWeek(utilities.ValidationUtilities.numberToDay(resultSet.getInt("dayOfWeek")));
			    	  season.setDuration(resultSet.getString("duration"));
			    	  System.out.println(" I am digging this season ");

			    	  statement1 = connect.createStatement();
					  resultSet1 = statement1.executeQuery("SELECT * from ch_game where seasonID= "+ seasonID);

					  while(resultSet1.next()){
						  Game game = new Game();
						  String gameID = resultSet1.getString("id");
						  System.out.println("Game id is: " + gameID);

						  Statement statement2 = null;
				    	  ResultSet resultSet2 = null;

				    	  statement2 = connect.createStatement();
						  resultSet2 = statement2.executeQuery("SELECT * from ch_slot where gameID= "+ gameID);

						  while(resultSet2.next()){
							  int status = resultSet2.getInt("status");

							  Slot slot = new Slot();
							  if (status == 1){
							  String playerIDs = resultSet2.getString("availablePlayers");
							  String playerNames = utilities.ValidationUtilities.getPlayerNames(request, playerIDs);
							  System.out.println("Player Names: " +playerNames);

							  slot.setPlayers(playerNames);
							  slot.setScheduledDate(resultSet2.getString("scheduledDate"));
							  slot.setStatus(resultSet2.getInt("status"));
							  slot.setGameID(resultSet2.getString("gameID"));
							  slot.setId(resultSet2.getString("id"));

							  } else if (status == 0) {
								  Statement statement3 = null;
						    	  ResultSet resultSet3 = null;

						    	  slot.setScheduledDate(resultSet2.getString("scheduledDate"));
						    	  slot.setGameID(resultSet2.getString("gameID"));
						    	  statement3 = connect.createStatement();
								  resultSet3 = statement3.executeQuery("SELECT * from ch_user_game where Gameid= "+ gameID);
								  String playerIDs = null;
								  while(resultSet3.next()){
									  if (playerIDs != null){
										  playerIDs += ", " + resultSet3.getString("Userid");
										  System.out.println(resultSet3.getString("Userid"));
									  }else {
										  playerIDs = resultSet3.getString("Userid");
									  }
								  }
								  System.out.println(playerIDs);
								  String playerNames = utilities.ValidationUtilities.getPlayerNames(request, playerIDs);
								  System.out.println("Player Names: " +playerNames);
								  slot.setPlayers(playerNames);
							  }
							  slots.add(slot);
						  }
						  }
					  request.setAttribute("dayOfWeek", utilities.ValidationUtilities.numberToDay(resultSet.getInt("dayOfWeek")));
					  request.setAttribute("time", utilities.ValidationUtilities.numberToDay(resultSet.getInt("dayOfWeek")));
					  }

		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("slots", slots);

	} 
	 */

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
		//String seasonID = null;
		try{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM ch_season WHERE id= " + seasonID);

			while (resultSet.next()) {
				season.setYear(resultSet.getString("year"));
				season.setSeason(ValidationUtilities.seasonName(resultSet.getString("season")));
				season.setId(resultSet.getString("id"));
				season.setGender(ValidationUtilities.genderName(resultSet.getString("gender")));
				season.setStartDate(resultSet.getString("startDate"));
				season.setStartDateFullYear(ValidationUtilities.dateFullYear(resultSet.getString("startDate")));season.setStartTime(ValidationUtilities.toTime( Integer.parseInt(resultSet.getString("startTime"))));
				season.setDayOfWeek(ValidationUtilities.numberToDay(Integer.parseInt(resultSet.getString("dayOfWeek"))));
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