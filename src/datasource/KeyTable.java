package datasource;

import java.sql.*;

public class KeyTable {
    private static final String findNextIdWithTableName = "SELECT next_id FROM keys WHERE table_name = ? FOR UPDATE";
    private static final String updateNextIdWithTableName = "UPDATE keys SET next_id = ? WHERE table_name = ?";

    public static int getKey(String tableName) {
        // get the next key from the table
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        int result = -1;
        try {
            findStatement = DBConnection.prepare(findNextIdWithTableName);
            findStatement.setString(1, tableName);
            rs = findStatement.executeQuery();
            rs.next();
            result = rs.getInt(1);
        } catch (SQLException e) {
        }

        // update the table with the next key
        int nextKey = result + 1;
        PreparedStatement updatePrepared = null;
        try {
            updatePrepared = DBConnection.prepare(updateNextIdWithTableName);
            updatePrepared.setInt(1, nextKey);
            updatePrepared.setString(2, tableName);
            updatePrepared.execute();
        } catch (SQLException e) {
        }
        return result;
    }
}
