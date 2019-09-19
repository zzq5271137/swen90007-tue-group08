package domain;

public class Destination {
    private int destination_id;
    private String address;

<<<<<<< HEAD
    public Destination(int destination_id) {
        this.destination_id = destination_id;
    }

    public Destination(int destination_id, String address) {
        this.destination_id = destination_id;
        this.address = address;
    }

    public int getDestination_id() {
=======
    public Destination(int id) {
		this.destination_id = id;
	}

	public int getDestination_id() {
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
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
