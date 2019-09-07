package datasource;

import java.sql.*;

public class KeyTable {
    public static int getKey(String tableName) {
        // get the next key from the table
        String query = "SELECT next_id FROM keys WHERE table_name = ? FOR UPDATE";
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        int result = -1;
        try {
            findStatement = DBConnection.prepare(query);
            findStatement.setString(1, tableName);
            rs = findStatement.executeQuery();
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException e) {
        }
        
        // update the table with the next key
        int nextKey = result + 1;
        String update = "UPDATE keys SET next_id = ? WHERE table_name = ?";
        PreparedStatement updatePrepared = null;
        try {
            updatePrepared = DBConnection.prepare(update);
            updatePrepared.setInt(1, nextKey);
            updatePrepared.setString(2, tableName);
            updatePrepared.execute();
        } catch (SQLException e) {
        }
        return result;
    }
}
