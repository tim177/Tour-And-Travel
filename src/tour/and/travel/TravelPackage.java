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

   public void printPassengerList() {
    DatabaseManager databaseManager = new DatabaseManager();
    String query = "SELECT p.name, p.passenger_number " +
                   "FROM passengers p " +
                   "JOIN passenger_activities pa ON p.id = pa.passenger_id " +
                   "JOIN activities a ON pa.activity_id = a.id " +
                   "JOIN destinations d ON a.destination_id = d.id " +
                   "WHERE d.package_id = (SELECT id FROM travel_packages WHERE name = ?)";
    try (Connection connection = databaseManager.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, name);
        try (ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Travel Package: " + name);
            System.out.println("Passenger Capacity: " + passengerCapacity);
            System.out.println("Number of Passengers Enrolled: " + passengers.size());
            System.out.println("Passengers:");
            while (resultSet.next()) {
                String passengerName = resultSet.getString("name");
                String passengerNumber = resultSet.getString("passenger_number");
                System.out.println("- Name: " + passengerName + ", Number: " + passengerNumber);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        databaseManager.closeConnection();
    }
}
   
   public void printPassengerDetails(int passengerNumber) {
    DatabaseManager databaseManager = new DatabaseManager();
    String query = "SELECT p.name, p.passenger_number, p.type, " +
                   "CASE " +
                   "    WHEN p.type = 'Standard' THEN s.balance " +
                   "    WHEN p.type = 'Gold' THEN g.balance " +
                   "    ELSE 'Unlimited' " +
                   "END AS balance, a.name AS activity_name, a.description AS activity_description, a.cost AS activity_cost " +
                   "FROM passengers p " +
                   "LEFT JOIN standard_passengers s ON p.id = s.passenger_id " +
                   "LEFT JOIN gold_passengers g ON p.id = g.passenger_id " +
                   "JOIN passenger_activities pa ON p.id = pa.passenger_id " +
                   "JOIN activities a ON pa.activity_id = a.id " +
                   "WHERE p.passenger_number = ?";
    try (Connection connection = databaseManager.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, passengerNumber);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String passengerName = resultSet.getString("name");
                String passengerType = resultSet.getString("type");
                String balance = resultSet.getString("balance");
                System.out.println("Passenger Name: " + passengerName);
                System.out.println("Passenger Number: " + passengerNumber);
                System.out.println("Passenger Type: " + passengerType);
                System.out.println("Balance: " + balance);
                System.out.println("Activities:");
                do {
                    String activityName = resultSet.getString("activity_name");
                    String activityDescription = resultSet.getString("activity_description");
                    double activityCost = resultSet.getDouble("activity_cost");
                    System.out.println("- Activity Name: " + activityName);
                    System.out.println("  Description: " + activityDescription);
                    System.out.println("  Cost: " + activityCost);
                } while (resultSet.next());
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
}
    