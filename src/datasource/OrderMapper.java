package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import domain.Courier;
import domain.Customer;
import domain.Destination;
import domain.Item;
=======
import com.sun.prism.impl.Disposer.Record;

import domain.Customer;
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
import domain.Order;
import domain.User;

public class OrderMapper {
<<<<<<< HEAD

    private static final String findAllOrdersForCustomerLazy = "SELECT order_id FROM orders WHERE customer_id = ?";
    private static final String findOrderFromOrderId = "SELECT status, item_size, item_weight, destination_id, customer_id, courier_id FROM orders WHERE order_id = ?";
    private static final String updateOrder = "UPDATE orders SET item_size = ?, item_weight = ?, destination_id = ? WHERE order_id = ?";
    private static final String insertNewOrder = "INSERT INTO orders(order_id, status, item_size, item_weight, destination_id, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String deleteOrder = "DELETE FROM orders WHERE order_id = ?";

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
=======
	public IdentityMap<Order> map = new IdentityMap<Order>();
	
	private static final String findOrderFromOrderId = "SELECT order_id, status, item_size, item_weight, destination_id, " +
				"customer_id, courier_id FROM orders WHERE order_id = ?";
	
	private static final String findOrderFromCustomerId = "SELECT order_id, status, item_size, item_weight, destination_id, " +
			"customer_id, courier_id FROM orders WHERE customer_id = ?";
	
	private static final String findOrderFromCourierId = "SELECT order_id, status, item_size, item_weight, destination_id, " +
			"customer_id, courier_id FROM orders WHERE courier_id = ?";
	
	private static final String findOrderWithStatus = "SELECT order_id, status, item_size, item_weight, destination_id, " +
			"customer_id, courier_id FROM orders WHERE status = ?";
	
	// check whether the order objects registered in IdentityMap
	public Order checkIdentityMap(int order_id) {
		Order order = new Order();
		order = map.get(order_id);
		
		if(order == null) {
        	return order;
        }
		return null;
	}
	
    public ResultSet findOrderFromOrderId(int order_id) {
    	 PreparedStatement findStatement = null;
         ResultSet rs = null;
         try {
             findStatement = DBConnection.prepare(findOrderFromOrderId);
             findStatement.setInt(1, order_id);
             rs = findStatement.executeQuery();
         } catch (SQLException e) {
         }
    	return rs;
    }
    
    // used for customers looking for their order
    public ResultSet findOrderFromCustomerId(int customer_id){
    	 PreparedStatement findStatement = null;
         ResultSet rs = null;
         try {
             findStatement = DBConnection.prepare(findOrderFromCustomerId);
             findStatement.setInt(1, customer_id);
             rs = findStatement.executeQuery();
         } catch (SQLException e) {
         }
         return rs;
    }

    // used for couriers looking for their order
    public ResultSet findOrderFromCourierId(int courier_id){
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
    public ResultSet findOrderWithStatus(String status){
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

    // create new object -- Identity Map & Lazy Load & Foreign Key Mapping
    public List<Order> createOrder(ResultSet rs) throws SQLException {
    	Order order = new Order();
    	List<Order> results = new ArrayList<>();
    	UserMapper userMapper = new UserMapper();
    	User customer;
    	User courier;
    	 while (rs.next()) {
    		int order_id = rs.getInt(0);
         	order = checkIdentityMap(order_id);
         	// create the customer that associated with this Order,
         	// but not assign customer info in this Order obj because the use of Lazy Load Pattern
         	customer = userMapper.findWithUserId(rs.getInt(6));
         	courier = userMapper.findWithUserId(rs.getInt(7));
         	
         	if(order == null) { // if not exists in Identity Map, then create a new Order obj
         		order = new Order(order_id); // set order_id value only (Lazy Load)
                map.put(order_id, order); // add this new Order obj to IdetityMap
         	}
         	results.add(order);
         }
    	return results;
    }
    
    // Identity Field -- key tables
    public void insert(Order order) {
    	int order_id = KeyTable.getKey("orders"); // automatically generated primary key
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
    }
}
