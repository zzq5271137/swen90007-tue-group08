package domain;

public class Item {
    private float item_size;
    private float item_weight;

    public Item(float item_size, float item_weight) {
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
}
