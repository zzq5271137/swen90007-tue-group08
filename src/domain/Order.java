package domain;

import java.sql.SQLException;
import datasource.OrderMapper;

public class Order {
    public static final String CONFIRMED_STATUS = "Confirmed";
    public static final String SHIPPED_STATUS = "Shipped";
    public static final String DELIVERED_STATUS = "Delivered";
    
    private int order_id;
    private String status;
    private float item_size = -1;
    private float item_weight = -1;
    private Destination destination;
    private Customer customer; // Foreign Key Mapping
    private Courier courier; // Foreign Key Mapping

    public Order() {
    }

    public Order(int order_id) {
        this.order_id = order_id;
    }

    public Order(int order_id, String status, float item_size,
            float item_weight, Destination destination, Customer customer,
            Courier courier) {
        setOrder_id(order_id);
        setStatus(status);
        setItem_size(item_size);
        setItem_weight(item_weight);
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

    public float getItem_size() throws SQLException {
        if (item_size == -1) {
            load();
        }
        return item_size;
    }

    public void setItem_size(float item_size) {
        this.item_size = item_size;
    }

    public float getItem_weight() throws SQLException {
        if (item_weight == -1) {
            load();
        }
        return item_weight;
    }

    public void setItem_weight(float item_weight) {
        this.item_weight = item_weight;
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
        OrderMapper mapper = new OrderMapper();
        Order order = mapper.findOrderFromOrderId(order_id);
        if (status == null) {
            setStatus(order.getStatus());
        }
        if (item_size == -1) {
            setItem_size(order.getItem_size());
        }
        if (item_weight == -1) {
            setItem_weight(order.getItem_weight());
        }
        if (destination == null) {
            setDestination(order.getDestination());
        }
        if (customer == null) {
            setCustomer(order.getCustomer());
        }
    }
}
