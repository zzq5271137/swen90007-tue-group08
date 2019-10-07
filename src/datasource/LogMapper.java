package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CourierLog;

public class LogMapper {

    private static final String findAllLogs = "SELECT log_date, sent_count FROM courier_log WHERE courier_id = ?";
    private static final String findCurrentLog = "SELECT sent_count FROM courier_log WHERE courier_id = ? AND log_date = ?";
    private static final String insertLog = "INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (?, ?, 1)";
    private static final String updateLog = "UPDATE courier_log SET sent_count = ? WHERE courier_id = ? AND log_date = ?";
    private static final String deleteLog = "DELETE FROM courier_log WHERE courier_id = ? AND log_date = ?";

    // retrieve all log records of this courier
    public List<CourierLog> findAllLogsForCourier(int courier_id) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        List<CourierLog> myLogs = new ArrayList<>();
        try {
            findStatement = DBConnection.prepare(findAllLogs);
            findStatement.setInt(1, courier_id);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                String date = rs.getString(1);
                int sent_count = rs.getInt(2);
                CourierLog myLog = new CourierLog(courier_id, date,
                        sent_count);
                myLogs.add(myLog);
            }
        } catch (SQLException e) {
        }
        return myLogs;
    }

    // return the current sent_count of this courier for today
    public CourierLog findCurrentLog(int courier_id, String date) {
        int sent_count = -1;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        CourierLog currentLog = null;
        try {
            findStatement = DBConnection.prepare(findCurrentLog);
            findStatement.setInt(1, courier_id);
            findStatement.setString(2, date);
            rs = findStatement.executeQuery();
            if (rs.next()) {
                sent_count = rs.getInt(1);
                currentLog = new CourierLog(courier_id, date, sent_count);
            }
        } catch (SQLException e) {
        }
        return currentLog;
    }

    // insert into courier_log table
    public void insert(int courier_id, String date) {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = DBConnection.prepare(insertLog);
            insertStatement.setInt(1, courier_id);
            insertStatement.setString(2, date);
            insertStatement.execute();
        } catch (SQLException e) {
        }
    }

    // update courier_log table
    public void update(CourierLog courierLog) {
        PreparedStatement updateStatement = null;
        try {
            updateStatement = DBConnection.prepare(updateLog);
            updateStatement.setInt(1, courierLog.getSent_count());
            updateStatement.setInt(2, courierLog.getCourier_id());
            updateStatement.setString(3, courierLog.getDate());
            updateStatement.execute();
        } catch (SQLException e) {
        }
    }

    // delete a log record for a specific day
    public void delete(int courier_id, String date) {
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = DBConnection.prepare(deleteLog);
            deleteStatement.setInt(1, courier_id);
            deleteStatement.setString(2, date);
            deleteStatement.execute();
        } catch (SQLException e) {
        }
    }

}
