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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<% scheme.listAllColourSchemes(request); %>
	<script type="text/javascript">
	var Schemes = new Array();
	
	<c:forEach items="${colour_schemes}" var="colour_scheme" varStatus="status"> 
		SchemeObj = new Object();
		SchemeObj.id = ${colour_scheme.id}; 
		SchemeObj.name = "${colour_scheme.name}";
		SchemeObj.dark_colour = "${colour_scheme.dark_colour}";
		SchemeObj.med_colour = "${colour_scheme.med_colour}";
		SchemeObj.light_colour = "${colour_scheme.light_colour}";
		SchemeObj.text_colour = "${colour_scheme.text_colour}"; 
		Schemes.push(SchemeObj);
	</c:forEach> 
	SchemeObj = new Object();
	SchemeObj.id = "000"; 
	SchemeObj.name = "--Add New--";
	SchemeObj.dark_colour = "";
	SchemeObj.med_colour = "";
	SchemeObj.light_colour = "";
	SchemeObj.text_colour = ""; 
	Schemes.push(SchemeObj);
</script>


<div class="row">
	<div class="col-xs-12">
		<p>Use this section to customize your frontend colour scheme options.</p>
		<p>Once entered, default colour scheme can be set in the <a href="Preferences.jsp">Preferences</a> page.</p>
		<div class="row">
			<div class="col-sm-7 col-sm-offset-5">
				<select name="id" class="form-control" id="inptSchemeID">
			 		<option value="000" ${colour_scheme.name == '--Add New--' ? 'selected' : ''}>--Add New--</option>
					<c:forEach items="${colour_schemes}" var="colour_scheme">
				 		<option value="${colour_scheme.id}" ${colour_schemeID == colour_scheme.id ? 'selected' : ''}>${colour_scheme.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<hr>
	</div>
</div>

<form action="/clubhub/ColourSchemeController" method="post" class="form" role="form">
<div class="form-group ColourSchemes">


	<div class="row">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Colour Scheme Name
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="name" id="schemeName">
		</div>	
	</div>
	
	<div class="row">
		<div class="col-xs-7 col-sm-offset-5 control-label">
			<p>Please enter 3 or 6 digit hexadecimal codes <i>without</i> the octothrope (#) for the following colour fields:</p>
		</div>
	</div>
	
	<div class="row">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Dark Colour
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="dark_colour" id="schemeDark_colour" value="">
			<p class="help-block">Dark Colour is used for navbar, page headings and links.</p>
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Medium Colour
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="med_colour" id="schemeMed_colour" value="">
			<p class="help-block">Medium Colour is used as a background for hovered links in the navbar.</p>
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Light Colour
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="light_colour" id="schemeLight_colour" value="">
			<p class="help-block">Light Colour is used as page background.</p>
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 col-sm-offset-2 control-label form-group">
			Text Colour
		</label>
		<div class="col-sm-7 form-group">
			<input class="form-control" type="text" name="text_colour" id="schemeText_colour" value="">
			<p class="help-block">Text Colour is used for all text except headers.</p>
		</div>	
	</div>
	
	
	<div class="row">
		<div class="col-xs-12 form-group">
			<input type="hidden" name="option" value="add">
			<button class="btn btn-info" type="submit" value="add" name="option">Submit</button>
		</div>
	</div>			
</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>