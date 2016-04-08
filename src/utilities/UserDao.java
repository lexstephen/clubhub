package utilities;
import java.io.InputStream;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import utilities.DatabaseAccess;

@MultipartConfig(maxFileSize = 10177215) // upload file's size up to 16MB
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
			String passwordHashed = HashPassword.hashPassword(password);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			switch(option) {
			case "register":
				resultSet = statement.executeQuery("select * from ch_user where username = \"" + username + "\""); 
				break;
			case "login":
				resultSet = statement.executeQuery("select * from ch_user where username = \"" + username + "\" and password = \"" + passwordHashed + "\""); 
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
				String password = request.getParameter("password");
				System.out.println("Password here is " + password);
				String passwordHashed = HashPassword.hashPassword(password);
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select id from ch_user where username = \"" + request.getParameter("username") + "\" and password = \"" + passwordHashed + "\"");
				while (resultSet.next()) {
					session.setAttribute("loggedInUserID", resultSet.getString("id")); 
					System.out.println("getUserID id = " + session.getAttribute("loggedInUserID"));
				}
			} catch (Exception e) {
				throw e;
			}
			break;
		case "viewprofile":
			try {
				HttpSession session = request.getSession();
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select id from ch_user where username = \"" + request.getParameter("username") + "\"");
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
		String password = request.getParameter("password");
		String passwordHashed = HashPassword.hashPassword(password);
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

			/* ********** take care of image uploading *****************/

			InputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("profilePhoto");
			if (filePart != null) {
				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}

			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("insert into ch_user values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, request.getParameter("username")); 					// username
			preparedStatement.setString(2, passwordHashed); 									// password
			preparedStatement.setString(3, request.getParameter("emailAddress"));				// emailAddress
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // dateCreated
			preparedStatement.setString(5, "unverified");										// userStatus
			preparedStatement.setString(6, request.getParameter("firstName"));					// firstName
			preparedStatement.setString(7, request.getParameter("lastName")); 					// lastName
			preparedStatement.setString(8, request.getParameter("gender")); 					// gender
			preparedStatement.setString(9, request.getParameter("telephone"));					// phoneNumber
			preparedStatement.setString(10, request.getParameter("streetAddress"));				// streetAddress
			preparedStatement.setString(11, request.getParameter("city"));						// city
			preparedStatement.setString(12, province);											// province
			preparedStatement.setString(13, request.getParameter("postalCode"));				// postalCode
			preparedStatement.setString(14, request.getParameter("country"));					// country
			//preparedStatement.setString(15, request.getParameter("profilePhoto"));			// profilePhoto
			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				preparedStatement.setBlob(15, inputStream);
			}
			preparedStatement.setString(16, request.getParameter("dateOfBirth"));					// dateOfBirth
			preparedStatement.setString(17, request.getParameter("emergencyContactName"));		// emergencyContactName
			preparedStatement.setString(18, request.getParameter("emergencyContactPhoneNumber"));	// emergencyContactPhoneNumber
			preparedStatement.executeUpdate();
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

	public void listAllUsers(HttpServletRequest request) throws Exception {
		List<User> users = new ArrayList<User>();
		try{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_user ORDER BY username");

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

	public void getLastUsers(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		//this method returns the latest 3 users
		List<User> users = new ArrayList<User>();
		try{
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_user ORDER BY id DESC LIMIT 3");

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
			String number = resultSet.getString("phoneNumber");
			// 
			user.setTelephone(number);
			user.setFormattedTelephone(String.format("(%s) %s-%s", number.substring(0, 3), number.substring(3, 6), number.substring(6, 10)));
			user.setCity(resultSet.getString("city"));
			user.setProvince(resultSet.getString("province"));
			user.setPostalCode(resultSet.getString("postalCode"));
			user.setCountry(resultSet.getString("country"));
			user.setPhoto(resultSet.getString("photo"));

			String MyDate = resultSet.getString("dateOfBirth");
			SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatDate = new SimpleDateFormat("MMM dd yyyy");
			Date date = (Date) parseDate.parse(MyDate);
			String DisplayDate = formatDate.format(date);
			user.setFormattedDateOfBirth(DisplayDate);
			user.setDateOfBirth(MyDate);

			user.setEmergencyContactName(resultSet.getString("emergencyContactName"));
			String contactNumber = resultSet.getString("emergencyContactPhoneNumber");
			// String.format("(%s) %s-%s", contactNumber.substring(0, 3), contactNumber.substring(3, 6), contactNumber.substring(6, 10))
			user.setEmergencyContactPhoneNumber(contactNumber);
			user.setFormattedEmergencyContactPhoneNumber(String.format("(%s) %s-%s", contactNumber.substring(0, 3), contactNumber.substring(3, 6), contactNumber.substring(6, 10)));
		}

		request.setAttribute("user", user);
	}

	public void editUser(HttpServletRequest request, HttpServletResponse response, String _userID) throws Exception {
		try {			
			String userStatus = request.getParameter("userStatus");
			String password = request.getParameter("password");
			System.out.println("This userDao password is " + password);
			String passwordHashed = HashPassword.hashPassword(password);

			String province = null;
			switch(request.getParameter("country")) {
			case "Canada":
				province = request.getParameter("province");
				break;
			case "United States of America":
				province = request.getParameter("state");
				break;
			}

			String qry = "";

			statement = connect.createStatement();
			/* ********** take care of image uploading *****************/

			InputStream inputStream = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("profilePhoto");
			if (filePart != null) {
				// obtains input stream of the upload file
				if (filePart.getSize() != 0) {
					inputStream = filePart.getInputStream();
				}
			}


			qry = "UPDATE clubhub.ch_user SET username = ?, password = ?, emailAddress = ?" // 1 2 3
					+ ", firstName = ?, lastName = ?, gender = ?, phoneNumber = ?"  // 4 5 6 7
					+ ", streetAddress = ?, city = ?, province = ?, postalCode = ?"  // 8 9 10 11
					+ ", country = ?, dateOfBirth = ?, emergencyContactName = ?, emergencyContactPhoneNumber = ?";  // 12, 13, 14, 15

			if (userStatus != null) {
				// fetches input stream of the upload file for the blob column
				qry += ", userStatus = ?"; // 16
			}

			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				qry += ", photo = ?"; //inputStream // 17 or 16
			}

			qry += " WHERE id = ?"; // 18 or 17 or 16

			String postalCode = request.getParameter("postalCode");
			postalCode = postalCode.replaceAll("\\s+","");

			preparedStatement = connect.prepareStatement(qry);
			preparedStatement.setString(1, request.getParameter("username")); 					// username
			preparedStatement.setString(2, passwordHashed); 									// password
			preparedStatement.setString(3, request.getParameter("emailAddress"));				// emailAddress
			preparedStatement.setString(4, request.getParameter("firstName"));					// firstName
			preparedStatement.setString(5, request.getParameter("lastName")); 					// lastName
			preparedStatement.setString(6, request.getParameter("gender")); 					// gender
			preparedStatement.setString(7, request.getParameter("telephone"));					// phoneNumber
			preparedStatement.setString(8, request.getParameter("streetAddress"));				// streetAddress
			preparedStatement.setString(9, request.getParameter("city"));						// city
			preparedStatement.setString(10, province);											// province
			preparedStatement.setString(11, postalCode);										// postalCode
			preparedStatement.setString(12, request.getParameter("country"));					// country
			preparedStatement.setString(13, request.getParameter("dateOfBirth"));				// dateOfBirth
			preparedStatement.setString(14, request.getParameter("emergencyContactName"));		// emergencyContactName
			preparedStatement.setString(15, request.getParameter("emergencyContactPhoneNumber"));	// emergencyContactPhoneNumber
			if (userStatus != null) {
				// we have a userStatus, set that
				preparedStatement.setString(16, request.getParameter("userStatus"));

				if (inputStream != null) {
					// we have an image, set that
					preparedStatement.setBlob(17, inputStream);
					// set the WHERE condition
					preparedStatement.setString(18, request.getParameter("userID"));
				} else {
					// we do not have an image 
					// set the WHERE condition
					preparedStatement.setString(17, request.getParameter("userID"));
				}
			} else {
				// we do not have a userStatus
				if (inputStream != null) {
					// we have an image, set that
					preparedStatement.setBlob(16, inputStream);
					preparedStatement.setString(17, request.getParameter("userID"));
				} else {
					// we do not have an image 
					// set the WHERE condition
					preparedStatement.setString(16, request.getParameter("userID"));
				}
			}

			preparedStatement.executeUpdate();
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
			System.out.println("I just set " + userID);
			//		request.setAttribute("userID", userID);
			deleteUser(request, response, userID);
		}		
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response, String _userID) throws Exception {
		String userID = _userID;
		try {
			statement = connect.createStatement();
			System.out.println("I am deleting " + userID);
			statement.executeUpdate("delete from ch_user where id =" + userID); 
		} catch (SQLException e) {
			throw e;
		}
	}
	/*
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = request.getParameter("userID");
		  try {
			  statement = connect.createStatement();
				System.out.println("I am deleting " + userID);
			  statement.executeUpdate("delete from ch_user where id =" + userID); 
		  } catch (SQLException e) {
		      throw e;
		  }
	} */
}