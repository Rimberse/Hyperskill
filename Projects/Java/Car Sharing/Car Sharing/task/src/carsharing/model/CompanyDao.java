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

public class CompanyDao implements Dao<Company> {
    private List<Company> companies;
    private H2Client client;
    private Connection connection;
    private static final String tableName = "COMPANY";
    private static final String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
            "(ID INTEGER NOT NULL AUTO_INCREMENT, " +
            "NAME VARCHAR(50) UNIQUE NOT NULL, " +
            "PRIMARY KEY (ID));";
    private static final String selectAllRecordsQuery = "SELECT * FROM " + tableName + ";";
    private static final String insertRecordQuery = "INSERT INTO " + tableName + " (NAME) VALUES (?);";
    private static final String updateRecordQuery = "UPDATE " + tableName + " " +
            "SET NAME = ? " +
            "WHERE ID = ?;";
    private static final String deleteRecordQuery = "DELETE FROM " + tableName + " WHERE ID = ?;";

    private CompanyDao() {
        try {
            client = H2Client.getInstance();
            companies = new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CompanyDao(String dbName) {
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
    public Optional<Company> get(long id) {
        return Optional.ofNullable(companies.get((int) id - 1));
    }

    @Override
    public List<Company> getAll() {
        companies = new ArrayList<>();

        try {
            ResultSet result = client.executeQuery(selectAllRecordsQuery);

            while(result.next()) {
                long id = result.getLong("ID");
                String name = result.getString("NAME");
                Company company = new Company(id, name);
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       return companies;
    }

    @Override
    public void save(Company company) {
        companies.add(company);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertRecordQuery);
            preparedStatement.setString(1, company.getName());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Company company, String[] params) {
        company.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));

        companies.add(company);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateRecordQuery);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setLong(2, company.getId());
            client.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Company company) {
        companies.remove(company);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRecordQuery);
            preparedStatement.setLong(1, company.getId());
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
