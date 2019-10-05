package domain;

import java.util.Date;
import java.util.List;

import datasource.DestinationMapper;
import datasource.IdentityMap;
import datasource.KeyTable;
import datasource.OrderMapper;
import datasource.UnityOfWork;

public class Courier extends User {
	
    public Courier() {
        super();
        setUser_type(User.COURIER_TYPE);
    }

    public Courier(int user_id) {
        super();
        this.setUser_id(user_id);
    }
    
    // get all orders that belong to this courier
    public List<Order> getAllOrders() {
    	OrderMapper mapper = new OrderMapper();
        orders = mapper.findAllOrdersForCourier(getUser_id());
        setOrders(orders);
        return orders;
    }
    
    // change the order status to "Delivered" directly
    public void markDelivered(int order_id) {
    	Order order = new Order();
    	IdentityMap<Order> iMap = IdentityMap.getInstance(order);
    	order = iMap.get(order_id);
    	
    	order.setStatus("Delivered");
    	UnityOfWork.newCurrent();
        UnityOfWork.getCurrent().registerDirty(order);
        UnityOfWork.getCurrent().commit();
    }
    
    // pick Confirmed orders from order pool to individual tube
    public void pickOrders(int[] order_ids) {
    	int amount = order_ids.length;
    	Order order = new Order();
    	for(int i = 0; i<amount; i++) {
    		IdentityMap<Order> iMap = IdentityMap.getInstance(order);
    		order = iMap.get(order_ids[i]);
    		order.setStatus("Shipped");

        	UnityOfWork.newCurrent();
            UnityOfWork.getCurrent().registerDirty(order);
            UnityOfWork.getCurrent().commit();
    	}
    }

    // automatically update courier_log table by increasing the sent_count by 1
    public void addToLog() {
    	int sent_count = -1;
    	OrderMapper mapper = new OrderMapper();
    	sent_count = mapper.findCurrentLog(getUser_id());
    	if(sent_count == -1) {
    		mapper.insertToLog(getUser_id());
    	}else {
    		mapper.updateLog(getUser_id(), ++sent_count);
    	}
    }
    
    // delete my log
    public void deleteLog(Date chosen_date) {
    	java.sql.Date chosenDate=new java.sql.Date(chosen_date.getTime());
    	OrderMapper mapper = new OrderMapper();
    	mapper.deleteLog(getUser_id(), chosenDate);
    }
}
