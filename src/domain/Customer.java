package domain;

import java.util.List;

public class Customer extends User {
    private String user_type = User.CUSTOMER_TYPE;
    private List<Order> orders;

    public Customer(int user_id) {
        super();
        this.setUser_id(user_id);
    }
}
