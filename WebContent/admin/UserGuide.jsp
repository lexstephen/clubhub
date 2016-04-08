<%-- 
	Project: ClubHub Content and User Management System 
	Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
	Student Number: 100563954, 100911472, 100898311
	Date: February 1, 2016
	Description: AddPost.jsp
 --%>

<% request.setAttribute("thisPage", "User Guide"); %>

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

<h3>Add Season</h3>
<p>Efficiently unleash cross-media information without cross-media
	value. Quickly maximize timely deliverables for real-time schemas.
	Dramatically maintain clicks-and-mortar solutions without functional
	solutions.</p>

<h3>Add/Edit Posts</h3>
<p>Completely synergize resource taxing relationships via premier
	niche markets. Professionally cultivate one-to-one customer service
	with robust ideas. Dynamically innovate resource-leveling customer
	service for state of the art customer service.</p>

<h3>Invoices</h3>
<p>Collaboratively administrate empowered markets via plug-and-play
	networks. Dynamically procrastinate B2C users after installed base
	benefits. Dramatically visualize customer directed convergence without
	revolutionary ROI.</p>

<%@ include file="/WEB-INF/footer_backend.jsp"%>