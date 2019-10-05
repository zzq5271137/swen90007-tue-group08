package domain;

import java.util.Date;
import java.util.List;

import datasource.DestinationMapper;
import datasource.IdentityMap;
import datasource.KeyTable;
import datasource.OrderMapper;
import datasource.LogMapper;
import datasource.UnityOfWork;

public class Courier extends User {
	
	private List<CourierLog> myLog;
	
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
    public void log() {
    	CourierLog currentLog = null;
    	LogMapper mapper = new LogMapper();
    	currentLog = mapper.findCurrentLog(getUser_id());
    	if(currentLog == null) {
    		mapper.insert(getUser_id());
    	}else {
    		int current_count = currentLog.getSentCount();
    		int newCount = current_count+1;
    		currentLog.setSentCount(newCount);
    		mapper.update(getUser_id(), newCount);
    	}
    }
    
    // delete my log
    public void deleteLog(Date chosen_date) {
    	java.sql.Date chosenDate=new java.sql.Date(chosen_date.getTime());
    	LogMapper mapper = new LogMapper();
    	mapper.delete(getUser_id(), chosenDate);
    }
}
