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
<% 
	pref.showAllPrefs(request,response);
	pref.showPrefs(request); 
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<form action="${pageContext.request.contextPath}/PreferenceController" method="post" class="form" role="form" enctype="multipart/form-data">
<div class="form-group preferences">

	<script type="text/javascript">
	var Prefs = new Array();
	var prefCnt = 0;

	PrefObj = new Object();
	PrefObj.id = prefCnt;
	PrefObj.prefid = "000"; 
	PrefObj.preference_name = "${preference_name}";
	PrefObj.club_name_long = "${club_name_long}";
	PrefObj.club_name_short = "${club_name_short}";
	PrefObj.colour_schemeid = "${csid}";
	PrefObj.telephone = "${country}";
	PrefObj.address = "${address}";
	PrefObj.city = "${city}";
	PrefObj.province = "${province}";
	PrefObj.country = "<c:choose><c:when test="${empty country}">Canada</c:when><c:otherwise>${country}</c:otherwise></c:choose>"	;
	PrefObj.postalcode = "${postalcode}";
	PrefObj.tax_rate = "${tax_rate}"; 
	PrefObj.image_logo_exists = "false";
	PrefObj.image_small_logo_exists = "false"; 
	Prefs.push(PrefObj);
	prefCnt++;
	
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
		PrefObj.telephone = "${preference.telephone}";
		PrefObj.address = "${preference.address}";
		PrefObj.city = "${preference.city}";
		PrefObj.province = "${preference.province}";
		PrefObj.country = "${preference.country}";
		PrefObj.postalcode = "${preference.postalcode}";
		PrefObj.tax_rate = "${preference.tax_rate}"; 
		PrefObj.status = "${preference.status}";  
		PrefObj.image_logo_exists = "${preference.image_logo}";  
		PrefObj.image_small_logo_exists = "${preference.image_small_logo}";    
		Prefs.push(PrefObj);
	</c:forEach> 
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
				<div class="col-sm-6">
					<div class="row">
						<label class="col-sm-4 control-label">
							Active Preference:
						</label>
						<div class="col-sm-8">
							 <em><span id="activePrefId" class="hidden">${preference.id }</span> ${preference.preference_name }</em> [<a href="#" id="loadPref">load</a>] [<a href="SetPreferences.jsp">change default</a>]
						</div>	
					</div>
				</div>
				<div class="col-sm-6">
					<select name="prefid" class="form-control" id="inptPrefID">
				 		<option value="000" ${preference.id == '000' ? 'selected' : ''}>-- Add New --</option>
						<c:forEach items="${prefs}" var="preference">
					 		<option value="${preference.id}" ${preferenceID == preference.id ? 'selected' : ''}>${preference.preference_name}</option>
						</c:forEach>
					</select>
				</div>	
			</div>
			<hr>
			<c:if test="${!empty errorString}">
				<div class="alert alert-danger" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
				  ${errorString }
				</div>
			</c:if>
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
	
	
	<div class="row <c:if test="${!empty errorTelephone}">has-error</c:if>">
		<label class="col-sm-3 control-label">
			Telephone
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="telephone" id="inpt_telephone" value="${telephone }">
		</div>	
	</div>
	
	<div class="row <c:if test="${!empty errorAddress}">has-error</c:if>">
		<label class="col-sm-3 control-label">
			Address
		</label>
		<div class="col-sm-9">
			<input class="form-control" type="text" name="address" id="inpt_address" value="${address }">
		</div>	
	</div>
	
	
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCity}">has-error</c:if>">
			    	<label for="inptCity">City</label>
			    	<input type="text" name="city" class="form-control" id="inptCity" value="${city}">
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorPostalCode}">has-error</c:if>">
			    	<label for="inptPostalCode" id="lblPostalCode" ${country == 'United States of America' ? ' class="hiddenest"' : ''}>
						Postal Code
					</label>
					<label for="inptProvince" id="lblZipCode" ${country == 'Canada' ? ' class="hiddenest"' : ''} ${country == null ? ' class="hiddenest"' : ''}>
						Zip Code
					</label>
			    	<input type="text" name="postalCode" class="form-control" id="inptPostalCode" value="${postalCode}">
			  	</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorCountry}">has-error</c:if>">
			    	<label for="inptCountry">Country</label>
					<select name="country" class="form-control" id="inptCountry">
					  <option ${country == 'Canada' ? 'selected' : ''}>Canada</option>
					  <option ${country == 'United States of America' ? 'selected' : ''}>United States of America</option>
					</select>
			  	</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group <c:if test="${!empty errorProvince}">has-error</c:if>">
					<label for="inptProvince" id="lblProvince" ${country == 'United States of America' ? ' class="hiddenest"' : ''}>
						Province
					</label>
					<label for="inptState" id="lblState" ${country == 'Canada' ? ' class="hiddenest"' : ''} ${country == null ? ' class="hiddenest"' : ''}>
						State
					</label>
					<select name="province" class="form-control ${country == 'United States of America' ? ' hiddenest' : ''}" id="inptProvince">
					  <option ${province == 'AB' ? 'selected' : ''}>AB</option>
					  <option ${province == 'BC' ? 'selected' : ''}>BC</option>
					  <option ${province == 'MB' ? 'selected' : ''}>MB</option>
					  <option ${province == 'NB' ? 'selected' : ''}>NB</option>
					  <option ${province == 'NL' ? 'selected' : ''}>NL</option>
					  <option ${province == 'NS' ? 'selected' : ''}>NS</option>
					  <option ${province == 'NT' ? 'selected' : ''}>NT</option>
					  <option ${province == 'NU' ? 'selected' : ''}>NU</option>
					  <option ${province == 'ON' ? 'selected' : ''}>ON</option>
					  <option ${province == 'PE' ? 'selected' : ''}>PE</option>
					  <option ${province == 'QC' ? 'selected' : ''}>QC</option>
					  <option ${province == 'SK' ? 'selected' : ''}>SK</option>
					  <option ${province == 'YT' ? 'selected' : ''}>YT</option>
					</select>
					<select name="state" class="form-control ${country == 'Canada' ? ' hiddenest' : ''} ${country == null ? ' hiddenest' : ''}" id="inptState">
					  <option ${province == 'AK' ? 'selected' : ''}>AK</option>
					  <option ${province == 'AL' ? 'selected' : ''}>AL</option>
					  <option ${province == 'AR' ? 'selected' : ''}>AR</option>
					  <option ${province == 'AZ' ? 'selected' : ''}>AZ</option>
					  <option ${province == 'CA' ? 'selected' : ''}>CA</option>
					  <option ${province == 'CO' ? 'selected' : ''}>CO</option>
					  <option ${province == 'CT' ? 'selected' : ''}>CT</option>
					  <option ${province == 'DC' ? 'selected' : ''}>DC</option>
					  <option ${province == 'DE' ? 'selected' : ''}>DE</option>
					  <option ${province == 'FL' ? 'selected' : ''}>FL</option>
					  <option ${province == 'GA' ? 'selected' : ''}>GA</option>
					  <option ${province == 'HI' ? 'selected' : ''}>HI</option>
					  <option ${province == 'IA' ? 'selected' : ''}>IA</option>
					  <option ${province == 'ID' ? 'selected' : ''}>ID</option>
					  <option ${province == 'IL' ? 'selected' : ''}>IL</option>
					  <option ${province == 'IN' ? 'selected' : ''}>IN</option>
					  <option ${province == 'KS' ? 'selected' : ''}>KS</option>
					  <option ${province == 'KY' ? 'selected' : ''}>KY</option>
					  <option ${province == 'LA' ? 'selected' : ''}>LA</option>
					  <option ${province == 'MA' ? 'selected' : ''}>MA</option>
					  <option ${province == 'MD' ? 'selected' : ''}>MD</option>		
					  <option ${province == 'ME' ? 'selected' : ''}>ME</option>
  					  <option ${province == 'MI' ? 'selected' : ''}>MI</option>
  					  <option ${province == 'MN' ? 'selected' : ''}>MN</option>
  					  <option ${province == 'MO' ? 'selected' : ''}>MO</option>
  					  <option ${province == 'MS' ? 'selected' : ''}>MS</option>
  					  <option ${province == 'MT' ? 'selected' : ''}>MT</option>
  					  <option ${province == 'NC' ? 'selected' : ''}>NC</option>
  					  <option ${province == 'ND' ? 'selected' : ''}>ND</option>
  					  <option ${province == 'NE' ? 'selected' : ''}>NE</option>
  					  <option ${province == 'NH' ? 'selected' : ''}>NH</option>
  					  <option ${province == 'NJ' ? 'selected' : ''}>NJ</option>
  					  <option ${province == 'NM' ? 'selected' : ''}>NM</option>
  					  <option ${province == 'NV' ? 'selected' : ''}>NV</option>
  					  <option ${province == 'NY' ? 'selected' : ''}>NY</option>
  					  <option ${province == 'OH' ? 'selected' : ''}>OH</option>
  					  <option ${province == 'OK' ? 'selected' : ''}>OK</option>
  					  <option ${province == 'OR' ? 'selected' : ''}>OR</option>
  					  <option ${province == 'PA' ? 'selected' : ''}>PA</option>
  					  <option ${province == 'RI' ? 'selected' : ''}>RI</option>
  					  <option ${province == 'SC' ? 'selected' : ''}>SC</option>
  					  <option ${province == 'SD' ? 'selected' : ''}>SD</option>
  					  <option ${province == 'TN' ? 'selected' : ''}>TN</option>
  					  <option ${province == 'TX' ? 'selected' : ''}>TX</option>
  					  <option ${province == 'UT' ? 'selected' : ''}>UT</option>
  					  <option ${province == 'VA' ? 'selected' : ''}>VA</option>
  					  <option ${province == 'VT' ? 'selected' : ''}>VT</option>
  					  <option ${province == 'WA' ? 'selected' : ''}>WA</option>
  					  <option ${province == 'WI' ? 'selected' : ''}>WI</option>
  					  <option ${province == 'WV' ? 'selected' : ''}>WV</option>
  					  <option ${province == 'WY' ? 'selected' : ''}>WY</option>
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