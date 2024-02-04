package tour.and.travel;

public class GoldPassenger extends Passenger {
    private static final double DISCOUNT_RATE = 0.10;

    public GoldPassenger(String name, String passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }

    @Override
    public boolean canSignUpForActivity(double cost) {
        return balance >= (cost * (1 - DISCOUNT_RATE));
    }

    @Override
    public void signUpForActivity(double cost) {
        if (canSignUpForActivity(cost)) {
            double discountedCost = cost * (1 - DISCOUNT_RATE);
            balance -= discountedCost;
            System.out.println(getName() + " signed up for an activity with 10% discount. Remaining balance: " + getBalance());
        } else {
            System.out.println(getName() + " cannot sign up for the activity due to insufficient balance.");
        }
    }
}
