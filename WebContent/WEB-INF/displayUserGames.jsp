<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tr>
	<td class="col-xs-12 col-md-3 control-label">${game.seasonID }</td>
	<td class="col-xs-12 col-md-2"><a href="${pageContext.request.contextPath}/admin/Game.jsp?gameID=${game.gameID}">${game.gameID}</a></td>
	<td class="col-xs-12 col-md-2">${game.score}</td>
	<td class="col-xs-12 col-md-2">${game.team}</td>
	<td class="col-xs-12 col-md-2">${game.outcome}</td>
</tr>