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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
					String emailAddress = request.getParameter("emailAddress");
					String password = request.getParameter("password");
				
			      // Statements allow to issue SQL queries to the database
			      statement = connect.createStatement();
			      
			      switch(option) {
				      case "register":
					      resultSet = statement.executeQuery("select * from ch_user where emailAddress = \"" + emailAddress + "\""); 
			    	  break;
				      case "login":
					      resultSet = statement.executeQuery("select * from ch_user where emailAddress = \"" + emailAddress + "\" and password = \"" + password + "\""); 
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
		
		public String getUserId(HttpServletRequest request) throws Exception {
		    try {
		    	  String username = request.getParameter("username");
				  String password = request.getParameter("password");
			      statement = connect.createStatement();
			      resultSet = statement.executeQuery("select id from ch_user where username = \"" + username + "\" and password = \"" + password + "\"");
			      while (resultSet.next()) {
			    	  String userID = resultSet.getString("id");
					     return userID; 
			      }
			    } catch (Exception e) {
			      throw e;
			    }
		    return "";
		}
		
		public String getName(HttpServletRequest request) throws Exception {
		    try {
		    	  String userID = getUserId(request);
			      statement = connect.createStatement();
			      resultSet = statement.executeQuery("select firstName, lastName from ch_user where id = \"" + userID + "\"");
			      while (resultSet.next()) {
			    	  String username = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
					     return username; 
			      }
			    } catch (Exception e) {
			      throw e;
			    }
		    return "";
		}
		
		public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    try {
		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      
		      // PreparedStatements can use variables and are more efficient
		      preparedStatement = connect.prepareStatement("insert into ch_user values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		      // columns are 0 `id`, 1 `username`, 2 `password`, 3 `emailAddress`
		      // 4 `dateCreated`, `userStatus`, `firstName`, `lastName`, `gender`, 
		      // 9 `phoneNumber`, `streetAddress`, `city`, `province`, `postalCode`, 
		      // 14 `country`, `photo`, `dateOfBirth`, `emergencyContactName`, 
		      // 18 `emergencyContactPhoneNumber`
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
		      preparedStatement.setString(12, request.getParameter("province"));					// province
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
		
		public void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
			  List<Post> posts = new ArrayList<Post>();
			  
			  	try{
				    // Statements allow to issue SQL queries to the database
				    statement = connect.createStatement();
				    resultSet = statement.executeQuery("select * from ch_post");
				      
				    while (resultSet.next()) {
				    	  Post post = new Post();
				    	  post.setTitle(resultSet.getString("title"));
				    	  post.setId(resultSet.getString("id"));
				    	  post.setPost_date(resultSet.getString("post_date"));
				    	  post.setContent(resultSet.getString("content"));
				    	  post.setUserid(resultSet.getString("Userid"));
				    	  posts.add(post);
				    }
			    
				  } catch (SQLException e) {
				      throw e;
				  }
			  	request.setAttribute("posts", posts);
		} 
}