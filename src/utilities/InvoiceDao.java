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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Invoice;
import model.User;
import model.InvoiceLineItem;
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
	    try {
			String id = request.getParameter("invoiceID");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from ch_invoice where id = \"" + id + "\""); 
			// if there result set is before the first item, there are entries
			// if it is not, there are not
			if (!resultSet.isBeforeFirst() ) {    
				return false;
			} else {
				return true;
			}
	    } catch (Exception e) {
	    	throw e;
	    } 
	}
	
	public void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    try {	    	
	        
	    	// if editing an invoice, Parameter will be set and we will use that in the new insert
	    	// if adding new, Parameter does not exist and we will use 'default'
	    	String sqlDefault = (request.getParameter("invoiceID")) == null ? "default" : request.getParameter("invoiceID");
	    	String last_id = sqlDefault;
	    	
	    	System.out.println("default string = " + sqlDefault);
	    	
	    	
			  statement = connect.createStatement();
			  preparedStatement = connect.prepareStatement("insert into ch_invoice values (" + sqlDefault + ", ?, ?, ?)");
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
			  }
			  
			  // if we're using 'default', find that number
			  // get the ID of the freshly created invoice, to use in invoice_lineitems_invoice table
			  // otherwise, we're using the Parameter
			  if (sqlDefault.equals("default")) {
				  ResultSet insertedId = statement.executeQuery("SELECT LAST_INSERT_ID();");
				
				 
				    while (insertedId.next()) {
				    	last_id = insertedId.getString("LAST_INSERT_ID()");
				    	System.out.println("Inserted ID is " + last_id);
				    }
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
			    resultSet = statement.executeQuery("SELECT id, username, firstName, lastName from ch_user ORDER BY lastName");
			    while (resultSet.next()) {
			    	  User user = new User();
			    	  user.setUserid(resultSet.getString("id"));
			    	  user.setUsername(resultSet.getString("username"));
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
	public void listAllLineItemsForInvoice(HttpServletRequest request) throws Exception {
		  List<InvoiceLineItem> lineitemsforinvoice = new ArrayList<InvoiceLineItem>();
		  	try{
		  		statement = connect.createStatement();
		  		String qry = "SELECT * from ch_invoice_line_items ili"
					+ " JOIN clubhub.ch_invoice_line_items_invoice ilii"
					+ " ON ilii.invoice_line_itemsid = ili.id"
					+ " JOIN clubhub.ch_invoice invoice"
					+ " ON ilii.Invoiceid = invoice.id"
					+ " WHERE invoice.id = " + request.getParameter("invoiceID");
			    resultSet = statement.executeQuery(qry);
			    while (resultSet.next()) {
			    	InvoiceLineItem lineitem = new InvoiceLineItem();
			    	  lineitem.setId(resultSet.getString("id"));
			    	  lineitem.setDescription(resultSet.getString("description"));
			    	  lineitem.setCost(resultSet.getString("cost"));
			    	  lineitem.setTax(resultSet.getString("tax"));
			    	  lineitemsforinvoice.add(lineitem);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("lineitemsforinvoice", lineitemsforinvoice);
	} 

	public void listAll(HttpServletRequest request) throws Exception { 
		  List<Invoice> invoices = new ArrayList<Invoice>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT i.id, i.invDate, i.status, i.Userid, u.username, u.firstName, u.lastName"
		    				+ " FROM clubhub.ch_invoice i JOIN clubhub.ch_user u on i.Userid = u.id");
			      
			    while (resultSet.next()) {
			    	  Invoice invoice = new Invoice();
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  invoice.setUserID(resultSet.getString("Userid"));
			    	  invoice.setUsername(resultSet.getString("username"));
			    	  invoice.setFirstName(resultSet.getString("firstName"));
			    	  invoice.setLastName(resultSet.getString("lastName"));
			    	  request.setAttribute("invoiceID", invoice.getId());
			    	  invoices.add(invoice);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoices", invoices);

	}
	
	public void listAllForUser(HttpServletRequest request) throws Exception { 
		  
		List<Invoice> invoices = new ArrayList<Invoice>();
		String userID = request.getParameter("userID");
				
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT id, invDate, status"
		    				+ " FROM clubhub.ch_invoice WHERE Userid = " + userID);
			      
			    while (resultSet.next()) {
			    	  Invoice invoice = new Invoice();
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  request.setAttribute("invoiceID", invoice.getId());
			    	  invoices.add(invoice);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoices", invoices);
		  	System.out.println("invoices = " + invoices);

	}
	
	public void deleteInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// if coming from batchDelte, attribute will be set. otherwise, parameter will be set
		String invoiceID = (request.getAttribute("invoiceID")) == null ? request.getParameter("invoiceID") : (String) request.getAttribute("invoiceID");
		
		System.out.println("invoiceID to be deleted = " + invoiceID);
		
		try {	    	
			  statement = connect.createStatement();
			  
			  statement.executeUpdate("delete FROM ch_invoice_line_items_invoice where Invoiceid = + " + invoiceID + ";");
			  statement.executeUpdate("delete FROM ch_invoice where id = + " + invoiceID + ";");
			  		  
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		String [] markedForDeletion = request.getParameterValues("invoiceSelected");
		for (String invoiceID : markedForDeletion) {
			request.setAttribute("invoiceID", invoiceID);
			System.out.println("batchDelete invoiceID: " + request.getAttribute("invoiceID"));
			deleteInvoice(request, response);
		}	
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
		
		  // create an invoice object to store values into
		  Invoice invoice = new Invoice();	
		  PreferenceDao pDao = new PreferenceDao();
		  pDao.taxRate(request);
		  double tax_rate = (double) request.getAttribute("tax_rate"); 
		  // set a list of all available line items into a request attribute
		  listAllLineItems(request);
		  @SuppressWarnings("unchecked")
		  // pull that info into a list
		  List<InvoiceLineItem> lineitems = (ArrayList<InvoiceLineItem>)request.getAttribute("lineitems");
		  
		  // set a list of all of the invoice's individual line items into a request attribute		  
		  listAllLineItemsForInvoice(request);
		  @SuppressWarnings("unchecked")
		  // pull that info into a list
		  List<InvoiceLineItem> lineitemsforinvoice = (ArrayList<InvoiceLineItem>)request.getAttribute("lineitemsforinvoice");
		
		  // create a list that holds arrays that store description, total # ordered
		  List<String[]> lineItemArray = new ArrayList<String[]>();

		  boolean isMatched = false;
		  String currentDesc = null;
		  int itemCost = 0;
		  double itemTax = 0;
		  int currentCost = 0;
		  
		  // go through all items for sale
		  for (int i = 0; i < lineitems.size(); i++) {
			// counter starts at 0 for each potential item
			int cnt = 0;
			// store the current line item 
			InvoiceLineItem lItem = lineitems.get(i);
			// loop through all items in the invoice
			for (int j = 0; j < lineitemsforinvoice.size(); j++) {
				// store the current invoice line item
				InvoiceLineItem lItemForInvoice = lineitemsforinvoice.get(j);
				// check if the current item for sale is in the list of items for the invoice
				if (lItem.getDescription().equals(lItemForInvoice.getDescription())) {
					isMatched = true;
					cnt++;	// used to keep track of how many items were ordered
					currentDesc = lItemForInvoice.getId(); // store the current description
					itemCost = Integer.parseInt(lItemForInvoice.getCost()); // store the current description
					itemTax = Double.parseDouble(lItemForInvoice.getTax()); // store the current description
					currentCost = cnt * itemCost;
				}
			}
			// now that we have examined the whole list of the invoice's line items,
			// store the total # of items and description
			if (isMatched) {
				String[] keyVal = new String[3];
				keyVal[0] = currentDesc;
				keyVal[1] = Integer.toString(cnt);
				keyVal[2] = Integer.toString(currentCost);
				lineItemArray.add(keyVal);
				isMatched = false;
			}
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
			    	  invoice.setInvDate(resultSet.getString("invDate"));
			    	  invoice.setStatus(resultSet.getString("status"));
			    	  invoice.setUserID(resultSet.getString("Userid"));
			    	  System.out.println("Invoice is " + resultSet.getString("id") + " and count is " + resultSet.getString("numItems") + " from user number " + resultSet.getString("Userid"));
			    }
		    	invoice.setId(invoiceID);
			    

			  	String[] thisItem;
			  	int allItemsSubtotal = 0;
			  for (int k = 0; k < lineItemArray.size(); k++) {
				  thisItem = lineItemArray.get(k);
				  switch(k) {
				  case 0:
					  invoice.setCharge01(thisItem[0]);
					  invoice.setCharge01qty(thisItem[1]);
					  invoice.setCharge01subtotal(thisItem[2]);
					  allItemsSubtotal += Integer.parseInt(thisItem[2]);
					  break;
				  case 1:
					  invoice.setCharge02(thisItem[0]);
					  invoice.setCharge02qty(thisItem[1]);
					  invoice.setCharge02subtotal(thisItem[2]);
					  allItemsSubtotal += Integer.parseInt(thisItem[2]);
					  break;
				  case 2:
					  invoice.setCharge03(thisItem[0]);
					  invoice.setCharge03qty(thisItem[1]);
					  invoice.setCharge03subtotal(thisItem[2]);
					  allItemsSubtotal += Integer.parseInt(thisItem[2]);
					  break;
				  case 3:
					  invoice.setCharge04(thisItem[0]);
					  invoice.setCharge04qty(thisItem[1]);
					  invoice.setCharge04subtotal(thisItem[2]);
					  allItemsSubtotal += Integer.parseInt(thisItem[2]);
					  break;
				  case 4:
					  invoice.setCharge05(thisItem[0]);
					  invoice.setCharge05qty(thisItem[1]);
					  invoice.setCharge05subtotal(thisItem[2]);
					  allItemsSubtotal += Integer.parseInt(thisItem[2]);
					  break;
				  }

				  invoice.setResult(Integer.toString(allItemsSubtotal));
				  invoice.setTaxes(Double.toString(tax_rate * allItemsSubtotal));
				  invoice.setFinalresult(Double.toString(allItemsSubtotal + (tax_rate * allItemsSubtotal)));
			  }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoice", invoice);
	} 
	
	public void editInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String invoiceID = request.getParameter("invoiceID");
		
		System.out.println("invoiceID to be edited = " + invoiceID);
		
		try {	    	
			  statement = connect.createStatement();
			  
			  statement.executeUpdate("delete FROM ch_invoice_line_items_invoice where Invoiceid = " + invoiceID + ";");
			  statement.executeUpdate("delete FROM ch_invoice where id = " + invoiceID + ";");
			  
			  addToDatabase(request,response);
			  		  
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// this method edits the postType, accessLevel, and/or category in ch_post
		
		String [] markedForEdit = request.getParameterValues("invoiceSelected");
		
		String status = request.getParameter("status"); // invoice statuses		
		
		for (String invoiceID : markedForEdit) 
		{
		    statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_invoice SET status = '" + status + "' WHERE id='" + invoiceID + "'");
		}
	}
}

