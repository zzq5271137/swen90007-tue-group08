<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<style>
div#root {
    width: 100px;
    height: 300px;
    line-height: 40px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="root">
	   <div>
			<form action="CustomerLoginServlet" method="post">
				<label> username: <input type="text" name="username">
				</label> <br> <label> password: <input type="text"
					name="password">
				</label> <br> <input type="submit" value="Login">
			</form>
		</div>
	</div>
</body>
</html>