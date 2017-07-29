package main;

//import com.intellij*;

//import com.itextpdf.layout.Document;
import com.itextpdf.awt.FontMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.awt.PdfGraphics2D;
//import com.itextpdf.text.pdf.PdfTemplate;
//import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.gantt.XYTaskDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.time.*;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.util.List;


/**
 * Created by DM on 16.10.2016.
 */
public class FormTwo extends JFrame {
    private JPanel graphDig;
//    private JTable tableJointTable;
    private ChartPanel chartSpeed;
    private ChartPanel chartPress1;
    private ChartPanel chartAllDigits;
    private ChartPanel chartAllDigits2;
    private JPanel panelOne;
    private JPanel northPanel;
    private JPanel soutnPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JPanel tablePanel;
    private JPanel buttonsPanel;
    private JButton button1;
    private JButton button2;
    private JButton loadToTableButton;
    public JTabbedPane tabbedPane1;
    private JTable dataTable;
    private JButton button4;
    private JButton button5;
    private JScrollPane tableScrollPane;
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JComboBox comboBox2;
    private JSpinner spinner2;
    private JComboBox comboBox3;
    private JScrollPane tab3ScrollPane;
    private JPanel panelForChart;
    private JScrollPane scrollFive;
    private JLabel toolLabel;
    private JScrollPane scrollSix;
    private JScrollPane scrollFiveTwo;
    private JButton button6;
    private JScrollPane scrollFour;
    private JButton Button2;
    private JCheckBox reading;
    private JScrollPane scrollSeven;
    private JScrollPane scrollFourTwo;
    private JScrollPane scrollSevenTwo;
    private JScrollPane scrollEightTable;
    private JScrollPane scrollEightChart;
    private JButton dropDatabaseButton;
    private JButton button7;
    private JButton button8;
    private JScrollPane scrollNine;
    private JButton button9;
    private JButton button10;
    private JPanel tabOne;
    private JSlider slider1;
    private JScrollPane scrollBigTable;
    private JScrollPane scrollSelectGraphs;
    private JPanel graphLeftPanel;
    private JPanel graphCheckBoxPanel;
    private JPanel graphLeftButtonPanel;
    private JButton refreshGraphsButton;
    private JSlider slider2;
    private JScrollPane scrollGraphs;
    private JButton refreshButton;
    private JSlider graphsSlider;
    private JScrollPane graphScrollPanel;
    private JPanel graphsScrollChecks;
    private JCheckBox Speed;
    private JCheckBox Pres1;
    private JCheckBox Pres2;
    private  JCheckBox Dig1;
    private  JCheckBox Dig2;
    private  JCheckBox Dig3;
    private  JCheckBox Dig4;
    private  JCheckBox Dig5;
    private  JCheckBox Dig6;
    private  JCheckBox Dig7;
    private  JCheckBox Dig8;
    private  JCheckBox Dig9;
    private  JCheckBox Dig10;
    private  JCheckBox Dig11;
    private  JCheckBox Dig12;
    private  JCheckBox Dig13;
    private  JCheckBox Dig14;
    private  JCheckBox Dig15;
    private  JCheckBox Dig16;
    private  JCheckBox Dig17;
    private  JCheckBox Dig18;
    private  JCheckBox Dig19;
    private  JCheckBox Dig20;
    private  JCheckBox Dig21;
    private  JCheckBox Dig22;
    private  JCheckBox Dig23;
    private  JCheckBox Dig24;
    private  JCheckBox Dig25;
    private  JCheckBox Dig26;
    private  JCheckBox Dig27;
    private  JCheckBox Dig28;
    private  JCheckBox Dig29;
    private  JCheckBox Dig30;
    private  JCheckBox Dig31;
    private  JCheckBox Dig32;
    private  JCheckBox Dig33;
    private  JCheckBox Dig34;
    private  JCheckBox Dig35;
    private  JCheckBox Dig36;
    private  JCheckBox Dig37;
    private JCheckBox SelectAll;
    public JProgressBar progressBar1;
    private JButton readDeviceButton;
    private JButton syncroTimeButton;
    private JButton backupDatabaseButton;
    private JButton restoreDatabaseButton;
//    private JTable tableJointTable;
    private JDBCXYDataset jdsSpeed;
    private java.sql.Timestamp ts15;
    private java.sql.Timestamp ts17;
    private String startDisplayString;
    private String endDisplayString;

//    private DefaultTableModel dTableModelJointTable;



//    private static FormTwo instance;

    final FormTwo frmTwo = this;

    private static boolean flagSlider = false;
    private static boolean flagAxis = false;
    private static boolean flagAxisTen = false;
    private static boolean flagAxisTwenty = false;
    private static boolean flagAxisThirty = false;
    private static boolean flagDelete;

    private int graphsNum = 0;
    private long bigParticle;

    private int offsetToShow = 0000;
    private int secondsToShow = 2 * 3600;
    private int fetchSize = 100;

    public static String carNo = "temp";
    public double coeffPresOne = 0.0098;
    public double coeffPresTwo = 0.0098;
    public double coeffSpeed = 0.181818;


    public double coeffPresOnePmax = 1.0;
    public double coeffPresOneUmin = 0.0;
    public double coeffPresOneUmax = 1.0;

    public double coeffPresTwoPmax = 1.0;
    public double coeffPresTwoUmin = 0.0;
    public double coeffPresTwoUmax = 1.0;

    String dbUrl = "";


    private DecimalFormat df = new DecimalFormat("0.00");

//    public static FormTwo getInstance() {
//        if (FormTwo.instance == null) FormTwo.instance = new FormTwo();
//        return FormTwo.instance;
//    }

    public void setReadDeviceLbl(String text) {
        this.readDeviceLbl.setText(text);
    }

    public void setSynchronizeTimeLbl(String text) {
        this.synchronizeTimeLbl.setText(text);
    }

    public void setBackupDBLbl(String text) {
        this.backupDBLbl.setText(text);
    }

    public void setRestoreDBLbl(String text) {
        this.restoreDBLbl.setText(text);
    }

    private JLabel readDeviceLabel;
    public JLabel readDeviceLbl;
    private JLabel synchronizeTimeLbl;
    private JLabel backupDBLbl;
    private JLabel restoreDBLbl;
    private JTextField textSendCommand;
    private JButton button11;
    private JTabbedPane settingsTab;
    private JTextField textSendCommand2;
    private JLabel backupDBLabel;
    private JLabel restoreDBLabel;
    private JTextField timeSpanTxtFld;
    private JButton button12;
    private JButton loadDBButton;
    private JLabel loadDBLbl;
    private JLabel loadDBLabel;
    private JCheckBox loadingDB;
    private JLabel labelStart;
    private JLabel labelEnd;
    private JPanel graphOld;
    private JPanel tabOneOld;
    private JPanel tabTwoOld;
    private JPanel tabThreeOld;
    private JPanel tabFiveOld;
    private JButton printButton;
    private JLabel loadCurrentDBLbl;
    private JLabel loadCurretDBLabel;
    private JButton loadCarNoButton;
    public JTextField carNoText;
    private JTextArea textArea1;
    public JTextField textFieldStart;
    public JTextField textFieldEnd;
    public JTextField coefPresOneText;
    public JTextField coefPresTwoText;
    private JPanel timeAxisPanel;
    private JButton readCarNoButton;
    private JButton loadCoefficientsButton;
    public JProgressBar progressBar2;
    private JPanel workWithDataPanel;
    public JTextField carNoTwoText;
    public JTextField coefPresOneRead;
    public JTextField coefPresTwoRead;
    private JPanel loadDBPanel;
    private JPanel deleteDBPanel;
    private JPanel loadCurrentDBPanel;
    public JTextField coefSpeedText;
    public JTextField coeffSpeedRead;
    private JCheckBox backupDB;
    private JCheckBox restoreDB;
    private JScrollPane scrollTimeAxis;
    private JToolBar toolBarOne;
    private JButton dropOldDB;
    private JButton printSpeedButton;
    private JCheckBox allDigital;
    private JPanel graphicsPanel;
    private JPanel graphDataPanel;
    public JTextArea textAreaLogTime;
    public JTextArea textAreaLogCarNo;
    public JTextArea textAreaLogCoeffs;
    private JButton loadLogButton;
    private JButton button3;
    private JButton button13;
    private JPanel panelCoefOne;
    private JPanel panelCoefTwo;
    public JTextField carNoTop;
    private JPanel nextPrevPanel;
    public JTextField textReadCoefOnePmax;
    public JTextField textReadCoefOneUmax;
    public JTextField textReadCoefOneUmin;
    public JTextField textReadCoefTwoUmin;
    public JTextField textReadCoefTwoUmax;
    public JTextField textReadCoefTwoPmax;
    public JTextField textWriteCoefOneUmin;
    public JTextField textWriteCoefOneUmax;
    public JTextField textWriteCoefOnePmax;
    public JTextField textWriteCoefTwoUmin;
    public JTextField textWriteCoefTwoUmax;
    public JTextField textWriteCoefTwoPmax;
    public JTextField coeffSpeedWrite;
    private JPanel arrowsPanel;
    private JLabel timeSpanLbl;
    public JComboBox comboDate;
    private JButton PDFButton;
    public JSpinner spinnerStart;
    public JSpinner spinnerEnd;
    private JPanel synchroTimePanel;
    private JPanel newPressuresPanel;
    private JLabel lblNew;
    private JPanel backupDBPanel;
    private JPanel topInfoPanel;
    private JButton realTimeButton;
    private JLabel labelPath;
    private JLabel labelDays;
    private JLabel myInPipeLabel;
    private JLabel myOutPipeLabel;
    private JLabel tempLabel;
    private Connection conn = null;
//    private Statement st = null;
    private JFreeChart chTimeAxis;
    private JFreeChart chSpeed;
    private JFreeChart chPres1;
    private JFreeChart chPres2;
    private JFreeChart chAllDigits;
    private JFreeChart chAllDigits2;
    private JFreeChart chD1;
    private JFreeChart chD2;
    private JFreeChart chD3;
    private JFreeChart chD4;
    private JFreeChart chD5;
    private JFreeChart chD6;
    private JFreeChart chD7;
    private JFreeChart chD8;
    private JFreeChart chD9;
    private JFreeChart chD10;
    private JFreeChart chD11;
    private JFreeChart chD12;
    private JFreeChart chD13;
    private JFreeChart chD14;
    private JFreeChart chD15;
    private JFreeChart chD16;
    private JFreeChart chD17;
    private JFreeChart chD18;
    private JFreeChart chD19;
    private JFreeChart chD20;
    private JFreeChart chD21;
    private JFreeChart chD22;
    private JFreeChart chD23;
    private JFreeChart chD24;
    private JFreeChart chD25;
    private JFreeChart chD26;
    private JFreeChart chD27;
    private JFreeChart chD28;
    private JFreeChart chD29;
    private JFreeChart chD30;
    private JFreeChart chD31;
    private JFreeChart chD32;
    private JFreeChart chD33;
    private JFreeChart chD34;
    private JFreeChart chD35;
    private JFreeChart chD36;
    private JFreeChart chD37;

    private int lastValue = SLIDER_INITIAL_VALUE;
    private static int SLIDER_INITIAL_VALUE = 50;
    private double coefPresOne;
    private double coefPresTwo;

    private boolean isUser = false;
    private int timeSpan = Integer.parseInt(timeSpanTxtFld.getText());

    public static JFrame frame;

//    final JDialog dlg = new JDialog(frame, "Progress Dialog", true);
    public final JProgressBar dpb = new JProgressBar(0, 300000);

    public String getComboDate() {
        return (String) comboDate.getSelectedItem();
    }

    public String getTextFieldStart() {
        return textFieldStart.getText();
    }

    public String getTextFieldEnd() {
        return textFieldEnd.getText();
    }

    private boolean isFirstDatePick = true;

    //  Password on enter /////////////////////////////////////////////////////////////////////////////////////////////////
//    static {
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            // If Nimbus is not available, you can set the GUI to another look and feel.
//        }
//
//        String choice = JOptionPane.showInputDialog(JOptionPane.getRootFrame(), "Введіть пароль (1)", "Пароль: ", JOptionPane.PLAIN_MESSAGE);
//        if ((choice == null) || ((choice != null) && !(choice.equals("1")))) {
//            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Невірний пароль. Закриття програми");
//
//            System.exit(0);
//        } else if (choice.equals("user")) {
//            //     tabbedPane1.setEnabledAt(9, false);
//            //isUser = true;
//
//        }
//
//    }

///////////////////////////////////////////////Joint Table///////////////////////////////////////////////////////

    static Object[][] databaseInfoJointTable;
    static Object[] columnsJointTable = {"Дата/час                   ",
            "Швидкість",
            "Тиск НМ",
            "Тиск ГМ",
            "АРШ ввім.",
            "АЛС ввім.",
            "Радіост. ввім.",
            "ЕПК ввімк.",
            "РП відновл.",
            "Рух від КАХ",
            "Ввімк. ВАХ",
            "Ввімк. ВАД",
            "Ввімк. ОВТ",
            "Зачин. дверей",
            "КБ(ПБ)",
            "Відсутня част.",
            "Доп.шв.0 км/г",
            "Доп.шв. 40 км/г",
            "Доп.шв. 60 км/г",
            "Доп.шв. 70 км/г",
            "Доп.шв. 80 км/г",
            "Стоян. гальмо",
            "Ввімк.бл.перетв.",
            "КРП",
            "1 пп",
            "2 пп",
            "3 пп",
            "4 пп",
            "5 пп",
            "6 пп",
            "Провід Ф7",
            "8 пп",
            "18 пп",
            "20 пп",
            "25 пп",
            "34 пп",
            "48 пп",
            "АРШ",
            "ДАУ-АРШ",
            "ВУ",
            "Вибір напрямку"
    };

    static String[] tk = {"Дата/час           ",
            "Швидкість",
            "Тиск НМ",
            "Тиск ГМ",
            "АРШ ввім.",
            "АЛС ввім.",
            "Радіост. ввім.",
            "ЕПК ввімк.",
            "РП відновл.",
            "Рух від КАХ",
            "Ввімк. ВАХ",
            "Ввімк. ВАД",
            "Ввімк. ОВТ",
            "Зачин. дверей",
            "КБ(ПБ)",
            "Відсутня част.",
            "Доп.шв.0 км/г",
            "Доп.шв. 40 км/г",
            "Доп.шв. 60 км/г",
            "Доп.шв. 70 км/г",
            "Доп.шв. 80 км/г",
            "Стоян. гальмо",
            "Ввім.бл.перетв.",
            "КРП",
            "1 пп",
            "2 пп",
            "3 пп",
            "4 пп",
            "5 пп",
            "6 пп",
            "Провід Ф7",
            "8 пп",
            "18 пп",
            "20 пп",
            "25 пп",
            "34 пп",
            "48 пп",
            "АРШ",
            "ДАУ-АРШ",
            "ВУ",
            "Вибір напр."
    };

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Code for Tab3
    static Object[][] databaseInfo;
    static Object[] columns3 = {"Швидкість",
            "Тиск НМ",
            "Тиск ГМ",
            "АРШ ввім.",
            "АЛС ввім.",
            "Радіост. ввім.",
            "ЕПК ввімк.",
            "РП відновл.",
            "Рух від КАХ",
            "Ввімк. ВАХ",
            "Ввімк. ВАД",
            "Ввімк. ОВТ",
            "Зачин. дверей",
            "КБ(ПБ)",
            "Відсутня частота",
            "Доп. шв. 0 км/г",
            "Доп. шв. 40 км/г",
            "Доп. шв. 60 км/г",
            "Доп. шв. 70 км/г",
            "Доп. шв. 80 км/г",
            "Стоян. гальмо",
            "Ввімк. блока перетв.",
            "КРП",
            "1 пп",
            "2 пп",
            "3 пп",
            "4 пп",
            "5 пп",
            "6 пп",
            "Провід Ф7",
            "8 пп",
            "18 пп",
            "20 пп",
            "25 пп",
            "34 пп",
            "48 пп",
            "АРШ",
            "ДАУ-АРШ",
            "ВУ",
            "Вибір напрямку"
    };

    static Object[][] databaseInfo2;
    static Object[] columns4 = {"Дата/час",
            "Тиск НМ",
            "Тиск ГМ"
    };

    static Object[][] databaseInfoSpeed;
    static Object[] columnsSpeed = {"Дата/час",
            "Значення швидкості"
    };

    static Object[][] databaseInfoDigits;
    static Object[] columnsDigits = {"Дата/час",
            "Вх. 1",
            "Вх. 2",
            "Вх. 3",
            "Вх. 4",
            "Вх. 5",
            "Вх. 6",
            "Вх. 7",
            "Вх. 8",
            "Вх. 9",
            "Вх. 10",
            "Вх. 11",
            "Вх. 12",
            "Вх. 13",
            "Вх. 14",
            "Вх. 15",
            "Вх. 16",
            "Вх. 17",
            "Вх. 18",
            "Вх. 19",
            "Вх. 20",
            "Вх. 21",
            "Вх. 22",
            "Вх. 23",
            "Вх. 24",
            "Вх. 25",
            "Вх. 26",
            "Вх. 27",
            "Вх. 28",
            "Вх. 29",
            "Вх. 30",
            "Вх. 31",
            "Вх. 32",
            "Вх. 33",
            "Вх. 34",
            "Вх. 35",
            "Вх. 36",
            "Вх. 37",
    };

    static ResultSet rows;
    static ResultSetMetaData metaData;

    static ResultSet rows2;
    static ResultSetMetaData metaData2;

    static ResultSet rowsJointTable;
    static ResultSetMetaData metaDataJointTable;

//    static ResultSet rowsJointTableDigits;
//    static ResultSetMetaData metaDataJointTableDigits;

    static ResultSet rowsJointTablePressures;
    static ResultSetMetaData metaDataJointTablePressures;

//    static ResultSet rowsJointTableSpeed;
//    static ResultSetMetaData metaDataJointTableSpeed;

    static ResultSet rowsSpeed;
    static ResultSetMetaData metaDataSpeed;

    static ResultSet rowsDigits;
    static ResultSetMetaData metaDataDigits;

    static ResultSet settingsInfoSet;


    static DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns3) {
        public Class getColumnClass(int column) {
            Class returnValue;
            // Verifying that the column exists (index > 0 && index < number of columns
            if ((column >= 1) && (column < getColumnCount())) {
                returnValue = getValueAt(1, column).getClass();
            } else {
                // Returns the class for the item in the column
                returnValue = Object.class;
            }
            return returnValue;
        }
    };
///////////////////////////////TABLE MODEL FOR PRESSURES////////////////////////////////////////////

    static DefaultTableModel dTableModel2 = new DefaultTableModel(databaseInfo2, columns4) {
        public Class getColumnClass(int column) {
            Class returnValue;
            // Verifying that the column exists (index > 0 && index < number of columns
            if ((column >= 1) && (column < getColumnCount())) {
                returnValue = getValueAt(1, column).getClass();
            } else {
                // Returns the class for the item in the column
                returnValue = Object.class;
            }
            return returnValue;
        }
    };

/////////////////////////////////////TABLE MODEL FOR SPEED///////////////////////////////////////////

//    static DefaultTableModel dTableModelSpeed = new DefaultTableModel(databaseInfoSpeed, columnsSpeed) {
//        public Class getColumnClass(int column) {
//            Class returnValue;
//            // Verifying that the column exists (index > 0 && index < number of columns
//            if ((column >= 1) && (column < getColumnCount())) {
//                returnValue = getValueAt(1, column).getClass();
//            } else {
//                // Returns the class for the item in the column
//                returnValue = Object.class;
//            }
//            return returnValue;
//        }
//    };

/////////////////////////////////////TABLE MODEL FOR DIGITALS///////////////////////////////////////////

//    static DefaultTableModel dTableModelDigits = new DefaultTableModel(databaseInfoDigits, columnsDigits) {
//        public Class getColumnClass(int column) {
//            Class returnValue;
//            // Verifying that the column exists (index > 0 && index < number of columns
//            if ((column >= 1) && (column < getColumnCount())) {
//                returnValue = getValueAt(1, column).getClass();
//            } else {
//                // Returns the class for the item in the column
//                returnValue = Object.class;
//            }
//            return returnValue;
//        }
//    };


    public FormTwo(final JFrame frame) {
        final PasswordWindow pwnd = new PasswordWindow(frame, this);
//        pwnd.setVisible(true);
        pwnd.pack();
        pwnd.setLocationRelativeTo(null);
        pwnd.passwordField1.requestFocusInWindow();
        pwnd.setVisible(true);

        tabbedPane1.remove(12);
        tabbedPane1.remove(11);
        tabbedPane1.remove(10);
        tabbedPane1.remove(9);
        tabbedPane1.remove(8);
        tabbedPane1.remove(7);
        tabbedPane1.remove(6);
        tabbedPane1.remove(5);
        tabbedPane1.remove(4);
        settingsTab.remove(1);
        allDigital.setVisible(false);

        spinnerStart.setModel(new SpinnerDateModel());
        spinnerEnd.setModel(new SpinnerDateModel());
//        spinnerStart.setEnabled(false);
//        spinnerEnd.setEnabled(false);

        JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(spinnerStart, "HH:mm:ss");
        JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(spinnerEnd, "HH:mm:ss");
        spinnerStart.setEditor(timeEditorStart);
        spinnerEnd.setEditor(timeEditorEnd);

//        spinnerStart.getEditor().setEnabled(false);
//        spinnerEnd.getEditor().setEnabled(false);

        ((JSpinner.DefaultEditor) spinnerStart.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spinnerEnd.getEditor()).getTextField().setEditable(false);

        spinnerStart.setValue(Time.valueOf("00:00:00"));
        spinnerEnd.setValue(Time.valueOf("23:59:59"));

        hideSpinnerArrow(spinnerStart);
        hideSpinnerArrow(spinnerEnd);

//        graphScrollPanel.getViewport().setMinimumSize(new Dimension(160, 200));
//        graphScrollPanel.getViewport().setPreferredSize(new Dimension(160, 200));
//        graphsScrollChecks.getViewport().setMinimumSize(new Dimension(160, 200));
//        graphsScrollChecks.getViewport().setPreferredSize(new Dimension(160, 200));

        carNoTwoText.setEnabled(false);
        coeffSpeedRead.setEnabled(false);

        textReadCoefOneUmin.setEnabled(false);
        textReadCoefOneUmax.setEnabled(false);
        textReadCoefOnePmax.setEnabled(false);

        textReadCoefTwoUmin.setEnabled(false);
        textReadCoefTwoUmax.setEnabled(false);
        textReadCoefTwoPmax.setEnabled(false);

        refreshButton.setEnabled(false);
        PDFButton.setEnabled(false);



        if (pwnd.isAdmin()) {
//            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Congratulations! You're admin here!");
        } else

        if (pwnd.isUser()) {
            synchroTimePanel.setVisible(false);
            newPressuresPanel.setVisible(false);
            carNoText.setVisible(false);
            coeffSpeedWrite.setVisible(false);
            lblNew.setVisible(false);
            settingsTab.setEnabled(false);
            readDeviceButton.setEnabled(false);
            syncroTimeButton.setEnabled(false);
            loadCarNoButton.setEnabled(false);
            backupDatabaseButton.setEnabled(false);
            dropDatabaseButton.setEnabled(false);
            loadDBButton.setEnabled(false);
            //restoreDatabaseButton.setEnabled(false);
            carNoText.setEnabled(false);
//            carNoTwoText.setEnabled(false);
            loadCoefficientsButton.setEnabled(false);
            readCarNoButton.setEnabled(false);
            coefSpeedText.setEnabled(false);
//            coeffSpeedRead.setEnabled(false);
            coeffSpeedWrite.setEnabled(false);
            coefPresOneText.setEnabled(false);
            coefPresOneRead.setEnabled(false);
            coefPresTwoText.setEnabled(false);
            coefPresTwoRead.setEnabled(false);

//            textReadCoefOneUmin.setEnabled(false);
//            textReadCoefOneUmax.setEnabled(false);
//            textReadCoefOnePmax.setEnabled(false);
//
//            textReadCoefTwoUmin.setEnabled(false);
//            textReadCoefTwoUmax.setEnabled(false);
//            textReadCoefTwoPmax.setEnabled(false);

            textWriteCoefOneUmin.setEnabled(false);
            textWriteCoefOneUmax.setEnabled(false);
            textWriteCoefOnePmax.setEnabled(false);

            textWriteCoefTwoUmin.setEnabled(false);
            textWriteCoefTwoUmax.setEnabled(false);
            textWriteCoefTwoPmax.setEnabled(false);


            dropOldDB.setEnabled(false);

            tabbedPane1.remove(3);

        } else

        if (pwnd.isServiceStaff()) {
            //settingsTab.setEnabledAt(1, false);
            tabbedPane1.remove(3);


        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Incorrect password. Shutdown the system.");
            System.exit(0);
        }

        reading.setEnabled(false);
        loadingDB.setEnabled(false);
        backupDB.setEnabled(false);
        restoreDB.setEnabled(false);

//        if (pwnd.notAdmin()) {
//            tabbedPane1.setVisible(true);
//        }
        textFieldStart.setVisible(false);
        textFieldEnd.setVisible(false);

        coefSpeedText.setVisible(false);
        coefPresOneText.setVisible(false);
        coefPresTwoText.setVisible(false);

        panelCoefOne.setVisible(false);
        panelCoefTwo.setVisible(false);
        arrowsPanel.setVisible(false);
        timeSpanTxtFld.setVisible(false);
        timeSpanLbl.setVisible(false);



        scrollTimeAxis.setVisible(false);

        northPanel.setVisible(false);
        loadToTableButton.setVisible(false);
//        loadDBButton.setVisible(false);
//        loadDBLabel.setVisible(false);
//        loadDBLbl.setVisible(false);
//        loadingDB.setVisible(false);
//        nextPrevPanel.setVisible(false);


//        loadDBPanel.setVisible(false);
        buttonsPanel.setVisible(false);
        backupDBPanel.setVisible(false);
        topInfoPanel.setVisible(false);



        progressBar1.setVisible(false);
//        spinner3.setVisible(false);

        loadCurrentDBPanel.setVisible(false);
        deleteDBPanel.setVisible(false);


        coefPresOne = Double.parseDouble(coefPresOneText.getText());
        coefPresTwo = Double.parseDouble(coefPresTwoText.getText());
        timeAxisPanel.setVisible(false);

        System.out.println(coefPresOne);
        System.out.println(coefPresTwo);



//        tabbedPane1.setEnabledAt(3, false);
//        tabbedPane1.setEnabledAt(4, false);
//        tabbedPane1.setEnabledAt(5, false);
//        tabbedPane1.setEnabledAt(6, false);
//        tabbedPane1.setEnabledAt(7, false);
//        tabbedPane1.setEnabledAt(8, false);
//        tabbedPane1.setEnabledAt(9, false);
//        tabbedPane1.setEnabledAt(10, false);
//        tabbedPane1.setEnabledAt(11, false);
        scrollSelectGraphs.setVisible(false);
        //tabbedPane1.getTabComponentAt(0).setVisible(false);

        toolBarOne.setVisible(false);

//        Button2.setVisible(false);
//        button7.setVisible(false);
        button2.setVisible(false);
        button9.setVisible(false);
//        printButton.setVisible(false);
        button10.setVisible(false);


        ////////////////Load default database////////////
//        createBigTable();

        String[] columnNames = {"Швидкість",
                "Тиск НМ",
                "Тиск ГМ",
                "АРШ ввім.",
                "АЛС ввім.",
                "Радіост. ввім.",
                "ЕПК ввімк.",
                "РП відновл.",
                "Рух від КАХ",
                "Ввімк. ВАХ",
                "Ввімк. ВАД",
                "Ввімк. ОВТ",
                "Зачин. дверей",
                "КБ(ПБ)",
                "Відсутня частота",
                "Доп. шв. 0 км/г",
                "Доп. шв. 40 км/г",
                "Доп. шв. 60 км/г",
                "Доп. шв. 70 км/г",
                "Доп. шв. 80 км/г",
                "Стоян. гальмо",
                "Ввімк. блока перетв.",
                "КРП",
                "1 пп",
                "2 пп",
                "3 пп",
                "4 пп",
                "5 пп",
                "6 пп",
                "Провід Ф7",
                "8 пп",
                "18 пп",
                "20 пп",
                "25 пп",
                "34 пп",
                "48 пп",
                "АРШ",
                "ДАУ-АРШ",
                "ВУ",
                "Вибір напрямку"
        };


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Object[][] data = {
                {new Double(1.), new Double(2.), new Double(3.), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)
                },
                {new Double(1.), new Double(2.), new Double(3.), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false),
                        new Boolean(false), new Boolean(false), new Boolean(false), new Boolean(false)
                },
        };


        final JTable dataTable2 = new JTable(data, columnNames);
        dataTable2.setPreferredScrollableViewportSize(new Dimension(50, 100));
        dataTable2.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer rightRendered = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        rightRendered.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumn column = null;
        for (int i = 0; i < 40; i++) {
            column = dataTable2.getColumnModel().getColumn(i);
            column.setPreferredWidth(70); //third column is bigger
            column.setMinWidth(20);
            if (i > 2 && i < 40)
                dataTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            else
                dataTable2.getColumnModel().getColumn(i).setCellRenderer(rightRendered);
        }

        dataTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tableScrollPane.createHorizontalScrollBar();
        tableScrollPane.createVerticalScrollBar();
        tableScrollPane.setViewportView(dataTable2);
        dataTable2.setFillsViewportHeight(true);


        //code for Tab1
        TableModel dataModel = new
                AbstractTableModel() {
                    private String[] columnNames = {"one", "two", "three", "four"};

                    public int getColumnCount() {
                        return 10;
                    }

                    public int getRowCount() {
                        return 10;
                    }

                    public Object getValueAt(int row, int col) {
                        return row * col;
                    }
                };


        dataTable.setModel(dataModel);
        dataTable.setSize(10, 20);
        tablePanel.setSize(600, 600);
        tabbedPane1.setSize(600, 600);

        // Code for Tab3
        String strUrl = "jdbc:derby:wagons";
        Statement st;

        try {
            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();
            String selectStuff = "SELECT * FROM APP.MAINTABLE";
            rows = st.executeQuery(selectStuff);
            //st.close();


            int numOfCol;
            metaData = rows.getMetaData();
            numOfCol = metaData.getColumnCount();

            columns3 = new String[numOfCol];
            for (int i = 1; i <= numOfCol - 1; i++) {
                // Returns the column name
                columns3[i] = metaData.getColumnName(i);
                System.out.println(i);
            }

            Object[] tempRow;
            while (rows.next()) {
                tempRow = new Object[]{rows.getDouble(3), rows.getDouble(4), rows.getDouble(5), rows.getBoolean(6),
                        rows.getBoolean(7), rows.getBoolean(8), rows.getBoolean(9), rows.getBoolean(10),
                        rows.getBoolean(11), rows.getBoolean(12), rows.getBoolean(13), rows.getBoolean(14),
                        rows.getBoolean(15), rows.getBoolean(16), rows.getBoolean(17), rows.getBoolean(18),
                        rows.getBoolean(19), rows.getBoolean(20), rows.getBoolean(21), rows.getBoolean(22),
                        rows.getBoolean(23), rows.getBoolean(24), rows.getBoolean(25), rows.getBoolean(26),
                        rows.getBoolean(27), rows.getBoolean(28), rows.getBoolean(29), rows.getBoolean(30),
                        rows.getBoolean(31), rows.getBoolean(32), rows.getBoolean(33), rows.getBoolean(34),
                        rows.getBoolean(35), rows.getBoolean(36), rows.getBoolean(37), rows.getBoolean(38),
                        rows.getBoolean(39), rows.getBoolean(40), rows.getBoolean(41), rows.getBoolean(42)
                };

                //   tempLabel.setText(rows.toString());

                // Adds the row of data to the end of the model

                dTableModel.addRow(tempRow);
            }
//            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        final JTable table3 = new JTable(dTableModel);
//        table3.setFont(new Font("Serif", Font.PLAIN, 10));
        table3.setRowHeight(table3.getRowHeight() + 10);
        table3.setAutoCreateRowSorter(true);
        table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tab3ScrollPane.setViewportView(table3);
        table3.setFillsViewportHeight(true);


//tabFour // Code to display charts for different datasets from database /////////////////////////////////////////////
        final JDBCXYDataset jds = createDataset();
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Wagon info",
                "Date", "Count", jds);
        for (int i = 0; i < jds.getItemCount(); i++) {
            System.out.println(new Date(jds.getX(0, i).longValue()));
        }
//        chartPane.add(new ChartPanel(chart));
//        chartPanel.add().setViewportView(chartPane);


//        panelForChart.setLayout(new BorderLayout());
//        chartScroll.setLayout(new ScrollPaneLayout());
        final ChartPanel chP = new ChartPanel(chart, true);
        chP.setLayout(new BorderLayout());
        chP.setPreferredSize(new Dimension(780, 300));
        chP.setHorizontalAxisTrace(true);
        chP.setVerticalAxisTrace(true);



//        ChartFrame chF = new ChartFrame("Title", chart, true);
//        chF.setSize(20, 30);
//        chF.pack();
//        chF.setVisible(true);

//        chP.setHorizontalAxisTrace(true);
//        chP.setVerticalAxisTrace(true);
//        chP.setDomainZoomable(true);
//        chP.setRangeZoomable(false);
//        chP.setSize(20, 10);
        Dimension d = new Dimension(50, 50);
        //       chP.setPreferredSize(d);
//        chP.setMinimumDrawWidth(100);
//        chP.setMaximumDrawWidth(200);
        chP.setAutoscrolls(true);

//        chP.setDomainZoomable(true);
//        System.out.println(chP.getAutoscrolls());


//        panelForChart.add(graph, BorderLayout.CENTER);
//        panelForChart.setMinimumSize(d);
//        panelForChart.setMaximumSize(d);
//
//        panelForChart.add(chP, BorderLayout.CENTER);
//        panelForChart.validate();
//        repaint();
//        JButton button21;
//        button21 = new JButton();
//        button21.setHorizontalAlignment(0);
//        button21.setPreferredSize(new Dimension(200, 300));
//        button21.setText("Another one button");
//        button21.setAutoscrolls(true);
//        //buttonsPanel.add(button1);
//        panelForChart.add(button21);

//Tab Five //////// SHOW CHARTS basing on data from database/////////////////////////////////////////////////////

        //tabbedPane1.setPreferredSize(new Dimension(tablePanel.getHeight() - 10, tablePanel.getWidth() - 10));
        //chP.setPreferredSize(new Dimension(tabbedPane1.getHeight() - 10, tabbedPane1.getWidth() - 10));
        //chP.setPreferredSize(new Dimension(tablePanel.getWidth(), tablePanel.getHeight()));
        chP.restoreAutoBounds();

        toolLabel.setText("Some text");
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.LEFT);
        chP.setFocusable(true);



        chP.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(final ChartMouseEvent event){
                chP.setSize(new Dimension(scrollFive.getWidth() - 40, scrollFive.getHeight() - 40));
//                chP.set
                System.out.println("chartMouseClicked");
            }

            @Override
            public void chartMouseMoved(final ChartMouseEvent event){
                int newX = event.getTrigger().getX();
                int newY = event.getTrigger().getY();
                System.out.println("chartMouseMoved to " + newX + " " + newY);
            }
        });

        final JPanel graph = new JPanel();
        graph.add(chP);
        scrollFive.add(graph);
        scrollFive.setViewportView(graph);


        JPanel graphOne = new JPanel();
        graphOne.add(chP);
        JPanel graphTwo = new JPanel(new GridLayout(2, 1));
        //graphTwo.setPreferredSize(new Dimension(1000, 200));
        graphTwo.add(graphOne);
        graphTwo.add(createDemoPanel());

        scrollFiveTwo.add(graphTwo);
        scrollFiveTwo.setViewportView(graphTwo);
//        scrollFive.add(graphTwo);
//        scrollFive.setViewportView(graphTwo);

//tabSixOld //////////////////SHOWING SOME SILLY BAR CHART/////////////////////////////////////////////////////

        final JDBCCategoryDataset jds2 = createDataset2();
        final JFreeChart chart2 = ChartFactory.createBarChart("Wagon info",
                "Date", "Count", jds2, PlotOrientation.HORIZONTAL, true, true, false);
//        for (int i = 0; i < jds2.getItemCount(); i++) {
//            System.out.println(new Date(jds2.getX(0, i).longValue()));
//        }
        ChartPanel chP2 = new ChartPanel(chart2, true);
        chP2.setPreferredSize(new Dimension(600, 400));
        JPanel graph2 = new JPanel();
        graph2.add(chP2);
        scrollSix.add(graph2);
        scrollSix.setViewportView(graph2);

//        chartScroll.add(button21);
//        chartScroll.setViewportView(new JButton("No"));
//        chartScroll.repaint();
//        chartScroll.validate();
/*        chartScroll.getViewport().setPreferredSize(new Dimension(100, 100));
        chartScroll.setViewportView(chP);
        chartScroll.validate();
        chartScroll.setVisible(true);
*/

        //getContentPane().add(chartScroll);
//        chartScroll.setViewportView(chP);
//        chartScroll.add(graph, ScrollPaneLayout.VIEWPORT);
//        chartScroll.setViewportView(graph);
        //chartScroll.validate();
        // display();
//        chartScroll.add(chP);
//        chartScroll.setViewportView(chP);
//        chP.restoreAutoBounds();
//        repaint();


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statement st = null;
                try {
//                    Connection conn2;
//                    conn2 = DriverManager.getConnection(strUrl);
//                    Statement st2;
//                    st2 = conn2.createStatement();

//                    String strUrl = "jdbc:derby:wagons";
//                    Connection conn = DriverManager.getConnection(strUrl);
//                    st = conn.createStatement();

                    String insertNew = "INSERT INTO APP.MAINTABLE (WID, WNAME, SPEED, PRESSUREINPUMPINGMAIN, PRESSUREINBRAKELINE, ARSHON, ALSON, RADIOON, EPKON, RPREPAIRED, RUNFROMKAH, VAHON, VADON, OVTON, CLOSINGTRAINDOORS, KBPON, NOFREQSIGNAL, ALLOWEDSPEEDZERO, ALLOWEDSPEED40, ALLOWEDSPEED60, ALLOWEDSPEED70, ALLOWEDSPEED80, PARKINGBREAKS, CONVERTERBLOCKON, RESERVEDSTARTBUTTONON, TRAINWIRE1, TRAINWIRE2, TRAINWIRE3, TRAINWIRE4, TRAINWIRE5, TRAINWIRE6, WIREF7, TRAINWIRE8, TRAINWIRE18, TRAINWIRE20, TRAINWIRE25, TRAINWIRE34, TRAINWIRE48, ARSHTRIGGERED, DAUARSHON, VUCONTROLON, CHOOSEDIRECTION) " +
                            "VALUES (11, 'sdf', 115.0, 111.0, 12.0, TRUE," +
                            " TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE," +
                            " TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, " +
                            "TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE)";
                    st.executeUpdate(insertNew);
                } catch (Exception ex) {ex.printStackTrace();}
            }
        });
        //table3.repaint();
        dTableModel.fireTableDataChanged();
        //tab3ScrollPane.repaint();
        repaint();
        toolLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                toolLabel.setText(Integer.toString(scrollFive.getWidth()) + " x " + Integer.toString(scrollFive.getHeight()));
            }
        });


        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readDb();
            }
        });

        loadToTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
////////////////////////////// Creating data for table with pressures (tab4)////////////////////////////////////
                    String selectStuff1 = "SELECT * FROM APP.PRESSURE";
                    String strUrl = "jdbc:derby:wagons";
                    Connection connButtonLoadTable = DriverManager.getConnection(strUrl);
                    Statement st1 = connButtonLoadTable.createStatement();
                    rows2 = st1.executeQuery(selectStuff1);
                    int numOfCol2;
                    metaData2 = rows2.getMetaData();
                    numOfCol2 = metaData2.getColumnCount();
                    dTableModel2.setRowCount(0);

                    metaData2 = rows2.getMetaData();
                    numOfCol2 = metaData2.getColumnCount();
                    Object[] tempRow2;

                    columns4 = new String[numOfCol2];
                    for (int i = 1; i <= numOfCol2 - 1; i++) {
                        // Returns the column name
                        columns4[i] = metaData2.getColumnName(i);
                        System.out.println(i);
                    }

                    Object[] tempRow;
                    while (rows2.next()) {
                        tempRow2 = new Object[]{rows2.getTimestamp(1), rows2.getInt(2), rows2.getInt(3)
                        };
                        // Adds the row of data to the end of the model
                        dTableModel2.addRow(tempRow2);
                        System.out.println("In pressure table");

                    }



                } catch (SQLException ex) {
                    System.out.println("SQL Exception: " + ex.getMessage());
                    System.out.println("Vendor error: " + ex.getErrorCode());
                }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

////////////////////////////////CREATING NEW TABLE TO SHOW PRESSURES////////////////////////////////////////////////

                JTable table4 = new JTable(dTableModel2);
                ((DefaultTableCellRenderer)table4.getTableHeader().getDefaultRenderer())
                        .setHorizontalAlignment(JLabel.CENTER);

//                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

                table4.getColumnModel().getColumn(0).setPreferredWidth(150);
//                table4.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

//                column.setPreferredWidth(70); //third column is bigger
//                column.setMinWidth(20);
//
//        table3.setFont(new Font("Serif", Font.PLAIN, 10));
                table4.setRowHeight(table4.getRowHeight() + 10);
                table4.setAutoCreateRowSorter(true);
                table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                scrollFour.setViewportView(table4);
                table4.setFillsViewportHeight(true);

/////////////////////////CREATING DATASET FOR PRESSURES CHART/////////////////////////////////////////////////////////

                JDBCXYDataset jds5 = createDataset3();
                JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Pressures", "Date", "Count", jds5);
                for (int i = 0; i < jds5.getItemCount(); i++) {
                    System.out.println(new Date(jds5.getX(0, i).longValue()));
                }

                ChartPanel chP2 = new ChartPanel(chart1, true);
                chP2.setLayout(new BorderLayout());
                chP2.setPreferredSize(new Dimension(780, 400));

                JPanel graph2 = new JPanel();
                graph2.add(chP2);
                scrollSeven.add(graph2);
                scrollSeven.setViewportView(graph2);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////// Creating data for table with SPEED (tab4)////////////////////////////////////
//                try {
//
//
//                String selectSpeed = "SELECT * FROM APP.SPEED";
//
//                String strUrl = "jdbc:derby:wagons";
//
//                Connection connButtonLoadTableSpeed = DriverManager.getConnection(strUrl);
//                Statement st1 = connButtonLoadTableSpeed.createStatement();
//
//                st1.setFetchSize(fetchSize);
//                rowsSpeed = st1.executeQuery(selectSpeed);
//
//                int numOfColSpeed;
//                metaDataSpeed = rowsSpeed.getMetaData();
//                numOfColSpeed = metaDataSpeed.getColumnCount();
//
//                dTableModelSpeed.setRowCount(0);
//
//                metaDataSpeed = rowsSpeed.getMetaData();
//                numOfColSpeed = metaDataSpeed.getColumnCount();
//                Object[] tempRowSpeed;
//
//                columnsSpeed = new String[numOfColSpeed];
//                for (int i = 1; i <= numOfColSpeed - 1; i++) {
//                    // Returns the column name
//                    columnsSpeed[i] = metaDataSpeed.getColumnName(i);
//                    System.out.println(i);
//                }
//
//                Object[] tempRow;
//                while (rowsSpeed.next()) {
//                    tempRowSpeed = new Object[]{rowsSpeed.getTimestamp(1), rowsSpeed.getInt(2)
//                    };
//                    // Adds the row of data to the end of the model
//                    dTableModelSpeed.addRow(tempRowSpeed);
//                }
//
//                connButtonLoadTableSpeed.close();
//
//
//                } catch (SQLException ex) {
//                System.out.println("SQL Exception: " + ex.getMessage());
//                System.out.println("Vendor error: " + ex.getErrorCode());
//            }

////////////////////////////////CREATING NEW TABLE TO SHOW SPEED////////////////////////////////////////////////

//                JTable tableSpeed = new JTable(dTableModelSpeed);
//                ((DefaultTableCellRenderer)tableSpeed.getTableHeader().getDefaultRenderer())
//                        .setHorizontalAlignment(JLabel.CENTER);
//
//                tableSpeed.getColumnModel().getColumn(0).setPreferredWidth(150);
//
//                tableSpeed.setRowHeight(tableSpeed.getRowHeight() + 10);
//                tableSpeed.setAutoCreateRowSorter(true);
//                tableSpeed.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//                scrollFourTwo.setViewportView(tableSpeed);
//                tableSpeed.setFillsViewportHeight(true);

/////////////////////////CREATING DATASET FOR SPEED CHART/////////////////////////////////////////////////////////
//
//                jdsSpeed = createDatasetSpeed("", "");
//                JFreeChart chartSpeed = ChartFactory.createTimeSeriesChart("Speed", "Date", "Count", jdsSpeed);
//
//                Calendar calendar = Calendar.getInstance();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                for (int i = 0; i < jdsSpeed.getItemCount(); i++) {
//                    System.out.println(new Date(jdsSpeed.getX(0, i).longValue()));
//                    calendar.setTimeInMillis(jdsSpeed.getX(0, i).longValue());
//                    System.out.println(dateFormat.format(calendar.getTime()));
//
////                    if (jdsSpeed.getX(0, i) == null) jdsSpeed.getX(0, i) = 0;
//                }
//
//                ChartPanel chPSpeed = new ChartPanel(chartSpeed, true);
//                chPSpeed.setLayout(new BorderLayout());
//                chPSpeed.setPreferredSize(new Dimension(780, 400));
//
//                JPanel graphSpeed = new JPanel();
//                graphSpeed.add(chPSpeed);
//                scrollSevenTwo.add(graphSpeed);
//                scrollSevenTwo.setViewportView(graphSpeed);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////// Creating data for table with DIGITS (tab8)////////////////////////////////////
//                try {
//
//                    String selectDigits = "SELECT * FROM APP.DIGITS";
//
//                    String strUrl = "jdbc:derby:wagons";
//
//                    Connection connButtonLoadTableDigits = DriverManager.getConnection(strUrl);
//
//                    Statement st1 = connButtonLoadTableDigits.createStatement();
//
//
//                    rowsDigits = st1.executeQuery(selectDigits);
//
//                    int numOfColDigits;
//                    metaDataDigits = rowsDigits.getMetaData();
//                    numOfColDigits = metaDataDigits.getColumnCount();
//
//                    dTableModelDigits.setRowCount(0);
//
//                    metaDataDigits = rowsDigits.getMetaData();
//                    numOfColDigits = metaDataDigits.getColumnCount();
//                    Object[] tempRowDigits;
//
//                    columnsDigits = new String[numOfColDigits];
//                    for (int i = 1; i <= numOfColDigits - 1; i++) {
//                        // Returns the column name
//                        columnsDigits[i] = metaDataDigits.getColumnName(i);
//                        System.out.println(i);
//                    }
//
//                    Object[] tempRow;
//                    while (rowsDigits.next()) {
//                        tempRowDigits = new Object[]{rowsDigits.getTimestamp(1), rowsDigits.getBoolean(2),
//                                rowsDigits.getBoolean(3), rowsDigits.getBoolean(4), rowsDigits.getBoolean(5), rowsDigits.getBoolean(6),
//                                rowsDigits.getBoolean(7), rowsDigits.getBoolean(8), rowsDigits.getBoolean(9), rowsDigits.getBoolean(10),
//                                rowsDigits.getBoolean(11), rowsDigits.getBoolean(12), rowsDigits.getBoolean(13), rowsDigits.getBoolean(14),
//                                rowsDigits.getBoolean(15), rowsDigits.getBoolean(16), rowsDigits.getBoolean(17), rowsDigits.getBoolean(18),
//                                rowsDigits.getBoolean(19), rowsDigits.getBoolean(20), rowsDigits.getBoolean(21), rowsDigits.getBoolean(22),
//                                rowsDigits.getBoolean(23), rowsDigits.getBoolean(24), rowsDigits.getBoolean(25), rowsDigits.getBoolean(26),
//                                rowsDigits.getBoolean(27), rowsDigits.getBoolean(28), rowsDigits.getBoolean(29), rowsDigits.getBoolean(30),
//                                rowsDigits.getBoolean(31), rowsDigits.getBoolean(32), rowsDigits.getBoolean(33), rowsDigits.getBoolean(34),
//                                rowsDigits.getBoolean(35), rowsDigits.getBoolean(36), rowsDigits.getBoolean(37), rowsDigits.getBoolean(38)
//                        };
//                        // Adds the row of data to the end of the model
//                        dTableModelDigits.addRow(tempRowDigits);
//                    }
//                    connButtonLoadTableDigits.close();
//
//                } catch (SQLException ex) {
//                    System.out.println("SQL Exception: " + ex.getMessage());
//                    System.out.println("Vendor error: " + ex.getErrorCode());
//                }

////////////////////////////////CREATING NEW TABLE TO SHOW DIGITS////////////////////////////////////////////////

//                JTable tableDigits = new JTable(dTableModelDigits);
//                ((DefaultTableCellRenderer)tableDigits.getTableHeader().getDefaultRenderer())
//                        .setHorizontalAlignment(JLabel.CENTER);
//
//                tableDigits.getColumnModel().getColumn(0).setPreferredWidth(150);
//
//                tableDigits.setRowHeight(tableDigits.getRowHeight() + 10);
//                tableDigits.setAutoCreateRowSorter(true);
//                tableDigits.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//                scrollEightTable.setViewportView(tableDigits);
//                tableDigits.setFillsViewportHeight(true);

///////////////////////////CREATING DATASET FOR DIGITS CHART/////////////////////////////////////////////////////////
//
//                JDBCXYDataset jdsDigits = createDatasetDigits();
//                JFreeChart chartDigits = ChartFactory.createTimeSeriesChart("Digits", "Date", "Count", jdsDigits);
//                for (int i = 0; i < jdsDigits.getItemCount(); i++) {
//                    System.out.println(new Date(jdsDigits.getX(0, i).longValue()));
//                }
//
//                ChartPanel chPDigits = new ChartPanel(chartDigits, true);
//                chPDigits.setLayout(new BorderLayout());
//                chPDigits.setPreferredSize(new Dimension(780, 400));
//
//                JPanel graphDigits = new JPanel();
//                graphSpeed.add(chPDigits);
////                scrollEightChart.add(graphDigits);
////                scrollEightChart.setViewportView(graphDigits);
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                JPanel graphDigitsTwo = new JPanel();
//                //graphTwo.setPreferredSize(new Dimension(1000, 200));
//                graphDigitsTwo.add(createDemoDigits());
//                scrollEightChart.add(graphDigitsTwo);
//                scrollEightChart.setViewportView(graphDigitsTwo);
//
//                JPanel graphDigitsTwo2 = new JPanel();
//                graphDigitsTwo2.add(createDemoDigits());
////                graphDigitsTwo2.setPreferredSize(new Dimension(300, 50));
//                scrollNine.add(graphDigitsTwo2);
//                scrollNine.setViewportView(graphDigitsTwo2);


                System.out.println("Some text in Task Series");
                toolLabel.setText("Some text in task Series");

            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        graphCheckBoxPanel.setLayout(new GridLayout(0, 1));
        graphCheckBoxPanel.setPreferredSize(new Dimension(40, 400));
        graphCheckBoxPanel.setAutoscrolls(true);
        for (int i = 0; i < 40; i++){
            JCheckBox tmpChB = new JCheckBox(Integer.toString(i));
            tmpChB.setText(Integer.toString(i));
            tmpChB.setName("checkBox" + Integer.toString(i));
            graphCheckBoxPanel.add(tmpChB);
        }

        graphsScrollChecks.setLayout(new GridLayout(0, 1));
        graphsScrollChecks.setPreferredSize(new Dimension(40, 400));
//        graphsScrollChecks.setAutoscrolls(true);
//        for (int i = 0; i < 40; i++){
//            JCheckBox tmpChB = new JCheckBox(Integer.toString(i));
//            tmpChB.setText(Integer.toString(i));
//            tmpChB.setName("checkBox" + Integer.toString(i));
//            graphsScrollChecks.add(tmpChB);
//        }



        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String mpusbapiPath = Paths.get(".").toAbsolutePath().normalize().toString() /*+ "\\mpusbapi"*/;

                String mpusbapiJavaPath = mpusbapiPath.replaceAll("\\\\", "/");

                MpusbWrapperTwo mwtOne = new MpusbWrapperTwo();
//                System.out.println(mwtOne.readDLL());
                mwtOne.readDLL();

                try {
                    System.load(mpusbapiJavaPath + "\\mpusbapi.dll");
                } catch (UnsatisfiedLinkError ess) {
                    System.err.println("Native code library failed to load. \n" + ess);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не можу завантажити ДЛЛ.\n" +
                            "ок");

                }

                labelPath.setText(mpusbapiJavaPath);

                findFile("mpusbapi.dll", new File(mpusbapiPath));


//                final InputSpinnerTime ist = new InputSpinnerTime(frmTwo, spinnerEnd);

//                ist.pack();
////                ist.setLocation((int)spinnerStart.getLocation().getX() + 400, (int)spinnerStart.getLocation().getY() + 300);
////                ist.setLocationRelativeTo(spinnerStart);
////                ist.setLocation(400, 300);
//
//                ist.setVisible(true);

                //dbUrl = "jdbc:derby:" + populateCombo();
//                JFreeChart chartAn2;
//                try {
//                    Connection conn = DriverManager.getConnection(dbUrl + comboDate.getSelectedItem());
//
//                    String selectAll = "SELECT * FROM APP.ALLDATA ";// +
////                            " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerEndStr + "'";
//
//                    Statement st1 = conn.createStatement();
//
////                    st1.setFetchSize(fetchSize);
//                    ResultSet rowsJointTableDigits;
//                    System.out.println("Start joint table query...");
//
////                    rowsJointTableDigits = st1.executeQuery(selectAll);
//
//                    try {
//                        st1.execute("create view v1 as select timestampcol, speed, dig13 from app.alldata" +
//                                " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + "00:00:00" + "' and '" + comboDate.getSelectedItem() + " " + "23:59:59" + "'" +
////                                " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
//                                " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
////                                " AND APP.ALLDATA.SPEED is NULL");
//                    } catch (Exception ex15) {
//                        ex15.printStackTrace();
//                    }
//
//                    rowsJointTableDigits = st1.executeQuery("SELECT * FROM APP.V1");
//
////                    chartAn2 = ChartFactory.createTimeSeriesChart("\n \n \n \n ", "", "", rowsJointTableDigits);
//                    while (rowsJointTableDigits.next()) {
//                        System.out.println(rowsJointTableDigits.getTimestamp(1) + " -- " + rowsJointTableDigits.getDouble(2));
//                    }
//
//                    System.out.println("Finished");
//
//
////                    createBigTable(conn);
////                    showGraphs(conn);
//                    if (!conn.isClosed()) conn.close();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
////                System.out.println(populateCombo());
            }
        });


        dropDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllDb();
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final File file = new File("out6.bin");

                ReadBinFile rbf = new ReadBinFile();
                System.out.println("Start read file");
                try {
                    byte[] arr = rbf.getBytesFromFileTwo(file, frame, FormTwo.this);
                    System.out.println("Arr size: " + arr.length);
                    for (int i = 0; i < arr.length; i++)
                        arr[i] = (byte) (arr[i] + 4 * 27);
                        //System.out.println("arr[" + i + "]= " + arr[i]);

                } catch (IOException e5) {}
                System.out.println("Stop read file.");

//                System.out.println(comboDate.getSelectedItem().toString());
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//                Date st = new Date(textFieldStart.getText());
//                Date fin = new Date(textFieldEnd.getText());


//                System.out.println("ChSpeed = " + chSpeed);
//                String pdfPath = "c:/" + carNo + "_" + comboDate.getSelectedItem() + ".pdf";
//
//                try {
//                    convertToPDF(chSpeed, 600, 800, pdfPath);
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Файл збережено до теки " + pdfPath);
//                } catch (Exception e1) {
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не вдалося зберегти файл...");
//                }

 //               readDb();
 //               readCoeffs();



//                java.sql.Timestamp ts1 = Timestamp.valueOf(textFieldStart.getText());
//                java.sql.Timestamp ts2 = Timestamp.valueOf(textFieldEnd.getText());
//
//                int difference = (int)(ts2.getTime() - ts1.getTime());
//
//                System.out.println("Difference is: " + difference);


//                getRecordStartEnd();
//                loadDigitDataSet();

//                String strUrl = "jdbc:derby:wagons";
//                try {
//                    Connection connDel2;
//                    connDel2 = DriverManager.getConnection(strUrl);
//                    PreparedStatement pstmDel2_1 = connDel2.prepareStatement("DELETE FROM APP.PRESSURE WHERE 1 = 1");
//                    pstmDel2_1.executeUpdate();
//                    connDel2.close();
//                } catch (Exception ex) {ex.printStackTrace();}
//                System.out.println("Start query...");
////                String queryOne = "SELECT * FROM APP.DIGITS WHERE TIMESTAMP between '2016-12-21 15:23:30.0' and '2016-12-21 15:33:30.0'";
//                String queryOne = "SELECT app.speed.TIMESTAMP FROM APP.speed where SECOND (APP.speed.TIMESTAMP) - FLOOR (SECOND (APP.SPEED.TIMESTAMP)) = 0.0";
//
//                String strUrl = "jdbc:derby:wagons";
//                ResultSet rs = null;
//                try {
//                    Connection connDel2;
//                    connDel2 = DriverManager.getConnection(strUrl);
//                    Statement pstmDel2_1 = connDel2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                    rs = pstmDel2_1.executeQuery(queryOne);
//                    rs.next();
////                    System.out.println(rs.getTimestamp(1));
////                    while (rs.next())
////                        System.out.println(rs.getString(1));
////
////                    rs.last();
//                //    rs.getTimestamp(1);
////                    System.out.println(rs.getTimestamp(1));
//                    System.out.println(rs.getTimestamp(1));
//
//                    connDel2.close();
//                } catch (Exception ex) {ex.printStackTrace();}
//
//                System.out.println("Stop query");

            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String strUrl = "jdbc:derby:wagons";
                try {
                    Connection connDel3;
                    connDel3 = DriverManager.getConnection(strUrl);

//                    PreparedStatement pstmDel1 = connDel1.prepareStatement("DELETE FROM APP.PRESSURE WHERE " +
//                                    "APP.PRESSURE.TIMESTAMP =  (SELECT MAX (" +
//                                    "(SELECT M TIMESTAMP FROM)" +
//                            );

                    PreparedStatement pstmDel3_1 = connDel3.prepareStatement("DELETE FROM APP.PRESSURE " +
                            "WHERE PRESSUREONE < 0 OR PRESSUREONE > 1024");
//                    PreparedStatement pstmDel3_2 = connDel3.prepareStatement("DELETE FROM APP.SPEED_OLD " +
//                            "WHERE SPEED_OLD.SPEED < -1 OR SPEED_OLD.SPEED > 1024");
//                    PreparedStatement pstmDel3_3 = connDel3.prepareStatement("DELETE FROM APP.SPEED_OLD " +
//                            "WHERE MAX(SPEED_OLD.SPEED)");


                    pstmDel3_1.executeUpdate();
//                    pstmDel3_2.executeUpdate();
//                    pstmDel3_3.executeUpdate();
                    pstmDel3_1.close();
                    connDel3.close();
                } catch (Exception ex) {ex.printStackTrace();}
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                XYItemRenderer renderer = new StandardXYItemRenderer();
//                Renderer
//                renderer =
                tabbedPane1.setEnabledAt(0, false);
             //   tabOne.setEnabled(false);

            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createBigTable();
//                System.out.println(dTableModelJointTable.getRowCount());
            }
        });
//        refreshGraphsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                ////////////////////////////////CREATING NEW TABLE TO SHOW DIGITS////////////////////////////////////////////////
//
//                JTable tableDigits = new JTable(dTableModelDigits);
//                ((DefaultTableCellRenderer)tableDigits.getTableHeader().getDefaultRenderer())
//                        .setHorizontalAlignment(JLabel.CENTER);
//
//                tableDigits.getColumnModel().getColumn(0).setPreferredWidth(150);
//
//                tableDigits.setRowHeight(tableDigits.getRowHeight() + 10);
//                tableDigits.setAutoCreateRowSorter(true);
//                tableDigits.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//                scrollEightTable.setViewportView(tableDigits);
//                tableDigits.setFillsViewportHeight(true);
//
//                JPanel graphDigitsTwo = new JPanel();
//                //graphTwo.setPreferredSize(new Dimension(1000, 200));
//                graphDigitsTwo.add(createDemoDigits());
//                scrollEightChart.add(graphDigitsTwo);
//                scrollEightChart.setViewportView(graphDigitsTwo);
//
//
///////////////////////////CREATING DATASET FOR SPEED CHART/////////////////////////////////////////////////////////
//                JPanel graphDig1 = new JPanel(new GridLayout(3, 1));
//
//                /////////////////////////CREATING DATASET FOR DIG1 CHART/////////////////////////////////////////////////////////
//
//                JDBCXYDataset jdsDig1 = createDatasetDigitOne();
////                JFreeChart chartDig1 = ChartFactory.createTimeSeriesChart("Dig1", "", "", jdsDig1);
//
////                ChartPanel chPDig1 = new ChartPanel(chartDig1, true);
////                chPDig1.setLayout(new BorderLayout());
////                chPDig1.setPreferredSize(new Dimension(280, 200));
////                chartDig1.removeLegend();
//
////                graphDig1.add(chPDig1);
//                graphDig1.add(newDigitalGraph("name", jdsDig1));
//
//                /////////////////////////CREATING DATASET FOR DIG2 CHART/////////////////////////////////////////////////////////
//
//                JDBCXYDataset jdsDig2 = createDatasetDigitTwo(Integer.toString(2));
//                JFreeChart chartDig2 = ChartFactory.createTimeSeriesChart("Dig2", "", "", jdsDig2);
//
//                ChartPanel chPDig2 = new ChartPanel(chartDig2, true);
//                chPDig2.setLayout(new BorderLayout());
//                chPDig2.setPreferredSize(new Dimension(600, 200));
//                chartDig2.removeLegend();
//                chartDig2.getTitle().setPosition(RectangleEdge.LEFT);
//                chartDig2.getTitle().setFont(new Font("TimesNewRoman", Font.ITALIC, 10));
//                XYPlot xyPlot = (XYPlot) chartDig2.getPlot();
//                NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
//                range.setTickUnit(new NumberTickUnit(1.0));
//       //         chartDig2
//
//                graphDig1.add(chPDig2);
//
//                JPanel sliderPanel = new JPanel();
////                sliderPanel.setSize(-1, 40);
//                //sliderPanel
//                JSlider graphSlider = new JSlider();
//                sliderPanel.add(graphSlider);
//                graphDig1.add(sliderPanel, BorderLayout.SOUTH);
//
//                scrollBigTable.add(graphDig1);
//                scrollBigTable.setViewportView(graphDig1);
//            }
//        });

        scrollGraphs.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
//                int width = scrollGraphs.getWidth() - 30;

//            chPAn2.setPreferredSize(new Dimension(1300, 120));
//            chPAn2.setMaximumSize(new Dimension(1300, 120));
//            chPAn2.setMinimumSize(new Dimension(1300, 120));
//            chPAn2.setMaximumDrawWidth(1300);
//                Dimension dim = new Dimension(width, 120);
//                chartSpeed.setPreferredSize(dim);
//                chartSpeed.repaint();
//                chartSpeed.setMaximumSize(dim);
//                chartSpeed.setMinimumSize(dim);
//                chartSpeed.setMaximumDrawWidth(width);
//                chartSpeed.repaint();
//                System.out.println("Repaint");


//                int width = scrollGraphs.getWidth() - 30;
//                chartSpeed.setPreferredSize(new Dimension(width, 120));
//                chartSpeed.setMaximumSize(new Dimension(width, 120));
//                chartSpeed.setMinimumSize(new Dimension(width, 120));
////                chartSpeed.setMaximumDrawWidth(width);
//                chartSpeed.repaint();
//                graphDig.repaint();
//                graphicsPanel.repaint();
//                graphDataPanel.repaint();
//                scrollGraphs.repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        frame.addWindowStateListener(new WindowStateListener(){
            public void windowStateChanged(WindowEvent e) {

            int width = scrollGraphs.getWidth() - 30;
//            chartSpeed.setPreferredSize(new Dimension(width, 120));
//            chartSpeed.setMaximumSize(new Dimension(width, 120));
//            chartSpeed.setMinimumSize(new Dimension(width, 120));

//                System.out.println(e.getNewState());//I need to trap this state when it prints 7

//                if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
//                    int width = scrollGraphs.getWidth() - 30;
//                    chartSpeed.setPreferredSize(new Dimension(width, 120));
//                    chartSpeed.setMaximumSize(new Dimension(width, 120));
//                    chartSpeed.setMinimumSize(new Dimension(width, 120));
//                }

//                scrollGraphs.repaint();
//                int width = scrollGraphs.getWidth() - 30;
//                System.out.println("width = " + width);
//                //width = 1300;
//                int oldState = e.getOldState();
//                int newState = e.getNewState();
//
////                if (((newState & Frame.NORMAL) == Frame.NORMAL) && (oldState & Frame.NORMAL) != Frame.NORMAL) {
////                    int width = scrollGraphs.getWidth() - 30;
////                    chartSpeed.setPreferredSize(new Dimension(width, 120));
////                    chartSpeed.setMaximumSize(new Dimension(width, 120));
////                    chartSpeed.setMinimumSize(new Dimension(width, 120));
////                }
//                if (((newState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) && (oldState & Frame.MAXIMIZED_BOTH) != Frame.MAXIMIZED_BOTH) {
//
//                    chartSpeed.setPreferredSize(new Dimension(width, 120));
//                    chartSpeed.setMaximumSize(new Dimension(width, 120));
//                    chartSpeed.setMinimumSize(new Dimension(width, 120));
//                }



            }
        });

        frame.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
//                System.out.println(getState());
//
//                if (getState() == 6/*Frame.MAXIMIZED_HORIZ*/) {
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Iconified");
//
//                    //int width = scrollGraphs.getWidth() - 30;
//                    chartSpeed.setPreferredSize(new Dimension(300, 120));
//                    chartSpeed.setMaximumSize(new Dimension(300, 120));
//                    chartSpeed.setMinimumSize(new Dimension(300, 120));
//                }
////                if (getState() == Frame.NORMAL) {
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Нормальне вікно");
//                    int width = scrollGraphs.getWidth() - 30;
//                    chartSpeed.setPreferredSize(new Dimension(width, 120));
//                    chartSpeed.setMaximumSize(new Dimension(width, 120));
//                    chartSpeed.setMinimumSize(new Dimension(width, 120));
//
//                }
                // do stuff
//                Thread t1 = new Thread(new Runnable() {
//                    public void run()
//                    {
                        // code goes here.

                       // scrollGraphs.set
//                        int width = scrollGraphs.getWidth() - 30;
//                        width = centerPanel.getWidth() - 205;
//                        chartSpeed.setPreferredSize(new Dimension(width, 120));
//                        chartSpeed.setMaximumSize(new Dimension(width, 120));
//                        chartSpeed.setMinimumSize(new Dimension(width, 120));



//                chartSpeed.setMaximumDrawWidth(width);
//                        chartSpeed.repaint();
//                        graphDig.repaint();
//                        graphicsPanel.repaint();
//                        graphDataPanel.repaint();
//                        scrollGraphs.repaint();
//                        frame.repaint();
//                        System.out.println("Scroll width: " + (scrollGraphs.getWidth()));
//                        System.out.println("Chart width: " + (chartSpeed.getWidth()));
//                    }});
//                t1.start();

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verticalTraceLineDM = null;

                try {
                    Connection connSettingsInfo = DriverManager.getConnection(dbUrl + comboDate.getSelectedItem());
                    createSettingsInfoDataTwo(connSettingsInfo);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                showGraphsInThread();
                System.gc();
//                showGraphs();
            }
        });

        if (chartAllDigits2 != null) {
//            chartAllDigits2.addChartMouseListener(new ChartMouseListener() {
//                @Override
//                public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
//                }
//
//                public void chartMouseMoved(ChartMouseEvent event) {
//                    try {
//                        XYPlot localXYPlot = (XYPlot) chartAllDigits2.getChart().getPlot();
//
//                        double xValue = event.getTrigger().getX();
//                        double yValue = event.getTrigger().getY();
//                        localXYPlot.clearRangeMarkers();
//                        localXYPlot.clearDomainMarkers();
//
//                        Marker yMarker = new ValueMarker(yValue);
//                        yMarker.setPaint(Color.darkGray);
//                        localXYPlot.addRangeMarker(yMarker);
//
//                        Marker xMarker = new ValueMarker(xValue);
//                        xMarker.setPaint(Color.darkGray);
//                        localXYPlot.addDomainMarker(xMarker);
//
//                        chartAllDigits2.repaint();
//                    } catch (Exception e) {
//                    }
//                }
//            });
        }

        graphsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (chSpeed != null) {
                    //chSpeed.getXYPlot().setDomainCrosshairValue(graphsSlider.getValue() + bigParticle);
                    chSpeed.getXYPlot().clearDomainMarkers();
                    Marker xMarker = new ValueMarker(graphsSlider.getValue() + bigParticle);
                    xMarker.setPaint(Color.blue);
                    xMarker.setStroke(new BasicStroke(2));
                    chSpeed.getXYPlot().addDomainMarker(xMarker);
                    chartSpeed.repaint();
                }
                if (chPres1 != null) {
//                    chPres1.getXYPlot().setDomainCrosshairValue(graphsSlider.getValue() + bigParticle);
                    chPres1.getXYPlot().clearDomainMarkers();
                    Marker xMarker = new ValueMarker(graphsSlider.getValue() + bigParticle);
                    xMarker.setPaint(Color.blue);
                    xMarker.setStroke(new BasicStroke(2));
                    chPres1.getXYPlot().addDomainMarker(xMarker);
                    chartPress1.repaint();
                }
                if (chAllDigits2 != null) {
//                    chAllDigits2.getXYPlot().setDomainCrosshairValue(graphsSlider.getValue() + bigParticle);
                    chAllDigits2.getXYPlot().clearDomainMarkers();
                    Marker xMarker = new ValueMarker(graphsSlider.getValue() + bigParticle);
                    xMarker.setPaint(Color.blue);
                    xMarker.setStroke(new BasicStroke(2));
                    chAllDigits2.getXYPlot().addDomainMarker(xMarker);
                    chartAllDigits2.repaint();
                }

//                long value = graphsSlider.getValue();
//                int addition = timeSpan * 1000 / 1;
//                System.out.println(value);
//                System.out.println(graphsSlider.getMaximum());
//                System.out.println(timeSpan * 1000);
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try{
//                System.out.println((long) (dateFormat.parse(labelEnd.getText())).getTime() - bigParticle);
//                } catch (Exception exp) {}
////                graphsSlider.setMinorTickSpacing(1); // step / interavl
////                graphsSlider.setSnapToTicks(true); // should be activated for custom tick space
////                graphsSlider.setMinorTickSpacing(10);
////                graphsSlider.setMajorTickSpacing(10);
////                graphsSlider.setPaintTicks(true);
//
//                graphsSlider.setPaintTrack(true);
//
//                try {
//
//                if (chTimeAxis != null) {
//                    chTimeAxis.getPlot();
//                    XYPlot xyPlot = (XYPlot) chTimeAxis.getPlot();
//                    DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
//                }
//
//                if (chSpeed != null) {
//                    chSpeed.getPlot();
//                    XYPlot xyPlot = (XYPlot) chSpeed.getPlot();
//                    DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
//                    if (dateAxis.getMaximumDate().after(dateFormat.parse(labelEnd.getText()))) {
////                        dateAxis.setRange((long) ((dateFormat.parse(labelEnd.getText())).getTime() - bigParticle - addition),
////                                (long) (dateFormat.parse(labelEnd.getText())).getTime() - bigParticle);
//
//                        long asd = (long) ((dateFormat.parse(labelEnd.getText())).getTime() - bigParticle - addition);
//
//                    }
//                }
//
////                if (chAllDigits != null) {
////                    chAllDigits.getPlot();
////                    XYPlot xyPlot = (XYPlot) chAllDigits.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition ));
////                }
//
//                if (chAllDigits2 != null) {
//                    chAllDigits2.getPlot();
//                    XYPlot xyPlot = (XYPlot) chAllDigits2.getPlot();
//                    DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition ));
//                }
//
//
//                if (chPres1 != null) {
//                    chPres1.getPlot();
//                    XYPlot xyPlot = (XYPlot) chPres1.getPlot();
//                    DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
//                }
//                if (chPres2 != null) {
//                    chPres2.getPlot();
//                    XYPlot xyPlot = (XYPlot) chPres2.getPlot();
//                    DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
//                }
////                if (chD1 != null) {
////                    chD1.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD1.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD2 != null) {
////                    chD2.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD2.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition ));
////                }
////                if (chD3 != null) {
////                    chD3.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD3.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition ));
////                }
////                if (chD4 != null) {
////                    chD4.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD4.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD5 != null) {
////                    chD5.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD5.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD6 != null) {
////                    chD6.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD6.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD7 != null) {
////                    chD7.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD7.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD8 != null) {
////                    chD8.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD8.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD9 != null) {
////                    chD9.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD9.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD10 != null) {
////                    chD10.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD10.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD11 != null) {
////                    chD11.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD11.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD12 != null) {
////                    chD12.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD12.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD13 != null) {
////                    chD13.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD13.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD14 != null) {
////                    chD14.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD14.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD15 != null) {
////                    chD15.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD15.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD16 != null) {
////                    chD16.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD16.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD17 != null) {
////                    chD17.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD17.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD18 != null) {
////                    chD18.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD18.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD19 != null) {
////                    chD19.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD19.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD20 != null) {
////                    chD20.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD20.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD21 != null) {
////                    chD21.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD21.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD22 != null) {
////                    chD22.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD22.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD23 != null) {
////                    chD23.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD23.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD24 != null) {
////                    chD24.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD24.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD25 != null) {
////                    chD25.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD25.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD26 != null) {
////                    chD26.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD26.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD27 != null) {
////                    chD27.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD27.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD28 != null) {
////                    chD28.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD28.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD29 != null) {
////                    chD29.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD29.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD30 != null) {
////                    chD30.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD30.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD31 != null) {
////                    chD31.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD31.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD32 != null) {
////                    chD32.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD32.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD33 != null) {
////                    chD33.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD33.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD34 != null) {
////                    chD34.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD34.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD35 != null) {
////                    chD35.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD35.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD36 != null) {
////                    chD36.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD36.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
////                if (chD37 != null) {
////                    chD37.getPlot();
////                    XYPlot xyPlot = (XYPlot) chD37.getPlot();
////                    DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();
////                    dateAxis.setRange((long) (value + bigParticle), (long) (value + bigParticle + addition));
////                }
//                }
//                catch (ParseException ep) {
//                    ep.printStackTrace();
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Wrong Date format!", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//                if (chartSpeed != null) {
////                    Graphics g = graphicsPanel.getGraphics();
////                    g.drawLine((int) (value - bigParticle) + 125 - 43, 27, (int) (value - bigParticle) + 125 - 43, 800);
////                    Graphics g1 = chartPress1.getGraphics();
////                    g1.drawLine((int) (value - bigParticle) + 125 - 43, 8, (int) (value - bigParticle) + 125 - 43, 800);
////                    Graphics g2 = chartAllDigits2.getGraphics();
////                    g2.drawLine((int) (value - bigParticle) + 125 - 43, 8, (int) (value - bigParticle) + 125 - 43, 1000);
////                    repaint();
////                    drawHorizontalAxisTraceDM((Graphics2D)chartSpeed.getGraphics(), (int) (value - bigParticle), chartSpeed);
//
//                }

            }
        });


        // Listen for changes in the text
        textFieldStart.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
 //               warn();
            }
            public void removeUpdate(DocumentEvent e) {
  //              warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                try {
//                    Date startRecord = dateFormat.parse(labelStart.getText());
//                    Date endRecord = dateFormat.parse(labelEnd.getText());
//                    Date startDisplay = dateFormat.parse(textFieldStart.getText());
//
//                    if (startDisplay.after(endRecord) || startRecord.after(startDisplay)){
//                        JOptionPane.showMessageDialog(null,
//                                "Error: Wrong time value", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                    } else {
//
//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        graphsSlider.setMinimum(min);
//                        graphsSlider.setValue(min);
//                    }
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Wrong Date format!", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
            }
        });

        coefPresOneText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coefPresOne = Double.parseDouble(coefPresOneText.getText());
                System.out.println(coefPresOne);
            }
        });

        coefPresTwoText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coefPresTwo = Double.parseDouble(coefPresTwoText.getText());
                System.out.println(coefPresOne);
            }
        });


        final JSpinner.DateEditor editor = (JSpinner.DateEditor) spinnerStart.getEditor();

        editor.getTextField().addFocusListener(new FocusAdapter() {
            boolean firstFocus = true;
            @Override
            public void focusGained(FocusEvent e) {
                if (firstFocus) {
                    final InputSpinnerTime ist = new InputSpinnerTime(frmTwo, spinnerStart);
                    ist.pack();
    //                ist.setLocationRelativeTo(spinnerStart);
//                    ist.setLocation((int)spinnerStart.getLocation().getX(), (int)spinnerStart.getLocation().getY() + 50);
                    ist.setLocation(frame.getX() + spinnerStart.getX() + 10, frame.getY() + spinnerStart.getY() + 50 + 10);

                    ist.setVisible(true);
                    firstFocus = false;
                } else {
                    firstFocus = true;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {

                    Time startTime = new Time(((Date) spinnerStart.getValue()).getTime());
                    Time endTime = new Time(((Date) spinnerEnd.getValue()).getTime());

                    if (startTime.after(endTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        spinnerStart.setValue(Time.valueOf("00:00:00"));
                    }

                    String strDateStart = comboDate.getSelectedItem() + " " + startTime.toString();
                    String strDateEnd = comboDate.getSelectedItem() + " " + endTime.toString();

                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                    DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                    dateAxisDigits.setPositiveArrowVisible(true);
                    dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                    DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                    dateAxisSpeed.setPositiveArrowVisible(true);
                    dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                    DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

                    dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());

                    bigParticle = (long) (Math.floor(tsStart.getTime() / 1000000000L) * 1000000000L);

                    int min = (int)(tsStart.getTime() - bigParticle);
                    int max = (int)(tsEnd.getTime() - bigParticle);

                    System.out.println("Preset slider max: " + max);

                    flagSlider = true;
                    graphsSlider.setMinimum(min);
                    graphsSlider.setMaximum(max);



                    System.gc();

                } catch (Exception pe) {
                    pe.printStackTrace();
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Wrong Date format!", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                    spinnerStart.setValue(Time.valueOf("00:00:00"));
                }
            }
        });

                editor.getTextField().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {

                            Time startTime = new Time(((Date) spinnerStart.getValue()).getTime());
                            Time endTime = new Time(((Date) spinnerEnd.getValue()).getTime());

                            if (startTime.after(endTime)){
                                JOptionPane.showMessageDialog(null,
                                        "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
                                        JOptionPane.ERROR_MESSAGE);
                                spinnerStart.setValue(Time.valueOf("00:00:00"));
                            }

                            String strDateStart = comboDate.getSelectedItem() + " " + startTime.toString();
                            String strDateEnd = comboDate.getSelectedItem() + " " + endTime.toString();

                            java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                            java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                            XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                            DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                            dateAxisDigits.setPositiveArrowVisible(true);
                            dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                            XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                            DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                            dateAxisSpeed.setPositiveArrowVisible(true);
                            dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                            XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                            DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

                            dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());

                            bigParticle = (long) (Math.floor(tsStart.getTime() / 1000000000L) * 1000000000L);

                            int min = (int)(tsStart.getTime() - bigParticle);
                            int max = (int)(tsEnd.getTime() - bigParticle);

                            System.out.println("Preset slider max: " + max);

                            flagSlider = true;
                            graphsSlider.setMinimum(min);
                            graphsSlider.setMaximum(max);



                            System.gc();

                        } catch (Exception pe) {
                            pe.printStackTrace();
                        }
                    }
                });

        editor.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
//                               warn();
            }
            public void removeUpdate(DocumentEvent e) {
                //              warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
 //               System.out.println("Warn!!!");

//                try {
//                    System.out.println(editor.getTextField().getText(2, 1));
//                    System.out.println(editor.getTextField().getText().length());
//                    if (!editor.getTextField().getText(2, 1).equals(":")) {
//                        System.out.println(editor.getTextField().getText().substring(0, 2) + ":" + editor.getTextField().getText().substring(4, 9));
//                    }
//                } catch (BadLocationException e) {
//                    e.printStackTrace();
//                }


//                editor.getTextField().getText().
//                if (editor.getTextField().getText().length() > 8) spinnerStart.setValue(Time.valueOf("00:00:00")); //editor.getTextField().setText("00:00:00");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                try {
//                    Date startRecord = dateFormat.parse(labelStart.getText());
//                    Date endRecord = dateFormat.parse(labelEnd.getText());
//                    Date startDisplay = dateFormat.parse(textFieldStart.getText());
//
//                    if (startDisplay.after(endRecord) || startRecord.after(startDisplay)){
//                        JOptionPane.showMessageDialog(null,
//                                "Error: Wrong time value", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                    } else {
//
//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        graphsSlider.setMinimum(min);
//                        graphsSlider.setValue(min);
//                    }
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Wrong Date format!", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
//                }
            }
        });




        JSpinner.DateEditor editor2 = (JSpinner.DateEditor) spinnerEnd.getEditor();
        editor2.getTextField().addFocusListener(new FocusAdapter() {

            boolean firstFocus = true;
            @Override
            public void focusGained(FocusEvent e) {
                if (firstFocus) {
                    final InputSpinnerTime ist2 = new InputSpinnerTime(frmTwo, spinnerEnd);
                    ist2.pack();
                    ist2.setLocation(frame.getX() + spinnerEnd.getX() + 10, frame.getY() + spinnerEnd.getY() + 50 + 10);

                    ist2.setVisible(true);
                    firstFocus = false;
                } else {
                    firstFocus = true;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {

                    Time startTime = new Time(((Date) spinnerStart.getValue()).getTime());
                    Time endTime = new Time(((Date) spinnerEnd.getValue()).getTime());

                    if (startTime.after(endTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Кінець відображення не може бути раніше дати початку відображення!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        spinnerEnd.setValue(Time.valueOf("23:59:59"));
                    }

                    String strDateStart = comboDate.getSelectedItem() + " " + startTime.toString();
                    String strDateEnd = comboDate.getSelectedItem() + " " + endTime.toString();

                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                    DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                    dateAxisDigits.setPositiveArrowVisible(true);
                    dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                    DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                    dateAxisSpeed.setPositiveArrowVisible(true);
                    dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                    DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

                    dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());


                    bigParticle = (long) (Math.floor(tsStart.getTime() / 1000000000L) * 1000000000L);

                    int min = (int)(tsStart.getTime() - bigParticle);
                    int max = (int)(tsEnd.getTime() - bigParticle);

                    System.out.println("Preset slider max: " + max);

                    flagSlider = true;
                    graphsSlider.setMinimum(min);
                    graphsSlider.setMaximum(max);



                    System.gc();

                } catch (Exception pe) {
                    pe.printStackTrace();
                }
            }
        });

        editor2.getTextField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Time startTime = new Time(((Date) spinnerStart.getValue()).getTime());
                    Time endTime = new Time(((Date) spinnerEnd.getValue()).getTime());

                    if (startTime.after(endTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Кінець відображення не може бути раніше дати початку відображення!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        spinnerEnd.setValue(Time.valueOf("23:59:59"));
                    }

                    String strDateStart = comboDate.getSelectedItem() + " " + startTime.toString();
                    String strDateEnd = comboDate.getSelectedItem() + " " + endTime.toString();

                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                    DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                    dateAxisDigits.setPositiveArrowVisible(true);
                    dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                    DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                    dateAxisSpeed.setPositiveArrowVisible(true);
                    dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                    XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                    DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

                    dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());


                    bigParticle = (long) (Math.floor(tsStart.getTime() / 1000000000L) * 1000000000L);

                    int min = (int)(tsStart.getTime() - bigParticle);
                    int max = (int)(tsEnd.getTime() - bigParticle);

                    System.out.println("Preset slider max: " + max);

                    flagSlider = true;
                    graphsSlider.setMinimum(min);
                    graphsSlider.setMaximum(max);



                    System.gc();

                } catch (Exception pe) {
                    pe.printStackTrace();
                }
            }
        });

        textFieldStart.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                try {
//                    Date startRecord = dateFormat.parse(labelStart.getText());
//                    Date endRecord = dateFormat.parse(labelEnd.getText());
//                    Date startDisplay = dateFormat.parse(startDisplayString);
//                    Date endDisplay = dateFormat.parse(endDisplayString);

                    Date startRecord = dateFormat.parse(labelStart.getText());
                    Date endRecord = dateFormat.parse(labelEnd.getText());

//                    Time startTime = Time.valueOf(textFieldStart.getText());
//                    Time endTime = Time.valueOf(textFieldEnd.getText());
//                    Date spinnerStartDate = (Date) spinnerStart.getValue();
//                    Date spinnerEndDate = (Date) spinnerEnd.getValue();

//                    Time startTime = new Time(spinnerStartDate.getTime());
//                    Time endTime = new Time(spinnerEndDate.getTime());
                    Time startTime = new Time(((Date) spinnerStart.getValue()).getTime());
                    Time endTime = new Time(((Date) spinnerEnd.getValue()).getTime());


                    if (startTime.after(endTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        textFieldStart.setText("00:00:00");
                        spinnerStart.setValue(Time.valueOf("00:00:00"));
                    }

//                    Date startDisplay = timeFormat.parse(comboDate.getSelectedItem() + " " + textFieldStart.getText());
//                    Date endDisplay = timeFormat.parse(comboDate.getSelectedItem() + " " + textFieldEnd.getText());
//
//
//                    System.out.println(startDisplay);
//                    System.out.println(endDisplay);
//
//                    System.out.println(startDisplayString);
//                    System.out.println(endDisplayString);
//
//                    System.out.println(startDisplay.toString());
//                    System.out.println(endDisplay.toString());

//                    if (startRecord.after(startDisplay)) {
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldStart.setText(labelStart.getText());
//                    }
//                    else if (startDisplay.after(endRecord)){
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Кінець відображення не може бути пізніше дати кінця запису!", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldStart.setText(labelStart.getText());

//                    } else {

//                        String strDateStart = textFieldStart.getText();
//                        String strDateEnd = textFieldEnd.getText();
//                        java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
//                        java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);


//                    String strDateStart = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//                    String strDateEnd = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
                    String strDateStart = comboDate.getSelectedItem() + " " + spinnerStart.getValue().toString();
                    String strDateEnd = comboDate.getSelectedItem() + " " + spinnerEnd.getValue().toString();


                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);


                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                        DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                        dateAxisDigits.setPositiveArrowVisible(true);
                        dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                        DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                        dateAxisSpeed.setPositiveArrowVisible(true);
                        dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                        DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

//                        dateAxisPressures.setPositiveArrowVisible(true);
                        dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());
                        System.gc();


//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        int max = (int)((long) endDisplay.getTime() - bigParticle - timeSpan * 1000);
//
//                        graphsSlider.setMinimum(min);
//                        graphsSlider.setMaximum(max);
//                        graphsSlider.setValue(min);
//                    }

                } catch (ParseException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: Wrong Date format!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    textFieldStart.setText("00:00:00");
            }

            }
        });



        textFieldStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
//                    Date startRecord = dateFormat.parse(labelStart.getText());
//                    Date endRecord = dateFormat.parse(labelEnd.getText());
//                    Date startDisplay = dateFormat.parse(textFieldStart.getText());
//                    Date endDisplay = dateFormat.parse(textFieldEnd.getText());

//                    Date startRecord = dateFormat.parse(labelStart.getText());
//                    Date endRecord = dateFormat.parse(labelEnd.getText());
//                    Date startDisplay = dateFormat.parse(textFieldStart.getText());
//                    Date endDisplay = dateFormat.parse(textFieldEnd.getText());

                    Date startRecord = dateFormat.parse(labelStart.getText());
                    Date endRecord = dateFormat.parse(labelEnd.getText());
                    Date startDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldStart.getText());
                    Date endDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldEnd.getText());

                    Time startTime = Time.valueOf(textFieldStart.getText());
                    Time endTime = Time.valueOf(textFieldEnd.getText());
                    if (startTime.after(endTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        textFieldStart.setText("00:00:00");
                    }

//                    if (startRecord.after(startDisplay)) {
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Початок відображення не може бути раніше дати початку запису!", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldStart.setText(labelStart.getText());
//                    }
//                    else if (startDisplay.after(endRecord)){
//                            JOptionPane.showMessageDialog(null,
//                                    "Помилка: Кінець відображення не може бути пізніше дати кінця запису!", "Error Massage",
//                                    JOptionPane.ERROR_MESSAGE);
//                        textFieldStart.setText(labelStart.getText());
//                    } else {


//                        String strDateStart = textFieldStart.getText();
//                        String strDateEnd = textFieldEnd.getText();
//                        java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
//                        java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    String strDateStart = comboDate.getSelectedItem() + " " + textFieldStart.getText();
                    String strDateEnd = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);



                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                        DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                        dateAxisDigits.setPositiveArrowVisible(true);
                        dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                        DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                        dateAxisSpeed.setPositiveArrowVisible(true);
                        dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                        DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

//                        dateAxisPressures.setPositiveArrowVisible(true);
                        dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());
                        System.gc();

//
//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        int max = (int)((long) endDisplay.getTime() - bigParticle - timeSpan * 1000);
//
//                        graphsSlider.setMinimum(min);
//                        graphsSlider.setMaximum(max);
//                        graphsSlider.setValue(min);
//                    }
//                    showGraphs();

                } catch (ParseException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: Wrong Date format!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    textFieldStart.setText("00:00:00");
                }

            }
        });

        textFieldEnd.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date startRecord = dateFormat.parse(labelStart.getText());
                    Date endRecord = dateFormat.parse(labelEnd.getText());
                    Date endDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldEnd.getText());
                    Date startDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldStart.getText());

                    Time startTime = Time.valueOf(textFieldStart.getText());
                    Time endTime = Time.valueOf(textFieldEnd.getText());
                    if (endTime.before(startTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Кінець відображення не може бути пізніше дати початку запису!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        textFieldEnd.setText("23:59:59");
                    }


//                    if (endDisplay.after(endRecord)) {
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Кінець відображення не може бути пізніше дати кінця запису!\n" +
//                                        "endRecord: " + endRecord.toString() + "   End display: " + endDisplay, "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldEnd.setText(labelEnd.getText());
//                    }
//                    else if (startRecord.after(endDisplay)){
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Початок відображення не може бути раніше дати кінця запису!", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldEnd.setText(labelEnd.getText());
//
//                    } else {
//                        String strDateStart = textFieldStart.getText();
//                        String strDateEnd = textFieldEnd.getText();
//                        java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
//                        java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    String strDateStart = comboDate.getSelectedItem() + " " + textFieldStart.getText();
                    String strDateEnd = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);


                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                        DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                        dateAxisDigits.setPositiveArrowVisible(true);
                        dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                        DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                        dateAxisSpeed.setPositiveArrowVisible(true);
                        dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                        DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

                        dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());
                        System.gc();


//                        long longTimeEnd = endDisplay.getTime();
//                        long longTimeStart = startDisplay.getTime();
//                        int difference = (int)(longTimeEnd - longTimeStart) / 1000;
//                        //System.out.println(difference);
//                        //timeSpan = difference;
//
////                        int max = (int)((long) endRecord.getTime() - bigParticle - timeSpan * 1000);
////                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        int max = (int)((long) endDisplay.getTime() - bigParticle - timeSpan * 1000);
//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//
//                        //if (min + timeSpan > max) timeSpan = 0;
////                        if (max > endRecord.getTime()) max = (int)(endRecord.getTime() - bigParticle);
//                        graphsSlider.setMaximum(max);
//                        graphsSlider.setValue(graphsSlider.getMaximum() - timeSpan * 1000);
////                        if (graphsSlider.getValue() < max) {
////                            graphsSlider.setValue(graphsSlider.getValue() + 1);
////                        } else graphsSlider.setValue(graphsSlider.getValue() - 1);
//                    }

                } catch (ParseException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: Wrong Date format!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    textFieldEnd.setText("23:59:59");
                }

            }
        });


        textFieldEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date startRecord = dateFormat.parse(labelStart.getText());
                    Date endRecord = dateFormat.parse(labelEnd.getText());
                    Date endDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldEnd.getText());
                    Date startDisplay = dateFormat.parse(comboDate.getSelectedItem() + " " + textFieldStart.getText());

                    Time startTime = Time.valueOf(textFieldStart.getText());
                    Time endTime = Time.valueOf(textFieldEnd.getText());
                    if (endTime.before(startTime)){
                        JOptionPane.showMessageDialog(null,
                                "Помилка: Кінець відображення не може бути пізніше дати початку запису!", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                        textFieldEnd.setText("23:59:59");
                    }


//                    if (endDisplay.after(endRecord)) {
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Кінець відображення не може бути пізніше дати кінця запису!\n" +
//                                        "endRecord: " + endRecord.toString() + "   End display: " + endDisplay, "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldEnd.setText(labelEnd.getText());
//                    }
//                    else if (startRecord.after(endDisplay)){
//                        JOptionPane.showMessageDialog(null,
//                                "Помилка: Початок відображення не може бути раніше дати кінця запису!", "Error Massage",
//                                JOptionPane.ERROR_MESSAGE);
//                        textFieldEnd.setText(labelEnd.getText());
//                    } else {

//                        String strDateStart = textFieldStart.getText();
//                        String strDateEnd = textFieldEnd.getText();
//                        java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
//                        java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);

                    String strDateStart = comboDate.getSelectedItem() + " " + textFieldStart.getText();
                    String strDateEnd = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
                    java.sql.Timestamp tsEnd = Timestamp.valueOf(strDateEnd);
                    java.sql.Timestamp tsStart = Timestamp.valueOf(strDateStart);


                    XYPlot xyPlotDigits = (XYPlot) chAllDigits2.getPlot();
                        DateAxis dateAxisDigits = (DateAxis) xyPlotDigits.getDomainAxis();

                        dateAxisDigits.setPositiveArrowVisible(true);
                        dateAxisDigits.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotSpeed = (XYPlot) chSpeed.getPlot();
                        DateAxis dateAxisSpeed = (DateAxis) xyPlotSpeed.getDomainAxis();

                        dateAxisSpeed.setPositiveArrowVisible(true);
                        dateAxisSpeed.setRange(tsStart.getTime(), tsEnd.getTime());

                        XYPlot xyPlotPressures = (XYPlot) chPres1.getPlot();
                        DateAxis dateAxisPressures = (DateAxis) xyPlotPressures.getDomainAxis();

//                        dateAxisPressures.setPositiveArrowVisible(true);
                        dateAxisPressures.setRange(tsStart.getTime(), tsEnd.getTime());
                        System.gc();
//                        long longTimeEnd = endDisplay.getTime();
//                        long longTimeStart = startDisplay.getTime();
//                        int difference = (int)(longTimeEnd - longTimeStart) / 1000;
//                        //System.out.println(difference);
//                        //timeSpan = difference;
//
////                        int max = (int)((long) endRecord.getTime() - bigParticle - timeSpan * 1000);
////                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//                        int max = (int)((long) endDisplay.getTime() - bigParticle - timeSpan * 1000);
//                        int min = (int)((long) startDisplay.getTime() - bigParticle);
//
//                        //if (min + timeSpan > max) timeSpan = 0;
////                        if (max > endRecord.getTime()) max = (int)(endRecord.getTime() - bigParticle);
//                        graphsSlider.setMaximum(max);
//                        graphsSlider.setValue(graphsSlider.getMaximum() - timeSpan * 1000);
////                        if (graphsSlider.getValue() < max) {
////                            graphsSlider.setValue(graphsSlider.getValue() + 1);
////                        } else graphsSlider.setValue(graphsSlider.getValue() - 1);
//                    }
//                    showGraphs();

                } catch (ParseException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: Wrong Date format!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    textFieldEnd.setText("23:59:59");
                }

            }
        });


        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Integer.decode(textSendCommand.getText()));
                System.out.println((byte) 0x71);
                System.out.println((byte) 113);
            }
        });

////////////////////////////Processing SYNCRO_TIME button/////////////////////////////////////////////////

        syncroTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MpusbWrapperTwo mwt = new MpusbWrapperTwo();

                int[] commands = new int[2];
                commands[0] = Integer.decode(textSendCommand.getText());
                commands[1] = Integer.decode(textSendCommand2.getText());

                if (mwt.setTime(commands))
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Час синхронізовано");
//                else
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Помилка синхронізації");
            }
        });

        readCarNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                readCarNo();
                if (!readCarNo())
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не вдалося зчитати...");
            }
        });

        loadLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readLog();
            }
        });


        loadCarNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carNoText.getText().isEmpty()) JOptionPane.showMessageDialog(frame, "Input correct car number and try again, please...");
                else
                    loadCarNo();
            }
        });

        loadCoefficientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!coeffSpeedWrite.getText().matches("-?\\d+(\\.\\d+)?")) ||

                   (!textWriteCoefOnePmax.getText().matches("-?\\d+(\\.\\d+)?")) ||
                   (!textWriteCoefOneUmax.getText().matches("-?\\d+(\\.\\d+)?")) ||
                   (!textWriteCoefOneUmin.getText().matches("-?\\d+(\\.\\d+)?")) ||

                   (!textWriteCoefTwoPmax.getText().matches("-?\\d+(\\.\\d+)?")) ||
                   (!textWriteCoefTwoUmax.getText().matches("-?\\d+(\\.\\d+)?")) ||
                   (!textWriteCoefTwoUmin.getText().matches("-?\\d+(\\.\\d+)?"))

                   )
                    JOptionPane.showMessageDialog(frame, "Input correct coefficients, please!...");

                else if ((Double.parseDouble(textWriteCoefOneUmin.getText()) > Double.parseDouble(textWriteCoefOneUmax.getText()))
                      || (Double.parseDouble(textWriteCoefTwoUmin.getText()) > Double.parseDouble(textWriteCoefTwoUmax.getText())))
                    JOptionPane.showMessageDialog(frame, "Umin could'n be larger than Umax...");

                else {
                    loadCoefficients();
                    coeffSpeedWrite.setText("");

                    textWriteCoefOnePmax.setText("");
                    textWriteCoefOneUmax.setText("");
                    textWriteCoefOneUmin.setText("");

                    textWriteCoefTwoPmax.setText("");
                    textWriteCoefTwoUmax.setText("");
                    textWriteCoefTwoUmin.setText("");
                }
            }
        });

////////////////////////////Processing READ_DEVICE button ////////////////////////////////////////////////


        readDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readDeviceProc();
//                new Thread() {
//                    public void run() {
////                        MpusbWrapperTwo mwt = new MpusbWrapperTwo();
////                        int[] commands = new int[2];
////                        commands[0] = Integer.decode(textSendCommand.getText());
////                        commands[1] = Integer.decode(textSendCommand2.getText());
//
//                        readDeviceLabel.setForeground(Color.GREEN);
//                        reading.setSelected(readDevice());
//                        if (!reading.isSelected()) {
//                            readDeviceLabel.setForeground(Color.GRAY);
//                            readDeviceLbl.setText("Збій передачі...");
//                            progressBar1.setIndeterminate(false);
//                            progressBar2.setValue(0);
////                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Спроба завантажити поточні дані...");
//
//                        }
//                        else {
//                            readDeviceLbl.setText("Дані отримано!");
//                            progressBar1.setIndeterminate(false);
//                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані отримано!");
//                            readDeviceLabel.setForeground(Color.GRAY);
//                            readDeviceLbl.setText("Очікування");
//                            progressBar2.setValue(0);
//
//
//                            progressBar2.setMaximum(30000 * 10);
//                            progressBar2.setValue(0);
//                            progressBar1.setIndeterminate(true);
//                            deleteAllDb();
//                            readDeviceButton.setText("Завантаження до БД");
//                            readDb();
//                            createBigTable();
//                            createDigitsTable();
//                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані зконвертовано у БД");
//
//                        }
//                        //progressBar2.setMaximum(1258291);
////                        progressBar2.setMaximum(30000 * 10);
////                        progressBar2.setValue(0);
////                        progressBar1.setIndeterminate(true);
////                        deleteAllDb();
////                        readDb();
////                        createBigTable();
////                        createDigitsTable();
//                        progressBar2.setValue(0);
//                        progressBar1.setIndeterminate(false);
//
//                    }
//                }.start();

            }
        });

        readDeviceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                readDeviceLbl.setText("Зчитування...");
                readDeviceLabel.setForeground(Color.RED);
                progressBar1.setIndeterminate(true);
//                progressBar1.setVisible(true);
//                progressBar1.setIndeterminate(true);

            }
        });

///////////////////////////////////Load DB Button///////////////////////////////////////////////////////////

//        final JDialog dlg = new JDialog(frame, "Progress Dialog", true);
////        JProgressBar dpb = new JProgressBar(0, 500);
//        dlg.add(BorderLayout.CENTER, dpb);
//        dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
//        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        dlg.setSize(300, 75);
//        dlg.setLocationRelativeTo(frame);


        loadDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDB();
//                getRecordStartEnd();
//                new Thread() {
//                    public void run() {
//                        dlg.setVisible(true);
//
////                        for(Component component : panelOne.getComponents()) {
////                            component.setEnabled(true);
////                        }
//                    }
//                }.start();
//
//
////                Thread t = new Thread(new Runnable() {
////                    public void run() {
////                        dlg.setVisible(true);
////                    }
////                });
////                t.start();
//
//
////                deleteAllDb();
////                readDb();
////                createBigTable();
////                createDigitsTable();
////
////                loadDBLabel.setForeground(Color.GREEN);
////                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані зконвертовано!");
//////                        loadDBLbl.setText("БД завантажено");
////                loadDBLbl.setText("Очікування");
////                loadDBLabel.setForeground(Color.GRAY);
////                progressBar1.setIndeterminate(false);
////                progressBar2.setValue(0);
//
//                for (int i = 0; i <= 500; i++) {
//                    //jl.setText("Count : " + i);
//                    dpb.setValue(i);
//                    if (dpb.getValue() == 100) {
//                        dlg.setVisible(false);
//                        System.exit(0);
//
//                    }
//                    try {
//                        Thread.sleep(25);
//                    } catch (InterruptedException e2) {
//                        e2.printStackTrace();
//                    }
//                }
//                dlg.setVisible(true);
//
////                try {
////                    Thread.sleep(25);
////                } catch (InterruptedException e5) {
////                    e5.printStackTrace();
////                }
//
//
            }
        });

        loadDBButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                loadDBLbl.setText("Завантаження БД...");
                loadDBLabel.setForeground(Color.RED);
                //progressBar1.setIndeterminate(true);
                progressBar2.setMinimum(0);
                progressBar2.setMaximum(12570624);
                progressBar2.setStringPainted(true);
//                progressBar1.setVisible(true);
                progressBar1.setIndeterminate(true);
//                frame.setEnabled(false);

               // tabbedPane1.setEnabled(false);
//                for(Component component : workWithDataPanel.getComponents()) {
//                    component.setEnabled(false);
//                }
            }
        });



////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////
        backupDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            saveDatabaseProc();
//             new Thread() {
//                 public void run() {
//                     try{
//                         String strUrl = "jdbc:derby:wagons";
//                         Connection backupConn = DriverManager.getConnection(strUrl);
//                         backUpDatabase(backupConn);
//                         backupDBLabel.setForeground(Color.GREEN);
//                         backupDBLbl.setText("Базу збережено");
//                         backupConn.close();
//                         progressBar1.setIndeterminate(false);
//                         JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Базу збережено!");
//                         backupDBLabel.setForeground(Color.GRAY);
//                         backupDBLbl.setText("Очікування");
//                     } catch (SQLException ex) {
//                         ex.printStackTrace(System.err);
//                     }
//                 }
//             }.start();

             }
        });

        backupDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                backupDBLbl.setText("Зберігання...");
                backupDBLabel.setForeground(Color.RED);
                progressBar1.setIndeterminate(true);
            }
        });


        restoreDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboDate.removeAllItems();

                dbUrl = "jdbc:derby:" + populateCombo();
                int urlLen = dbUrl.length();
//                String wagNo = dbUrl.substring(24, 29);
                String wagNo = dbUrl.substring(urlLen - 6, urlLen - 1);

                try {
                    Connection connSettingsInfo = DriverManager.getConnection(dbUrl + comboDate.getSelectedItem());
                    createSettingsInfoDataTwo(connSettingsInfo);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                refreshButton.setEnabled(true);

                JOptionPane.showMessageDialog(frame, "Робота з базою даних вагона №" + wagNo);
//                restoreDatabaseProc();
//                new Thread(){
//                    public void run(){
//                        try{
//                            String strUr0 = "jdbc:derby:;shutdown=true";
//                            System.out.println(DriverManager.getConnection(strUr0));
//                        } catch (SQLException ex) {
//                            //              ex.printStackTrace();
//                            System.out.println(ex.getErrorCode());
//                            ex.printStackTrace(System.err);
//                        }
//
//                        FileDialog fileDialog = new FileDialog(new Frame(), "Choose A Backup Folder)", FileDialog.LOAD);
//                        fileDialog.setVisible(true);
//                        String fileName = fileDialog.getDirectory();
//
//                        if (fileDialog.getDirectory() != null) {
//                            try {
//                                progressBar1.setIndeterminate(true);
//                                fileName = fileName.substring(0, fileName.lastIndexOf(System.getProperty("file.separator")));
//                                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//                                String strUrl = "jdbc:derby:wagons";
//                                Properties connectionProps = new Properties();
//                                connectionProps.put("restoreFrom", fileName);
//
//                                connectionProps.put("user", "pass");
//                                connectionProps.put("root", "wagons");
//                                Connection backupConn1 = DriverManager.getConnection(strUrl, connectionProps);
//                                backupConn1.commit();
//                                backupConn1.close();
//                            } catch (SQLException ex) {
//                                //              ex.printStackTrace();
//                                System.out.println(ex.getErrorCode());
//                                ex.printStackTrace(System.err);
//                            } catch (Exception es) {}
//                        } else {
//                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid database format");
//                        }
//                        restoreDBLabel.setForeground(Color.GREEN);
//                        restoreDBLbl.setText("Базу відтворено");
//
//                        spinner3.setModel(new SpinnerDateModel());
//                        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner3, "dd-MMM-yyyy HH:mm:ss");
//                        spinner3.setEditor(timeEditor);
//
//                        createBigTable();
//                        createDigitsTable();
//
//                        progressBar1.setIndeterminate(false);
//                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Базу відтворено!");
//                        restoreDBLabel.setForeground(Color.GRAY);
//                        restoreDBLbl.setText("Очікування");
//                    }
//                }.start();
//

            }


        });

        restoreDatabaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
//                restoreDBLbl.setText("Відтворення БД...");
//                restoreDBLabel.setForeground(Color.RED);
//                progressBar1.setIndeterminate(true);
            }
        });

/////////////////////////////////////Loading BIG TABLE//////////////////////////////
//        final AtomicBoolean running2 = new AtomicBoolean(false);
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                running2.set(!running2.get());
//                if (running2.get()) {
                    new Thread() {
                        public void run(){
//                            int v = 0;
//                            while (running2.get() && !createBigTable() && !createDigitsTable()) {
//                               // progressBar1.setIndeterminate(true);
//                            }
//                            if (!(running2.get() && !createBigTable() && !createDigitsTable())){
                            spinnerStart.setModel(new SpinnerDateModel());
                            JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerStart, "dd-MMM-yyyy HH:mm:ss");
                            spinnerStart.setEditor(timeEditor);


                            createBigTable();
//                                createDigitsTable(offsetToShow, secondsToShow);
                                loadCurretDBLabel.setForeground(Color.GREEN);
                                progressBar1.setIndeterminate(false);
                                loadCurrentDBLbl.setText("Базу завантажено");
//                            JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
//                            JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "dd-MM-yyyy HH:mm:ss");
//                            timeSpinner.setEditor(timeEditor);
//                            timeSpinner.setValue(new Date());
                        //    axisPanel.add(timeSpinner);
//                            spinner3.setValue(new Date());


//                            }

                        }
                    }.start();
//                }
//                createBigTable();
//                createDigitsTable();
//                loadCurretDBLabel.setForeground(Color.GREEN);
//                loadCurrentDBLbl.setText("Базу завантажено");
            }
        });

        button12.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                loadCurrentDBLbl.setText("Завантаження...");
                loadCurretDBLabel.setForeground(Color.RED);
                progressBar1.setIndeterminate(true);
            }
        });




        SelectAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SelectAll.isSelected()) {
                    Speed.setSelected(true);
                    Pres1.setSelected(true);
                    Pres2.setSelected(true);
                    Dig1.setSelected(true);
                    Dig2.setSelected(true);
                    Dig3.setSelected(true);
                    Dig4.setSelected(true);
                    Dig5.setSelected(true);
                    Dig6.setSelected(true);
                    Dig7.setSelected(true);
                    Dig8.setSelected(true);
                    Dig9.setSelected(true);
                    Dig10.setSelected(true);
                    Dig11.setSelected(true);
                    Dig12.setSelected(true);
                    Dig13.setSelected(true);
                    Dig14.setSelected(true);
                    Dig15.setSelected(true);
                    Dig16.setSelected(true);
                    Dig17.setSelected(true);
                    Dig18.setSelected(true);
                    Dig19.setSelected(true);
                    Dig20.setSelected(true);
                    Dig21.setSelected(true);
                    Dig22.setSelected(true);
                    Dig23.setSelected(true);
                    Dig24.setSelected(true);
                    Dig25.setSelected(true);
                    Dig26.setSelected(true);
                    Dig27.setSelected(true);
                    Dig28.setSelected(true);
                    Dig29.setSelected(true);
                    Dig30.setSelected(true);
                    Dig31.setSelected(true);
                    Dig32.setSelected(true);
                    Dig33.setSelected(true);
                    Dig34.setSelected(true);
                    Dig35.setSelected(true);
                    Dig36.setSelected(true);
                    Dig37.setSelected(true);
                } else {
                    Speed.setSelected(false);
                    Pres1.setSelected(false);
                    Pres2.setSelected(false);
                    Dig1.setSelected(false);
                    Dig2.setSelected(false);
                    Dig3.setSelected(false);
                    Dig4.setSelected(false);
                    Dig5.setSelected(false);
                    Dig6.setSelected(false);
                    Dig7.setSelected(false);
                    Dig8.setSelected(false);
                    Dig9.setSelected(false);
                    Dig10.setSelected(false);
                    Dig11.setSelected(false);
                    Dig12.setSelected(false);
                    Dig13.setSelected(false);
                    Dig14.setSelected(false);
                    Dig15.setSelected(false);
                    Dig16.setSelected(false);
                    Dig17.setSelected(false);
                    Dig18.setSelected(false);
                    Dig19.setSelected(false);
                    Dig20.setSelected(false);
                    Dig21.setSelected(false);
                    Dig22.setSelected(false);
                    Dig23.setSelected(false);
                    Dig24.setSelected(false);
                    Dig25.setSelected(false);
                    Dig26.setSelected(false);
                    Dig27.setSelected(false);
                    Dig28.setSelected(false);
                    Dig29.setSelected(false);
                    Dig30.setSelected(false);
                    Dig31.setSelected(false);
                    Dig32.setSelected(false);
                    Dig33.setSelected(false);
                    Dig34.setSelected(false);
                    Dig35.setSelected(false);
                    Dig36.setSelected(false);
                    Dig37.setSelected(false);
                }
            }
        });

        timeSpanTxtFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeSpan = Integer.parseInt(timeSpanTxtFld.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    Date startRecord = dateFormat.parse(labelStart.getText());
                    Date endRecord = dateFormat.parse(labelEnd.getText());
                    Date startDisplay = dateFormat.parse(textFieldStart.getText());
                    Date endDisplay = dateFormat.parse(textFieldEnd.getText());
                    int min = (int)((long) startDisplay.getTime() - bigParticle);
                    int max = (int)((long) endDisplay.getTime() - bigParticle - timeSpan * 1000);
                    graphsSlider.setMinimum(min);
                    graphsSlider.setMaximum(max);
                    graphsSlider.setValue(min);
                } catch (Exception e1) {}
            }
        });


        timeSpanTxtFld.addMouseListener(new MouseAdapter() {
        });
//        printButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (tableJointTable != null) try {
//                    tableJointTable.print();
//
//                } catch (PrinterException e1) {
//                    e1.printStackTrace();
//                } else {
//                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо роздрукувати. Таблиця порожня");
//                }
//            }
//        });


        dropOldDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response =
                JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Ви впевнені, що хочете видалити дані, старіше за 6 місяців?",
                        "Видалення застарілих даних", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не знайдено БД старіше за 6 місяців.");
                }

            }
        });

        printSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chartSpeed != null) {

//                    chartSpeed.createChartPrintJob();
//                    ChartPanel chartsPrint = new ChartPanel(null);
//                    chartsPrint.add(chartSpeed);
//                    chartsPrint.add(chartPress1);
//                    chartsPrint.add(chartAllDigits);
//                    chartsPrint.createChartPrintJob();


                    // chartAllDigits.createChartPrintJob();
//                    chartAllDigits2.print();


//                    Toolkit tkp = graphDig.getToolkit();
//                    PrintJob pjp = tkp.getPrintJob(frame, null, null);
//                    Graphics g = pjp.getGraphics();
////                    graphDig.print(g);
//                    scrollGraphs.print(g);
//
//                    g.dispose();
//                    pjp.end();


                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setJobName("Print Java Component");

                    job.setPrintable (new Printable() {
                        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
                            if (pageIndex > 0) {
                                return(NO_SUCH_PAGE);
                            } else {
                                Graphics2D g2d = (Graphics2D)g;
                                g2d.translate( pageFormat.getImageableX() / 2,
                                        pageFormat.getImageableY() / 2);
 //                               g2d.setClip(10, 10, graphDig.getWidth(), graphDig.getHeight());

                                chartSpeed.setSize(600, 120);
                                chartPress1.setSize(600, 100);
                                chartAllDigits2.setSize(600, 600);

//                                chartSpeed.setPreferredSize(new Dimension(600, 200));
//                                chartPress1.setPreferredSize(new Dimension(600, 300));
//                                chartAllDigits2.setPreferredSize(new Dimension(600, 700));

//                                graphDig.setSize(600, 600);

                                graphDig.paint(g2d);

//                                chartSpeed.paint(g2d);
//                                chartPress1.paint(g2d);
//                                chartAllDigits.paint(g2d);
                                return(PAGE_EXISTS);
                            }
                        }
                    });

                    if (job.printDialog()) {
                        try {
                            job.print();
                        } catch (PrinterException ep) {
                            System.err.println(ep.getMessage());
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не знайдено даних для друку.");
                }
            }
        });

       // final


        graphsSlider.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

//                Graphics2D g = (Graphics2D) graphicsPanel.getGraphics();
//                g.setXORMode(Color.orange);
//
//                if (verticalTraceLineDM != null) {
//                    g.draw(verticalTraceLineDM);
//                    verticalTraceLineDM.setLine(e.getX() + 90, 24, e.getX() + 90, 900);
//
//                }
//                else {
//                    verticalTraceLineDM = new Line2D.Float(e.getX() + 90, 24, e.getX() + 90, 900);
//
//                }
//                g.draw(verticalTraceLineDM);
//                g.setPaintMode();




//                verticalTraceLineDM = new Line2D.Float(e.getX() + 300, e.getY() + 100, e.getX() + 300, e.getY() + 500);
////                g.drawLine(e.getX() + 300, e.getY() + 100, e.getX() + 300, e.getY() + 500);
//                g.draw(verticalTraceLineDM);
//                verticalTraceLineDM = null;

                JLabel label = new JLabel("Verylonglabel");

// Create tool tip.
                label.setToolTipText(label.getText());

// Set the size of the label
                label.setPreferredSize(new Dimension(80,40));// Width, Height
                label.setAlignmentX(e.getX());
                label.setAlignmentY(e.getY());

//                verticalTraceLineDM = null;
//                graphicsPanel.paintComponents(g);

//                Graphics g = panelOne.getGraphics();
//                //scrollGraphs.repaint();
//                g.drawLine(e.getX() + 300, e.getY() + 100, e.getX() + 300, e.getY() + 500);
//                    super.paintComponent(g);  // fixes the immediate problem.
//                    Graphics2D g2 = (Graphics2D) g;
//
//                    g2.draw(horizontalLine);
//                    g2.draw(verticalLine);
//                Graphics2D
//                Line2D lin = new Line2D.Float(e.getX(), e.getY() + 20, e.getX(), e.getY() + 50);
            }
        });

        graphsSlider.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

//                Graphics2D g = (Graphics2D) graphicsPanel.getGraphics();
//                g.setXORMode(Color.orange);
//
//                if (verticalTraceLineDM != null) {
//                    g.draw(verticalTraceLineDM);
////                    int x = graphsSlider.getValue();
//                    verticalTraceLineDM.setLine(e.getX() + 121, 24, e.getX() + 121, 900);
////                    verticalTraceLineDM.setLine(x, 24, x, 900);
//
//                }
//                else {
//                    verticalTraceLineDM = new Line2D.Float(e.getX() + 121, 24, e.getX() + 121, 900);
//
//                }
//                g.draw(verticalTraceLineDM);
//                g.setPaintMode();

            }
        });

        graphsSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

//                Graphics2D g = (Graphics2D) graphicsPanel.getGraphics();
//                g.setXORMode(Color.orange);
//
//                if (verticalTraceLineDM != null) {
//                    g.draw(verticalTraceLineDM);
//                    verticalTraceLineDM.setLine(e.getX() + 90, 24, e.getX() + 90, 900);
//
//                }
//                else {
//                    verticalTraceLineDM = new Line2D.Float(e.getX() + 90, 24, e.getX() + 90, 900);
//
//                }
//                g.draw(verticalTraceLineDM);
//                g.setPaintMode();
//


//                //Graphics g = panelOne.getGraphics();
//                Graphics g = chartSpeed.getGraphics();
//                g.drawLine(e.getX() + 125 - 43, 27, e.getX() + 125 - 43, 800);
//                Graphics g1 = chartPress1.getGraphics();
//                g1.drawLine(e.getX() + 125 - 43, 8, e.getX() + 125 - 43, 800);
//                Graphics g2 = chartAllDigits2.getGraphics();
//                g2.drawLine(e.getX() + 125 - 43, 8, e.getX() + 125 - 43, 1000);
//                repaint();
//
//                final Graphics2D g3 = (Graphics2D) graphicsPanel.getGraphics();
//
//                Line2D horizontalTraceLine = new Line2D.Float(e.getX() + 125 - 43, 27, e.getX() + 125 - 43, 800);
//                g3.draw(horizontalTraceLine);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
//                Graphics g = chartSpeed.getGraphics();
//                g.drawLine(e.getX() + 125 - 43, 27, e.getX() + 125 - 43, 800);
//                Graphics g1 = chartPress1.getGraphics();
//                g1.drawLine(e.getX() + 125 - 43, 8, e.getX() + 125 - 43, 800);
//                Graphics g2 = chartAllDigits2.getGraphics();
//                g2.drawLine(e.getX() + 125 - 43, 8, e.getX() + 125 - 43, 1000);
//
//                repaint();


            }

        });

//        graphsSlider.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseExited(MouseEvent e) {
//                super.mouseExited(e);
//            }
//        });

        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verticalTraceLineDM = null;
                nextDataProc();
//                offsetToShow += secondsToShow;
//                //secondsToShow += 1000;
////                try {
////                    DriverManager.getConnection("jdbc:derby:wagons;shutdown=true");
////                } catch (SQLException e1) {
////                    e1.printStackTrace();
////                }
//
//                //jointTable(offsetToShow, secondsToShow);
//                System.gc();
//                createBigTable();
////                createDigitsTable(offsetToShow, secondsToShow);
//                showGraphs();
//                System.gc();
////                System.gc();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousDataProc();
//                offsetToShow = (offsetToShow > secondsToShow ? offsetToShow - secondsToShow : 0);
//                //secondsToShow += 1000;
//                System.gc();
//                createBigTable();
////                createDigitsTable(offsetToShow, secondsToShow);
//                showGraphs();
//                System.gc();

            }
        });


        comboDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldStart.setText("00:00:00");
                textFieldEnd.setText("23:59:59");
                spinnerStart.setValue(Time.valueOf("00:00:00"));
                spinnerEnd.setValue(Time.valueOf("23:59:59"));
                isFirstDatePick = true;

            }
        });
        graphicsPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                verticalTraceLineDM = null;
            }
        });

        Speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
//                if (!Speed.isSelected()) {
//                    chartSpeed.setVisible(false);
//                    chartPress1.getChart().getXYPlot().getDomainAxis().setVisible(true);
//                    chartPress1.setPreferredSize(new Dimension(-1, chartPress1.getHeight() * 4));
//                } else if (Speed.isSelected()) {
//                    chartSpeed.setVisible(true);
//                    chartPress1.getChart().getXYPlot().getDomainAxis().setVisible(false);
//                    chartPress1.setPreferredSize(new Dimension(-1, chartPress1.getHeight() / 1));
//
//                }
            }
        });

//        Dig1.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig1.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(0, false);
//                } else if (Dig1.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(0, true);
//                }
//
//            }
//        });
//
//        Dig2.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig2.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(1, false);
//                } else if (Dig2.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(1, true);
//                }
//            }
//        });
//
//        Dig3.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig3.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(2, false);
//                } else if (Dig3.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(2, true);
//                }
//            }
//        });
//
//        Dig4.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig4.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(3, false);
//                } else if (Dig4.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(3, true);
//                }
//
//            }
//        });
//
//        Dig5.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig5.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(4, false);
//                } else if (Dig5.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(4, true);
//                }
//
//            }
//        });
//
//        Dig6.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig6.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(5, false);
//                } else if (Dig6.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(5, true);
//                }
//
//            }
//        });
//
//        Dig7.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig7.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(6, false);
//                } else if (Dig7.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(6, true);
//                }
//
//            }
//        });
//
//        Dig8.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig8.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(7, false);
//                } else if (Dig8.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(7, true);
//                }
//
//            }
//        });
//
//        Dig9.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig9.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(8, false);
//                } else if (Dig9.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(8, true);
//                }
//
//            }
//        });
//
//        Dig10.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig10.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(9, false);
//                } else if (Dig10.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(9, true);
//                }
//
//            }
//        });
//
//        Dig11.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig11.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(10, false);
//                } else if (Dig11.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(10, true);
//                }
//
//            }
//        });
//
//        Dig12.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig12.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(11, false);
//                } else if (Dig12.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(11, true);
//                }
//            }
//        });
//
//        Dig13.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig13.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(12, false);
//                } else if (Dig13.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(12, true);
//                }
//            }
//        });
//
//        Dig14.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig14.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(13, false);
//                } else if (Dig14.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(13, true);
//                }
//
//            }
//        });
//
//        Dig15.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig15.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(14, false);
//                } else if (Dig15.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(14, true);
//                }
//
//            }
//        });
//
//        Dig16.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig16.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(15, false);
//                } else if (Dig16.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(15, true);
//                }
//
//            }
//        });
//
//        Dig17.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig17.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(16, false);
//                } else if (Dig17.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(16, true);
//                }
//
//            }
//        });
//
//        Dig18.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig18.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(17, false);
//                } else if (Dig18.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(17, true);
//                }
//
//            }
//        });
//
//        Dig19.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig19.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(18, false);
//                } else if (Dig19.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(18, true);
//                }
//            }
//        });
//
//        Dig20.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig20.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(19, false);
//                } else if (Dig20.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(19, true);
//                }
//            }
//        });
//
//        Dig21.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig21.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(20, false);
//                } else if (Dig21.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(20, true);
//                }
//
//            }
//        });
//
//        Dig22.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig22.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(21, false);
//                } else if (Dig22.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(21, true);
//                }
//            }
//        });
//
//        Dig23.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig23.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(22, false);
//                } else if (Dig23.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(22, true);
//                }
//            }
//        });
//
//        Dig24.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig24.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(23, false);
//                } else if (Dig24.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(23, true);
//                }
//
//            }
//        });
//
//        Dig25.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig25.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(24, false);
//                } else if (Dig25.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(24, true);
//                }
//
//            }
//        });
//
//        Dig26.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig26.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(25, false);
//                } else if (Dig26.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(25, true);
//                }
//
//            }
//        });
//
//        Dig27.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig27.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(26, false);
//                } else if (Dig27.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(26, true);
//                }
//
//            }
//        });
//
//        Dig28.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig28.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(27, false);
//                } else if (Dig28.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(27, true);
//                }
//
//            }
//        });
//
//        Dig29.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig29.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(28, false);
//                } else if (Dig29.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(28, true);
//                }
//
//            }
//        });
//
//        Dig30.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig30.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(29, false);
//                } else if (Dig30.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(29, true);
//                }
//
//            }
//        });
//
//        Dig31.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig31.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(30, false);
//                } else if (Dig31.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(30, true);
//                }
//
//            }
//        });
//
//        Dig32.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig32.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(31, false);
//                } else if (Dig32.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(31, true);
//                }
//            }
//        });
//
//        Dig33.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig33.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(32, false);
//                } else if (Dig33.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(32, true);
//                }
//            }
//        });
//
//        Dig34.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig34.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(33, false);
//                } else if (Dig34.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(33, true);
//                }
//
//            }
//        });
//
//        Dig35.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig35.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(34, false);
//                } else if (Dig35.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(34, true);
//                }
//
//            }
//        });
//
//        Dig36.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig36.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(35, false);
//                } else if (Dig36.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(35, true);
//                }
//
//            }
//        });
//
//        Dig37.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (!Dig37.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(36, false);
//                } else if (Dig37.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(36, true);
//                }
//
//            }
//        });

        allDigital.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!allDigital.isSelected()) {
                    chartAllDigits2.setVisible(false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(0, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(1, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(2, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(3, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(4, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(5, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(6, false);
                } else if (allDigital.isSelected()) {
                    chartAllDigits2.setVisible(true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(0, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(1, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(2, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(3, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(4, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(5, true);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(6, true);
                }

            }
        });

        Pres1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!Pres1.isSelected()) {
                    chartPress1.getChart().getXYPlot().getRenderer().setSeriesVisible(0, false);
                } else if (Pres1.isSelected()) {
                    chartPress1.getChart().getXYPlot().getRenderer().setSeriesVisible(0, true);
                }
            }
        });

        Pres2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!Pres2.isSelected()) {
                    chartPress1.getChart().getXYPlot().getRenderer().setSeriesVisible(1, false);
                } else if (Pres2.isSelected()) {
                    chartPress1.getChart().getXYPlot().getRenderer().setSeriesVisible(1, true);
                }
            }
        });

//        allDigital.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (allDigital.isSelected()) {
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(0, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(1, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(2, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(3, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(4, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(5, false);
//                    chartAllDigits2.getChart().getXYPlot().getRenderer().setSeriesVisible(6, false);
//                }
//
//            }
//        });


        PDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String spinnerStartStr = String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
                String spinnerEndStr = String.valueOf(new Time(((Date)spinnerEnd.getValue()).getTime()));

                String folderPath = "c:\\PDF_Reports\\" + carNo + "\\" + comboDate.getSelectedItem();
                File file = new File(folderPath);
                if (!file.exists()) file.mkdirs();

                String filename = spinnerStartStr.replaceAll(":", "-") + "_" + spinnerEndStr.replaceAll(":", "-");
                String pdfPath = folderPath + "\\" + filename + ".pdf";

//                String pdfPath = "d:\\123\\" + carNo + "_" + comboDate.getSelectedItem() + ".pdf";


                try {
                    convertToPDF(chSpeed, 600, 800, pdfPath);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Файл збережено до теки " + pdfPath);

                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File(pdfPath);
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            // no application registered for PDFs
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No application registered for PDF detected in the system");
                        }
                    }


//                    FileDialog fileDialog = new FileDialog(new Frame(), "Choose A Backup Folder)", FileDialog.LOAD);
//                    fileDialog.setDirectory(folderPath);
//                    fileDialog.setVisible(true);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не вдалося зберегти файл...");
                }



            }
        });


        realTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textReadCoefOneUmin.getText().equals("")){
                    if (!readCarNo()) {
                     JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо зчитати коефіцієнти. \n" +
                     "Перевірте підключення пристрою і повторіть спробу.");
                    }
                    else {
                        DTSCTest rtMode = new DTSCTest(frmTwo);
                        rtMode.pack();
                        RefineryUtilities.centerFrameOnScreen(rtMode);
                        rtMode.setVisible(true);
                        rtMode.start();
                    }
                } else {
                    DTSCTest rtMode = new DTSCTest(frmTwo);
                    rtMode.pack();
                    RefineryUtilities.centerFrameOnScreen(rtMode);
                    rtMode.setVisible(true);
                    rtMode.start();

                }
            }

        });

//        tabbedPane1.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                System.gc();
//            }
//        });



    }


    private transient Line2D verticalTraceLine;

    void loadDigitDataSet() {
        int width = scrollGraphs.getWidth() - 163;

        chAllDigits2 = ChartFactory.createTimeSeriesChart(" \n ", "", "", null);
        chartAllDigits2 = new ChartPanel(chAllDigits2, true);

        chartAllDigits2.setLayout(new GridLayout());

        chAllDigits2.removeLegend();
        chAllDigits2.getTitle().setPosition(RectangleEdge.LEFT);
        chartAllDigits2.setAlignmentX((float) 0.0);

        chartAllDigits2.setPreferredSize(new Dimension(width + 135, 650));
        chartAllDigits2.setMaximumSize(new Dimension(width + 135, 650));
        chartAllDigits2.setMinimumSize(new Dimension(width + 135, 650));

        chartAllDigits2.setMaximumDrawHeight(650);
        chartAllDigits2.setMinimumDrawHeight(10);

        XYPlot xyPlot = (XYPlot) chAllDigits2.getPlot();

        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
        xyPlot.getDomainAxis().setTickLabelFont(new Font("TimesNewRoman", Font.PLAIN, 12));
        NumberAxis numberAxis = (NumberAxis) xyPlot.getRangeAxis();
        numberAxis.setRange(0.0, 370.0);
        numberAxis.setTickUnit(new NumberTickUnit(10.0));

//        dateAxis.setRange(ts17.getTime(), ts17.getTime() + timeSpan * 1000);

        JFreeChart chart7 = chartAllDigits2.getChart();
        XYPlot localXYPlot = (XYPlot) chart7.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();



        JDBCXYDataset jdsDig1 = createDatasetDigitals(offsetToShow, secondsToShow, "dig1", 0);
        localXYPlot.setDataset(0, jdsDig1);
        jdsDig1.close();

        JDBCXYDataset jdsDig2 = createDatasetDigitals(offsetToShow, secondsToShow, "dig2", 10);
        localXYPlot.setDataset(1, jdsDig2);
        jdsDig2.close();


        JDBCXYDataset jdsDig3 = createDatasetDigitals(offsetToShow, secondsToShow, "dig3", 20);
        localXYPlot.setDataset(2, jdsDig3);
        jdsDig3.close();

        JDBCXYDataset jdsDig4 = createDatasetDigitals(offsetToShow, secondsToShow, "dig4", 30);
        localXYPlot.setDataset(3, jdsDig4);
        jdsDig4.close();

        JDBCXYDataset jdsDig5 = createDatasetDigitals(offsetToShow, secondsToShow, "dig5", 40);
        localXYPlot.setDataset(4, jdsDig5);
        jdsDig5.close();

        JDBCXYDataset jdsDig6 = createDatasetDigitals(offsetToShow, secondsToShow, "dig6", 50);
        localXYPlot.setDataset(5, jdsDig6);
        jdsDig6.close();

        JDBCXYDataset jdsDig7 = createDatasetDigitals(offsetToShow, secondsToShow, "dig7", 60);
        localXYPlot.setDataset(6, jdsDig7);
        jdsDig7.close();

        JDBCXYDataset jdsDig8 = createDatasetDigitals(offsetToShow, secondsToShow, "dig8", 70);
        JDBCXYDataset jdsDig9 = createDatasetDigitals(offsetToShow, secondsToShow, "dig9", 80);
        JDBCXYDataset jdsDig10 = createDatasetDigitals(offsetToShow, secondsToShow, "dig10", 90);
        JDBCXYDataset jdsDig11 = createDatasetDigitals(offsetToShow, secondsToShow, "dig11", 100);
        JDBCXYDataset jdsDig12 = createDatasetDigitals(offsetToShow, secondsToShow, "dig12", 110);
        JDBCXYDataset jdsDig13 = createDatasetDigitals(offsetToShow, secondsToShow, "dig13", 120);
        JDBCXYDataset jdsDig14 = createDatasetDigitals(offsetToShow, secondsToShow, "dig14", 130);
        JDBCXYDataset jdsDig15 = createDatasetDigitals(offsetToShow, secondsToShow, "dig15", 140);
        JDBCXYDataset jdsDig16 = createDatasetDigitals(offsetToShow, secondsToShow, "dig16", 150);
        JDBCXYDataset jdsDig17 = createDatasetDigitals(offsetToShow, secondsToShow, "dig17", 160);
        JDBCXYDataset jdsDig18 = createDatasetDigitals(offsetToShow, secondsToShow, "dig18", 170);
        JDBCXYDataset jdsDig19 = createDatasetDigitals(offsetToShow, secondsToShow, "dig19", 180);
        JDBCXYDataset jdsDig20 = createDatasetDigitals(offsetToShow, secondsToShow, "dig20", 190);
        JDBCXYDataset jdsDig21 = createDatasetDigitals(offsetToShow, secondsToShow, "dig21", 200);
        JDBCXYDataset jdsDig22 = createDatasetDigitals(offsetToShow, secondsToShow, "dig22", 210);
        JDBCXYDataset jdsDig23 = createDatasetDigitals(offsetToShow, secondsToShow, "dig23", 220);
        JDBCXYDataset jdsDig24 = createDatasetDigitals(offsetToShow, secondsToShow, "dig24", 230);
        JDBCXYDataset jdsDig25 = createDatasetDigitals(offsetToShow, secondsToShow, "dig25", 240);
        JDBCXYDataset jdsDig26 = createDatasetDigitals(offsetToShow, secondsToShow, "dig26", 250);
        JDBCXYDataset jdsDig27 = createDatasetDigitals(offsetToShow, secondsToShow, "dig27", 260);
        JDBCXYDataset jdsDig28 = createDatasetDigitals(offsetToShow, secondsToShow, "dig28", 270);
        JDBCXYDataset jdsDig29 = createDatasetDigitals(offsetToShow, secondsToShow, "dig29", 280);
        JDBCXYDataset jdsDig30 = createDatasetDigitals(offsetToShow, secondsToShow, "dig30", 290);
        JDBCXYDataset jdsDig31 = createDatasetDigitals(offsetToShow, secondsToShow, "dig31", 300);
        JDBCXYDataset jdsDig32 = createDatasetDigitals(offsetToShow, secondsToShow, "dig32", 310);
        JDBCXYDataset jdsDig33 = createDatasetDigitals(offsetToShow, secondsToShow, "dig33", 320);
        JDBCXYDataset jdsDig34 = createDatasetDigitals(offsetToShow, secondsToShow, "dig34", 330);
        JDBCXYDataset jdsDig35 = createDatasetDigitals(offsetToShow, secondsToShow, "dig35", 340);
        JDBCXYDataset jdsDig36 = createDatasetDigitals(offsetToShow, secondsToShow, "dig36", 350);
        JDBCXYDataset jdsDig37 = createDatasetDigitals(offsetToShow, secondsToShow, "dig37", 360);
//

        localXYPlot.setDataset(0, jdsDig1);
//        localXYPlot.setDataset(1, jdsDig2);
//        localXYPlot.setDataset(2, jdsDig3);
//        localXYPlot.setDataset(3, jdsDig4);
//        localXYPlot.setDataset(4, jdsDig5);
//        localXYPlot.setDataset(5, jdsDig6);
//        localXYPlot.setDataset(6, jdsDig7);
//        localXYPlot.setDataset(7, jdsDig8);
//        localXYPlot.setDataset(8, jdsDig9);
//        localXYPlot.setDataset(9, jdsDig10);
//        localXYPlot.setDataset(10, jdsDig11);
//        localXYPlot.setDataset(11, jdsDig12);
//        localXYPlot.setDataset(12, jdsDig13);
//        localXYPlot.setDataset(13, jdsDig14);
//        localXYPlot.setDataset(14, jdsDig15);
//        localXYPlot.setDataset(15, jdsDig16);
//        localXYPlot.setDataset(16, jdsDig17);
//        localXYPlot.setDataset(17, jdsDig18);
//        localXYPlot.setDataset(18, jdsDig19);
//        localXYPlot.setDataset(19, jdsDig20);
//        localXYPlot.setDataset(20, jdsDig21);
//        localXYPlot.setDataset(21, jdsDig22);
//        localXYPlot.setDataset(22, jdsDig23);
//        localXYPlot.setDataset(23, jdsDig24);
//        localXYPlot.setDataset(24, jdsDig25);
//        localXYPlot.setDataset(25, jdsDig26);
//        localXYPlot.setDataset(26, jdsDig27);
//        localXYPlot.setDataset(27, jdsDig28);
//        localXYPlot.setDataset(28, jdsDig29);
//        localXYPlot.setDataset(29, jdsDig30);
//        localXYPlot.setDataset(30, jdsDig31);
//        localXYPlot.setDataset(31, jdsDig32);
//        localXYPlot.setDataset(32, jdsDig33);
//        localXYPlot.setDataset(33, jdsDig34);
//        localXYPlot.setDataset(34, jdsDig35);
//        localXYPlot.setDataset(35, jdsDig36);
//        localXYPlot.setDataset(36, jdsDig37);
//
//        renderer.setSeriesOutlinePaint(1, Color.GREEN);
//        renderer.setSeriesOutlinePaint(2, Color.GREEN);
//        renderer.setSeriesOutlinePaint(3, Color.GREEN);
//        renderer.setSeriesOutlinePaint(4, Color.GREEN);
//        renderer.setSeriesOutlinePaint(5, Color.GREEN);
//        renderer.setSeriesPaint(1, Color.GREEN);
//        renderer.setSeriesPaint(2, Color.GREEN);
//        renderer.setSeriesPaint(3, Color.GREEN);
//        renderer.setSeriesPaint(4, Color.GREEN);
//        renderer.setSeriesPaint(5, Color.GREEN);
//        renderer.setBasePaint(Color.GREEN);
//
//        localXYPlot.setRenderer(1, renderer);
//        localXYPlot.setRenderer(2, renderer);
//        localXYPlot.setRenderer(3, renderer);
//        localXYPlot.setRenderer(4, renderer);
//        localXYPlot.setRenderer(5, renderer);
//
//        System.out.println("Speed datasets: " + localXYPlot.getDatasetCount());
//        localXYPlot.setBackgroundPaint(Color.WHITE);
//        localXYPlot.setDomainGridlinePaint(Color.GRAY);
//        localXYPlot.setRangeGridlinePaint(Color.GRAY);
//
//        graphDig.add(chartAllDigits2);
    }

    private int decimation = 0;

    void showGraphsInThread(){
        final JDialog dlgDbSave = new JDialog(frame, "Хід завантаження...", true);
        dlgDbSave.add(BorderLayout.CENTER, dpb);
        final JLabel dialogLabel = new JLabel("Завантаження даних з бази...");
        dlgDbSave.add(BorderLayout.NORTH, dialogLabel);
        dlgDbSave.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDbSave.setSize(300, 75);
        dlgDbSave.setLocationRelativeTo(frame);
        dpb.setStringPainted(false);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try{

                    String dbUrlShow = dbUrl + comboDate.getSelectedItem();
                    try {
//                        if (!dbUrl.equals("") && conn.isClosed()){
                            conn = DriverManager.getConnection(dbUrlShow);
//                        }
//                        else JOptionPane.showMessageDialog(frame, "Неможливо встановити з'єднання з базою даних");


                        dpb.setIndeterminate(true);
                        dialogLabel.setText("Відображення таблиць...");
                        if (isFirstDatePick) {

                            createBigTable(conn);
                            isFirstDatePick = false;
                        }
                        dialogLabel.setText("Відображення графіків...");
                        showGraphs(conn);
                        PDFButton.setEnabled(true);
                        dpb.setIndeterminate(false);
                        dlgDbSave.setVisible(false);

                    } catch (SQLException esql) {}


                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                    dlgDbSave.setVisible(false);
                }

            }
        });
        t.start();
        dlgDbSave.setVisible(true);

    }

    public int digSigsCalc() {
        int digSigsNumber = 0;

        if (Dig1.isSelected()) digSigsNumber++;
        if (Dig2.isSelected()) digSigsNumber++;
        if (Dig3.isSelected()) digSigsNumber++;
        if (Dig4.isSelected()) digSigsNumber++;
        if (Dig5.isSelected()) digSigsNumber++;
        if (Dig6.isSelected()) digSigsNumber++;
        if (Dig7.isSelected()) digSigsNumber++;
        if (Dig8.isSelected()) digSigsNumber++;
        if (Dig9.isSelected()) digSigsNumber++;
        if (Dig10.isSelected()) digSigsNumber++;
        if (Dig11.isSelected()) digSigsNumber++;
        if (Dig12.isSelected()) digSigsNumber++;
        if (Dig13.isSelected()) digSigsNumber++;
        if (Dig14.isSelected()) digSigsNumber++;
        if (Dig15.isSelected()) digSigsNumber++;
        if (Dig16.isSelected()) digSigsNumber++;
        if (Dig17.isSelected()) digSigsNumber++;
        if (Dig18.isSelected()) digSigsNumber++;
        if (Dig19.isSelected()) digSigsNumber++;
        if (Dig20.isSelected()) digSigsNumber++;
        if (Dig21.isSelected()) digSigsNumber++;
        if (Dig22.isSelected()) digSigsNumber++;
        if (Dig23.isSelected()) digSigsNumber++;
        if (Dig24.isSelected()) digSigsNumber++;
        if (Dig25.isSelected()) digSigsNumber++;
        if (Dig26.isSelected()) digSigsNumber++;
        if (Dig27.isSelected()) digSigsNumber++;
        if (Dig28.isSelected()) digSigsNumber++;
        if (Dig29.isSelected()) digSigsNumber++;
        if (Dig30.isSelected()) digSigsNumber++;
        if (Dig31.isSelected()) digSigsNumber++;
        if (Dig32.isSelected()) digSigsNumber++;
        if (Dig33.isSelected()) digSigsNumber++;
        if (Dig34.isSelected()) digSigsNumber++;
        if (Dig35.isSelected()) digSigsNumber++;
        if (Dig36.isSelected()) digSigsNumber++;
        if (Dig37.isSelected()) digSigsNumber++;

        return digSigsNumber;
    }

    public String[] makeTickLabels(int tickNumber) {
        String tickLabels[] = new String[tickNumber + 1];
        int i = tickNumber;
        //i++;

        if (Dig1.isSelected()) {tickLabels[i] =     "АРШ ввім."; i--;}
        if (Dig2.isSelected()) {tickLabels[i] =     "АЛС ввім."; i--;}
        if (Dig3.isSelected()) {tickLabels[i] =     "Радіост. ввім."; i--;}
        if (Dig4.isSelected()) {tickLabels[i] =     "ЕПК ввімк."; i--;}
        if (Dig5.isSelected()) {tickLabels[i] =     "РП відновл."; i--;}
        if (Dig6.isSelected()) {tickLabels[i] =     "Рух від КАХ"; i--;}
        if (Dig7.isSelected()) {tickLabels[i] =     "Ввімк. ВАХ"; i--;}
        if (Dig8.isSelected()) {tickLabels[i] =     "Ввімк. ВАД"; i--;}
        if (Dig9.isSelected()) {tickLabels[i] =     "Ввімк. ОВТ"; i--;}
        if (Dig10.isSelected()) {tickLabels[i] =    "Зачин. дверей"; i--;}


        if (Dig11.isSelected()) {tickLabels[i] = "          КБ(ПБ)"; i--;}
        if (Dig12.isSelected()) {tickLabels[i] = "  Відсутня част."; i--;}
        if (Dig13.isSelected()) {tickLabels[i] = "   Доп.шв.0 км/г"; i--;}
        if (Dig14.isSelected()) {tickLabels[i] = " Доп.шв. 40 км/г"; i--;}
        if (Dig15.isSelected()) {tickLabels[i] = " Доп.шв. 60 км/г"; i--;}
        if (Dig16.isSelected()) {tickLabels[i] = " Доп.шв. 70 км/г"; i--;}
        if (Dig17.isSelected()) {tickLabels[i] = " Доп.шв. 80 км/г"; i--;}
        if (Dig18.isSelected()) {tickLabels[i] = "   Стоян. гальмо"; i--;}
        if (Dig19.isSelected()) {tickLabels[i] = "Ввімк.бл.перетв."; i--;}
        if (Dig20.isSelected()) {tickLabels[i] = "             КРП"; i--;}

        if (Dig21.isSelected()) {tickLabels[i] = "            1 пп"; i--;}
        if (Dig22.isSelected()) {tickLabels[i] = "            2 пп"; i--;}
        if (Dig23.isSelected()) {tickLabels[i] = "            3 пп"; i--;}
        if (Dig24.isSelected()) {tickLabels[i] = "            4 пп"; i--;}
        if (Dig25.isSelected()) {tickLabels[i] = "            5 пп"; i--;}
        if (Dig26.isSelected()) {tickLabels[i] = "            6 пп"; i--;}
        if (Dig27.isSelected()) {tickLabels[i] = "       Провід Ф7"; i--;}
        if (Dig28.isSelected()) {tickLabels[i] = "            8 пп"; i--;}
        if (Dig29.isSelected()) {tickLabels[i] = "           18 пп"; i--;}
        if (Dig30.isSelected()) {tickLabels[i] = "           20 пп"; i--;}

        if (Dig31.isSelected()) {tickLabels[i] = "           25 пп"; i--;}
        if (Dig32.isSelected()) {tickLabels[i] = "           34 пп"; i--;}
        if (Dig33.isSelected()) {tickLabels[i] = "           48 пп"; i--;}
        if (Dig34.isSelected()) {tickLabels[i] = "             АРШ"; i--;}
        if (Dig35.isSelected()) {tickLabels[i] = "         ДАУ-АРШ"; i--;}
        if (Dig36.isSelected()) {tickLabels[i] = "              ВУ"; i--;}
        if (Dig37.isSelected()) {tickLabels[i] = "  Вибір напрямку"; i--;}
        tickLabels[0]                          = String.format("%0$-31s","");




        return tickLabels;
    }


    void showGraphs() {
        ////////////////////////////////////////////////////////////////////////////////////////////////////
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date st = new Date(textFieldStart.getText());
//        Date fin = new Date(textFieldEnd.getText());
//        String ts1String = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//        String ts2String = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
        final String spinnerStartStr = String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
        final String spinnerEndStr = String.valueOf(new Time(((Date)spinnerEnd.getValue()).getTime()));

        String ts1String = comboDate.getSelectedItem() + " " + spinnerStartStr;
        String ts2String = comboDate.getSelectedItem() + " " + spinnerEndStr;

        System.out.println("Ts1 = " + ts1String);
        System.out.println("Ts2 = " + ts2String);


        java.sql.Timestamp ts1 = Timestamp.valueOf(ts1String);
        java.sql.Timestamp ts2 = Timestamp.valueOf(ts2String);

        int difference = (int)(ts2.getTime() - ts1.getTime());
//        if (difference <= 3600000) decimation = 0;
//        else if (difference > 3600000 && difference < 5 * 3600000) decimation = 10;
//        else decimation = 20;
        decimation = 0;
        graphDig = null;
        System.gc();
        graphsSlider.setValue(graphsSlider.getMinimum());
        int width = scrollGraphs.getWidth() - 110;

        graphsSlider.setSize(new Dimension(width - 60, 27));
        graphsSlider.setPreferredSize(new Dimension(width - 60, 27));

//////////////////////////////////////////////////////////////////////////////////////////////////////
        flagAxis = false;
        flagSlider = false;
        flagAxisTen = false;
        graphDig = new JPanel();

        graphsNum = 0;



        if (Speed.isSelected()) {
//            chartSpeed = null;
            graphsNum++;
            final JDBCXYDataset jdsDig1 = createDatasetSpeed(spinnerStartStr, spinnerEndStr);
            System.gc();

            if (jdsDig1.getItemCount() < 3) {
                jdsDig1.close();
                System.out.println("Can't show the speed graph");
            } else {


                final JDBCXYDataset jdsDigZero = createDatasetSpeed0(spinnerStartStr, spinnerEndStr);
                System.gc();
                final JDBCXYDataset jdsDigForty = createDatasetSpeed40(spinnerStartStr, spinnerEndStr);
                System.gc();
                final JDBCXYDataset jdsDigSixty = createDatasetSpeed60(spinnerStartStr, spinnerEndStr);
                System.gc();
                final JDBCXYDataset jdsDigSeventy = createDatasetSpeed70(spinnerStartStr, spinnerEndStr);
                System.gc();
                final JDBCXYDataset jdsDigEighty = createDatasetSpeed80(spinnerStartStr, spinnerEndStr);
                System.gc();

                chartSpeed = newAnalogGraph("  (Швидкість)", jdsDig1, 0, spinnerStartStr, spinnerEndStr);

                chSpeed = chartSpeed.getChart();
                chartSpeed.setAlignmentX((float) 0.0);

                JFreeChart chart7 = chartSpeed.getChart();
                XYPlot localXYPlot = (XYPlot) chart7.getPlot();

//                TickUnits units = new TickUnits();
//                units.add(new NumberTickUnit(40));
//                units.add(new NumberTickUnit(60));
//                units.add(new NumberTickUnit(70));
//                units.add(new NumberTickUnit(80));
//                localXYPlot.getRangeAxis().setStandardTickUnits(units);



//                ValueAxis yAxis = new SymbolAxis("", new String[]   {
//                        "", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "40", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "60", "", "", "", "", "", "", "", "", "",
//                        "70", "", "", "", "", "", "", "", "", "",
//                        "80", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", ""});
//                yAxis.setAutoRange(false);
//                localXYPlot.setRangeAxis(yAxis);
                //localXYPlot.getRangeAxis().setAutoRange(false);



                localXYPlot.setDataset(1, jdsDigZero);
                localXYPlot.setDataset(2, jdsDigForty);
                localXYPlot.setDataset(3, jdsDigSixty);
                localXYPlot.setDataset(4, jdsDigSeventy);
                localXYPlot.setDataset(5, jdsDigEighty);


//            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
////            XYItemRenderer renderer = localXYPlot.getRenderer();
//  //          XYRenderer
//
////                    renderer.setSeriesLinesVisible(1, true);
////                    renderer.setSeriesLinesVisible(2, true);
////                    renderer.setSeriesLinesVisible(3, true);
////                    renderer.setSeriesLinesVisible(4, true);
////                    renderer.setSeriesLinesVisible(5, true);
////            renderer.setSeriesOutlinePaint(1, Color.GREEN);
////            renderer.setSeriesOutlinePaint(2, Color.GREEN);
////            renderer.setSeriesOutlinePaint(3, Color.GREEN);
////            renderer.setSeriesOutlinePaint(4, Color.GREEN);
////            renderer.setSeriesOutlinePaint(5, Color.GREEN);
//            renderer.setSeriesPaint(0, Color.RED);
//            renderer.setSeriesStroke(0, new BasicStroke(2));
//            renderer.setSeriesPaint(1, Color.RED);
//            renderer.setSeriesPaint(2, Color.RED);
//            renderer.setSeriesPaint(3, Color.RED);
//            renderer.setSeriesPaint(4, Color.RED);
//            renderer.setSeriesPaint(5, Color.RED);
//            renderer.setSeriesStroke(1, new BasicStroke(2));
//            renderer.setSeriesStroke(2, new BasicStroke(2));
//            renderer.setSeriesStroke(3, new BasicStroke(2));
//            renderer.setSeriesStroke(4, new BasicStroke(2));
//            renderer.setSeriesStroke(5, new BasicStroke(2));
////            renderer.setSeriesShape(1, );
//
//
//            renderer.setBasePaint(Color.GREEN);
//
////                    localXYPlot.setDataset(1, createDatasetDigitsThree("(Циф14)", 1));
//
                XYItemRenderer ren1 = localXYPlot.getRenderer();
                XYItemRenderer ren2 = localXYPlot.getRenderer();

                ren1.setSeriesPaint(0, Color.GREEN);
//
////            ren1.setSeriesStroke(1, new BasicStroke(1));
////            ren1.setSeriesPaint(0, Color.GREEN);
////            ren2.setSeriesPaint(1, Color.GREEN);
////            ren2.setSeriesPaint(2, Color.GREEN);
////            ren2.setSeriesPaint(3, Color.GREEN);
//
//
////            ren1.setSeriesPaint(3, Color.GREEN);
////            ren1.setSeriesPaint(4, Color.GREEN);
////            ren1.setSeriesPaint(5, Color.GREEN);
////
////            ren1.setSeriesStroke(0, new BasicStroke(3));
//
//
////            localXYPlot.setRenderer(0, renderer);
//            localXYPlot.setRenderer(1, renderer);
//            localXYPlot.setRenderer(2, renderer);
//            localXYPlot.setRenderer(3, renderer);
//            localXYPlot.setRenderer(4, renderer);
//            localXYPlot.setRenderer(5, renderer);


                System.out.println("Speed datasets: " + localXYPlot.getDatasetCount());
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);

                //localXYPlot.setRenderer(new SamplingXYLineRenderer());

                SamplingXYLineRenderer lineRenderer = new SamplingXYLineRenderer();

                lineRenderer.setSeriesPaint(0, Color.RED);
                lineRenderer.setSeriesStroke(0, new BasicStroke(2));
                lineRenderer.setSeriesPaint(1, Color.RED);
                lineRenderer.setSeriesPaint(2, Color.RED);
                lineRenderer.setSeriesPaint(3, Color.RED);
                lineRenderer.setSeriesPaint(4, Color.RED);
                lineRenderer.setSeriesPaint(5, Color.RED);
                lineRenderer.setSeriesStroke(1, new BasicStroke(2));
                lineRenderer.setSeriesStroke(2, new BasicStroke(2));
                lineRenderer.setSeriesStroke(3, new BasicStroke(2));
                lineRenderer.setSeriesStroke(4, new BasicStroke(2));
                lineRenderer.setSeriesStroke(5, new BasicStroke(2));

                localXYPlot.setRenderer(1, lineRenderer);
                localXYPlot.setRenderer(2, lineRenderer);
                localXYPlot.setRenderer(3, lineRenderer);
                localXYPlot.setRenderer(4, lineRenderer);
                localXYPlot.setRenderer(5, lineRenderer);

                localXYPlot.setInsets(new RectangleInsets(0.0, 20.0, 0.0, 0.0));


                localXYPlot.setDomainCrosshairVisible(true);
                localXYPlot.setDomainCrosshairStroke(new BasicStroke(2));
                localXYPlot.setDomainCrosshairPaint(Color.blue);
                localXYPlot.setDomainCrosshairLockedOnData(false);

//                localXYPlot.setDomainCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairValue(50.0);
////                localXYPlot.setDomainCrosshairValue(ts15.getTime() + 10000);
//                localXYPlot.setDomainCrosshairValue(graphsSlider.getValue());


                jdsDig1.close();
                jdsDigZero.close();
                jdsDigForty.close();
                jdsDigSixty.close();
                jdsDigSeventy.close();
                jdsDigEighty.close();


                graphDig.add(chartSpeed);
            }
            System.gc();
        }

        if (Pres1.isSelected() || Pres2.isSelected()) {
 //           chartPress1 = null;
            graphsNum++;
            JDBCXYDataset jdsDig1 = createDatasetPressOne(spinnerStartStr, spinnerEndStr);

            if (jdsDig1.getItemCount() < 3) {
                jdsDig1.close();
                System.out.println("Can't show the pressures ghraphs");
            } else {

                chartPress1 = newAnalogGraph("(Тиск НМ, ГМ)", jdsDig1, 1, spinnerStartStr, spinnerEndStr);
                jdsDig1.close();
                chPres1 = chartPress1.getChart();
                chartPress1.setAlignmentX((float) 0.0);

                JFreeChart chart7 = chartPress1.getChart();
                XYPlot localXYPlot = (XYPlot) chart7.getPlot();
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);
                localXYPlot.setRenderer(new SamplingXYLineRenderer());

//                localXYPlot.setDomainCrosshairVisible(true);

                localXYPlot.setDomainCrosshairVisible(true);
                localXYPlot.setDomainCrosshairStroke(new BasicStroke(2));
                localXYPlot.setDomainCrosshairPaint(Color.blue);

//                localXYPlot.setRangeCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairValue(5.0);
//                localXYPlot.setRangeCrosshairStroke(new BasicStroke(1));
//                localXYPlot.setRangeCrosshairPaint(Color.RED);
//                long value = (long)jdsDig1.getX(0, 0) + 2000;
//                localXYPlot.setDomainCrosshairValue(value);

                //localXYPlot.setAxisOffset(new RectangleInsets(0.0, 10.0, 0.0, 0.0));
               // graphDig.insets();
                localXYPlot.setInsets(new RectangleInsets(0.0, 20.0, 0.0, 0.0));


                graphDig.add(chartPress1);
                graphDig.setEnabled(false);
                chartPress1.setEnabled(false);
            }
            System.gc();
        }

        if (allDigital.isSelected()/* == 1*/ && difference > 1000/*&& allDigital.isSelected() && (Speed.isSelected() || Pres1.isSelected() || Pres2.isSelected())*/) {

            graphsNum++;
            final int digSigsNumber = digSigsCalc();

            String tkLabels[] = makeTickLabels(digSigsNumber);

            System.out.println("Start query all digits...");
            final JDBCXYDataset jdsDig0 = createDatasetDigitalsAll(spinnerStartStr, spinnerEndStr, digSigsNumber);;



//            JDBCXYDataset jdsDig0 = createDatasetDigitalsAll(spinnerStartStr, spinnerEndStr, digSigsNumber);
            System.gc();

            System.out.println("Stop query all digits.");

//            if (jdsDig0 == null) System.out.println("Is null");
            System.out.println(jdsDig0.getX(0, 0));
            System.out.println(jdsDig0.getX(0, jdsDig0.getItemCount() - 1));
//            System.out.println(Timestamp.valueOf(textFieldEnd.getText()).getTime());
            System.out.println("Items in Digits: " + jdsDig0.getItemCount());



            if (jdsDig0.getItemCount() < 3) {
                jdsDig0.close();
                System.out.println("Can't show the digits graph");
            } else {

                //////////////


                String name = "Вагон №: " + carNo + "   Дата запису: " + (String)comboDate.getSelectedItem();
//                String name = "";
                chAllDigits2 = ChartFactory.createTimeSeriesChart(name + "", "", "", jdsDig0);
                chartAllDigits2 = new ChartPanel(chAllDigits2, true);

                chAllDigits2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 17));

                chartAllDigits2.setLayout(new GridLayout());

                chAllDigits2.removeLegend();
                chAllDigits2.getTitle().setPosition(RectangleEdge.LEFT);
                chartAllDigits2.setMouseZoomable(false);


                chartAllDigits2.setAlignmentX((float) 0.0);




                int heightDigits = (digSigsNumber + 1) * 17 + 20;

                if (digSigsNumber < 22) //heightDigits = 22 * 17 + 20;
                {
                    chAllDigits2.setTitle(" \n " + name);
                    chAllDigits2.getTitle().setPosition(RectangleEdge.BOTTOM);
                    tkLabels[0] = String.format("%0$-39s","");
                    heightDigits = (digSigsNumber + 1) * 17 + 75;
                }

//                if (digSigsNumber < 22) //heightDigits = 22 * 17 + 20;
//                {
//                    chAllDigits2.getTitle().setPosition(RectangleEdge.BOTTOM);
//
//                }

                chartAllDigits2.setPreferredSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMaximumSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMinimumSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMaximumDrawWidth(width + 135 - 47);

                chartAllDigits2.setMinimumDrawWidth(100);
                chartAllDigits2.setMaximumDrawHeight(heightDigits);
                chartAllDigits2.setMinimumDrawHeight(10);

                XYPlot xyPlot = (XYPlot) chAllDigits2.getPlot();


                xyPlot.setDomainCrosshairVisible(true);
                xyPlot.setDomainCrosshairStroke(new BasicStroke(2));
                xyPlot.setDomainCrosshairPaint(Color.blue);

                // xyPlot.setInsets(new RectangleInsets(0.0, 50.0, 0.0, 0.0));
 //               xyPlot.getRangeAxis().setLabelInsets(new RectangleInsets(0.0, 50.0, 0.0, 0.0));
 //               xyPlot.getRangeAxis().setTickLabelInsets(new RectangleInsets(0.0, 250.0, 0.0, 0.0));

                DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                xyPlot.getDomainAxis().setTickLabelFont(new Font("TimesNewRoman", Font.PLAIN, 6));
//                xyPlot.getRangeAxis().setTickLabelFont(new Font("TimesNewRoman", Font.PLAIN, 6));
//                NumberAxis numberAxis = (NumberAxis) xyPlot.getRangeAxis();
//                SymbolAxis symbolAxis = (SymbolAxis) xyPlot.getRangeAxis();
//                symbolAxis.setRange(0.0, 3800.0);
//                TickUnits units = new TickUnits();
//                String tk[] = (String[]) columnsJointTable;




//                ValueAxis yAxis = new SymbolAxis("", new String[]
//                        {"",
//                                 tk[40], tk[39], tk[38], tk[37], tk[36], tk[35], tk[34], tk[33], tk[32],
//                                tk[31], tk[30], tk[29], tk[28], tk[27], tk[26], tk[25], tk[24], tk[23], tk[22],
//                                tk[21], tk[20], tk[19], tk[18], tk[17], tk[16], tk[15], tk[14], tk[13], tk[12],
//                                tk[11], tk[10], tk[9], tk[8], tk[7], tk[6], tk[5], tk[4]
//                        });


                ValueAxis yAxis = new SymbolAxis("", tkLabels);

                xyPlot.setRangeAxis(yAxis);
                yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 11));
//                yAxis.setTickMarkOutsideLength((float) 210.0);
//                yAxis.setLabelInsets(new RectangleInsets(0.0, 10.0, 0.0, 0.0), true);
                yAxis.setAutoRange(false);
//                numberAxis.setRange(0.0, 380.0);
//                numberAxis.setTickUnit(new NumberTickUnit(10.0));


//                numberAxis.setTickUnit(new CategoryTick());

//        dateAxis.setRange(dataSet.getYValue(0, 0), dataSet.getYValue(0, 0) + timeSpan * 1000);
//                dateAxis.setRange(ts17.getTime(), ts17.getTime() + timeSpan * 1000);

                String strDateEnd = textFieldEnd.getText();

//                String tsString = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
//                java.sql.Timestamp ts = Timestamp.valueOf(tsString);

//                String tsEndString = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
//                String tsEndString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime())));
                String tsEndString = comboDate.getSelectedItem() + " " + spinnerEndStr;
                java.sql.Timestamp tsEnd = Timestamp.valueOf(tsEndString);

//                String tsStartString = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//                String tsStartString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
                String tsStartString = comboDate.getSelectedItem() + " " + spinnerStartStr;
                java.sql.Timestamp tsStart = Timestamp.valueOf(tsStartString);



//                java.sql.Timestamp ts = Timestamp.valueOf(strDateEnd);

                dateAxis.setPositiveArrowVisible(true);
                dateAxis.setRange(tsStart.getTime(), tsEnd.getTime());
//                dateAxis.setRange(jdsDig0.getXValue(0, 0), jdsDig0.getXValue(0, jdsDig0.getItemCount() - 1));

                ////////////

//sfsd
//            chartSpeed = newAnalogGraph("  (Швидкість)", jdsDig1, 0);

//                    ChartPanel chartSpeed = newAnalogGraph("(Швидкість)", jdsSpeed);
//            chAllDigits2 = chartAllDigits2.getChart();
//                    chartSpeed.createChartPrintJob();

//                    chartSpeed.setRangeZoomable(false);
//                    chartSpeed.setMouseZoomable(false);
//                    chartSpeed.zoomOutRange(5, 10);
//                    chartSpeed.restoreAutoDomainBounds();
                //chartSpeed.restoreAutoRangeBounds();

//                    chartSpeed.restoreAutoBounds();

//            chartAllDigits2.setAlignmentX((float) 0.0);


                final Marker km10 = new ValueMarker(1.0);
                km10.setLabel("Вибір напрямку");
                km10.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km20 = new ValueMarker(2.0);
                km20.setLabel("ВУ");
                km20.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km30 = new ValueMarker(3.0);
                km30.setLabel("ДАУ-АРШ");
                km30.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km40 = new ValueMarker(4.0);
                km40.setLabel("АРШ");
                km40.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km50 = new ValueMarker(5.0);
                km50.setLabel("48 пп");
                km50.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km60 = new ValueMarker(6.0);
                km60.setLabel("34 пп");
                km60.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km70 = new ValueMarker(7.0);
                km70.setLabel("25 пп");
                km70.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km80 = new ValueMarker(8.0);
                km80.setLabel("20 пп");
                km80.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km90 = new ValueMarker(9.0);
                km90.setLabel("18 пп");
                km90.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km100 = new ValueMarker(10.0);
                km100.setLabel("8 пп");
                km100.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km110 = new ValueMarker(11.0);
                km110.setLabel("Провід Ф7");
                km110.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km120 = new ValueMarker(12.0);
                km120.setLabel("6 пп");
                km120.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km130 = new ValueMarker(13.0);
                km130.setLabel("5 пп");
                km130.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km140 = new ValueMarker(14.0);
                km140.setLabel("4 пп");
                km140.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km150 = new ValueMarker(15.0);
                km150.setLabel("3 пп");
                km150.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km160 = new ValueMarker(16.0);
                km160.setLabel("2 пп");
                km160.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km170 = new ValueMarker(17.0);
                km170.setLabel("1 пп");
                km170.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km180 = new ValueMarker(18.0);
                km180.setLabel("КРП");
                km180.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km190 = new ValueMarker(19.0);
                km190.setLabel("Ввімк.бл.перетв");
                km190.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km200 = new ValueMarker(20.0);
                km200.setLabel("Стоян. гальмо");
                km200.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km210 = new ValueMarker(21.0);
                km210.setLabel("Доп.шв. 80 км/г");
                km210.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km220 = new ValueMarker(22.0);
                km220.setLabel("Доп.шв. 70 км/г");
                km220.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km230 = new ValueMarker(23.0);
                km230.setLabel("Доп.шв. 60 км/г");
                km230.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km240 = new ValueMarker(24.0);
                km240.setLabel("Доп.шв. 40 км/г");
                km240.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km250 = new ValueMarker(25.0);
                km250.setLabel("Доп.шв.0 км/г");
                km250.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km260 = new ValueMarker(26.0);
                km260.setLabel("Відсутня част.");
                km260.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km270 = new ValueMarker(27.0);
                km270.setLabel("КБ(ПБ)");
                km270.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km280 = new ValueMarker(28.0);
                km280.setLabel("Зачин. дверей");
                km280.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km290 = new ValueMarker(29.0);
                km290.setLabel("Ввімк. ОВТ");
                km290.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km300 = new ValueMarker(30.0);
                km300.setLabel("Ввімк. ВАД");
                km300.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km310 = new ValueMarker(31.0);
                km310.setLabel("Ввімк. ВАХ");
                km310.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km320 = new ValueMarker(32.0);
                km320.setLabel("Рух від КАХ");
                km320.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km330 = new ValueMarker(33.0);
                km330.setLabel("РП відновл.");
                km330.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km340 = new ValueMarker(34.0);
                km340.setLabel("ЕПК ввімк.");
                km340.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km350 = new ValueMarker(35.0);
                km350.setLabel("Радіост. ввім.");
                km350.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km360 = new ValueMarker(36.0);
                km360.setLabel("АЛС ввім.");
                km360.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km370 = new ValueMarker(37.0);
                km370.setLabel("АРШ ввім");
                km370.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                JFreeChart chart7 = chartAllDigits2.getChart();
                XYPlot localXYPlot = (XYPlot) chart7.getPlot();

//                localXYPlot.addRangeMarker(km10);
//                localXYPlot.addRangeMarker(km20);
//                localXYPlot.addRangeMarker(km30);
//                localXYPlot.addRangeMarker(km40);
//                localXYPlot.addRangeMarker(km50);
//                localXYPlot.addRangeMarker(km60);
//                localXYPlot.addRangeMarker(km70);
//                localXYPlot.addRangeMarker(km80);
//                localXYPlot.addRangeMarker(km90);
//                localXYPlot.addRangeMarker(km100);
//                localXYPlot.addRangeMarker(km110);
//                localXYPlot.addRangeMarker(km120);
//                localXYPlot.addRangeMarker(km130);
//                localXYPlot.addRangeMarker(km140);
//                localXYPlot.addRangeMarker(km150);
//                localXYPlot.addRangeMarker(km160);
//                localXYPlot.addRangeMarker(km170);
//                localXYPlot.addRangeMarker(km180);
//                localXYPlot.addRangeMarker(km190);
//                localXYPlot.addRangeMarker(km200);
//                localXYPlot.addRangeMarker(km210);
//                localXYPlot.addRangeMarker(km220);
//                localXYPlot.addRangeMarker(km230);
//                localXYPlot.addRangeMarker(km240);
//                localXYPlot.addRangeMarker(km250);
//                localXYPlot.addRangeMarker(km260);
//                localXYPlot.addRangeMarker(km270);
//                localXYPlot.addRangeMarker(km280);
//                localXYPlot.addRangeMarker(km290);
//                localXYPlot.addRangeMarker(km300);
//                localXYPlot.addRangeMarker(km310);
//                localXYPlot.addRangeMarker(km320);
//                localXYPlot.addRangeMarker(km330);
//                localXYPlot.addRangeMarker(km340);
//                localXYPlot.addRangeMarker(km350);
//                localXYPlot.addRangeMarker(km360);
//                localXYPlot.addRangeMarker(km370);

//                localXYPlot.getRangeAxis().setVisible(false);

                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

                System.out.println("Speed datasets: " + localXYPlot.getDatasetCount());
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);

                localXYPlot.setRenderer(new SamplingXYLineRenderer());

                XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer();
                XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
                XYItemRenderer ren0 = localXYPlot.getRenderer();

//            SamplingXYLineRenderer ren0 = new SamplingXYLineRenderer();

                ren0.setSeriesPaint(0, new Color(10, 10, 150));
                ren0.setSeriesPaint(1, new Color(10, 10, 125));
                ren0.setSeriesPaint(2, new Color(10, 10, 100));
                ren0.setSeriesPaint(3, new Color(10, 10, 75));
                ren0.setSeriesPaint(4, new Color(10, 50, 75));
                ren0.setSeriesPaint(5, new Color(10, 75, 75));
                ren0.setSeriesPaint(6, new Color(10, 100, 75));
                ren0.setSeriesPaint(7, new Color(10, 125, 75));
                ren0.setSeriesPaint(8, new Color(10, 150, 75));
                ren0.setSeriesPaint(9, new Color(10, 175, 75));
                ren0.setSeriesPaint(10, new Color(10, 200, 75));
                ren0.setSeriesPaint(11, new Color(10, 225, 75));
                ren0.setSeriesPaint(12, new Color(10, 250, 75));
                ren0.setSeriesPaint(13, new Color(10, 250, 75));
                ren0.setSeriesPaint(14, new Color(100, 250, 75));
//            ren0.setSeriesPaint(15, Color.black);
//            ren0.setSeriesPaint(16, Color.black);
                ren0.setSeriesPaint(17, Color.GREEN);
//            ren0.setSeriesPaint(18, Color.black);
//            ren0.setSeriesPaint(19, Color.black);
                ren0.setSeriesPaint(20, Color.DARK_GRAY);
                ren0.setSeriesPaint(21, Color.BLUE);
//            ren0.setSeriesPaint(22, Color.black);
//            ren0.setSeriesPaint(23, Color.black);
                ren0.setSeriesPaint(24, Color.BLUE);
//            ren0.setSeriesPaint(25, Color.black);
                ren0.setSeriesPaint(26, Color.ORANGE);
//            ren0.setSeriesPaint(27, Color.black);
                ren0.setSeriesPaint(28, Color.RED);
//            ren0.setSeriesPaint(29, Color.black);
//            ren0.setSeriesPaint(30, Color.black);
//            ren0.setSeriesPaint(31, Color.GRAY);
//            ren0.setSeriesPaint(32, Color.CYAN);
//            ren0.setSeriesPaint(33, Color.YELLOW);
//            ren0.setSeriesPaint(34, Color.ORANGE);
//            ren0.setSeriesPaint(35, Color.green);
//            ren0.setSeriesPaint(36, Color.blue);

//            ren0.setSeriesPaint(37, Color.white);
                ren0.setSeriesPaint(digSigsNumber, Color.white);
//
//            ren0.setSeriesPaint(38, Color.white);
//                ren0.setSeriesPaint(39, Color.white);
                int stroke = 5;

                ren0.setSeriesStroke(0, new BasicStroke(stroke));
                ren0.setSeriesStroke(1, new BasicStroke(stroke));
                ren0.setSeriesStroke(2, new BasicStroke(stroke));
                ren0.setSeriesStroke(3, new BasicStroke(stroke));
                ren0.setSeriesStroke(4, new BasicStroke(stroke));
                ren0.setSeriesStroke(5, new BasicStroke(stroke));
                ren0.setSeriesStroke(6, new BasicStroke(stroke));
                ren0.setSeriesStroke(7, new BasicStroke(stroke));
                ren0.setSeriesStroke(8, new BasicStroke(stroke));
                ren0.setSeriesStroke(9, new BasicStroke(stroke));
                ren0.setSeriesStroke(10, new BasicStroke(stroke));
                ren0.setSeriesStroke(11, new BasicStroke(stroke));
                ren0.setSeriesStroke(12, new BasicStroke(stroke));
                ren0.setSeriesStroke(13, new BasicStroke(stroke));
                ren0.setSeriesStroke(14, new BasicStroke(stroke));
                ren0.setSeriesStroke(15, new BasicStroke(stroke));
                ren0.setSeriesStroke(16, new BasicStroke(stroke));
                ren0.setSeriesStroke(17, new BasicStroke(stroke));
                ren0.setSeriesStroke(18, new BasicStroke(stroke));
                ren0.setSeriesStroke(19, new BasicStroke(stroke));
                ren0.setSeriesStroke(20, new BasicStroke(stroke));
                ren0.setSeriesStroke(21, new BasicStroke(stroke));
                ren0.setSeriesStroke(22, new BasicStroke(stroke));
                ren0.setSeriesStroke(23, new BasicStroke(stroke));
                ren0.setSeriesStroke(24, new BasicStroke(stroke));
                ren0.setSeriesStroke(25, new BasicStroke(stroke));
                ren0.setSeriesStroke(26, new BasicStroke(stroke));
                ren0.setSeriesStroke(27, new BasicStroke(stroke));
                ren0.setSeriesStroke(28, new BasicStroke(stroke));
                ren0.setSeriesStroke(29, new BasicStroke(stroke));
                ren0.setSeriesStroke(30, new BasicStroke(stroke));
                ren0.setSeriesStroke(31, new BasicStroke(stroke));
                ren0.setSeriesStroke(32, new BasicStroke(stroke));
                ren0.setSeriesStroke(33, new BasicStroke(stroke));
                ren0.setSeriesStroke(34, new BasicStroke(stroke));
                ren0.setSeriesStroke(35, new BasicStroke(stroke));
                ren0.setSeriesStroke(36, new BasicStroke(stroke));
                ren0.setSeriesStroke(37, new BasicStroke(1));

//            localXYPlot.setRenderer(new SamplingXYLineRenderer());

                jdsDig0.close();

                graphDig.add(chartAllDigits2);
            }
        }

        graphDig.setLayout(new BoxLayout(graphDig, BoxLayout.Y_AXIS));
        graphDig.setAlignmentX(Component.LEFT_ALIGNMENT);
        graphDig.setAlignmentY(Component.LEFT_ALIGNMENT);
        graphDig.setBackground(Color.white);

        if (!Speed.isSelected()) chartSpeed.setVisible(false);
        if (!Pres1.isSelected() && !Pres2.isSelected()) chartPress1.setVisible(false);
        if (!Dig1.isSelected() && !Dig2.isSelected() && !Dig3.isSelected() && !Dig4.isSelected() && !Dig5.isSelected() &&
            !Dig6.isSelected() && !Dig7.isSelected() && !Dig8.isSelected() && !Dig9.isSelected() && !Dig10.isSelected() &&

                !Dig11.isSelected() && !Dig12.isSelected() && !Dig13.isSelected() && !Dig14.isSelected() && !Dig15.isSelected() &&
                !Dig16.isSelected() && !Dig17.isSelected() && !Dig18.isSelected() && !Dig19.isSelected() && !Dig20.isSelected() &&

                !Dig21.isSelected() && !Dig22.isSelected() && !Dig23.isSelected() && !Dig24.isSelected() && !Dig25.isSelected() &&
                !Dig26.isSelected() && !Dig27.isSelected() && !Dig28.isSelected() && !Dig29.isSelected() && !Dig30.isSelected() &&

                !Dig31.isSelected() && !Dig32.isSelected() && !Dig33.isSelected() && !Dig34.isSelected() && !Dig35.isSelected() &&
                !Dig36.isSelected() && !Dig37.isSelected()
                ) chartAllDigits2.setVisible(false);

        scrollGraphs.add(graphDig);
        scrollGraphs.setAlignmentY(Component.LEFT_ALIGNMENT);
        scrollGraphs.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollGraphs.setViewportView(graphDig);

        System.gc();

    }

    class ThisMouseListener implements ChartMouseListener {

        @Override
        public void chartMouseMoved(ChartMouseEvent event) {
        }

        @Override
        public void chartMouseClicked(ChartMouseEvent event) {
            return;
//            ChartEntity entity = event.getEntity();
//            if (entity != null && (entity instanceof XYItemEntity)) {
//                XYItemEntity item = (XYItemEntity) entity;
//                renderer.select(item.getSeriesIndex(), item.getItem());
//                return;
//            }
//            // deselect
//            renderer.select(-1, -1);
        }
    }

    void showGraphs(Connection conn) {
        ////////////////////////////////////////////////////////////////////////////////////////////////////
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date st = new Date(textFieldStart.getText());
//        Date fin = new Date(textFieldEnd.getText());
//        String ts1String = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//        String ts2String = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
        final String spinnerStartStr = String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
        final String spinnerEndStr = String.valueOf(new Time(((Date)spinnerEnd.getValue()).getTime()));

        String ts1String = comboDate.getSelectedItem() + " " + spinnerStartStr;
        String ts2String = comboDate.getSelectedItem() + " " + spinnerEndStr;

        System.out.println("Ts1 = " + ts1String);
        System.out.println("Ts2 = " + ts2String);


        java.sql.Timestamp ts1 = Timestamp.valueOf(ts1String);
        java.sql.Timestamp ts2 = Timestamp.valueOf(ts2String);

        int difference = (int)(ts2.getTime() - ts1.getTime());
        if (difference <= 3600000) decimation = 0;
//        else if (difference > 3600000 && difference < 5 * 3600000) decimation = 10;
//        else decimation = 1000000;
        else decimation = 20;
//        decimation = 0;
        graphDig = null;
        System.gc();
        graphsSlider.setValue(graphsSlider.getMinimum());
        int width = scrollGraphs.getWidth() - 110;

        graphsSlider.setSize(new Dimension(width - 44, 27));
        graphsSlider.setPreferredSize(new Dimension(width - 44, 27));

//////////////////////////////////////////////////////////////////////////////////////////////////////
        flagAxis = false;
        flagSlider = false;
        flagAxisTen = false;
        graphDig = new JPanel();

        graphsNum = 0;



        if (Speed.isSelected()) {
//            chartSpeed = null;
            graphsNum++;
            final JDBCXYDataset jdsDig1 = createDatasetSpeed(spinnerStartStr, spinnerEndStr, conn);
            System.gc();

            if (jdsDig1.getItemCount() < 3) {
                jdsDig1.close();
                System.out.println("Can't show the speed graph");
            } else {


                final JDBCXYDataset jdsDigZero = createDatasetSpeed0(spinnerStartStr, spinnerEndStr, conn);
                System.gc();
                final JDBCXYDataset jdsDigForty = createDatasetSpeed40(spinnerStartStr, spinnerEndStr, conn);
                System.gc();
                final JDBCXYDataset jdsDigSixty = createDatasetSpeed60(spinnerStartStr, spinnerEndStr, conn);
                System.gc();
                final JDBCXYDataset jdsDigSeventy = createDatasetSpeed70(spinnerStartStr, spinnerEndStr, conn);
                System.gc();
                final JDBCXYDataset jdsDigEighty = createDatasetSpeed80(spinnerStartStr, spinnerEndStr, conn);
                System.gc();

                chartSpeed = newAnalogGraph("  (Швидкість)", jdsDig1, 0, spinnerStartStr, spinnerEndStr);
 //               chartSpeed.addChartMouseListener(new ThisMouseListener());

                chSpeed = chartSpeed.getChart();
                chartSpeed.setAlignmentX((float) 0.0);

                JFreeChart chart7 = chartSpeed.getChart();
                final XYPlot localXYPlot = (XYPlot) chart7.getPlot();


//                TickUnits units = new TickUnits();
//                units.add(new NumberTickUnit(40));
//                units.add(new NumberTickUnit(60));
//                units.add(new NumberTickUnit(70));
//                units.add(new NumberTickUnit(80));
//                localXYPlot.getRangeAxis().setStandardTickUnits(units);



//                ValueAxis yAxis = new SymbolAxis("", new String[]   {
//                        "", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "40", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", "",
//                        "60", "", "", "", "", "", "", "", "", "",
//                        "70", "", "", "", "", "", "", "", "", "",
//                        "80", "", "", "", "", "", "", "", "", "",
//                        "", "", "", "", "", "", "", "", "", ""});
//                yAxis.setAutoRange(false);
//                localXYPlot.setRangeAxis(yAxis);
                //localXYPlot.getRangeAxis().setAutoRange(false);



                localXYPlot.setDataset(1, jdsDigZero);
                localXYPlot.setDataset(2, jdsDigForty);
                localXYPlot.setDataset(3, jdsDigSixty);
                localXYPlot.setDataset(4, jdsDigSeventy);
                localXYPlot.setDataset(5, jdsDigEighty);


//            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
////            XYItemRenderer renderer = localXYPlot.getRenderer();
//  //          XYRenderer
//
////                    renderer.setSeriesLinesVisible(1, true);
////                    renderer.setSeriesLinesVisible(2, true);
////                    renderer.setSeriesLinesVisible(3, true);
////                    renderer.setSeriesLinesVisible(4, true);
////                    renderer.setSeriesLinesVisible(5, true);
////            renderer.setSeriesOutlinePaint(1, Color.GREEN);
////            renderer.setSeriesOutlinePaint(2, Color.GREEN);
////            renderer.setSeriesOutlinePaint(3, Color.GREEN);
////            renderer.setSeriesOutlinePaint(4, Color.GREEN);
////            renderer.setSeriesOutlinePaint(5, Color.GREEN);
//            renderer.setSeriesPaint(0, Color.RED);
//            renderer.setSeriesStroke(0, new BasicStroke(2));
//            renderer.setSeriesPaint(1, Color.RED);
//            renderer.setSeriesPaint(2, Color.RED);
//            renderer.setSeriesPaint(3, Color.RED);
//            renderer.setSeriesPaint(4, Color.RED);
//            renderer.setSeriesPaint(5, Color.RED);
//            renderer.setSeriesStroke(1, new BasicStroke(2));
//            renderer.setSeriesStroke(2, new BasicStroke(2));
//            renderer.setSeriesStroke(3, new BasicStroke(2));
//            renderer.setSeriesStroke(4, new BasicStroke(2));
//            renderer.setSeriesStroke(5, new BasicStroke(2));
////            renderer.setSeriesShape(1, );
//
//
//            renderer.setBasePaint(Color.GREEN);
//
////                    localXYPlot.setDataset(1, createDatasetDigitsThree("(Циф14)", 1));
//
                XYItemRenderer ren1 = localXYPlot.getRenderer();
                XYItemRenderer ren2 = localXYPlot.getRenderer();

                ren1.setSeriesPaint(0, Color.GREEN);
//
////            ren1.setSeriesStroke(1, new BasicStroke(1));
////            ren1.setSeriesPaint(0, Color.GREEN);
////            ren2.setSeriesPaint(1, Color.GREEN);
////            ren2.setSeriesPaint(2, Color.GREEN);
////            ren2.setSeriesPaint(3, Color.GREEN);
//
//
////            ren1.setSeriesPaint(3, Color.GREEN);
////            ren1.setSeriesPaint(4, Color.GREEN);
////            ren1.setSeriesPaint(5, Color.GREEN);
////
////            ren1.setSeriesStroke(0, new BasicStroke(3));
//
//
////            localXYPlot.setRenderer(0, renderer);
//            localXYPlot.setRenderer(1, renderer);
//            localXYPlot.setRenderer(2, renderer);
//            localXYPlot.setRenderer(3, renderer);
//            localXYPlot.setRenderer(4, renderer);
//            localXYPlot.setRenderer(5, renderer);


                System.out.println("Speed datasets: " + localXYPlot.getDatasetCount());
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);

                //localXYPlot.setRenderer(new SamplingXYLineRenderer());

                SamplingXYLineRenderer lineRenderer = new SamplingXYLineRenderer();

                lineRenderer.setSeriesPaint(0, Color.RED);
                lineRenderer.setSeriesStroke(0, new BasicStroke(2));
                lineRenderer.setSeriesPaint(1, Color.RED);
                lineRenderer.setSeriesPaint(2, Color.RED);
                lineRenderer.setSeriesPaint(3, Color.RED);
                lineRenderer.setSeriesPaint(4, Color.RED);
                lineRenderer.setSeriesPaint(5, Color.RED);
                lineRenderer.setSeriesStroke(1, new BasicStroke(2));
                lineRenderer.setSeriesStroke(2, new BasicStroke(2));
                lineRenderer.setSeriesStroke(3, new BasicStroke(2));
                lineRenderer.setSeriesStroke(4, new BasicStroke(2));
                lineRenderer.setSeriesStroke(5, new BasicStroke(2));

                localXYPlot.setRenderer(1, lineRenderer);
                localXYPlot.setRenderer(2, lineRenderer);
                localXYPlot.setRenderer(3, lineRenderer);
                localXYPlot.setRenderer(4, lineRenderer);
                localXYPlot.setRenderer(5, lineRenderer);



                //localXYPlot.setDomainCrosshairVisible();

//                localXYPlot.setDomainCrosshairVisible(true);
//                localXYPlot.setDomainCrosshairStroke(new BasicStroke(2));
//                localXYPlot.setDomainCrosshairPaint(Color.blue);

//                localXYPlot.setDomainCrosshairLockedOnData(false);

                localXYPlot.setInsets(new RectangleInsets(0.0, 20.0, 0.0, 0.0));


//                localXYPlot.setDomainCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairValue(50.0);
////                localXYPlot.setDomainCrosshairValue(ts15.getTime() + 10000);
//                localXYPlot.setDomainCrosshairValue(graphsSlider.getValue());

                try {
                    System.out.println("Connection is closed: " + conn.isClosed());
                } catch (SQLException e) {
                    e.printStackTrace();
                }



//                jdsDig1.close();
//                jdsDigZero.close();
//                jdsDigForty.close();
//                jdsDigSixty.close();
//                jdsDigSeventy.close();
//                jdsDigEighty.close();

//                chartSpeed.addChartMouseListener(new ChartMouseListener() {
//
//                    @Override
//                    public void chartMouseClicked(final ChartMouseEvent event){
//                        //System.out.println("chartMouseClicked: " + localXYPlot.getDomainCrosshairValue());
//                        localXYPlot.setDomainCrosshairValue(graphsSlider.getValue() + bigParticle);
//
//                    }
//
//                    @Override
//                    public void chartMouseMoved(final ChartMouseEvent event){
//                    }
//                });

                graphDig.setEnabled(false);
                graphDig.add(chartSpeed);
                chartSpeed.setEnabled(false);

            }
            System.gc();
        }


        if (Pres1.isSelected() || Pres2.isSelected()) {
            //           chartPress1 = null;
            graphsNum++;
            JDBCXYDataset jdsDig1 = createDatasetPressOne(spinnerStartStr, spinnerEndStr, conn);

            if (jdsDig1.getItemCount() < 3) {
 //               jdsDig1.close();
                System.out.println("Can't show the pressures ghraphs");
            } else {

                chartPress1 = newAnalogGraph("(Тиск НМ, ГМ)", jdsDig1, 1, spinnerStartStr, spinnerEndStr);

                chPres1 = chartPress1.getChart();
                chartPress1.setAlignmentX((float) 0.0);

                JFreeChart chart7 = chartPress1.getChart();
                XYPlot localXYPlot = (XYPlot) chart7.getPlot();
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);
                localXYPlot.setRenderer(new SamplingXYLineRenderer());

//                localXYPlot.setDomainCrosshairVisible(true);

                localXYPlot.setDomainCrosshairVisible(true);
                localXYPlot.setDomainCrosshairStroke(new BasicStroke(2));
                localXYPlot.setDomainCrosshairPaint(Color.blue);

//                localXYPlot.setRangeCrosshairVisible(true);
//                localXYPlot.setRangeCrosshairValue(5.0);
//                localXYPlot.setRangeCrosshairStroke(new BasicStroke(1));
//                localXYPlot.setRangeCrosshairPaint(Color.RED);
//                long value = (long)jdsDig1.getX(0, 0) + 2000;
//                localXYPlot.setDomainCrosshairValue(value);

                //localXYPlot.setAxisOffset(new RectangleInsets(0.0, 10.0, 0.0, 0.0));
                // graphDig.insets();
                localXYPlot.setInsets(new RectangleInsets(0.0, 20.0, 0.0, 0.0));

//                jdsDig1.close();

                graphDig.add(chartPress1);
//                graphDig.setEnabled(false);
//                chartPress1.setEnabled(false);
            }
            System.gc();
        }

        if (allDigital.isSelected()/* == 1*/ && difference > 1000/*&& allDigital.isSelected() && (Speed.isSelected() || Pres1.isSelected() || Pres2.isSelected())*/) {

            graphsNum++;
            final int digSigsNumber = digSigsCalc();

            String tkLabels[] = makeTickLabels(digSigsNumber);

            System.out.println("Start query all digits...");
            final JDBCXYDataset jdsDig0 = createDatasetDigitalsAll(spinnerStartStr, spinnerEndStr, digSigsNumber, conn);;



//            JDBCXYDataset jdsDig0 = createDatasetDigitalsAll(spinnerStartStr, spinnerEndStr, digSigsNumber);
            System.gc();

            System.out.println("Stop query all digits.");

//            if (jdsDig0 == null) System.out.println("Is null");
            System.out.println(jdsDig0.getX(0, 0));
            System.out.println(jdsDig0.getX(0, jdsDig0.getItemCount() - 1));
//            System.out.println(Timestamp.valueOf(textFieldEnd.getText()).getTime());
            System.out.println("Items in Digits: " + jdsDig0.getItemCount());



            if (jdsDig0.getItemCount() < 3) {
//                jdsDig0.close();
                System.out.println("Can't show the digits graph");
            } else {

                //////////////


                String name = "Вагон №: " + carNo + "   Дата запису: " + (String)comboDate.getSelectedItem();
//                String name = "";
                chAllDigits2 = ChartFactory.createTimeSeriesChart(name + "", "", "", jdsDig0);
                chartAllDigits2 = new ChartPanel(chAllDigits2, true);

                chAllDigits2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 17));

                chartAllDigits2.setLayout(new GridLayout());

                chAllDigits2.removeLegend();
                chAllDigits2.getTitle().setPosition(RectangleEdge.LEFT);
                chartAllDigits2.setMouseZoomable(false);


                chartAllDigits2.setAlignmentX((float) 0.0);




                int heightDigits = (digSigsNumber + 1) * 17 + 20;

                if (digSigsNumber < 22) //heightDigits = 22 * 17 + 20;
                {
                    chAllDigits2.setTitle(" \n " + name);
                    chAllDigits2.getTitle().setPosition(RectangleEdge.BOTTOM);
                    tkLabels[0] = String.format("%0$-39s","");
                    heightDigits = (digSigsNumber + 1) * 17 + 75;
                }

//                if (digSigsNumber < 22) //heightDigits = 22 * 17 + 20;
//                {
//                    chAllDigits2.getTitle().setPosition(RectangleEdge.BOTTOM);
//
//                }

                chartAllDigits2.setPreferredSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMaximumSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMinimumSize(new Dimension(width + 135 - 47, heightDigits));
                chartAllDigits2.setMaximumDrawWidth(width + 135 - 47);

                chartAllDigits2.setMinimumDrawWidth(100);
                chartAllDigits2.setMaximumDrawHeight(heightDigits);
                chartAllDigits2.setMinimumDrawHeight(10);

                XYPlot xyPlot = (XYPlot) chAllDigits2.getPlot();


                xyPlot.setDomainCrosshairVisible(true);
                xyPlot.setDomainCrosshairStroke(new BasicStroke(2));
                xyPlot.setDomainCrosshairPaint(Color.blue);

                // xyPlot.setInsets(new RectangleInsets(0.0, 50.0, 0.0, 0.0));
                //               xyPlot.getRangeAxis().setLabelInsets(new RectangleInsets(0.0, 50.0, 0.0, 0.0));
                //               xyPlot.getRangeAxis().setTickLabelInsets(new RectangleInsets(0.0, 250.0, 0.0, 0.0));

                DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//                xyPlot.getDomainAxis().setTickLabelFont(new Font("TimesNewRoman", Font.PLAIN, 6));
//                xyPlot.getRangeAxis().setTickLabelFont(new Font("TimesNewRoman", Font.PLAIN, 6));
//                NumberAxis numberAxis = (NumberAxis) xyPlot.getRangeAxis();
//                SymbolAxis symbolAxis = (SymbolAxis) xyPlot.getRangeAxis();
//                symbolAxis.setRange(0.0, 3800.0);
//                TickUnits units = new TickUnits();
//                String tk[] = (String[]) columnsJointTable;




//                ValueAxis yAxis = new SymbolAxis("", new String[]
//                        {"",
//                                 tk[40], tk[39], tk[38], tk[37], tk[36], tk[35], tk[34], tk[33], tk[32],
//                                tk[31], tk[30], tk[29], tk[28], tk[27], tk[26], tk[25], tk[24], tk[23], tk[22],
//                                tk[21], tk[20], tk[19], tk[18], tk[17], tk[16], tk[15], tk[14], tk[13], tk[12],
//                                tk[11], tk[10], tk[9], tk[8], tk[7], tk[6], tk[5], tk[4]
//                        });


                ValueAxis yAxis = new SymbolAxis("", tkLabels);

                xyPlot.setRangeAxis(yAxis);
                yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 11));
//                yAxis.setTickMarkOutsideLength((float) 210.0);
//                yAxis.setLabelInsets(new RectangleInsets(0.0, 10.0, 0.0, 0.0), true);
                yAxis.setAutoRange(false);
//                numberAxis.setRange(0.0, 380.0);
//                numberAxis.setTickUnit(new NumberTickUnit(10.0));


//                numberAxis.setTickUnit(new CategoryTick());

//        dateAxis.setRange(dataSet.getYValue(0, 0), dataSet.getYValue(0, 0) + timeSpan * 1000);
//                dateAxis.setRange(ts17.getTime(), ts17.getTime() + timeSpan * 1000);

                String strDateEnd = textFieldEnd.getText();

//                String tsString = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
//                java.sql.Timestamp ts = Timestamp.valueOf(tsString);

//                String tsEndString = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
//                String tsEndString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime())));
                String tsEndString = comboDate.getSelectedItem() + " " + spinnerEndStr;
                java.sql.Timestamp tsEnd = Timestamp.valueOf(tsEndString);

//                String tsStartString = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//                String tsStartString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
                String tsStartString = comboDate.getSelectedItem() + " " + spinnerStartStr;
                java.sql.Timestamp tsStart = Timestamp.valueOf(tsStartString);



//                java.sql.Timestamp ts = Timestamp.valueOf(strDateEnd);

                dateAxis.setPositiveArrowVisible(true);
                dateAxis.setRange(tsStart.getTime(), tsEnd.getTime());
//                dateAxis.setRange(jdsDig0.getXValue(0, 0), jdsDig0.getXValue(0, jdsDig0.getItemCount() - 1));

                ////////////

//sfsd
//            chartSpeed = newAnalogGraph("  (Швидкість)", jdsDig1, 0);

//                    ChartPanel chartSpeed = newAnalogGraph("(Швидкість)", jdsSpeed);
//            chAllDigits2 = chartAllDigits2.getChart();
//                    chartSpeed.createChartPrintJob();

//                    chartSpeed.setRangeZoomable(false);
//                    chartSpeed.setMouseZoomable(false);
//                    chartSpeed.zoomOutRange(5, 10);
//                    chartSpeed.restoreAutoDomainBounds();
                //chartSpeed.restoreAutoRangeBounds();

//                    chartSpeed.restoreAutoBounds();

//            chartAllDigits2.setAlignmentX((float) 0.0);


                final Marker km10 = new ValueMarker(1.0);
                km10.setLabel("Вибір напрямку");
                km10.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km20 = new ValueMarker(2.0);
                km20.setLabel("ВУ");
                km20.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km30 = new ValueMarker(3.0);
                km30.setLabel("ДАУ-АРШ");
                km30.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km40 = new ValueMarker(4.0);
                km40.setLabel("АРШ");
                km40.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km50 = new ValueMarker(5.0);
                km50.setLabel("48 пп");
                km50.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km60 = new ValueMarker(6.0);
                km60.setLabel("34 пп");
                km60.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km70 = new ValueMarker(7.0);
                km70.setLabel("25 пп");
                km70.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km80 = new ValueMarker(8.0);
                km80.setLabel("20 пп");
                km80.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km90 = new ValueMarker(9.0);
                km90.setLabel("18 пп");
                km90.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km100 = new ValueMarker(10.0);
                km100.setLabel("8 пп");
                km100.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km110 = new ValueMarker(11.0);
                km110.setLabel("Провід Ф7");
                km110.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km120 = new ValueMarker(12.0);
                km120.setLabel("6 пп");
                km120.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km130 = new ValueMarker(13.0);
                km130.setLabel("5 пп");
                km130.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km140 = new ValueMarker(14.0);
                km140.setLabel("4 пп");
                km140.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km150 = new ValueMarker(15.0);
                km150.setLabel("3 пп");
                km150.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km160 = new ValueMarker(16.0);
                km160.setLabel("2 пп");
                km160.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km170 = new ValueMarker(17.0);
                km170.setLabel("1 пп");
                km170.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km180 = new ValueMarker(18.0);
                km180.setLabel("КРП");
                km180.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km190 = new ValueMarker(19.0);
                km190.setLabel("Ввімк.бл.перетв");
                km190.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km200 = new ValueMarker(20.0);
                km200.setLabel("Стоян. гальмо");
                km200.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km210 = new ValueMarker(21.0);
                km210.setLabel("Доп.шв. 80 км/г");
                km210.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km220 = new ValueMarker(22.0);
                km220.setLabel("Доп.шв. 70 км/г");
                km220.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km230 = new ValueMarker(23.0);
                km230.setLabel("Доп.шв. 60 км/г");
                km230.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km240 = new ValueMarker(24.0);
                km240.setLabel("Доп.шв. 40 км/г");
                km240.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km250 = new ValueMarker(25.0);
                km250.setLabel("Доп.шв.0 км/г");
                km250.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km260 = new ValueMarker(26.0);
                km260.setLabel("Відсутня част.");
                km260.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km270 = new ValueMarker(27.0);
                km270.setLabel("КБ(ПБ)");
                km270.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km280 = new ValueMarker(28.0);
                km280.setLabel("Зачин. дверей");
                km280.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km290 = new ValueMarker(29.0);
                km290.setLabel("Ввімк. ОВТ");
                km290.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km300 = new ValueMarker(30.0);
                km300.setLabel("Ввімк. ВАД");
                km300.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km310 = new ValueMarker(31.0);
                km310.setLabel("Ввімк. ВАХ");
                km310.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km320 = new ValueMarker(32.0);
                km320.setLabel("Рух від КАХ");
                km320.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km330 = new ValueMarker(33.0);
                km330.setLabel("РП відновл.");
                km330.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km340 = new ValueMarker(34.0);
                km340.setLabel("ЕПК ввімк.");
                km340.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km350 = new ValueMarker(35.0);
                km350.setLabel("Радіост. ввім.");
                km350.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km360 = new ValueMarker(36.0);
                km360.setLabel("АЛС ввім.");
                km360.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                final Marker km370 = new ValueMarker(37.0);
                km370.setLabel("АРШ ввім");
                km370.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);

                JFreeChart chart7 = chartAllDigits2.getChart();
                XYPlot localXYPlot = (XYPlot) chart7.getPlot();

//                localXYPlot.addRangeMarker(km10);
//                localXYPlot.addRangeMarker(km20);
//                localXYPlot.addRangeMarker(km30);
//                localXYPlot.addRangeMarker(km40);
//                localXYPlot.addRangeMarker(km50);
//                localXYPlot.addRangeMarker(km60);
//                localXYPlot.addRangeMarker(km70);
//                localXYPlot.addRangeMarker(km80);
//                localXYPlot.addRangeMarker(km90);
//                localXYPlot.addRangeMarker(km100);
//                localXYPlot.addRangeMarker(km110);
//                localXYPlot.addRangeMarker(km120);
//                localXYPlot.addRangeMarker(km130);
//                localXYPlot.addRangeMarker(km140);
//                localXYPlot.addRangeMarker(km150);
//                localXYPlot.addRangeMarker(km160);
//                localXYPlot.addRangeMarker(km170);
//                localXYPlot.addRangeMarker(km180);
//                localXYPlot.addRangeMarker(km190);
//                localXYPlot.addRangeMarker(km200);
//                localXYPlot.addRangeMarker(km210);
//                localXYPlot.addRangeMarker(km220);
//                localXYPlot.addRangeMarker(km230);
//                localXYPlot.addRangeMarker(km240);
//                localXYPlot.addRangeMarker(km250);
//                localXYPlot.addRangeMarker(km260);
//                localXYPlot.addRangeMarker(km270);
//                localXYPlot.addRangeMarker(km280);
//                localXYPlot.addRangeMarker(km290);
//                localXYPlot.addRangeMarker(km300);
//                localXYPlot.addRangeMarker(km310);
//                localXYPlot.addRangeMarker(km320);
//                localXYPlot.addRangeMarker(km330);
//                localXYPlot.addRangeMarker(km340);
//                localXYPlot.addRangeMarker(km350);
//                localXYPlot.addRangeMarker(km360);
//                localXYPlot.addRangeMarker(km370);

//                localXYPlot.getRangeAxis().setVisible(false);

                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

                System.out.println("Speed datasets: " + localXYPlot.getDatasetCount());
                localXYPlot.setBackgroundPaint(Color.WHITE);
                localXYPlot.setDomainGridlinePaint(Color.GRAY);
                localXYPlot.setRangeGridlinePaint(Color.GRAY);

                localXYPlot.setRenderer(new SamplingXYLineRenderer());

                XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer();
                XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
                XYItemRenderer ren0 = localXYPlot.getRenderer();

//            SamplingXYLineRenderer ren0 = new SamplingXYLineRenderer();

                ren0.setSeriesPaint(0, new Color(10, 10, 150));
                ren0.setSeriesPaint(1, new Color(10, 10, 125));
                ren0.setSeriesPaint(2, new Color(10, 10, 100));
                ren0.setSeriesPaint(3, new Color(10, 10, 75));
                ren0.setSeriesPaint(4, new Color(10, 50, 75));
                ren0.setSeriesPaint(5, new Color(10, 75, 75));
                ren0.setSeriesPaint(6, new Color(10, 100, 75));
                ren0.setSeriesPaint(7, new Color(10, 125, 75));
                ren0.setSeriesPaint(8, new Color(10, 150, 75));
                ren0.setSeriesPaint(9, new Color(10, 175, 75));
                ren0.setSeriesPaint(10, new Color(10, 200, 75));
                ren0.setSeriesPaint(11, new Color(10, 225, 75));
                ren0.setSeriesPaint(12, new Color(10, 250, 75));
                ren0.setSeriesPaint(13, new Color(10, 250, 75));
                ren0.setSeriesPaint(14, new Color(100, 250, 75));
//            ren0.setSeriesPaint(15, Color.black);
//            ren0.setSeriesPaint(16, Color.black);
                ren0.setSeriesPaint(17, Color.GREEN);
//            ren0.setSeriesPaint(18, Color.black);
//            ren0.setSeriesPaint(19, Color.black);
                ren0.setSeriesPaint(20, Color.DARK_GRAY);
                ren0.setSeriesPaint(21, Color.BLUE);
//            ren0.setSeriesPaint(22, Color.black);
//            ren0.setSeriesPaint(23, Color.black);
                ren0.setSeriesPaint(24, Color.BLUE);
//            ren0.setSeriesPaint(25, Color.black);
                ren0.setSeriesPaint(26, Color.ORANGE);
//            ren0.setSeriesPaint(27, Color.black);
                ren0.setSeriesPaint(28, Color.RED);
//            ren0.setSeriesPaint(29, Color.black);
//            ren0.setSeriesPaint(30, Color.black);
//            ren0.setSeriesPaint(31, Color.GRAY);
//            ren0.setSeriesPaint(32, Color.CYAN);
//            ren0.setSeriesPaint(33, Color.YELLOW);
//            ren0.setSeriesPaint(34, Color.ORANGE);
//            ren0.setSeriesPaint(35, Color.green);
//            ren0.setSeriesPaint(36, Color.blue);

//            ren0.setSeriesPaint(37, Color.white);
                ren0.setSeriesPaint(digSigsNumber, Color.white);
//
//            ren0.setSeriesPaint(38, Color.white);
//                ren0.setSeriesPaint(39, Color.white);
                int stroke = 5;

                ren0.setSeriesStroke(0, new BasicStroke(stroke));
                ren0.setSeriesStroke(1, new BasicStroke(stroke));
                ren0.setSeriesStroke(2, new BasicStroke(stroke));
                ren0.setSeriesStroke(3, new BasicStroke(stroke));
                ren0.setSeriesStroke(4, new BasicStroke(stroke));
                ren0.setSeriesStroke(5, new BasicStroke(stroke));
                ren0.setSeriesStroke(6, new BasicStroke(stroke));
                ren0.setSeriesStroke(7, new BasicStroke(stroke));
                ren0.setSeriesStroke(8, new BasicStroke(stroke));
                ren0.setSeriesStroke(9, new BasicStroke(stroke));
                ren0.setSeriesStroke(10, new BasicStroke(stroke));
                ren0.setSeriesStroke(11, new BasicStroke(stroke));
                ren0.setSeriesStroke(12, new BasicStroke(stroke));
                ren0.setSeriesStroke(13, new BasicStroke(stroke));
                ren0.setSeriesStroke(14, new BasicStroke(stroke));
                ren0.setSeriesStroke(15, new BasicStroke(stroke));
                ren0.setSeriesStroke(16, new BasicStroke(stroke));
                ren0.setSeriesStroke(17, new BasicStroke(stroke));
                ren0.setSeriesStroke(18, new BasicStroke(stroke));
                ren0.setSeriesStroke(19, new BasicStroke(stroke));
                ren0.setSeriesStroke(20, new BasicStroke(stroke));
                ren0.setSeriesStroke(21, new BasicStroke(stroke));
                ren0.setSeriesStroke(22, new BasicStroke(stroke));
                ren0.setSeriesStroke(23, new BasicStroke(stroke));
                ren0.setSeriesStroke(24, new BasicStroke(stroke));
                ren0.setSeriesStroke(25, new BasicStroke(stroke));
                ren0.setSeriesStroke(26, new BasicStroke(stroke));
                ren0.setSeriesStroke(27, new BasicStroke(stroke));
                ren0.setSeriesStroke(28, new BasicStroke(stroke));
                ren0.setSeriesStroke(29, new BasicStroke(stroke));
                ren0.setSeriesStroke(30, new BasicStroke(stroke));
                ren0.setSeriesStroke(31, new BasicStroke(stroke));
                ren0.setSeriesStroke(32, new BasicStroke(stroke));
                ren0.setSeriesStroke(33, new BasicStroke(stroke));
                ren0.setSeriesStroke(34, new BasicStroke(stroke));
                ren0.setSeriesStroke(35, new BasicStroke(stroke));
                ren0.setSeriesStroke(36, new BasicStroke(stroke));
                ren0.setSeriesStroke(37, new BasicStroke(1));

//            localXYPlot.setRenderer(new SamplingXYLineRenderer());

//                jdsDig0.close();

                graphDig.add(chartAllDigits2);
            }
        }

        graphDig.setLayout(new BoxLayout(graphDig, BoxLayout.Y_AXIS));
        graphDig.setAlignmentX(Component.LEFT_ALIGNMENT);
        graphDig.setAlignmentY(Component.LEFT_ALIGNMENT);
        graphDig.setBackground(Color.white);

        if (!Speed.isSelected() && chartSpeed != null) chartSpeed.setVisible(false);
        if (!Pres1.isSelected() && !Pres2.isSelected() && chartPress1 != null) chartPress1.setVisible(false);
        if (!Dig1.isSelected() && !Dig2.isSelected() && !Dig3.isSelected() && !Dig4.isSelected() && !Dig5.isSelected() &&
                !Dig6.isSelected() && !Dig7.isSelected() && !Dig8.isSelected() && !Dig9.isSelected() && !Dig10.isSelected() &&

                !Dig11.isSelected() && !Dig12.isSelected() && !Dig13.isSelected() && !Dig14.isSelected() && !Dig15.isSelected() &&
                !Dig16.isSelected() && !Dig17.isSelected() && !Dig18.isSelected() && !Dig19.isSelected() && !Dig20.isSelected() &&

                !Dig21.isSelected() && !Dig22.isSelected() && !Dig23.isSelected() && !Dig24.isSelected() && !Dig25.isSelected() &&
                !Dig26.isSelected() && !Dig27.isSelected() && !Dig28.isSelected() && !Dig29.isSelected() && !Dig30.isSelected() &&

                !Dig31.isSelected() && !Dig32.isSelected() && !Dig33.isSelected() && !Dig34.isSelected() && !Dig35.isSelected() &&
                !Dig36.isSelected() && !Dig37.isSelected()

                && chartAllDigits2 != null
                ) chartAllDigits2.setVisible(false);


        scrollGraphs.add(graphDig);
        scrollGraphs.setAlignmentY(Component.LEFT_ALIGNMENT);
        scrollGraphs.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollGraphs.setViewportView(graphDig);

        System.gc();

    }

    void saveDatabaseProc() {
        final JDialog dlgDbSave = new JDialog(frame, "Хід завантаження...", true);
        dlgDbSave.add(BorderLayout.CENTER, dpb);
        JLabel dialogLabel = new JLabel("Збереження даних до БД...");
        dlgDbSave.add(BorderLayout.NORTH, dialogLabel);
        dlgDbSave.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDbSave.setSize(300, 75);
        dlgDbSave.setLocationRelativeTo(frame);
        dpb.setStringPainted(false);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try{
                    dpb.setIndeterminate(true);
                    String strUrl = "jdbc:derby:wagons";
                    Connection backupConn = DriverManager.getConnection(strUrl);

                    backUpDatabase(backupConn);

                    backupDBLabel.setForeground(Color.GREEN);
                    backupDBLbl.setText("Базу збережено");
                    backupConn.close();
                    progressBar1.setIndeterminate(false);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Базу збережено!");
                    backupDBLabel.setForeground(Color.LIGHT_GRAY);
                    backupDBLbl.setText("Очікування");
                    dpb.setIndeterminate(false);
                    dlgDbSave.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace(System.err);
                    dpb.setIndeterminate(false);
                    dlgDbSave.setVisible(false);
                }

//
//                deleteAllDb();
//                readDb();
//                dpb.setValue(300000);
//                if (dpb.getValue() == 300000) dlgDbSave.setVisible(false);
//                createBigTable();
//                createDigitsTable();
//
//                loadDBLabel.setForeground(Color.GREEN);
//                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Базу даних збережено!");
//                loadDBLbl.setText("Очікування");
//                loadDBLabel.setForeground(Color.GRAY);
//                progressBar1.setIndeterminate(false);
//                progressBar2.setValue(0);
//                dpb.setValue(0);
            }
        });
        t.start();
        dlgDbSave.setVisible(true);


    }

    void nextDataProc() {
        final JDialog dlgDbRestore = new JDialog(frame, "Отримання даних...", true);
        dlgDbRestore.add(BorderLayout.CENTER, dpb);
        JLabel dialogLabel = new JLabel("Завантаження даних з БД...");
        dlgDbRestore.add(BorderLayout.NORTH, dialogLabel);
        dlgDbRestore.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDbRestore.setSize(300, 75);
        dlgDbRestore.setLocationRelativeTo(frame);
        dpb.setStringPainted(false);


        Thread t = new Thread(new Runnable() {

            public void run() {

                    try {
                        dpb.setIndeterminate(true);
                        progressBar1.setIndeterminate(true);

                        offsetToShow += secondsToShow;
                        System.gc();
//                        createBigTable();
                        showGraphs();
                        System.gc();

                        dpb.setIndeterminate(false);
                        dlgDbRestore.setVisible(false);

                        progressBar1.setIndeterminate(false);


                    } catch (Exception es) {}

                }

        });
        t.start();
        dlgDbRestore.setVisible(true);
    }

    void previousDataProc() {
        final JDialog dlgDbRestore = new JDialog(frame, "Отримання даних...", true);
        dlgDbRestore.add(BorderLayout.CENTER, dpb);
        JLabel dialogLabel = new JLabel("Завантаження даних з БД...");
        dlgDbRestore.add(BorderLayout.NORTH, dialogLabel);
        dlgDbRestore.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDbRestore.setSize(300, 75);
        dlgDbRestore.setLocationRelativeTo(frame);
        dpb.setStringPainted(false);

        Thread t = new Thread(new Runnable() {

            public void run() {

                try {
                    dpb.setIndeterminate(true);
                    progressBar1.setIndeterminate(true);

                    offsetToShow = (offsetToShow > secondsToShow ? offsetToShow - secondsToShow : 0);
                    System.gc();
//                    createBigTable();
                    showGraphs();
                    System.gc();

                    dpb.setIndeterminate(false);
                    dlgDbRestore.setVisible(false);

                    progressBar1.setIndeterminate(false);

                } catch (Exception es) {}

            }

        });
        t.start();
        dlgDbRestore.setVisible(true);
    }

    void restoreDatabaseProc() {
        final JDialog dlgDbRestore = new JDialog(frame, "Хід завантаження...", true);
        dlgDbRestore.add(BorderLayout.CENTER, dpb);
        JLabel dialogLabel = new JLabel("Завантаження даних з БД...");
        dlgDbRestore.add(BorderLayout.NORTH, dialogLabel);
        dlgDbRestore.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDbRestore.setSize(300, 75);
        dlgDbRestore.setLocationRelativeTo(frame);
        dpb.setStringPainted(false);

        try{
            String strUr0 = "jdbc:derby:;shutdown=true";
            System.out.println(DriverManager.getConnection(strUr0));
        } catch (SQLException ex) {
            //              ex.printStackTrace();
            System.out.println(ex.getErrorCode());
            ex.printStackTrace(System.err);
        }

        Thread t = new Thread(new Runnable() {

            public void run() {

                FileDialog fileDialog = new FileDialog(new Frame(), "Choose A Backup Folder)", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String fileName = fileDialog.getDirectory();


                if (fileDialog.getDirectory() != null) {
                    try {
                        dpb.setIndeterminate(true);
                        progressBar1.setIndeterminate(true);
                        fileName = fileName.substring(0, fileName.lastIndexOf(System.getProperty("file.separator")));
                        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                        String strUrl = "jdbc:derby:wagons";
                        Properties connectionProps = new Properties();
                        connectionProps.put("restoreFrom", fileName);

                        connectionProps.put("user", "pass");
                        connectionProps.put("root", "wagons");
                        Connection backupConn1 = DriverManager.getConnection(strUrl, connectionProps);
                        backupConn1.commit();
                        backupConn1.close();

                        restoreDBLabel.setForeground(Color.GREEN);
                        restoreDBLbl.setText("Базу відтворено");

//                        spinner3.setModel(new SpinnerDateModel());
//                        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner3, "dd-MMM-yyyy HH:mm:ss");
//                        spinner3.setEditor(timeEditor);
                        readCoeffs();
                        getRecordStartEnd();
                        createSettingsInfoData();
//                        createBigTable();



//                        createDigitsTable(offsetToShow, secondsToShow);

                        dpb.setIndeterminate(false);
                        dlgDbRestore.setVisible(false);

                        progressBar1.setIndeterminate(false);
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Базу завантажено!");
                        restoreDBLabel.setForeground(Color.LIGHT_GRAY);
                        restoreDBLbl.setText("Очікування");
                        System.gc();


                    } catch (SQLException ex) {
                        //              ex.printStackTrace();
                        System.out.println(ex.getErrorCode());
                        ex.printStackTrace(System.err);
                    } catch (Exception es) {}
                } else {
                    dlgDbRestore.setVisible(false);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid database format");
                    restoreDBLabel.setForeground(Color.LIGHT_GRAY);
                    restoreDBLbl.setText("Очікування");

                }
            }
        });
        t.start();
        dlgDbRestore.setVisible(true);

    }

    void readDeviceProc(){
        textReadCoefOnePmax.setText("");
        textReadCoefOneUmin.setText("");
        textReadCoefOneUmax.setText("");
        textReadCoefTwoPmax.setText("");
        textReadCoefTwoUmin.setText("");
        textReadCoefTwoUmax.setText("");
        coeffSpeedRead.setText("");

        final JDialog dlgDevice = new JDialog(frame, "Хід завантаження...", true);
        dlgDevice.add(BorderLayout.CENTER, dpb);
        final JLabel dialogLabel = new JLabel("Завантаження даних з пристрою...");
        dlgDevice.add(BorderLayout.NORTH, dialogLabel);
        dlgDevice.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dlgDevice.setSize(300, 75);
        dlgDevice.setLocationRelativeTo(frame);
        dpb.setStringPainted(true);

        Thread t = new Thread(new Runnable() {
            public void run() {

                readDeviceLabel.setForeground(Color.GREEN);
                reading.setSelected(readDevice());

                dpb.setValue(300000);
                if (dpb.getValue() == 300000) dpb.setValue(0); //dlgDevice.setVisible(false);

                if (!reading.isSelected()) {
                    readDeviceLabel.setForeground(Color.LIGHT_GRAY);
                    readDeviceLbl.setText("Збій передачі...");
                    progressBar1.setIndeterminate(false);
                    progressBar2.setValue(0);
                    dpb.setValue(0);
                    dlgDevice.setVisible(false);
//                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Спроба завантажити поточні дані...");

                }
                else {
                    readDeviceLbl.setText("Дані отримано!");
                    progressBar1.setIndeterminate(false);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані отримано!");
                    readDeviceLabel.setForeground(Color.LIGHT_GRAY);
                    readDeviceLbl.setText("Очікування");
                    progressBar2.setValue(0);
                    dpb.setValue(0);

//                    int progValueBin = 12582912;
                    int progValueBin = 12582912 - (36 * 4096);
//                    progValueBin = 5000000;
                    dpb.setMaximum(progValueBin);

                    progressBar2.setMaximum(progValueBin);
                    progressBar2.setValue(0);
                    progressBar1.setIndeterminate(true);
 //                   deleteAllDb();
                    dialogLabel.setText("Завантаження до БД");
                    readDeviceButton.setText("Завантаження до БД");

                    loadDB();
//                    refreshButton.setEnabled(true);
//                    readDb();
//                    createBigTable();
//                    createDigitsTable(offsetToShow, secondsToShow);


                    dpb.setValue(progValueBin);
                    if (dpb.getValue() == progValueBin)
                        dlgDevice.setVisible(false);


                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані зконвертовано у БД");

                }
                //progressBar2.setMaximum(1258291);
//                        progressBar2.setMaximum(30000 * 10);
//                        progressBar2.setValue(0);
//                        progressBar1.setIndeterminate(true);
//                        deleteAllDb();
//                        readDb();
//                        createBigTable();
//                        createDigitsTable();
                dpb.setValue(0);
                progressBar2.setValue(0);
                progressBar1.setIndeterminate(false);

//                loadDBLabel.setForeground(Color.GREEN);
//                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані завантажено!");
//                loadDBLbl.setText("Очікування");
//                loadDBLabel.setForeground(Color.GRAY);
//                progressBar1.setIndeterminate(false);
//                progressBar2.setValue(0);
            }
        });
        t.start();
        dlgDevice.setVisible(true);

    }


        void  loadDB() {

            final JDialog dlg = new JDialog(frame, "Progress Dialog", true);
            dlg.add(BorderLayout.CENTER, dpb);
            JLabel dialogLabel = new JLabel("Збереження даних до БД...");
//            dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
            dlg.add(BorderLayout.NORTH, dialogLabel);
            dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dlg.setSize(300, 75);
            dlg.setLocationRelativeTo(frame);
            dpb.setStringPainted(true);



//            new Thread() {
//                public void run() {
//                    dlg.setVisible(true);
//                }
//            }.start();


                Thread t = new Thread(new Runnable() {
                    public void run() {
                        int progValueBin = 12582912;
                        //progValueBin = 300000;
                        dpb.setMaximum(progValueBin);

                        progressBar2.setMaximum(progValueBin);


//                        deleteAllDb();
                        readDb();
//                        readCoeffs();


                        dpb.setValue(progValueBin);

//                        createBigTable();

//                        createDigitsTable();


                        if (dpb.getValue() == progValueBin) dlg.setVisible(false);

                        loadDBLabel.setForeground(Color.GREEN);
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані зконвертовано!");
                        loadDBLbl.setText("Очікування");
                        loadDBLabel.setForeground(Color.LIGHT_GRAY);
                        progressBar1.setIndeterminate(false);
                        progressBar2.setValue(0);
                        dpb.setValue(0);
                    }
                });
                t.start();
            dlg.setVisible(true);
        }


    ///////////////// CREATE DIGITAL GRAPH //////////////////////////////////////////////////////
    private ChartPanel newDigitalGraphTwo(String name, IntervalXYDataset dataSet) {

        JFreeChart chartDig2 = createChartDigitsTwo(dataSet, name);

        ChartPanel chPDig2 = new ChartPanel(chartDig2, true);
        chPDig2.setLayout(new GridLayout());

        chartDig2.removeLegend();
        chartDig2.setTitle("");

        chartDig2.getTitle().setPosition(RectangleEdge.LEFT);
        chartDig2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 11));
        XYPlot xyPlot = (XYPlot) chartDig2.getPlot();

        DateAxis dateAxis = (DateAxis) xyPlot.getRangeAxis();

//        dateAxis.setRange(dataSet.getYValue(0, 0), dataSet.getYValue(0, 0) + timeSpan * 1000);
        dateAxis.setRange(ts17.getTime(), ts17.getTime() + timeSpan * 1000);

        if (graphsNum == 1 /*|| graphsNum % 12 == 0*/) {
            flagAxisTen = true;
            dateAxis.setVisible(true);
            if (dataSet.getItemCount(0) < 1)
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "We have no data to display...");
            else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String stringStart  = dateFormat.format(dataSet.getYValue(0, 0));
                String stringStart = dateFormat.format(ts17.getTime());
//            String stringEnd  = dateFormat.format(dataSet.getXValue(0, dataSet.getItemCount(0) - 1));
//            String stringEnd  = dateFormat.format(dataSet.getYValue(0, dataSet.getItemCount(0) - 1));
                String stringEnd = dateFormat.format(dataSet.getYValue(0, dataSet.getItemCount(0) - 10));
                System.out.println("String Start = " + stringStart);
                System.out.println("String End = " + stringEnd);


                //    labelStart.setText(stringStart);
                labelEnd.setText(stringEnd);
                comboBox1.addItem(stringStart);
                comboBox2.addItem(stringEnd);
                //    textFieldStart.setText(stringStart);
                textFieldEnd.setText(stringEnd);


                chPDig2.setPreferredSize(new Dimension(1300, 50));
                chPDig2.setMaximumSize(new Dimension(1300, 50));
                chPDig2.setMinimumSize(new Dimension(1300, 50));
                chPDig2.setMaximumDrawWidth(1200);
                chPDig2.setMinimumDrawWidth(100);
                chPDig2.setMaximumDrawHeight(50);
                chPDig2.setMinimumDrawHeight(50);
            }

        } else {
            dateAxis.setVisible(false);
            chPDig2.setPreferredSize(new Dimension(1300, 30));
            chPDig2.setMaximumSize(new Dimension(1300, 30));
            chPDig2.setMinimumSize(new Dimension(1300, 30));
            chPDig2.setMaximumDrawWidth(1300);
            chPDig2.setMinimumDrawWidth(100);
            chPDig2.setMaximumDrawHeight(30);
            chPDig2.setMinimumDrawHeight(30);
        }

        if (!flagSlider) {
            if (dataSet.getItemCount(0) < 1)
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "We have no data to display...");
            else {
                bigParticle = (long) (Math.floor(dataSet.getYValue(0, 0) / 1000000000L) * 1000000000L);
                int min = (int)((long) dataSet.getYValue(0, 0) - bigParticle);
                int max = (int)((long) dataSet.getYValue(0, dataSet.getItemCount(0) - 1) - bigParticle);

                flagSlider = true;
                graphsSlider.setMinimum(min);
                graphsSlider.setMaximum(max);
            }
        }

        return chPDig2;
    }


    private ChartPanel newDigitalGraph(String name, JDBCXYDataset jdsDig2) {

//        JDBCXYDataset jdsDig2 = createDatasetDigitTwo();
        JFreeChart chartDig2 = ChartFactory.createTimeSeriesChart(name + "\n.\n.", "", "", jdsDig2);

        ChartPanel chPDig2 = new ChartPanel(chartDig2, true);
        chPDig2.setLayout(new GridLayout());

//        chPDig2.setSize(new Dimension(70, 70));
//        chPDig2.setMaximumSize(new Dimension(700, 160));
        chartDig2.removeLegend();
        chartDig2.getTitle().setPosition(RectangleEdge.LEFT);
        chartDig2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 11));
        XYPlot xyPlot = (XYPlot) chartDig2.getPlot();
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(-0.1, 1.1);
        range.setTickUnit(new NumberTickUnit(1.0));

        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();

        dateAxis.setRange(jdsDig2.getXValue(0, 0), jdsDig2.getXValue(0, 0) + timeSpan * 1000);

        if (graphsNum == 1 || graphsNum % 12 == 0) {
            flagAxisTen = true;
            dateAxis.setVisible(true);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringStart  = dateFormat.format(jdsDig2.getXValue(0, 0));
            String stringEnd  = dateFormat.format(jdsDig2.getXValue(0, jdsDig2.getItemCount() - 1));

        //    labelStart.setText(stringStart);
            labelEnd.setText(stringEnd);
            comboBox1.addItem(stringStart);
            comboBox2.addItem(stringEnd);
         //   textFieldStart.setText(stringStart);
            textFieldEnd.setText(stringEnd);


            chPDig2.setPreferredSize(new Dimension(1300, 70));
            chPDig2.setMaximumSize(new Dimension(1300, 70));
            chPDig2.setMinimumSize(new Dimension(1300, 70));
            chPDig2.setMaximumDrawWidth(1200);
            chPDig2.setMinimumDrawWidth(100);
            chPDig2.setMaximumDrawHeight(70);
            chPDig2.setMinimumDrawHeight(70);

        } else {
            dateAxis.setVisible(false);
            chPDig2.setPreferredSize(new Dimension(1300, 50));
            chPDig2.setMaximumSize(new Dimension(1300, 50));
            chPDig2.setMinimumSize(new Dimension(1300, 50));
            chPDig2.setMaximumDrawWidth(1300);
            chPDig2.setMinimumDrawWidth(100);
            chPDig2.setMaximumDrawHeight(50);
            chPDig2.setMinimumDrawHeight(50);
        }

        if (!flagSlider) {
            bigParticle = (long) (Math.floor(jdsDig2.getXValue(0, 0) / 1000000000L) * 1000000000L);
            int min = (int)((long) jdsDig2.getXValue(0, 0) - bigParticle);
            int max = (int)((long) jdsDig2.getXValue(0, jdsDig2.getItemCount() - 1) - bigParticle);
//            int max = (int)((long) jdsDig2.getXValue(0, jdsDig2.getItemCount() - 1) - bigParticle);

            flagSlider = true;
            graphsSlider.setMinimum(min);
            graphsSlider.setMaximum(max);
        }

        return chPDig2;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

///////////////////CREATE ALL DIGITALS GRAPH////////////////////////////////////////////////


    ///////////////// CREATE ANALOG GRAPH //////////////////////////////////////////////////////
    private ChartPanel newAnalogGraph11(String name, JDBCXYDataset jdsAn2, int signalType) {
        JFreeChart chartTimeAxis;
        name = "";

        if (signalType == 0) {
            chartTimeAxis = ChartFactory.createTimeSeriesChart(name + "\n \n \n ", "", "", jdsAn2);
        }else {
            chartTimeAxis = ChartFactory.createTimeSeriesChart(name + "\n \n \n \n ", "", "", jdsAn2);
        }
        ChartPanel chPTimeAxis = new ChartPanel(chartTimeAxis, true);

        chPTimeAxis.setLayout(new GridLayout());

        chartTimeAxis.removeLegend();
        chartTimeAxis.getTitle().setPosition(RectangleEdge.LEFT);

        if (signalType == 0) {
            chartTimeAxis.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 16));
        }  else {
            chartTimeAxis.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
        }
        XYPlot xyPlot = (XYPlot) chartTimeAxis.getPlot();
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();


            range.setRange(-1.1, 1.0/*coefPresOne*/);
            range.setTickUnit(new NumberTickUnit(0.5));


        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);
        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, 0) + timeSpan * 1000);

        dateAxis.setVisible(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringStart  = dateFormat.format(jdsAn2.getXValue(0, 0));
        String stringEnd  = dateFormat.format(jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1));

//            //    labelStart.setText(stringStart);
//            labelEnd.setText(stringEnd);
//            comboBox1.addItem(stringStart);
//            comboBox2.addItem(stringEnd);
////            spinner1.setValue(stringStart);
////            spinner2.setValue(stringEnd);
//            //   textFieldStart.setText(stringStart);
//            textFieldEnd.setText(stringEnd);

        chPTimeAxis.setPreferredSize(new Dimension(1300, 40));
        chPTimeAxis.setMaximumSize(new Dimension(1300, 40));
        chPTimeAxis.setMinimumSize(new Dimension(1300, 40));
        chPTimeAxis.setMaximumDrawWidth(1300);
        chPTimeAxis.setMinimumDrawWidth(100);
        chPTimeAxis.setMaximumDrawHeight(20);
        chPTimeAxis.setMinimumDrawHeight(20);



//        range.setAutoRange(false);
////        dateAxis.setAutoRange(false);
//        range.setDefaultAutoRange(new Range(-50.0, 50.0));
////        chPAn2.zoomOutRange(0, 0);
//        range.setFixedAutoRange(100.0);
//        chPAn2.setRangeZoomable(false);


//        if (!flagSlider) {
//
//            bigParticle = (long) (Math.floor(jdsAn2.getXValue(0, 0) / 1000000000L) * 1000000000L);
//            int min = (int)((long) jdsAn2.getXValue(0, 0) - bigParticle);
//            int max = (int)((long) jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1) - bigParticle - timeSpan * 1050);
//
//            System.out.println("Preset slider max: " + max);
//
//            flagSlider = true;
//            graphsSlider.setMinimum(min);
//            graphsSlider.setMaximum(max);
//
//        }

//        range.setAutoRange(false);
////        range.setFixedAutoRange(10.0);
//        range.setRange(new Range(0.1, 10.1));
//        range.setDefaultAutoRange(new Range(0.1, 10.1));
//        chPTimeAxis.zoomOutRange(0.1, 100.1);
////        range.setAutoRange


        return chPTimeAxis;
    }
//////////////////////////////////////////////////////////////////////////////////////////////



    ///////////////// CREATE ANALOG GRAPH //////////////////////////////////////////////////////
    private ChartPanel newAnalogGraph(String name, JDBCXYDataset jdsAn2, int signalType, String spinnerStartStr, String spinnerEndStr) {
        final JFreeChart chartAn2;
        String strDateEnd = textFieldEnd.getText();

        if (signalType == 0) {
            chartAn2 = ChartFactory.createTimeSeriesChart(name + "\n \n \n \n ", "", "", jdsAn2);
        }else {
            chartAn2 = ChartFactory.createTimeSeriesChart(name + "\n \n \n \n ", "", "", jdsAn2);
        }
        ChartPanel chPAn2 = new ChartPanel(chartAn2, true);

//        chPAn2.setRangeZoomable(false);
//        chPAn2.setDomainZoomable(true);
//        chPAn2.zoomInRange(1, 2);
//        chPAn2.setRangeZoomable(false);
//        chPAn2.restoreAutoBounds();
//        chPAn2.
        chPAn2.setMouseZoomable(false);

        chPAn2.setLayout(new GridLayout());

        chartAn2.removeLegend();
        chartAn2.getTitle().setPosition(RectangleEdge.LEFT);

        final Marker km40 = new ValueMarker(40.0);
//        km40.setLabel("40 км/год");
        km40.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        km40.setLabelFont(new Font("TimesNewRoman", Font.PLAIN, 12));

        final Marker km60 = new ValueMarker(60.0);
//        km60.setLabel("60 км/год");
        km60.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        km60.setLabelFont(new Font("TimesNewRoman", Font.PLAIN, 12));

        final Marker km70 = new ValueMarker(70.0);
//        km70.setLabel("70 км/год");
        km70.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        km70.setLabelFont(new Font("TimesNewRoman", Font.PLAIN, 12));

        final Marker km80 = new ValueMarker(80.0);
//        km80.setLabel("80 км/год");
        km80.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        km80.setLabelFont(new Font("TimesNewRoman", Font.PLAIN, 12));

        km40.setPaint(Color.GRAY);
        km60.setPaint(Color.GRAY);
        km70.setPaint(Color.GRAY);
        km80.setPaint(Color.GRAY);


        if (signalType == 0) {
            chartAn2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
            XYPlot xyPlot1 = (XYPlot) chartAn2.getPlot();

            xyPlot1.addRangeMarker(km40);
            xyPlot1.addRangeMarker(km60);
            xyPlot1.addRangeMarker(km70);
            xyPlot1.addRangeMarker(km80);
            xyPlot1.setRangeGridlinesVisible(false);
        }  else {
            chartAn2.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 14));
        }
        XYPlot xyPlot = (XYPlot) chartAn2.getPlot();
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setTickMarkPaint(Color.black);



//        XYItemRenderer r = xyPlot.getRenderer();
//        XYLineAndShapeRenderer r = xyPlot.getRenderer();
//        if (r instanceof XYLineAndShapeRenderer) {
//            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
//            renderer.getPlot().setSeriesVisible(false);
////            renderer.setDefaultShapesFilled(true);
//        }

        if (signalType == 0) {
            range.setRange(-1.1, 90.1 * 1/*coefPresOne*/);
//            range.setTickUnit(new NumberTickUnit(50.0));
            range.setTickUnit(new NumberTickUnit(10.0));
            TickUnits units = new TickUnits();
            units.add(new NumberTickUnit(40.0));
            units.add(new NumberTickUnit(60.0));
            units.add(new NumberTickUnit(70.0));
            units.add(new NumberTickUnit(80.0));
            range.setStandardTickUnits(units);

            range.setAutoRange(false);

        } else {
            range.setRange(-0.5, 10.1 * 1/*coefPresOne*/);
            range.setTickUnit(new NumberTickUnit(2.0));
            if (Pres2.isSelected() && !Pres1.isSelected())
                xyPlot.getRenderer().setSeriesPaint(0, Color.BLUE);
        }


//        String tsEndString = comboDate.getSelectedItem() + " " + textFieldEnd.getText();
//        String tsEndString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime())));
        String tsEndString = comboDate.getSelectedItem() + " " + spinnerEndStr;
        java.sql.Timestamp tsEnd = Timestamp.valueOf(tsEndString);

//        String tsStartString = comboDate.getSelectedItem() + " " + textFieldStart.getText();
//        String tsStartString = comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
        String tsStartString = comboDate.getSelectedItem() + " " + spinnerStartStr;
        java.sql.Timestamp tsStart = Timestamp.valueOf(tsStartString);


//        java.sql.Timestamp ts = Timestamp.valueOf(strDateEnd);


//        Date endDateShow = null;
//        try {
//            endDateShow = SimpleDateFormat.getDateTimeInstance().parse(strDateEnd);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);
//        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, 0) + timeSpan * 1000);
//        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1));
//        dateAxis.setRange(jdsAn2.getXValue(0, 0), ts.getTime());
        dateAxis.setRange(tsStart.getTime(), tsEnd.getTime());


        xyPlot.setDomainAxisLocation(0, AxisLocation.TOP_OR_LEFT);


        int width = scrollGraphs.getWidth() - 30;

        if (!flagAxis) {
            flagAxis = true;
            dateAxis.setVisible(true);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringStart  = dateFormat.format(jdsAn2.getXValue(0, 0));
            String stringEnd  = dateFormat.format(jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1));

        //    labelStart.setText(stringStart);
//            labelEnd.setText(stringEnd);
            comboBox1.addItem(stringStart);
            comboBox2.addItem(stringEnd);
//            spinner1.setValue(stringStart);
//            spinner2.setValue(stringEnd);
         //   textFieldStart.setText(stringStart);
//            textFieldEnd.setText(stringEnd);
            endDisplayString = stringEnd;



//            chPAn2.setPreferredSize(new Dimension(1300, 120));
//            chPAn2.setMaximumSize(new Dimension(1300, 120));
//            chPAn2.setMinimumSize(new Dimension(1300, 120));
//            chPAn2.setMaximumDrawWidth(1300);
            int heightSpeed = 0;
            if (signalType == 0)
                heightSpeed = 130 + 20;
            else heightSpeed = 130;

            chPAn2.setPreferredSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumSize(new Dimension(width, heightSpeed));
            chPAn2.setMinimumSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumDrawWidth(width);

            chPAn2.setMinimumDrawWidth(100);
            chPAn2.setMaximumDrawHeight(heightSpeed);
            chPAn2.setMinimumDrawHeight(10);

        } else {
            dateAxis.setVisible(false);
            int heightSpeed = 0;

            if (signalType == 0)
                heightSpeed = 100 + 20;
            else
                heightSpeed = 100;

//            chPAn2.setPreferredSize(new Dimension(1300, 100));
//            chPAn2.setMaximumSize(new Dimension(1300, 100));
//            chPAn2.setMinimumSize(new Dimension(1300, 100));
//            chPAn2.setMaximumDrawWidth(1300);

            chPAn2.setPreferredSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumSize(new Dimension(width, heightSpeed));
            chPAn2.setMinimumSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumDrawWidth(width);


            chPAn2.setMinimumDrawWidth(100);
            chPAn2.setMaximumDrawHeight(heightSpeed);
            chPAn2.setMinimumDrawHeight(10);
        }

//        range.setAutoRange(false);
////        dateAxis.setAutoRange(false);
//        range.setDefaultAutoRange(new Range(-50.0, 50.0));
////        chPAn2.zoomOutRange(0, 0);
//        range.setFixedAutoRange(100.0);
//        chPAn2.setRangeZoomable(false);


        if (!flagSlider) {

            bigParticle = (long) (Math.floor(jdsAn2.getXValue(0, 0) / 1000000000L) * 1000000000L);
//            int min = (int)((long) jdsAn2.getXValue(0, 0) - bigParticle);
//            int max = (int)((long) jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1) - bigParticle);

            int min = (int)(tsStart.getTime() - bigParticle);
            int max = (int)(tsEnd.getTime() - bigParticle);

            System.out.println("Preset slider max: " + max);

            flagSlider = true;
            graphsSlider.setMinimum(min);
            graphsSlider.setMaximum(max);

        }

        range.setAutoRange(false);
//        range.setFixedAutoRange(10.0);
//        range.setRange(new Range(0.1, 10.1));
//        range.setDefaultAutoRange(new Range(0.1, 10.1));
//        chPAn2.zoomOutRange(0.1, 100.1);
////        range.setAutoRange
//        chPAn2.setHorizontalAxisTrace(true);


        return chPAn2;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

    public Rectangle2D getScreenDataArea() {
//        Rectangle2D dataArea = this.info.getPlotInfo().getDataArea();
//        Insets insets = getInsets();
//        double x = dataArea.getX() * this.scaleX + insets.left;
//        double y = dataArea.getY() * this.scaleY + insets.top;
//        double w = dataArea.getWidth() * this.scaleX;
//        double h = dataArea.getHeight() * this.scaleY;
        double x = scrollGraphs.getX();
        double y = scrollGraphs.getY();
        double w = scrollGraphs.getWidth();
        double h = scrollGraphs.getHeight();

        return new Rectangle2D.Double(x, y, w, h);
    }

    private transient Line2D verticalTraceLineDM;

    private void drawHorizontalAxisTraceDM(Graphics2D g2, int x, ChartPanel chP) {

        Rectangle2D dataArea = getScreenDataArea();


        g2.setXORMode(Color.orange);
        if (((int) dataArea.getMinX() < x) && (x < (int) dataArea.getMaxX())) {


            if (this.verticalTraceLine != null) {
                g2.draw(this.verticalTraceLine);
                this.verticalTraceLine.setLine(x, (int) dataArea.getMinY(), x,
                        (int) dataArea.getMaxY());
            }
            else {
                this.verticalTraceLine = new Line2D.Float(x,
                        (int) dataArea.getMinY(), x, (int) dataArea.getMaxY());
            }
            g2.draw(this.verticalTraceLine);
        }

        // Reset to the default 'overwrite' mode
        g2.setPaintMode();
    }

    //Creating charts

    private void display() {
        JFrame f = new JFrame("JDBCTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDBCXYDataset jds = createDataset();
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Wagon info",
                "Date", "Count", jds);
        f.add(new ChartPanel(chart));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        for (int i = 0; i < jds.getItemCount(); i++) {
            System.out.println(new Date(jds.getX(0, i).longValue()));
        }
    }


    /////////////////////////////////////////////////////////////


    private static JFreeChart createChart(
            IntervalXYDataset paramIntervalXYDataset) {
        JFreeChart localJFreeChart = ChartFactory.createXYBarChart(
                "XYTaskDatasetDemo1", "Resource", false, "Timing",
                paramIntervalXYDataset, PlotOrientation.HORIZONTAL, true,
                false, false);
        localJFreeChart.setBackgroundPaint(Color.white);
        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
        SymbolAxis localSymbolAxis = new SymbolAxis("Series", new String[] {
                "Team A", "Team B", "Team C", "Team D" });
        localSymbolAxis.setGridBandsVisible(false);
        localXYPlot.setDomainAxis(localSymbolAxis);
        XYBarRenderer localXYBarRenderer = (XYBarRenderer) localXYPlot
                .getRenderer();
        localXYBarRenderer.setUseYInterval(true);
        localXYPlot.setRangeAxis(new DateAxis("Timing"));
        ChartUtilities.applyCurrentTheme(localJFreeChart);
        return localJFreeChart;
    }


    private static JFreeChart createChartDigits(IntervalXYDataset paramIntervalXYDataset) {
        JFreeChart localJFreeChart = ChartFactory.createXYBarChart(
                "", "", false, "",
                paramIntervalXYDataset, PlotOrientation.HORIZONTAL, true,
                false, false);
        localJFreeChart.setBackgroundPaint(Color.white);
        localJFreeChart.setBorderPaint(Color.BLUE);
        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
//        localXYPlot.setBackgroundPaint(Color.BLUE);
//        localXYPlot.setOutlinePaint(Color.BLUE);
//        localXYPlot.setRangeGridlinePaint(Color.BLUE);
        SymbolAxis localSymbolAxis = new SymbolAxis("", new String[] {
                "Вибір напрямку", "ВУ",
                "ДАУ-АРШ", "АРШ", "48 пп", "34 пп", "25 пп",
                "20 пп", "18 пп", "8 пп", "Провід Ф7", "6 пп",
                "5 пп", "4 пп", "3 пп", "2 пп", "1 пп",
                "КРП", "   Ввімк.бл.перетв.", "Стоян. гальмо", "Доп.шв. 80 км/г", "Доп.шв. 70 км/г",
                "Доп.шв. 60 км/г", "Доп.шв. 40 км/г", "Доп.шв.0 км/г", "Відсутня част.", "КБ(ПБ)",
                "Зачин. дверей", "Ввімк. ОВТ", "Ввімк. ВАД","Ввімк. ВАХ", "Рух від КАХ",
                "РП відновл.", "ЕПК ввімк.", "Радіост. ввім.", "АЛС ввім.", "АРШ ввім."
    });


//        DateAxis dateAxis = (DateAxis) localXYPlot.getRangeAxis();
//        dateAxis.setDateFormatOverride(new SimpleDateFormat("SSS"));

        localJFreeChart.removeLegend();


        localSymbolAxis.setGridBandsVisible(false);
        localXYPlot.setDomainAxis(localSymbolAxis);
        XYBarRenderer localXYBarRenderer = (XYBarRenderer) localXYPlot.getRenderer();
        localXYBarRenderer.setUseYInterval(true);
        localXYPlot.setRangeAxis(new DateAxis());

        DateAxis dateAxis = (DateAxis) localXYPlot.getRangeAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        ChartPanel chPAn2 = new ChartPanel(localJFreeChart, true);

        chPAn2.setLayout(new GridLayout());

        localJFreeChart.removeLegend();
        localJFreeChart.getTitle().setPosition(RectangleEdge.LEFT);
        localJFreeChart.setTitle("");
        localJFreeChart.getTitle().setFont(new Font("TimesNewRoman", Font.PLAIN, 14));



//        XYPlot xyPlot = (XYPlot) localJFreeChart.getPlot();
//        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
//        range.setRange(-2.1, 1024.1 * coefPresOne);
//        range.setTickUnit(new NumberTickUnit(500.0));

//        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
//        dateAxis.setPositiveArrowVisible(true);
//        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, 0) + timeSpan * 1000);


        ChartUtilities.applyCurrentTheme(localJFreeChart);
        return localJFreeChart;
    }


//    private static JFreeChart createChart2(
//            IntervalXYDataset paramIntervalXYDataset) {
//        JFreeChart localJFreeChart2 = ChartFactory.createXYBarChart(
//                "Pressures", "Value", false, "Timing",
//                paramIntervalXYDataset, PlotOrientation.HORIZONTAL, true,
//                false, false);
//        localJFreeChart2.setBackgroundPaint(Color.white);
//        XYPlot localXYPlot = (XYPlot) localJFreeChart2.getPlot();
//        SymbolAxis localSymbolAxis = new SymbolAxis("Series", new String[] {
//                "Team A", "Team B", "Team C", "Team D" });
//        localSymbolAxis.setGridBandsVisible(false);
//        localXYPlot.setDomainAxis(localSymbolAxis);
//        XYBarRenderer localXYBarRenderer = (XYBarRenderer) localXYPlot
//                .getRenderer();
//        localXYBarRenderer.setUseYInterval(true);
//        localXYPlot.setRangeAxis(new DateAxis("Timing"));
//        ChartUtilities.applyCurrentTheme(localJFreeChart2);
//        return localJFreeChart2;
//    }

    public static JPanel createDemoPanel() {
        ChartPanel chP = new ChartPanel(createChart(createDataset5()));
        chP.setPreferredSize(new Dimension(780, 200));
        return chP;
    }

    private static IntervalXYDataset createDataset5() {
        return new XYTaskDataset(createTasks());
    }

    private static TaskSeriesCollection createTasks() {
        TimeSeries timeseries = new TimeSeries("L&G European Index Trust");
        RegularTimePeriod rtp = new Year(new Date());
        timeseries.add(new TimeSeriesDataItem(rtp, WIDTH));

        TaskSeriesCollection localTaskSeriesCollection = new TaskSeriesCollection();
        TaskSeries localTaskSeries1 = new TaskSeries("Team A");
        localTaskSeries1.add(new Task("T1a", new Second(10, new Minute(5, new Hour(11, new Day())))));
        localTaskSeries1.add(new Task("T1a", new Second(11, new Minute(5, new Hour(11, new Day())))));
        localTaskSeries1.add(new Task("T1a", new Minute(7, new Hour(11, new Day()))));
        localTaskSeries1.add(new Task("T1a", new Minute(8, new Hour(11, new Day()))));

        localTaskSeries1.add(new Task("T1b", new Hour(14, new Day())));
        localTaskSeries1.add(new Task("T1c", new Hour(15, new Day())));
        TaskSeries localTaskSeries2 = new TaskSeries("Team B");
        localTaskSeries2.add(new Task("T2a", new Hour(13, new Day())));
        localTaskSeries2.add(new Task("T2b", new Hour(19, new Day())));
        localTaskSeries2.add(new Task("T2c", new Hour(21, new Day())));
        TaskSeries localTaskSeries3 = new TaskSeries("Team C");
        localTaskSeries3.add(new Task("T3a", new Hour(13, new Day())));
        localTaskSeries3.add(new Task("T3b", new Hour(19, new Day())));
        localTaskSeries3.add(new Task("T3c", new Hour(21, new Day())));
        TaskSeries localTaskSeries4 = new TaskSeries("Team D");
        localTaskSeries4.add(new Task("T4a", new Day()));
        localTaskSeriesCollection.add(localTaskSeries1);
        localTaskSeriesCollection.add(localTaskSeries2);
        localTaskSeriesCollection.add(localTaskSeries3);
        localTaskSeriesCollection.add(localTaskSeries4);
        return localTaskSeriesCollection;
    }

//////////////////////////////////////CREATE TASKS FOR DIGITS////////////////////////////////////////////////////

//    public JPanel createDemoDigits() {
//        ChartPanel chP = new ChartPanel(createChartDigits(createDatasetDigits2()));
//        chP.setPreferredSize(new Dimension(1400, 380));
//        return chP;
//    }

//    private IntervalXYDataset createDatasetDigits2() {
//        return new XYTaskDataset(createTasksDigits());
//    }

//    public static int[] convertToIntArray(Timestamp ts, int row){
//        int[] result = new int[7];
//
////        Timestamp tsOne = (Timestamp) dTableModelDigits.getValueAt(row, 0);
////        System.out.println(tsOne.getTime());
////        System.out.println(tsOne.getNanos());
////        String text = tsOne.toString();
////        System.out.println(text);
//        String millsString = text.substring(20);
//        String secsString = text.substring(17, 19);
//        String minsStrng = text.substring(14, 16);
//        String hoursSting = text.substring(11, 13);
//        String dayString = text.substring(8, 10);
//        String monthString = text.substring(5, 7);
//        String yearString = text.substring(0, 4);
//
//        result[0] = Integer.parseInt(millsString);
//        result[1] = Integer.parseInt(secsString);
//        result[2] = Integer.parseInt(minsStrng);
//        result[3] = Integer.parseInt(hoursSting);
//        result[4] = Integer.parseInt(dayString);
//        result[5] = Integer.parseInt(monthString);
//        result[6] = Integer.parseInt(yearString);
//
//        return result;
//    }

//    private TaskSeriesCollection createTasksDigits() {
////        RegularTimePeriod rtp = new Year(new Date());
////        timeseries.add(new TimeSeriesDataItem(rtp, WIDTH));
//
////        TaskSeriesCollection localTaskSeriesCollection = new TaskSeriesCollection();
////        TaskSeries localTaskSeries1 = new TaskSeries("Dig1");
//
//
//
//        TimeSeries timeseriesDigits = new TimeSeries("L&G European Index Trust");
//        //RegularTimePeriod rtp = new Year(new Date());
////        Millisecond rtp = new Millisecond();
////        timeseriesDigits.add(new TimeSeriesDataItem(rtp, WIDTH));
////        timeseriesDigits.add(new TimeSeriesDataItem(rtp, WIDTH));
//
//
//        TaskSeriesCollection localTaskSeriesCollection = new TaskSeriesCollection();
//        TaskSeries localTaskSeriesDigits1 = new TaskSeries("Dig1");
//        TaskSeries localTaskSeriesDigits2 = new TaskSeries("Dig2");
//        TaskSeries localTaskSeriesDigits3 = new TaskSeries("Dig3");
//        TaskSeries localTaskSeriesDigits4 = new TaskSeries("Dig4");
//        TaskSeries localTaskSeriesDigits5 = new TaskSeries("Dig5");
//        TaskSeries localTaskSeriesDigits6 = new TaskSeries("Dig6");
//        TaskSeries localTaskSeriesDigits7 = new TaskSeries("Dig7");
//        TaskSeries localTaskSeriesDigits8 = new TaskSeries("Dig8");
//        TaskSeries localTaskSeriesDigits9 = new TaskSeries("Dig9");
//        TaskSeries localTaskSeriesDigits10 = new TaskSeries("Dig10");
//        TaskSeries localTaskSeriesDigits11 = new TaskSeries("Dig11");
//        TaskSeries localTaskSeriesDigits12 = new TaskSeries("Dig12");
//        TaskSeries localTaskSeriesDigits13 = new TaskSeries("Dig13");
//        TaskSeries localTaskSeriesDigits14 = new TaskSeries("Dig14");
//        TaskSeries localTaskSeriesDigits15 = new TaskSeries("Dig15");
//        TaskSeries localTaskSeriesDigits16 = new TaskSeries("Dig16");
//        TaskSeries localTaskSeriesDigits17 = new TaskSeries("Dig17");
//        TaskSeries localTaskSeriesDigits18 = new TaskSeries("Dig18");
//        TaskSeries localTaskSeriesDigits19 = new TaskSeries("Dig19");
//        TaskSeries localTaskSeriesDigits20 = new TaskSeries("Dig20");
//        TaskSeries localTaskSeriesDigits21 = new TaskSeries("Dig21");
//        TaskSeries localTaskSeriesDigits22 = new TaskSeries("Dig22");
//        TaskSeries localTaskSeriesDigits23 = new TaskSeries("Dig23");
//        TaskSeries localTaskSeriesDigits24 = new TaskSeries("Dig24");
//        TaskSeries localTaskSeriesDigits25 = new TaskSeries("Dig25");
//        TaskSeries localTaskSeriesDigits26 = new TaskSeries("Dig26");
//        TaskSeries localTaskSeriesDigits27 = new TaskSeries("Dig27");
//        TaskSeries localTaskSeriesDigits28 = new TaskSeries("Dig28");
//        TaskSeries localTaskSeriesDigits29 = new TaskSeries("Dig29");
//        TaskSeries localTaskSeriesDigits30 = new TaskSeries("Dig30");
//        TaskSeries localTaskSeriesDigits31 = new TaskSeries("Dig31");
//        TaskSeries localTaskSeriesDigits32 = new TaskSeries("Dig32");
//        TaskSeries localTaskSeriesDigits33 = new TaskSeries("Dig33");
//        TaskSeries localTaskSeriesDigits34 = new TaskSeries("Dig34");
//        TaskSeries localTaskSeriesDigits35 = new TaskSeries("Dig35");
//        TaskSeries localTaskSeriesDigits36 = new TaskSeries("Dig36");
//        TaskSeries localTaskSeriesDigits37 = new TaskSeries("Dig37");

//        System.out.println("Row count = " + dTableModelDigits.getRowCount());

////        for (int i = 0; i < dTableModelDigits.getRowCount(); i++) {
////
////            boolean dig1 = (Boolean) dTableModelDigits.getValueAt(i, 37);
////            boolean dig2 = (Boolean) dTableModelDigits.getValueAt(i, 36);
////            boolean dig3 = (Boolean) dTableModelDigits.getValueAt(i, 35);
////            boolean dig4 = (Boolean) dTableModelDigits.getValueAt(i, 34);
////            boolean dig5 = (Boolean) dTableModelDigits.getValueAt(i, 33);
////            boolean dig6 = (Boolean) dTableModelDigits.getValueAt(i, 32);
////            boolean dig7 = (Boolean) dTableModelDigits.getValueAt(i, 31);
////            boolean dig8 = (Boolean) dTableModelDigits.getValueAt(i, 30);
////            boolean dig9 = (Boolean) dTableModelDigits.getValueAt(i, 29);
////            boolean dig10 = (Boolean) dTableModelDigits.getValueAt(i, 28);
////            boolean dig11 = (Boolean) dTableModelDigits.getValueAt(i, 27);
////            boolean dig12 = (Boolean) dTableModelDigits.getValueAt(i, 26);
////            boolean dig13 = (Boolean) dTableModelDigits.getValueAt(i, 25);
////            boolean dig14 = (Boolean) dTableModelDigits.getValueAt(i, 24);
////            boolean dig15 = (Boolean) dTableModelDigits.getValueAt(i, 23);
////            boolean dig16 = (Boolean) dTableModelDigits.getValueAt(i, 22);
////            boolean dig17 = (Boolean) dTableModelDigits.getValueAt(i, 21);
////            boolean dig18 = (Boolean) dTableModelDigits.getValueAt(i, 20);
////            boolean dig19 = (Boolean) dTableModelDigits.getValueAt(i, 19);
////            boolean dig20 = (Boolean) dTableModelDigits.getValueAt(i, 18);
////            boolean dig21 = (Boolean) dTableModelDigits.getValueAt(i, 17);
////            boolean dig22 = (Boolean) dTableModelDigits.getValueAt(i, 16);
////            boolean dig23 = (Boolean) dTableModelDigits.getValueAt(i, 15);
////            boolean dig24 = (Boolean) dTableModelDigits.getValueAt(i, 14);
////            boolean dig25 = (Boolean) dTableModelDigits.getValueAt(i, 13);
////            boolean dig26 = (Boolean) dTableModelDigits.getValueAt(i, 12);
////            boolean dig27 = (Boolean) dTableModelDigits.getValueAt(i, 11);
////            boolean dig28 = (Boolean) dTableModelDigits.getValueAt(i, 10);
////            boolean dig29 = (Boolean) dTableModelDigits.getValueAt(i, 9);
////            boolean dig30 = (Boolean) dTableModelDigits.getValueAt(i, 8);
////            boolean dig31 = (Boolean) dTableModelDigits.getValueAt(i, 7);
////            boolean dig32 = (Boolean) dTableModelDigits.getValueAt(i, 6);
////            boolean dig33 = (Boolean) dTableModelDigits.getValueAt(i, 5);
////            boolean dig34 = (Boolean) dTableModelDigits.getValueAt(i, 4);
////            boolean dig35 = (Boolean) dTableModelDigits.getValueAt(i, 3);
////            boolean dig36 = (Boolean) dTableModelDigits.getValueAt(i, 2);
////            boolean dig37 = (Boolean) dTableModelDigits.getValueAt(i, 1);
////
////            Timestamp tsOne = (Timestamp) dTableModelDigits.getValueAt(i, 0);
////            System.out.println("Some text in Task Series");
//            int[] digitTS = convertToIntArray(tsOne, i);
////            System.out.println("digitTS length = " + digitTS.length);
////            System.out.println(digitTS[0]);
////            System.out.println(digitTS[1]);
////            System.out.println(digitTS[2]);
////            System.out.println(digitTS[3]);
//            long mills = tsOne.getTime();
//
//            if (dig1){
////                System.out.println("Bool = true");
////                localTaskSeriesDigits1.add(new Task("Dig1", new Millisecond(digitTS[0], new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day()))))));
////                localTaskSeriesDigits1.add(new Task("Dig1", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day(digitTS[4], digitTS[5], digitTS[6]))))));
//
////                localTaskSeriesDigits1.add(new Task("Dig1", new Millisecond(digitTS[0] * 1, new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day(digitTS[4], digitTS[5], digitTS[6])))))));
//
////                localTaskSeriesDigits1.add(new Task("Dig1", new Date(digitTS[0]), new Date(digitTS[0] + 100)));
//                localTaskSeriesDigits1.add(new Task("Dig1", new Date(mills), new Date(mills + 100)));
//
//
////                System.out.println(digitTS[0]);
////                System.out.println(new Millisecond(digitTS[0] * 1, new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day(digitTS[4], digitTS[5], digitTS[6]))))));
//
//
//            }
//
//            if (dig2){
////                localTaskSeriesDigits2.add(new Task("Dig2", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits2.add(new Task("Dig2", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig3){
////                localTaskSeriesDigits3.add(new Task("Dig3", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits3.add(new Task("Dig3", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig4){
////                localTaskSeriesDigits4.add(new Task("Dig4", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits4.add(new Task("Dig4", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig5){
////                localTaskSeriesDigits5.add(new Task("Dig5", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits5.add(new Task("Dig5", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig6){
////                localTaskSeriesDigits6.add(new Task("Dig6", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits6.add(new Task("Dig6", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig7){
////                localTaskSeriesDigits7.add(new Task("Dig7", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits7.add(new Task("Dig7", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig8){
////                localTaskSeriesDigits8.add(new Task("Dig8", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits8.add(new Task("Dig8", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig9){
////                localTaskSeriesDigits9.add(new Task("Dig9", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits9.add(new Task("Dig9", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig10){
////                localTaskSeriesDigits10.add(new Task("Dig10", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits10.add(new Task("Dig10", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig11){
////                localTaskSeriesDigits11.add(new Task("Dig11", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits11.add(new Task("Dig11", new Date(mills), new Date(mills + 100)));
//            }
//
//
//            if (dig12){
////                localTaskSeriesDigits12.add(new Task("Dig12", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits12.add(new Task("Dig12", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig13){
////                localTaskSeriesDigits13.add(new Task("Dig13", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits13.add(new Task("Dig13", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig14){
////                localTaskSeriesDigits14.add(new Task("Dig14", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits14.add(new Task("Dig14", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig15){
////                localTaskSeriesDigits15.add(new Task("Dig15", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits15.add(new Task("Dig15", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig16){
////                localTaskSeriesDigits16.add(new Task("Dig16", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits16.add(new Task("Dig16", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig17){
////                localTaskSeriesDigits17.add(new Task("Dig17", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits17.add(new Task("Dig17", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig18){
////                localTaskSeriesDigits18.add(new Task("Dig18", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits18.add(new Task("Dig18", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig19){
////                localTaskSeriesDigits19.add(new Task("Dig19", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits19.add(new Task("Dig19", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig20){
////                localTaskSeriesDigits20.add(new Task("Dig20", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits20.add(new Task("Dig20", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig21){
////                localTaskSeriesDigits21.add(new Task("Dig21", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits21.add(new Task("Dig21", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig22){
////                localTaskSeriesDigits22.add(new Task("Dig22", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits22.add(new Task("Dig22", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig23){
////                localTaskSeriesDigits23.add(new Task("Dig23", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits23.add(new Task("Dig23", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig24){
////                localTaskSeriesDigits24.add(new Task("Dig24", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits24.add(new Task("Dig24", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig25){
////                localTaskSeriesDigits25.add(new Task("Dig25", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits25.add(new Task("Dig25", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig26){
////                localTaskSeriesDigits26.add(new Task("Dig26", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits26.add(new Task("Dig26", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig27){
////                localTaskSeriesDigits27.add(new Task("Dig27", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits27.add(new Task("Dig27", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig28){
////                localTaskSeriesDigits28.add(new Task("Dig28", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits28.add(new Task("Dig28", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig29){
////                localTaskSeriesDigits29.add(new Task("Dig29", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits29.add(new Task("Dig29", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig30){
////                localTaskSeriesDigits30.add(new Task("Dig30", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits30.add(new Task("Dig30", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig31){
////                localTaskSeriesDigits31.add(new Task("Dig31", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits31.add(new Task("Dig31", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig32){
////                localTaskSeriesDigits32.add(new Task("Dig32", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits32.add(new Task("Dig32", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig33){
////                localTaskSeriesDigits33.add(new Task("Dig33", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits33.add(new Task("Dig33", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig34){
////                localTaskSeriesDigits34.add(new Task("Dig34", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits34.add(new Task("Dig34", new Date(mills), new Date(mills + 100)));
//            }
//            if (dig35){
////                localTaskSeriesDigits35.add(new Task("Dig35", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits35.add(new Task("Dig35", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig36){
////                localTaskSeriesDigits36.add(new Task("Dig36", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits36.add(new Task("Dig36", new Date(mills), new Date(mills + 100)));
//            }
//
//            if (dig37){
////                localTaskSeriesDigits37.add(new Task("Dig37", new Second(digitTS[1], new Minute(digitTS[2],
////                        new Hour(digitTS[3], new Day())))));
//                localTaskSeriesDigits37.add(new Task("Dig37", new Date(mills), new Date(mills + 100)));
//            }
//
//        }
//
//        if (Dig1.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits1);
//        if (Dig2.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits2);
//        if (Dig3.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits3);
//        if (Dig4.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits4);
//        if (Dig5.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits5);
//        if (Dig6.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits6);
//        if (Dig7.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits7);
//        if (Dig8.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits8);
//        if (Dig9.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits9);
//        if (Dig10.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits10);
//        if (Dig11.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits11);
//        if (Dig12.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits12);
//        if (Dig13.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits13);
//        if (Dig14.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits14);
//        if (Dig15.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits15);
//        if (Dig16.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits16);
//        if (Dig17.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits17);
//        if (Dig18.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits18);
//        if (Dig19.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits19);
//        if (Dig20.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits20);
//        if (Dig21.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits21);
//        if (Dig22.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits22);
//        if (Dig23.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits23);
//        if (Dig24.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits24);
//        if (Dig25.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits25);
//        if (Dig26.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits26);
//        if (Dig27.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits27);
//        if (Dig28.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits28);
//        if (Dig29.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits29);
//        if (Dig30.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits30);
//        if (Dig31.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits31);
//        if (Dig32.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits32);
//        if (Dig33.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits33);
//        if (Dig34.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits34);
//        if (Dig35.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits35);
//        if (Dig36.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits36);
//        if (Dig37.isSelected())
//            localTaskSeriesCollection.add(localTaskSeriesDigits37);
//
//        return localTaskSeriesCollection;
//    }


////////////////////////////////NEW TASK DATASET FOR DIGITS//////////////////////////////////////////////////////
////////**********************************************************************************************///////////

//    private static TaskSeriesCollection createTasksDigitsThree(String name, int number) {
//        TaskSeriesCollection localTaskSeriesCollection = new TaskSeriesCollection();
//        TaskSeries localTaskSeriesDigits1 = new TaskSeries(name);
//        XYSeries series = new XYSeries("");
//
//        for (int i = 0; i < dTableModelDigits.getRowCount(); i++) {
//            boolean dig3 = (Boolean) dTableModelDigits.getValueAt(i, number);
//
//            Timestamp tsOne = (Timestamp) dTableModelDigits.getValueAt(i, 0);
//            int[] digitTS = convertToIntArray(tsOne, i);
////            System.out.println("digitTS length = " + digitTS.length);
//
//            long mills = tsOne.getTime();
//
//            if (dig3){
//                series.add(mills, 40);
////                localTaskSeriesDigits1.add(new Task("Dig1", new Date(mills), new Date(mills + 100)));
//            }
//        }
//        localTaskSeriesCollection.add(localTaskSeriesDigits1);
//        return localTaskSeriesCollection;
//    }
//
//    private static IntervalXYDataset createDatasetDigitsThree(String name, int number) {
//        return new XYTaskDataset(createTasksDigitsThree(name, number));
//    }


//    private static TaskSeriesCollection createTasksDigitsTwo(String name, int number) {
//        TaskSeriesCollection localTaskSeriesCollection = new TaskSeriesCollection();
//        TaskSeries localTaskSeriesDigits1 = new TaskSeries(name);
//
//        for (int i = 0; i < dTableModelDigits.getRowCount(); i++) {
//            boolean dig1 = (Boolean) dTableModelDigits.getValueAt(i, number);
//
//            Timestamp tsOne = (Timestamp) dTableModelDigits.getValueAt(i, 0);
//            int[] digitTS = convertToIntArray(tsOne, i);
////            System.out.println("digitTS length = " + digitTS.length);
//
//            long mills = tsOne.getTime();
//
//            if (dig1){
//                localTaskSeriesDigits1.add(new Task("Dig1", new Date(mills), new Date(mills + 100)));
//            }
//        }
//        localTaskSeriesCollection.add(localTaskSeriesDigits1);
//        return localTaskSeriesCollection;
//    }

//    private static IntervalXYDataset createDatasetDigitsTwo(String name, int number) {
//        return new XYTaskDataset(createTasksDigitsTwo(name, number));
//    }

//    public static JPanel createDemoDigitsTwo() {
//        ChartPanel chP = new ChartPanel(createChartDigitsTwo(createDatasetDigitsTwo()));
//        chP.setPreferredSize(new Dimension(1300, 20));
//        return chP;
//    }

    private static JFreeChart createChartDigitsTwo(IntervalXYDataset paramIntervalXYDataset, String name) {
        JFreeChart localJFreeChart = ChartFactory.createXYBarChart(
                "", "Resource", false, "",
                paramIntervalXYDataset, PlotOrientation.HORIZONTAL, true,
                false, false);
        localJFreeChart.setBackgroundPaint(Color.white);
        XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
        SymbolAxis localSymbolAxis = new SymbolAxis("", new String[] {name});

        localSymbolAxis.setGridBandsVisible(false);
        localXYPlot.setDomainAxis(localSymbolAxis);
        XYBarRenderer localXYBarRenderer = (XYBarRenderer) localXYPlot.getRenderer();
        localXYBarRenderer.setUseYInterval(true);
        localXYPlot.setRangeAxis(new DateAxis());

        DateAxis dateAxis = (DateAxis) localXYPlot.getRangeAxis();
        //dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS"));
        dateAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
//        dateAxis.setTickUnit(new DateTickUnit(DateTickUnit.MILLISECOND, 5000));

        ChartUtilities.applyCurrentTheme(localJFreeChart);
        return localJFreeChart;
    }

/////////////////////********************************************************************////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////

    private JDBCXYDataset createDataset() {

        Statement st;

        try{
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:derby:wagons");
//            Connection conn = databaseOperations.dbConnection;
//            Statement st = conn.createStatement();
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select wid, speed, pressureinbrakeline, pressureinpumpingmain from app.maintable");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

/////////////////////////CREATING DATASET FOR PRESSURES CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDataset3() {

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select APP.PRESSURE.TIMESTAMP, pressureone, pressuretwo from app.pressure");
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

/////////////////////CREATING DATASET FOR SPEED CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetSpeed(String spinnerStartStr, String spinnerEndStr) {

//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;
//        String selectAllSpeed = "SELECT * FROM APP.SPEED OFFSET " + ofSpeed + " ROWS FETCH NEXT " + rwsSpeed + " ROWS ONLY";

        Statement st;
        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);


//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, speed from app.speed" +
//                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'");
//            } else {
//                jds.executeQuery("select timestamp, speed from app.speed" +
//                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'" +
//                        " and  MOD(cast(SECOND (APP.SPEED.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, speed from app.speed" +
//                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, speed from app.speed" +
//                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.SPEED.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

            if (decimation == 0) {
                jds.executeQuery("select timestamp, speed from app.speed" +
                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, speed from app.speed" +
                        " where APP.SPEED.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.SPEED.TIMESTAMP) as INT), " + decimation + ") = 0");
            }


            st.close();
            conn.close();

            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed0(String spinnerStartStr, String spinnerEndStr) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;
        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'" +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }


            if (decimation == 0) {
                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
            }


//            jds.executeQuery("select timestamp, case when dig13 then 0 else null end from app.digits " +
//                    " where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'");// +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 10) = 0");// +


//            "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            //conn = DriverManager.getConnection("jdbc:derby:wagons;shutdown=true");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed40(String spinnerStartStr, String spinnerEndStr) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;
        Statement st;
        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig14 then 40 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig14 then 40 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

            if (decimation == 0) {
                jds.executeQuery("select timestamp, case when dig14 then 40 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, case when dig14 then 40 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
            }


//            jds.executeQuery("select timestamp, case when dig14 then 40 else null end from app.digits " +
//                    " where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'");// +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 10) = 0");// +

//                    "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            //conn = DriverManager.getConnection("jdbc:derby:wagons;shutdown=true");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed60(String spinnerStartStr, String spinnerEndStr) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;
        Statement st;
        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig15 then 60 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig15 then 60 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

            if (decimation == 0) {
                jds.executeQuery("select timestamp, case when dig15 then 60 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, case when dig15 then 60 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
            }


//            jds.executeQuery("select timestamp, case when dig15 then 60 else null end from app.digits " +
//                    " where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'");// +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 10) = 0");// +


//            "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed70(String spinnerStartStr, String spinnerEndStr) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig16 then 70 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig16 then 70 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");

            if (decimation == 0) {
                jds.executeQuery("select timestamp, case when dig16 then 70 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, case when dig16 then 70 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");

            }

//            jds.executeQuery("select timestamp, case when dig16 then 70 else null end from app.digits " +
//                    " where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'");// +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 10) = 0");// +


//            "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed80(String spinnerStartStr, String spinnerEndStr) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;
//        int ofSpeed = ofst * 4;
//        int rwsSpeed = rws * 4;

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

//            if (decimation == 0) {
//                jds.executeQuery("select timestamp, case when dig17 then 80 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");
//            } else {
//                jds.executeQuery("select timestamp, case when dig17 then 80 else null end from app.digits " +
//                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
//            }

            if (decimation == 0) {
                jds.executeQuery("select timestamp, case when dig17 then 80 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");
            } else {
                jds.executeQuery("select timestamp, case when dig17 then 80 else null end from app.digits " +
                        " where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
            }



//            jds.executeQuery("select timestamp, case when dig17 then 80 else null end from app.digits " +
//                    " where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'");// +
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 10) = 0");// +

//            "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            st.close();
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }


    private JDBCXYDataset createDatasetSpeed(String spinnerStartStr, String spinnerEndStr, Connection conn) {

        Statement st;
        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, speed from app.alldata" +
                        " WHERE APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL) ORDER BY APP.ALLDATA.TIMESTAMPCOL");
            } else {
                jds.executeQuery("select timestampcol, speed from app.alldata" +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" + //);// +
//                        " and MOD((ROW_NUMBER() OVER ()), " + decimation + ") = 0" +

                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL) ORDER BY APP.ALLDATA.TIMESTAMPCOL");
            }

            st.close();
//            conn.close();
//            int count = jds.getItemCount();
//            for (int kk = 0; kk < count; kk++) {
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                String stringEnd = dateFormat.format(jds.getXValue(0, kk));
//                System.out.println("String Start = " + stringEnd);
//            }

            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed0(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        Statement st;

        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, case when dig13 then 0 else null end from app.alldata " +
                        " where APP.alldata.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            } else {
                jds.executeQuery("select timestampcol, case when dig13 then 0 else null end from app.alldata " +
                        " where APP.alldata.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");

            }
            st.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed40(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        Statement st;
        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, case when dig14 then 40 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            } else {
                jds.executeQuery("select timestampcol, case when dig14 then 40 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            }
            st.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed60(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        Statement st;
        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, case when dig15 then 60 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            } else {
                jds.executeQuery("select timestampcol, case when dig15 then 60 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            }
            st.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed70(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        Statement st;

        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, case when dig16 then 70 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            } else {
                jds.executeQuery("select timestampcol, case when dig16 then 70 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            }
            st.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
    private JDBCXYDataset createDatasetSpeed80(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        Statement st;

        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);

            st.setFetchSize(fetchSize);

            if (decimation == 0) {
                jds.executeQuery("select timestampcol, case when dig17 then 80 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            } else {
                jds.executeQuery("select timestampcol, case when dig17 then 80 else null end from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " and  MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                        " AND (APP.ALLDATA.SPEED <> -55.5 OR APP.ALLDATA.SPEED IS NULL)");
            }
            st.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    private JDBCXYDataset createDatasetPressOne(String spinnerStartStr, String spinnerEndStr, Connection conn) {
        coefPresOne = coeffPresOne;
        Statement st;

        try{
            st = conn.createStatement();
            st.setFetchSize(fetchSize);
            double coefOneTempOne = coeffPresOnePmax / ((coeffPresOneUmax - coeffPresOneUmin)*0.641026);
            double coefOneTempTwo = coeffPresOneUmin * 0.641026;

            double coefTwoTempOne = coeffPresTwoPmax / ((coeffPresTwoUmax - coeffPresTwoUmin)*0.641026);
            double coefTwoTempTwo = coeffPresTwoUmin * 0.641026;



            JDBCXYDataset jds = new JDBCXYDataset(conn);
            if (Pres1.isSelected() && Pres2.isSelected())

                jds.executeQuery("select timestampcol, case when " +
                        "(pressone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" > 0 then " +
                        "(pressone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" else case when pressone < 90 then 0 else pressone end end" /*+ coefPresOne*/ +
                        " , case when " +
                        "(presstwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" > 0 then " +
                        "(presstwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" else case when presstwo < 90 then 0 else presstwo end end" /*+ coefPresOne*/ +
                        " from app.alldata " +
                        " where APP.alldata.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.PRESSONE <> -55 OR APP.ALLDATA.PRESSONE IS NULL) ORDER BY APP.ALLDATA.TIMESTAMPCOL");// +

            else if (Pres1.isSelected() && !Pres2.isSelected())
                jds.executeQuery("select timestampcol, case when " +
                        "(pressone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" > 0 then " +
                        "(pressone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" else case when pressone < 90 then 0 else pressone end end" /*+ coefPresOne*/ +
                        " from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.PRESSONE <> -55 OR APP.ALLDATA.PRESSONE IS NULL) ORDER BY APP.ALLDATA.TIMESTAMPCOL"); //+

            else
                jds.executeQuery("select timestampcol, case when " +
                        "(presstwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" > 0 then " +
                        "(presstwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" else case when presstwo < 90 then 0 else presstwo end end" /*+ coefPresOne*/ +
                        " from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                        " AND (APP.ALLDATA.PRESSONE <> -55 OR APP.ALLDATA.PRESSONE IS NULL) ORDER BY APP.ALLDATA.TIMESTAMPCOL");// +

            st.close();
//            jds.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    private JDBCXYDataset createDatasetDigitalsAll(String spinnerStartStr, String spinnerEndStr, int digits, Connection conn) {
        Statement st;

        try{
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            System.out.println("Start query ALL..." );
            String selectDigits = buildDigitStartQuery(digits);

            if (decimation == 0) {
                jds.executeQuery(selectDigits +
                        "case when dig37 then 0 else 0 end " +
                        "from app.alldata " +
                        "where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");

            } else {
                jds.executeQuery(
                        selectDigits +
                                "case when dig37 then 0 else 0 end " +
                                "from app.alldata " +
                                "where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +
                                " and  (MOD(cast(SECOND (APP.ALLDATA.TIMESTAMPCOL) as INT), " + decimation + ") = 0" +
                                " OR APP.ALLDATA.SPEED IS NULL)");
            }

            st.close();
            jds.close();
//            conn.close();
            System.out.println("Stop query...");
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    //////////////////////CREATING DATASETS FOR DIGITAL SIGNALS////////////////////////////////////

    private JDBCXYDataset createDatasetDigitals(int ofst, int rws, String signal, int value) {
        int ofDigits = ofst * 10;
        int rwsDigits = rws * 10;

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";


            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = null;
            jds = new JDBCXYDataset(conn);
            System.out.println("Start query..." + value);
//            st.setFetchSize(fetchSize);
            jds.executeQuery("select timestamp, case when " + signal + " then " + value + " else null end from app.digits OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            st.close();
            jds.close();
            conn.close();
//            DriverManager.getConnection("jdbc:derby:wagons;shutdown=true");
            System.out.println("Stop query..." + value);
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    private String buildDigitStartQuery(int digits) {
        String app = null;
        StringBuilder result = new StringBuilder();
        result.append("select timestampcol, ");
//        digits++;
//        digits++;

        if (Dig1.isSelected()) {
            app = "case when dig1 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig2.isSelected()) {
            app = "case when dig2 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig3.isSelected()) {
            app = "case when dig3 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig4.isSelected()) {
            app = "case when dig4 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig5.isSelected()) {
            app = "case when dig5 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig6.isSelected()) {
            app = "case when dig6 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig7.isSelected()) {
            app = "case when dig7 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig8.isSelected()) {
            app = "case when dig8 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig9.isSelected()) {
            app = "case when dig9 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig10.isSelected()) {
            app = "case when dig10 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }

        if (Dig11.isSelected()) {
            app = "case when dig11 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig12.isSelected()) {
            app = "case when dig12 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig13.isSelected()) {
            app = "case when dig13 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig14.isSelected()) {
            app = "case when dig14 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig15.isSelected()) {
            app = "case when dig15 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig16.isSelected()) {
            app = "case when dig16 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig17.isSelected()) {
            app = "case when dig17 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig18.isSelected()) {
            app = "case when dig18 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig19.isSelected()) {
            app = "case when dig19 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig20.isSelected()) {
            app = "case when dig20 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }

        if (Dig21.isSelected()) {
            app = "case when dig21 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig22.isSelected()) {
            app = "case when dig22 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig23.isSelected()) {
            app = "case when dig23 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig24.isSelected()) {
            app = "case when dig24 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig25.isSelected()) {
            app = "case when dig25 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig26.isSelected()) {
            app = "case when dig26 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig27.isSelected()) {
            app = "case when dig27 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig28.isSelected()) {
            app = "case when dig28 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig29.isSelected()) {
            app = "case when dig29 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig30.isSelected()) {
            app = "case when dig30 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }

        if (Dig31.isSelected()) {
            app = "case when dig31 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig32.isSelected()) {
            app = "case when dig32 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig33.isSelected()) {
            app = "case when dig33 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig34.isSelected()) {
            app = "case when dig34 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig35.isSelected()) {
            app = "case when dig35 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig36.isSelected()) {
            app = "case when dig36 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }
        if (Dig37.isSelected()) {
            app = "case when dig37 then " + digits + " else null end, ";
            result.append(app);
            digits--;
        }


        return result.toString();
    }

    private JDBCXYDataset createDatasetDigitalsAll(String spinnerStartStr, String spinnerEndStr, int digits) {
//        int ofDigits = ofst * 10;
//        int rwsDigits = rws * 10;

//        ofDigits = 0;

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";


            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            System.out.println("Start query ALL..." );
            String selectDigits = buildDigitStartQuery(digits);
//            st.setFetchSize(fetchSize);

//            StringBuilder queryBuilderOne = new StringBuilder();
//            queryBuilderOne.append("select timestamp, ");
//            if (Dig1.isSelected()) queryBuilderOne.append("case when dig1 then 37 else null end, ");
//            if (Dig2.isSelected()) queryBuilderOne.append("case when dig2 then 36 else null end, ");
//            if (Dig3.isSelected()) queryBuilderOne.append("case when dig3 then 35 else null end, ");
//            if (Dig4.isSelected()) queryBuilderOne.append("case when dig4 then 34 else null end, ");


            if (decimation == 0) {
                jds.executeQuery(selectDigits +
//                        "select timestamp, " +
//                        "case when dig1 then 37 else null end, " +
//                        "case when dig2 then 36 else null end, " +
//                        "case when dig3 then 35 else null end, " +
//                        "case when dig4 then 34 else null end, " +
//                        "case when dig5 then 33 else null end, " +
//                        "case when dig6 then 32 else null end, " +
//                        "case when dig7 then 31 else null end, " +
//                        "case when dig8 then 30 else null end, " +
//                        "case when dig9 then 29 else null end, " +
//                        "case when dig10 then 28 else null end, " +
//                        "case when dig11 then 27 else null end, " +
//                        "case when dig12 then 26 else null end, " +
//                        "case when dig13 then 25 else null end, " +
//                        "case when dig14 then 24 else null end, " +
//                        "case when dig15 then 23 else null end, " +
//                        "case when dig16 then 22 else null end, " +
//                        "case when dig17 then 21 else null end, " +
//                        "case when dig18 then 20 else null end, " +
//                        "case when dig19 then 19 else null end, " +
//                        "case when dig20 then 18 else null end, " +
//                        "case when dig21 then 17 else null end, " +
//                        "case when dig22 then 16 else null end, " +
//                        "case when dig23 then 15 else null end, " +
//                        "case when dig24 then 14 else null end, " +
//                        "case when dig25 then 13 else null end, " +
//                        "case when dig26 then 12 else null end, " +
//                        "case when dig27 then 11 else null end, " +
//                        "case when dig28 then 10 else null end, " +
//                        "case when dig29 then 9 else null end, " +
//                        "case when dig30 then 8 else null end, " +
//                        "case when dig31 then 7 else null end, " +
//                        "case when dig32 then 6 else null end, " +
//                        "case when dig33 then 5 else null end, " +
//                        "case when dig34 then 4 else null end, " +
//                        "case when dig35 then 3 else null end, " +
//                        "case when dig36 then 2 else null end, " +
//                        "case when dig37 then 1 else null end, " +
                        "case when dig37 then 0 else 0 end " +
                        "from app.digits " +
                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");

//                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'");

//                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'");
                // +
//
//                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 1) = 0");// +
                } else {
                jds.executeQuery(
                        selectDigits +
//                        "select timestamp, " +
//                        "case when dig1 then 370 else null end, " +
//                        "case when dig2 then 360 else null end, " +
//                        "case when dig3 then 350 else null end, " +
//                        "case when dig4 then 340 else null end, " +
//                        "case when dig5 then 330 else null end, " +
//                        "case when dig6 then 320 else null end, " +
//                        "case when dig7 then 310 else null end, " +
//                        "case when dig8 then 300 else null end, " +
//                        "case when dig9 then 290 else null end, " +
//                        "case when dig10 then 280 else null end, " +
//                        "case when dig11 then 270 else null end, " +
//                        "case when dig12 then 260 else null end, " +
//                        "case when dig13 then 250 else null end, " +
//                        "case when dig14 then 240 else null end, " +
//                        "case when dig15 then 230 else null end, " +
//                        "case when dig16 then 220 else null end, " +
//                        "case when dig17 then 210 else null end, " +
//                        "case when dig18 then 200 else null end, " +
//                        "case when dig19 then 190 else null end, " +
//                        "case when dig20 then 180 else null end, " +
//                        "case when dig21 then 170 else null end, " +
//                        "case when dig22 then 160 else null end, " +
//                        "case when dig23 then 150 else null end, " +
//                        "case when dig24 then 140 else null end, " +
//                        "case when dig25 then 130 else null end, " +
//                        "case when dig26 then 120 else null end, " +
//                        "case when dig27 then 110 else null end, " +
//                        "case when dig28 then 100 else null end, " +
//                        "case when dig29 then 90 else null end, " +
//                        "case when dig30 then 80 else null end, " +
//                        "case when dig31 then 70 else null end, " +
//                        "case when dig32 then 60 else null end, " +
//                        "case when dig33 then 50 else null end, " +
//                        "case when dig34 then 40 else null end, " +
//                        "case when dig35 then 30 else null end, " +
//                        "case when dig36 then 20 else null end, " +
//                        "case when dig37 then 10 else null end, " +
                        "case when dig37 then 0 else 0 end " +
                        "from app.digits " +
                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'" +

//                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime()))) + "' and '" + comboDate.getSelectedItem() + " " + String.valueOf(new Time((((Date)spinnerEnd.getValue()).getTime()))) + "'" +
//                        "where APP.DIGITS.TIMESTAMP between '" + comboDate.getSelectedItem() + " " + textFieldStart.getText() + "' and '" + comboDate.getSelectedItem() + " " + textFieldEnd.getText() + "'" +

                        " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), " + decimation + ") = 0");
            }

//            jds.executeQuery("select timestamp, " +
//                    "case when dig1 then 370 else null end, " +
//                    "case when dig2 then 360 else null end, " +
//                    "case when dig3 then 350 else null end, " +
//                    "case when dig4 then 340 else null end, " +
//                    "case when dig5 then 330 else null end, " +
//                    "case when dig6 then 320 else null end, " +
//                    "case when dig7 then 310 else null end, " +
//                    "case when dig8 then 300 else null end, " +
//                    "case when dig9 then 290 else null end, " +
//                    "case when dig10 then 280 else null end, " +
//                    "case when dig11 then 270 else null end, " +
//                    "case when dig12 then 260 else null end, " +
//                    "case when dig13 then 250 else null end, " +
//                    "case when dig14 then 240 else null end, " +
//                    "case when dig15 then 230 else null end, " +
//                    "case when dig16 then 220 else null end, " +
//                    "case when dig17 then 210 else null end, " +
//                    "case when dig18 then 200 else null end, " +
//                    "case when dig19 then 190 else null end, " +
//                    "case when dig20 then 180 else null end, " +
//                    "case when dig21 then 170 else null end, " +
//                    "case when dig22 then 160 else null end, " +
//                    "case when dig23 then 150 else null end, " +
//                    "case when dig24 then 140 else null end, " +
//                    "case when dig25 then 130 else null end, " +
//                    "case when dig26 then 120 else null end, " +
//                    "case when dig27 then 110 else null end, " +
//                    "case when dig28 then 100 else null end, " +
//                    "case when dig29 then 90 else null end, " +
//                    "case when dig30 then 80 else null end, " +
//                    "case when dig31 then 70 else null end, " +
//                    "case when dig32 then 60 else null end, " +
//                    "case when dig33 then 50 else null end, " +
//                    "case when dig34 then 40 else null end, " +
//                    "case when dig35 then 30 else null end, " +
//                    "case when dig36 then 20 else null end, " +
//                    "case when dig37 then 10 else null end " +
//                    "from app.digits " +
////                    "where ROW_NUMBER() = 0 " +
////                    "where MOD(MINUTE(APP.DIGITS.TIMESTAMP), 10) = 0 " +
//
////                    "where MOD(ROW_NUMBER(), 20) = 0 " +
////                    "where SECOND (APP.DIGITS.TIMESTAMP) - FLOOR (SECOND(APP.DIGITS.TIMESTAMP)) = 0.0 " +
////                    "OR SECOND (APP.DIGITS.TIMESTAMP) - FLOOR (SECOND(APP.DIGITS.TIMESTAMP)) = 0.5 " +
////                    "where APP.DIGITS.TIMESTAMP between '2016-12-21 15:23:30.0' and '2016-12-22 19:33:30.0' " +
//                    "where APP.DIGITS.TIMESTAMP between '" + textFieldStart.getText() + "' and '" + textFieldEnd.getText() + "'" +
//
//                    " and  MOD(cast(SECOND (APP.DIGITS.TIMESTAMP) as INT), 1) = 0");// +
//                    "and  SECOND (APP.DIGITS.TIMESTAMP) - FLOOR (SECOND(APP.DIGITS.TIMESTAMP)) = 0.0");// +
//                    "OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY");
            st.close();
            jds.close();
            conn.close();
//            DriverManager.getConnection("jdbc:derby:wagons;shutdown=true");
            System.out.println("Stop query...");
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }


    /////////////////////////CREATING DATASET FOR Pressure One CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetPressOne(String spinnerStartStr, String spinnerEndStr) {
        coefPresOne = coeffPresOne;
        Statement st;

        try{
            st = conn.createStatement();
            st.setFetchSize(fetchSize);
            double coefOneTempOne = coeffPresOnePmax / ((coeffPresOneUmax - coeffPresOneUmin)*0.641026);
            double coefOneTempTwo = coeffPresOneUmin * 0.641026;

            double coefTwoTempOne = coeffPresTwoPmax / ((coeffPresTwoUmax - coeffPresTwoUmin)*0.641026);
            double coefTwoTempTwo = coeffPresTwoUmin * 0.641026;



            JDBCXYDataset jds = new JDBCXYDataset(conn);
            if (Pres1.isSelected() && Pres2.isSelected())

            jds.executeQuery("select timestampcol, case when " +
                    "(pressureone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" > 0 then " +
                    "(pressureone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" else case when pressureone < 90 then 0 else pressureone end end" /*+ coefPresOne*/ +
                    " , case when " +
                    "(pressuretwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" > 0 then " +
                    "(pressuretwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" else case when pressuretwo < 90 then 0 else pressuretwo end end" /*+ coefPresOne*/ +
                    " from app.alldata " +
                    " where APP.alldata.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");// +

            else if (Pres1.isSelected() && !Pres2.isSelected())
                jds.executeQuery("select timestampcol, case when " +
                        "(pressureone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" > 0 then " +
                        "(pressureone*0.0032258 - "+coefOneTempTwo+")*" +coefOneTempOne+" else case when pressureone < 90 then 0 else pressureone end end" /*+ coefPresOne*/ +
                        " from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'"); //+

            else
                jds.executeQuery("select timestampcol, case when " +
                        "(pressuretwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" > 0 then " +
                        "(pressuretwo*0.0032258 - "+coefTwoTempTwo+")*" +coefTwoTempOne+" else case when pressuretwo < 90 then 0 else pressuretwo end end" /*+ coefPresOne*/ +
                        " from app.alldata " +
                        " where APP.ALLDATA.TIMESTAMPCOL between '" + comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + comboDate.getSelectedItem() + " " + spinnerEndStr + "'");// +

            st.close();
            jds.close();
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }


    /////////////////////////CREATING DATASET FOR Pressure Two CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetPressTwo() {
        coefPresTwo = coeffPresTwo;

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();
            st.setFetchSize(fetchSize);

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select timestamp, pressuretwo*" + coefPresTwo + " from app.pressure");
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }


/////////////////////////CREATING DATASET FOR DIGIT_ONE CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetDigitOne() {

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            st.setFetchSize(fetchSize);

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select timestam, case when dig1 then 1 else 0 end from app.digits");
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

/////////////////////////CREATING DATASET FOR DIGIT_ONE CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetDigitTwo(String digNum) {

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            st.setFetchSize(fetchSize);
            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select timestamp, case when dig" + digNum + " then 1 else 0 end from app.digits");
            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }



/////////////////////////CREATING DATASET FOR DIGITS CHART/////////////////////////////////////////////////////////
    private JDBCXYDataset createDatasetDigits() {
        try{
            String strUrl = "jdbc:derby:wagons";

//            Connection conn = DriverManager.getConnection(strUrl);
//            st = conn.createStatement();

            JDBCXYDataset jds = new JDBCXYDataset(conn);
            jds.executeQuery("select timestam, dig1, dig2, dig3, dig4, dig5," +
                    "dig6, dig7, dig8, dig9, dig10, dig11, dig12, dig13, dig14, dig15, dig16," +
                    "dig17, dig18, dig19, dig20, dig21, dig22, dig23, dig24, dig25, dig26, dig27, dig28," +
                    "dig29, dig30, dig31, dig32, dig33, dig34, dig35, dig36, dig37 from app.digits");
//            conn.close();
            return jds;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }



    private JDBCCategoryDataset createDataset2() {

        Statement st;

        try{
            String strUrl = "jdbc:derby:wagons";

            Connection conn = DriverManager.getConnection(strUrl);
            st = conn.createStatement();

            st.setFetchSize(fetchSize);
            JDBCCategoryDataset jds2 = new JDBCCategoryDataset(conn);
            jds2.executeQuery("select case when radioon then 1 else 0 end, speed from app.maintable");
            st.close();
            conn.close();
            return jds2;
        } catch (SQLException ex2) {
            ex2.printStackTrace(System.err);
        }
        return null;
    }


////////////////////////////////BACKUP DATABASE//////////////////////////////////////////////////////////////////////
    public static void backUpDatabase(Connection conn)throws SQLException
    {
    // Get today's date as a string:
        java.text.SimpleDateFormat todaysDate =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String carNoWrite = carNo.replaceAll("\0+$", "");
        String backupdirectory = "/mybackups/" + carNoWrite + "/" +
                todaysDate.format((java.util.Calendar.getInstance()).getTime());

        CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
        cs.setString(1, backupdirectory);
        System.out.println(cs.execute());

        cs.close();

        System.out.println("backed up database to " + backupdirectory);
    }


    public DefaultTableModel jointTable(int ofst, int rws) {
        DefaultTableModel resultTableModel = null;

        try {
            int ofDigits = ofst * 10;
            int rwsDigits = rws * 10;
            int ofSpeed = ofst * 4;
            int rwsSpeed = rws * 4;
////////////////////////////// Creating data for Joint Table////////////////////////////////////
            String selectAllDigits = "SELECT * FROM APP.DIGITS " +
                    "left join APP.PRESSURE on APP.DIGITS.TIMESTAMP = APP.PRESSURE.TIMESTAMP" +
                    " left join app.speed on app.digits.timestamp = app.speed.timestamp OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY";
//            case when dig1 then 1 else 0 end
//
//                jds2.executeQuery("select case when radioon then 1 else 0 end, speed from app.maintable");

            String selectAllPressures = "SELECT * FROM APP.PRESSURE";
            String selectAllSpeed = "SELECT * FROM APP.SPEED OFFSET " + ofSpeed + " ROWS FETCH NEXT " + rwsSpeed + " ROWS ONLY";
//            String selectSmth = "SELECT * FROM APP.DIGITS LEFT JOINT APP.DIGITS.TIMESTAM";
//            " UNION SELECT APP.SPEED.SPEED FROM APP.SPEED UNION SELECT APP.PRESSURE.PRESSUREONE, " +
//                    "APP.PRESSURE.PRESSURETWO FROM APP.PRESSURE ";
            String strUrl = "jdbc:derby:wagons";
            Connection connButtonJointTable = DriverManager.getConnection(strUrl);
            Statement st1 = connButtonJointTable.createStatement();
            Statement st2 = connButtonJointTable.createStatement();

            st1.setFetchSize(fetchSize);
            ResultSet rowsJointTableDigits;
            System.out.println("Start joint table query...");


            rowsJointTableDigits = st1.executeQuery(selectAllDigits);
            System.out.println("End joint table query...");
            System.out.println("rowsJointTableDigits = " + rowsJointTableDigits.getFetchSize());
//            if (dTableModelJointTable != null) dTableModelJointTable = null;
//                //System.out.println("model not null...");
//            else
//                System.out.println("model is null");
            resultTableModel = new DefaultTableModel(databaseInfoJointTable, columnsJointTable);


//            st1.close();

//            rowsJointTableDigits = st1.executeQuery(selectSmth);

//            rowsJointTableSpeed = st1.executeQuery(selectAllSpeed);

            int numOfColJointTableDigits;
//            ResultSetMetaData metaDataJointTableDigits;


            ResultSetMetaData metaDataJointTableDigits = null;
            ResultSetMetaData metaDataJointTableSpeed = null;

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            numOfColJointTableDigits = metaDataJointTableDigits.getColumnCount();
            System.out.println("Num of columns in joint table: " + numOfColJointTableDigits);

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            if (resultTableModel.getRowCount() > 0) {
                resultTableModel.setNumRows(0);

            }
//            if (dTableModelSpeed.getRowCount() > 0) {
//                dTableModelSpeed.setRowCount(0);
//            }
//            if (dTableModelDigits.getRowCount() > 0) {
//                dTableModelDigits.setRowCount(0);
//            }


//            if (dTableModelJointTable.getRowCount() > 0) {
////                dTableModelJointTable.setRowCount(0);
//                dTableModelJointTable.setNumRows(2);
//                flagDelete = true;
//            }

//            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
//            numOfColJointTableDigits = metaDataJointTableDigits.getColumnCount();

//            columnsJointTable = new String[numOfColJointTableDigits];
//            for (int i = 1; i <= numOfColJointTableDigits - 1; i++) {
//                // Returns the column name
//                columnsJointTable[i] = metaDataJointTableDigits.getColumnName(i);
//                System.out.println(i);
//            }

            Object[] tempRowJointTableDigits;
            System.out.println("rows joint table = " + rowsJointTableDigits.getFetchSize());
            System.out.println("Start draw table");
            String presTableOneStr;
            String presTableTwoStr;
            while (rowsJointTableDigits.next()) {
                java.sql.Timestamp ts = rowsJointTableDigits.getTimestamp(1);

                if (ts.getNanos() % 1000000000 == 0 && Integer.toString(rowsJointTableDigits.getInt(40)) != null){
//                    System.out.println("TIME IS: " + ts + " " + rowsJointTableDigits.getInt(40));
//                    System.out.println("Error: " + rowsJointTableDigits.getString(43));
//                    double speed = rowsJointTableSpeed.getDouble(43) / 5.5;
//                    speed = (speed < 0.01 ? 0 : speed);
//                    System.out.println(speed);
                    if (rowsJointTableDigits.getString(40) != null) {

                    double presTableOne = (Math.round((double)((rowsJointTableDigits.getInt(40) * /*(double)coefPresOne **/ 0.01258 - 1.24995) * 100.0))) / 100.0;
                    double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * /*(double)coefPresTwo **/ 0.01258 - 1.24995) * 100.0))) / 100.0;

                    presTableOne = presTableOne < 0.0 ? 0.0 : presTableOne;
                    presTableTwo = presTableTwo < 0.0 ? 0.0 : presTableTwo;

                    presTableOneStr = Double.toString(presTableOne);
                    presTableTwoStr = Double.toString(presTableTwo);
                    }
                    else {
                        presTableOneStr = "-";
                        presTableTwoStr = "-";
                    }
                            tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                            //Double.toString(rowsJointTableDigits.getDouble(43) / 5.5),
                            //new DecimalFormat("#0.00").format(rowsJointTableSpeed.getDouble(43) / 5.5),
                            //String.format("%.12f", rowsJointTableSpeed.getDouble(43) / 5.5),
                            //rowsJointTableDigits.getString(43),
                            Double.toString(Math.round(rowsJointTableDigits.getDouble(43))),

//                            Double.toString((Math.round((double)rowsJointTableDigits.getInt(40) * (double)coefPresOne * 100.0)) / 100.0),
//                            Double.toString((Math.round((double)rowsJointTableDigits.getInt(41) * (double)coefPresTwo * 100.0)) / 100.0),
//                            Double.toString(presTableOne),
//                            Double.toString(presTableTwo),
                                    presTableOneStr,
                                    presTableTwoStr,
                            rowsJointTableDigits.getInt(2), rowsJointTableDigits.getInt(3),
                            rowsJointTableDigits.getInt(4), rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
                            rowsJointTableDigits.getInt(7), rowsJointTableDigits.getInt(8), rowsJointTableDigits.getInt(9),
                            rowsJointTableDigits.getInt(10), rowsJointTableDigits.getInt(11), rowsJointTableDigits.getInt(12),
                            rowsJointTableDigits.getInt(13), rowsJointTableDigits.getInt(14), rowsJointTableDigits.getInt(15),
                            rowsJointTableDigits.getInt(16), rowsJointTableDigits.getInt(17), rowsJointTableDigits.getInt(18),
                            rowsJointTableDigits.getInt(19), rowsJointTableDigits.getInt(20), rowsJointTableDigits.getInt(21),
                            rowsJointTableDigits.getInt(22), rowsJointTableDigits.getInt(23), rowsJointTableDigits.getInt(24),
                            rowsJointTableDigits.getInt(25), rowsJointTableDigits.getInt(26), rowsJointTableDigits.getInt(27),
                            rowsJointTableDigits.getInt(28), rowsJointTableDigits.getInt(29), rowsJointTableDigits.getInt(30),
                            rowsJointTableDigits.getInt(31), rowsJointTableDigits.getInt(32), rowsJointTableDigits.getInt(33),
                            rowsJointTableDigits.getInt(34), rowsJointTableDigits.getInt(35), rowsJointTableDigits.getInt(36),
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38)
                    };
                }
                else if (ts.getNanos() % 500000000 == 0){
//                    System.out.println("TIME IS: " + ts + " " + rowsJointTableDigits.getInt(40));
//                    double speed = rowsJointTableSpeed.getDouble(43) / 5.5;
//                    speed = (speed < 0.01 ? 0 : speed);
                    tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                           //rowsJointTableDigits.getString(43),
                            Double.toString(Math.round(rowsJointTableDigits.getDouble(43))),
                            "-",
                            "-",
                            rowsJointTableDigits.getInt(2), rowsJointTableDigits.getInt(3),
                            rowsJointTableDigits.getInt(4), rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
                            rowsJointTableDigits.getInt(7), rowsJointTableDigits.getInt(8), rowsJointTableDigits.getInt(9),
                            rowsJointTableDigits.getInt(10), rowsJointTableDigits.getInt(11), rowsJointTableDigits.getInt(12),
                            rowsJointTableDigits.getInt(13), rowsJointTableDigits.getInt(14), rowsJointTableDigits.getInt(15),
                            rowsJointTableDigits.getInt(16), rowsJointTableDigits.getInt(17), rowsJointTableDigits.getInt(18),
                            rowsJointTableDigits.getInt(19), rowsJointTableDigits.getInt(20), rowsJointTableDigits.getInt(21),
                            rowsJointTableDigits.getInt(22), rowsJointTableDigits.getInt(23), rowsJointTableDigits.getInt(24),
                            rowsJointTableDigits.getInt(25), rowsJointTableDigits.getInt(26), rowsJointTableDigits.getInt(27),
                            rowsJointTableDigits.getInt(28), rowsJointTableDigits.getInt(29), rowsJointTableDigits.getInt(30),
                            rowsJointTableDigits.getInt(31), rowsJointTableDigits.getInt(32), rowsJointTableDigits.getInt(33),
                            rowsJointTableDigits.getInt(34), rowsJointTableDigits.getInt(35), rowsJointTableDigits.getInt(36),
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38)
                    };
                }
                else {
//                    System.out.println("TIME IS: " + ts + " " + rowsJointTableDigits.getInt(40));
                    tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                            "-",
                            "-",
                            "-",
                            rowsJointTableDigits.getInt(2), rowsJointTableDigits.getInt(3),
                            rowsJointTableDigits.getInt(4), rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
                            rowsJointTableDigits.getInt(7), rowsJointTableDigits.getInt(8), rowsJointTableDigits.getInt(9),
                            rowsJointTableDigits.getInt(10), rowsJointTableDigits.getInt(11), rowsJointTableDigits.getInt(12),
                            rowsJointTableDigits.getInt(13), rowsJointTableDigits.getInt(14), rowsJointTableDigits.getInt(15),
                            rowsJointTableDigits.getInt(16), rowsJointTableDigits.getInt(17), rowsJointTableDigits.getInt(18),
                            rowsJointTableDigits.getInt(19), rowsJointTableDigits.getInt(20), rowsJointTableDigits.getInt(21),
                            rowsJointTableDigits.getInt(22), rowsJointTableDigits.getInt(23), rowsJointTableDigits.getInt(24),
                            rowsJointTableDigits.getInt(25), rowsJointTableDigits.getInt(26), rowsJointTableDigits.getInt(27),
                            rowsJointTableDigits.getInt(28), rowsJointTableDigits.getInt(29), rowsJointTableDigits.getInt(30),
                            rowsJointTableDigits.getInt(31), rowsJointTableDigits.getInt(32), rowsJointTableDigits.getInt(33),
                            rowsJointTableDigits.getInt(34), rowsJointTableDigits.getInt(35), rowsJointTableDigits.getInt(36),
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38)
                    };

                }
               // rowsJointTableDigits.close();
                // Adds the row of data to the end of the model
                resultTableModel.addRow(tempRowJointTableDigits);
                tempRowJointTableDigits = null;
            }
            System.out.println("Stop drawing general table");


//            while (rowsJointTableDigits.next()) {
//                int i = 1;
//            if (dTableModelDigits.getValueAt(i, 2) == 7777) {
//                Component c = getTableCellRendererComponent(
//                        dTableModelDigits, 7777, false, false, i, 2);
//                ((JLabel) c).setText("");
//            }
//            }

//            rowsJointTablePressures = st1.executeQuery(selectAllPressures);
//            int numOfColJointTablePressures;
//            metaDataJointTablePressures = rowsJointTablePressures.getMetaData();
//
//
//            metaDataJointTablePressures = rowsJointTablePressures.getMetaData();
//            numOfColJointTablePressures = metaDataJointTablePressures.getColumnCount();
//
//            columnsJointTable = new String[numOfColJointTablePressures];
//            for (int i = 1; i <= numOfColJointTablePressures - 1; i++) {
//                // Returns the column name
//                columnsJointTable[i] = metaDataJointTablePressures.getColumnName(i);
//                System.out.println(i);
//            }
//
//            Object[] tempRowJointTablePressures;
//            while (rowsJointTablePressures.next()) {
//                tempRowJointTablePressures = new Object[]{rowsJointTablePressures.getTimestamp(1), "",
//                        rowsJointTablePressures.getInt(2), rowsJointTablePressures.getInt(3),
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",
//                        "", "", "",  ""
//                };
//                // Adds the row of data to the end of the model
//                dTableModelJointTable.addRow(tempRowJointTablePressures);
//            }
///////////*****************SPEEDS************************************////////////////////////////////////
            st2.setFetchSize(fetchSize);
            ResultSet rowsJointTableSpeed = st2.executeQuery(selectAllSpeed);
//            st2.close();
            int numOfColJointTableSpeed;
//            metaDataJointTableSpeed = rowsJointTableSpeed.getMetaData();


            metaDataJointTableSpeed = rowsJointTableSpeed.getMetaData();
            numOfColJointTableSpeed = metaDataJointTableSpeed.getColumnCount();

//            columnsJointTable = new String[numOfColJointTableSpeed];
//            for (int i = 1; i <= numOfColJointTableSpeed - 1; i++) {
//                // Returns the column name
//                columnsJointTable[i] = metaDataJointTableSpeed.getColumnName(i);
//                System.out.println(i);
//            }

            System.out.println("Num of columns in joint table speed: " + numOfColJointTableSpeed);

            boolean isFirst = true;
            Object[] tempRowJointTableSpeed = null;
            while (rowsJointTableSpeed.next()) {
                ts15 = rowsJointTableSpeed.getTimestamp(1);
                if (isFirst) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String stringStart  = dateFormat.format(ts15);
                    ts17 = ts15;
                    labelStart.setText(stringStart);
                    textFieldStart.setText(stringStart);
//                    spinnerStart.setValue(Time.valueOf(stringStart));
//                    System.out.println("ts15 = " + ts15);
//                    System.out.println("ts17 = " + ts17);
//                    spinner3.setValue(ts15);
                    isFirst = false;
                }
                System.out.println(ts15.getNanos());
                if ((ts15.getNanos() % 500000000 != 0) && (ts15.getNanos() % 1000000000 != 0)){
                    tempRowJointTableSpeed = new Object[]{ts15,
                            //new DecimalFormat("#0.00").format(rowsJointTableSpeed.getDouble(2) / 5.5),
                            //rowsJointTableSpeed.getString(2),
                            Double.toString(Math.round(rowsJointTableSpeed.getDouble(2))),
                            "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-",
                            "-", "-", "-", "-"
                    };
                    // Adds the row of data to the end of the model
                    resultTableModel.addRow(tempRowJointTableSpeed);
                }
            }
            if (flagDelete) {
                resultTableModel.removeRow(2);
                flagDelete = false;
            }
            rowsJointTableDigits.close();
            st1.close();
            st2.close();
            connButtonJointTable.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }

////////////////////////////////CREATING NEW TABLE TO SHOW JOINT TABLE////////////////////////////////////////////////

//        JTable tableJointTable = new JTable(dTableModelJointTable);
//        ((DefaultTableCellRenderer) tableJointTable.getTableHeader().getDefaultRenderer())
//                .setHorizontalAlignment(JLabel.CENTER);
//
//        tableJointTable.getColumnModel().getColumn(0).setPreferredWidth(150);
//        tableJointTable.setRowHeight(tableJointTable.getRowHeight() + 10);
//        tableJointTable.setAutoCreateRowSorter(true);
//        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        //scrollFour.setViewportView(table4);
//        tableJointTable.setFillsViewportHeight(true);
    return resultTableModel;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////retrieving database date limits//////////////////////////////////////////////////////////////

    private void getRecordStartEnd() {
        try {

            String selectLastTime = "SELECT TIMESTAMP FROM APP.DIGITS ORDER BY TIMESTAMP DESC FETCH FIRST ROW ONLY";
            String selectFirstTime = "SELECT TIMESTAMP FROM APP.DIGITS ORDER BY TIMESTAMP ASC FETCH FIRST ROW ONLY";

            String strUrl = "jdbc:derby:wagons";

            Connection connSettingsInfo = DriverManager.getConnection(strUrl);

            Statement st1 = connSettingsInfo.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            settingsInfoSet = st1.executeQuery(selectLastTime);
            settingsInfoSet.next();
            java.sql.Timestamp timeEnd = settingsInfoSet.getTimestamp(1);

           // timeDisplayEnd.setTime(24 * 60 * 60 * 1000 - 1000);

            settingsInfoSet = st1.executeQuery(selectFirstTime);
            settingsInfoSet.next();
            java.sql.Timestamp timeStart = settingsInfoSet.getTimestamp(1);

            java.sql.Timestamp timeDisplayEnd = settingsInfoSet.getTimestamp(1);
            timeDisplayEnd.setHours(23);
            timeDisplayEnd.setMinutes(59);
            timeDisplayEnd.setSeconds(59);

            Date dateStart = new Date(timeStart.getTime());
            Date dateEnd = new Date(timeEnd.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);


            String timeStartStringOne = simpleDateFormat.format(timeStart);
            String timeEndStringOne = simpleDateFormat.format(timeEnd);
            String timeDisplayStringEnd = timeFormat.format(timeDisplayEnd);
            String timeDisplayStringStart = timeFormat.format(timeStart);

            System.out.println("End time: " + timeEndStringOne);
            System.out.println("Start time: " + timeStartStringOne);
            System.out.println("End display time: " + timeDisplayStringEnd);

            labelStart.setText(timeStartStringOne);
            labelEnd.setText(timeEndStringOne);
//            labelStart.setText(simpleDateFormat.format(dateStart.toString()));
//            labelEnd.setText(simpleDateFormat.format(dateEnd.toString()));


            labelStart.setForeground(new Color(0, 0, 255));
            labelEnd.setForeground(new Color(0, 0, 255));
            textFieldStart.setText(timeDisplayStringStart);
            textFieldEnd.setText(timeDisplayStringEnd);

//            startDisplayString = timeStart.toString();
            int days = (int) Math.round((dateEnd.getTime() - dateStart.getTime()) / (double)86400000);
            System.out.println("days = " + days);
//            labelDays.setText(String.valueOf(days));

            startDisplayString = timeStartStringOne;
//            System.out.println("Combo date 0 = " + timeStart.getTime());
            Date comboDates = new Date(timeStart.getTime());
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

//            System.out.println("Combo date 0 = " + dateFormat2.format(comboDates));

            Calendar c = Calendar.getInstance();
            comboDate.addItem(dateFormat2.format(comboDates));
            for (int i = 0; i < days; i++) {
                c.setTime(comboDates);
                c.add(Calendar.DATE, 1);
                comboDates = c.getTime();
                comboDate.addItem(dateFormat2.format(comboDates));
            }



            st1.close();
            connSettingsInfo.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не вдалося визначити дати початку та кінця запису...");
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }

    }

//////////////////////Create Settings Info data/////////////////////////

    private void createSettingsInfoData() {
        try {

            String selectDigits = "SELECT * FROM APP.SETTINGSINFO";

            String strUrl = "jdbc:derby:wagons";

            Connection connSettingsInfo = DriverManager.getConnection(strUrl);

            Statement st1 = connSettingsInfo.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//            st1.setFetchSize(fetchSize);
            settingsInfoSet = st1.executeQuery(selectDigits);

//            while (!settingsInfoSet.isLast()) settingsInfoSet.next();
//            if (!settingsInfoSet.last()) System.out.println("Not Last");
            settingsInfoSet.next();
            settingsInfoSet.last();
//            settingsInfoSet.previous();

            carNo = settingsInfoSet.getString(2);
            coeffPresOne = settingsInfoSet.getDouble(3);
            coeffPresTwo = settingsInfoSet.getDouble(4);
            coeffSpeed = settingsInfoSet.getDouble(5);


            coeffSpeed *= 100;
            coeffSpeed = Math.round(coeffSpeed);
            coeffSpeed /= 100;

            coeffPresOne *= 100;
            coeffPresOne = Math.round(coeffPresOne);
            coeffPresOne /= 100;

            coeffPresTwo *= 100;
            coeffPresTwo = Math.round(coeffPresTwo);
            coeffPresTwo /= 100;


            //carNoTwoText.setText(Integer.toString(carNo));
//            String textSpeed = String.format("%2.f", coeffSpeed);
//            try {
//            System.out.println("TextSpeed = " + textSpeed);
//            } catch (Exception ek) {
//                ek.printStackTrace();
//            }

            carNoTwoText.setText(carNo);
            carNoTop.setText(carNo);
            coeffSpeedRead.setText((df.format(coeffSpeed)));
            coefPresOneRead.setText(df.format(coeffPresOne));
            coefPresTwoRead.setText(df.format(coeffPresTwo));



            System.out.println("Database written time: " + settingsInfoSet.getTimestamp(1));

            st1.close();
            connSettingsInfo.close();
//            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Couldn't find table with settings...");
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
//            return false;
        }

    }

    private void createSettingsInfoDataTwo(Connection conn) {
        try {

            String selectDigits = "SELECT * FROM APP.SETTINGSINFO";

//            String strUrl = "jdbc:derby:wagons";

//            Connection connSettingsInfo = DriverManager.getConnection(strUrl);

            Statement st1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//            st1.setFetchSize(fetchSize);
            settingsInfoSet = st1.executeQuery(selectDigits);

//            while (!settingsInfoSet.isLast()) settingsInfoSet.next();
//            if (!settingsInfoSet.last()) System.out.println("Not Last");
            settingsInfoSet.next();
            settingsInfoSet.last();
//            settingsInfoSet.previous();

            carNo = settingsInfoSet.getString(2);
            coeffSpeed = settingsInfoSet.getDouble(3);
            coeffPresOnePmax = settingsInfoSet.getDouble(4);
            coeffPresOneUmin = settingsInfoSet.getDouble(5);
            coeffPresOneUmax = settingsInfoSet.getDouble(6);

            coeffPresTwoPmax = settingsInfoSet.getDouble(7);
            coeffPresTwoUmin = settingsInfoSet.getDouble(8);
            coeffPresTwoUmax = settingsInfoSet.getDouble(9);

            coeffSpeed *= 100;
            coeffSpeed = Math.round(coeffSpeed);
            coeffSpeed /= 100;

            coeffPresOnePmax *= 100;
            coeffPresOnePmax = Math.round(coeffPresOnePmax);
            coeffPresOnePmax /= 100;

            coeffPresOneUmin *= 100;
            coeffPresOneUmin = Math.round(coeffPresOneUmin);
            coeffPresOneUmin /= 100;

            coeffPresOneUmax *= 100;
            coeffPresOneUmax = Math.round(coeffPresOneUmax);
            coeffPresOneUmax /= 100;

            coeffPresTwoPmax *= 100;
            coeffPresTwoPmax = Math.round(coeffPresTwoPmax);
            coeffPresTwoPmax /= 100;

            coeffPresTwoUmin *= 100;
            coeffPresTwoUmin = Math.round(coeffPresTwoUmin);
            coeffPresTwoUmin /= 100;

            coeffPresTwoUmax *= 100;
            coeffPresTwoUmax = Math.round(coeffPresTwoUmax);
            coeffPresTwoUmax /= 100;


            carNoTwoText.setText(carNo);
            carNoTop.setText(carNo);
            coeffSpeedRead.setText((df.format(coeffSpeed)));
//            coefPresOneRead.setText(df.format(coeffPresOne));
//            coefPresTwoRead.setText(df.format(coeffPresTwo));

            textReadCoefOnePmax.setText(df.format(coeffPresOnePmax));
            textReadCoefOneUmin.setText(df.format(coeffPresOneUmin));
            textReadCoefOneUmax.setText(df.format(coeffPresOneUmax));

            textReadCoefTwoPmax.setText(df.format(coeffPresTwoPmax));
            textReadCoefTwoUmin.setText(df.format(coeffPresTwoUmin));
            textReadCoefTwoUmax.setText(df.format(coeffPresTwoUmax));


            System.out.println("Database written time: " + settingsInfoSet.getTimestamp(1));

            st1.close();
//            connSettingsInfo.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Couldn't find table with settings...");
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
//            return false;
        }

    }

///////////////Create big table//////////////////////////////

    private DefaultTableModel createDigitsTable(int ofst, int rws) {
        int ofDigits = ofst * 10;
        int rwsDigits = rws * 10;
        DefaultTableModel resultDigitsModel = new DefaultTableModel(databaseInfoDigits, columnsDigits);
////////////////////////////// Creating data for Joint Table////////////////////////////////////
//        String selectAllDigits = "SELECT * FROM APP.DIGITS " +
//                "left join APP.PRESSURE on APP.DIGITS.TIMESTAMP = APP.PRESSURE.TIMESTAMP" +
//                " left join app.speed on app.digits.timestamp = app.speed.timestamp OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY";




        try {

            String selectDigits = "SELECT * FROM APP.DIGITS OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY";

            String strUrl = "jdbc:derby:wagons";

            Connection connButtonLoadTableDigits = DriverManager.getConnection(strUrl);

            Statement st1 = connButtonLoadTableDigits.createStatement();

            st1.setFetchSize(fetchSize);
            rowsDigits = st1.executeQuery(selectDigits);

            int numOfColDigits;
            metaDataDigits = rowsDigits.getMetaData();
            numOfColDigits = metaDataDigits.getColumnCount();

//            DefaultTableModel dTableModelDigits = null;

            resultDigitsModel.setRowCount(0);

            metaDataDigits = rowsDigits.getMetaData();
            numOfColDigits = metaDataDigits.getColumnCount();
            Object[] tempRowDigits;

            columnsDigits = new String[numOfColDigits];
            for (int i = 1; i <= numOfColDigits - 1; i++) {
                // Returns the column name
                columnsDigits[i] = metaDataDigits.getColumnName(i);
                System.out.println(i);
            }

            Object[] tempRow;
            while (rowsDigits.next()) {
                tempRowDigits = new Object[]{rowsDigits.getTimestamp(1), rowsDigits.getBoolean(2),
                        rowsDigits.getBoolean(3), rowsDigits.getBoolean(4), rowsDigits.getBoolean(5), rowsDigits.getBoolean(6),
                        rowsDigits.getBoolean(7), rowsDigits.getBoolean(8), rowsDigits.getBoolean(9), rowsDigits.getBoolean(10),
                        rowsDigits.getBoolean(11), rowsDigits.getBoolean(12), rowsDigits.getBoolean(13), rowsDigits.getBoolean(14),
                        rowsDigits.getBoolean(15), rowsDigits.getBoolean(16), rowsDigits.getBoolean(17), rowsDigits.getBoolean(18),
                        rowsDigits.getBoolean(19), rowsDigits.getBoolean(20), rowsDigits.getBoolean(21), rowsDigits.getBoolean(22),
                        rowsDigits.getBoolean(23), rowsDigits.getBoolean(24), rowsDigits.getBoolean(25), rowsDigits.getBoolean(26),
                        rowsDigits.getBoolean(27), rowsDigits.getBoolean(28), rowsDigits.getBoolean(29), rowsDigits.getBoolean(30),
                        rowsDigits.getBoolean(31), rowsDigits.getBoolean(32), rowsDigits.getBoolean(33), rowsDigits.getBoolean(34),
                        rowsDigits.getBoolean(35), rowsDigits.getBoolean(36), rowsDigits.getBoolean(37), rowsDigits.getBoolean(38)
                };
                // Adds the row of data to the end of the model
                resultDigitsModel.addRow(tempRowDigits);
            }
            st1.close();
            connButtonLoadTableDigits.close();
//            return true;

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
//            return false;
        }
        return resultDigitsModel;
    }


    private void createBigTable() {
        System.gc();
//        String spinnerStartStr = String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
//        String spinnerEndStr = String.valueOf(new Time(((Date)spinnerEnd.getValue()).getTime()));

        String spinnerStartStr = "00:00:00";
        String spinnerEndStr = "23:59:59";


        final CreateBigTableClass cbtc = new CreateBigTableClass(spinnerStartStr, spinnerEndStr, this);
        JTable tableJointTable = cbtc.getTableJointTable();
//        System.out.println(tableJointTable);
//        try {
//            tableJointTable.setFillsViewportHeight(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        scrollBigTable.getComponentCount().getComponents();




//        scrollBigTable.remove(scrollBigTable.getComponentCount() - 1);



//        tableJointTable.setAutoCreateRowSorter(true);
//        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(tableJointTable.getModel());
//        tableJointTable.setRowSorter(sorter2);
//
//        List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter2.setSortKeys(sortKeys2);

        scrollBigTable.setViewportView(tableJointTable);


//        final FixedColumnTable fct = new FixedColumnTable(1, scrollBigTable);
//
//        fct.getFixedTable().setAutoCreateRowSorter(true);
//        fct.getFixedTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        final TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(fct.getFixedTable().getModel());
//        fct.getFixedTable().setRowSorter(sorter2);
//
//        final List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter2.setSortKeys(sortKeys2);

//        labelStart.setText(cbtc.getStringStart());
//        textFieldStart.setText(cbtc.getStringStart());
        ts15 = cbtc.getTs15();
        ts17 = cbtc.getTs17();

        System.gc();


    }

    private void createBigTable(Connection conn) {
        System.gc();
//        String spinnerStartStr = String.valueOf(new Time((((Date)spinnerStart.getValue()).getTime())));
//        String spinnerEndStr = String.valueOf(new Time(((Date)spinnerEnd.getValue()).getTime()));

        String spinnerStartStr = "00:00:00";
        String spinnerEndStr = "23:59:59";


        final CreateBigTableClass cbtc = new CreateBigTableClass(spinnerStartStr, spinnerEndStr, this, conn);
        JTable tableJointTable = cbtc.getTableJointTable();
//        System.out.println(tableJointTable);
//        try {
//            tableJointTable.setFillsViewportHeight(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        scrollBigTable.getComponentCount().getComponents();




//        scrollBigTable.remove(scrollBigTable.getComponentCount() - 1);



//        tableJointTable.setAutoCreateRowSorter(true);
//        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(tableJointTable.getModel());
//        tableJointTable.setRowSorter(sorter2);
//
//        List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter2.setSortKeys(sortKeys2);

        scrollBigTable.setViewportView(tableJointTable);


//        final FixedColumnTable fct = new FixedColumnTable(1, scrollBigTable);
//
//        fct.getFixedTable().setAutoCreateRowSorter(true);
//        fct.getFixedTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        final TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(fct.getFixedTable().getModel());
//        fct.getFixedTable().setRowSorter(sorter2);
//
//        final List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
//        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter2.setSortKeys(sortKeys2);

//        labelStart.setText(cbtc.getStringStart());
//        textFieldStart.setText(cbtc.getStringStart());
        ts15 = cbtc.getTs15();
        ts17 = cbtc.getTs17();

        System.gc();


    }


    private void createBigTableOld(){
//        boolean result = false;
        //scrollBigTable = null;
        JointTableClass jtc = new JointTableClass("", "", this);


//        DefaultTableModel dTableModelJointTable = jointTable(offsetToShow, secondsToShow);
        DefaultTableModel dTableModelJointTable = jtc.getJointTable();
//        labelStart.setText(jtc.getStringStart());
        textFieldStart.setText(jtc.getStringStart());
        ts15 = jtc.getTs15();
        ts17 = jtc.getTs17();

//        if (tableJointTable != null) tableJointTable = null;

        JTable tableJointTable = new JTable(dTableModelJointTable);
        System.out.println("Number of colums in table model: " + dTableModelJointTable.getColumnCount());

        ((DefaultTableCellRenderer) tableJointTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

       // JLabel tableHeader = ((JLabel)  tableJointTable.getTableHeader());

        TableCellRenderer headerRenderer = new VerticalTableHeaderCellRenderer();
        System.out.println("Number of columns in joint table 2: " + tableJointTable.getColumnCount());


//        tableJointTable.setShowGrid(true);

        tableJointTable.getTableHeader().setDefaultRenderer(headerRenderer);
        tableJointTable.getTableHeader().setAutoscrolls(true);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

        tableJointTable.getColumn(tableJointTable.getColumnName(1)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(2)).setCellRenderer( rightRenderer );
        tableJointTable.getColumn(tableJointTable.getColumnName(3)).setCellRenderer( rightRenderer );

        tableJointTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableJointTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableJointTable.getColumnModel().getColumn(3).setPreferredWidth(50);

//        tableJointTable.getTableHeader().setPreferredSize(new Dimension(-1, 100));
//        tableJointTable.getTableHeader().setMinimumSize(new Dimension(-1, 100));

        for (int i = 4; i < tableJointTable.getColumnCount(); i++) {
            tableJointTable.getColumnModel().getColumn(i).setPreferredWidth(32);
//            tableJointTable.getColumnModel().getColumn(i).setWidth(32);
        }

        tableJointTable.setRowHeight(tableJointTable.getRowHeight() + 10);
        tableJointTable.setAutoCreateRowSorter(true);
        tableJointTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableJointTable.getModel());
        tableJointTable.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

//        ////////////////////////////////////////////////////////////////////////////////////////
//        int tableLength = dTableModelJointTable.getRowCount() - 1;
//        progressBar1.setMaximum(tableLength);
//        for (int i = 0; i < dTableModelJointTable.getRowCount() - 1; i++) {
//            if (!dTableModelJointTable.getValueAt(i, 1).equals(7777)) break;
////            final int percent = i;
////            try {
////                SwingUtilities.invokeLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        progressBar1.setValue(percent);
////                        progressBar1.repaint();
////                        FormTwo.getInstance().repaint();
////                    }
////                });
////                java.lang.Thread.sleep(100);
////            } catch (InterruptedException e) {}
//
//            java.sql.Timestamp d = (java.sql.Timestamp) dTableModelJointTable.getValueAt(i, 0);
//
//            if (d.getNanos() % 1000000000 == 0 && dTableModelJointTable.getValueAt(i, 1).equals(7777)) {
//             for (int j = i ; j < dTableModelJointTable.getRowCount() - 1; j++) {
//                 java.sql.Timestamp d1 = (java.sql.Timestamp) dTableModelJointTable.getValueAt(j, 0);
//
//                 if (d1.equals(d) /*&& dTableModelJointTable.getValueAt(i, 1).equals(7777)*/) {
//                     if (dTableModelJointTable.getValueAt(j, 1).equals(3333) ){
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 2), i, 2);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 3), i, 3);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 2), i + 10, 2);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 3), i + 10, 3);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 2), i + 20, 2);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 3), i + 20, 3);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 2), i + 30, 2);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 3), i + 30, 3);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 2), i + 40, 2);
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 3), i + 40, 3);
//
////                         System.out.println("Some text in loop 3333");
//                         dTableModelJointTable.removeRow(j);
//                     } else if (dTableModelJointTable.getValueAt(j, 2).equals(2222)){
//                         dTableModelJointTable.setValueAt((int)dTableModelJointTable.getValueAt(j, 1), i, 1);
////                         System.out.println("Some text in loop 2");
//                         dTableModelJointTable.removeRow(j);
//                     }
//                 }
//             }
//            }
////            else if (i > 1) {
////                         dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 1), i, 1);
////                         dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 2), i, 2);
////                         dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 3), i, 3);
////            }
//        }
//        for (int i = 0; i < dTableModelJointTable.getRowCount(); i++) {
//            java.sql.Timestamp d = (java.sql.Timestamp) dTableModelJointTable.getValueAt(i, 0);
//            int nanos = d.getNanos() / 100000000;
//
//            if (((nanos % 25 == 0) || (nanos % 75 == 0)) && dTableModelJointTable.getValueAt(i, 2).equals(2222) && i > 0) {
//
//                System.out.println(dTableModelJointTable.getValueAt(i, 2));
//                System.out.println(dTableModelJointTable.getValueAt(i - 1, 2));
//                System.out.println();
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 1), i, 1);
//
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 2), i, 2);
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 3), i, 3);
//                for (int j = 4; j < 41; j++) {
//                    dTableModelJointTable.setValueAt( (boolean)dTableModelJointTable.getValueAt(i - 1, j), i, j);
//                }
//            }
//            else if (nanos % 10 != 0 && ((nanos % 25 != 0) && (nanos % 75 != 0)) && i > 0) {
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 1), i, 1);
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 2), i, 2);
//                dTableModelJointTable.setValueAt((int) dTableModelJointTable.getValueAt(i - 1, 3), i, 3);
//            }
//
//        }
//
////        for (int i = 1; i < tableJointTable.getRowCount(); i++) {
////            if (tableJointTable.getTa.getCellRenderer(i, 1).toString() == "7777") {
////                tableJointTable.getModel().g
////            }
////        }
//
//        ////////////////////////////////////////////////////////////////////////

//        tableJointTable.setCellSelectionEnabled(false);
//        tableJointTable.setDefaultEditor(Object.class, null);
        tableJointTable.setEnabled(false);
        //tableJointTable.setRowHeight(50);
        scrollBigTable.setViewportView(tableJointTable);
        tableJointTable.setFillsViewportHeight(true);
        FixedColumnTable fct = new FixedColumnTable(1, scrollBigTable);

        fct.getFixedTable().setAutoCreateRowSorter(true);
        fct.getFixedTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        fct.getFixedTable().getTableHeader().setDefaultRenderer(headerRenderer);
//        fct.getFixedTable().getTableHeader().setPreferredSize(new Dimension(-1, 500));
//        fct.getFixedTable().getTableHeader().setSize(-1, 500);
//        fct.getFixedTable().getTableHeader().setMinimumSize(new Dimension(-1, 500));



        TableRowSorter<TableModel> sorter2 = new TableRowSorter<TableModel>(fct.getFixedTable().getModel());
        fct.getFixedTable().setRowSorter(sorter2);

        List<RowSorter.SortKey> sortKeys2 = new ArrayList<>(25);
        sortKeys2.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter2.setSortKeys(sortKeys2);


    }

/////////////////////////////////////////////////////////////////

    //////////////////Delete all Database///////////////////////////

    public void deleteAllDb(){
        ReadBinFile rbf = new ReadBinFile();
        System.out.println(rbf.timeStamp);

        String strUrl = "jdbc:derby:wagons";
        try {
            Connection connDel1;
            connDel1 = DriverManager.getConnection(strUrl);

            PreparedStatement pstmDel1 = connDel1.prepareStatement("DELETE FROM APP.PRESSURE WHERE 1 = 1");
            PreparedStatement pstmDel2 = connDel1.prepareStatement("DELETE FROM APP.SPEED WHERE 1 = 1");
            PreparedStatement pstmDel3 = connDel1.prepareStatement("DELETE FROM APP.DIGITS WHERE 1 = 1");

            pstmDel1.executeUpdate();
            pstmDel2.executeUpdate();
            pstmDel3.executeUpdate();
            pstmDel1.close();
            pstmDel2.close();
            pstmDel3.close();
            connDel1.close();
        } catch (Exception ex) {ex.printStackTrace();}
    }

    ////////////////////////////////////////////////////////////////

    ////////////Read device//////////////////////////

    private boolean readDevice(){
        MpusbWrapperTwo mwt = new MpusbWrapperTwo();
        int[] commands = new int[2];
        commands[0] = Integer.decode(textSendCommand.getText());
        commands[1] = Integer.decode(textSendCommand2.getText());
        return mwt.readData(commands, frame, this);
    }

    private boolean readCarNo() {
        MpusbWrapperTwo mwt = new MpusbWrapperTwo();
        int[] commands = new int[2];
        commands[0] = 0x81;
        commands[1] = 0x00;
        if (mwt.readWagonNumber(commands, this)) {
           JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Коефіцієнти завантажено.");
            System.out.println("CoeffPresOne = " + coeffPresOnePmax);
            System.out.println("CoeffPresTwo = " + coeffPresTwoPmax);

            return true;
        } else
            return false;
    }

    private boolean readLog() {
        MpusbWrapperTwo mwt = new MpusbWrapperTwo();
        int[] commands = new int[2];
        commands[0] = 0x91;
        commands[1] = 0x00;
        if (mwt.readLog(commands, this))
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Лог прочитано");
        return true;
    }


    private boolean loadCarNo(){
        MpusbWrapperTwo mwt = new MpusbWrapperTwo();

        int[] commands = new int[2];
        commands[0] = Integer.decode(textSendCommand.getText());
        commands[1] = Integer.decode(textSendCommand2.getText());

        if (mwt.setWagonNumber(commands, this))
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Номер вагона перезаписано");
        return true;
    }

    private boolean loadCoefficients() {
        MpusbWrapperTwo mwt = new MpusbWrapperTwo();

        int[] commands = new int[2];
        commands[0] = Integer.decode(textSendCommand.getText());
        commands[1] = Integer.decode(textSendCommand2.getText());

        if (mwt.setCoefficients(commands, this))
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Коефіцієнти презаписано");
        return true;
    }

    ///////////////////Read file to Database////////////////////////
    private void readCoeffs(){
        final File file = new File("out6.bin");
        System.out.println("Opening file...");
        final ReadBinFile rbf = new ReadBinFile();
        try {
            byte[] d1 = rbf.getBytesFromFileServiceData(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        textReadCoefOneUmin.setText(String.valueOf(coeffPresOneUmin));
//        textReadCoefOneUmax.setText(String.valueOf(coeffPresOneUmax));
//        textReadCoefOnePmax.setText(String.valueOf(coeffPresOnePmax));
//
//        textReadCoefTwoUmin.setText(String.valueOf(coeffPresTwoUmin));
//        textReadCoefTwoUmax.setText(String.valueOf(coeffPresTwoUmax));
//        textReadCoefTwoPmax.setText(String.valueOf(coeffPresTwoPmax));

        textReadCoefOneUmin.setText(df.format(coeffPresOneUmin));
        textReadCoefOneUmax.setText(df.format(coeffPresOneUmax));
        textReadCoefOnePmax.setText(df.format(coeffPresOnePmax));

        textReadCoefTwoUmin.setText(df.format(coeffPresTwoUmin));
        textReadCoefTwoUmax.setText(df.format(coeffPresTwoUmax));
        textReadCoefTwoPmax.setText(df.format(coeffPresTwoPmax));

    }



    private void readDb(){
         final File file = new File("out6.bin");
System.out.println("Opening file...");

            final ReadBinFile rbf = new ReadBinFile();
            /////////////***********************************************
System.out.println("Creating dialog...");
//            final JDialog dlg = new JDialog(frame, "Progress Dialog", true);
//            JProgressBar dpb = new JProgressBar(0, 12582912);
//            dlg.add(BorderLayout.CENTER, dpb);
//            dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
//            dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//            dlg.setSize(300, 75);
//            dlg.setLocationRelativeTo(frame);
//System.out.println("Starting thread...");
//            Thread t = new Thread(new Runnable() {
//                public void run() {
//                    dlg.setVisible(true);
//System.out.println("Inside thread..");
                    try {
System.out.println("Starting method...");
//                        byte[] d1 = rbf.getBytesFromFileServiceData(file, this);
                        byte[] d = rbf.getBytesFromFileTwo(file, frame, this);
System.out.println("Inside the method...");
//                        progressBar2.setValue(rbf.percent);
                    } catch (IOException e) {}
//                }
//            });
//            t.start();
//            for (int i = 0; i <= 12582912; i++) {
//                //jl.setText("Count : " + i);
//                dpb.setValue(rbf.percent);
//                if(dpb.getValue() == 12582912){
//                    dlg.setVisible(false);
////                    System.exit(0);
//
//                }
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            dlg.setVisible(true);

            /////////////***********************************************

    }

    public void convertToPDF(JFreeChart chart, int width2, int height2, String filename) {
        Document document = new Document(/*new Rectangle(width, height)*/);

        FontMapper arialuni = new FontMapper() {
            public BaseFont awtToPdf(Font font) {
                try {
                    return BaseFont.createFont(
//                            "c:/windows/fonts/arialuni.ttf",
                            "arialuni.ttf",
                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public Font pdfToAwt(BaseFont font, int size) {
                return null;
            }

        };


        try {

            PdfWriter writer;
            writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

            document.open();
            document.addAuthor("Author");
            document.addTitle("New Title");
//            document.add(new Paragraph("SOme text для вагонів"));

            PdfContentByte cb = writer.getDirectContent();

            float width = PageSize.A4.getWidth() - 10;
            float height = PageSize.A4.getHeight() / 2;



//            PdfTemplate tpSpeed = cb.createTemplate(200, 200);
//            Graphics2D g2d1  = new PdfGraphics2D(tpSpeed, 200, 200, arialuni);
//            Rectangle2D r2d1 = new Rectangle2D.Double(0, 0, 200, 200);
//            chSpeed.draw(g2d1, r2d1);
//            g2d1.dispose();
 //           cb.addTemplate(tpSpeed, 0, 0);
 //           cb.addTemplate(tpSpeed, 0, 200);
            System.out.println("Height speed = " + height / 2);



            PdfTemplate tpSpeed = cb.createTemplate(width, 140);
            Graphics2D g2d0  = new PdfGraphics2D(tpSpeed, width, 140, arialuni);
            Rectangle2D r2d0 = new Rectangle2D.Double(0, 0, width, 140);
            chSpeed.draw(g2d0, r2d0);
            g2d0.dispose();
            cb.addTemplate(tpSpeed, 0, 700);


//            cb.addTemplate(tpSpeed, 0, 200);

            PdfTemplate tpPressures = cb.createTemplate(width, 120);
            Graphics2D g2d2  = new PdfGraphics2D(tpPressures, width, 120, arialuni);
            Rectangle2D r2d2 = new Rectangle2D.Double(0, 0, width, 120);
            chPres1.draw(g2d2, r2d2);
            g2d2.dispose();
            cb.addTemplate(tpPressures, 0, 580);

            int digSigsNum = digSigsCalc();
            int digHeight;

            if (digSigsNum > 21) digHeight = digSigsNum * 15 + 30;
            else digHeight = digSigsNum * 15 + 60 + 30;

            PdfTemplate tpDigits = cb.createTemplate(width + 10, digHeight);
            Graphics2D g2d3  = new PdfGraphics2D(tpDigits, width + 10, digHeight, arialuni);
            Rectangle2D r2d3 = new Rectangle2D.Double(0, 0, width + 10, digHeight);
            chAllDigits2.draw(g2d3, r2d3);
            g2d3.dispose();
            cb.addTemplate(tpDigits, 0, 580 - digHeight);



//            PdfTemplate tpDigits = cb.createTemplate(width + 10, 580);
//            Graphics2D g2d3  = new PdfGraphics2D(tpDigits, width + 10, 580, arialuni);
//            Rectangle2D r2d3 = new Rectangle2D.Double(0, 0, width + 10, 580);
//            chAllDigits2.draw(g2d3, r2d3);
//            g2d3.dispose();
//            cb.addTemplate(tpDigits, 0, 0);


            System.out.println("Height pressure = " + height / 2);


            document.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не вдалося зберегти файл..." + e.getStackTrace());

            e.printStackTrace();
        }

    }

    private String populateCombo() {
        String wagonFolderStr = "";
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Оберіть теку з номером вагона: ");
        fc.setCurrentDirectory(new java.io.File("/mybackups/")); // start at application current directory
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File wagonFolder = fc.getSelectedFile();
            wagonFolderStr = wagonFolder.getAbsolutePath().toString();
            System.out.println("File folder: " + wagonFolder);
            File[] datesFolders = wagonFolder.listFiles();

            for (int i = 0; i < datesFolders.length; i++) {
                if (datesFolders[i].isDirectory()) {
                    comboDate.addItem(datesFolders[i].getName());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No folder was chosen...");
        }
        wagonFolderStr = wagonFolderStr.replaceAll("\\\\", "/");
        return wagonFolderStr + "/";
    }

    public void hideSpinnerArrow(JSpinner spinner) {
        Dimension d = spinner.getPreferredSize();
        d.width = 20;
        spinner.setUI(new BasicSpinnerUI() {
            protected Component createNextButton() {
                return null;
            }

            protected Component createPreviousButton() {
                return null;
            }
        });
        spinner.setPreferredSize(d);
    }





    ////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        //set Look and Feel "Nimbus" theme
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        frame = new JFrame("Реєстратор вагонний. Демо версія.");
        frame.setContentPane(new FormTwo(frame).panelOne);
        //frame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        frame.setMinimumSize(new Dimension(1100, 800));
//        frame.setLocation((dim.width - frame.getWidth()) / 4, (dim.height - frame.getHeight()) / 8);

        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    public void findFile(String name, File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(name, fil);
                }
                else if (name.equalsIgnoreCase(fil.getName())) {
                    System.out.println(fil.getParentFile());
                    textAreaLogTime.setText(textAreaLogTime.getText() + "\n" + fil.getParentFile().toString());
                }
            }
        }
    }

}
