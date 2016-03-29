<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>
 
<% request.setAttribute("thisPage", "View / Update Colour Schemes"); %>

<%@ page import="utilities.ColourSchemeDao"%>
<% ColourSchemeDao scheme = new ColourSchemeDao(); %>
<% scheme.listAllColourSchemes(request); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

	<script type="text/javascript">
	var Schemes = new Array();
	
	<c:forEach items="${colour_schemes}" var="colour_scheme" varStatus="status"> 
		SchemeObj = new Object();
		SchemeObj.csid = ${colour_scheme.id}; 
		SchemeObj.name = "${colour_scheme.name}";
		SchemeObj.dark_colour = "${colour_scheme.dark_colour}";
		SchemeObj.med_colour = "${colour_scheme.med_colour}";
		SchemeObj.light_colour = "${colour_scheme.light_colour}";
		SchemeObj.text_colour = "${colour_scheme.text_colour}"; 
		Schemes.push(SchemeObj);
	</c:forEach> 
	SchemeObj = new Object();
	SchemeObj.csid = "000"; 
	SchemeObj.name = "--Add New--";
	SchemeObj.dark_colour = "";
	SchemeObj.med_colour = "";
	SchemeObj.light_colour = "";
	SchemeObj.text_colour = ""; 
	Schemes.push(SchemeObj);
</script>

<form action="${pageContext.request.contextPath}/ColourSchemeController" method="post" class="form" role="form">

<div class="row">
	<div class="col-xs-12">
		<p>Use this section to customize your frontend colour scheme options. Once entered, default colour scheme can be set in the <a href="Preferences.jsp">Preferences</a> page.</p>
		<p>Edit a pre-existing colour scheme by selecting it from the drop down list, or select 'Add New' to enter a new one.</p>
		<div class="row">
			<div class="col-sm-7 col-sm-offset-5">
				<select name="csid" class="form-control" id="inptSchemeID">
			 		<option value="000" ${colour_scheme.name == '--Add New--' ? 'selected' : ''}>--Add New--</option>
					<c:forEach items="${colour_schemes}" var="colour_scheme">
				 		<option value="${colour_scheme.id}" ${csid == colour_scheme.id ? 'selected' : ''}>${colour_scheme.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<hr>
	</div>
</div>

<div class="form-group ColourSchemes">
	<c:if test="${!empty errorString}">
		<div class="alert alert-danger" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Error:</span>
		  ${errorString }
		</div>
	</c:if>

	<div class="row <c:if test="${!empty errorCSName}">has-error</c:if>">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Colour Scheme Name
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="name" id="schemeName" value="${csname}">
		</div>	
	</div>
	
	<div class="row">
		<div class="col-xs-7 col-sm-offset-5 control-label">
			<p>Please enter 3 or 6 digit hexadecimal codes <i>without</i> the octothrope (#) for the following colour fields:</p>
		</div>
	</div>
	
	<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Dark Colour
			<p class="help-block">Dark Colour is used for navbar, page headings and links.</p>
		</label>
		<div class="col-sm-7 form-group preview_colour" id="preview_Dark_colour">
			<div class="row">
				<div class="col-sm-6">
					<input class="form-control hex_code" type="color" name="dark_colour" id="schemeDark_colour" value="${dark_colour }">
				</div>
				<div class="col-sm-6">
				&nbsp;
				</div>
			</div>
			
		</div>	
		<div class="col-sm-2">&nbsp;</div>
	</div>
	
	<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Medium Colour
			<p class="help-block">Used as a background for hovered links in the navbar.</p>
		</label>
		<div class="col-sm-7 form-group preview_colour" id="preview_Med_colour">
			<div class="row">
				<div class="col-sm-6">
					<input class="form-control hex_code" type="color" name="med_colour" id="schemeMed_colour" value="${med_colour }">
				</div>
				<div class="col-sm-6">
				&nbsp;
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Light Colour
			<p class="help-block">Used as page background.</p>
		</label>
		<div class="col-sm-7 form-group preview_colour" id="preview_Light_colour">
			<div class="row">
				<div class="col-sm-6">
					<input class="form-control hex_code" type="color" name="light_colour" id="schemeLight_colour" value="${light_colour }">
				</div>
				<div class="col-sm-6">
				&nbsp;
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Text Colour
			<p class="help-block">Used for all text except headers.</p>
		</label>
		<div class="col-sm-7 form-group preview_colour" id="preview_Text_colour">
			<div class="row">
				<div class="col-sm-6">
					<input class="form-control hex_code" type="color" name="text_colour" id="schemeText_colour" value="${text_colour }">
				</div>
				<div class="col-sm-6">
				&nbsp;
				</div>
			</div>
		</div>	
	</div>
	
	
	<div class="row">
		<div class="col-xs-12 form-group">
			<input type="hidden" name="option" value="add">
			<button class="btn btn-info" type="submit">Submit</button>
		</div>
	</div>			
</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>