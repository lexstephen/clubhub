package utilities;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import model.Post;
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
    		// Colour_Schemeid, tax_rate, country

    		
	        InputStream input_image_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("image_logo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
		            input_image_logo = filePart.getInputStream();
	            }
	        }
	        
	        InputStream input_image_small_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        filePart = request.getPart("image_small_logo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
		            input_image_small_logo = filePart.getInputStream();
	            }
	        } 

		    		preparedStatement = connect.prepareStatement("insert into ch_Preferences "
		    				+ "(`id`, "
		    				+ "`club_name_long`, `club_name_short`, `Colour_Schemeid`, `tax_rate`, "
		    				+ "`country`, `status`, `preference_name`, `image_logo`, `image_small_logo`) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		    		preparedStatement.setString(1, request.getParameter("club_name_long")); // club_name_long
		    		preparedStatement.setString(2, request.getParameter("club_name_short")); // club_name_short 
		    		preparedStatement.setInt(3, Integer.parseInt(request.getParameter("colour_schemeid"))); // Colour_Schemeid
		    		preparedStatement.setString(4, request.getParameter("tax_rate")); // tax_rate
		    		preparedStatement.setString(5, request.getParameter("country")); // country
		    		preparedStatement.setString(6, "0"); // status
		    		preparedStatement.setString(7, request.getParameter("preference_name")); // preference_name
		    		preparedStatement.setBlob(8, input_image_logo); // image_logo
		    		preparedStatement.setBlob(9, input_image_small_logo); // image_small_logo

		    		preparedStatement.executeUpdate(); 

    } catch (Exception e) {
      throw e;
    }
}

	public void showPrefs(HttpServletRequest request) throws Exception {
    	Preference pref = new Preference();
    	HttpSession session = request.getSession();
		try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_preferences WHERE status = 1");
			    while (resultSet.next()) {
			    	pref.setId(resultSet.getString("id"));
			    	pref.setClub_name_long(resultSet.getString("club_name_long"));
			    	pref.setClub_name_short(resultSet.getString("club_name_short"));
			    	pref.setPreference_name(resultSet.getString("preference_name"));
				  	session.setAttribute("preference", pref);
				  	session.setAttribute("prefID", pref.getId());
				  	//session.setAttribute("active_preference_name", resultSet.getString("preference_name"));
				  	request.setAttribute("clubName", pref.getClub_name_long());
				  	
				  	System.out.println("clubName = " + request.getAttribute("clubName") + " and ID is " + pref.getId() + " and pref name is " + resultSet.getString("preference_name"));
			    }
		    } catch (SQLException e) {
			      throw e;
			}
	} 
	public void showAllPrefs(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
		List<Preference> prefs = new ArrayList<Preference>();
		try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_preferences");
			    while (resultSet.next()) {
			    	Preference pref = new Preference();
			    	pref.setId(resultSet.getString("id"));
			    	pref.setPreference_name(resultSet.getString("preference_name"));
			    	pref.setClub_name_long(resultSet.getString("club_name_long"));
			    	pref.setClub_name_short(resultSet.getString("club_name_short"));
			    	pref.setColour_schemeid(Integer.parseInt(resultSet.getString("Colour_Schemeid")));
			    	pref.setTax_rate(Float.parseFloat(resultSet.getString("tax_rate")));
			    	pref.setCountry(resultSet.getString("country"));
			    	pref.setStatus(resultSet.getString("status"));
				  	request.setAttribute("clubName", pref.getClub_name_long());

			  		pref.setImage_logo("true");
				  	prefs.add(pref);
			    }
			  	session.setAttribute("prefs", prefs);
		    } catch (SQLException e) {
			      throw e;
			}
	} 

	public void deletePreference(HttpServletRequest request, HttpServletResponse response, String PreferenceID) throws Exception {
		
	}

	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
	}
	
	public void setPreference(HttpServletRequest request) throws Exception { 
		// this function sets status for all all preferences to 0 except the selected one which becomes 1
		String setPrefID = request.getParameter("inptPrefID");
		preparedStatement = connect.prepareStatement("UPDATE ch_preferences set status = '0' WHERE id <> " + setPrefID);
		preparedStatement.executeUpdate();
		preparedStatement = connect.prepareStatement("UPDATE ch_preferences set status = '1' WHERE id = " + setPrefID);
		preparedStatement.executeUpdate();
		
	} 
	
	public void editPreference(HttpServletRequest request, HttpServletResponse response, String _PreferenceID) throws Exception {
	    try {	// connect to the database
    		statement = connect.createStatement();
    		// id, image_logo, image_small_logo, club_name_long, club_name_short, 
    		// Colour_Schemeid, tax_rate, country
    		boolean isImageLogo = false, isImageSmallLogo = false;
    		
    		String qry = "UPDATE ch_Preferences "
    				+ "SET club_name_long = ?, "
    				+ "club_name_short = ?, Colour_Schemeid = ?, tax_rate = ?, "
    				+ "country = ?, preference_name = ?";
    		
	        InputStream input_image_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("image_logo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
	            	isImageLogo = true;
	            	qry += ", image_logo = ?";
		            input_image_logo = filePart.getInputStream();
	            }
	        }
	        
	        InputStream input_image_small_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        filePart = request.getPart("image_small_logo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
	            	isImageSmallLogo = true;
	            	qry += ", image_small_logo = ?";
		            input_image_small_logo = filePart.getInputStream();
	            }
	        } 

					String prefID = request.getParameter("prefid");

	        				qry += " WHERE id = " + prefID;
		    		preparedStatement = connect.prepareStatement(qry);
		    		preparedStatement.setString(1, request.getParameter("club_name_long")); // club_name_long
		    		preparedStatement.setString(2, request.getParameter("club_name_short")); // club_name_short 
		    		preparedStatement.setInt(3, Integer.parseInt(request.getParameter("colour_schemeid"))); // Colour_Schemeid
		    		preparedStatement.setString(4, request.getParameter("tax_rate")); // tax_rate
		    		preparedStatement.setString(5, request.getParameter("country")); // country
		    		preparedStatement.setString(6, request.getParameter("preference_name")); // preference_name
		    		if(isImageLogo) {
		    			preparedStatement.setBlob(7, input_image_logo);// image_logo
			    		if(isImageSmallLogo) {
			    			preparedStatement.setBlob(8, input_image_small_logo); // image_small_logo
		    			} 
	    			} else {
			    		if(isImageSmallLogo) {
			    			preparedStatement.setBlob(7, input_image_small_logo); // image_small_logo
		    			} 
	    			}
		    		preparedStatement.executeUpdate(); 
		    		
		    		
    } catch (Exception e) {
      throw e;
    }
}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
	
	public void taxRate(HttpServletRequest request) { 
	  	ResultSet taxRate;
		try {
	  		statement = connect.createStatement();
			taxRate = statement.executeQuery("SELECT tax_rate FROM ch_preferences WHERE status = 1");
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

