<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.Order"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>View Books</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<link rel='stylesheet' href='style.css' />
</head>
<body>
	<div class='container'>
		<h1>View Books</h1>
		<table class='table table-bordered table-striped'>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>price</th>
				<th>Add to Cart</th>
			</tr>
			<c:if test="${empty books }">
			     out.write("no books");
			</c:if>
			<c:forEach items="${books }" var="book">
				<form action="cart" method="post">
					<tr>
						<td>${book.isbn }</td>
						<td>${book.title }</td>
						<td>${book.author }</td>
						<td>${book.price }</td>
						<td colspan="2" align="center">
							<button type="submit" name="isbn" value="${book.isbn }"
								class="btn btn-default">Add to Cart</button>
						</td>
					</tr>
				</form>
			</c:forEach>
		</table>
	</div>
</body>
</html>
