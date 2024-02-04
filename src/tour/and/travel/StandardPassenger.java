package tour.and.travel;

public class StandardPassenger extends Passenger {
    public StandardPassenger(String name, String passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }

    @Override
    public boolean canSignUpForActivity(double cost) {
        return balance >= cost;
    }
}