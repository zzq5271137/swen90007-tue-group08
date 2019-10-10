package service;

import java.sql.SQLException;
import java.util.List;

import domain.Courier;
import domain.CourierLog;
import domain.Order;
import domain.User;

public class CourierServices {

    public static List<Order> getAllDeliveringOrdersService(User user) {
        List<Order> orders = user.getAllOrders();
        return orders;
    }

    public static void finishDeliverService(User user, int order_id) {
        ((Courier) user).markDelivered(order_id);
        ((Courier) user).logWork();
    }

    public static List<Order> inspectAllNewOrdersService(User user) {
        List<Order> orders = ((Courier) user).inspectAllNewOrders();
        return orders;
    }

    public static void confirmPickOrderService(User user, int order_id) {
        try {
            ((Courier) user).confirmPickOrder(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<CourierLog> getCourierLogsService(User user) {
        List<CourierLog> myLogs = ((Courier) user).getMyLogs();
        return myLogs;
    }

    public static void deleteLogService(User user, String date) {
        ((Courier) user).deleteLog(date);
    }
}
