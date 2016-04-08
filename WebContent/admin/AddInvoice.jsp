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

<%@ page import="utilities.InvoiceDao"%>
<%@ page import="utilities.PreferenceDao"%>
<% InvoiceDao invoice = new InvoiceDao(); %>
<% PreferenceDao preference2 = new PreferenceDao(); %>
<% preference2.taxRate(request); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
<c:if test="${!empty errorString}">
	<div class="alert alert-danger" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span> ${errorString }
		<c:if test="${!empty errorInvDate}">
			<li>${ errorInvDate }</li>
		</c:if>
		<c:if test="${!empty errorLineItems}">
			<li>${ errorLineItems }</li>
		</c:if>
	</div>
</c:if>

<form action="${pageContext.request.contextPath}/InvoiceController"
	method="post" class="form" role="form">
	<div class="row">
		<div class="col-xs-3 control-label">
			<label for="inptUserID">Member</label>
		</div>
		<div class="col-xs-7">
			<div class="form-group">
				<% invoice.listAllUsers(request); %>
				<select name="userID" class="form-control" id="inptUserID">
					<c:forEach items="${users}" var="user">
						<option value="${user.userid}"
							${userID == user.userid ? 'selected' : ''}>${user.firstName}
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
					id="inptInvDate" value="${invDate}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 control-label">
			<label>Charges <small>(New line items can be added <a
					href="AddLineItems.jsp">here</a>.)
			</small></label>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
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
	</div>



	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
			<div class="form-group">
				<% invoice.listAllLineItems(request); %>
				<select name="charge01" class="form-control" id="inptCharge01">
					<option value="---" ${charge01 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${charge01 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge01Qty}">has-error</c:if>">
			<input type="text" name="charge01qty" class="form-control qty"
				id="inptCharge01qty" value="${(charge01qty > 0)? charge01qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge01cost" id="charge01cost"
				class="form-control unit" readonly value="${charge01cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge01subtotal" id="charge01subtotal"
				class="form-control amount" readonly value="${charge01subtotal }">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
			<div class="form-group">
				<select name="charge02" class="form-control" id="inptCharge02">
					<option value="---" ${charge02 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${charge02 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge02Qty}">has-error</c:if>">
			<input type="text" name="charge02qty" class="form-control qty"
				id="inptCharge02qty" value="${(charge02qty > 0)? charge02qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge02cost" id="charge02cost"
				class="form-control unit" readonly value="${charge02cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge02subtotal" id="charge02subtotal"
				class="form-control amount" readonly value="${charge02subtotal }">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
			<div class="form-group">
				<select name="charge03" class="form-control" id="inptCharge03">
					<option value="---" ${charge03 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${charge03 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge03Qty}">has-error</c:if>">
			<input type="text" name="charge03qty" class="form-control qty"
				id="inptCharge03qty" value="${(charge03qty > 0)? charge03qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge03cost" id="charge03cost"
				class="form-control unit" readonly value="${charge03cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge03subtotal" id="charge03subtotal"
				class="form-control amount" readonly value="${charge03subtotal }">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
			<div class="form-group">
				<select name="charge04" class="form-control" id="inptCharge04">
					<option value="---" ${charge04 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${charge04 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge04Qty}">has-error</c:if>">
			<input type="text" name="charge04qty" class="form-control qty"
				id="inptCharge04qty" value="${(charge04qty > 0)? charge04qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge04cost" id="charge04cost"
				class="form-control unit" readonly value="${charge04cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge04subtotal" id="charge04subtotal"
				class="form-control amount" readonly value="${charge04subtotal }">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-5 col-xs-offset-1">
			<div class="form-group">
				<select name="charge05" class="form-control" id="inptCharge05">
					<option value="---" ${charge05 == '---' ? 'selected' : ''}>
						-</option>
					<c:forEach items="${lineitems}" var="lineitem">
						<option value="${lineitem.id}"
							${charge05 == lineitem.id ? 'selected' : ''}>${lineitem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div
			class="col-xs-2 <c:if test="${!empty errorCharge05Qty}">has-error</c:if>">
			<input type="text" name="charge05qty" class="form-control qty"
				id="inptCharge05qty" value="${(charge05qty > 0)? charge05qty:'0'}">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge05cost" id="charge05cost"
				class="form-control unit" readonly value="${charge05cost }">
		</div>
		<div class="col-xs-2">
			<input type="text" name="charge05subtotal" id="charge05subtotal"
				class="form-control amount" readonly value="${charge05subtotal }">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-8">
			<label>Subtotal</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="result" id="result"
					class="form-control result" readonly value="${result }">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-8">
			<label>Taxes</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="taxes" id="taxes" class="form-control taxes" readonly value="${taxes}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-xs-offset-8">
			<label>Total Due</label>
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<input type="text" name="finalresult" id="finalresult" class="form-control finalresult" readonly value="${finalresult}">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-3 control-label">
			<label for="inptStatus">Status</label>
		</div>
		<div class="col-xs-9">
			<div class="radio">
				<label class="checkbox-inline"> <input type="radio"
					name="status" id="inptStatusU" value="unpaid"
					${status != 'paid' ? 'checked' : ''}> Unpaid
				</label> <label class="checkbox-inline"> <input type="radio"
					name="status" id="inptStatusP" value="paid"
					${status == 'paid' ? 'checked' : ''}> Paid
				</label>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<input type="hidden" name="option" value="add"> 
				<input type="submit" class="btn btn-info" value="Create Invoice">
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
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
function fnAlltax(){
  	var total=0;
	var tax_rate = ${tax_rate };
	
    $(".amount").each(function(){
     total += parseFloat(($(this).val() * tax_rate)||0);
});
$(".taxes").val(total);
   	fnFinalTotal();
}
</script>
<%@ include file="/WEB-INF/footer_backend.jsp"%>