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
		try {
			String preference_name = request.getParameter("preference_name");
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
	
			resultSet = statement.executeQuery("select * from ch_Preferences where preference_name = \"" + preference_name + "\""); 
	
			// if there result set is before the first item, there are entries
			// if it is not, there are not
			if (!resultSet.isBeforeFirst() ) {    
				return false;
			} else {
				request.setAttribute("errorPreference_Name", true);
				return true;
			}
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {	// connect to the database
    		statement = connect.createStatement();
    		// id, image_logo, image_small_logo, club_name_long, club_name_short, 
    		// Colour_Schemeid, tax_rate, country
    		
			String province = null;
			switch(request.getParameter("country")) {
			case "Canada":
				province = request.getParameter("province");
				break;
			case "United States of America":
				province = request.getParameter("state");
				break;
			}
			
    		
	        InputStream input_image_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("image_logo");
	        if (filePart != null) {
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
		            input_image_logo = filePart.getInputStream();
	            }
	        }
	        
	        InputStream input_image_small_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        filePart = request.getPart("image_small_logo");
	        if (filePart != null) {
	            // obtains input stream of the upload file
	            if (filePart.getSize() != 0) {
		            input_image_small_logo = filePart.getInputStream();
	            }
	        } 
	        		String qry = "insert into ch_Preferences "
		    				+ "(`id`, `club_name_long`, `club_name_short`, `Colour_Schemeid`, `tax_rate`, "
		    				+ "`telephone`, `address`, `city`, `province`, `postal_code`, `country`, "
		    				+ "`status`, `preference_name`, `contactName`, `emailAddress`, `clubURL`, `image_logo`, `image_small_logo`) "
		    				+ " values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        		System.out.println(qry);
		    		preparedStatement = connect.prepareStatement(qry);
		    		preparedStatement.setString(1, request.getParameter("club_name_long")); // club_name_long
		    		preparedStatement.setString(2, request.getParameter("club_name_short")); // club_name_short 
		    		preparedStatement.setInt(3, Integer.parseInt(request.getParameter("colour_schemeid"))); // Colour_Schemeid
		    		preparedStatement.setString(4, request.getParameter("tax_rate")); // tax_rate
		    		preparedStatement.setString(5, request.getParameter("telephone")); // telephone
		    		preparedStatement.setString(6, request.getParameter("address")); // address
		    		preparedStatement.setString(7, request.getParameter("city")); // city
		    		preparedStatement.setString(8, province); // province
		    		preparedStatement.setString(9, request.getParameter("postalCode")); // postalcode
		    		preparedStatement.setString(10, request.getParameter("country")); // country
		    		preparedStatement.setString(11, "0"); // status
		    		preparedStatement.setString(12, request.getParameter("preference_name")); // preference_name
		    		preparedStatement.setString(13, request.getParameter("contactName")); // preference_name
		    		preparedStatement.setString(14, request.getParameter("emailAddress")); // preference_name
		    		preparedStatement.setString(15, request.getParameter("clubURL")); // preference_name
		    		preparedStatement.setBlob(16, input_image_logo); // image_logo
		    		preparedStatement.setBlob(17, input_image_small_logo); // image_small_logo

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
			    	pref.setAddress(resultSet.getString("address"));
			    	pref.setCity(resultSet.getString("city"));
			    	pref.setProvince(resultSet.getString("province"));
			    	pref.setPostalcode(resultSet.getString("postal_code"));
			    	pref.setCountry(resultSet.getString("country"));
					String number = (resultSet.getString("telephone") == null)?"1234567890":resultSet.getString("telephone");
			    	pref.setTelephone(number);
					pref.setFormatted_telephone(String.format("(%s) %s-%s", number.substring(0, 3), number.substring(3, 6), number.substring(6, 10)));
			    	pref.setTax_rate(Float.parseFloat(resultSet.getString("tax_rate")));
			    	pref.setContactName(resultSet.getString("contactName"));
			    	pref.setEmailAddress(resultSet.getString("emailAddress"));
			    	pref.setClubURL(resultSet.getString("clubURL"));

			    	pref.setPreference_name(resultSet.getString("preference_name"));
				  	session.setAttribute("preference", pref);
				  	session.setAttribute("prefID", pref.getId());
				  	//session.setAttribute("active_preference_name", resultSet.getString("preference_name"));
				  	request.setAttribute("clubName", pref.getClub_name_long());
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
			    	pref.setTelephone(resultSet.getString("telephone"));
			    	pref.setAddress(resultSet.getString("address"));
			    	pref.setCity(resultSet.getString("city"));
			    	pref.setProvince(resultSet.getString("province"));
			    	pref.setPostalcode(resultSet.getString("postal_code"));
			    	pref.setCountry(resultSet.getString("country"));
			    	pref.setTax_rate(Float.parseFloat(resultSet.getString("tax_rate")));
			    	pref.setColour_schemeid(Integer.parseInt(resultSet.getString("Colour_Schemeid")));
			    	pref.setStatus(resultSet.getString("status"));
			    	pref.setContactName(resultSet.getString("contactName"));
			    	pref.setEmailAddress(resultSet.getString("emailAddress"));
			    	pref.setClubURL(resultSet.getString("clubURL"));
				  	request.setAttribute("clubName", pref.getClub_name_long());
				  	if(resultSet.getBlob("image_logo")!=null) {
				  		pref.setImage_logo("true");				  		
				  	} else {
				  		pref.setImage_logo("false");
				  	}
				  	if(resultSet.getBlob("image_small_logo")!=null) {
				  		pref.setImage_small_logo("true");				  		
				  	} else {
				  		pref.setImage_small_logo("false");
				  	}
				  	prefs.add(pref);
			    }
			  	session.setAttribute("prefs", prefs);
		    } catch (SQLException e) {
			      throw e;
			}
	} 

	public void deletePreference(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String delPrefID = request.getParameter("prefID");
		String defaultPrefID = (String) session.getAttribute("prefID");
//		showPrefs(request);
		System.out.println("Preference ID is " + defaultPrefID + " deleting " + delPrefID);
//		Preference delPref = (Preference) request.getAttribute("preference");
	    try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_preferences where id ="  + delPrefID); 
		  } catch (SQLException e) {
		      throw e;
		  }
	    
		if (defaultPrefID.equals(delPrefID)) {
			System.out.println("Matched - now I will find a new number");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_Preferences ORDER BY id ASC LIMIT 1"); 

			// if there result set is before the first item, there are entries
			// if it is not, there are not
			while(resultSet.next()) {
		    	String firstPref = resultSet.getString("id");
		    	System.out.println("The ID is " + firstPref);
				preparedStatement = connect.prepareStatement("UPDATE ch_preferences set status = '1' WHERE id = " + firstPref);
				preparedStatement.executeUpdate();
			} 
		}
	}

	
	public void setPreference(HttpServletRequest request) throws Exception { 
		// this function sets status for all all preferences to 0 except the selected one which becomes 1
		String setPrefID = (String) ((request.getParameter("inptPrefID") != null)?request.getParameter("inptPrefID"):request.getAttribute("inptPrefID"));
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

			String province = null;
			switch(request.getParameter("country")) {
			case "Canada":
				province = request.getParameter("province");
				break;
			case "United States of America":
				province = request.getParameter("state");
				break;
			}
    		String qry = "UPDATE ch_Preferences "
    				+ "SET  preference_name = ?, club_name_long = ?, "
    				+ "club_name_short = ?, Colour_Schemeid = ?, tax_rate = ?, "
    				+ "telephone = ?, address = ?, city = ?, province = ?, postal_code = ?, country = ?, contactName = ?, emailAddress = ?, clubURL = ?";
    		
	        InputStream input_image_logo = null; // input stream of the upload file 
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("image_logo");
	        if (filePart != null) {
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
		    		preparedStatement.setString(1, request.getParameter("preference_name")); // preference_name
		    		preparedStatement.setString(2, request.getParameter("club_name_long")); // club_name_long
		    		preparedStatement.setString(3, request.getParameter("club_name_short")); // club_name_short 
		    		preparedStatement.setInt(4, Integer.parseInt(request.getParameter("colour_schemeid"))); // Colour_Schemeid
		    		preparedStatement.setString(5, request.getParameter("tax_rate")); // tax_rate
		    		preparedStatement.setString(6, request.getParameter("telephone")); // telephone
		    		preparedStatement.setString(7, request.getParameter("address")); // address
		    		preparedStatement.setString(8, request.getParameter("city")); // city
		    		preparedStatement.setString(9, province); // province
		    		preparedStatement.setString(10, request.getParameter("postalCode")); // postal_code
		    		preparedStatement.setString(11, request.getParameter("country")); // country
		    		preparedStatement.setString(12, request.getParameter("contactName")); // country
		    		preparedStatement.setString(13, request.getParameter("emailAddress")); // country
		    		preparedStatement.setString(14, request.getParameter("clubURL")); // country
		    		if(isImageLogo) {
		    			preparedStatement.setBlob(15, input_image_logo);// image_logo
			    		if(isImageSmallLogo) {
			    			preparedStatement.setBlob(16, input_image_small_logo); // image_small_logo
		    			} 
	    			} else if(isImageSmallLogo) {
			    			preparedStatement.setBlob(17, input_image_small_logo); // image_small_logo
	    			} 
		    		preparedStatement.executeUpdate(); 
		    		
		    		
    } catch (Exception e) {
      throw e;
    }
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

