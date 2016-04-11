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
	<td class="col-md-2">${season.year}</td>
	<td class="col-md-2">${season.dayOfWeek}</td>
	<td class="col-md-2" sorttable_customkey="${season.startDateFullYear}">${season.startDateFullYear}</td>
	<td class="col-md-3">
			<c:if test="${(isAdmin == true)}">
				<form action="${pageContext.request.contextPath}/SeasonController" method="post" class="form" role="form">
					<input type="hidden" name="seasonID" value="${season.id}">
					<input type="hidden" name="option" value="close">
					<button class="btn btn-warning btn-sm" type="submit">Close/Assign Players</button>
				</form>
			</c:if> 
	</td>
</tr>

