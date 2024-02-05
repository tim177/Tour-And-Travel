package tour.and.travel.test;

import tour.and.travel.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PremiumPassengerTest {
    @Test
    public void testCanSignUpForActivity() {
        String name = "Test Premium Passenger";
        String passengerNumber = "123";

        PremiumPassenger passenger = new PremiumPassenger(name, passengerNumber);

        assertTrue(passenger.canSignUpForActivity(0.0)); // Premium passengers can sign up for activities for free
    }

    // Add more tests as needed
}
