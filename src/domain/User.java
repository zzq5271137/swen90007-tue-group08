package domain;

import java.util.List;

public abstract class User {
    public static final String CUSTOMER_TYPE = "customer";
    public static final String COURIER_TYPE = "courier";

    private int user_id;
    private String username;
    private String password;
    private String user_type;
    protected List<Order> orders;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public List<Destination> getAllAddresses() {
        return null;
    }

}
