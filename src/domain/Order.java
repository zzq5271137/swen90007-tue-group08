package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import datasource.OrderMapper;

public class Order {
    private int order_id;
    private String status;
    private int item_size = 0;
    private int item_weight = 0;
    private Destination destination;
    private Customer customer; // Foreign Key Mapping
    private Courier courier; // Foreign Key Mapping
    private OrderMapper mapper;

    public Order() {
    	
    }
    
    public Order(int order_id) {
    	this.order_id = order_id;
    }
    
    public Order(int order_id, String status, int item_size, int item_weight, 
    		int destination_id, int customer_id, int courier_id) {
    	this.order_id = order_id;
    	this.status = status;
    	this.item_size = item_size;
    	this.item_weight = item_weight;
    	this.destination.setDestination_id(destination_id);
    	this.customer.setUser_id(customer_id);
    	this.courier.setUser_id(courier_id);
    }
    
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() throws SQLException {
    	if(this.status == null) {
    		load();
    	}
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Destination getDestination() throws SQLException {
    	if(this.destination == null) {
    		load();
    	}
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Customer getCustomer() throws SQLException {
    	if(this.customer == null) {
    		load();
    	}
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() throws SQLException {
    	if(this.courier == null) {
    		load();
    	}
        return this.courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
    
    // lazy load -- ghost
    public void load() throws SQLException {
    	ResultSet rs = mapper.findOrderFromOrderId(order_id);
    	if(this.status == null) {
    		this.status = rs.getString(2);
    	}
    	if(this.item_size == 0) {
    		this.item_size = rs.getInt(3);
    	}
    	if(this.item_weight == 0) {
    		this.item_weight = rs.getInt(4);
    	}
    	if(this.destination == null) {
    		int destination_id = rs.getInt(5);
    		this.destination = new Destination(destination_id);
    	}
    	if(this.customer == null) {
    		int customer_id = rs.getInt(6);
    		this.customer = new Customer(customer_id);
    	}
    	if(this.courier == null) {
    		int courier_id = rs.getInt(7);
    		this.courier = new Courier(courier_id);
    	}
    }

}
