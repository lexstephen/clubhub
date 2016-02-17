<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>
 
<% request.setAttribute("thisPage", "Add Invoice"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<form action="/clubhub/InvoiceController" method="post" class="form" role="form">
		<div class="row">
			<div class="col-xs-3 control-label">
		    	<label for="inptUserID">Member</label>
			</div>
			<div class="col-xs-7">
				<div class="form-group <c:if test="${!empty errorUserid}">has-error</c:if>">
					<select name="Userid" class="form-control" id="inptUserID">
					  <option value="1" ${Userid == '1' ? 'selected' : ''}>Bob Loblaw</option>
					  <option value="2" ${Userid == '2' ? 'selected' : ''}>Rob Lorlaw</option>
					</select>
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-3 control-label">
			    	<label for="inptInvDate">Date Created</label>
			</div>
			<div class="col-xs-7">
				<div class="form-group <c:if test="${!empty errorInvDate}">has-error</c:if>">
			    	<input type="date" name="invDate" class="form-control" id="inptInvDate" value="${invDate}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-3 control-label">
		    	<label>Charges</label>
			</div>
			<div class="col-xs-5">
			    <label>Item</label>
			</div>
			<div class="col-xs-2">
			    <label>Qty</label>
			</div>
			<div class="col-xs-2">
			    <label>Price</label>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge01" class="form-control" id="inptCharge01">
					  <option ${charge01 == '--' ? 'selected' : ''}>--</option>
					  <option ${charge01 == 'Membership Fee - Adult' ? 'selected' : ''}>Membership Fee - Adult</option>
					  <option ${charge01 == 'Membership Fee - Student' ? 'selected' : ''}>Membership Fee - Student</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge01qty" class="form-control" id="inptCharge01qty" value="${charge01qty}">
			</div>
			<div class="col-xs-2">
				$0.00
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge02" class="form-control" id="inptCharge02">
					  <option ${charge02 == '--' ? 'selected' : ''}>--</option>
					  <option ${charge02 == 'Membership Fee - Adult' ? 'selected' : ''}>Membership Fee - Adult</option>
					  <option ${charge02 == 'Membership Fee - Student' ? 'selected' : ''}>Membership Fee - Student</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge02qty" class="form-control" id="inptCharge02qty" value="${charge02qty}">
			</div>
			<div class="col-xs-2">
				$0.00
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge03" class="form-control" id="inptCharge03">
					  <option ${charge03 == '--' ? 'selected' : ''}>--</option>
					  <option ${charge03 == 'Membership Fee - Adult' ? 'selected' : ''}>Membership Fee - Adult</option>
					  <option ${charge03 == 'Membership Fee - Student' ? 'selected' : ''}>Membership Fee - Student</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge03qty" class="form-control" id="inptCharge03qty" value="${charge03qty}">
			</div>
			<div class="col-xs-2">
				$0.00
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge04" class="form-control" id="inptCharge04">
					  <option ${charge04 == '--' ? 'selected' : ''}>--</option>
					  <option ${charge04 == 'Membership Fee - Adult' ? 'selected' : ''}>Membership Fee - Adult</option>
					  <option ${charge04 == 'Membership Fee - Student' ? 'selected' : ''}>Membership Fee - Student</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge04qty" class="form-control" id="inptCharge04qty" value="${charge04qty}">
			</div>
			<div class="col-xs-2">
				$0.00
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge05" class="form-control" id="inptCharge05">
					  <option ${charge05 == '--' ? 'selected' : ''}>--</option>
					  <option ${charge05 == 'Membership Fee - Adult' ? 'selected' : ''}>Membership Fee - Adult</option>
					  <option ${charge05 == 'Membership Fee - Student' ? 'selected' : ''}>Membership Fee - Student</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge05qty" class="form-control" id="inptCharge05qty" value="${charge05qty}">
			</div>
			<div class="col-xs-2">
				$0.00
			</div>
		</div>

		<div class="row">
			<div class="col-xs-3 control-label">
			    <label>Subtotal</label>			  
			</div>
			<div class="col-xs-9">
				$0.00
			</div>
		</div>

		<div class="row">
			<div class="col-xs-3 control-label">
			    <label>Taxes</label>			  
			</div>
			<div class="col-xs-9">
				$0.00
			</div>
		</div>

		<div class="row">
			<div class="col-xs-3 control-label">
			    <label>Total Due</label>			  
			</div>
			<div class="col-xs-9">
				$0.00
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-3 control-label">
			    	<label for="inptStatus">Status</label>			  
			</div>
			<div class="col-xs-9">
				<div class="radio">
			    	<label class="checkbox-inline">
			      		<input type="radio" name="status" id="inptStatusU" value="unpaid" checked  ${status == 'unpaid' ? 'checked' : ''}> Unpaid
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="status" id="inptStatusP" value="paid"  ${status == 'P' ? 'paid' : ''}> Paid
			      	</label>
			  	</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="form-group">
					<input type="hidden" name="option" value="add">
			    	<input type="submit" class="btn btn-default" value="Create Invoice">
			  	</div>
			</div>
		</div>
	</form>
<%@ include file="/WEB-INF/footer_backend.jsp" %>