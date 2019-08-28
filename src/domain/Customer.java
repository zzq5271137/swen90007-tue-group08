package domain;

import java.util.List;

public class Customer {
    private int id;
    private String email;
    private String name;
    private String address;
    private List<Order> orders;
    private ShoppingCart cart;

    public Customer(int id, String email, String name, String address,
                    List<Order> orders, ShoppingCart cart) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }
}
