package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import datasource.IdentityMap;
import datasource.OrderMapper;
import datasource.LogMapper;

public class Courier extends User {

    private List<CourierLog> myLogs;

    public Courier() {
        super();
        setUser_type(User.COURIER_TYPE);
    }

    public Courier(int user_id) {
        super();
        this.setUser_id(user_id);
    }

    // get all orders that belong to this courier,
    // this method is used to fetch all orders that the courier is currently
    // delivering, so only need to retrieve orders that the status is Shipped
    @Override
    public List<Order> getAllOrders() {
        OrderMapper mapper = new OrderMapper();
        orders = mapper.findAllOrdersForCourier(getUser_id(), "Shipped");
        setOrders(orders);
        return orders;
    }

    // get all Confirmed(new) orders from order pool
    public List<Order> inspectAllNewOrders() {
        OrderMapper mapper = new OrderMapper();
        orders = mapper.findAllConfirmedOrders();
        return orders;
    }

    // set status of these selected orders to be Shipped and the courier_id of this
    // orders to be the id of this courier
    public void confirmPickOrder(int order_id) throws SQLException {
        OrderMapper mapper = new OrderMapper();
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = mapper.findOrderFromOrderId(order_id);
        order.setCourier(this);
        order.setStatus("Shipped");
        iMap.put(order_id, order);
        mapper.updateShipmentOfOrder(order);
    }

    // change the order status to "Delivered" directly
    public void markDelivered(int order_id) {
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = iMap.get(order_id);
        order.setStatus("Delivered");
        OrderMapper mapper = new OrderMapper();
        mapper.updateShipmentOfOrder(order);
    }

    // get all logs related to this courier
    public List<CourierLog> getMyLogs() {
        List<CourierLog> myLogs = new ArrayList<>();
        LogMapper lMapper = new LogMapper();
        myLogs = lMapper.findAllLogsForCourier(getUser_id());
        return myLogs;
    }

    public void setMyLogs(List<CourierLog> logs) {
        this.myLogs = logs;
    }

    // automatically update courier_log table by increasing the sent_count by 1,
    // if there is no log for today, create one log with sent_count equals to 1
    // and insert it into courier_log table
    public void logWork() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(currentTime);

        CourierLog log = null;
        LogMapper mapper = new LogMapper();
        log = mapper.findCurrentLog(getUser_id(), date);
        if (log == null) {
            mapper.insert(getUser_id(), date);
        } else {
            log.setSent_count(log.getSent_count() + 1);
            mapper.update(log);
        }
    }

    // delete a single piece of log
    public void deleteLog(String date) {
        LogMapper mapper = new LogMapper();
        mapper.delete(getUser_id(), date);
    }
}
