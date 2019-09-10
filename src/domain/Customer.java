package domain;

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

    @Override
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

    @Override
    public void CreateNewOrder(float item_size, float item_weight,
            String address) {
        Order order = new Order();
        int order_id = KeyTable.getKey(KeyTable.ORDER_TABLE);
        order.setOrder_id(order_id);
        order.setStatus(Order.CONFIRMED_STATUS);
        order.setItem_size(item_size);
        order.setItem_weight(item_weight);
        Destination destination = new Destination(
                KeyTable.getKey(KeyTable.DESTINATION_TABLE));
        destination.setAddress(address);
        order.setDestination(destination);
        order.setCustomer(this);
        DestinationMapper dMapper = new DestinationMapper();
        dMapper.insert(getUser_id(), destination);
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        iMap.put(order_id, order);
        List<Order> orders = getAllOrders();
        orders.add(order);
        setOrders(orders);
        UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerNew(order);
        UnityOfWork.getCurrent().commit();
    }

    @Override
    public void deleteOrder(int order_id) {
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = iMap.get(order_id);
        List<Order> orders = getAllOrders();
        orders.remove(order);
        setOrders(orders);
        iMap.remove(order_id);
        UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerDeleted(order);
        UnityOfWork.getCurrent().commit();
    }
}
