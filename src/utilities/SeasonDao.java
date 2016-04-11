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
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Season;
import model.UserSeason;
import utilities.DatabaseAccess;

public class SeasonDao {
	private Connection connect = null;
	private Statement statement = null;
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
			connect = DatabaseAccess.connectDataBase();
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
		}  finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public String addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String season_id = null;
		try {

			String theDate = request.getParameter("theDate");

			Calendar c = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(theDate);
			c.setTime(date);
			int year = c.get(Calendar.YEAR);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			connect = DatabaseAccess.connectDataBase();
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

		}catch (Exception e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return season_id;
	}

	public void getUserSeasons(HttpServletRequest request) throws Exception {

		List<UserSeason> seasons = new ArrayList<UserSeason>();
		List<String> seasonIDs = new ArrayList<String>();
		String userID = (String) request.getAttribute("userID");

		try {
			connect = DatabaseAccess.connectDataBase();
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
				if (season.getGames() == 0 || season.getWins() == 0) {
					season.setPercentage(0);
				} else {
					season.setPercentage(season.getWins()*100/season.getGames());

				}
				seasons.add(season);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		request.setAttribute("seasons", seasons);

	}

	public void listSeasonIDs(HttpServletRequest request) throws Exception {
		List<String> seasons = new ArrayList<String>();

		try {
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT id from ch_season");

			while (resultSet.next()) {
				seasons.add(resultSet.getString("id"));
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		request.setAttribute("seasons", seasons);

	}

	public void closeSeason(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String seasonID = (String) request.getAttribute("seasonID");
		List <String> slotIDs = new ArrayList<String>();
		GameDao game = new GameDao();
		String gameIDforError= null;
		List <String> playerIDs  = new ArrayList<String>();
		boolean slotsFull = true;

		try {
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT slot.id, game.id from ch_game game JOIN ch_season seas ON game.Seasonid = seas.id "
					+ "JOIN ch_slot slot ON game.id = slot.gameID WHERE seas.id = " + seasonID + " AND slot.status = 1");

			while (resultSet.next()) {
				gameIDforError = resultSet.getString("game.id");
				slotIDs.add(resultSet.getString("slot.id"));
			}

			try {
				for (String k : slotIDs) {
					resultSet = statement.executeQuery("SELECT * from clubhub.ch_user_slot us JOIN ch_slot slot "
							+ "ON us.Slotid = slot.id where id="+ k); // This should be user_slot

					while(resultSet.next()){
						playerIDs.add(resultSet.getString("Userid"));
					}
					
					if (playerIDs.size() < 8) {
						slotsFull = false;
					}

				}
				
				if (slotsFull) {
					for (String k : slotIDs) {
						game.closeSlot(request, k);
					}
				} else {
					request.setAttribute("errorString", "All slots in a season must have at least 8 users before closing a season");
				}
			} catch (SQLException e) {
				statement.executeUpdate("DELETE FROM ch_user_game WHERE Gameid = " + gameIDforError);
				for (String k : slotIDs) {
					statement.executeUpdate("UPDATE ch_slot SET status = 1 WHERE id = " + k);
				}
			}
		} catch (SQLException e){
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	public void notifyUsersOfSeason(HttpServletRequest request, HttpServletResponse response, String seasonID) throws Exception {

		try {
			SendEmail email = new SendEmail();
			String thisSeasonID = request.getParameter("seasonID");
			email.sendAvailabiltyOpenEmail(request, response, thisSeasonID);
			
		} catch (MessagingException mex) {
		}
	}

	public void listOpenSeasons(HttpServletRequest request) throws Exception {
		List<Season> seasons = new ArrayList<Season>();
		boolean hasOpenSlots;
		try { 
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * from ch_season");
			while (resultSet.next()) {
				Season season = new Season();
				hasOpenSlots = false;
				season.setId(resultSet.getString("id"));
				season.setYear(resultSet.getString("year"));
				season.setSeason(ValidationUtilities.seasonName(resultSet.getString("season")));
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
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		request.setAttribute("seasons", seasons);
	} 
	public void listClosedSeasons(HttpServletRequest request) throws Exception {
		List<Season> seasons = new ArrayList<Season>();
		boolean hasOpenSlots;
		try { 
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * from ch_season");
			while (resultSet.next()) {
				Season season = new Season();
				hasOpenSlots = false;
				season.setId(resultSet.getString("id"));
				season.setYear(resultSet.getString("year"));
				season.setSeason(ValidationUtilities.seasonName(resultSet.getString("season")));
				season.setGender(ValidationUtilities.genderName(resultSet.getString("gender")));
				season.setStartDate(resultSet.getString("startDate"));
				season.setStartTime(ValidationUtilities.toTime(resultSet.getInt("startTime")));
				season.setDayOfWeek(ValidationUtilities.numberToDay(resultSet.getInt("dayOfWeek")));
				season.setDuration(resultSet.getString("duration"));
				season.setStartDateFullYear(ValidationUtilities.dateFullYear(resultSet.getString("startDate")));
				statement = connect.createStatement();
				resultSet1 = statement.executeQuery("SELECT * from ch_slot slot join ch_game game ON slot.gameID = game.id "
						+ "WHERE Seasonid = " + resultSet.getString("id") + " AND status = 0");
				while (resultSet1.next()) {			    	  
					hasOpenSlots = true;
				}

				if (hasOpenSlots) {
					seasons.add(season);
				} 

			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		request.setAttribute("closedSeasons", seasons);
	} 


	public void listAll(HttpServletRequest request) throws Exception {
		List<Season> seasons = new ArrayList<Season>();
		try{  	
			connect = DatabaseAccess.connectDataBase();
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
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		request.setAttribute("seasons", seasons);
	}

	public void deleteSeason(HttpServletRequest request, HttpServletResponse response, String seasonID) throws Exception {
		try {
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			statement.executeUpdate("delete from ch_season where id =" + seasonID); 

		} catch (SQLException e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
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
			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM ch_season WHERE id= " + seasonID);

			while (resultSet.next()) {
				season.setYear(resultSet.getString("year"));
				season.setSeason(ValidationUtilities.seasonName(resultSet.getString("season")));
				season.setId(resultSet.getString("id"));
				season.setGender(resultSet.getString("gender"));
				season.setStartDate(resultSet.getString("startDate"));
				season.setStartDateFullYear(ValidationUtilities.dateFullYear(resultSet.getString("startDate")));season.setStartTime(ValidationUtilities.toTime( Integer.parseInt(resultSet.getString("startTime"))));
				season.setDayOfWeek(ValidationUtilities.numberToDay(Integer.parseInt(resultSet.getString("dayOfWeek"))));
				season.setDuration(resultSet.getString("duration"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
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


			connect = DatabaseAccess.connectDataBase();
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_season SET year='" + year + "', season='" + season + "', gender='" + gender + 
					"', startDate='" + startDate + "', startTime='" + startTime + "' dayOfWeek='" + dayOfWeek + "'duration='" 
					+ duration + "' WHERE id='" + seasonID + "'");

			//preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (connect != null) {
				// closes the database connection
				try {
					connect.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}