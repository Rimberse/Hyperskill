package carsharing.model;

import carsharing.controller.H2Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {
    private List<Customer> customers;
    private H2Client client;
    private Connection connection;
    private static final String tableName = "CUSTOMER";
    private static final String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            "NAME VARCHAR(50) UNIQUE NOT NULL, " +
            "RENTED_CAR_ID INTEGER, " +
            "PRIMARY KEY (ID), " +
            "CONSTRAINT FK_CUSTOMER_CAR FOREIGN KEY(RENTED_CAR_ID) " +
            "REFERENCES CAR(ID));";
    private static final String selectAllRecordsQuery = "SELECT * FROM " + tableName + ";";
    private static final String selectFilteredByRentedCarIdRecordsQuery = "SELECT * FROM " + tableName + " " +
            "WHERE RENTED_CAR_ID = ";
    private static final String insertRecordQuery = "INSERT INTO " + tableName + " " +
            "(NAME) VALUES (?);";
    private static final String updateRecordQuery = "UPDATE " + tableName + " " +
            "SET NAME = ? " +
            "WHERE ID = ?;";

    private static final String updateRecordRentedCarIdQuery = "UPDATE " + tableName + " " +
            "SET RENTED_CAR_ID = ? " +
            "WHERE ID = ?;";
    private static final String deleteRecordQuery = "DELETE FROM " + tableName + " WHERE ID = ?;";

    private CustomerDao() {
        try {
            client = H2Client.getInstance();
            customers = new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerDao(String dbName) {
        this();

        try {
            connection = client.connect(dbName);
            PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery);
            client.executeUpdate(preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customer> get(long id) {
        return Optional.ofNullable(customers.get((int) id - 1));
    }

    public List<Customer> getBy(long rentedCarId) {
        List<Customer> customersByRentedCarId = new ArrayList<>();
//        customersByRentedCarId = customers.stream().filter(customer -> customer.getRentedCarId() == rentedCarId)
//                .collect(Collectors.toList());

        try {
            ResultSet result = client.executeQuery(selectFilteredByRentedCarIdRecordsQuery + rentedCarId + ";");

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                Customer car = new Customer(id, name, rentedCarId);
                customersByRentedCarId.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customersByRentedCarId;
    }

    @Override
    public List<Customer> getAll() {
        customers = new ArrayList<>();

        try {
            ResultSet result = client.executeQuery(selectAllRecordsQuery);

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                long rentedCarId = result.getLong("RENTED_CAR_ID");
                Customer customer = new Customer(id, name, rentedCarId == 0 ? -1 : rentedCarId);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertRecordQuery);
            preparedStatement.setString(1, customer.getName());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer, String[] params) {
        customer.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));

        customers.add(customer);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateRecordQuery);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setLong(2, customer.getId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRentedCarId(Customer customer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateRecordRentedCarIdQuery);
            if (customer.getRentedCarId() == -1)
                preparedStatement.setNull(1, Types.INTEGER);
            else
                preparedStatement.setLong(1, customer.getRentedCarId());
            preparedStatement.setLong(2, customer.getId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        customers.remove(customer);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRecordQuery);
            preparedStatement.setLong(1, customer.getId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        try {
            client.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
