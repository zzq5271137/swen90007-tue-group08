package distribution;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import datasource.IOrderMapper;
import datasource.OrderLockingMapper;
import domain.Order;

public class OrderFacade {

    public OrderDTO getOrder(int order_id) throws SQLException {
        IOrderMapper mapper = new OrderLockingMapper();
        Order order = mapper.findOrderFromOrderId(order_id);
        OrderDTO orderDTO = OrderAssembler.createOrderDTO(order);
        return orderDTO;
    }

    public void updateOrder(OrderDTO orderDTO) {
        OrderAssembler.updateOrder(orderDTO);
    }

    public void getOrderXML(int order_id, OutputStream outputStream)
            throws SQLException {
        OrderDTO orderDTO = getOrder(order_id);
        OrderDTO.toXML(orderDTO, outputStream);
    }

    public void updateOrderXML(InputStream inputStream) {
        OrderDTO orderDTO = OrderDTO.fromXML(inputStream);
        OrderAssembler.updateOrder(orderDTO);
    }
}
