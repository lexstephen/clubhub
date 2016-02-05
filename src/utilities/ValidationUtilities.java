package utilities;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ValidationUtilities {
	
	// check that email and password match required formatting
	public static boolean isValidLogin(HttpServletRequest request) {
		boolean isValid = true;
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		if (username.equals("admin") && password.equals("admin")) {
			session.setAttribute("isAdmin", true);
		} else {
			session.setAttribute("isAdmin", false);
			if (isMissing(username)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLoginEmail", true);}
			if (!isPwd(password)) {
				isValid = false;
				request.setAttribute("errorString", "Please check your input");
				request.setAttribute("errorLoginPassword", true);} 
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
