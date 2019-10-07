package datasource;

import java.util.ArrayList;
import java.util.List;

import domain.Order;

public class UnityOfWork {
    private static ThreadLocal current = new ThreadLocal();

    private List<Order> newOrders = new ArrayList<>();
    private List<Order> dirtyOrders = new ArrayList<>();
    private List<Order> deletedOrders = new ArrayList<>();

    public static void newCurrent() {
        setCurrent(new UnityOfWork());
    }

    public static void setCurrent(UnityOfWork uow) {
        current.set(uow);
    }

    public static UnityOfWork getCurrent() {
        return (UnityOfWork) current.get();
    }

    public void registerNew(Order order) {
        assert order.getOrder_id() != 0 : "Order id is null.";
        assert !newOrders.contains(order) : "The order is new.";
        assert !dirtyOrders.contains(order) : "The order is dirty.";
        assert !deletedOrders.contains(order) : "The order is deleted.";
        newOrders.add(order);
    }

    public void registerDirty(Order order) {
        assert order.getOrder_id() != 0 : "Order id is null.";
        assert !deletedOrders.contains(order) : "The order is deleted.";
        if (!dirtyOrders.contains(order) && !newOrders.contains(order))
            dirtyOrders.add(order);
    }

    public void registerDeleted(Order order) {
        assert order.getOrder_id() != 0 : "Order id is null.";
        if (newOrders.remove(order))
            return;
        dirtyOrders.remove(order);
        if (!deletedOrders.contains(order))
            deletedOrders.add(order);
    }

    public void commit() {
        OrderMapper oMapper = new OrderMapper();
        for (Order order : newOrders) {
            oMapper.insert(order);
        }
        for (Order order : dirtyOrders) {
            oMapper.updateDetailOfOrder(order);
        }
        for (Order order : deletedOrders) {
            oMapper.delete(order);
        }
    }
}
