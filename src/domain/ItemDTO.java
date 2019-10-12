package domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class ItemDTO {
    private float item_size;
    private float item_weight;

    public ItemDTO(float item_size, float item_weight) {
        this.item_size = item_size;
        this.item_weight = item_weight;
    }

    public float getItem_size() {
        return item_size;
    }

    public void setItem_size(float item_size) {
        this.item_size = item_size;
    }

    public float getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(float item_weight) {
        this.item_weight = item_weight;
    }

    /**
     * Data Serialization function.
     * 
     * @param itemDTO
     * @param outputStream
     */
    public static void toXML(ItemDTO itemDTO, OutputStream outputStream) {
        XMLEncoder encoder = new XMLEncoder(outputStream);
        encoder.writeObject(itemDTO);
        encoder.close();
    }

    /**
     * Data De-Serialization function.
     * @param inputStream
     * @return
     */
    public static ItemDTO fromXML(InputStream inputStream) {
        XMLDecoder decoder = new XMLDecoder(inputStream);
        ItemDTO result = (ItemDTO) decoder.readObject();
        decoder.close();
        return result;
    }
}
