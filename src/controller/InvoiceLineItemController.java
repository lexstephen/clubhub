package controller;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: February 03, 2016
* Description: InvoiceController - routes requests to proper view
****************************************************************************************************/
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilities.InvoiceLineItemDao;
import utilities.ValidationUtilities;

@WebServlet("/InvoiceLineItemController")
public class InvoiceLineItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InvoiceLineItemController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		InvoiceLineItemDao dao = new InvoiceLineItemDao();
		String address = "";
		//
		String errorChecker = "n/a";
		
		System.out.println(option);
		
	    try {
	    	switch(option) {
		    	case "add":
		    		if (ValidationUtilities.isValidInvoice(request)) {
		    			dao.addToDatabase(request, response);
		    			//
		    			errorChecker = "Invoice successful";
		    			address = "admin/BatchInvoices.jsp";
		    		} else {
		    			//
			    		errorChecker = "Invoice fail";
		    			address = "/Main.jsp";
		    		}
	    		break;
		    	case "edit":
		    		if (ValidationUtilities.isValidInvoice(request)) {
		    			String invoiceID = request.getParameter("invoiceID");
		    			dao.editInvoice(request, response, invoiceID);
		    			//
		    			errorChecker = "Invoice edited";
		    			address = "/Main.jsp";
		    		} else {
		    			//
			    		errorChecker = "Invoice failed to edit";
		    			address = "/Main.jsp";
		    		}
	    		break;
		    	case "batchEdit":
		    		try {
		    			dao.batchEdit(request, response);
		    			errorChecker = "Invoices edited";
		    		} catch (Exception e){
		    			e.printStackTrace();
		    		}
		    		address = "admin/BatchInvoices.jsp";
	    		break;
		    	case "delete":
		    		try {
		    			String invoiceID = request.getParameter("invoiceID");
		    			System.out.println("Delete invoiceID = " + invoiceID);
						dao.deleteInvoice(request, response, invoiceID);
						//
						errorChecker = "Invoice deleted";
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "admin/BatchInvoices.jsp";
	    		break;
		    	case "batchDelete":
		    		try {
						dao.batchDelete(request, response);
						//
						errorChecker = "Invoices deleted";
					} catch (Exception e) {
						e.printStackTrace();
					}
		    		address = "admin/BatchInvoices.jsp";
	    		break;
	    		default:
	    			errorChecker = "Something has gone horribly wrong";
	    	}
	    	System.out.println(errorChecker);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	    	dispatcher.forward(request, response);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
