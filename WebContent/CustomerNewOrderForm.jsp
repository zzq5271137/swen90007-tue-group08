<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8"%>
<%@page import="domain.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Order Detail</title>
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
        <h1>Please fill in order detail.</h1>
		
        <form action="CustomerConfirmNewOrderController" method="post">
            <label> 
                Item size:
                <input type="text" name="item_size" placeholder="Item Size" class="input">
            </label> 
            <br> 
            <label>
                Item weight: 
                <input type="text" name="item_weight"  placeholder="Item Weight" class="input" >
            </label> 
            <br>
            <label>
                Destination:
                <input type="text" name="address" placeholder="Destination" class="input" >
            </label>
            <br>
            <input type="hidden" name="user_id" value="${user_id }">
            <input type="submit" value="Confirm" class="submit btn btn-default">
        </form>
    </div>
</body>
</html>