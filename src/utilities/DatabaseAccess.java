package utilities;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: DatabaseAccess - Provides access to database
****************************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseAccess {
	  private static Connection connect = null;
	  
	  public static Connection connectDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/clubhub?"
		              + "user=root&password=admin");
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
}
