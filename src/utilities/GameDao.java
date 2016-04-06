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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import model.Game;
import model.Post;
import model.Season;
import model.Slot;
import model.User;
import model.UserGame;
import utilities.DatabaseAccess;
import utilities.ValidationUtilities;

public class GameDao {
	private Connection connect = null;
	private Statement statement = null;
	private Statement statement2 = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet resultSet2 = null;
	private ResultSet resultSet3 = null;
	
	public GameDao() {
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
	
	public void getUserGames(HttpServletRequest request) throws Exception {
		
		List<UserGame> games = new ArrayList<UserGame>();
		
		try {
			
			statement = connect.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM ch_user_game ug JOIN ch_game game ON ug.Gameid = game.id "
					+ "WHERE Userid = " + request.getAttribute("userID"));
			
			while(results.next()) {
				UserGame game = new UserGame();
				
				game.setGameID(results.getString("Gameid"));
				game.setUserID(results.getString("Userid"));
				game.setTeam(results.getString("team"));
				game.setScore(results.getString("score"));
				game.setSeasonID(results.getString("Seasonid"));
				game.setOutcome(results.getString("outcome"));
				
				games.add(game);
			}
			
		} catch (Exception e) {
		      throw e;
	    }
		
		request.setAttribute("games", games);
		
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response, String seasonID) throws Exception {
		try {
			HttpSession session = request.getSession();
			System.out.println("I'm in addToDatabase in GameDao");
			System.out.println("The season Id is: " + seasonID);
			
			// First we need to check how many games are in the current season we have passed in. 
			// We also need to get the start date because we have to add 7 days to it everytime we go through the loop
			statement = connect.createStatement();
			ResultSet results = statement.executeQuery("select * from ch_season where id = " + seasonID); 
			while(results.next()){
				int games = Integer.parseInt(results.getString("duration"));
				String date = results.getString("startDate");
				int dayOfWeek = Integer.parseInt(results.getString("dayOfWeek"));
				int time = Integer.parseInt(results.getString("startTime"));
				String gender = results.getString("gender");
				System.out.println("Number of games: " + games);
		
			// Next we need to create a loop in order to create as many games as we need for the current season 
	
				int cnt=0;
				int week = 0;
			
				do {
					String gameID = null;
					cnt++;
					week++;
					// add the game 
					PreparedStatement preparedStatement = connect.prepareStatement("insert into ch_game values (default, ?, ?, ?)");
					preparedStatement.setInt(1, week);	//week of game
					preparedStatement.setString(2, date); // date of game
					preparedStatement.setString(3, seasonID);
					preparedStatement.executeUpdate();
					statement = connect.createStatement();
					ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();");
					while(rs.next()){
						// get the created game's ID
						gameID = rs.getString("LAST_INSERT_ID()");
						System.out.println("The current game ID is: " + gameID);
					}
					// create the associated slot
					PreparedStatement preparedStatement2 = connect.prepareStatement("insert into ch_slot values (default, ?, ?, ?, ?, ?, ?, null, ?)");
					preparedStatement2.setInt(1, dayOfWeek );	//week of game
					preparedStatement2.setInt(2, time); // date of game
					preparedStatement2.setInt(3, week); // week #
					preparedStatement2.setString(4, gender); // gender
					preparedStatement2.setInt(5, 1); // status 1 = open, 0 is closed
					preparedStatement2.setString(6, date); // scheduledDate
					preparedStatement2.setString(7, gameID); // gameID
					preparedStatement2.executeUpdate();
					
					// increment the date after adding it so that it's ready for the next game/slot
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(date));
					c.add(Calendar.DATE, 7);  // number of days to add
					date = sdf.format(c.getTime()); 
				} while (cnt < games);
			}
	    } catch (Exception e) {
		      throw e;
	    }
	}
	
	public boolean gameOnDate(HttpServletRequest request, String calendarDate) throws Exception {
		HttpSession session = request.getSession();
		boolean isMatched = false;
		boolean isLoggedIn = session.getAttribute("isLoggedIn") == null ? false : (boolean) session.getAttribute("isLoggedIn");
		String output = "", gender = "", gameDate = "";
	  	try{  		
	  		statement = connect.createStatement();
		    resultSet = statement.executeQuery("SELECT game.id, game.Seasonid, season.gender, season.startTime FROM clubhub.ch_game game JOIN clubhub.ch_season season "
		    		+ "ON game.Seasonid = season.id "
		    		+ "WHERE game.scheduledDate LIKE '" + calendarDate + "'");
		      
		    while (resultSet.next()) {
		    	gender = ValidationUtilities.genderName(resultSet.getString("season.gender"));
		    	gameDate = ValidationUtilities.toTime(request, Integer.parseInt(resultSet.getString("season.startTime")));
		    	if (isLoggedIn) {
		    		output += "<a href=\'admin/Game.jsp?gameID=" + resultSet.getString("game.id") + "\'>" + gender + " " + gameDate + "</a><br>";
		    	} else {
		    		output += gender + " " + gameDate + "<br>";
		    	}
		    }
	    } catch (SQLException e) {
		      throw e;
		}
	  	if (!output.equals("")) {
	  		request.setAttribute("output", output);
	  		isMatched = true;
	  	}
	  	
	  	return isMatched;
	}
	
	 public void listAll(HttpServletRequest request) throws Exception {
		List<Game> games = new ArrayList<Game>();
	  	try{  		
	  		statement = connect.createStatement();
		    resultSet = statement.executeQuery("SELECT * FROM ch_game ORDER BY scheduledDate DESC");
		    while (resultSet.next()) {
		    	  // save game details to game object
		    	  Game game = new Game();
		    	  game.setId(resultSet.getString("id"));
		    	  game.setScheduledDate(resultSet.getString("scheduledDate"));
		    	  game.setDateFormatted(ValidationUtilities.dateWithoutYear(resultSet.getString("scheduledDate")));
		    	  game.setWeek(resultSet.getString("week"));
		    	  game.setSeasonId(resultSet.getString("seasonId"));
			  	  try {  
			  		    // save season details to game object
			  	  		statement = connect.createStatement();
			  	  		resultSet2 = statement.executeQuery("SELECT * FROM ch_season WHERE id = " + game.getSeasonId());
				  		while (resultSet2.next()) {
				  			game.setYear(resultSet2.getString("year"));
				  			game.setSeason(ValidationUtilities.seasonName(resultSet2.getString("season")));
				  			game.setGender(ValidationUtilities.genderName(resultSet2.getString("gender")));
				  			game.setStartDate(resultSet2.getString("startDate"));
				  			game.setStartTime(ValidationUtilities.toTime(request, Integer.parseInt(resultSet2.getString("startTime"))));
				  			game.setDayOfWeek(ValidationUtilities.numberToDay(Integer.parseInt(resultSet2.getString("dayOfWeek"))));
				  			game.setDuration(resultSet2.getString("duration"));
				  		}
			  	  }
			  	  catch (SQLException e) {
		  		  	throw e;
			  	  }
			  	  try {  		
			  		  	// save slot details to game object
			  	  		statement = connect.createStatement();
			  	  		resultSet3 = statement.executeQuery("SELECT * FROM ch_slot WHERE gameid = " + game.getId());
				  		while (resultSet3.next()) {
				  			game.setSlotid(resultSet3.getString("id"));
				  			game.setSlotStatus(resultSet3.getString("status"));
				  		}
			  	  }
			  	  catch (SQLException e) {
		  		  	throw e;
			  	  }

			  	  try {  		
			  		  	// save slot details to game object
			  	  		statement = connect.createStatement();
			  	  		resultSet3 = statement.executeQuery("SELECT * FROM ch_slot WHERE gameid = " + game.getId());
				  		while (resultSet3.next()) {
				  			game.setSlotid(resultSet3.getString("id"));
				  			game.setSlotStatus(resultSet3.getString("status"));
				  		}
			  	  }
			  	  catch (SQLException e) {
		  		  	throw e;
			  	  }
			  	  games.add(game);
		    }
	    } catch (SQLException e) {
		      throw e;
		}
	  	request.setAttribute("games", games);
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
	
	public int getDuration(HttpServletRequest request, String seasonID) throws Exception {
		statement = connect.createStatement();
	    resultSet = statement.executeQuery("SELECT * FROM ch_season WHERE id= " + seasonID );
	    
	    String str = resultSet.getString("duration");
	    int duration = Integer.parseInt(str);
	    
	    System.out.println("I'm in getDuration, the current duration is: "+ duration);
	    
	    return duration;
	}
	
	
	public void findGameSet(HttpServletRequest request, int seasonID) throws Exception{
		 List<Game> games = new ArrayList<Game>();
		  	try{  		
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * "
				+ "FROM ch_game JOIN ch_season ON ch_game.seasonID = ch_season.id AND seasonID= " + seasonID);
			      
			    while (resultSet.next()) {
			    	
			    	/*
			    	  Game game = new Game();
			    	  game.setWeek(resultSet.getString("week"));
			    	  game.setScheduledDate(resultSet.getString("scheduledDate"));
			    	  game.setSeasonId(resultSet.getString("seasonID"));
			    	  
			    	  request.setAttribute("gameID", game.getId());
			    	  games.add(game); */
			    	  Game game = new Game();
			    	  game.setId(resultSet.getString("id"));
			    	  game.setScheduledDate(ValidationUtilities.dateWithoutYear(resultSet.getString("scheduledDate")));
			    	  game.setWeek(resultSet.getString("week"));
			    	  game.setSeasonId(resultSet.getString("seasonId"));
				  	  try{  		
				  	  	statement = connect.createStatement();
				  		resultSet2 = statement.executeQuery("SELECT * FROM ch_season WHERE id = " + game.getSeasonId());
				  		while (resultSet2.next()) {
				  			game.setYear(resultSet2.getString("year"));
				  			game.setSeason(ValidationUtilities.seasonName(resultSet2.getString("season")));
				  			game.setGender(ValidationUtilities.genderName(resultSet2.getString("gender")));
				  			game.setStartDate(resultSet2.getString("startDate"));
				  			game.setStartTime(ValidationUtilities.toTime(request, Integer.parseInt(resultSet2.getString("startTime"))));
				  			game.setDayOfWeek(ValidationUtilities.numberToDay(Integer.parseInt(resultSet2.getString("dayOfWeek"))));
				  			game.setDuration(resultSet2.getString("duration"));
				  		}
				  	  }
				  	  catch (SQLException e) {
			  		   	throw e;
				  	  }
				  	  games.add(game);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("games", games);
	}
	
	public void findRecentGames(HttpServletRequest request) throws Exception{
		 List<Game> games = new ArrayList<Game>();
		 
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date date1 = format.parse(new SimpleDateFormat("yyyy-MM-dd").format((new Date())));
		 
		  	try{  		
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * "
				+ "FROM ch_game game JOIN ch_season season ON game.seasonID = season.id "
				+ "ORDER BY scheduledDate DESC");
			      
			    while (resultSet.next() && games.size() < 3) {
			    	
		    	  Game game = new Game();
		    	  game.setId(resultSet.getString("id"));
		    	  game.setScheduledDate(resultSet.getString("scheduledDate"));
		    	  game.setGender(ValidationUtilities.genderName(resultSet.getString("season.gender")));
		  			
		    	  if (format.parse(game.getScheduledDate()).equals(date1) || format.parse(game.getScheduledDate()).before(date1)){
		    		  game.setScheduledDate(ValidationUtilities.dateWithoutYear(resultSet.getString("scheduledDate")));
		    		  games.add(game);
		    	  }
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	
		  	
		  	request.setAttribute("games", games);
	}
	
	public void findTeamsForGames(HttpServletRequest request) throws SQLException {
		List<User> teamA = new ArrayList<User>();
		List<User> teamB = new ArrayList<User>();
		String gameID = request.getParameter("gameID");
		int teamAscore = 0, teamBscore = 0;
		
	  	try{
		    statement = connect.createStatement();
		    resultSet = statement.executeQuery("SELECT * FROM ch_user_game JOIN ch_user ON ch_user_game.Userid = ch_user.id WHERE Gameid= " + gameID);
		    while (resultSet.next()) {
		    	if(resultSet.getString("team").equals("1")) {
					User user = new User();
					user.setUserid(resultSet.getString("id"));
					user.setScore(resultSet.getString("score"));
					user.setUsername(resultSet.getString("username"));
					user.setEmailAddress(resultSet.getString("emailAddress"));
					user.setUserStatus(resultSet.getString("userStatus"));
					user.setFirstName(resultSet.getString("firstName"));
					user.setLastName(resultSet.getString("lastName"));
					user.setGender(resultSet.getString("gender"));
					user.setStreetAddress(resultSet.getString("streetAddress"));
					user.setTelephone(resultSet.getString("phoneNumber"));
					user.setCity(resultSet.getString("city"));
					user.setProvince(resultSet.getString("province"));
					user.setPostalCode(resultSet.getString("postalCode"));
					user.setCountry(resultSet.getString("country"));
					user.setDateOfBirth(resultSet.getString("dateOfBirth"));
					user.setEmergencyContactName(resultSet.getString("emergencyContactName"));
					user.setEmergencyContactPhoneNumber(resultSet.getString("emergencyContactPhoneNumber"));
					String score = resultSet.getString("score") != null?resultSet.getString("score"):"0";
		    		teamAscore += Integer.parseInt(score);
					teamA.add(user);
		    	}
		    	if(resultSet.getString("team").equals("2")) {
					User user = new User();
					user.setUserid(resultSet.getString("id"));
					user.setScore(resultSet.getString("score"));
					user.setUsername(resultSet.getString("username"));
					user.setEmailAddress(resultSet.getString("emailAddress"));
					user.setUserStatus(resultSet.getString("userStatus"));
					user.setFirstName(resultSet.getString("firstName"));
					user.setLastName(resultSet.getString("lastName"));
					user.setGender(resultSet.getString("gender"));
					user.setStreetAddress(resultSet.getString("streetAddress"));
					user.setTelephone(resultSet.getString("phoneNumber"));
					user.setCity(resultSet.getString("city"));
					user.setProvince(resultSet.getString("province"));
					user.setPostalCode(resultSet.getString("postalCode"));
					user.setCountry(resultSet.getString("country"));
					user.setDateOfBirth(resultSet.getString("dateOfBirth"));
					user.setEmergencyContactName(resultSet.getString("emergencyContactName"));
					user.setEmergencyContactPhoneNumber(resultSet.getString("emergencyContactPhoneNumber"));
					String score = resultSet.getString("score") != null?resultSet.getString("score"):"0";
		    		teamBscore += Integer.parseInt(score);
					teamB.add(user);
		    	}
		    }
	  	}
	  	  catch (SQLException e) {
	  		   	throw e;
  	  }
	  	
	  	String winner = null;
	  	
	  	if ((teamAscore == 0) && (teamBscore == 0)) 
	  		{ winner = "TBD"; }
	  	
	  	if (teamAscore > teamBscore) {
	  		winner = "Team A";
	  	} else if (teamBscore > teamAscore) {
	  		winner = "Team B";
	  	} else {
	  		winner = "Tie";
	  	}
	  	
	  	request.setAttribute("teamA", teamA);
	  	request.setAttribute("teamB", teamB);
	  	request.setAttribute("teamAscore", teamAscore);
	  	request.setAttribute("teamBscore", teamBscore);
	  	request.setAttribute("winner", winner);
	}
	
	public void findGames(HttpServletRequest request, String gameID) throws Exception {
		  Game game = new Game();
		  SeasonDao seasondao = new SeasonDao();
		  	try{
			    statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * FROM ch_game WHERE id= " + gameID );
			    
			    
			    while (resultSet.next()) {
			    	  seasondao.findSeason(request, resultSet.getString("seasonId"));
			    	  game.setId(resultSet.getString("id"));
			    	  game.setScheduledDate(ValidationUtilities.dateWithoutYear(resultSet.getString("scheduledDate")));
			    	  game.setScheduledDateFullYear(ValidationUtilities.dateFullYear(resultSet.getString("scheduledDate")));
			    	  game.setWeek(resultSet.getString("week"));
			    	  game.setSeasonId(resultSet.getString("seasonId"));

				  	  try{  		
				  	  	statement = connect.createStatement();
				  		resultSet2 = statement.executeQuery("SELECT * FROM ch_season WHERE id = " + game.getSeasonId());
				  		while (resultSet2.next()) {
				  			game.setYear(resultSet2.getString("year"));
				  			game.setSeason(ValidationUtilities.seasonName(resultSet2.getString("season")));
				  			game.setGender(ValidationUtilities.genderName(resultSet2.getString("gender")));
				  			game.setStartDate(resultSet2.getString("startDate"));
				  			game.setStartTime(ValidationUtilities.toTime(request, Integer.parseInt(resultSet2.getString("startTime"))));
				  			game.setDayOfWeek(ValidationUtilities.numberToDay(Integer.parseInt(resultSet2.getString("dayOfWeek"))));
				  			game.setDuration(resultSet2.getString("duration"));
				  		}
				  	  }
				  	  catch (SQLException e) {
			  		   	throw e;
				  	  }
			    	  
			}} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("game", game);
	} 

	public void addUserSlot(HttpServletRequest request) throws Exception{
		// figure out who is updating their availability
		String playerToAdd = (String) request.getAttribute("playerToAdd");
		// take the CSV of slots that the player will play and split them out into an array
		@SuppressWarnings("unchecked")
		List<String> slotList = (ArrayList<String>) request.getAttribute("desiredSlotIDs"); // their new availability
		List<Slot> desiredSlots = new ArrayList<Slot>(); // their old availability
		List<Slot> existantSlots = new ArrayList<Slot>(); // their old availability
		for (String slot : slotList) {
			Slot slt = new Slot();
			slt.setId(slot);
			slt.setConflict(0);
			desiredSlots.add(slt);
		}
		List<String> slots = new ArrayList<String>(); // where we'll store the new user slots
		List<String> conflictSlots = new ArrayList<String>(); // where we'll track any conflicts
		try{
			// first check if this slot has already been assigned
			try{
				statement = connect.createStatement();
				ResultSet results = statement.executeQuery("select * from ch_user_slot where Userid = " + playerToAdd); 
				Slot existantSlot = new Slot();
				while(results.next()){
					existantSlot.setId(results.getString("Slotid"));
					existantSlot.setConflict(Integer.parseInt(results.getString("conflict")));
//					
					existantSlots.add(existantSlot);
				}
				
				for (Slot a : desiredSlots) {
					if(!existantSlots.contains(a)) {

						System.out.println("User already assigned to " + a.getId() + " and has " + a.getConflict());
					} else {
						if (existantSlot.sameSlotDifferentConflict(a)) {
							System.out.println("User already assigned to " + a.getId() + " and has " + a.getConflict());
						} else {
							System.out.println("Adding " + a.getId());
							slots.add(a.getId());
						}
					}
			    }
				
				for (Slot a : existantSlots) {
					if(!desiredSlots.contains(a)) {
						System.out.println("They did not want " + a);
						conflictSlots.add(a.getId());
					} 
			    }
			} catch(SQLException e) {
			      throw e;
			}

			// add the new user_slots
			statement = connect.createStatement();
			for (String slot : slots) { 
					PreparedStatement preparedStatement = connect.prepareStatement("insert into ch_user_slot values (?, ?, ?)");
					preparedStatement.setString(1, playerToAdd); // Userid
					preparedStatement.setString(2, slot); // Slotid
					preparedStatement.setBoolean(3, false); // no conflict
					preparedStatement.executeUpdate();
			}
			
			// set the conflict to true for slots they changed availability on
			statement = connect.createStatement();
			for (String slot : conflictSlots) { 
				System.out.println("UPDATE ch_user_slot SET conflict = 1 WHERE Userid = " + playerToAdd + " AND Slotid= " + slot);
					PreparedStatement preparedStatement = connect.prepareStatement("UPDATE ch_user_slot SET conflict = 1 WHERE Userid = " + playerToAdd + " AND Slotid= " + slot);
					preparedStatement.executeUpdate();
			}
		} catch(SQLException e) {
		      throw e;
		}
	}

	public void findAllOfUsersSlots(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		// figure out who is updating their availability
		String playerToFind = (String) session.getAttribute("loggedInUserID");
		UserDao userdao = new UserDao();
		userdao.findUser(request, playerToFind);
		User user = (User) request.getAttribute("user");
		List<String> assignedSlots = new ArrayList<String>();
		
		// take the CSV of slots that the player will play and split them out into an array
		try{
			statement = connect.createStatement();
			ResultSet results = statement.executeQuery("select Slotid from ch_user_slot where Userid = " + playerToFind); 
			while(results.next()){
				assignedSlots.add(results.getString("Slotid"));
			}
		} catch(SQLException e) {
		      throw e;
		}
		user.setSlotid(assignedSlots);
	  	request.setAttribute("user", user);
	}
	

	public void playersAvailable(HttpServletRequest request, String userID, String slotIDs) throws Exception{
		
		String [] slots = slotIDs.split(",");
		try{
			statement = connect.createStatement();
			
			for (int i = 0; i < slots.length; i++) { 
				String slotID = slots[i];
				ResultSet results = statement.executeQuery("select * from ch_slot where id = " + slotID); 
				while(results.next()){
					String availablePlayers = results.getString("availablePlayers");
					if(availablePlayers != null && availablePlayers.contains(userID)){
						}else {
							
							if(availablePlayers != null){
								String theIDs = availablePlayers +", " + userID;
								System.out.println(userID);
								PreparedStatement preparedStatement = connect.prepareStatement("UPDATE ch_slot SET availablePlayers = ? WHERE id= " + slotID);
								preparedStatement.setString(1, theIDs);
								preparedStatement.executeUpdate();
							}else{
								PreparedStatement preparedStatement = connect.prepareStatement("UPDATE ch_slot SET availablePlayers = ? WHERE id= " + slotID);
								System.out.println(userID);
								preparedStatement.setString(1, userID);
								preparedStatement.executeUpdate();
							}
							
						}
					}
				}
			
		}catch(SQLException e) {
		      throw e;
		}
	}
	
	public void playersToSwitch(HttpServletRequest request, String gameID) throws Exception{
		
		statement = connect.createStatement();
		ResultSet resultSet1 = statement.executeQuery("Select * from ch_slot where gameID= "+gameID);
		ArrayList<String> currentPlayers = new ArrayList<String>();
		while (resultSet1.next()){
			String players2 = resultSet1.getString("availablePlayers");
			players2 =  utilities.ValidationUtilities.getPlayerNames(request, players2);
			String [] availablePlayers = players2.split(", ");
			ArrayList<String> theAvailablePlayers = new ArrayList<String>();
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from ch_user_game where Gameid= "+gameID);
			while (resultSet.next()){
				currentPlayers.add(resultSet.getString("Userid"));
			}
			StringBuilder builder = new StringBuilder();
    		if (currentPlayers.size() >= 1) {
    			builder.append(currentPlayers.get(0));
    		}

    		for (int i = 1; i < currentPlayers.size(); i++) { 
    			builder.append(", ");
    			builder.append(currentPlayers.get(i));
    		}
    		
    		String players = builder.toString();
    		players =  utilities.ValidationUtilities.getPlayerNames(request, players);
    		String [] theCurrentPlayers = players.split(", ");
			//request.setAttribute("theCurrentPlayers", theCurrentPlayers);
			System.out.println(Arrays.toString(theCurrentPlayers));
		
			for (int j = 0; j < availablePlayers.length; j++) {
				if (!availablePlayers[j].equals(theCurrentPlayers[0]) 
						&& !availablePlayers[j].equals(theCurrentPlayers[1])
						&& !availablePlayers[j].equals(theCurrentPlayers[2])
						&& !availablePlayers[j].equals(theCurrentPlayers[3])) {
					theAvailablePlayers.add(availablePlayers[j]);
				} 
			}
			request.setAttribute("theCurrentPlayers", theCurrentPlayers);
			request.setAttribute("theAvailablePlayers", theAvailablePlayers);
			request.setAttribute("gameID", gameID);
			}
		}
	
	public void switchThem(HttpServletRequest request, String currentPlayer, String newPlayer, String gameID) throws Exception{
		
		String currentPlayerID = utilities.ValidationUtilities.getPlayerNumber(request, currentPlayer);
		String newPlayerID = utilities.ValidationUtilities.getPlayerNumber(request, newPlayer);
		
		System.out.println("The current player id is: " + currentPlayerID);
		System.out.println("The new player id is: " + newPlayerID);
		
		
		statement = connect.createStatement();
		statement.executeUpdate("UPDATE ch_user_game SET Userid= "+ newPlayerID +"  where Gameid= "+ gameID + " && Userid= " + currentPlayerID);
		
		}
	
	
	public void closeSlot(HttpServletRequest request, String slotID) throws Exception{
		System.out.println("The slot ID recieved is: "+ slotID);
			System.out.println("Im in closeSlot");
			//String seasonID = null;
			//Statement statement1 = null;
			//ResultSet resultSet1 = null;
			statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * from ch_slot where id= "+ slotID);
			  
			  while(resultSet.first()){
				  //Slot slot = new Slot();
				  String playerIDs = resultSet.getString("availablePlayers");
				  String gameID = resultSet.getString("gameID");
				  
			  
			System.out.println("Available Players: "+ playerIDs);
			Statement statement2 = null;
			  statement2 = connect.createStatement();
			  statement2.executeUpdate("UPDATE ch_slot SET status= 0 Where id = " + slotID); 
			  
				String [] players = playerIDs.split(", ");
				List<Integer> indexes = new ArrayList<Integer>();
				//String [] indexes = new String[4];
				int hold1,hold2,hold3,hold4;
				hold1 = new Random().nextInt(players.length);
				hold2 = new Random().nextInt(players.length);
				hold3 = new Random().nextInt(players.length);
				hold4 = new Random().nextInt(players.length);
				
				do{
					if(hold1==hold2 ||hold1 == hold3 || hold1 == hold4){
						hold1 = new Random().nextInt(players.length);
					}else{
						indexes.add(0,hold1);
						if(hold2==hold3||hold2==hold4){
							hold2 = new Random().nextInt(players.length);
						}
						else{
							indexes.add(1,hold2);
							if(hold3==hold4){
								hold3 = new Random().nextInt(players.length);
							}else{
								indexes.add(2,hold3);
								indexes.add(3,hold4);
							}
						}
					}
				}while(indexes.size()<4);
				
				
				String [] playingPlayers = new String[4];
				playingPlayers[0] = players[indexes.get(0)];
				playingPlayers[1] = players[indexes.get(1)];
				playingPlayers[2] = players[indexes.get(2)];
				playingPlayers[3] = players[indexes.get(3)];
				
				for(int i=0; i < playingPlayers.length ;i++){
					//Statement statement3 = null;
					statement = connect.createStatement();
				    PreparedStatement preparedStatement = connect.prepareStatement("insert into ch_user_game values (?,?,null,null)");
				    preparedStatement.setString(1, playingPlayers[i]);	//userID
				    preparedStatement.setString(2, gameID); // gameID
				    preparedStatement.executeUpdate();
				}
			    
				
				StringBuilder builder = new StringBuilder();
				
				if (playingPlayers.length >= 1) {
					builder.append(playingPlayers[0]);
				}
		
				for (int i = 1; i < playingPlayers.length; i++) { 
					builder.append(",");
					builder.append(playingPlayers[i]);
				}
				
				String thePlayers = builder.toString();
				System.out.println("Playing Players: "+thePlayers);
				
				statement = connect.createStatement();
				ResultSet resultSet1 = statement.executeQuery("Select * from ch_game where id= "+ gameID);
				while(resultSet1.next()){
					String seasonID= resultSet1.getString("seasonID");
					request.setAttribute("seasonID", seasonID);
				}
				
			  };
		}
			
		
		public void findOpenGameSlots(HttpServletRequest request, String userID) throws Exception {
			  //Game game = new Game();
			List<Slot> slots = new ArrayList<Slot>();
		  	try {
			  		statement = connect.createStatement();
				    resultSet = statement.executeQuery("SELECT * FROM ch_user WHERE id= " + userID );
				    //String userGender = null;
				    resultSet.next();
				    String userGender = resultSet.getString("gender");
				    
				    ResultSet resultSet2;
				    resultSet2 = statement.executeQuery("Select * from ch_slot "
				    		+ "join ch_game "
				    		+ "on ch_game.id = ch_slot.gameID "
				    		+ "join ch_season "
				    		+ "on ch_game.seasonid = ch_season.id "
				    		+ "where ch_slot.gender IN (\""+ userGender +"\", \"X\") and ch_slot.status = 1");
				    
				    while (resultSet2.next()) {
				    	Slot slot = new Slot();
				    	int num = resultSet2.getInt("dayOfWeek");
				    	int givenTime = resultSet2.getInt("time");
				    	String slotID = resultSet2.getString("id");
				    	String dayOfWeek = utilities.ValidationUtilities.numberToDay(num);
				    	String time = utilities.ValidationUtilities.toTime(request,givenTime);
				    	slot.setDayOfWeek(dayOfWeek);
				    	slot.setTime(time);
				    	slot.setSeasonName(ValidationUtilities.seasonName(resultSet2.getString("season")));
				    	slot.setYear(resultSet2.getString("year"));
				    	slot.setGender(ValidationUtilities.genderName(resultSet2.getString("gender")));
				    	slot.setScheduledDate(ValidationUtilities.dateFullYear(resultSet2.getString("scheduledDate")));
				    	slot.setId(slotID);    
				    	request.setAttribute("slotID", slot.getId());
				    	slot.setConflict(checkSlotConflict(userID,slotID));
				    	slots.add(slot);
					}
			    } catch (SQLException e) {
			      throw e;
			}
			  	request.setAttribute("slots", slots);
		} 
		

public int checkSlotConflict(String userID, String slotID) {
    	try {
			statement2 = connect.createStatement();
		    ResultSet resultSet3;
	    	System.out.println("I get into the third one with user " + userID + " and slot " + slotID);
		    resultSet3 = statement2.executeQuery("SELECT * from ch_user_slot where Userid = \"" + userID + "\" and Slotid = \"" + slotID + "\"");
		    while (resultSet3.next()) {
		    	return resultSet3.getInt("conflict");
		    }
	  	} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
}
		/*
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
		}*/
	}