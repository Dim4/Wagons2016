package main;

import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by DM on 13.10.2016.
 */
public class MainWindow extends JPanel {

    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTabbedPane tabbedPane1;
    private JRadioButton radioButton1;
    private JButton button4;
    private JComboBox comboBox1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JTable table1;
    private JTable table2;
    private boolean DEBUG = false;

    public MainWindow() {
        super(new GridLayout(1, 0));

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        /*final JTable*/
        table2 = new JTable(data, columnNames);
        table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table2.setFillsViewportHeight(true);

        if (DEBUG) {
            table2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table2);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

//    public MainWindow() {
//        button4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Hello, Button!");
//            }
//        });
//    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Wagons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create and set up the content pane.
        SimpleTableDemo newContentPane = new SimpleTableDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);
        frame.setContentPane(new MainWindow().panel1);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
//        JFrame frame = new JFrame("Wagons");
//        frame.setContentPane(new MainWindow().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
