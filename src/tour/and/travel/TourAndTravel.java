package tour.and.travel;

import javax.swing.*;

public class TourAndTravel {

    public static void main(String[] args) {
        String[] options = {"Input Values", "Output Values"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // Call Input class
            Input input = new Input();
//            input.getInputValues();
        } else  {
            // Call Output class
            Output output = new Output();
//            output.outputValues();
        } 
    }
    
}
