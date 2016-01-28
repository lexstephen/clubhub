<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: January 24, 2016
	Description: Main.jsp
 --%>
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<div class="container backend">
	<div class="row">
		<div class="sidebar col-sm-2 col-xs-12">
			<%@ include file="/WEB-INF/sidebar_backend.jsp" %>
		</div>
		
		<div class="content col-sm-10 col-xs-12">
			<h2>Member Dashboard</h2>
			
			<span class="memberMeta"><img src="/clubhub/images/avatar.gif"> You are now logged in as this puppy</span>
			 

		</div>
	</div>
</div>
<%@ include file="/WEB-INF/footer_backend.jsp" %>