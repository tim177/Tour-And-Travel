package tour.and.travel;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tourandtravel";
    private static final String USER = "root";
    private static final String PASSWORD = "qwertyuiop";

    private Connection connection;

    public DatabaseManager() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database😐😐😐");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void insertData(String query) {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Executing query: " + query);
            statement.executeUpdate(query);
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            System.out.println("Failed to insert data: " + e.getMessage());
        }
    }
    
    public int getPackageId(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get package ID: " + e.getMessage());
        }
        return -1; // Return -1 if the package ID is not found
    }
 
    public int getDestinationId(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get destination ID: " + e.getMessage());
        }
        return -1; // Return -1 if the destination ID is not found
    }
    
     public int getActivityId(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get activity ID: " + e.getMessage());
        }
        return -1; // Return -1 if the activity ID is not found
    }
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}
