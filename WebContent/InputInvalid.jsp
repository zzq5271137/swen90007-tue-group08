<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login Invalid</title>
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
		<h1>Invalid input, Please retry</h1>
		<form action="CustomerShowOrderController" method="post">
			<button type="submit" name="user_id" value="${user_id }"
				class="btn btn-default">View Your Orders</button>
		</form>
	</div>
</body>
</html>