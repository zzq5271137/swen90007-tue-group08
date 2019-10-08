<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>View Addresses</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<link rel='stylesheet' href='style.css' />
<style>
#h1{
	display: inline-block
}
#logout{
	display: inline-block;
	float: right;
	margin-top:20px;
	margin-bottom:10px
}
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
		<h1 id = 'h1'>Addresses</h1>
		<form action="logout" method="post" id='logout'>
			<button class="btn btn-info btn-lg">Log out</button>
		</form>
		<table class='table table-bordered table-striped'>
			<tr>
				<th>Id</th>
				<th>Address</th>
			</tr>
			<c:if test="${empty addresses }">
                <h3>You have no addresses!</h3>
            </c:if>
			<c:forEach items="${addresses }" var="address">
				<tr>
					<td>${address.destination_id }</td>
					<td>${address.address }</td>
				</tr>
			</c:forEach>
		</table>
		<form action="CustomerShowOrderController" method="post">
            <button type="submit" name="user_id" value="${user_id }"
                class="btn btn-default">Back To Your Order List</button>
        </form>
	</div>
</body>
</html>
