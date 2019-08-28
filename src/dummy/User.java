package dummy;

import domain.Customer;
import domain.ShoppingCart;

import java.util.LinkedList;

public class User {
    private static Customer user;

    public static Customer getCustomer() {
        if (User.user == null) {
            User.user = new Customer(1, "albert.einstein@ias.edu", "Albert Einstein",
                    "Office 9, IAS, Princeton, USA", new LinkedList<>(), new ShoppingCart());
        }
        return user;
    }
}
