package domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class DestinationDTO {
    private int destination_id;
    private String address;

    public DestinationDTO(int destination_id, String address) {
        this.destination_id = destination_id;
        this.address = address;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Data Serialization function.
     * 
     * @param destinationDTO
     * @param outputStream
     */
    public static void toXML(DestinationDTO destinationDTO,
            OutputStream outputStream) {
        XMLEncoder encoder = new XMLEncoder(outputStream);
        encoder.writeObject(destinationDTO);
        encoder.close();
    }

    /**
     * Data Deserialize function.
     * 
     * @param inputStream
     * @return
     */
    public static DestinationDTO fromXML(InputStream inputStream) {
        XMLDecoder decoder = new XMLDecoder(inputStream);
        DestinationDTO result = (DestinationDTO) decoder.readObject();
        decoder.close();
        return result;
    }
}
