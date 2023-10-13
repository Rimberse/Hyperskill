package carsharing.model;

import carsharing.controller.H2Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarDao implements Dao<Car> {
    private List<Car> cars;
    private H2Client client;
    private Connection connection;
    private static final String tableName = "CAR";
    private static final String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            "NAME VARCHAR(50) UNIQUE NOT NULL, " +
            "COMPANY_ID INTEGER NOT NULL, " +
            "PRIMARY KEY (ID), " +
            "CONSTRAINT FK_CAR_COMPANY FOREIGN KEY(COMPANY_ID) " +
            "REFERENCES COMPANY(ID));";
    private static final String selectAllRecordsQuery = "SELECT * FROM " + tableName + ";";
    private static final String selectFilteredByCompanyIdRecordsQuery = "SELECT * FROM " + tableName + " " +
            "WHERE COMPANY_ID = ";
    private static final String selectFilteredByIdNotPresentInCustomerRecordsQuery = "SELECT * FROM " + tableName + " " +
            "WHERE NOT EXISTS " +
            "(SELECT 1 FROM CUSTOMER " +
            "WHERE " + tableName + ".ID = CUSTOMER.RENTED_CAR_ID) " +
            "AND COMPANY_ID = ";
    private static final String insertRecordQuery = "INSERT INTO " + tableName + " " +
            "(NAME, COMPANY_ID) VALUES (?, ?);";
    private static final String updateRecordQuery = "UPDATE " + tableName + " " +
            "SET NAME = ? " +
            "WHERE ID = ?;";
    private static final String deleteRecordQuery = "DELETE FROM " + tableName + " WHERE ID = ?;";

    private CarDao() {
        try {
            client = H2Client.getInstance();
            cars = new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CarDao(String dbName) {
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
    public Optional<Car> get(long id) {
        getAll();
        return Optional.ofNullable(id == -1 ? null : cars.get((int) id - 1));
    }

    public List<Car> getBy(long companyId) {
        List<Car> carsByCompany = new ArrayList<>();
//        carsByCompany = cars.stream().filter(car -> car.getCompanyId() == companyId)
//                .collect(Collectors.toList());

        try {
            ResultSet result = client.executeQuery(selectFilteredByCompanyIdRecordsQuery + companyId + ";");

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                Car car = new Car(id, name, companyId);
                carsByCompany.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carsByCompany;
    }

    public List<Car> getNonRentedBy(long companyId) {
        List<Car> carsByCompany = new ArrayList<>();

        try {
            ResultSet result = client.executeQuery(selectFilteredByIdNotPresentInCustomerRecordsQuery + companyId + ";");

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                Car car = new Car(id, name, companyId);
                carsByCompany.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carsByCompany;
    }

    @Override
    public List<Car> getAll() {
        cars = new ArrayList<>();

        try {
            ResultSet result = client.executeQuery(selectAllRecordsQuery);

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                long companyId = result.getLong("COMPANY_ID");
                Car car = new Car(id, name, companyId);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public void save(Car car) {
        cars.add(car);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertRecordQuery);
            preparedStatement.setString(1, car.getName());
            preparedStatement.setLong(2, car.getCompanyId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car car, String[] params) {
        car.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));

        cars.add(car);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateRecordQuery);
            preparedStatement.setString(1, car.getName());
            preparedStatement.setLong(2, car.getId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car car) {
        cars.remove(car);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRecordQuery);
            preparedStatement.setLong(1, car.getId());
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
