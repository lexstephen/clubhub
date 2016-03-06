<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayInvoices.jsp - HTML formatting for Main.jsp
 --%>
     
<tr>
 	<td class="col-xs-12 col-md-1 checkbox"><input type="checkbox" name="invoiceSelected" value="${invoice.id}"></td>
	<td class="col-xs-12 col-md-3 control-label">${invoice.id }</td>
	<td class="col-xs-12 col-md-2">${invoice.invDate }</td>
	<td class="col-xs-12 col-md-2">${invoice.firstName } ${invoice.lastName }</td>
	<td class="col-xs-12 col-md-2">${invoice.status }</td>
	<td class="col-xs-12 col-md-2">
		<span class="expand">
			<a href="/clubhub/admin/Invoice.jsp?invoiceID=${invoice.id}" class="btn btn-primary btn-xs">Edit</a>
			<a href="/clubhub/admin/Invoice.jsp?invoiceID=${invoice.id}" class="btn btn-primary btn-xs">More</a>
		</span>
	</td>
</tr>