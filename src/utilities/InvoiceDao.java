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
import model.Invoice;
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

	public void listAll(HttpServletRequest request) throws Exception { 
		  List<Invoice> invoices = new ArrayList<Invoice>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT id, invDate, status, Userid"
		    				+ " FROM clubhub.ch_invoice ");
			      
			    while (resultSet.next()) {
			    	  Invoice invoice = new Invoice();
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  invoice.setUserID(resultSet.getString("Userid"));
			    	  /* 
			    	  invoice.setCharge01(resultSet.getString("charge01"));
			    	  invoice.setCharge01qty(resultSet.getString("charge01qty"));
			    	  invoice.setCharge02(resultSet.getString("charge02"));
			    	  invoice.setCharge02qty(resultSet.getString("charge02qty"));
			    	  invoice.setCharge03(resultSet.getString("charge03"));
			    	  invoice.setCharge03qty(resultSet.getString("charge03qty"));
			    	  invoice.setCharge04(resultSet.getString("charge04"));
			    	  invoice.setCharge04qty(resultSet.getString("charge04qty"));
			    	  invoice.setCharge05(resultSet.getString("charge05"));
			    	  invoice.setCharge05qty(resultSet.getString("charge05qty"));
			    	  invoice.setResult(resultSet.getString("result"));
			    	  invoice.setTaxes(resultSet.getString("taxes"));
			    	  invoice.setFinalresult(resultSet.getString("finalresult"));
			    	  */
			    	  request.setAttribute("invoiceID", invoice.getId());
			    	  
			    	  /*
			    	   * 
		request.setAttribute("userID", request.getParameter("userID"));
		request.setAttribute("invDate", request.getParameter("invDate"));
		request.setAttribute("charge01", request.getParameter("charge01"));
		request.setAttribute("charge01qty", request.getParameter("charge01qty"));
		request.setAttribute("charge01subtotal", request.getParameter("charge01subtotal"));
		request.setAttribute("charge02", request.getParameter("charge02"));
		request.setAttribute("charge02qty", request.getParameter("charge02qty"));
		request.setAttribute("charge02subtotal", request.getParameter("charge02subtotal"));
		request.setAttribute("charge03", request.getParameter("charge03"));
		request.setAttribute("charge03qty", request.getParameter("charge03qty"));
		request.setAttribute("charge03subtotal", request.getParameter("charge03subtotal"));
		request.setAttribute("charge04", request.getParameter("charge04"));
		request.setAttribute("charge04qty", request.getParameter("charge04qty"));
		request.setAttribute("charge04subtotal", request.getParameter("charge04subtotal"));
		request.setAttribute("charge05", request.getParameter("charge05"));
		request.setAttribute("charge05qty", request.getParameter("charge05qty"));
		request.setAttribute("charge05subtotal", request.getParameter("charge05subtotal"));
		request.setAttribute("result", request.getParameter("result"));
		request.setAttribute("taxes", request.getParameter("taxes"));
		request.setAttribute("finalresult", request.getParameter("finalresult"));
			    	   */
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
	
	public void countLineItemsForInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception { 
	  	try{
	  		int numItems = 0;
		    statement = connect.createStatement();
		    String qry = "SELECT count(*) AS numItems FROM clubhub.ch_invoice_line_items_invoice ili WHERE ili.Invoiceid = " + request.getParameter("invoiceID");
		    System.out.println(qry);
		    resultSet = statement.executeQuery(qry);
		    while (resultSet.next()) {
		    	numItems = Integer.parseInt(resultSet.getString("numItems"));
		  		request.setAttribute("numItems", numItems);
		    }
		} catch (SQLException e) {
		      throw e;
		}
	}
		
	public void findInvoice(HttpServletRequest request, String invoiceID) throws Exception { 
		  Invoice invoice = new Invoice();	
		  listAllLineItems(request);
		  System.out.println(request.getParameter("lineitems"));
		  List<InvoiceLineItem> lineitems = new ArrayList<InvoiceLineItem>();
		  for (int i = 0; i < lineitems.size(); i++) {
			    InvoiceLineItem lItem = lineitems.get(i);
			    System.out.println(lItem.getDescription());
		  }
		  	try{
			    statement = connect.createStatement();
			    String qry = "SELECT invoice.id, invoice.invDate, "
			    		+ "invoice.status, invoice.Userid, "
			    		+ "user.username, user.firstName, user.lastName, "
			    		+ "li.description, li.cost, "
			    		+ "pref.tax_rate, " 
			    		+ "(SELECT count(*) FROM clubhub.ch_invoice_line_items_invoice ili WHERE ili.Invoiceid = invoice.id) AS numItems "
				+ " FROM clubhub.ch_invoice invoice "
				+ " JOIN clubhub.ch_user user "
				+ " ON invoice.Userid = user.id "
				+ " JOIN clubhub.ch_invoice_line_items_invoice inv_li_i"
				+ " ON inv_li_i.Invoiceid = invoice.id"
				+ " JOIN clubhub.ch_invoice_line_items li"
				+ " ON inv_li_i.Invoice_Line_ItemsId = li.id"
				+ " JOIN clubhub.ch_preferences pref"
				+ " ON pref.id = 1"
				+ " WHERE invoice.id = " + invoiceID;
			    System.out.println(qry);
			    resultSet = statement.executeQuery(qry);
			    while (resultSet.next()) {
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  System.out.println("Invoice is " + resultSet.getString("id") + " and count is " + resultSet.getString("numItems"));
			    }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoice", invoice);
	} 
	
	public void editInvoice(HttpServletRequest request, HttpServletResponse response, String _invoiceID) throws Exception {
		
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
}

