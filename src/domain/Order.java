package domain;

import java.sql.SQLException;

import concurrency.LockManager;
import datasource.IOrderMapper;
import datasource.OrderLockingMapper;

public class Order {
    public static final String CONFIRMED_STATUS = "Confirmed";
    public static final String SHIPPED_STATUS = "Shipped";
    public static final String DELIVERED_STATUS = "Delivered";

    private int order_id;
    private String status;
    private Item item = null; // embedded value pattern
    private Destination destination;
    private Customer customer; // Foreign Key Mapping
    private Courier courier; // Foreign Key Mapping

    public Order() {
    }

    public Order(int order_id) {
        this.order_id = order_id;
    }

    public Order(int order_id, String status, Item item,
            Destination destination, Customer customer, Courier courier) {
        setOrder_id(order_id);
        setStatus(status);
        setItem(item);
        setDestination(destination);
        setCustomer(customer);
        setCourier(courier);
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() throws SQLException {
        if (status == null) {
            load();
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItem() throws SQLException {
        if (item == null) {
            load();
        }
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Destination getDestination() throws SQLException {
        if (destination == null) {
            load();
        }
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Customer getCustomer() throws SQLException {
        if (customer == null) {
            load();
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() throws SQLException {
        if (courier == null) {
            load();
        }
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    // lazy load -- ghost
    public void load() throws SQLException {
        IOrderMapper mapper = new OrderLockingMapper();
        Order order = mapper.findOrderFromOrderId(order_id);
        if (status == null) {
            setStatus(order.getStatus());
        }
        if (item == null) {
            setItem(order.getItem());
        }
        if (destination == null) {
            setDestination(order.getDestination());
        }
        if (customer == null) {
            setCustomer(order.getCustomer());
        }
        LockManager.getInstance().releaseReadLock(order);
    }
}
