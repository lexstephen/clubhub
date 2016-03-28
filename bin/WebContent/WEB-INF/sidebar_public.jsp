<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="sidebar" class="row">
	<div class="col-sm-12">
		<img src="images/tempcurlingpicture.png" id="sidebarImage">
	</div>
	
	<c:if test="${!isLoggedIn == true}">
		<%@ include file="/WEB-INF/login_form.jsp"%>
	</c:if>
	
	<c:if test="${!isLoggedIn == true}">
		<%@ include file="/WEB-INF/logged_in_sidebar_calendar.jsp"%>
	</c:if>
	
	<c:if test="${!isLoggedIn == false}">
		<%@ include file="/WEB-INF/sidebar_calendar.jsp"%>
	</c:if>

</div>