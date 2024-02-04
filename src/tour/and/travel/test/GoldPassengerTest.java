/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tour.and.travel.test;

import tour.and.travel.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoldPassengerTest {
    @Test
    public void testCanSignUpForActivityWithSufficientBalance() {
        String name = "Test Gold Passenger";
        String passengerNumber = "123";
        double initialBalance = 100.0;
        double activityCost = 50.0;

        GoldPassenger passenger = new GoldPassenger(name, passengerNumber, initialBalance);

        assertTrue(passenger.canSignUpForActivity(activityCost));
    }

    @Test
    public void testCanSignUpForActivityWithInsufficientBalance() {
        String name = "Test Gold Passenger";
        String passengerNumber = "123";
        double initialBalance = 50.0;
        double activityCost = 100.0;

        GoldPassenger passenger = new GoldPassenger(name, passengerNumber, initialBalance);

        assertFalse(passenger.canSignUpForActivity(activityCost));
    }

    // Add more tests as needed
}
