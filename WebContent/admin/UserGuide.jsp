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
	<li><a href="#addSeason">Add Season</a></li>
	<li><a href="#addPost">Add/Edit Posts</a></li>
	<li><a href="#invoice">Invoices</a></li>
	<li><a href="#">#</a></li>
	<li><a href="#">#</a></li>
	<li><a href="#">#</a></li>
</ul>

<h3>Users</h3>

<b>List all users</b> - On this page you can select and update a users status or view their profile.
<b>Edit profile</b> - This page allows you to edit your own profile or edit another users profile.
<b>Add new user</b> - On this page you are able to register a user for the clubs invoices.
<b>List all invoices</b> - On this page you will be able to select and invoice and update the status or view, edit or even delete the selected invoice.
<b>Add invoice </b> - On this page you will be able to add a new invoice for the chosen user.
<b>Add line item</b> - On this page you will be able to add items and they will automatically added to the “Add Invoice” screen.
 
<h3>Games</h3>

<b>Game availability</b> - This page will display all the scheduled games that the current logged in user is eligible to participate in. You will be able to select the individual games that you are available to play.
<b> View Schedule</b> - On this page you will be able to view your upcoming as well as your recently completed games.
<b>Add new season</b> - On this page you will be able to add a new season. After you fill out the information needed to create the season you will be redirected to a page where the information you inputted will be shown to you once again and then you will be asked to confirm the above information. Once the season has been confirmed the user slots are populated for the availability screen and the individual games are set up.
<b>Manage Season</b> - On this page you can choose to “close” a season which will automatically populate the games with randomly chosen players which were available for the game. You may also choose to view a closed season in which case you will be able to send an email to the available users letting them know that there is an opening in the current game.
<b>View outstanding conflicts</b> - This page will display any games where a current user needs to be replaced.
<b>List all games</b> - On this page you will be able to view or edit a game from a list of all the upcoming games.
 
 
 
<h3>Settings</h3> 

<b>User guide</b> - This is the current page. On this page you have already probably found all the information you will need.

<b>Color scheme</b> - On this page you will be able to create a custom color scheme to be used in the front end of the website or delete an existing color scheme.

<b>Preferences</b> -On this page you will be able to customize the site to a certain extent. You can  enter club specific information, add images such as logos, specify the tax rate to be used for invoice calculation and set your color scheme.
 
<b>Set default</b> -On this page you can specify which of the existing preferences you would like to be active on the website’s front end.
 
<h3>Profile</h3> 

On this page you will be able to view your profile or you may also use the dropdown menu in the top right corner to select another user's profile.
If you are logged in as an administrator you will be able to view extra details. When logged in as a regular user the only information about other players available to you are their name, location and membership date.
You can also view the statics for the seasons and games located below the user info section. When logged in as an administrator you will also be able to view any invoices assigned to the current user.

<%@ include file="/WEB-INF/footer_backend.jsp"%>