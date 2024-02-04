package tour.and.travel;

public class PremiumPassenger extends Passenger {
    public PremiumPassenger(String name, String passengerNumber) {
        super(name, passengerNumber, 0);
    }

    @Override
    public boolean canSignUpForActivity(double cost) {
        return true; // Premium passengers can sign up for activities for free
    }

    @Override
    public void signUpForActivity(double cost) {
        System.out.println(getName() + " signed up for an activity for free.");
    }
}