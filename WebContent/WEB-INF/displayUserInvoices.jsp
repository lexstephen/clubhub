<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tr>
 	<td class="col-xs-12 col-md-1 ">
	 	<c:if test="${(isAdmin == true)}">
	 		<input type="checkbox" name="invoiceSelected" value="${invoice.id}">
 		</c:if>
	</td>
	<td class="col-xs-12 col-md-3 control-label">${invoice.id }</td>
	<td class="col-xs-12 col-md-2">${invoice.invDate }</td>
	<td class="col-xs-12 col-md-2">${invoice.status }</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">		
			<a href="${pageContext.request.contextPath}/admin/EditInvoice.jsp?invoiceID=${invoice.id}" class="btn btn-primary btn-xs">View</a>
		</span>
	</td>
</tr>