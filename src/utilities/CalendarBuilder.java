package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarBuilder { 

	   /***************************************************************************
	    *  Given the month (M), day (D), and year (Y), return which day
	    *  of the week it falls on according to the Gregorian calendar.
	    *  For M use 1 for January, 2 for February, and so forth. Outputs
	    *  0 for Sunday, 1 for Monday, and so forth.
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

	    public CalendarBuilder() throws Exception {
	    	
	    	todaysDate();
	    	int M = monthAndYear[0]; // current month #
	    	int Y = monthAndYear[1]; // current year
	    	int currentDay = monthAndYear[2]; // current day #

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
	        output += months[M] + " " + Y + "</th></tr>";
	        output += "<tr><th class=\"text-center\">S</th><th>M</th><th>Tu</th><th>W</th><th>Th</th><th>F</th><th>S</th></tr><tr>";

	        // starting day
	        int d = day(M, 1, Y);

	        // print the calendar
	        for (int i = 0; i < d; i++)
	            output += "<td></td>";
	        for (int i = 1; i <= days[M]; i++) {
	        	if (i == currentDay) {
	        		output += "<td><strong>" + String.format("%2d ", i) + "</strong></td>";
	        	} else {
	            output += "<td>" + String.format("%2d ", i) + "</td>";
	        	}
	            if (((i + d) % 7 == 0) || (i == days[M])) output += "</tr><tr>";
	        }
	        
	        output += "</tr></tbody>";

	        System.out.println("output: " + output);
	        
	    }

		@Override
		public String toString() {
			return output;
		}
	}