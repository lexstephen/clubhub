
<c:if test="${(isAdmin == true)}">
	<fieldset>
		<legend>Posts</legend>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/admin/BatchPosts.jsp">List
					All</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin/AddPost.jsp">Add
					New</a></li>
		</ul>
	</fieldset>
</c:if>

<fieldset>
	<legend>Users</legend>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/BatchUsers.jsp">List
				All</a></li>
		<c:if test="${isLoggedIn == true}">
			<li><a
				href="${pageContext.request.contextPath}/admin/EditProfile.jsp?userID=${loggedInUserID }">Edit
					Profile</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin/Availability.jsp">Availability</a></li>
		</c:if>
		<c:if test="${(isAdmin == true)}">
			<li><a href="${pageContext.request.contextPath}/Register.jsp">Add
					New</a></li>
		</c:if>
	</ul>
</fieldset>

<fieldset>
	<legend>Invoices</legend>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/BatchInvoices.jsp">List
				All</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a
				href="${pageContext.request.contextPath}/admin/AddInvoice.jsp">Add
					Invoice</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin/AddLineItems.jsp">Add
					Item</a></li>
		</c:if>
	</ul>
</fieldset>

<fieldset>
	<legend>Seasons</legend>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/DisplayGames.jsp">List
				All</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a
				href="${pageContext.request.contextPath}/admin/CreateSeason.jsp">Add
					New</a></li>
		</c:if>
	</ul>
</fieldset>


<fieldset>
	<legend>Settings</legend>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/UserGuide.jsp">User
				Guide</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a
				href="${pageContext.request.contextPath}/admin/ColourSchemes.jsp">Colour
					Scheme</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin/Preferences.jsp">Preferences</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin/SetPreferences.jsp">Set
					Default</a></li>
		</c:if>
	</ul>
</fieldset>
