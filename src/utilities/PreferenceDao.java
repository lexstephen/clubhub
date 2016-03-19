package utilities;
import java.io.InputStream;
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
import javax.servlet.http.Part;

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
	    		// id, image_logo, image_small_logo, club_name_long, club_name_short, 
	    		// featured_image_01, featured_image_02, featured_image_03, featured_image_04, featured_image_05, 
	    		// featured_image_06, featured_image_07, featured_image_08, featured_image_09, featured_image_10,
	    		// Colour_Schemeid, tax_rate, country

	    		
		        InputStream input_image_logo = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        Part filePart = request.getPart("image_logo");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_image_logo = filePart.getInputStream();
		        }
		        
		        InputStream input_image_small_logo = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("image_small_logo");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_image_small_logo = filePart.getInputStream();
		        }
		        /*
		        InputStream input_featured_image_01 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_01");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_01 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_02 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_02");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_02 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_03 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_03");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_03 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_04 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_04");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_04 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_05 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_05");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_05 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_06 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_06");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_06 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_07 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_07");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_07 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_08 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_08");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_08 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_09 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_09");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_09 = filePart.getInputStream();
		        }
		        
		        InputStream input_featured_image_10 = null; // input stream of the upload file 
		        // obtains the upload file part in this multipart request
		        filePart = request.getPart("featured_image_10");
		        if (filePart != null) {
		            // prints out some information for debugging
		            System.out.println(filePart.getName());
		            // obtains input stream of the upload file
		            input_featured_image_10 = filePart.getInputStream();
		        }
		        */
			    		preparedStatement = connect.prepareStatement("insert into ch_Preferences (`id`, `image_logo`, `image_small_logo`, `club_name_long`, `club_name_short`, `Colour_Schemeid`, `tax_rate`, `country`, `status`) values (default, ?, ?, ?, ?, ?, ?, ?, ?)");
			    		preparedStatement.setBlob(1, input_image_logo); // image_logo
			    		preparedStatement.setBlob(2, input_image_small_logo); // image_small_logo
			    		preparedStatement.setString(3, request.getParameter("club_name_long")); // club_name_long
			    		preparedStatement.setString(4, request.getParameter("club_name_short")); // club_name_short 
			    		/*
			    		preparedStatement.setBlob(5, input_featured_image_01); // featured_image_01
			    		preparedStatement.setBlob(6, input_featured_image_02); // featured_image_02
			    		preparedStatement.setBlob(7, input_featured_image_03); // featured_image_03 
			    		preparedStatement.setBlob(8, input_featured_image_04); // featured_image_04
			    		preparedStatement.setBlob(9, input_featured_image_05); // featured_image_05 
			    		preparedStatement.setBlob(10, input_featured_image_06); // featured_image_06
			    		preparedStatement.setBlob(11, input_featured_image_07); // featured_image_07
			    		preparedStatement.setBlob(12, input_featured_image_08); // featured_image_08
			    		preparedStatement.setBlob(13, input_featured_image_09); // featured_image_09
			    		preparedStatement.setBlob(14, input_featured_image_10); // featured_image_10
			    		preparedStatement.setInt(15, Integer.parseInt(request.getParameter("Colour_Schemeid"))); // Colour_Schemeid
			    		preparedStatement.setString(16, request.getParameter("tax_rate")); // tax_rate
			    		preparedStatement.setString(17, request.getParameter("country")); // country
			    		*/
			    		String colour_schemeid = request.getParameter("Colour_Schemeid");
			    		preparedStatement.setInt(5, Integer.parseInt(colour_schemeid)); // Colour_Schemeid
			    		preparedStatement.setString(6, request.getParameter("tax_rate")); // tax_rate
			    		preparedStatement.setString(7, request.getParameter("country")); // country
			    		String status = request.getParameter("status");
			    		preparedStatement.setInt(8, Integer.parseInt(status)); // status

			    		preparedStatement.executeUpdate(); 
			    		
			    		// 
			    		String latestID = "";
				  		statement = connect.createStatement();
					    resultSet = statement.executeQuery("SELECT last_insert_id()");
					    while (resultSet.next()) {
					    	latestID = resultSet.getString("last_insert_id()");
						  	System.out.println("InsertedID = " + latestID);
					    }
					    
			    		if (status.equals("1")) {
			    			preparedStatement = connect.prepareStatement("UPDATE ch_preferences set status = '0' WHERE id <> " + latestID);
			    			preparedStatement.executeUpdate();
			    		}
			    		// UPDATE clubhub.ch_preferences set status = "0" WHERE id <> 4;
			    		
			    		
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void showPrefs(HttpServletRequest request) throws Exception {
    	Preference pref = new Preference();
    	HttpSession session = request.getSession();
		try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_Preferences WHERE status = 1");
			    while (resultSet.next()) {
			    	pref.setClub_name_long(resultSet.getString("club_name_long"));
			    	pref.setClub_name_short(resultSet.getString("club_name_short"));
				  	session.setAttribute("preference", pref);
				  	request.setAttribute("clubName", pref.getClub_name_long());
				  	System.out.println("clubName = " + request.getAttribute("clubName"));
			    }
		    } catch (SQLException e) {
			      throw e;
			}
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

