package tour.and.travel;

// Common base class for all passengers

import java.util.ArrayList;
import java.util.List;

abstract class Passenger {
    private String name;
    private String passengerNumber;
    protected double balance;
    private List<Activity> activities = new ArrayList<>();
    
    public Passenger(String name, String passengerNumber, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public double getBalance() {
        return balance;
    }
    
    public List<Activity> getActivities() {
        return activities;
    }
    
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract boolean canSignUpForActivity(double cost);

    public void signUpForActivity(double cost) {
        if (canSignUpForActivity(cost)) {
            balance -= cost;
            System.out.println(name + " signed up for an activity. Remaining balance: " + balance);
        } else {
            System.out.println(name + " cannot sign up for the activity due to insufficient balance.");
        }
    }
}
