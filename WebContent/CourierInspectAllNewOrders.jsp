<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Inspect New Orders</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<link rel='stylesheet' href='style.css' />
<style>
#logout{
	display: inline-block;
	float: right;
	margin-top:20px;
	margin-bottom:10px
}
h1 {
	display: inline-block;
}

#back {
	float: left
}
</style>
</head>
<body>
	<div class='container'>
		<h1>New Orders</h1>
		<form action="logout" method="post" id='logout'>
			<button class="btn btn-info btn-lg">Log out</button>
		</form>
		<table class='table table-bordered table-striped'>
			<tr>
				<th>Order Id</th>
				<th>Package Size</th>
				<th>Package Weight</th>
				<th>Destination</th>
				<th>Status</th>
			</tr>
			<c:if test="${empty orders }">
				<h3>No new orders for now!</h3>
			</c:if>
			<c:forEach items="${orders }" var="order">
				<tr>
					<td>${order.order_id }</td>
					<td>${order.item.item_size }</td>
					<td>${order.item.item_weight }</td>
					<td>${order.destination.address }</td>
					<td>${order.status }</td>
					<td colspan="2" align="center">
						<form action="CourierConfirmPickOrderController" method="post">
							<input type="hidden" name="user_id" value="${user_id }">
							<button type="submit" name="order_id" value="${order.order_id }"
								class="btn btn-default">Pick</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id='back'>
			<form action="CourierShowDeliveringOrderController" method="post">
				<button type="submit" name="user_id" value="${user_id }"
					class="btn btn-default">Back To Your Delivering Order List</button>
			</form>
		</div>
	</div>
</body>
</html>
