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
	
	<% invoice.listAll(request); %>
	

	<form action="/clubhub/InvoiceController" method="post" class="form" role="form">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-hover sortable">
				<thead>
					<tr>
						<th class="col-xs-12 col-md-1 sorttable_nosort"></th>
						<th class="col-xs-12 col-md-3 control-label">Invoice Id</th>
						<th class="col-xs-12 col-md-2">Invoice Date</th>
						<th class="col-xs-12 col-md-2">User</th>
						<th class="col-xs-12 col-md-2">Invoice Status</th>
						<th class="col-xs-12 col-md-2 sorttable_nosort"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${invoices}" var="invoice">
						<%@ include file="/WEB-INF/displayEditInvoices.jsp" %>			
					</c:forEach>			
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-2 control-label">
			Invoice Status
		</div>
		<div class="col-sm-3">
			<select class="form-control" name="status">
				<option value="paid">paid</option>
				<option value="unpaid">unpaid</option>
			</select>
		</div>
		<div class="col-sm-4 control-label">
			<button class="btn btn-warning" type="submit" name="option" value="batchEdit">Edit Marked</button>
			<button class="btn btn-danger" type="submit" name="option" value="batchDelete">Delete Marked</button>
		</div>
	</div>
	
	<span class="pagination">
		<a href="first">&lt;&lt;</a> | <a href="previous">&lt;</a> | <a href="next">&gt;</a> | <a href="last">&gt;&gt;</a>
	</span>
	
	</form>
	<!--  INDIVIDUAL PAGE CONTENT ENDS HERE -->

<%@ include file="/WEB-INF/footer_backend.jsp" %>