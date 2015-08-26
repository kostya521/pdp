package com.epam.pdp;

import java.sql.*;
import java.util.Properties;

public class App {
    private static final String URL = "jdbc:postgresql://localhost/pdp_db";

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
        Properties props = new Properties();
        props.put("user", "pdp_user");
        props.put("password", "pdp_user");
        return DriverManager.getConnection(URL, props);
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
