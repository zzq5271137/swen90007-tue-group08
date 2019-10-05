package datasource;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Courier;
import domain.Customer;
import domain.Destination;
import domain.Item;
import domain.Order;
import domain.User;

public class OrderMapper {

    private static final String findAllOrdersForCustomerLazy = "SELECT order_id FROM orders WHERE customer_id = ?";
    private static final String findAllOrdersForCourierLazy = "SELECT order_id FROM orders WHERE courier_id = ?";
    private static final String findOrderFromOrderId = "SELECT status, item_size, item_weight, destination_id, customer_id, courier_id FROM orders WHERE order_id = ?";
    private static final String updateOrder = "UPDATE orders SET item_size = ?, item_weight = ?, destination_id = ? WHERE order_id = ?";
    private static final String insertNewOrder = "INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String deleteOrder = "DELETE FROM orders WHERE order_id = ?";
    private static final String findConfirmedOrders = "SELECT order_id FROM orders WHERE status = Confirmed";
    private static final String findAllLogForCourier = "SELECT log_date, sent_count FROM courier_log WHERE courier_id = ?";
    private static final String findCurrentLog = "SELECT sent_count FROM courier_log WHERE courier_id = ? AND log_date = now()";
    private static final String insertLog = "INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (?, now(), 1)";
    private static final String updateLog = "UPDATE courier_log SET courier_id = ?, log_date = now(), sent_count = ?";    
    private static final String deleteLog = "DELETE FROM courier_log WHERE courier_id = ?, log_date = ?";

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
                    Item item = new Item(item_size, item_weight);

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
                    order = new Order(order_id, status, item, destination,
                            (Customer) customer, (Courier) courier);
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
            findStatement = DBConnection.prepare(findAllOrdersForCustomerLazy);
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
    
    public List<Order> findAllOrdersForCourier(int courier_id) {
       PreparedStatement findStatement = null;
       ResultSet rs = null;
       List<Order> orders = new ArrayList<>();
       try {
           findStatement = DBConnection.prepare(findAllOrdersForCourierLazy);
           findStatement.setInt(1, courier_id);
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

    // return the current sent_count of this courier for today 
    public int findCurrentLog(int courier_id) {
    	int sent_count = -1;
    	PreparedStatement findStatement = null;
    	ResultSet rs = null;
    	 try {
             findStatement = DBConnection.prepare(findCurrentLog);
             findStatement.setInt(1, courier_id);
             rs = findStatement.executeQuery();
             while (rs.next()) {
                 sent_count = rs.getInt(1);
             }
         } catch (SQLException e) {
         }
		return sent_count;
    }
    
    // return all confirmed 
    public List<Order> findConfirmedOrders(){
    	PreparedStatement findStatement = null;
    	ResultSet rs = null;
    	List<Order> orders = new ArrayList<>();
    	try {
    		findStatement = DBConnection.prepare(findConfirmedOrders);
    		rs = findStatement.executeQuery();
    		while (rs.next()) {
                int order_id = rs.getInt(1);
                Order order = checkIdentityMap(order_id, true);
                orders.add(order);
            }
    	}catch(SQLException e) {
    	}
    	return orders;
    }
   
    public void update(Order order) {
        PreparedStatement updateStatement = null;
        try {
            updateStatement = DBConnection.prepare(updateOrder);
            updateStatement.setFloat(1, order.getItem().getItem_size());
            updateStatement.setFloat(2, order.getItem().getItem_weight());
            updateStatement.setInt(3,
                    order.getDestination().getDestination_id());
            updateStatement.setInt(4, order.getOrder_id());
            updateStatement.execute();
        } catch (SQLException e) {
        }
    }

    public void insert(Order order) {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = DBConnection.prepare(insertNewOrder);
            insertStatement.setInt(1, order.getOrder_id());
            insertStatement.setString(2, order.getStatus());
            insertStatement.setFloat(3, order.getItem().getItem_size());
            insertStatement.setFloat(4, order.getItem().getItem_weight());
            insertStatement.setInt(5,
                    order.getDestination().getDestination_id());
            insertStatement.setInt(6, order.getCustomer().getUser_id());
            insertStatement.execute();
        } catch (SQLException e) {

        }
    }

    public void delete(Order order) {
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = DBConnection.prepare(deleteOrder);
            deleteStatement.setInt(1, order.getOrder_id());
            deleteStatement.execute();
        } catch (SQLException e) {
        }
    }

    // insert into courier_log table 
    public void insertToLog(int courier_id) {
    	PreparedStatement insertStatement = null;
    	try {
    		insertStatement = DBConnection.prepare(insertLog);
    		insertStatement.setInt(1, courier_id);
    	}catch(SQLException e) {
    	}
    }
    
    // update courier_log table 
    public void updateLog(int courier_id, int sent_count) {
    	PreparedStatement updateStatement = null;
    	try {
    		updateStatement = DBConnection.prepare(updateLog);
    		updateStatement.setInt(1, courier_id);
    		updateStatement.setInt(2, sent_count);
    	}catch(SQLException e) {
    	}
    }
    
    // delete a log record for a specific day
    public void deleteLog(int courier_id, Date chosen_date) {
    	 PreparedStatement deleteStatement = null;
         try {
             deleteStatement = DBConnection.prepare(deleteLog);
             deleteStatement.setInt(1, courier_id);
             deleteStatement.setDate(2, chosen_date);
             deleteStatement.execute();
         } catch (SQLException e) {
         }
    }
}
