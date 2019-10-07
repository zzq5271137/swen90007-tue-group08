<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>View Delivering Orders</title>
<link rel='stylesheet' href='resources/bootstrap.min.css' />
<link rel='stylesheet' href='style.css' />
<style>
h1{
    display: inline-block;
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
        <h1>Orders</h1>
        <a id='logout' href="Login.jsp" class="btn btn-info btn-lg">
          Log out
        </a>
        <table class='table table-bordered table-striped'>
            <tr>
                <th>Order Id</th>
                <th>Package Size</th>
                <th>Package Weight</th>
                <th>Destination</th>
                <th>Status</th>
            </tr>
            <c:if test="${empty orders }">
                <h3>You have no orders!</h3>
            </c:if>
            <c:forEach items="${orders }" var="order">
                <tr>
                    <td>${order.order_id }</td>
                    <td>${order.item.item_size }</td>
                    <td>${order.item.item_weight }</td>
                    <td>${order.destination.address }</td>
                    <td>${order.status }</td>
                    <td colspan="2" align="center">
                        <form action="CourierFinishDeliverController" method="post">
                            <input type="hidden" name="user_id" value="${user_id }">
                            <button type="submit" name="order_id" value="${order.order_id }"
                                class="btn btn-default">Finish Deliver</button>
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <div id='address'>
            <form action="CourierShowLogsController" method="post">
                <button type="submit" name="user_id" value="${user_id }"
                    class="btn btn-default">View Your Logs</button>
            </form>
        </div>
        <div id='add'>
            <form action="CourierPickOrdersController" method="post">
                <button type="submit" name="user_id" value="${user_id }"
                    class="btn btn-default">Pick Orders to Deliver</button>
            </form>
        </div>
    </div>
</body>
</html>
