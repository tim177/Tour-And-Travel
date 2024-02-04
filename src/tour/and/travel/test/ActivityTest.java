/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tour.and.travel.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import tour.and.travel.*;

public class ActivityTest {
    @Test
    public void testGettersAndSetters() {
        String name = "Test Activity";
        String description = "Description";
        double cost = 10.0;
        int capacity = 5;

        Activity activity = new Activity(name, description, cost, capacity);

        assertEquals(name, activity.getName());
        assertEquals(description, activity.getDescription());
        assertEquals(cost, activity.getCost());
        assertEquals(capacity, activity.getCapacity());

        // Test setters
        String newName = "New Test Activity";
        String newDescription = "New Description";
        double newCost = 15.0;
        int newCapacity = 8;

        activity.setName(newName);
        activity.setDescription(newDescription);
        activity.setCost(newCost);
        activity.setCapacity(newCapacity);

        assertEquals(newName, activity.getName());
        assertEquals(newDescription, activity.getDescription());
        assertEquals(newCost, activity.getCost());
        assertEquals(newCapacity, activity.getCapacity());
    }

    // Add more tests as needed
}
