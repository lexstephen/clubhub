<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>
 
<% request.setAttribute("thisPage", "Set Default Site Preferences"); %>
<%@ page import="utilities.ColourSchemeDao"%>
<% ColourSchemeDao scheme = new ColourSchemeDao(); %>
<% scheme.listAllColourSchemes(request); %>

<%@ page import="utilities.PreferenceDao"%>
<% PreferenceDao pref = new PreferenceDao(); %>
<% pref.showPrefs(request); %>
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
			<p>Use this section to set your site's default preferences.  Preferences can be <a href="${pageContext.request.contextPath}/admin/Preferences.jsp">edited here</a>.</p>
			${prefID  }
			<c:if test="${prefID == null}"><b>WARNING</b>: you must select a default preference for your site to function correctly!</c:if>
			<div class="row">
				<div class="col-sm-7 col-sm-offset-5">
					<div class="radio">
						<form action="${pageContext.request.contextPath}/PreferenceController" method="post" class="form" role="form" enctype="multipart/form-data">
							<c:forEach items="${prefs}" var="preference">
								<div class="row">
							    	<label class="checkbox-inline">
							      		<input type="radio" name="inptPrefID" id="inptPrefID" value="${preference.id}" ${prefID == preference.id ? 'checked' : ''}> ${preference.preference_name}
							      	</label>
					      		</div>	
							</c:forEach>
				 			<input type="hidden" name="option" value="setPref">
							<button class="btn btn-info" type="submit">Submit</button>
						</form>
					</div>
				</div>	
			</div>
			<hr>
		</div>
	</div>
</div>
</form>		
			 
<%@ include file="/WEB-INF/footer_backend.jsp" %>