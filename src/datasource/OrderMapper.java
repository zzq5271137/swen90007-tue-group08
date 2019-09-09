package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Courier;
import domain.Customer;
import domain.Destination;
import domain.Order;
import domain.User;

public class OrderMapper {

    // ===========================
    private static final String findOrderFromCustomerId = "SELECT order_id, status, item_size, item_weight, destination_id, "
            + "customer_id, courier_id FROM orders WHERE customer_id = ?";
    private static final String findOrderFromCourierId = "SELECT order_id, status, item_size, item_weight, destination_id, "
            + "customer_id, courier_id FROM orders WHERE courier_id = ?";
    private static final String findOrderWithStatus = "SELECT order_id, status, item_size, item_weight, destination_id, "
            + "customer_id, courier_id FROM orders WHERE status = ?";
    // ===================================

    private static final String FindAllOrdersForCustomerLazy = "SELECT order_id FROM orders WHERE customer_id = ?";

    private static final String findOrderFromOrderId = "SELECT status, item_size, item_weight, destination_id, customer_id, courier_id FROM orders WHERE order_id = ?";

    /**
     * Retrieve order object from Identity Map according to order_id. If the
     * corresponding order is not registered in the Identity Map, fetch the data
     * from database and register it into Identity Map.
     * 
     * @param order_id
     *            The id of the order to be retrieved.
     * @param lazy
     *            Whether use the lazy mode or not.
     * @return Retrieved Order.
     */
    public Order checkIdentityMap(int order_id, boolean lazy) {
        Order order = new Order();
        IdentityMap<Order> iMap = IdentityMap.getInstance(order);
        order = iMap.get(order_id);
        if (order == null) {
            if (lazy) {
                order = new Order(order_id);
            } else {
                PreparedStatement findStatement = null;
                ResultSet rs = null;
                try {
                    findStatement = DBConnection.prepare(findOrderFromOrderId);
                    findStatement.setInt(1, order_id);
                    rs = findStatement.executeQuery();
                    rs.next();
                    String status = rs.getString(1);
                    float item_size = rs.getFloat(2);
                    float item_weight = rs.getFloat(3);
                    int destination_id = rs.getInt(4);
                    int customer_id = rs.getInt(5);
                    int courier_id = rs.getInt(6);

                    UserMapper uMapper = new UserMapper();
                    User customer = uMapper.findWithUserId(customer_id,
                            User.CUSTOMER_TYPE, false);
                    User courier = uMapper.findWithUserId(courier_id,
                            User.COURIER_TYPE, true);

                    DestinationMapper dMapper = new DestinationMapper();
                    Destination destination = dMapper
                            .findDestinationFromDestinationId(destination_id);
                    order = new Order(order_id, status, item_size, item_weight,
                            destination, (Customer) customer,
                            (Courier) courier);
                    iMap.put(order_id, order);
                } catch (SQLException e) {
                }
            }
        }
        return order;
    }

    /**
     * Fetch all orders for one customer according to his/her id.
     * 
     * @param customer_id
     *            The id of the customer for whom retrieve the orders.
     * @return Retrieved list of orders.
     */
    public List<Order> findAllOrdersForCustomer(int customer_id) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        try {
            findStatement = DBConnection.prepare(FindAllOrdersForCustomerLazy);
            findStatement.setInt(1, customer_id);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                Order order = checkIdentityMap(order_id, true);
                orders.add(order);
            }
        } catch (SQLException e) {
        }
        return orders;
    }

    /**
     * Retrieve all data of one order. Lazy mode off.
     * 
     * @param order_id
     *            The id of the order.
     * @return Retrieved order.
     * @throws SQLException
     */
    public Order findOrderFromOrderId(int order_id) throws SQLException {
        return checkIdentityMap(order_id, false);
    }

    /*
     * Methods below are not used for now.
     */

    // used for couriers looking for their order
    public ResultSet findOrderFromCourierId(int courier_id) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = DBConnection.prepare(findOrderFromCourierId);
            findStatement.setInt(1, courier_id);
            rs = findStatement.executeQuery();
        } catch (SQLException e) {
        }
        return rs;
    }

    // used for couriers looking for orders with a specific status
    public ResultSet findOrderWithStatus(String status) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = DBConnection.prepare(findOrderWithStatus);
            findStatement.setString(1, status);
            rs = findStatement.executeQuery();
        } catch (SQLException e) {
        }
        return rs;
    }

    // Identity Field -- key tables
    public void insert(Order order) {
        int order_id = KeyTable.getKey("orders"); // automatically generated primary key
    }
}
