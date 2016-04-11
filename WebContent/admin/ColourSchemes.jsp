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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>
	<c:if test="${sessionScope.isAdmin == false}">
		<c:redirect url="index.jsp" />
	</c:if>

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
	SchemeObj.dark_colour = "#000000";
	SchemeObj.med_colour = "#000000";
	SchemeObj.light_colour = "#000000";
	SchemeObj.text_colour = "#000000"; 
	Schemes.push(SchemeObj);
</script>

<form action="${pageContext.request.contextPath}/ColourSchemeController"
	method="post" class="form" role="form">

	<div class="row">
		<div class="col-xs-12">
			<p>
				Use this section to customize your frontend colour scheme options.
				Once entered, default colour scheme can be set in the <a
					href="${pageContext.request.contextPath}/admin/Preferences.jsp">Preferences</a> page.
			</p>
			<p>Edit a pre-existing colour scheme by selecting it from the
				drop down list, or select 'Add New' to enter a new one.</p>
			<div class="row">
				<div class="col-sm-7 col-sm-offset-5">
					<select name="csid" class="form-control" id="inptSchemeID">
						<option value="000"
							${colour_scheme.name == '--Add New--' ? 'selected' : ''}>--Add
							New--</option>
						<c:forEach items="${colour_schemes}" var="colour_scheme">
							<option value="${colour_scheme.id}"
								${csid == colour_scheme.id ? 'selected' : ''}>${colour_scheme.name}</option>
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
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span>
				${errorString }
			</div>
		</c:if>

		<div class="row <c:if test="${!empty errorCSName}">has-error</c:if>">
			<label class="col-md-5 col-xs-12 control-label form-group">
				Colour Scheme Name </label>
			<div class="col-md-7 col-xs-12 form-group">
				<input class="form-control" type="text" name="name" id="schemeName"
					value="${csname}">
			</div>
		</div>

		<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
			<label class="col-md-5 col-xs-12 form-group text-right"> Dark
				Colour <br> <small>used for navigation bar, content
					highlight, and links.</small>
			</label>
			<div class="col-md-7 col-xs-12 form-group preview_colour"
				id="preview_Dark_colour">
				<div class="row">
					<div class="col-sm-6">
						<input class="form-control hex_code" type="color"
							name="dark_colour" id="schemeDark_colour"
							value="<c:choose><c:when test="${empty dark_colour }">#000000</c:when><c:otherwise>${dark_colour }</c:otherwise></c:choose>">
					</div>
					<div class="col-sm-6">&nbsp;</div>
				</div>
			</div>
		</div>

		<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
			<label class="col-md-5 col-xs-12 control-label form-group text-right">
				Medium Colour <br> <small>Navigation bar highlight
					element and page headings.</small>
			</label>
			<div class="col-md-7 col-xs-12 form-group preview_colour"
				id="preview_Med_colour">
				<div class="row">
					<div class="col-sm-6">
						<input class="form-control hex_code" type="color"
							name="med_colour" id="schemeMed_colour"
							value="<c:choose><c:when test="${empty med_colour }">#ff0000</c:when><c:otherwise>${med_colour }</c:otherwise></c:choose>">
					</div>
					<div class="col-sm-6">&nbsp;</div>
				</div>
			</div>
		</div>

		<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
			<label class="col-md-5 col-xs-12 control-label form-group text-right">
				Light Colour <br> <small>Content and sidebar
					background.</small>
			</label>
			<div class="col-md-7 col-xs-12 form-group preview_colour"
				id="preview_Light_colour">
				<div class="row">
					<div class="col-sm-6">
						<input class="form-control hex_code" type="color"
							name="light_colour" id="schemeLight_colour"
							value="<c:choose><c:when test="${empty light_colour }">#ffffff</c:when><c:otherwise>${light_colour }</c:otherwise></c:choose>">
					</div>
					<div class="col-sm-6">&nbsp;</div>
				</div>
			</div>
		</div>

		<div class="row <c:if test="${!empty errorCSColour}">has-error</c:if>">
			<label class="col-md-5 col-xs-12 control-label form-group text-right">
				Text Colour <br> <small>Used for all text except
					headers.</small>
			</label>
			<div class="col-md-7 col-xs-12 form-group preview_colour"
				id="preview_Text_colour">
				<div class="row">
					<div class="col-sm-6">
						<input class="form-control hex_code" type="color"
							name="text_colour" id="schemeText_colour"
							value="<c:choose><c:when test="${empty text_colour }">#000000</c:when><c:otherwise>${text_colour }</c:otherwise></c:choose>">
					</div>
					<div class="col-sm-6">&nbsp;</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-xs-1 form-group">
				<input type="hidden" name="option" value="add">
				<button class="btn btn-info" type="submit">Submit</button>
</form>
</div>
<div class="col-xs-11 form-group">
	<form
		action="${pageContext.request.contextPath}/ColourSchemeController"
		method="post">
		<input id="inpt_delete" type="hidden" name="csID" value="${csid}">
		<input type="hidden" name="option" value="delete"> <input
			class="btn btn-danger" type="submit" value="Delete"> <label>Warning!
			Deletion cannot be undone.</label>
	</form>
</div>
</div>
</div>



<%@ include file="/WEB-INF/footer_backend.jsp"%>