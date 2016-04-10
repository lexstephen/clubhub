package utilities;
import java.io.InputStream;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: ColourSchemeDao - prepares a database access object for the ColourScheme model
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
import javax.servlet.http.Part;

import model.ColourScheme;
import model.User;
import utilities.DatabaseAccess;

public class ColourSchemeDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ColourSchemeDao() {
		try {
			connect = DatabaseAccess.connectDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return true;
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {	
	    	
	    	String option = request.getParameter("csid");
	    	System.out.println("I see this option: " + option);
	    	switch(option) {
	    	case "000":
	    	// connect to the database
	    		statement = connect.createStatement();
	    		// id, name, dark_colour, med_colour, light_colour, text_colour
	    		
			    		preparedStatement = connect.prepareStatement("insert into ch_colour_scheme (`id`, `name`, `dark_colour`,"
			    				+ " `med_colour`, `light_colour`, `text_colour`) values (default, ?, ?, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("name")); // name
			    		preparedStatement.setString(2, request.getParameter("dark_colour")); // dark_colour 
			    		preparedStatement.setString(3, request.getParameter("med_colour")); // med_colour
			    		preparedStatement.setString(4, request.getParameter("light_colour")); // light_colour
			    		preparedStatement.setString(5, request.getParameter("text_colour")); // text_colour
			    		preparedStatement.executeUpdate();
		    

			  	      ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();");
			  	      
			  	      while(rs.next()){
			  	    	  request.setAttribute("csid", rs.getString("LAST_INSERT_ID()"));
  			  	      }
			  	      
			  	      rs.close();
	    		break;
    		default:
	    	// connect to the database
	    		statement = connect.createStatement();
	    		// id, name, dark_colour, med_colour, light_colour, text_colour
	    	//


			    		preparedStatement = connect.prepareStatement("UPDATE ch_colour_scheme SET `name`=?, `dark_colour`=?, `med_colour`=?, `light_colour`=?, `text_colour`=? WHERE `id` = " + request.getParameter("csid"));
			    		preparedStatement.setString(1, request.getParameter("name")); // name
			    		preparedStatement.setString(2, request.getParameter("dark_colour")); // dark_colour 
			    		preparedStatement.setString(3, request.getParameter("med_colour")); // med_colour
			    		preparedStatement.setString(4, request.getParameter("light_colour")); // light_colour
			    		preparedStatement.setString(5, request.getParameter("text_colour")); // text_colour
			    		preparedStatement.executeUpdate();
			    		preparedStatement.close();
    			break;
	    	}
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void showColourScheme(HttpServletRequest request) throws Exception {
    	ColourScheme cscheme = new ColourScheme();
		try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * FROM ch_colour_scheme cs JOIN ch_preferences p ON cs.id = p.Colour_schemeid AND p.status = 1");
			    while (resultSet.next()) {
			    	cscheme.setName(resultSet.getString("name"));
			    	cscheme.setDark_colour(resultSet.getString("dark_colour"));
			    	cscheme.setMed_colour(resultSet.getString("med_colour"));
			    	cscheme.setLight_colour(resultSet.getString("light_colour"));
			    	cscheme.setText_colour(resultSet.getString("text_colour"));
			    }
			    resultSet.close();
				request.setAttribute("colour_scheme", cscheme);
		    } catch (SQLException e) {
			      throw e;
			}
	} 

	public void listAllColourSchemes(HttpServletRequest request) throws Exception {
		List<ColourScheme> colour_schemes = new ArrayList<ColourScheme>();
		try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * FROM ch_colour_scheme");
			    while (resultSet.next()) {
			    	ColourScheme cscheme = new ColourScheme();
			    	cscheme.setId(resultSet.getString("id"));
			    	cscheme.setName(resultSet.getString("name"));
			    	cscheme.setDark_colour(resultSet.getString("dark_colour"));
			    	cscheme.setMed_colour(resultSet.getString("med_colour"));
			    	cscheme.setLight_colour(resultSet.getString("light_colour"));
			    	cscheme.setText_colour(resultSet.getString("text_colour"));
			    	colour_schemes.add(cscheme);
			    }
			    resultSet.close();
				request.setAttribute("colour_schemes", colour_schemes);
		    } catch (SQLException e) {
			      throw e;
			}
	} 

	public void deleteColourScheme(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String csID = request.getParameter("csID");
		
		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_colour_scheme where id ="  + csID); 
			  statement.close();
		  } catch (SQLException e) {
		      throw e;
		  }	
	}
}

