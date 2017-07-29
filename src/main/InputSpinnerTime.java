package main;

import org.jfree.ui.IntegerDocument;
import org.jfree.ui.LengthLimitingDocument;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.*;
import java.sql.Time;

public class InputSpinnerTime extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panelSpinners;
    private static FormTwo parentFrm;
    private static JSpinner parentSpinner;

    public InputSpinnerTime(FormTwo mainForm, JSpinner mainSpinner) {
        setUndecorated(true);
        parentFrm = mainForm;
        parentSpinner = mainSpinner;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 59, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(0, 0, 59, 1);

        panelSpinners.setVisible(false);


        spinner1.setModel(model1);
        spinner2.setModel(model2);
        spinner3.setModel(model3);

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



        spinner1.setEditor(new JSpinner.NumberEditor(spinner1));

        final JSpinner.NumberEditor editor = (JSpinner.NumberEditor) spinner1.getEditor();
//        final JSpinner.NumberEditor editor = (JSpinner.NumberEditor) parentSpinner.getEditor();

//        IntegerDocument filter = new IntegerDocument();

//        editor.getTextField().setDocument(new LengthLimitingDocument(1));
//        editor.getTextField().selectAll();
        editor.getTextField().setDocument(new IntegerDocument());

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.getTextField().selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        editor.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    int l = editor.getTextField().getSelectedText().length();
                    editor.getTextField().setText("");
                } catch (NullPointerException enull) {}
                if (editor.getTextField().getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume();


            }
        });

        String initHours = "00";
        String initMinutes = "00";
        String initSeconds = "00";

//        final JSpinner.DateEditor editor1 = (JSpinner.DateEditor) parentFrm.spinnerStart.getEditor();
        final JSpinner.DateEditor editor1 = (JSpinner.DateEditor) parentSpinner.getEditor();

        try {
            initHours = editor1.getTextField().getText(0, 2);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        try {
            initMinutes = editor1.getTextField().getText(3, 2);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        try {
            initSeconds = editor1.getTextField().getText(6, 2);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        textField1.setDocument(new IntegerDocument());
        textField1.setText(initHours);

        textField2.setDocument(new IntegerDocument());
        textField2.setText(initMinutes);

        textField3.setDocument(new IntegerDocument());
        textField3.setText(initSeconds);

        textField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField1.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textField1.getText().equals("") && Integer.parseInt(textField1.getText()) > 23) textField1.setText("23");
                else if (!textField1.getText().equals("") && Integer.parseInt(textField1.getText()) < 0) textField1.setText("00");
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    int l = textField1.getSelectedText().length();
                    textField1.setText("");
                } catch (NullPointerException ne) {

                }
                if (textField1.getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume();

            }
        });

        textField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField2.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textField2.getText().equals("") && Integer.parseInt(textField2.getText()) > 59) textField2.setText("59");
                else if (!textField2.getText().equals("") && Integer.parseInt(textField2.getText()) < 0) textField2.setText("00");
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    int l = textField2.getSelectedText().length();
                    textField2.setText("");
                } catch (NullPointerException ne) {

                }
                if (textField2.getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume();

            }
        });

        textField3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField3.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!textField3.getText().equals("") && Integer.parseInt(textField3.getText()) > 59) textField3.setText("59");
                else if (!textField3.getText().equals("") && Integer.parseInt(textField3.getText()) < 0) textField3.setText("00");
            }
        });
        textField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    int l = textField3.getSelectedText().length();
                    textField3.setText("");
                } catch (NullPointerException ne) {

                }
                if (textField3.getText().length() >= 2 ) // limit textfield to 3 characters
                    e.consume();

            }
        });


//        editor.getTextField().addCaretListener(new CaretListener() {
//            @Override
//            public void caretUpdate(CaretEvent e) {
//            if (editor.getTextField().getText().length() >= 2) editor.getTextField().setText(editor.getTextField().getText().substring(0, 1));
//            }
//        });

    }

    private void onOK() {
        // add your code here

        try {
            String newTime = textField1.getText() + ":" + textField2.getText() + ":" + textField3.getText();

//            parentFrm.spinnerStart.setValue(Time.valueOf(newTime));
//            parentFrm.spinnerEnd.transferFocus();

            parentSpinner.setValue(Time.valueOf(newTime));
            System.out.println(parentSpinner.getName());
            parentFrm.tabbedPane1.transferFocus();
//            if (parentSpinner.getName().equals("spinnerStart"))
//            {
//                parentFrm.spinnerEnd.transferFocus();
//            }
//            else parentFrm.spinnerEnd.transferFocus();


        } catch (Exception e) {}

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        parentFrm.tabbedPane1.transferFocus();
        dispose();
    }

    public static void main(String[] args) {
        InputSpinnerTime dialog = new InputSpinnerTime(parentFrm, parentSpinner);


//        dialog.setUndecorated(true);
//        dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
//
//        dialog.setDefaultCloseOperation(0);

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
