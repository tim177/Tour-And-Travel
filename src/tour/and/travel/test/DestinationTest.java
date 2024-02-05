package tour.and.travel.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import tour.and.travel.*;

public class DestinationTest {
    private Destination destination;

    @BeforeEach
    public void setUp() {
        destination = new Destination("Test Destination");
    }

    @Test
    public void testAddActivity() {
        Activity activity = new Activity("Test Activity", "Description", 10.0, 5);
        destination.addActivity(activity);
        assertTrue(destination.getActivities().contains(activity));
    }

    @Test
    public void testRemoveActivity() {
        Activity activity = new Activity("Test Activity", "Description", 10.0, 5);
        destination.addActivity(activity);
        destination.removeActivity(activity);
        assertFalse(destination.getActivities().contains(activity));
    }

    // Add more tests as needed
}
