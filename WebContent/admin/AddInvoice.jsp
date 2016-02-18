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
<%	InvoiceDao invoice = new InvoiceDao(); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<form action="/clubhub/InvoiceController" method="post" class="form" role="form">
		<div class="row">
			<div class="col-xs-3 control-label">
		    	<label for="inptUserID">Member</label>
			</div>
			<div class="col-xs-7">
				<div class="form-group <c:if test="${!empty errorUserid}">has-error</c:if>">
					<% invoice.listAllUsers(request); %>
					<select name="userID" class="form-control" id="inptUserID">
						<c:forEach items="${users}" var="user">
					 		<option value="${user.userid}" ${userID == user.userid} ? 'selected' : ''}>${user.firstName} ${user.lastName}</option>
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
					<% invoice.listAllLineItems(request); %>
					<select name="charge01" class="form-control" id="inptCharge01">
				 		<option value="---" ${charge01 == '---'} ? 'selected' : ''}> - </option>
						<c:forEach items="${lineitems}" var="lineitem">
					 		<option value="${lineitem.id}" ${charge01 == lineitem.id} ? 'selected' : ''}>${lineitem.description}</option>
						</c:forEach>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge01qty" class="form-control" id="inptCharge01qty" value="${(charge01qty > 0)? charge01qty:'0'}">
			</div>
			<div class="col-xs-2">
				<script type="text/javascript">
				var lineItems = new Array();
				<c:forEach items="${lineitems}" var="lineitem" varStatus="status"> 
					lineItemObj = new Object();
					lineItemObj.id = ${lineitem.id}; 
					lineItemObj.cost = ${lineitem.cost}; 
					lineItems.push(lineItemObj);
				</c:forEach> 

				$(document).ready(function(){
				    $("select[name='charge01']").change(function(){
				        $(this).find("option:selected").each(function(){
							for	(index = 0; index < lineItems.length; index++) {
					            if($(this).attr("value")==lineItems[index].id){
									  document.getElementById('inptCharge01qty').value = "1";
									  document.getElementById('charge01cost').innerHTML = "$" + parseFloat(lineItems[index].cost);
					            }
				            }
				        });
				    }).change();
				});

				$(document).ready(function(){
				    $("select[name='charge02']").change(function(){
				        $(this).find("option:selected").each(function(){
							for	(index = 0; index < lineItems.length; index++) {
					            if($(this).attr("value")==lineItems[index].id){
									  document.getElementById('inptCharge02qty').value = "1";
									  document.getElementById('charge02cost').innerHTML = "$" + parseFloat(lineItems[index].cost);
					            }
				            }
				        });
				    }).change();
				});
				
				$(document).ready(function(){
				    $("select[name='charge03']").change(function(){
				        $(this).find("option:selected").each(function(){
							for	(index = 0; index < lineItems.length; index++) {
					            if($(this).attr("value")==lineItems[index].id){
									  document.getElementById('inptCharge03qty').value = "1";
									  document.getElementById('charge03cost').innerHTML = "$" + parseFloat(lineItems[index].cost);
					            }
				            }
				        });
				    }).change();
				});

				$(document).ready(function(){
				    $("select[name='charge04']").change(function(){
				        $(this).find("option:selected").each(function(){
							for	(index = 0; index < lineItems.length; index++) {
					            if($(this).attr("value")==lineItems[index].id){
									  document.getElementById('inptCharge04qty').value = "1";
									  document.getElementById('charge04cost').innerHTML = "$" + parseFloat(lineItems[index].cost);
					            }
				            }
				        });
				    }).change();
				});
				

				$(document).ready(function(){
				    $("select[name='charge05']").change(function(){
				        $(this).find("option:selected").each(function(){
							for	(index = 0; index < lineItems.length; index++) {
					            if($(this).attr("value")==lineItems[index].id){
									  document.getElementById('inptCharge05qty').value = "1";
									  document.getElementById('charge05cost').innerHTML = "$" + parseFloat(lineItems[index].cost);
					            }
				            }
				        });
				    }).change();
				});
				
				</script>
				<div id="charge01cost"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge02" class="form-control" id="inptCharge02">
				 		<option value="---" ${charge02 == '---'} ? 'selected' : ''}> - </option>
						<c:forEach items="${lineitems}" var="lineitem">
					 		<option value="${lineitem.id}" ${charge02 == lineitem.id} ? 'selected' : ''}>${lineitem.description}</option>
						</c:forEach>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge02qty" class="form-control" id="inptCharge02qty" value="${(charge02qty > 0)? charge02qty:'0'}">
			</div>
			<div class="col-xs-2">
				<div id="charge02cost"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge03" class="form-control" id="inptCharge03">
				 		<option value="---" ${charge03 == '---'} ? 'selected' : ''}> - </option>
						<c:forEach items="${lineitems}" var="lineitem">
					 		<option value="${lineitem.id}" ${charge03 == lineitem.id} ? 'selected' : ''}>${lineitem.description}</option>
						</c:forEach>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge03qty" class="form-control" id="inptCharge03qty" value="${(charge03qty > 0)? charge03qty:'0'}">
			</div>
			<div class="col-xs-2">
				<div id="charge03cost"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge04" class="form-control" id="inptCharge04">
				 		<option value="---" ${charge04 == '---'} ? 'selected' : ''}> - </option>
						<c:forEach items="${lineitems}" var="lineitem">
					 		<option value="${lineitem.id}" ${charge04 == lineitem.id} ? 'selected' : ''}>${lineitem.description}</option>
						</c:forEach>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge04qty" class="form-control" id="inptCharge04qty" value="${(charge04qty > 0)? charge04qty:'0'}">
			</div>
			<div class="col-xs-2">
				<div id="charge04cost"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5 col-xs-offset-3">
				<div class="form-group">
					<select name="charge05" class="form-control" id="inptCharge05">
				 		<option value="---" ${charge05 == '---'} ? 'selected' : ''}> - </option>
						<c:forEach items="${lineitems}" var="lineitem">
					 		<option value="${lineitem.id}" ${charge05 == lineitem.id} ? 'selected' : ''}>${lineitem.description}</option>
						</c:forEach>
					</select>
			  	</div>
			</div>
			<div class="col-xs-2">
				<input type="text" name="charge05qty" class="form-control" id="inptCharge05qty" value="${(charge05qty > 0)? charge05qty:'0'}">
			</div>
			<div class="col-xs-2">
				<div id="charge05cost"></div>
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