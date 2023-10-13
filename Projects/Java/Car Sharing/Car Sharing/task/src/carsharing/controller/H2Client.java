package carsharing.controller;

import java.sql.*;

public class H2Client {
    private static H2Client instance;
    private static Connection connection;
    private static Statement statement;

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private H2Client() {}

    public Connection connect(String databaseFileName) throws ClassNotFoundException, SQLException {
        if (connection == null) {
            // Registering the JDBC database driver
            Class.forName (JDBC_DRIVER);
            // Opening the connection
            connection = DriverManager.getConnection ("jdbc:h2:./src/carsharing/db/" + databaseFileName);
            connection.setAutoCommit(true);
        }

        return connection;
    }

    public static H2Client getInstance() throws SQLException {
        if (instance == null)
            instance = new H2Client();

        return instance;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        // Creating a statement
        statement = connection.createStatement();
        // Executing a statement and receiving Resultset
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public int executeUpdate(PreparedStatement statement) throws SQLException {
        // Executing a statement and receiving Resultset
        int update = statement.executeUpdate();
        statement.close();
        return update;
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}
