    package tour.and.travel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Output extends JFrame implements ActionListener {
    private JButton btnTravelPackage, btnActivity, btnDestination, btnPassengers;

    public Output() {
        setTitle("Tour and Travel Management System");
        setBounds(300, 150, 300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 1, 10, 10));

        btnTravelPackage = new JButton("Travel Package Detail");
        btnTravelPackage.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTravelPackage.setForeground(Color.WHITE);
        btnTravelPackage.setBackground(new Color(47, 79, 79));
        btnTravelPackage.addActionListener(this);
        add(btnTravelPackage);

        btnActivity = new JButton("Passenger List");
        btnActivity.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnActivity.setForeground(Color.WHITE);
        btnActivity.setBackground(new Color(47, 79, 79));
        btnActivity.addActionListener(this);
        add(btnActivity);

        btnDestination = new JButton("Passenger Detail");
        btnDestination.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDestination.setForeground(Color.WHITE);
        btnDestination.setBackground(new Color(47, 79, 79));
        btnDestination.addActionListener(this);
        add(btnDestination);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // Handle button actions here
        if (ae.getSource() == btnTravelPackage) {
            System.out.println("Here is the detail og the Travel Package🛍️🛍️🛍️");
            TravelPackage travelPackage = new TravelPackage("YourPackage", 10); // Replace with actual package name and capacity
            travelPackage.printItinerary();
        } else if (ae.getSource() == btnActivity) {
            System.out.println("Here is the Detail of Passenger🚢🚢🚢");
            TravelPackage travelPackage = new TravelPackage("YourPackage", 10);
            travelPackage.printPassengerList();
        } else if (ae.getSource() == btnDestination) {
            System.out.println("Destination button clicked");
            TravelPackage travelPackage = new TravelPackage("YourPackage", 10);
            int passengerNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the Passener:"));
            travelPackage.printPassengerDetails(passengerNumber);
        }
    }

    public static void main(String[] args) {
        new Output();
    }
}
