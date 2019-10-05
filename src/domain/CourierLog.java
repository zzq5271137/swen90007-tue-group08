package domain;

import java.sql.Date;

public class CourierLog {
    private int courier_id;
    private Date date;
    private int sent_count;

    public CourierLog(int courier_id, Date date) {
        this.courier_id = courier_id;
        this.date = date;
    }

    public int getCourierId() {
        return this.courier_id;
    }

    public void setCourierId(int courier_id) {
        this.courier_id = courier_id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSentCount() {
        return this.sent_count;
    }

    public void setSentCount(int sent_count) {
        this.sent_count = sent_count;
    }

}
