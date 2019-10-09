package datasource;

import java.sql.SQLException;
import java.util.List;

import domain.Order;

public interface IOrderMapper {

    public void insert(Order order);

    public void updateDetailOfOrder(Order order);

    public void updateShipmentOfOrder(Order order);

    public void delete(Order order);

    public List<Order> findAllOrdersForCustomer(int customer_id);

    public Order findOrderFromOrderId(int order_id) throws SQLException;

    public List<Order> findAllOrdersForCourier(int courier_id, String status);

    public List<Order> findAllConfirmedOrders();

}
