package datasource;

import java.sql.*;

public class DBConnection {
    // local
//    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/parceldelivery";
//    private static final String DB_USER = "postgres";
//    private static final String DB_PASSWORD = "ZZQ930603";

    // remote
  private static final String DB_CONNECTION = "jdbc:postgresql://ec2-54-83-201-84.compute-1.amazonaws.com:5432/d9lup48l3ne30t";
  private static final String DB_USER = "gnqgdcwzkcrzgj";
  private static final String DB_PASSWORD = "9921c2eb0aabd5163ceb5c5d42587eb820f31af9f8286d7d58614422d99a2290";
    
    static Connection dbConnection;

    public static PreparedStatement prepare(String stm) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            while (dbConnection == null) {
                dbConnection = getDBConnection();
            }

            preparedStatement = dbConnection.prepareStatement(stm);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return preparedStatement;
    }

    private static Connection getDBConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connection problem");
        return null;
    }
}
