<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>View Orders</title>
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
		<h1>Orders</h1>
		<table class='table table-bordered table-striped'>
			<tr>
				<th>Order Id</th>
				<th>Package Size</th>
				<th>Package Weight</th>
				<th>Destination</th>
				<th>Status</th>
			</tr>
			<c:if test="${empty orders }">
				<h3>You have no orders!</h3>
			</c:if>
			<c:forEach items="${orders }" var="order">
				<form action="#" method="post">
					<tr>
						<td>${order.order_id }</td>
						<td>${order.item_size }</td>
						<td>${order.item_weight }</td>
						<td>${order.destination.address }</td>
						<td>${order.status }</td>
						<td colspan="2" align="center">
							<button type="submit" name="order_id" value="${order.order_id }"
								class="btn btn-default">Change Detail</button>
						</td>
						<td colspan="2" align="center">
							<form action="#" method="post">
								<button type="submit" name="order_id" value="${order.order_id }"
									class="btn btn-default">Delete</button>
							</form>
						</td>
					</tr>
				</form>
			</c:forEach>
		</table>
		<div id='address'>
			<form action="CustomerViewAddresses" method="post">
				<button type="submit" name="user_id" value="${user_id }"
					class="btn btn-default">View Your History Target Addresses</button>
			</form>
		</div>
		<div id='add'>
			<form action="#" method="post">
				<button type="submit" name="user_id" value="${user_id }"
					class="btn btn-default">Add New Order</button>
			</form>
		</div>
	</div>
</body>
</html>
