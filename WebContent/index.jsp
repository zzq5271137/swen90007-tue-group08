<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Parcel Delivery System</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<style>
div#root {
	text-align: center;
}

a button {
	font-size: 20px;
	width: 200px;
}
</style>
</head>
<body>
	<div id="root">
		<h1>Welcome to Parcel Delivery System</h1>
		<a href="./CustomerLogin.jsp">
			<button class="btn btn-default">Customer Login</button>
		</a> 
		<br> 
		<br> 
		<a href="./CourierLogin.jsp">
			<button class="btn btn-default">Courier Login</button>
		</a>
	</div>
</body>
</html>