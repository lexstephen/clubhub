<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="sidebar" class="row">
	<c:if test="${!isLoggedIn == true}">
		<%@ include file="/WEB-INF/login_form.jsp"%>
	</c:if>

	<%@ include file="/WEB-INF/sidebar_calendar.jsp"%>


</div>