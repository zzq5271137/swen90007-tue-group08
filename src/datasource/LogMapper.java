package datasource;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Courier;
import domain.CourierLog;
import domain.Order;

public class LogMapper {

    private static final String findAllLogs = "SELECT log_date, sent_count FROM courier_log WHERE courier_id = ?";
    private static final String findCurrentLog = "SELECT sent_count,log_date FROM courier_log WHERE courier_id = ? AND log_date = now()";
    private static final String insertLog = "INSERT INTO courier_log(courier_id, log_date, sent_count) VALUES (?, now(), 1)";
    private static final String updateLog = "UPDATE courier_log SET courier_id = ?, log_date = now(), sent_count = ?";    
    private static final String deleteLog = "DELETE FROM courier_log WHERE courier_id = ?, log_date = ?";

    // return the current sent_count of this courier for today 
    public CourierLog findCurrentLog(int courier_id) {
    	int sent_count = -1;
    	PreparedStatement findStatement = null;
    	ResultSet rs = null;
    	CourierLog currentLog = null;
    	 try {
             findStatement = DBConnection.prepare(findCurrentLog);
             findStatement.setInt(1, courier_id);
             rs = findStatement.executeQuery();
             while (rs.next()) {
                 sent_count = rs.getInt(1);
                 Date date = rs.getDate(2);
                 currentLog = new CourierLog(courier_id, date);
                 currentLog.setSentCount(sent_count);
             }
         } catch (SQLException e) {
         }
		return currentLog;
    }
    
    // return all log recordes of this courier
    public List<CourierLog> findAllLogs(int courier_id){
    	 PreparedStatement findStatement = null;
         ResultSet rs = null;
         List<CourierLog> myLogs = new ArrayList<>();
         try {
             findStatement = DBConnection.prepare(findAllLogs);
             findStatement.setInt(1, courier_id);
             rs = findStatement.executeQuery();
             while (rs.next()) {
                 Date date = rs.getDate(1);
                 int sent_count = rs.getInt(2);
                 CourierLog myLog = new CourierLog(courier_id, date);
                 myLog.setSentCount(sent_count);
                 myLogs.add(myLog);
             }
         } catch (SQLException e) {
         }
         return myLogs;
    }
    
    // insert into courier_log table 
    public void insert(int courier_id) {
    	PreparedStatement insertStatement = null;
    	try {
    		insertStatement = DBConnection.prepare(insertLog);
    		insertStatement.setInt(1, courier_id);
    	}catch(SQLException e) {
    	}
    }
    
    // update courier_log table 
    public void update(int courier_id, int sent_count) {
    	PreparedStatement updateStatement = null;
    	try {
    		updateStatement = DBConnection.prepare(updateLog);
    		updateStatement.setInt(1, courier_id);
    		updateStatement.setInt(2, sent_count);
    	}catch(SQLException e) {
    	}
    }
    
    // delete a log record for a specific day
    public void delete(int courier_id, Date chosen_date) {
    	 PreparedStatement deleteStatement = null;
         try {
             deleteStatement = DBConnection.prepare(deleteLog);
             deleteStatement.setInt(1, courier_id);
             deleteStatement.setDate(2, chosen_date);
             deleteStatement.execute();
         } catch (SQLException e) {
         }
    }

}
