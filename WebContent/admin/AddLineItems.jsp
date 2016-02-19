<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>
 
<% request.setAttribute("thisPage", "Add Line Items"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<%@ page import="utilities.InvoiceLineItemDao"%>
<%	InvoiceLineItemDao lineitem = new InvoiceLineItemDao(); %>
<% lineitem.listAllLineItems(request); %>

${errorString }

	<form action="/clubhub/InvoiceLineItemController" method="post" class="form" role="form">
		<div class="row">
			<div class="col-xs-4 control-label"></div>
			<div class="col-xs-4 control-label">
				<label>Description</label>
			</div>
			<div class="col-xs-4 control-label">
				<label>Cost</label>
			</div>
		</div>

		<div class="row form-group">
			<div class="col-xs-4 control-label">
		    	<label for="inptUserID">New Item #1:</label>
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem01Description" class="form-control" id="idLineItem01Description" value="${lineItem01Description}">
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem01Cost" class="form-control" id="idLineItem01Description" value="${(lineItem01Cost > 0)? lineItem01Cost:'0'}">
			</div>
		</div>

		<div class="row form-group">
			<div class="col-xs-4 control-label">
		    	<label for="inptUserID">New Item #2:</label>
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem02Description" class="form-control" id="idLineItem02Description" value="${lineItem02Description}">
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem02Cost" class="form-control" id="idLineItem02Description" value="${(lineItem02Cost > 0)? lineItem02Cost:'0'}">
			</div>
		</div>


		<div class="row form-group">
			<div class="col-xs-4 control-label">
		    	<label for="inptUserID">New Item #3:</label>
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem03Description" class="form-control" id="idLineItem03Description" value="${lineItem03Description}">
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem03Cost" class="form-control" id="idLineItem03Description" value="${(lineItem03Cost > 0)? lineItem03Cost:'0'}">
			</div>
		</div>		
		

		<div class="row form-group">
			<div class="col-xs-4 control-label">
		    	<label for="inptUserID">New Item #4:</label>
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem04Description" class="form-control" id="idLineItem04Description" value="${lineItem04Description}">
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem04Cost" class="form-control" id="idLineItem04Description" value="${(lineItem04Cost > 0)? lineItem04Cost:'0'}">
			</div>
		</div>
		

		<div class="row form-group">
			<div class="col-xs-4 control-label">
		    	<label for="inptUserID">New Item #5:</label>
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem05Description" class="form-control" id="idLineItem05Description" value="${lineItem05Description}">
			</div>
			<div class="col-xs-4">
				<input type="text" name="lineItem05Cost" class="form-control" id="idLineItem05Description" value="${(lineItem05Cost > 0)? lineItem05Cost:'0'}">
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<input type="hidden" name="option" value="add">
			    	<input type="submit" class="btn btn-default" value="Add Line Item">
			  	</div>
			</div>
		</div>
	</form>
<%@ include file="/WEB-INF/footer_backend.jsp" %>