package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public JPasswordField passwordField1;
    private JComboBox comboBox1;

    public PasswordWindow(Frame frame, FormTwo parentFrm) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        comboBox1.addItem("Розробник");
        comboBox1.addItem("Користувач");
        comboBox1.addItem("Сервіс");
        comboBox1.setSelectedIndex(0);

        this.passwordField1.requestFocusInWindow();
        this.passwordField1.grabFocus();

    }

    public boolean isAdmin() {
 //       String comboField = (String)comboBox1.getSelectedIndex();
        if (passwordField1.getText().equals("1") && "Розробник".equals(comboBox1.getSelectedItem()))
        return true;
        else return false;
    }

    public boolean isUser() {
        if (passwordField1.getText().equals("2") && "Користувач".equals(comboBox1.getSelectedItem())) return true;
        else return false;
    }

    public boolean isServiceStaff() {
        if (passwordField1.getText().equals("3") && "Сервіс".equals(comboBox1.getSelectedItem())) return true;
        else return false;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.exit(0);
        dispose();
    }

//    public static void main(String[] args) {
//        PasswordWindow dialog = new PasswordWindow();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }

}
