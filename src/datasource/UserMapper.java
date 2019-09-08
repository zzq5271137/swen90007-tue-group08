package datasource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Courier;
import domain.Customer;
import domain.User;

public class UserMapper {
    private static final String findUserWithUsernameAndPassword = "SELECT user_id, user_type FROM users WHERE username = ? and password = ?";

    public List<User> findWithUsernameAndPassword(String username,
            String password, String targetType) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        List<User> results = new ArrayList<>();
        try {
            findStatement = DBConnection
                    .prepare(findUserWithUsernameAndPassword);
            findStatement.setString(1, username);
            findStatement.setString(2, password);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt(1);
                String user_type = rs.getString(2);
                if (user_type.equalsIgnoreCase(targetType)) {
                    results.add(createUser(targetType, user_id));
                }
            }
        } catch (SQLException e) {
        }
        return results;
    }

    private User createUser(String user_type, int user_id) {
        if (user_type.equalsIgnoreCase(User.CUSTOMER_TYPE))
            return new Customer(user_id);
        else
            return new Courier(user_id);
    }
}
