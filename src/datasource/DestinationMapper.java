package datasource;

<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Destination;

public class DestinationMapper {
    private static final String findDestinationFromDestinationId = "SELECT address FROM destination WHERE destination_id = ?";
    private static final String findAllAddressForCustomer = "SELECT destination_id FROM user_has_destination WHERE user_id = ?";
    private static final String insertIntoAddress = "INSERT INTO destination(destination_id, address) VALUES(?, ?)";
    private static final String insertIntoAssociationTable = "INSERT INTO user_has_destination(user_id, destination_id) VALUES (?, ?)";

    public Destination findDestinationFromDestinationId(int destination_id) {
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        Destination destination = null;
        try {
            findStatement = DBConnection
                    .prepare(findDestinationFromDestinationId);
            findStatement.setInt(1, destination_id);
            rs = findStatement.executeQuery();
            rs.next();
            String address = rs.getString(1);
            destination = new Destination(destination_id, address);
        } catch (SQLException e) {
        }
        return destination;
    }

    public List<Destination> findAllAddressForCustomer(int user_id) {
        PreparedStatement findAddresses = null;
        ResultSet rs = null;
        List<Destination> results = new ArrayList<>();
        try {
            findAddresses = DBConnection.prepare(findAllAddressForCustomer);
            findAddresses.setInt(1, user_id);
            rs = findAddresses.executeQuery();
            while (rs.next()) {
                int destination_id = rs.getInt(1);
                results.add(findDestinationFromDestinationId(destination_id));
            }
        } catch (SQLException e) {
        }
        return results;
    }

    public void insert(int user_id, Destination destination) {
        PreparedStatement insertAddressStatement = null;
        PreparedStatement insertAssociationStatement = null;
        try {
            insertAddressStatement = DBConnection.prepare(insertIntoAddress);
            insertAddressStatement.setInt(1, destination.getDestination_id());
            insertAddressStatement.setString(2, destination.getAddress());
            insertAddressStatement.execute();

            insertAssociationStatement = DBConnection
                    .prepare(insertIntoAssociationTable);
            insertAssociationStatement.setInt(1, user_id);
            insertAssociationStatement.setInt(2,
                    destination.getDestination_id());
            insertAssociationStatement.execute();
        } catch (SQLException e) {
        }
    }
=======
import domain.Destination;

public class DestinationMapper {
	public IdentityMap<Destination> map = new IdentityMap<Destination>();
	
	private static final String findOrderFromOrderId = "";
>>>>>>> f900d601036224e9cd1e1724a12ccccc51e4163f
}
