package distribution;

import java.sql.SQLException;

import datasource.IOrderMapper;
import datasource.OrderLockingMapper;
import domain.Courier;
import domain.Customer;
import domain.Destination;
import domain.Item;
import domain.Order;

public class OrderAssembler {

    public static OrderDTO createOrderDTO(Order order) throws SQLException {
        OrderDTO result = new OrderDTO();

        int order_id = order.getOrder_id();
        String status = order.getStatus();
        int customer_id = order.getCustomer().getUser_id();
        int courier_id = order.getCourier().getUser_id();

        result.setOrder_id(order_id);
        result.setStatus(status);
        result.setCustomer_id(customer_id);
        result.setCourier_id(courier_id);
        writeItemDTO(result, order);
        writeDestinationDTO(result, order);

        return result;
    }

    private static void writeItemDTO(OrderDTO orderDTO, Order order)
            throws SQLException {
        Item item = order.getItem();
        float item_size = item.getItem_size();
        float item_weight = item.getItem_weight();
        ItemDTO itemDTO = new ItemDTO(item_size, item_weight);
        orderDTO.setItemDTO(itemDTO);
    }

    private static void writeDestinationDTO(OrderDTO orderDTO, Order order)
            throws SQLException {
        Destination destination = order.getDestination();
        int destination_id = destination.getDestination_id();
        String address = destination.getAddress();
        DestinationDTO destinationDTO = new DestinationDTO(destination_id,
                address);
        orderDTO.setDestinationDTO(destinationDTO);
    }

    public static void updateOrder(OrderDTO orderDTO) {
        Order order = new Order();

        int order_id = orderDTO.getOrder_id();
        String status = orderDTO.getStatus();

        Customer customer = new Customer();
        int customer_id = orderDTO.getCustomer_id();
        customer.setUser_id(customer_id);

        Courier courier = new Courier();
        int courier_id = orderDTO.getCourier_id();
        courier.setUser_id(courier_id);

        order.setOrder_id(order_id);
        order.setStatus(status);
        order.setCustomer(customer);
        order.setCourier(courier);
        readItemDTO(order, orderDTO);
        readDestinationDTO(order, orderDTO);

        IOrderMapper mapper = new OrderLockingMapper();
        mapper.updateDetailOfOrder(order);
    }

    private static void readItemDTO(Order order, OrderDTO orderDTO) {
        Item item = new Item();
        ItemDTO itemDTO = orderDTO.getItemDTO();
        float item_size = itemDTO.getItem_size();
        float item_weight = itemDTO.getItem_weight();
        item.setItem_size(item_size);
        item.setItem_weight(item_weight);
        order.setItem(item);
    }

    private static void readDestinationDTO(Order order, OrderDTO orderDTO) {
        Destination destination = new Destination();
        DestinationDTO destinationDTO = orderDTO.getDestinationDTO();
        int destination_id = destinationDTO.getDestination_id();
        String address = destinationDTO.getAddress();
        destination.setDestination_id(destination_id);
        destination.setAddress(address);
        order.setDestination(destination);
    }
}
