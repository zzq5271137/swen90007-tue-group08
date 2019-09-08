package domain;

import java.util.List;

public class Courier extends User {
    private String user_type = User.COURIER_TYPE;
    private List<Order> orders;

    public Courier(int user_id) {
        super();
        this.setUser_id(user_id);
    }
}
