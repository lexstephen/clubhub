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

import utilities.InvoiceDao;
import utilities.PreferenceDao;
import utilities.ValidationUtilities;

@WebServlet("/InvoiceController")
public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InvoiceController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		InvoiceDao dao = new InvoiceDao();
		PreferenceDao pref = new PreferenceDao();
		String address = "";
		String errorChecker = "n/a";
		
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
		pref.taxRate(request);
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
		    			address = "admin/AddInvoice.jsp";
		    		}
	    		break;
		    	case "edit":
		    		if (ValidationUtilities.isValidInvoice(request)) {
		    			dao.editInvoice(request, response);
		    			//
		    			errorChecker = "Invoice edited";
		    			address = "admin/BatchInvoices.jsp";
		    		} else {
		    			//
			    		errorChecker = "Invoice failed to edit";
		    			address = "admin/EditInvoice.jsp?invoiceID=" + request.getParameter("invoiceID");
		    		}
	    		break;
		    	case "batchEdit":
		    		try {
		    			dao.batchEdit(request, response);
		    			errorChecker = "Invoices edited";
		    		} catch (Exception e){
		    			e.printStackTrace();
		    		}
		    		
		    		System.out.println("attribute profile = " + request.getParameter("profileRedirect"));
		    		
		    		if (request.getParameter("profileRedirect") != null) {
		    			address = (String) request.getParameter("profileRedirect");
		    		} else {
		    		address = "admin/BatchInvoices.jsp";
		    		}
		    		
	    		break;
		    	case "delete":
		    		try {
						dao.deleteInvoice(request, response);
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
