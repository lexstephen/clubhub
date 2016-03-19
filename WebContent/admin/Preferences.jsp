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
<% pref.showAllPrefs(request); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<p>Use this section to customize your ClubHub installation to suit your club.</p>
<form action="/clubhub/PreferenceController" method="post" class="form" role="form" enctype="multipart/form-data">
<div class="form-group preferences">

	<script type="text/javascript">
	var prefs = new Array();
	
	<c:forEach items="${prefs}" var="preference" varStatus="status"> 
		PrefObj = new Object();
		PrefObj.id = "${preference.id}"; 
		PrefObj.preference_name = "${preference.preference_name}";
		PrefObj.club_name_long = "${preference.club_name_long}";
		PrefObj.club_name_short = "${preference.club_name_short}";
		PrefObj.colour_schemeid = "${preference.colour_schemeid}";
		PrefObj.tax_rate = "${preference.tax_rate}"; 
		PrefObj.country = "${preference.country}";
		PrefObj.status = "${preference.status}";  
		prefs.push(PrefObj);
	</c:forEach> 
	PrefObj = new Object();
	PrefObj.id = "000"; 
	PrefObj.name = "--Add New--";
	prefs.push(PrefObj);
</script>

	<div class="row">
		<label class="col-sm-3 control-label">
		 
		</label>
		<div class="col-sm-9">
			<select name="prefid" class="form-control" id="inptPrefID">
		 		<option value="000" ${preference.preference_name == '--Add New--' ? 'selected' : ''}>--Add New--</option>
				<c:forEach items="${prefs}" var="preference">
			 		<option value="${preference.id}" ${preferenceID == preference.id ? 'selected' : ''}>${preference.preference_name}</option>
				</c:forEach>
			</select>
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 control-label">
			Name these settings:
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="preference_name" id="inpt_preference_name" value="">
		</div>	
	</div>
	
	<div class="row">
		<h3>Club Settings</h3>
		<label class="col-sm-3 control-label">
			Club Name (Long)
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="club_name_long" id="inpt_club_name_long" value="">
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 control-label">
			Club Name (Short)
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="club_name_short" id="inpt_club_name_short" value="">
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 control-label">
			Country
		</label>
		<div class="col-sm-9">
			<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
				<select name="country" class="form-control" id="inptCountry">
				  <option ${preference.country == 'Canada' ? 'selected' : ''}>Canada</option>
				  <option ${preference.country == 'United States of America' ? 'selected' : ''}>United States of America</option>
				</select>
		  	</div>
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-3 control-label">
			Tax Rate
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="tax_rate" placeholder="0.14">
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
						<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
						<select name="colour_schemeid" class="form-control" id="inptSchemeID">
							<c:forEach items="${colour_schemes}" var="colour_scheme">
						 		<option value="${colour_scheme.id}" ${colour_schemeid == colour_scheme.id ? 'selected' : ''}>${colour_scheme.name}</option>
							</c:forEach>
						</select>
					  	</div>
				</div>
				<!--  
				<label class="col-sm-4 control-label">
					Customized Colour Scheme
				</label>
				<div class="col-sm-8">
					<div class="row">
						<div class="col-sm-12">
							<input class="form-control" type="text" name="name" placeholder="Name your colour scheme">
						</div>
					</div>
					<div class="row">
						<div class="col-sm-1">
							Dark:
						</div>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="dark_colour" placeholder="#000000">
						</div>	
						<div class="col-sm-1">
							Medium:
						</div>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="med_colour" placeholder="#444444">
						</div>	
						<div class="col-sm-1">
							Light:
						</div>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="light_colour" placeholder="#FFFFFF">
						</div>	
					</div>
				</div>	-->
			</div>
		</div>
	</div>
	
	<div class="row">
	<h3>Images</h3>
		<label class="col-sm-4 control-label">
			Website Logo
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="image_logo">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Small Website Logo
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="image_small_logo">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 01
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_01">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 02
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_02">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 03
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_03">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 04
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_04">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 05
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_05">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 06
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_06">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 07
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_07">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 08
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_08">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 09
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_09">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
	<div class="row">
		<label class="col-sm-4 control-label">
			Featured Image 10
		</label>
		<div class="col-sm-6">
			<input class="form-control" type="file" name="featured_image_10">
		</div>	
		<div class="col-sm-2">
			<!-- <img src="/clubhub/images/rcc.png"> -->
		</div>	
	</div>
	
			<div class="row">
			<div class="col-xs-3 control-label">
			    	<label for="inptStatus">Status</label>			  
			</div>
			<div class="col-xs-9">
				<div class="radio">
			    	<label class="checkbox-inline">
			      		<input type="radio" name="status" id="inptStatusActive" checked value="1" ${status == '1' ? 'checked' : ''}> Active
			      	</label>
			    	<label class="checkbox-inline">
			      		<input type="radio" name="status" id="inptStatusInactive" value="0"  ${status == '0' ? 'checked' : ''}> Inactive
			      	</label>
			  	</div>
			</div>
		</div>

					<input type="hidden" name="option" value="add">
					<button class="btn btn-info" type="submit" value="add" name="option">Submit</button>
</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>