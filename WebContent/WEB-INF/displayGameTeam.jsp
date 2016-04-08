<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: Feb 03, 2016
	Description: displayGameTeam.jsp - HTML formatting for Games View
 --%>
 
<tr>
	<td class="col-md-4 col-xs-12">
    	<a href="${pageContext.request.contextPath}/Profile.jsp?userID=${tm.userid }"><img src="${pageContext.request.contextPath}/ImageDao?t=profile&id=${tm.userid }" class="game_photo"></a>
  		</td>
	<td class="col-md-4 col-xs-12">
	
	${tm.firstName } ${tm.lastName }

	<c:forEach items="${backupUsers}" var="backup">
		${backup.firstName }	
	</c:forEach>		
	</td>
</tr>
