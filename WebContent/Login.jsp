<%@ page import="authentication.AppSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
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
        <h1>Login</h1>
        <% if (!AppSession.isAuthenticated() || AppSession.getUser()==null) { %>
        <form action="LoginController" method="post">
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
        <% } else { %>
            <% if(AppSession.getUser().getUser_type().equals(AppSession.CUSTOMER_ROLE)) {%>
                You are already logged in as <%=AppSession.getUser().getUsername() %>
                <form action="CustomerShowOrderController" method="post">
                    <button type="submit" name="user_id" value="${user_id }"
                        class="btn btn-default">View Your Orders</button>
                </form>
            <%} else if(AppSession.getUser().getUser_type().equals(AppSession.COURIER_ROLE)){%>
                You are already logged in as <%=AppSession.getUser().getUsername() %>
                <form action="CourierShowDeliveringOrderController" method="post">
                    <button type="submit" name="user_id" value="${user_id }"
                        class="btn btn-default">View Your Orders</button>
                </form>
            <%} %>
        <%} %>
    </div>
</body>
</html>