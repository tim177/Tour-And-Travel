package tour.and.travel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
        } else {
            System.out.println("Cannot add passenger. Package is full.");
        }
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Destination> itinerary) {
        this.itinerary = itinerary;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    
   public void printItinerary() {
    System.out.println("Inside printItinerary");
    String packageName = JOptionPane.showInputDialog("Enter the name of the travel package:");
    if (packageName != null && !packageName.isEmpty()) {
        DatabaseManager databaseManager = new DatabaseManager();
        String query = "SELECT d.name AS destination_name, a.name AS activity_name, a.description, a.cost, a.capacity " +
                "FROM destinations d " +
                "JOIN activities a ON d.id = a.destination_id " +
                "WHERE d.package_id = (SELECT id FROM travel_packages WHERE name = ?)";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, packageName);
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Travel Package: " + packageName);
                while (resultSet.next()) {
                    String destinationName = resultSet.getString("destination_name");
                    String activityName = resultSet.getString("activity_name");
                    String description = resultSet.getString("description");
                    double cost = resultSet.getDouble("cost");
                    int capacity = resultSet.getInt("capacity");
                    System.out.println("- Destination: " + destinationName);
                    System.out.println("  - Activity: " + activityName);
                    System.out.println("    - Description: " + description);
                    System.out.println("    - Cost: " + cost);
                    System.out.println("    - Capacity: " + capacity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection();
        }
    } else {
        System.out.println("Invalid input. Please enter a valid travel package name.");
    }
}
   
   public void printPassengerDetails(int passengerNumber) {
        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection connection = databaseManager.getConnection()) {
            String query = "SELECT p.name, p.passenger_number, p.type, p.balance " +
               "FROM passengers p " +
               "WHERE p.passenger_number = '" + passengerNumber + "'";
                
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    String passengerName = resultSet.getString("name");
                    String passengerType = resultSet.getString("type");
                    String balance = resultSet.getString("balance");
                    System.out.println("Passenger Name: " + passengerName);
                    System.out.println("Passenger Number: " + passengerNumber);
                    System.out.println("Passenger Type: " + passengerType);
                    System.out.println("Balance: " + balance);
                } else {
                    System.out.println("Passenger with number " + passengerNumber + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection();
       }
    }
  
    public void printPassengerList() {
    String packageName = JOptionPane.showInputDialog("Enter the name of the travel package:");

    DatabaseManager databaseManager = new DatabaseManager();
    String packageQuery = "SELECT id FROM travel_packages WHERE name = '" + packageName + "'";
    try (Connection connection = databaseManager.getConnection();
         Statement packageStatement = connection.createStatement();
         ResultSet packageResultSet = packageStatement.executeQuery(packageQuery)) {
        if (packageResultSet.next()) {
            int packageId = packageResultSet.getInt("id");
            String query = "SELECT p.name, p.passenger_number, p.type, p.balance " +
                    "FROM passengers p " +
                    "WHERE p.travel_package_name = '" + packageName + "'";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String passengerName = resultSet.getString("name");
                    String passengerNumber = resultSet.getString("passenger_number");
                    String passengerType = resultSet.getString("type");
                    double balance = resultSet.getDouble("balance");
                    System.out.println("Passenger Name: " + passengerName);
                    System.out.println("Passenger Number: " + passengerNumber);
                    System.out.println("Passenger Type: " + passengerType);
                    System.out.println("Balance: " + balance);
                    System.out.println("------------------------");
                }
            }
        } else {
            System.out.println("Travel package not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        databaseManager.closeConnection();
    }
}

}
    