package domain;

public class Courier extends User {
    public Courier() {
        super();
        setUser_type(User.COURIER_TYPE);
    }

    public Courier(int user_id) {
        super();
        this.setUser_id(user_id);
    }
}
