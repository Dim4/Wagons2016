package main;

import javax.swing.*;

/**
 * Created by DM on 04.10.2016.
 */
public class Wagons {

    private static DatabaseOperations databaseOperations = new DatabaseOperations();

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        System.out.println("Hello, wagons!");
        databaseOperations.connectDB();
        databaseOperations.closeDB();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
