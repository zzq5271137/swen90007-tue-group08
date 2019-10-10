package service;

import java.util.List;

import domain.Customer;
import domain.Destination;
import domain.Order;
import domain.User;

public class CustomerServices {

    public static List<Order> getAllOrderService(User user) {
        List<Order> orders = user.getAllOrders();
        return orders;
    }

    public static void createNewOrderService(User user, float item_size,
            float item_weight, String address) {
        ((Customer) user).createNewOrder(item_size, item_weight, address);
    }

    public static void changeOrderService(User user, int order_id,
            float item_size, float item_weight, String address) {
        ((Customer) user).changeOrderDetail(order_id, item_size, item_weight,
                address);
    }

    public static void deleteOrderService(User user, int order_id) {
        ((Customer) user).deleteOrder(order_id);
    }

    public static List<Destination> getAllDestinationsServices(User user) {
        List<Destination> destinations = ((Customer) user).getDestinations();
        return destinations;
    }
}
