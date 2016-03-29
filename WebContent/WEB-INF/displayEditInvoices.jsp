<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayInvoices.jsp - HTML formatting for BatchInvoices.jsp
 --%>
     
<tr>
 	<td class="col-xs-12 col-md-1 checkbox">
	 	<c:if test="${(isAdmin == true)}">
	 		<input type="checkbox" name="invoiceSelected" value="${invoice.id}">
 		</c:if>
	</td>
	<td class="col-xs-12 col-md-3 control-label">${invoice.id }</td>
	<td class="col-xs-12 col-md-2">${invoice.invDate }</td>
	<td class="col-xs-12 col-md-2">${invoice.firstName } ${invoice.lastName }</td>
	<td class="col-xs-12 col-md-2">${invoice.status }</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">
			<c:choose>
				<c:when test="${isAdmin == true}"><a href="${pageContext.request.contextPath}/admin/EditInvoice.jsp?invoiceID=${invoice.id}" class="btn btn-primary btn-xs">View / Edit</a></c:when>
				<c:otherwise><a href="${pageContext.request.contextPath}/admin/Invoice.jsp?invoiceID=${invoice.id}" class="btn btn-primary btn-xs">View</a></c:otherwise>
			</c:choose>
		</span>
	</td>
</tr>