package com.epam.pdp;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Statement statement = null;
        try (Connection connection = getConnection();) {
            statement = connection.createStatement();

        } catch (SQLException e) {
            printErrors(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(App.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    private static void createTables(Connection connecton) throws SQLException {
        String createSuppliersTableQuery =
                "create table SUPPLIERS\n" +
                        "    (SUP_ID integer NOT NULL,\n" +
                        "    SUP_NAME varchar(40) NOT NULL,\n" +
                        "    STREET varchar(40) NOT NULL,\n" +
                        "    CITY varchar(20) NOT NULL,\n" +
                        "    STATE char(2) NOT NULL,\n" +
                        "    ZIP char(5),\n" +
                        "    PRIMARY KEY (SUP_ID));\n";

        String createCoffeesTableQuery =
                "create table COFFEES\n" +
                        "    (COF_NAME varchar(32) NOT NULL,\n" +
                        "    SUP_ID int NOT NULL,\n" +
                        "    PRICE numeric(10,2) NOT NULL,\n" +
                        "    SALES integer NOT NULL,\n" +
                        "    TOTAL integer NOT NULL,\n" +
                        "    PRIMARY KEY (COF_NAME),\n" +
                        "    FOREIGN KEY (SUP_ID)\n" +
                        "        REFERENCES SUPPLIERS (SUP_ID));";
        try (Statement statement = connecton.createStatement()) {
            statement.addBatch(createSuppliersTableQuery);
            statement.addBatch(createCoffeesTableQuery);
            statement.executeBatch();
            printWarnings(statement.getWarnings());
        }
    }

    private static void printWarnings(SQLWarning warnings) {
        while (warnings != null) {
            System.out.println("SQL State: " + warnings.getSQLState());
            System.out.println("Error Code: " + warnings.getErrorCode());
            System.out.println("Message: " + warnings.getMessage());
            warnings = warnings.getNextWarning();
        }
    }

    private static void printErrors(SQLException e) {
        while (e != null) {
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e = e.getNextException();
        }
    }

}
