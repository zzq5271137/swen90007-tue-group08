package domain;

import java.util.List;

import datasource.UserMapper;

public abstract class User {
    public static final String CUSTOMER_TYPE = "customer";
    public static final String COURIER_TYPE = "courier";

    private int user_id;
    private String username;
    private String password;
    private String user_type;
    protected List<Order> orders;
    
    public static User getUser(String username) {
    	User result = null;
    	UserMapper uMapper = new UserMapper();
    	result = uMapper.findWithUsername(username);
    	return result;
    }
    
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

    public List<Order> getAllOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Destination> getAllAddresses() {
        return null;
    }

    public void ChangeOrderDetail(int order_id, float item_size,
            float item_weight, String address) {
    }

    public void CreateNewOrder(float item_size, float item_weight,
            String address) {
    }
    
    public void deleteOrder(int order_id) {
    }
}
