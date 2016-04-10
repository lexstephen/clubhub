<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayseasons.jsp - HTML formatting for Displayseasons.jsp
 --%>


<tr>
	<td class="col-md-1">${season.gender}</td>
	<td class="col-md-2">${season.season}</td>
	<td class="col-md-1">${season.year}</td>
	<td class="col-md-2">${season.dayOfWeek}</td>
	<td class="col-md-2" sorttable_customkey="${season.startDateFullYear}">${season.startDateFullYear}</td>
	<td class="col-md-4">
	<div class="row">
		<div class="col-md-4 col-xs-12">
				<a href="${pageContext.request.contextPath}/admin/DisplaySeason.jsp?seasonID=${season.id}" class="btn btn-info btn-sm">View Season</a>
		</div>	
		<div class="col-md-4 col-xs-12">
	<c:if test="${(isAdmin == true)}">
		<form action="${pageContext.request.contextPath}/UserController" method="post">
			<input type="hidden" name="seasonID" value="${season.id}"> 
			<input type="hidden" name="userID" value="${user.userid}"> 
			<input type="hidden" name="option" value="email"> 
			<input type="hidden" name="emailType" value="availability"> 
			<input class="btn btn-info btn-sm" type="submit" value="Notify Users About New Season">
		</form>
	</c:if> 
		</div>
	</div>
	

	</td>
</tr>

