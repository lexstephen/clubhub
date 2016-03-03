<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: BatchInvoices.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utilities.InvoiceDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("thisPage", "Showing All Invoices"); %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<!--  INDIVIDUAL PAGE CONTENT BEGINS HERE -->
	
	<%	InvoiceDao invoice = new InvoiceDao(); %>
	
	<% invoice.listAll(request, response); %>
	
	<!-- This form wont work because there are embedded forms in the included file. I dont know how to work around this.  --> 
		
	<form action="/clubhub/InvoiceController" method="invoice" class="form" role="form">
	
	<div class="row">
		<div class="col-xs-12 col-md-2 col-md-offset-1 control-label">Invoice Id</div>
		<div class="col-xs-12 col-md-2 control-label">Invoice Date</div>
		<div class="col-xs-12 col-md-2 control-label">User</div>
		<div class="col-xs-12 col-md-2 control-label">Invoice Status</div>
		<div class="col-xs-12 col-md-2 control-label">Update</div>
	</div>
		<c:forEach items="${invoices}" var="invoice">
			
			<%@ include file="/WEB-INF/displayEditInvoices.jsp" %>			
		</c:forEach>
		
		<label class="col-sm-2 control-label">
			Invoice Status
		</label>
		<div class="col-sm-3">
			<select class="form-control" name="status">
				<option value="paid">paid</option>
				<option value="unpaid">unpaid</option>
			</select>
		</div>
		<button class="btn btn-warning" type="submit" name="option" value="batchEdit">Edit Marked</button>
		<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
	</form>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>