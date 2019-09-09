<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
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
			<label> <input type="text" name="username"
				placeholder="username" class="input">
			</label> <br> <label> <input type="password" name="password"
				placeholder="password" class="input">
			</label> <br> <input type="submit" value="Login" class="submit">
		</form>
	</div>
</body>
</html>