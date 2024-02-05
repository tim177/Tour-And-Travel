package tour.and.travel;

import java.util.Scanner;

public class Input {
    public Input(){
        System.out.println("Input");

        DatabaseManager databaseManager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);
        
        //TRAVEL PACAKGES
        System.out.println("Do you want to add a travel package? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("Enter travel package details:");
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Passenger capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine().trim());

            String query = "INSERT INTO travel_packages (name, passenger_capacity) VALUES ('" + name + "', " + capacity + ")";
            databaseManager.insertData(query);
        }
        
        //DESTINATIONS
        System.out.println("Do you want to add a destination? (yes/no)");
        response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("Enter destination details:");
            System.out.print("Name: ");
            String destinationName = scanner.nextLine().trim();
            System.out.print("Enter travel package name for this destination: ");
            String packageName = scanner.nextLine().trim();

            // Get the ID of the travel package from the database
            String packageIdQuery = "SELECT id FROM travel_packages WHERE name = '" + packageName + "'";
            int packageId = databaseManager.getPackageId(packageIdQuery);

            if (packageId != -1) {
                String destinationQuery = "INSERT INTO destinations (name, package_id) VALUES ('" + destinationName + "', " + packageId + ")";
                databaseManager.insertData(destinationQuery);
                System.out.println("Destination added successfully.");
            } else {
                System.out.println("Failed to add destination. Travel package not found.");
            }
        }
        
        //ACTIVTIES
        System.out.println("Do you want to add an activity? (yes/no)");
        response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("Enter activity details:");
            System.out.print("Name: ");
            String activityName = scanner.nextLine().trim();
            System.out.print("Description: ");
            String description = scanner.nextLine().trim();
            System.out.print("Cost: ");
            double cost = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Capacity: ");
            int activityCapacity = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter destination name for this activity: ");
            String destinationName = scanner.nextLine().trim();

            // Get the ID of the destination from the database
            String destinationIdQuery = "SELECT id FROM destinations WHERE name = '" + destinationName + "'";
            int destinationId = databaseManager.getDestinationId(destinationIdQuery);

            if (destinationId != -1) {
                String activityQuery = "INSERT INTO activities (name, description, cost, capacity, destination_id) " +
                        "VALUES ('" + activityName + "', '" + description + "', " + cost + ", " + activityCapacity + ", " + destinationId + ")";
                databaseManager.insertData(activityQuery);
                System.out.println("Activity added successfully.");
            } else {
                System.out.println("Failed to add activity. Destination not found.");
            }
        }

        
        //PASSENGER
        System.out.println("Do you want to add a passenger? (yes/no)");
        response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("Enter passenger details:");
            System.out.print("Name: ");
            String passengerName = scanner.nextLine().trim();
            System.out.print("Passenger number: ");
            String passengerNumber = scanner.nextLine().trim();
            System.out.print("Passenger type (standard/gold/premium): ");
            String passengerType = scanner.nextLine().trim().toLowerCase();
            System.out.print("Passenger Balance: ");
            String passengerBalance = scanner.nextLine().trim();
            System.out.print("Enter travel package name for this Passenger: ");
            String packageNameRef = scanner.nextLine().trim();
            
            String passengerQuery = "INSERT INTO passengers (name, passenger_number, type, balance, travel_package_name) " +
                    "VALUES ('" + passengerName + "', '" + passengerNumber + "', '" + passengerType + "', '" + passengerBalance + "', '" + packageNameRef + "')";
            databaseManager.insertData(passengerQuery);

            System.out.println("Passenger added successfully.");
        }
        
        //ADD ACTIVITY FOR PASSENGER 
        System.out.println("Do you want to add an activity for a passenger? (yes/no)");
        response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.println("Enter passenger ID: ");
            int passengerId = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Enter activity name: ");
            String activityName = scanner.nextLine().trim();

            // Get the ID of the activity from the database
            String activityIdQuery = "SELECT id FROM activities WHERE name = '" + activityName + "'";
            int activityId = databaseManager.getActivityId(activityIdQuery);

            if (activityId != -1) {
                String passengerActivityQuery = "INSERT INTO passenger_activities (passenger_id, activity_id) " +
                        "VALUES (" + passengerId + ", " + activityId + ")";
                databaseManager.insertData(passengerActivityQuery);
                System.out.println("Activity added for passenger successfully.");
            } else {
                System.out.println("Failed to add activity. Activity not found.");
            }
        }

        databaseManager.closeConnection();
        
    }
 public static void main(String[] agrs){
        new Input();
 }   
}
