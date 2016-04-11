<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>



<% request.setAttribute("thisPage", "Edit Invoice"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<c:if test="${sessionScope.isAdmin == false }">
	<c:redirect url="../Main.jsp" />
</c:if>

<% 
InvoiceDao invoice = new InvoiceDao();
UserDao user = new UserDao();
invoice.findInvoice(request, request.getParameter("invoiceID"));
preference.taxRate(request); %>
${errorString }
<ul>
	<c:if test="${!empty errorInvDate}">
		<li>${ errorInvDate }</li>
	</c:if>
	<c:if test="${!empty errorLineItems}">
		<li>${ errorLineItems }</li>
	</c:if>

</ul>
<form action="${pageContext.request.contextPath}/InvoiceController"
	method="post" class="form" role="form">
	<div class="row">
		<div class="col-xs-3 control-label">
			<label for="inptUserID">Member</label>
		</div>
		<div class="col-xs-7">
			<div class="form-group">
				<% user.listAllUsers(request); %>
				<select name="userID" class="form-control" id="inptUserID">
					<c:forEach items="${users}" var="user">
						<option value="${user.userid}"
							${invoice.userID == user.userid ? 'selected' : ''}>${user.firstName}
							${user.lastName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-3 control-label">
			<label for="inptInvDate">Date Created</label>
		</div>
		<div class="col-xs-7">
			<div
				class="form-group <c:if test="${!empty errorInvDate}">has-error</c:if>">
				<input type="date" name="invDate" class="form-control"
					id="inptInvDate" value="${invoice.invDate}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 control-label">
			<label>Charges</label>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<label>Item</label>
		</div>
		<div class="col-xs-2">
			<label>Qty</label>
		</div>
		<div class="col-xs-2">
			<label>Price</label>
		</div>
		<div class="col-xs-2">
			<label>Subtotal</label>
		</div>
		<div class="col-xs-1">
			<label>Edit</label>
		</div>
	</div>



	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<div class="form-group">
				<% invoice.listAllLineItems(request); %>
				<select name="charge01" class="form-control" id="inptCharge01">
					<option value="---" ${invoice.charge01 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${invoice.charge01 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge01Qty}">has-error</c:if>">
			<input type="text" name="charge01qty" class="form-control qty"
				id="inptCharge01qty"
				value="${(invoice.charge01qty > 0)? invoice.charge01qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge01cost" id="charge01cost"
				class="form-control unit" readonly value="${charge01cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge01subtotal" id="charge01subtotal"
				class="form-control amount" readonly
				value="${invoice.charge01subtotal }">
		</div>
		<div class="col-xs-1">
			<input type="button" class="btn btn-info" value="Clear"
				onclick="clearItem(1)">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<div class="form-group">
				<select name="charge02" class="form-control" id="inptCharge02">
					<option value="---" ${invoice.charge02 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${invoice.charge02 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge02Qty}">has-error</c:if>">
			<input type="text" name="charge02qty" class="form-control qty"
				id="inptCharge02qty"
				value="${(invoice.charge02qty > 0)? invoice.charge02qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge02cost" id="charge02cost"
				class="form-control unit" readonly value="${charge02cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge02subtotal" id="charge02subtotal"
				class="form-control amount" readonly
				value="${invoice.charge02subtotal }">
		</div>
		<div class="col-xs-1">
			<input type="button" class="btn btn-info" value="Clear"
				onclick="clearItem(2)">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<div class="form-group">
				<select name="charge03" class="form-control" id="inptCharge03">
					<option value="---" ${invoice.charge03 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${invoice.charge03 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge03Qty}">has-error</c:if>">
			<input type="text" name="charge03qty" class="form-control qty"
				id="inptCharge03qty"
				value="${(invoice.charge03qty > 0)? invoice.charge03qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge03cost" id="charge03cost"
				class="form-control unit" readonly value="${charge03cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge03subtotal" id="charge03subtotal"
				class="form-control amount" readonly
				value="${invoice.charge03subtotal }">
		</div>
		<div class="col-xs-1">
			<input type="button" class="btn btn-info" value="Clear"
				onclick="clearItem(3)">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<div class="form-group">
				<select name="charge04" class="form-control" id="inptCharge04">
					<option value="---" ${invoice.charge04 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${invoice.charge04 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge04Qty}">has-error</c:if>">
			<input type="text" name="charge04qty" class="form-control qty"
				id="inptCharge04qty"
				value="${(invoice.charge04qty > 0)? invoice.charge04qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge04cost" id="charge04cost"
				class="form-control unit" readonly value="${charge04cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge04subtotal" id="charge04subtotal"
				class="form-control amount" readonly
				value="${invoice.charge04subtotal }">
		</div>
		<div class="col-xs-1">
			<input type="button" class="btn btn-info" value="Clear"
				onclick="clearItem(4)" id="poop">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-4 col-xs-offset-1">
			<div class="form-group">
				<select name="charge05" class="form-control" id="inptCharge05">
					<option value="---" ${invoice.charge05 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${invoice.charge05 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge05Qty}">has-error</c:if>">
			<input type="text" name="charge05qty" class="form-control qty"
				id="inptCharge05qty"
				value="${(invoice.charge05qty > 0)? invoice.charge05qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge05cost" id="charge05cost"
				class="form-control unit" readonly value="${charge05cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge05subtotal" id="charge05subtotal"
				class="form-control amount" readonly
				value="${invoice.charge05subtotal }">
		</div>
		<div class="col-xs-1">
			<input type="button" class="btn btn-info" value="Clear"
				onclick="clearItem(5)">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-7">
			<label>Subtotal</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="result" id="result"
					class="form-control result" readonly value="${invoice.result }">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-7">
			<label>Taxes</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="taxes" id="taxes"
					class="form-control taxes" readonly value="${invoice.taxes }">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-7">
			<label>Total Due</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="finalresult" id="finalresult"
					class="form-control finalresult" readonly
					value="${invoice.finalresult }">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 control-label">
			<label for="inptStatus">Status</label>
		</div>
		<div class="col-xs-10">
			<div class="radio">
				<label class="checkbox-inline"> <input type="radio"
					name="status" id="inptStatusU" value="unpaid"
					${invoice.status == 'unpaid' ? 'checked' : ''}> Unpaid
				</label> <label class="checkbox-inline"> <input type="radio"
					name="status" id="inptStatusP" value="paid"
					${invoice.status == 'paid' ? 'checked' : ''}> Paid
				</label>
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-xs-2">
			<input type="hidden" name="option" value="edit"> <input
				type="hidden" name="invoiceID" value="${invoice.id}"> <input
				type="submit" class="btn btn-info" value="Edit Invoice">
</form>
</div>
<div class="col-xs-2">
	<form action="${pageContext.request.contextPath}/InvoiceController"
		method="post">
		<input type="hidden" name="invoiceID" value="${invoice.id}"> <input
			type="hidden" name="option" value="delete"> <input
			class="btn btn-danger" type="submit" value="Delete">
</div>
<span class="col-xs-8"></span>
</div>

<script type="text/javascript">
	
	function fnAlltax(){
	  	var total=0;
		var tax_rate = ${tax_rate };
		
	    $(".amount").each(function(){
	         total += parseFloat(($(this).val() * tax_rate)||0);
	    });
	    $(".taxes").val(total);
	   	fnFinalTotal();
	}	
	
	var lineItems = new Array();
	<c:forEach items="${lineitems}" var="lineitem" varStatus="status"> 
		lineItemObj = new Object();
		lineItemObj.id = ${lineitem.id}; 
		lineItemObj.cost = ${lineitem.cost}; 
		lineItems.push(lineItemObj);
	</c:forEach> 

	lineItemObj = new Object();
	lineItemObj.id = '---'; 
	lineItemObj.cost = 0; 
	lineItems.push(lineItemObj);

<%@ include file="/WEB-INF/footer_backend.jsp"%>