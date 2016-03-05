package utilities;
/****************************************************************************************************
 * Project: Hackers 1995
 * Assignment: COMP 3095 Assignment 2
 * Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
 * Student Number: 100563954, 100911472, 100898311
 * Date: December 4th, 2016
 * Description: UserDao - prepares a database access object for the User model
 ****************************************************************************************************/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import model.User;
import utilities.DatabaseAccess;

public class UserDao {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public UserDao() {
		try {
			connect = DatabaseAccess.connectDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isInDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String option = request.getParameter("option");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			switch(option) {
			case "register":
				resultSet = statement.executeQuery("select * from ch_user where username = \"" + username + "\""); 
				break;
			case "login":
				resultSet = statement.executeQuery("select * from ch_user where username = \"" + username + "\" and password = \"" + password + "\""); 
				break;
			default:
				resultSet = null;
				break;
			}

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

	public void getUserId(HttpServletRequest request, String option) throws Exception {
		switch(option) {
		case "login":
			try {
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select id from ch_user where username = \"" + request.getParameter("username") + "\" and password = \"" + request.getParameter("password") + "\"");
				while (resultSet.next()) {
					session.setAttribute("loggedInUserID", resultSet.getString("id")); 
				}
			} catch (Exception e) {
				throw e;
			}
			break;
		case "viewprofile":
			try {
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select id from ch_user where username = \"" + request.getParameter("username") + "\" and password = \"" + request.getParameter("password") + "\"");
				while (resultSet.next()) {
					session.setAttribute("userID", resultSet.getString("id")); 
				}
			} catch (Exception e) {
				throw e;
			}
			break;
		}
	}

