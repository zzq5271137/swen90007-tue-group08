package domain;

<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.sql.ResultSet;
import java.sql.SQLException;

>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
import datasource.OrderMapper;

public class Order {
    public static final String CONFIRMED_STATUS = "Confirmed";
    public static final String SHIPPED_STATUS = "Shipped";
    public static final String DELIVERED_STATUS = "Delivered";

    private int order_id;
    private String status;
<<<<<<< HEAD
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
=======
    private int item_size = 0;
    private int item_weight = 0;
    private Destination destination;
    private Customer customer; // Foreign Key Mapping
    private Courier courier; // Foreign Key Mapping
    private OrderMapper mapper;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f

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
<<<<<<< HEAD
        if (status == null) {
            load();
        }
        return status;
=======
    	if(this.status == null) {
    		load();
    	}
        return this.status;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
    }

    public void setStatus(String status) {
        this.status = status;
    }

<<<<<<< HEAD
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
=======
    public Destination getDestination() throws SQLException {
    	if(this.destination == null) {
    		load();
    	}
        return this.destination;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Customer getCustomer() throws SQLException {
<<<<<<< HEAD
        if (customer == null) {
            load();
        }
        return customer;
=======
    	if(this.customer == null) {
    		load();
    	}
        return this.customer;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() throws SQLException {
<<<<<<< HEAD
        if (courier == null) {
            load();
        }
        return courier;
=======
    	if(this.courier == null) {
    		load();
    	}
        return this.courier;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
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

    // lazy load -- ghost
    public void load() throws SQLException {
        OrderMapper mapper = new OrderMapper();
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
    }
}
