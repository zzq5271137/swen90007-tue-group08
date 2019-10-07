<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>View Logs</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<link rel='stylesheet' href='style.css' />
<style>
#address {
	float: left
}

#add {
	float: right
}
</style>
</head>
<body>
	<div class='container'>
		<h1>Logs</h1>
		<table class='table table-bordered table-striped'>
			<tr>
				<th>Date of Log</th>
				<th>Count of Work</th>
			</tr>
			<c:if test="${empty myLogs }">
				<h3>You have no logs!</h3>
			</c:if>
			<c:forEach items="${myLogs }" var="log">
				<tr>
					<td>${log.date }</td>
					<td>${log.sent_count }</td>
					<td colspan="2" align="center">
						<form action="CourierDeleteLogController" method="post">
							<input type="hidden" name="user_id" value="${user_id }">
							<button type="submit" name="date" value="${log.date }"
								class="btn btn-default">Delete Log</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<form action="CourierShowDeliveringOrderController" method="post">
			<button type="submit" name="user_id" value="${user_id }"
				class="btn btn-default">Back To Your Delivering Order List</button>
		</form>
	</div>
</body>
</html>