	public void getUserAge(HttpServletRequest request) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select dateCreated from ch_user where id = '" + request.getParameter("userID") + "'");
			while (resultSet.next()) {
				String MyDate = resultSet.getString("dateCreated");
				SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat formatDate = new SimpleDateFormat("MMM yyyy");
				Date date = (Date) parseDate.parse(MyDate);
				String DisplayDate = formatDate.format(date);
				
				
				request.setAttribute("dateCreated", DisplayDate);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void isAdmin(HttpServletRequest request) throws Exception {
		try {
			HttpSession session = request.getSession();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select userStatus from ch_user where id = '" + session.getAttribute("loggedInUserID") + "'");
			while (resultSet.next()) {
				session.setAttribute("userStatus", resultSet.getString("userStatus"));
				session.setAttribute("isAdmin", resultSet.getString("userStatus").equals("admin")?true:false);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void getName(HttpServletRequest request, String option) throws Exception {
		switch (option) {
		case "login": 
			try {
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select firstName, lastName from ch_user where id = \"" + session.getAttribute("loggedInUserID") + "\"");
				while (resultSet.next()) {
					session.setAttribute("loggedInUserFullName", resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
				}
			} catch (Exception e) {
				throw e;
			}
			break;
		case "viewprofile":
			try {
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select firstName, lastName from ch_user where id = \"" + request.getParameter("userID") + "\"");
				while (resultSet.next()) {
					session.setAttribute("userFullName", resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
				}
			} catch (Exception e) {
				throw e;
			}
			break;
		}
	}

	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// determine whether we should be looking for the province or state variable, based on their country
			String province = null;
			switch(request.getParameter("country")) {
			case "Canada":
				province = request.getParameter("province");
				break;
			case "United States of America":
				province = request.getParameter("state");
				break;
			}

			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("insert into ch_user values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, request.getParameter("username")); 					// username
			preparedStatement.setString(2, request.getParameter("password1")); 					// password
			preparedStatement.setString(3, request.getParameter("emailAddress"));					// emailAddress
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // dateCreated
			preparedStatement.setString(5, "unverified");											// userStatus
			preparedStatement.setString(6, request.getParameter("firstName"));					// firstName
			preparedStatement.setString(7, request.getParameter("lastName")); 					// lastName
			preparedStatement.setString(8, request.getParameter("gender")); 						// gender
			preparedStatement.setString(9, request.getParameter("telephone"));					// phoneNumber
			preparedStatement.setString(10, request.getParameter("streetAddress"));				// streetAddress
			preparedStatement.setString(11, request.getParameter("city"));						// city
			preparedStatement.setString(12, province);											// province
			preparedStatement.setString(13, request.getParameter("postalCode"));					// postalCode
			preparedStatement.setString(14, request.getParameter("country"));						// country
			preparedStatement.setString(15, request.getParameter("profilePhoto"));				// profilePhoto
			preparedStatement.setString(16, request.getParameter("dateOfBirth"));					// dateOfBirth
			preparedStatement.setString(17, request.getParameter("emergencyContactName"));		// emergencyContactName
			preparedStatement.setString(18, request.getParameter("emergencyContactPhoneNumber"));	// emergencyContactPhoneNumber
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public void listAllUsers(HttpServletRequest request) throws Exception {
		List<User> users = new ArrayList<User>();
		try{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_user");

			while (resultSet.next()) {
				User user = new User();
				user.setUserid(resultSet.getString("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
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
				user.setPhoto(resultSet.getString("photo"));
				user.setDateOfBirth(resultSet.getString("dateOfBirth"));
				user.setEmergencyContactName(resultSet.getString("emergencyContactName"));
				user.setEmergencyContactPhoneNumber(resultSet.getString("emergencyContactPhoneNumber"));
				users.add(user);
			}

		} catch (SQLException e) {
			throw e;
		}
		request.setAttribute("users", users);
	} 


	public void findUser(HttpServletRequest request, String _userID) throws Exception {
		User user = new User();
		String userID = _userID;

		statement = connect.createStatement();
		resultSet = statement.executeQuery(
				"SELECT * FROM clubhub.ch_user WHERE id = '" + userID + "'");
		while (resultSet.next()) {
			user.setUserid(userID);
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setEmailAddress(resultSet.getString("emailAddress"));
			user.setUserStatus(resultSet.getString("userStatus"));
			user.setFirstName(resultSet.getString("firstName"));
			user.setLastName(resultSet.getString("lastName"));
			String gender = resultSet.getString("gender");
			user.setGender(gender);
			String teamGender = (gender.equals("F"))?"Women":"Men";	
			user.setTeamGender(teamGender);				
			user.setStreetAddress(resultSet.getString("streetAddress"));
			user.setTelephone(resultSet.getString("phoneNumber"));
			user.setCity(resultSet.getString("city"));
			user.setProvince(resultSet.getString("province"));
			user.setPostalCode(resultSet.getString("postalCode"));
			user.setCountry(resultSet.getString("country"));
			user.setPhoto(resultSet.getString("photo"));
			user.setDateOfBirth(resultSet.getString("dateOfBirth"));
			user.setEmergencyContactName(resultSet.getString("emergencyContactName"));
			user.setEmergencyContactPhoneNumber(resultSet.getString("emergencyContactPhoneNumber"));
		}

		request.setAttribute("user", user);
	}

	public void editUser(HttpServletRequest request, HttpServletResponse response, String _userID) throws Exception {
		try {			
			String userID = _userID;
			String username = request.getParameter("username");
			String password = request.getParameter("password1");
			String emailAddress = request.getParameter("emailAddress");
			String userStatus = request.getParameter("userStatus");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String streetAddress = request.getParameter("streetAddress");
			String city = request.getParameter("city");
			String province = null;
			switch(request.getParameter("country")) {
			case "Canada":
				province = request.getParameter("province");
				break;
			case "United States of America":
				province = request.getParameter("state");
				break;
			}
			String postalCode = request.getParameter("postalCode");
			String country = request.getParameter("country");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String emergencyContactName = request.getParameter("emergencyContactName");
			String emergencyContactPhoneNumber = request.getParameter("emergencyContactPhoneNumber");

			statement = connect.createStatement();
			statement.executeUpdate("UPDATE clubhub.ch_user SET username='" + username 
					+ "', password='" + password 
					+ "', emailAddress='" + emailAddress
					+ "', userStatus='" + userStatus
					+ "', firstName='" + firstName
					+ "', lastName='" + lastName
					+ "', gender='" + gender
					+ "', streetAddress='" + streetAddress
					+ "', city='" + city 
					+ "', province='" + province
					+ "', postalCode='" + postalCode
					+ "', country='" + country
					+ "', dateOfBirth='" + dateOfBirth
					+ "', emergencyContactName='" + emergencyContactName
					+ "', emergencyContactPhoneNumber='" + emergencyContactPhoneNumber
					+ "' WHERE id='" + userID + "';");
		} catch (Exception e) {
			throw e;
		}
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// this method edits the userType, accessLevel, and/or category in ch_user
		
		String [] markedForEdit = request.getParameterValues("userSelected");
		String userID, userStatus, executeString = "";
		
		userStatus = request.getParameter("userStatus"); // userStatus
		
	    if (!userStatus.equals("0")) {
	    	executeString = "userStatus='" + userStatus;
	    }
	    
	    if (!executeString.equals(""))
	    {
	    	System.out.println("executeString is not null. Here's userStatus values: " + userStatus);
	    	System.out.println("And here's executeString: " + executeString);
			for (String x : markedForEdit) 
			{
				userID = x;
			    statement = connect.createStatement();
				statement.executeUpdate("UPDATE ch_user SET " + executeString + "' WHERE id='" + userID + "'");
			}
		}				
	}

	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String [] markedForDeletion = request.getParameterValues("userSelected");
		for (String userID : markedForDeletion) {
			request.setAttribute("userID", userID);
			System.out.println("batchDelete userID: " + request.getAttribute("userID"));
			deleteUser(request, response);
		}		
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String userID = (String)request.getAttribute("userID").toString();
				
		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_user where id =" + userID); 
			  System.out.println("delte userID = " + userID);
		  } catch (SQLException e) {
		      throw e;
		  }
	}

}