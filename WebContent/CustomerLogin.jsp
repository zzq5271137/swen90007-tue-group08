<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Login</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<style>
div#root {
	width: 100%;
	margin-top: 120px;
	text-align: center;
}

input.submit {
	width: 100px;
	font-size: 20px;
}

input {
	margin-bottom: 7px;
	border: 1px solid #000;
}

input.input {
	width: 200px;
	height: 27px;
}
</style>
</head>
<body>
	<div id="root">
		<h1>Customer Login</h1>
		<form action="CustomerLoginServlet" method="post">
			<label> 
			    <input type="text" name="username" placeholder="username" class="input">
			</label> 
			<br> 
			<label> 
			    <input type="password" name="password" placeholder="password" class="input" >
			</label> 
			<br> 
			<input type="submit" value="Login" class="submit btn btn-default">
		</form>
	</div>
</body>
</html>