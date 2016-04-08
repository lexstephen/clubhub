
<c:if test="${(isAdmin == true)}">
	<fieldset>
		<legend>Posts</legend>
		<ul>
			<li><a href="/clubhub/admin/BatchPosts.jsp">List All</a></li>
			<li><a href="/clubhub/admin/AddPost.jsp">Add New</a></li>
		</ul>
	</fieldset>
</c:if>

<fieldset>
	<legend>Users</legend>
	<ul>
		<li><a href="/clubhub/admin/BatchUsers.jsp">List All</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a href="/clubhub/Register.jsp">Add New</a></li>
		</c:if>
	</ul>
</fieldset>

<fieldset>
	<legend>Invoices</legend>
	<ul>
		<li><a href="/clubhub/admin/BatchInvoices.jsp">List All</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a href="/clubhub/admin/AddInvoice.jsp">Add Invoice</a></li>
			<li><a href="/clubhub/admin/AddLineItems.jsp">Add Item</a></li>
		</c:if>
	</ul>
</fieldset>

<fieldset>
	<legend>Seasons</legend>
	<ul>
		<li><a href="/clubhub/admin/DisplayGames.jsp">List All</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a href="/clubhub/admin/CreateSeason.jsp">Add New</a></li>
		</c:if>
	</ul>
</fieldset>


<fieldset>
	<legend>Settings</legend>
	<ul>
		<li><a href="/clubhub/admin/UserGuide.jsp">User Guide</a></li>
		<c:if test="${(isAdmin == true)}">
			<li><a href="/clubhub/admin/ColourSchemes.jsp">Colour Scheme</a></li>
			<li><a href="/clubhub/admin/Preferences.jsp">Preferences</a></li>
		</c:if>
	</ul>
</fieldset>
