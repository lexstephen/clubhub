package utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

/****************************************************************************************************
* Project: Hackers 1995
* Assignment: COMP 3095 Assignment 2
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: December 4th, 2016
* Description: ValidationUtilities - checks user input for validity
****************************************************************************************************/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.User;

public class ValidationUtilities {
	
	// check that email and password match required formatting
	public static boolean isValidLogin(HttpServletRequest request) {
		System.out.println("in isValidLogin");
		boolean isValid = true;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.setAttribute("username", username);		
		request.setAttribute("password", password);
		if (isMissing(username)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorLoginEmail", true);}
		if (!isPwd(password)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorLoginPassword", true);} 
		return isValid;
	}

	public static boolean isValidPreference(HttpServletRequest request) {
		boolean isValid = true;
		request.setAttribute("preferenceID", request.getParameter("prefid"));
		request.setAttribute("preference_name", request.getParameter("preference_name"));
		request.setAttribute("club_name_long", request.getParameter("club_name_long"));
		request.setAttribute("club_name_short", request.getParameter("club_name_short"));
		request.setAttribute("country", request.getParameter("country"));
		request.setAttribute("tax_rate", request.getParameter("tax_rate"));
		request.setAttribute("csid", request.getParameter("csid"));

		if (isMissing(request.getParameter("preference_name"))) {
			isValid = false;
			request.setAttribute("errorPreference_Name", true);
		}

		if (isMissing(request.getParameter("club_name_long"))) {
			isValid = false;
			request.setAttribute("errorClub_Name_Long", true);
		}

		if (isMissing(request.getParameter("club_name_short"))) {
			isValid = false;
			request.setAttribute("errorClub_Name_Short", true);
		}

		if (isMissing(request.getParameter("tax_rate")) || !isDouble(request.getParameter("tax_rate"))) {
			isValid = false;
			request.setAttribute("errorTax_Rate", true);
		}
			
		return isValid;
	}
	
	public static boolean isValidColourScheme(HttpServletRequest request) {
		boolean isValid = true;
		request.setAttribute("csname", request.getParameter("name"));
		request.setAttribute("dark_colour", request.getParameter("dark_colour"));
		request.setAttribute("med_colour", request.getParameter("med_colour"));
		request.setAttribute("light_colour", request.getParameter("light_colour"));
		request.setAttribute("text_colour", request.getParameter("text_colour"));
		request.setAttribute("csid", request.getParameter("csid"));
		
		if (isMissing(request.getParameter("name")) || (request.getParameter("name").equals("--Add New--"))) {
			isValid = false;
			request.setAttribute("errorCSName", true);
		} 
		if ( request.getParameter("dark_colour").equals(request.getParameter("med_colour"))
				&& request.getParameter("dark_colour").equals(request.getParameter("light_colour"))
				&& request.getParameter("dark_colour").equals(request.getParameter("text_colour"))	
			) {
			// they entered the same colour four times.. bad idea
			isValid = false;
			request.setAttribute("errorCSName", true);
			request.setAttribute("errorCSColour", true);
		}
		
		if (!isRightLength(request.getParameter("dark_colour"),7) || 
				!isRightLength(request.getParameter("med_colour"),7) || 
				!isRightLength(request.getParameter("light_colour"),7) || 
				!isRightLength(request.getParameter("text_colour"),7)) {
			isValid = false;
			request.setAttribute("errorCSName", true);
			request.setAttribute("errorCSColour", true);
		}
			
		return isValid;
	}
	
	public static boolean isValidInvoice(HttpServletRequest request) {


		boolean isValid = true, isValidQty = true;
		String invDate = request.getParameter("invDate");
		String userID = request.getParameter("userID");
		String charge01 = request.getParameter("charge01");
		String charge01qty = request.getParameter("charge01qty");
		String charge02 = request.getParameter("charge02");
		String charge02qty = request.getParameter("charge02qty");
		String charge03 = request.getParameter("charge03");
		String charge03qty = request.getParameter("charge03qty");
		String charge04 = request.getParameter("charge04");
		String charge04qty = request.getParameter("charge04qty");
		String charge05 = request.getParameter("charge05");
		String charge05qty = request.getParameter("charge05qty");
		
		
		try {

			System.out.println("Charge01 is " + charge01 + " and quantity is " + charge01qty);
			System.out.println("Charge02 is " + charge02 + " and quantity is " + charge02qty);
			System.out.println("Charge03 is " + charge03 + " and quantity is " + charge03qty);
			System.out.println("Charge04 is " + charge04 + " and quantity is " + charge04qty);
			System.out.println("Charge05 is " + charge05 + " and quantity is " + charge05qty);
			
			if ((!charge01.equals("---")) || 
				(!charge02.equals("---")) || 
				(!charge03.equals("---")) || 
				(!charge04.equals("---")) || 
				(!charge05.equals("---")) ) {
			// at least one of the dropdowns has a selection so lets check their respective quantities 
				if (!charge01.equals("---")) {
					if (!charge01qty.equals("0")) 
						isValidQty = true;
					else {
						request.setAttribute("errorLineItems", "Please check quantities selected.");
						request.setAttribute("errorCharge01Qty", true);
						isValidQty = false;
					}
				}	
				if (!charge02.equals("---")) {
					if (!charge02qty.equals("0"))
						isValidQty = true;
					else {
						request.setAttribute("errorLineItems", "Please check quantities selected.");
						request.setAttribute("errorCharge02Qty", true);
						isValidQty = false;
					}
				}	
				if (!charge03.equals("---")) {
					if (!charge03qty.equals("0"))
						isValidQty = true;
					else {
						request.setAttribute("errorLineItems", "Please check quantities selected.");
						request.setAttribute("errorCharge03Qty", true);
						isValidQty = false;
					}
				}	
				if (!charge04.equals("---")) {
					if (!charge04qty.equals("0"))
						isValidQty = true;
					else {
						request.setAttribute("errorLineItems", "Please check quantities selected.");
						request.setAttribute("errorCharge04Qty", true);
						isValidQty = false;
					}
				}	
				if (!charge05.equals("---")) {
					if (!charge05qty.equals("0"))
						isValidQty = true;
					else {
						request.setAttribute("errorLineItems", "Please check quantities selected.");
						request.setAttribute("errorCharge05Qty", true);
						isValidQty = false;
					}
				}	
			} else {
				// none of the dropdowns had a selection, your invoice is invalid
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItems", "No line items selected");
				isValidQty = false;
			}
				
			
			// check date format to ensure it is entered yyyy-mm-dd
			String dateFormat = "yyyy-mm-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(invDate);
			System.out.println(date);
			isValid = true;
			return (isValid && isValidQty);
		} catch (Exception e) {
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorInvDate", "Date format must be yyyy-mm-dd");
			return false;
		}
	}

	public static String toTime (HttpServletRequest request, int givenTime){
		String time = null;
		
		String input = Integer.toString(givenTime);
		time = input.replaceAll("..(?!$)", "$0:");
		
		System.out.println("Game time is: " + time);
		
		return time;
	}
		
	public static String numberToDay (HttpServletRequest request, int num){
		String dayOfWeek = null;
		if (num == 1){
			 dayOfWeek = "Sunday";
		}else if (num == 2){
			dayOfWeek = "Monday";
		}else if (num == 3){
			dayOfWeek = "Tuesday";
		}else if (num == 4){
			dayOfWeek = "Wednesday";
		}else if (num == 5){
			dayOfWeek = "Thursday";
		}else if (num == 6){
			dayOfWeek = "Friday";
		}else if (num == 7){
			dayOfWeek = "Saturday";
		}
		
		System.out.println("The day of week is: " +dayOfWeek);
		return dayOfWeek;
		
	}
	
	public static int monthOfDate (String scheduledDate) throws Exception{
		// Get month from date //	
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(scheduledDate);		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);					
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static String getPlayerNames (HttpServletRequest request, String playerIDs) throws Exception{
		
		//String playerNames = null;
		Connection connect = null;
		Statement statement = null;
		//PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String players = null;
		
		String [] playerID = playerIDs.split(",");
		  
		  
		for (int i = 0; i < playerID.length; i++) { 
				String player = playerID[i];
			try{	
				connect = DatabaseAccess.connectDataBase();
				statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_user where id= "+ player);
			    
			    while(resultSet.next()){
			    	String playerName = resultSet.getString("firstName");
			    	
			    	if (players == null){
			    		players = playerName;
			    	
			    	}else{
			    		players += ", " + playerName;
			    	}
			    }
			    
	    		
			}catch (SQLException e) {
			      throw e;
			}
		}
		return players;
	}
		
	public static boolean isValidLineItem(HttpServletRequest request) {
		boolean isValid = false;
		String description = "", cost = "";	

		request.setAttribute("lineItem01Description", request.getParameter("lineItem01Description"));
		request.setAttribute("lineItem01Cost", isInt(request.getParameter("lineItem01Cost"))?request.getParameter("lineItem01Cost"):0);

		request.setAttribute("lineItem02Description", request.getParameter("lineItem02Description"));
		request.setAttribute("lineItem02Cost", isInt(request.getParameter("lineItem02Cost"))?request.getParameter("lineItem02Cost"):0);

		request.setAttribute("lineItem03Description", request.getParameter("lineItem03Description"));
		request.setAttribute("lineItem03Cost", isInt(request.getParameter("lineItem03Cost"))?request.getParameter("lineItem03Cost"):0);

		request.setAttribute("lineItem04Description", request.getParameter("lineItem04Description"));
		request.setAttribute("lineItem04Cost", isInt(request.getParameter("lineItem04Cost"))?request.getParameter("lineItem04Cost"):0);

		request.setAttribute("lineItem05Description", request.getParameter("lineItem05Description"));
		request.setAttribute("lineItem05Cost", isInt(request.getParameter("lineItem05Cost"))?request.getParameter("lineItem05Cost"):0);
		
		if (isInt(request.getParameter("lineItem01Cost"))) {
			if (!request.getParameter("lineItem01Cost").equals("0")) {
				if (!isMissing(request.getParameter("lineItem01Description"))) {
					// a description was entered and the cost is a number higher than 0
					isValid = true;
				} else {
					// the cost is a number higher than 0 but no description was entered
					request.setAttribute("errorString", "Please check your input");
					request.setAttribute("errorLineItem01", true);
				}
			} else {
				if (!isMissing(request.getParameter("lineItem01Description"))) {
					request.setAttribute("errorString", "Please enter a valid cost.");
					request.setAttribute("errorLineItem01", true);
				}
			}
		} else {
			request.setAttribute("errorLineItem01", true);
		}

		if (isInt(request.getParameter("lineItem02Cost"))) {
			if (!request.getParameter("lineItem02Cost").equals("0")) {
				if (!isMissing(request.getParameter("lineItem02Description"))) {
					// a description was entered and the cost is a number higher than 0
					isValid = true;
				} else {
					// the cost is a number higher than 0 but no description was entered
					request.setAttribute("errorString", "Please check your input");
					request.setAttribute("errorLineItem02", true);
				}
			} else {
				if (!isMissing(request.getParameter("lineItem02Description"))) {
					request.setAttribute("errorString", "Please enter a valid cost.");
					request.setAttribute("errorLineItem02", true);
				}
			}
		} else {
			request.setAttribute("errorLineItem02", true);
		}

		if (isInt(request.getParameter("lineItem03Cost"))) {
			if (!request.getParameter("lineItem03Cost").equals("0")) {
				if (!isMissing(request.getParameter("lineItem03Description"))) {
					// a description was entered and the cost is a number higher than 0
					isValid = true;
				} else {
					// the cost is a number higher than 0 but no description was entered
					request.setAttribute("errorString", "Please check your input");
					request.setAttribute("errorLineItem03", true);
				}
			} else {
				if (!isMissing(request.getParameter("lineItem03Description"))) {
					request.setAttribute("errorString", "Please enter a valid cost.");
					request.setAttribute("errorLineItem03", true);
				}
			}
		} else {
			request.setAttribute("errorLineItem03", true);
		}

		if (isInt(request.getParameter("lineItem04Cost"))) {
			if (!request.getParameter("lineItem04Cost").equals("0")) {
				if (!isMissing(request.getParameter("lineItem04Description"))) {
					// a description was entered and the cost is a number higher than 0
					isValid = true;
				} else {
					// the cost is a number higher than 0 but no description was entered
					request.setAttribute("errorString", "Please check your input");
					request.setAttribute("errorLineItem04", true);
				}
			} else {
				if (!isMissing(request.getParameter("lineItem04Description"))) {
					request.setAttribute("errorString", "Please enter a valid cost.");
					request.setAttribute("errorLineItem04", true);
				}
			}
		} else {
			request.setAttribute("errorLineItem04", true);
		}

		if (isInt(request.getParameter("lineItem05Cost"))) {
			if (!request.getParameter("lineItem05Cost").equals("0")) {
				if (!isMissing(request.getParameter("lineItem05Description"))) {
					// a description was entered and the cost is a number higher than 0
					isValid = true;
				} else {
					// the cost is a number higher than 0 but no description was entered
					request.setAttribute("errorString", "Please check your input");
					request.setAttribute("errorLineItem05", true);
				}
			} else {
				if (!isMissing(request.getParameter("lineItem05Description"))) {
					request.setAttribute("errorString", "Please enter a valid cost.");
					request.setAttribute("errorLineItem05", true);
				}
			}
		} else {
			request.setAttribute("errorLineItem05", true);
		}

		return isValid;
	}
	
	public static boolean isValidRegistration(HttpServletRequest request) {
		boolean isValid = true;
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");	
		String password2 = request.getParameter("password2");	
		String emailAddress = request.getParameter("emailAddress");	
		String emailAddress2 = request.getParameter("emailAddress2");	
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		String streetAddress = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postalCode");
		String country = request.getParameter("country");
		String profilePhoto = request.getParameter("profilePhoto");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String emergencyContactName = request.getParameter("emergencyContactName");
		String emergencyContactPhoneNumber = request.getParameter("emergencyContactPhoneNumber");

		String province = null;
		int postalCodeLength = 0;
		switch(country) {
		case "Canada":
			postalCodeLength = 6;
			province = request.getParameter("province");
			break;
		case "United States of America":
			postalCodeLength = 5;
			province = request.getParameter("state");
			break;
		}
		
		request.setAttribute("username", username);
		request.setAttribute("password1", password1);
		request.setAttribute("password2", password2);
		request.setAttribute("emailAddress", emailAddress);
		request.setAttribute("emailAddress2", emailAddress2);
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("gender", gender);
		request.setAttribute("telephone", telephone);
		request.setAttribute("streetAddress", streetAddress);
		request.setAttribute("city", city);
		request.setAttribute("province", province);
		request.setAttribute("postalCode", postalCode);
		request.setAttribute("country", country);
		request.setAttribute("profilePhoto", profilePhoto);
		request.setAttribute("dateOfBirth", dateOfBirth);
		request.setAttribute("emergencyContactName", emergencyContactName);
		request.setAttribute("emergencyContactPhoneNumber", emergencyContactPhoneNumber);


		if (isMissing(username)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorUsername", true);}
		
		if (!isPwd(password1)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPassword1", true);}
		if (!isPwd(password2)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPassword2", true);}
		if (!password1.equals(password2)) {
			isValid = false;
			request.setAttribute("errorString4", "Passwords do not match");
			request.setAttribute("errorPassword1", true);
			request.setAttribute("errorPassword2", true);}
		
		if (!isEmail(emailAddress)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmail1", true);}
		if (!isEmail(emailAddress2)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmail2", true);} 
		if (!emailAddress.equals(emailAddress2)) {
			isValid = false;
			request.setAttribute("errorString3", "Email addresses do not match");
			request.setAttribute("errorEmail1", true);
			request.setAttribute("errorEmail2", true);}
		
		if (!isString(firstName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorFirstName", true);}
		if (!isString(lastName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorLastName", true);} 

		if (isMissing(gender)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorGender", true);} 


		if (isMissing(telephone)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorTelephone", true);} 
		else {
			if (!isInt(telephone)) {
			isValid = false;
			request.setAttribute("errorString2", "Please enter only numbers in your telephone number");
			request.setAttribute("errorTelephone", true);}
		}

		if (isMissing(streetAddress)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorStreetAddress", true);}
		if (isMissing(city)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorCity", true);}
		if (isMissing(province)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorProvince", true);}
		if (!isRightLength(postalCode,postalCodeLength)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPostalCode", true);}
		if (isMissing(country)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorCountry", true);}
		if (isMissing(dateOfBirth)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorDateOfBirth", true);}
		if (isMissing(emergencyContactName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmergencyContactName", true);}
		if (!isInt(emergencyContactPhoneNumber)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmergencyContactPhoneNumber", true);}
		return isValid;
	}
	
	public static boolean isValidUser(HttpServletRequest request) {
		boolean isValid = true;
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");	
		String password2 = request.getParameter("password2");	
		String emailAddress = request.getParameter("emailAddress");	
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		String streetAddress = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postalCode = request.getParameter("postalCode");
		postalCode = postalCode.replaceAll("\\s+","");
		System.out.println("PC is " + postalCode);
		String country = request.getParameter("country");
		String profilePhoto = request.getParameter("profilePhoto");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String emergencyContactName = request.getParameter("emergencyContactName");
		String emergencyContactPhoneNumber = request.getParameter("emergencyContactPhoneNumber");

		request.setAttribute("username", username);
		request.setAttribute("password1", password1);
		request.setAttribute("password2", password2);
		request.setAttribute("emailAddress", emailAddress);
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("gender", gender);
		request.setAttribute("telephone", telephone);
		request.setAttribute("streetAddress", streetAddress);
		request.setAttribute("city", city);
		request.setAttribute("province", province);
		request.setAttribute("postalCode", postalCode);
		request.setAttribute("country", country);
		request.setAttribute("profilePhoto", profilePhoto);
		request.setAttribute("dateOfBirth", dateOfBirth);
		request.setAttribute("emergencyContactName", emergencyContactName);
		request.setAttribute("emergencyContactPhoneNumber", emergencyContactPhoneNumber);


		if (isMissing(username)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorUsername", true);}
		
		if (!isPwd(password1)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPassword1", true);}
		if (!isPwd(password2)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPassword2", true);}
		if (!password1.equals(password2)) {
			isValid = false;
			request.setAttribute("errorString4", "Passwords do not match");
			request.setAttribute("errorPassword1", true);
			request.setAttribute("errorPassword2", true);}
		
		if (!isEmail(emailAddress)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmail1", true);}
		
		if (!isString(firstName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorFirstName", true);}
		if (!isString(lastName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorLastName", true);} 

		if (isMissing(gender)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorGender", true);} 

		if (!isInt(telephone)) {
			isValid = false;
			request.setAttribute("errorString2", "Please enter only numbers in your telephone number");
			request.setAttribute("errorTelephone", true);}

		if (isMissing(streetAddress)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorStreetAddress", true);}
		if (isMissing(city)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorCity", true);}
		if (isMissing(province)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorProvince", true);}
		int postalCodeLength = 0;
		if (country.equals("Canada")) {
			postalCodeLength = 6;
		} else {
			postalCodeLength = 5;
		}
		if (!isRightLength(postalCode,postalCodeLength)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorPostalCode", true);}
		
		if (isMissing(country)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorCountry", true);}
		if (isMissing(dateOfBirth)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorDateOfBirth", true);}
		if (isMissing(emergencyContactName)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmergencyContactName", true);}
		if (!isInt(emergencyContactPhoneNumber)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorEmergencyContactPhoneNumber", true);}
		return isValid;
	}
	
	public static boolean isValidPost(HttpServletRequest request) {
		boolean isValid = true;
		String title = request.getParameter("blogTitle");
		String content = request.getParameter("blogContent");
		request.setAttribute("blogTitle", title);
		request.setAttribute("blogContent", content);
		if (isMissing(title)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorNewPostTitle", true);}
		if (isMissing(content)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorNewPostContent", true);} 
		return isValid;
	}

	
	public static boolean isValidSeason(HttpServletRequest request) {
		boolean isValid = true;
		//year, season, gender, StartDate, StartTime, DayOfWeek, Duration
		String year = request.getParameter("year");
		String season = request.getParameter("season");
		String gender = request.getParameter("gender");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String dayOfWeek = request.getParameter("dayOfWeek");
		String duration = request.getParameter("duration");
		request.setAttribute("year", year);
		request.setAttribute("season", season);
		request.setAttribute("gender", gender);
		request.setAttribute("startDate", startDate);
		request.setAttribute("startTime", startTime);
		request.setAttribute("dayOfWeek", dayOfWeek);
		request.setAttribute("duration", duration);
		
		if (isMissing(year)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(season)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(gender)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(startDate)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(startTime)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(dayOfWeek)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		if (isMissing(duration)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");}
		return isValid;
	}
	
	
	public static boolean isValidComment(HttpServletRequest request) {
		boolean isValid = true;
		String content = request.getParameter("content");
		request.setAttribute("content", content);
		if (isMissing(content)) {
			isValid = false;
			request.setAttribute("errorString", "Please check your input");
			request.setAttribute("errorNewPostContent", true);} 
		return isValid;
	}

	// check that an input field was filled out
	public static boolean isMissing(String theInput) {
		return theInput.matches("");
	}
	
	// check that an input field is the correct length
	public static boolean isRightLength(String theInput, int fieldSize ) {
		return theInput.length() == fieldSize;
	}

	// check that value is a String
	public static boolean isString(String theInput) {
		String alpha = "^[a-zA-Z]*$";
		return ((!isMissing(theInput)) && (theInput.matches(alpha)));
	}

	// check that value matches traditional email addresses
	public static boolean isEmail(String theInput) {
		String alpha = "^[a-zA-Z0-9\\+]+(\\.[_A-Za-s0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";
		return ((!isMissing(theInput)) && (theInput.matches(alpha)));
	}

	// check that value is a number
	public static boolean isInt(String theInput) {
		String num = "^[0-9]*$";
		return ((!isMissing(theInput)) && (theInput.matches(num)));
	}

	// check that value contains at least one letter and one number
	public static boolean isPwd(String theInput) {
		String alpha = "^.*(?=.*[0-9])(?=.*[a-zA-z]).*$";
		return ((!isMissing(theInput)) && (theInput.matches(alpha)));
	}
	
	public static boolean isDouble(String theInput) {
        try {
            Double.parseDouble(theInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	// check that value is correct length range
	public static boolean isRightLength(String theInput, int startLength, int endLength) {
		return ((theInput.length() >= startLength) && (theInput.length() <= endLength));
	}
}