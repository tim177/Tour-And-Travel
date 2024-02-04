package tour.and.travel.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import tour.and.travel.*;

public class TravelPackageTest {
    private TravelPackage travelPackage;

    @BeforeEach
    public void setUp() {
        travelPackage = new TravelPackage("Test Package", 10);
    }

    @Test
    public void testAddDestination() {
        Destination destination = new Destination("Test Destination");
        travelPackage.addDestination(destination);
        assertTrue(travelPackage.getItinerary().contains(destination));
    }

    @Test
    public void testAddPassenger() {
        StandardPassenger passenger = new StandardPassenger("Test Passenger", "1", 100.0);
        travelPackage.addPassenger(passenger);
        assertTrue(travelPackage.getPassengers().contains(passenger));
    }

    @Test
    public void testRemovePassenger() {
        StandardPassenger passenger = new StandardPassenger("Test Passenger", "1", 100.0);
        travelPackage.addPassenger(passenger);
        travelPackage.removePassenger(passenger);
        assertFalse(travelPackage.getPassengers().contains(passenger));
    }

    // Add more tests as needed
}
