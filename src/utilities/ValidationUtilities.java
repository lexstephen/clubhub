package utilities;
import java.text.SimpleDateFormat;
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

	public static boolean isValidLineItem(HttpServletRequest request) {
		boolean isValid = true;
		String description, cost;	
		
		if (Double.parseDouble(request.getParameter("lineItem01Cost")) != 0) {
			description = request.getParameter("lineItem01Description");
			cost = request.getParameter("lineItem01Cost");	
			request.setAttribute("lineItem01Description", description);
			request.setAttribute("lineItem01Cost", cost);
			if (isMissing(description)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem01", true);}
			if (!isInt(cost)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem01", true);}
		}
		if (Double.parseDouble(request.getParameter("lineItem02Cost")) != 0) {
			description = request.getParameter("lineItem02Description");
			cost = request.getParameter("lineItem02Cost");	
			request.setAttribute("lineItem02Description", description);
			request.setAttribute("lineItem02Cost", cost);
			if (isMissing(description)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem02", true);}
			if (!isInt(cost)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem02", true);}
		}
		if (Double.parseDouble(request.getParameter("lineItem03Cost")) != 0) {
			description = request.getParameter("lineItem03Description");
			cost = request.getParameter("lineItem03Cost");	
			request.setAttribute("lineItem03Description", description);
			request.setAttribute("lineItem03Cost", cost);
			if (isMissing(description)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem03", true);}
			if (!isInt(cost)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem03", true);}
		}
		if (Double.parseDouble(request.getParameter("lineItem04Cost")) != 0) {
			description = request.getParameter("lineItem04Description");
			cost = request.getParameter("lineItem04Cost");	
			request.setAttribute("lineItem04Description", description);
			request.setAttribute("lineItem04Cost", cost);
			if (isMissing(description)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem04", true);}
			if (!isInt(cost)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem04", true);}
		}
		if (Double.parseDouble(request.getParameter("lineItem05Cost")) != 0) {
			description = request.getParameter("lineItem05Description");
			cost = request.getParameter("lineItem05Cost");
			request.setAttribute("lineItem05Description", description);
			request.setAttribute("lineItem05Cost", cost);
			if (isMissing(description)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem05", true);}
			if (!isInt(cost)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLineItem05", true);}
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
		if (!isRightLength(postalCode,6)) {
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

	// check that value is correct length range
	public static boolean isRightLength(String theInput, int startLength, int endLength) {
		return ((theInput.length() >= startLength) && (theInput.length() <= endLength));
	}
}
