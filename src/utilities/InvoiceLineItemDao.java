package utilities;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: InvoiceDao - prepares a database access object for the Invoice model
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

import model.Invoice;
import model.User;
import model.InvoiceLineItem;
import utilities.DatabaseAccess;

public class InvoiceLineItemDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public InvoiceLineItemDao() {
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
	    try {	    	
			HttpSession session = request.getSession();

			  statement = connect.createStatement();
			  
			// first let's find the tax rate and store it in a variable
			  ResultSet taxRate = statement.executeQuery("SELECT * FROM ch_preferences");
				
			  int tax_rate = 0;
			    while (taxRate.next()) {
			    	tax_rate = Integer.parseInt(taxRate.getString("tax_rate"));
			    }
			    
			  // INSERT INTO `clubhub`.`ch_invoice_line_items` (`description`, `cost`, `tax`) VALUES ('Performance Hand Screws', '6', '0');

			    if (request.getParameter("lineItem01Description") != null && request.getParameter("lineItem01Cost") != null && ValidationUtilities.isInt(request.getParameter("lineItem01Cost")))	{

					  int cost = Integer.parseInt(request.getParameter("lineItem01Cost"));
					  int tax = cost * tax_rate;
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items values (default, ?, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  preparedStatement.setString(1, request.getParameter("lineItem01Description")); // description
					  preparedStatement.setString(2, request.getParameter("lineItem01Cost")); // cost
					  preparedStatement.setString(3, String.valueOf(tax)); // tax
					  preparedStatement.executeUpdate();

			    }
			    
	    } catch (Exception e) {
	      throw e;
	    }
	}



	public void listAllUsers(HttpServletRequest request) throws Exception {
		  List<User> users = new ArrayList<User>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT id, firstName, lastName from ch_user ORDER BY lastName");
			    while (resultSet.next()) {
			    	  User user = new User();
			    	  user.setUserid(resultSet.getString("id"));
			    	  user.setFirstName(resultSet.getString("firstName"));
			    	  user.setLastName(resultSet.getString("lastName"));
			    	  users.add(user);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("users", users);
	} 
	

	public void listAllLineItems(HttpServletRequest request) throws Exception {
		  List<InvoiceLineItem> lineitems = new ArrayList<InvoiceLineItem>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT * from ch_invoice_line_items");
			    while (resultSet.next()) {
			    	InvoiceLineItem lineitem = new InvoiceLineItem();
			    	  lineitem.setId(resultSet.getString("id"));
			    	  lineitem.setDescription(resultSet.getString("description"));
			    	  lineitem.setCost(resultSet.getString("cost"));
			    	  lineitem.setTax(resultSet.getString("tax"));
			    	  lineitems.add(lineitem);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("lineitems", lineitems);
	} 

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
	}
	public void deleteInvoice(HttpServletRequest request, HttpServletResponse response, String invoiceID) throws Exception {
		
	}

	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
	}
	
	public void findInvoice(HttpServletRequest request, String invoiceID) throws Exception { 
		
	} 
	
	public void editInvoice(HttpServletRequest request, HttpServletResponse response, String _invoiceID) throws Exception {
		
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
}

