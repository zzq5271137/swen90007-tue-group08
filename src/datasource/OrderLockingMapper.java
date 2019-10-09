package datasource;

import java.sql.SQLException;
import java.util.List;

import concurrency.LockManager;
import domain.Order;

public class OrderLockingMapper implements IOrderMapper {
    private OrderMapper mapper;

    public OrderLockingMapper() {
        super();
        this.mapper = new OrderMapper();
    }

    @Override
    public void insert(Order order) {
        try {
            LockManager.getInstance().acquireWriteLock(order);
            mapper.insert(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDetailOfOrder(Order order) {
        try {
            LockManager.getInstance().acquireWriteLock(order);
            mapper.updateDetailOfOrder(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        try {
            LockManager.getInstance().acquireWriteLock(order);
            mapper.delete(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShipmentOfOrder(Order order) {
        try {
            LockManager.getInstance().acquireWriteLock(order);
            mapper.updateShipmentOfOrder(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAllOrdersForCustomer(int customer_id) {
        return mapper.findAllOrdersForCustomer(customer_id);
    }

    @Override
    public List<Order> findAllOrdersForCourier(int courier_id, String status) {
        return mapper.findAllOrdersForCourier(courier_id, status);
    }

    @Override
    public List<Order> findAllConfirmedOrders() {
        return mapper.findAllConfirmedOrders();
    }

    @Override
    public Order findOrderFromOrderId(int order_id) throws SQLException {
        return mapper.findOrderFromOrderId(order_id);
    }

}
