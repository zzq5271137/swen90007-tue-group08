package domain;

import java.util.List;

import datasource.DestinationMapper;
import datasource.IdentityMap;
import datasource.KeyTable;
import datasource.OrderMapper;
import datasource.UnityOfWork;

public class Customer extends User {
    private List<Destination> destinations;

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

    public List<Destination> getDestinations() {
        if (this.destinations == null) {
            DestinationMapper dMapper = new DestinationMapper();
            setDestinations(dMapper.findAllAddressForCustomer(getUser_id()));
        }
        return this.destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public void CreateNewOrder(float item_size, float item_weight,
            String address) {
        Order order = new Order();

        int order_id = KeyTable.getKey(KeyTable.ORDER_TABLE);
        order.setOrder_id(order_id);
        order.setStatus(Order.CONFIRMED_STATUS);
        order.setCustomer(this);

        Item item = new Item(item_size, item_weight);
        order.setItem(item);

        Destination destination = new Destination(
                KeyTable.getKey(KeyTable.DESTINATION_TABLE));
        destination.setAddress(address);
        List<Destination> destinations = getDestinations();
        destinations.add(destination);
        setDestinations(destinations);
        DestinationMapper dMapper = new DestinationMapper();
        dMapper.insert(getUser_id(), destination);
        order.setDestination(destination);

        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        iMap.put(order_id, order);
        List<Order> orders = getAllOrders();
        orders.add(order);
        setOrders(orders);

        UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerNew(order);
        UnityOfWork.getCurrent().commit();
    }

    public void ChangeOrderDetail(int order_id, float item_size,
            float item_weight, String address) {
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = iMap.get(order_id);

        Destination destination = new Destination(
                KeyTable.getKey(KeyTable.DESTINATION_TABLE));
        destination.setAddress(address);
        List<Destination> destinations = getDestinations();
        destinations.add(destination);
        setDestinations(destinations);
        DestinationMapper dMapper = new DestinationMapper();
        dMapper.insert(getUser_id(), destination);
        order.setDestination(destination);

        Item item = new Item(item_size, item_weight);
        order.setItem(item);
        iMap.put(order_id, order);

        UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerDirty(order);
        UnityOfWork.getCurrent().commit();
    }

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
