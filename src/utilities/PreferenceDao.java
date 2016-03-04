package utilities;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: PreferenceDao - prepares a database access object for the Preference model
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

import model.Preference;
import model.User;
import utilities.DatabaseAccess;

public class PreferenceDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public PreferenceDao() {
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
	    try {	// connect to the database
	    		statement = connect.createStatement();
			  
			  	// first let's find the current tax rate from the preferences table and store it in a variable
	    		taxRate(request);
	    		double tax_rate = Double.parseDouble(request.getParameter("tax_rate"));
			    // users have the option to enter from 1-5 of these at a time
			    // check that parameters for lineItem01 were supplied and cost is a number
			    if (request.getParameter("lineItem01Description") != "" && 
		    		request.getParameter("lineItem01Cost") != "" && 
		    		ValidationUtilities.isInt(request.getParameter("lineItem01Cost")))	{
			    		// calculate the cost depending on the current tax rate
			    		double cost = Double.parseDouble(request.getParameter("lineItem01Cost"));
			    		double tax = cost * tax_rate;
			    		// columns are id, Preference_line_itemsid, Preferenceid
			    		preparedStatement = connect.prepareStatement("insert into ch_Preference_line_items values (default, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("lineItem01Description")); // description
			    		preparedStatement.setString(2, request.getParameter("lineItem01Cost")); // cost
			    		preparedStatement.setString(3, String.valueOf(tax)); // tax
			    		preparedStatement.executeUpdate();
			    }
			    // check that parameters for lineItem02 were supplied and cost is a number
			    if (request.getParameter("lineItem02Description") != "" && 
		    		request.getParameter("lineItem02Cost") != "" && 
		    		ValidationUtilities.isInt(request.getParameter("lineItem02Cost")))	{
			    		// calculate the cost depending on the current tax rate
			    		double cost = Double.parseDouble(request.getParameter("lineItem02Cost"));
			    		double tax = cost * tax_rate;
			    		// columns are id, Preference_line_itemsid, Preferenceid
			    		preparedStatement = connect.prepareStatement("insert into ch_Preference_line_items values (default, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("lineItem02Description")); // description
			    		preparedStatement.setString(2, request.getParameter("lineItem02Cost")); // cost
			    		preparedStatement.setString(3, String.valueOf(tax)); // tax
			    		preparedStatement.executeUpdate();
			    }
			    // check that parameters for lineItem03 were supplied and cost is a number
			    if (request.getParameter("lineItem03Description") != "" && 
		    		request.getParameter("lineItem03Cost") != "" && 
		    		ValidationUtilities.isInt(request.getParameter("lineItem03Cost")))	{
			    		// calculate the cost depending on the current tax rate
			    		double cost = Double.parseDouble(request.getParameter("lineItem03Cost"));
			    		double tax = cost * tax_rate;
			    		// columns are id, Preference_line_itemsid, Preferenceid
			    		preparedStatement = connect.prepareStatement("insert into ch_Preference_line_items values (default, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("lineItem03Description")); // description
			    		preparedStatement.setString(2, request.getParameter("lineItem03Cost")); // cost
			    		preparedStatement.setString(3, String.valueOf(tax)); // tax
			    		preparedStatement.executeUpdate();
			    }
			    // check that parameters for lineItem04 were supplied and cost is a number
			    if (request.getParameter("lineItem04Description") != "" && 
		    		request.getParameter("lineItem04Cost") != "" && 
		    		ValidationUtilities.isInt(request.getParameter("lineItem04Cost")))	{
			    		// calculate the cost depending on the current tax rate
			    		double cost = Double.parseDouble(request.getParameter("lineItem04Cost"));
			    		double tax = cost * tax_rate;
			    		// columns are id, Preference_line_itemsid, Preferenceid
			    		preparedStatement = connect.prepareStatement("insert into ch_Preference_line_items values (default, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("lineItem04Description")); // description
			    		preparedStatement.setString(2, request.getParameter("lineItem04Cost")); // cost
			    		preparedStatement.setString(3, String.valueOf(tax)); // tax
			    		preparedStatement.executeUpdate();
			    }
			    // check that parameters for lineItem05 were supplied and cost is a number
			    if (request.getParameter("lineItem05Description") != "" && 
		    		request.getParameter("lineItem05Cost") != "" && 
		    		ValidationUtilities.isInt(request.getParameter("lineItem05Cost")))	{
			    		// calculate the cost depending on the current tax rate
			    		double cost = Double.parseDouble(request.getParameter("lineItem05Cost"));
			    		double tax = cost * tax_rate;
			    		// columns are id, Preference_line_itemsid, Preferenceid
			    		preparedStatement = connect.prepareStatement("insert into ch_Preference_line_items values (default, ?, ?, ?)");
			    		preparedStatement.setString(1, request.getParameter("lineItem05Description")); // description
			    		preparedStatement.setString(2, request.getParameter("lineItem05Cost")); // cost
			    		preparedStatement.setString(3, String.valueOf(tax)); // tax
			    		preparedStatement.executeUpdate();
			    }
			    
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void listAllLineItems(HttpServletRequest request) throws Exception {
		  List<Preference> lineitems = new ArrayList<Preference>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_Preferencs");
			    while (resultSet.next()) {
			    	Preference pref = new Preference();
			    	pref.setId(resultSet.getString("id"));
			    	lineitems.add(pref);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("lineitems", lineitems);
	} 

	public void deletePreference(HttpServletRequest request, HttpServletResponse response, String PreferenceID) throws Exception {
		
	}

	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
	}
	
	public void findPreference(HttpServletRequest request, String PreferenceID) throws Exception { 
		
	} 
	
	public void editPreference(HttpServletRequest request, HttpServletResponse response, String _PreferenceID) throws Exception {
		
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
	
	public void taxRate(HttpServletRequest request) { 
	  	ResultSet taxRate;
		try {
	  		statement = connect.createStatement();
			taxRate = statement.executeQuery("SELECT * FROM ch_preferences");
		  	double tax_rate = 0;
		    while (taxRate.next()) {
		    	tax_rate = Double.parseDouble(taxRate.getString("tax_rate"));
		    }
		    request.setAttribute("tax_rate", tax_rate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

