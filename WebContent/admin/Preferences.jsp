<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>
 
<% request.setAttribute("thisPage", "Site Preferences"); %>
<%@ page import="utilities.ColourSchemeDao"%>
<% ColourSchemeDao scheme = new ColourSchemeDao(); %>
<% scheme.listAllColourSchemes(request); %>

<%@ page import="utilities.PreferenceDao"%>
<% PreferenceDao pref = new PreferenceDao(); %>
<% pref.showAllPrefs(request,response); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<form action="${pageContext.request.contextPath}/PreferenceController" method="post" class="form" role="form" enctype="multipart/form-data">
<div class="form-group preferences">

	<script type="text/javascript">
	var Prefs = new Array();
	var prefCnt = 0;
	<c:forEach items="${prefs}" var="preference" varStatus="status"> 
		PrefObj = new Object();
		PrefObj.id = prefCnt;
		prefCnt++;
		console.log( "Preference count is " + prefCnt);
		PrefObj.prefid = "${preference.id}"; 
		PrefObj.preference_name = "${preference.preference_name}";
		PrefObj.club_name_long = "${preference.club_name_long}";
		PrefObj.club_name_short = "${preference.club_name_short}";
		PrefObj.colour_schemeid = "${preference.colour_schemeid}";
		PrefObj.tax_rate = "${preference.tax_rate}"; 
		PrefObj.country = "${preference.country}";
		PrefObj.status = "${preference.status}";  
		PrefObj.image_logo_exists = "true";  
		PrefObj.image_small_logo_exists = "true";    
		Prefs.push(PrefObj);
	</c:forEach> 
	PrefObj = new Object();
	PrefObj.id = prefCnt;
	prefCnt++;
	PrefObj.prefid = "000"; 
	PrefObj.preference_name = "";
	PrefObj.club_name_long = "";
	PrefObj.club_name_short = "";
	PrefObj.colour_schemeid = "8";
	PrefObj.tax_rate = ""; 
	PrefObj.country = "";
	PrefObj.status = "";   
	PrefObj.image_logo_exists = "false";
	PrefObj.image_small_logo_exists = "false";
	Prefs.push(PrefObj);
</script>

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
	<div class="row">
		<div class="col-sm-12">
			<p>Use this section to customize your ClubHub installation to suit your club.</p>
			<p>Edit a pre-existing Preference by selecting it from the drop down list, or select 'Add New' to enter a new one.</p>
		
			<div class="row">
				<div class="col-sm-7 col-sm-offset-5">
					<select name="prefid" class="form-control" id="inptPrefID">
				 		<option value="000" ${preference.id == '000' ? 'selected' : ''}>-- Add New --</option>
						<c:forEach items="${prefs}" var="preference">
					 		<option value="${preference.id}" ${preferenceID == preference.id ? 'selected' : ''}>${preference.preference_name}</option>
						</c:forEach>
					</select>
				</div>	
			</div>
			<hr>
		</div>
	</div>
	
	<div class="row <c:if test="${!empty errorPreference_Name}">has-error</c:if>">
		<label class="col-sm-3 control-label">
			Name these settings:
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="preference_name" id="inpt_preference_name" value="${preference_name}">
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorClub_Name_Long}">has-error</c:if>">
		<h3>Club Settings</h3>
		<label class="col-sm-3 control-label">
			Club Name (Long)
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="club_name_long" id="inpt_club_name_long" value="${club_name_long }">
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorClub_Name_Short}">has-error</c:if>">
		<label class="col-sm-3 control-label">
			Club Name (Short)
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="club_name_short" id="inpt_club_name_short" value="${club_name_short }">
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 control-label">
			Country
		</label>
		<div class="col-sm-9">
			<div class="form-group">
				<select name="country" class="form-control" id="inptCountry">
				  <option ${preference.country == 'Canada' ? 'selected' : ''}>Canada</option>
				  <option ${preference.country == 'United States of America' ? 'selected' : ''}>United States of America</option>
				</select>
		  	</div>
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorTax_Rate}">has-error</c:if>">
		<label class="col-sm-3 control-label">
			Tax Rate
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="tax_rate" id="tax_rate" value="${tax_rate }">
		</div>	
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<h3>Colour Scheme</h3>
			<p>Choose from a predefined scheme, or enter your own.</p>
			
			<div class="row">
				<label class="col-sm-4 control-label">
					Colour Scheme
				</label>
				<div class="col-sm-8">
						<div class="form-group">
						<select name="colour_schemeid" class="form-control" id="inptSchemeID">
							<c:forEach items="${colour_schemes}" var="colour_scheme">
						 		<option value="${colour_scheme.id}" ${csid == colour_scheme.id ? 'selected' : ''}>${colour_scheme.name}</option>
							</c:forEach>
						</select>
					  	</div>
					  	<div class="row">
					  		<div class="col-xs-3" id="preview_Dark_colour">&nbsp;</div>
					  		<div class="col-xs-3" id="preview_Med_colour">&nbsp;</div>
					  		<div class="col-xs-3" id="preview_Light_colour">&nbsp;</div>
					  		<div class="col-xs-3" id="preview_Text_colour">&nbsp;</div>
					  	</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
	<h3>Images</h3>
		<label class="col-sm-4 control-label">
			Website Logo
		</label>
		<div class="col-sm-6">
			<input class="form-control file" type="file" name="image_logo" id="image_logo">
		</div>	
		<div class="col-sm-2 prev_image" id="display_image_logo">&nbsp;</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Small Website Logo
		</label>
		<div class="col-sm-6">
			<input class="form-control file" type="file" name="image_small_logo" id="image_small_logo">
		</div>	
		<div class="col-sm-2 prev_image" id="display_image_small_logo">&nbsp;</div>	
	</div>
	
	
			<!--  	<input type="hidden" name="option" value="add"> -->
					<button class="btn btn-info" type="submit">Submit</button>
</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>