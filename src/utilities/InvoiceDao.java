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
import model.Post;
import utilities.DatabaseAccess;

public class InvoiceDao {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public InvoiceDao() {
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
			  preparedStatement = connect.prepareStatement("insert into ch_invoice values (default, ?, ?, ?)");
			  // columns are id, invDate, status, Userid
			  preparedStatement.setString(1, request.getParameter("invDate")); // invDate
			  preparedStatement.setString(2, request.getParameter("status")); // status
			  preparedStatement.setString(3, request.getParameter("userID")); // Userid

			  // prevent invoice from being created if all quantities are 0
			  if (	!(request.getParameter("charge01qty").equals("0")) || 
					  !(request.getParameter("charge02qty").equals("0")) || 
					  !(request.getParameter("charge03qty").equals("0")) || 
					  !(request.getParameter("charge04qty").equals("0")) || 
					  !(request.getParameter("charge05qty").equals("0")) ){
				  // something in there wasn't equal to zero, let's add that invoice!
				   preparedStatement.executeUpdate();
			  } else {
				  System.out.println(" I think all quantities were zero ");
					System.out.println("Charge01 is " + request.getParameter("charge01") + " and quantity is " + request.getParameter("charge01qty"));
					System.out.println("Charge02 is " + request.getParameter("charge02") + " and quantity is " + request.getParameter("charge02qty"));
					System.out.println("Charge03 is " + request.getParameter("charge03") + " and quantity is " + request.getParameter("charge03qty"));
					System.out.println("Charge04 is " + request.getParameter("charge04") + " and quantity is " + request.getParameter("charge04qty"));
					System.out.println("Charge05 is " + request.getParameter("charge05") + " and quantity is " + request.getParameter("charge05qty"));
			  }
			  
			  // get the ID of the freshly created invoice, to use in invoice_lineitems_invoice table
			  ResultSet insertedId = statement.executeQuery("SELECT LAST_INSERT_ID();");
			
			  String last_id = null;
			    while (insertedId.next()) {
			    	last_id = insertedId.getString("LAST_INSERT_ID()");
			    	System.out.println("Inserted ID is " + last_id);
			    }

			    if (request.getParameter("charge01") != null && ValidationUtilities.isInt(request.getParameter("charge01")))	{
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items_invoice values (default, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  int quantity = Integer.parseInt(request.getParameter("charge01qty"));
					  preparedStatement.setString(1, request.getParameter("charge01"));	// invoice_line_itemsid
					  preparedStatement.setString(2, last_id); // invoiceid
					  for (int q = 0; q < quantity; q++) {
						  preparedStatement.executeUpdate();
					  }
			    }
			    
			    if (request.getParameter("charge02") != null && ValidationUtilities.isInt(request.getParameter("charge02")))	{
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items_invoice values (default, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  int quantity = Integer.parseInt(request.getParameter("charge02qty"));
					  preparedStatement.setString(1, request.getParameter("charge02"));	// invoice_line_itemsid
					  preparedStatement.setString(2, last_id); // invoiceid
					  for (int q = 0; q < quantity; q++) {
						  preparedStatement.executeUpdate();
					  }
			    }
			    
			    if (request.getParameter("charge03") != null && ValidationUtilities.isInt(request.getParameter("charge03")))	{
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items_invoice values (default, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  int quantity = Integer.parseInt(request.getParameter("charge03qty"));
					  preparedStatement.setString(1, request.getParameter("charge03"));	// invoice_line_itemsid
					  preparedStatement.setString(2, last_id); // invoiceid
					  for (int q = 0; q < quantity; q++) {
						  preparedStatement.executeUpdate();
					  }
			    }
			    
			    if (request.getParameter("charge04") != null && ValidationUtilities.isInt(request.getParameter("charge04")))	{
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items_invoice values (default, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  int quantity = Integer.parseInt(request.getParameter("charge04qty"));
					  preparedStatement.setString(1, request.getParameter("charge04"));	// invoice_line_itemsid
					  preparedStatement.setString(2, last_id); // invoiceid
					  for (int q = 0; q < quantity; q++) {
						  preparedStatement.executeUpdate();
					  }
			    }
			    
			    if (request.getParameter("charge05") != null && ValidationUtilities.isInt(request.getParameter("charge05")))	{
					  preparedStatement = connect.prepareStatement("insert into ch_invoice_line_items_invoice values (default, ?, ?)");
					  // columns are id, invoice_line_itemsid, invoiceid
					  int quantity = Integer.parseInt(request.getParameter("charge05qty"));
					  preparedStatement.setString(1, request.getParameter("charge05"));	// invoice_line_itemsid
					  preparedStatement.setString(2, last_id); // invoiceid
					  for (int q = 0; q < quantity; q++) {
						  preparedStatement.executeUpdate();
					  }
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
		  List<Invoice> invoices = new ArrayList<Invoice>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT invoice.id, invoice.invDate, invoice.status, invoice.Userid" 
				+ " FROM clubhub.ch_invoice invoice ");
			      
			    while (resultSet.next()) {
			    	  Invoice invoice = new Invoice();
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  invoice.setUserid(resultSet.getString("userid"));
			    	  request.setAttribute("invoiceID", invoice.getId());
			    	  invoices.add(invoice);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoices", invoices);

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

