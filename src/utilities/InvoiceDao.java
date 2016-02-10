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
			String id = request.getParameter("id");
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
			HttpSession session = request.getSession();
			// this is temp  v v v
			session.setAttribute("userID", "2");
			// this is temp  ^ ^ ^
	      statement = connect.createStatement();
	      preparedStatement = connect.prepareStatement("insert into ch_invoice values (default, ?, ?, ?, ?, ?, ?)");
	      // columns are title, content, Userid, Invoicetypeid, Accessid, Categoryid
	      // Parameters start with 1 because we are sending 'default' to the auto incrementing id
	      preparedStatement.setString(1, request.getParameter("blogTitle"));	// title
	      preparedStatement.setString(2, request.getParameter("blogContent")); // content
	      preparedStatement.setString(3, (String)session.getAttribute("userID"));	// Userid
	      preparedStatement.setString(4, request.getParameter("pageType")); // Invoicetypeid
	      preparedStatement.setString(5, request.getParameter("accessLevel")); // Accessid
	      preparedStatement.setString(6, request.getParameter("pageCategory")); // Categoryid
	      preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void listAll(HttpServletRequest request) throws Exception {
		  List<Invoice> invoices = new ArrayList<Invoice>();
		  	try{
		  		statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT invoice.title, invoice.content, invoice.id, user.username, user.firstName, user.lastName, invoicetype.type, access.type, category.type " 
				+ "FROM clubhub.ch_invoice invoice "
				+ "JOIN clubhub.ch_invoicetype invoicetype "
				+ "ON invoice.Invoicetypeid = invoicetype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON invoice.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON invoice.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON invoice.Categoryid = category.id ");
			      
			    while (resultSet.next()) {
			    	  Invoice invoice = new Invoice();
			    	  invoice.setTitle(resultSet.getString("title"));
			    	  invoice.setContent(resultSet.getString("content"));
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setUserFirstName(resultSet.getString("user.firstName"));
			    	  invoice.setUserLastName(resultSet.getString("user.lastName"));
			    	  invoice.setInvoiceType(resultSet.getString("invoicetype.type"));
			    	  invoice.setAccessLevel(resultSet.getString("access.type"));
			    	  invoice.setCategory(resultSet.getString("category.type"));
			    	  
			    	  request.setAttribute("invoiceID", invoice.getId());

			    	  invoices.add(invoice);
			    }
		    } catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoices", invoices);
	} 
	
	public void deleteInvoice(HttpServletRequest request, HttpServletResponse response, String invoiceID) throws Exception {

		  try {
			  statement = connect.createStatement();
			  statement.executeUpdate("delete from ch_invoice where id =" + invoiceID); 
		  } catch (SQLException e) {
		      throw e;
		  }
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String [] markedForDeletion = request.getParameterValues("invoiceSelected");
		for (String x : markedForDeletion) {
			deleteInvoice(request, response, x);
		}		
	}
	
	public void findInvoice(HttpServletRequest request, String invoiceID) throws Exception {
		  Invoice invoice = new Invoice();
		  
		  	try{
			    statement = connect.createStatement();
			    resultSet = statement.executeQuery("SELECT invoice.title, invoice.content, invoice.id, user.username, user.firstName, user.lastName, invoicetype.type, access.type, category.type " 
				+ "FROM clubhub.ch_invoice invoice "
				+ "JOIN clubhub.ch_invoicetype invoicetype "
				+ "ON invoice.Invoicetypeid = invoicetype.id "
				+ "JOIN clubhub.ch_user user "
				+ "ON invoice.Userid = user.id "
				+ "JOIN clubhub.ch_access access "
				+ "ON invoice.Accessid = access.id "
				+ "JOIN clubhub.ch_category category "
				+ "ON invoice.Categoryid = category.id "
				+ "WHERE invoice.id = " + invoiceID);
			    
			    while (resultSet.next()) {
			    	  invoice.setTitle(resultSet.getString("title"));
			    	  invoice.setContent(resultSet.getString("content"));
			    	  invoice.setId(resultSet.getString("id"));
			    	  invoice.setUserFirstName(resultSet.getString("user.firstName"));
			    	  invoice.setUserLastName(resultSet.getString("user.lastName"));
			    	  invoice.setInvoiceType(resultSet.getString("invoicetype.type"));
			    	  invoice.setAccessLevel(resultSet.getString("access.type"));
			    	  invoice.setCategory(resultSet.getString("category.type"));
			    }
			} catch (SQLException e) {
			      throw e;
			}
		  	request.setAttribute("invoice", invoice);
	} 
	
	public void editInvoice(HttpServletRequest request, HttpServletResponse response, String _invoiceID) throws Exception {
	    try {			
			String invoiceID = request.getParameter("invoiceID");
		    String title = request.getParameter("blogTitle");	// title
		    String content = request.getParameter("blogContent"); // content
		    String pageType = request.getParameter("pageType"); // Invoicetypeid
		    String accessLevel = request.getParameter("accessLevel"); // Accessid
		    String category = request.getParameter("pageCategory"); // Categoryid
			
	      
		    /*UPDATE `clubhub`.`ch_invoice` SET `title`='blogtitle', `content`='schoop doopy', 
	    		  `Userid`='1', `Invoicetypeid`='2', `Accessid`='2', `Categoryid`='2' WHERE `id`='6';*/
	      
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_invoice SET title='" + title + "', content='" + content + "', Invoicetypeid='" + pageType + 
					"', Accessid='" + accessLevel + "', Categoryid='" + category + "' WHERE id='" + invoiceID + "'");
	      
	      //preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    }
	}

	public void batchEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String [] markedForEdit = request.getParameterValues("invoiceSelected");
		String invoiceID, pageType, accessLevel, category;
		
		pageType = request.getParameter("pageType"); // Invoicetypeid
	    accessLevel = request.getParameter("accessLevel"); // Accessid
	    category = request.getParameter("pageCategory"); // Categoryid
		
		for (String x : markedForEdit) {
			invoiceID = x;
		    
			statement = connect.createStatement();
			statement.executeUpdate("UPDATE ch_invoice SET Invoicetypeid='" + pageType + 
					"', Accessid='" + accessLevel + "', Categoryid='" + category + "' WHERE id='" + invoiceID + "'");
		}				
	}
}

