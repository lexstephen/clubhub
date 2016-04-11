<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>

<% request.setAttribute("thisPage", "Clubhub User Guide"); %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/header_backend.jsp"%>

<ul>
	<li><a href="#posts">Website Content</a></li>
	<li><a href="#invoices">Invoices</a></li>
	<li><a href="#users">User Management</a></li>
	<li><a href="#games">Season/Game Management</a></li>
	<li><a href="#settings">Settings</a></li>
	<li><a href="#profile">Profile</a></li>
</ul>

<h3><a name="posts">Posts</a></h3>
<ul>
<li><b>List all posts</b> On this page you are able to edit or delete a single post or multiple posts at once. You can edit the access level of the posts or the category it belongs to or even the type of post it is.</li>
<li>*Note: to add a web page to your website you will just need to create a post set the access level to public and post type to web content. This will create a new page for you and automatically add it to the navigation menu at the top of the page with the title of your post being the name of the tab and the content being the content of the page.</li>
<li><b>Add new</b> On this page you can create a new post or a page for your website by filling in the required information.</li>
</ul>

<h3><a name="invoices">Invoices</a></h3>
<ul>
<li><b>List all invoices</b> On this page you will be able to select and invoice and update the status or view, edit or even delete the selected invoice.</li>
<li><b>Add invoice </b> On this page you will be able to add a new invoice for the chosen user.</li>
<li><b>Add line item</b> On this page you will be able to add items and they will automatically added to the “Add Invoice” screen.</li>
</ul>

<h3><a name="users">Users</a></h3>
<ul>
<li><b>List all users</b> On this page you can select and update a users status or view their profile.</li>
<li><b>Edit profile</b> This page allows you to edit your own profile or edit another users profile.</li>
<li><b>Add new user</b> On this page you are able to register a user for the club.</li>
</ul>

<h3><a name="games">Games</a></h3>
<ul>
<li><b>Game availability</b> This page will display all the scheduled games that the current logged in user is eligible to participate in. You will be able to select the individual games that you are available to play.</li>
<li><b> View Schedule</b> On this page you will be able to view your upcoming as well as your recently completed games.</li>
<li><b>Add new season</b> On this page you will be able to add a new season. After you fill out the information needed to create the season you will be redirected to a page where the information you inputted will be shown to you once again and then you will be asked to confirm the above information. Once the season has been confirmed the user slots are populated for the availability screen and the individual games are set up.</li>
<li><b>Manage Season</b> On this page you can choose to “close” a season which will automatically populate the games with randomly chosen players which were available for the game. You may also choose to view a closed season in which case you will be able to send an email to the available users letting them know that there is an opening in the current game.</li>
<li><b>View outstanding conflicts</b> This page will display any games where a current user needs to be replaced.</li>
<li><b>List all games</b> On this page you will be able to view or edit a game from a list of all the upcoming games.</li>
</ul> 
 
 
<h3><a name="settings">Settings</a></h3> 
<ul>
<li><b>User guide</b> This is the current page. On this page you have already probably found all the information you will need.</li>
<li><b>Color scheme</b> On this page you will be able to create a custom color scheme to be used in the front end of the website or delete an existing color scheme.</li>
<li><b>Preferences</b> On this page you will be able to customize the site to a certain extent. You can  enter club specific information, add images such as logos, specify the tax rate to be used for invoice calculation and set your color scheme.</li>
<li><b>Set default</b> On this page you can specify which of the existing preferences you would like to be active on the website’s front end.</li>
</ul>
 
<h3><a name="profile">Profile</a></h3> 
<ul>
<li>On this page you will be able to view your profile or you may also use the dropdown menu in the top right corner to select another user's profile. If you are logged in as an administrator you will be able to view extra details. When logged in as a regular user the only information about other players available to you are their name, location and membership date. You can also view the statics for the seasons and games located below the user info section. When logged in as an administrator you will also be able to view any invoices assigned to the current user.</li>
</ul>

<%@ include file="/WEB-INF/footer_backend.jsp"%>