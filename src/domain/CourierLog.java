package domain;

public class CourierLog {
    private int courier_id;
    private String date;
    private int sent_count;

    public CourierLog(int courier_id, String date, int sent_count) {
        this.courier_id = courier_id;
        this.date = date;
        this.sent_count = sent_count;
    }

    public int getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(int courier_id) {
        this.courier_id = courier_id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSent_count() {
        return sent_count;
    }

    public void setSent_count(int sent_count) {
        this.sent_count = sent_count;
    }
}
