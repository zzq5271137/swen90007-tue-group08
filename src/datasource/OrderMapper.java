package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.prism.impl.Disposer.Record;

import domain.Customer;
import domain.Order;
import domain.User;

public class OrderMapper {
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
    }
}
