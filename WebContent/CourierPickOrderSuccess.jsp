<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick Order Success</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<style>
div#root {
	text-align: center;
}

#root a button {
	font-size: 20px;
	width: 400px;
}
</style>
</head>
<body>
	<div id="root">
		<h1>Pick Order Success!</h1>
		<form action="CourierShowDeliveringOrderController" method="post">
			<button type="submit" name="user_id" value="${user_id }"
				class="btn btn-default">Back To Your Delivering Order List</button>
		</form>
		<br>
		<form action="CourierPickOrdersController" method="post">
			<button type="submit" name="user_id" value="${user_id }"
				class="btn btn-default">Back To Continue Order Pick</button>
		</form>
	</div>
</body>
</html>