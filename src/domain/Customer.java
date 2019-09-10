package domain;

import java.sql.SQLException;
import java.util.List;

import datasource.DestinationMapper;
import datasource.IdentityMap;
import datasource.KeyTable;
import datasource.OrderMapper;
import datasource.UnityOfWork;

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
    public List<Order> getAllOrders() {
        OrderMapper mapper = new OrderMapper();
        orders = mapper.findAllOrdersForCustomer(getUser_id());
        setOrders(orders);
        return orders;
    }

    @Override
    public List<Destination> getAllAddresses() {
        DestinationMapper dMapper = new DestinationMapper();
        List<Destination> destinations = dMapper
                .findAllAddressForCustomer(getUser_id());
        return destinations;
    }

    public void ChangeOrderDetail(int order_id, float item_size,
            float item_weight, String address) {
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = iMap.get(order_id);
        
        Destination destination = new Destination(
                KeyTable.getKey(KeyTable.DESTINATION_TABLE));
        destination.setAddress(address);
        order.setDestination(destination);
        DestinationMapper dMapper = new DestinationMapper();
        dMapper.insert(getUser_id(), destination);
        
        order.setItem_size(item_size);
        order.setItem_weight(item_weight);
        iMap.put(order_id, order);
        UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerDirty(order);
        UnityOfWork.getCurrent().commit();
    }
}
