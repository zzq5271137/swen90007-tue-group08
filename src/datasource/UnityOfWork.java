package datasource;

import java.util.ArrayList;
import java.util.List;

import domain.Order;

public class UnityOfWork {
    private static ThreadLocal current = new ThreadLocal();

    private List<Order> newOrders = new ArrayList<>();
    private List<Order> dirtyOrders = new ArrayList<>();
    private List<Order> deletedObjects = new ArrayList<>();

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
        assert !deletedObjects.contains(order) : "The order is deleted.";
        newOrders.add(order);
    }

    public void registerDirty(Order order) {
        assert order.getOrder_id() != 0 : "Order id is null.";
        assert !deletedObjects.contains(order) : "The order is deleted.";
        if (!dirtyOrders.contains(order) && !newOrders.contains(order))
            dirtyOrders.add(order);
    }

    public void registerDeleted(Order order) {
        assert order.getOrder_id() != 0 : "Order id is null.";
        if (newOrders.remove(order))
            return;
        dirtyOrders.remove(order);
        if (!deletedObjects.contains(order))
            deletedObjects.add(order);
    }

    public void commit() {
        
    }
}
