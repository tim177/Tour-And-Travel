package tour.and.travel.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tour.and.travel.*;

public class StandardPassengerTest {
    @Test
    public void testCanSignUpForActivityWithSufficientBalance() {
        String name = "Test Standard Passenger";
        String passengerNumber = "123";
        double initialBalance = 100.0;
        double activityCost = 50.0;

        StandardPassenger passenger = new StandardPassenger(name, passengerNumber, initialBalance);
        
        assertTrue(passenger.canSignUpForActivity(activityCost));
    }

    @Test
    public void testCanSignUpForActivityWithInsufficientBalance() {
        String name = "Test Standard Passenger";
        String passengerNumber = "123";
        double initialBalance = 50.0;
        double activityCost = 100.0;

        StandardPassenger passenger = new StandardPassenger(name, passengerNumber, initialBalance);

        assertFalse(passenger.canSignUpForActivity(activityCost));
    }

    // Add more tests as needed
}
