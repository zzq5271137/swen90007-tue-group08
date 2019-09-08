package domain;

public class Destination {
    private int destination_id;
    private String address;

    public Destination(int id) {
		this.destination_id = id;
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

}
