/* 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: March 29 , 2016
	Description: Builds sidebar calendar as HTML output
 */
 

package utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CalendarBuilder { 

	   /***************************************************************************
	    *  Given the month (M), day (D), and year (Y), return which day
	    *  of the week it falls on according to the Gregorian calendar.
	    *  For M use 1 for January, 2 for February, and so forth. Outputs
	    *  0 for Sunday, 1 for Monday, and so forth.
	    *  
	    *  Some code used from http://introcs.cs.princeton.edu/java/21function/Calendar.java.html
	    ***************************************************************************/
	
		public String output = "<thead><tr><th colspan=\"7\" class=\"text-center\">";	
		public static int[] monthAndYear = new int[3];
		
		
		public static void todaysDate() throws Exception {			
	    	
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());			
			monthAndYear[0] = ValidationUtilities.monthOfDate(date);
			monthAndYear[1] = ValidationUtilities.yearOfDate(date);
			monthAndYear[2] = ValidationUtilities.numberOfDate(date);
		}
	
	    public static int day(int M, int D, int Y) {
	        int y = Y - (14 - M) / 12;
	        int x = y + y/4 - y/100 + y/400;
	        int m = M + 12 * ((14 - M) / 12) - 2;
	        int d = (D + x + (31*m)/12) % 7;
	        return d;
	    }

	    // return true if the given year is a leap year
	    public static boolean isLeapYear(int year) {
	        if  ((year % 4 == 0) && (year % 100 != 0)) return true;
	        if  (year % 400 == 0) return true;
	        return false;
	    }

	    public CalendarBuilder(HttpServletRequest request, int m, int y) throws Exception {
	    	
	    	HttpSession session = request.getSession();
	    	int M, Y, currentDay;
			String calendarDate;
	    	todaysDate();
	    	
	    	if (m == 0 || y == 0){
		    	M = monthAndYear[0]; // current month #
		    	Y = monthAndYear[1]; // current year
		    	currentDay = monthAndYear[2]; // current day #
	    	} else {
	    		M = m;
	    		Y = y;
	    		if (m == monthAndYear[0] && y == monthAndYear[1]) {
	    			currentDay = monthAndYear[2]; }
	    		else { currentDay = 0; }
	    	}	    	

	        // months[i] = name of month i
	        String[] months = {
	            "",                               // leave empty so that months[1] = "January"
	            "January", "February", "March",
	            "April", "May", "June",
	            "July", "August", "September",
	            "October", "November", "December"
	        };

	        // days[i] = number of days in month i
	        int[] days = {
	            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	        };

	        // check for leap year
	        if (M == 2 && isLeapYear(Y)) days[M] = 29;


	        // print calendar header
	        output += "<a href=\"CalendarController?option=prev\">&lt</a> " + months[M] + " " + Y 
	        		+ " <a href=\"CalendarController?option=next\">&gt</a></th></tr>";
	        output += "<tr><th class=\"text-center\">S</th><th>M</th><th>Tu</th><th>W</th><th>Th</th><th>F</th><th>S</th></tr><tr>";

	        // starting day
	        int d = day(M, 1, Y);

	        // print the calendar
	        for (int i = 0; i < d; i++)
	            output += "<td></td>";
	        for (int i = 1; i <= days[M]; i++) {
	        	
	        	calendarDate = Y + "-" + String.format("%02d", M) + "-" + String.format("%02d", i);
	        	System.out.println("calendarDate = " + calendarDate);
	        	
	        	if (i == currentDay) {
	        		output += "<td><strong>" + String.format("%2d ", i) + "</strong></td>";
	        	} else {
	            output += "<td>" + String.format("%2d ", i) + "</td>";
	        	}
	            if (((i + d) % 7 == 0) || (i == days[M])) output += "</tr><tr>";
	        }
	        
	        //<a href="#" data-toggle="popover" title="Popover Header" data-content="Some content inside the popover">Toggle popover</a>
	        
	        output += "</tr></tbody>";	 
	        
	        session.setAttribute("selectedMonth", M);
	        session.setAttribute("selectedYear", Y);
	    }

		@Override
		public String toString() {
			return output;
		}
	}