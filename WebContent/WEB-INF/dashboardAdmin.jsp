<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-xs-12 col-sm-5 col-sm-offset-1">
		<h3>Recent Posts</h3>
		<%@ include file="/WEB-INF/dashboardAdminListBlogs.jsp"%>
	</div>

	<div class="col-xs-12 col-sm-5 col-sm-offset-1">
		<h3>New Users</h3>
		<%@ include file="/WEB-INF/dashboardAdminListUsers.jsp"%>
	</div>
</div>

<div class="row">
	<div class="col-xs-12 col-sm-5 col-sm-offset-1">
		<h3>Scheduling Conflicts</h3>
		<%@ include file="/WEB-INF/dashboardAdminListUpcomingConflicts.jsp"%>
	</div>

	<div class="col-xs-12 col-sm-5 col-sm-offset-1">
		<h3>Recent Games</h3>
		<%@ include file="/WEB-INF/dashboardAdminListRecentGames.jsp"%>
	</div>
</div>
