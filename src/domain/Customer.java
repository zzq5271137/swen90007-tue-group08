package domain;

import java.util.List;

import datasource.DestinationMapper;
import datasource.OrderMapper;

public class Customer extends User {

    public Customer() {
        super();
        setUser_type(User.CUSTOMER_TYPE);
    }

    public Customer(int user_id) {
        super();
        this.setUser_id(user_id);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = super.getOrders();
        if (orders == null) {
            OrderMapper mapper = new OrderMapper();
            orders = mapper.findAllOrdersForCustomer(getUser_id());
            setOrders(orders);
        }
        return orders;
    }

    @Override
    public List<Destination> getAllAddresses() {
        DestinationMapper dMapper = new DestinationMapper();
        List<Destination> destinations = dMapper
                .findAllAddressForCustomer(getUser_id());
        return destinations;
    }
}
