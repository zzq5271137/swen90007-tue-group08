package domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class OrderDTO {
    private int order_id;
    private String status;
    private ItemDTO itemDTO = null;
    private DestinationDTO destinationDTO;
    private int customer_id;
    private int courier_id;

    public OrderDTO() {
    }

    public OrderDTO(int order_id, String status, ItemDTO itemDTO,
            DestinationDTO destinationDTO, int customer_id, int courier_id) {
        this.order_id = order_id;
        this.status = status;
        this.itemDTO = itemDTO;
        this.destinationDTO = destinationDTO;
        this.customer_id = customer_id;
        this.courier_id = courier_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public DestinationDTO getDestinationDTO() {
        return destinationDTO;
    }

    public void setDestinationDTO(DestinationDTO destinationDTO) {
        this.destinationDTO = destinationDTO;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(int courier_id) {
        this.courier_id = courier_id;
    }

    public static void toXML(OrderDTO orderDTO, OutputStream outputStream) {
        XMLEncoder encoder = new XMLEncoder(outputStream);
        encoder.writeObject(orderDTO);
        encoder.close();
    }

    public static OrderDTO fromXML(InputStream inputStream) {
        XMLDecoder decoder = new XMLDecoder(inputStream);
        OrderDTO result = (OrderDTO) decoder.readObject();
        decoder.close();
        return result;
    }
}
