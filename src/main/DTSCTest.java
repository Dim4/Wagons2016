package main;

/**
 * Created by DM on 20.04.2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.SocketPermission;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

import com.sun.prism.shader.Solid_ImagePattern_Loader;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPPart1_1Impl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;


/** @see http://stackoverflow.com/questions/5048852 */
//public class DTSCTest extends ApplicationFrame {
public class DTSCTest extends JDialog {

    private JCheckBox Speed;
    private JCheckBox Pres1;
    private JCheckBox Pres2;
    private JCheckBox Dig1;
    private JCheckBox Dig2;
    private JCheckBox Dig3;
    private JCheckBox Dig4;
    private JCheckBox Dig5;
    private JCheckBox Dig6;
    private JCheckBox Dig7;
    private JCheckBox Dig8;
    private JCheckBox Dig9;
    private JCheckBox Dig10;
    private JCheckBox Dig11;
    private JCheckBox Dig12;
    private JCheckBox Dig13;
    private JCheckBox Dig14;
    private JCheckBox Dig15;
    private JCheckBox Dig16;
    private JCheckBox Dig17;
    private JCheckBox Dig18;
    private JCheckBox Dig19;
    private JCheckBox Dig20;
    private JCheckBox Dig21;
    private JCheckBox Dig22;
    private JCheckBox Dig23;
    private JCheckBox Dig24;
    private JCheckBox Dig25;
    private JCheckBox Dig26;
    private JCheckBox Dig27;
    private JCheckBox Dig28;
    private JCheckBox Dig29;
    private JCheckBox Dig30;
    private JCheckBox Dig31;
    private JCheckBox Dig32;
    private JCheckBox Dig33;
    private JCheckBox Dig34;
    private JCheckBox Dig35;
    private JCheckBox Dig36;
    private JCheckBox Dig37;
    private JScrollPane scrollGraphs;

    private ChartPanel graphsSpeed = null;
    private ChartPanel graphsPressure = null;
    private ChartPanel graphsDigital = null;




    private static final String TITLE = "Dynamic Series";
    private static final String START = "Старт";
    private static final String STOP = "Стоп";
    private static final float MINMAX = 100;
    private static final int COUNT = 4 * 60 * 4;
//    private static final int FAST = 100;
    private static final int FAST = 250;

    private static final int SLOW = FAST * 5;
    private static final Random random = new Random();
    private Timer timer;
    MpusbWrapperTwo mwt = new MpusbWrapperTwo();

    public DTSCTest(final FormTwo frmTwo) {
//        super(title);
        setResizable(false);
        setModal(true);

        int[] commands = new int[2];

        commands[0] = 0x81;
        commands[1] = 0x00;

//        if (mwt.readWagonNumber(commands, frmTwo)){
//
//        }

        if (!mwt.openRT()) {
            this.dispose();
        }

        float[] emptyData = new float[COUNT];
//        emptyData[10] = 50;
//        emptyData[11] = 20;

        for (int i = 0; i < COUNT; i++) {
            emptyData[i] = Float.NaN;
        }

        float[] newData1 = new float[3];

        Date currentDate = new Date();

        final DynamicTimeSeriesCollection dataset =
                new DynamicTimeSeriesCollection(3, COUNT, new Second(currentDate));

        final DynamicTimeSeriesCollection datasetDigitals =
                new DynamicTimeSeriesCollection(38, COUNT, new Second(currentDate));



        dataset.setTimeBase(new Second(/*0,*/ 0, 0, 0, 1, 1, 2017));
 //       dataset.setTimeBase(new Second(currentDate));

//        dataset.addSeries(new float[]{Float.NaN}, 0, "Gaussian data");
//        dataset.addSeries(new float[]{Float.NaN}, 1, "Gaussian data2");
//        dataset.addSeries(new float[]{Float.NaN}, 2, "Gaussian data3");

//        for (int i = 0; i < COUNT; i++) {
//            dataset.addValue(0, i, 10 + i / 50);
//            dataset.addValue(1, i, 30 + i / 50);
//            dataset.addValue(2, i, 50 + i / 50);
//            dataset.advanceTime();
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        dataset.addSeries(emptyData, 0, "");
//        dataset.addSeries(emptyData, 1, "Gaussian data2");
//        dataset.addSeries(emptyData, 2, "Gaussian data3");

//        for (int i = 0; i < COUNT * 1000 - 1; i++) {
//            newData1[0] = i / 100;
//            newData1[1] = i / 100 + 5;
//            newData1[2] = i * 2 / 100;
//            dataset.advanceTime();
//            dataset.appendData(newData1);
//        }

        final DynamicTimeSeriesCollection dataset1 =
                new DynamicTimeSeriesCollection(3, COUNT, new Second());
        dataset1.setTimeBase(new Second(/*0,*/ 0, 0, 0, 1, 1, 2017));
        dataset1.addSeries(emptyData, 0, "");
        dataset1.addSeries(emptyData, 1, "");

        datasetDigitals.setTimeBase(new Second(/*0,*/ 0, 0, 0, 1, 1, 2017));
        datasetDigitals.addSeries(emptyData, 0, "");
        datasetDigitals.addSeries(emptyData, 1, "");
        datasetDigitals.addSeries(emptyData, 2, "");
        datasetDigitals.addSeries(emptyData, 3, "");
        datasetDigitals.addSeries(emptyData, 4, "");
        datasetDigitals.addSeries(emptyData, 5, "");
        datasetDigitals.addSeries(emptyData, 6, "");
        datasetDigitals.addSeries(emptyData, 7, "");
        datasetDigitals.addSeries(emptyData, 8, "");
        datasetDigitals.addSeries(emptyData, 9, "");
        datasetDigitals.addSeries(emptyData, 10, "");
        datasetDigitals.addSeries(emptyData, 11, "");
        datasetDigitals.addSeries(emptyData, 12, "");
        datasetDigitals.addSeries(emptyData, 13, "");
        datasetDigitals.addSeries(emptyData, 14, "");
        datasetDigitals.addSeries(emptyData, 15, "");
        datasetDigitals.addSeries(emptyData, 16, "");
        datasetDigitals.addSeries(emptyData, 17, "");
        datasetDigitals.addSeries(emptyData, 18, "");
        datasetDigitals.addSeries(emptyData, 19, "");
        datasetDigitals.addSeries(emptyData, 20, "");
        datasetDigitals.addSeries(emptyData, 21, "");
        datasetDigitals.addSeries(emptyData, 22, "");
        datasetDigitals.addSeries(emptyData, 23, "");
        datasetDigitals.addSeries(emptyData, 24, "");
        datasetDigitals.addSeries(emptyData, 25, "");
        datasetDigitals.addSeries(emptyData, 26, "");
        datasetDigitals.addSeries(emptyData, 27, "");
        datasetDigitals.addSeries(emptyData, 28, "");
        datasetDigitals.addSeries(emptyData, 29, "");
        datasetDigitals.addSeries(emptyData, 30, "");
        datasetDigitals.addSeries(emptyData, 31, "");
        datasetDigitals.addSeries(emptyData, 32, "");
        datasetDigitals.addSeries(emptyData, 33, "");
        datasetDigitals.addSeries(emptyData, 34, "");
        datasetDigitals.addSeries(emptyData, 35, "");
        datasetDigitals.addSeries(emptyData, 36, "");
        datasetDigitals.addSeries(emptyData, 37, "");

        JFreeChart chart = createChart(dataset);
        JFreeChart chart2 = createChart2(dataset1);

        final JComboBox combo = new JComboBox();
        combo.addItem("Fast");
        combo.addItem("Slow");

        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Fast".equals(combo.getSelectedItem())) {
                    timer.setDelay(FAST);
                } else {
                    timer.setDelay(SLOW);
                }
            }
        });

//        this.add(new ChartPanel(chart), BorderLayout.NORTH);
//        this.add(new ChartPanel(chart2), BorderLayout.CENTER);

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel graphsPanel = new JPanel(new BorderLayout());
        this.add(graphsPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel speedLabel = new JLabel("Вікно = ");
        speedLabel.setAlignmentX(LEFT_ALIGNMENT);

        northPanel.add(speedLabel);

        northPanel.add(new JLabel());

//        graphsPanel.add(new ChartPanel(chart2), BorderLayout.CENTER);

        JScrollPane checksScroll = new JScrollPane();
        this.add(checksScroll, BorderLayout.WEST);
        final JPanel checksPanel = addCheckboxes();
        checksScroll.add(checksPanel);
        checksScroll.setViewportView(checksPanel);

        graphsPanel.setLayout(new BoxLayout(graphsPanel, BoxLayout.Y_AXIS));
        graphsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        graphsPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
        graphsPanel.setBackground(Color.white);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        scrollGraphs = new JScrollPane();
//        scrollGraphs.setPreferredSize(new Dimension(1000, 650));
        scrollGraphs.setPreferredSize(new Dimension(1000, (int)heightScreen - 160));

        scrollGraphs.add(graphsPanel);
        scrollGraphs.setViewportView(graphsPanel);
        scrollGraphs.setAlignmentY(Component.LEFT_ALIGNMENT);
        scrollGraphs.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(scrollGraphs, BorderLayout.CENTER);

//        graphsSpeed = null;
//        graphsPressure = null;
//        graphsDigital = null;

//        graphsPanel.add(graphsSpeed);
//        graphsPanel.add(graphsPressure);
//        graphsPanel.add(graphsDigital);

        final JLabel timeStartIndent = new JLabel();
        final JLabel timeStart = new JLabel();
        final JLabel timeQuarterIndent = new JLabel();
        final JLabel timeQuarter = new JLabel();
        final JLabel timeHalfIndent = new JLabel();
        final JLabel timeHalf = new JLabel();
        final JLabel timeThreeQuartersIndent = new JLabel();
        final JLabel timeThreeQuarters = new JLabel();
        final JLabel timeFullIndent = new JLabel();
        final JLabel timeFull = new JLabel();

        final JTextField domainRange = new JTextField();
        domainRange.setToolTipText("Діапазон 20-240 секунд");
        domainRange.setDocument(new IntegerDocument());
        domainRange.setPreferredSize(new Dimension(50, 25));
        domainRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(domainRange.getText()) < 20)
                    domainRange.setText("20");
                else if (Integer.parseInt(domainRange.getText()) > 240)
                    domainRange.setText("240");
                graphsSpeed.getChart().getXYPlot().getDomainAxis().setFixedAutoRange(Integer.parseInt(domainRange.getText()) * 4 * 1000);
                graphsPressure.getChart().getXYPlot().getDomainAxis().setFixedAutoRange(Integer.parseInt(domainRange.getText()) * 4 * 1000);
                graphsDigital.getChart().getXYPlot().getDomainAxis().setFixedAutoRange(Integer.parseInt(domainRange.getText()) * 4 * 1000);
                timeQuarter.setText(Integer.parseInt(domainRange.getText()) / 4 + "с");
                timeHalf.setText(Integer.parseInt(domainRange.getText()) / 2 + "с");
                timeThreeQuarters.setText(Integer.parseInt(domainRange.getText()) / 4 * 3 + "с");
                timeFull.setText(Integer.parseInt(domainRange.getText()) + "с");
            }
        });
//        domainRange.setLocation(100, 1000);
        northPanel.add(domainRange);

        timeStartIndent.setText("                                                        ");
        timeStart.setText("0 с");
        timeQuarterIndent.setText("                                                         ");
        timeQuarter.setText("15 с");
        timeHalfIndent.setText("                                                         ");
        timeHalf.setText("30 с");
        timeThreeQuartersIndent.setText("                                                         ");
        timeThreeQuarters.setText("45 с");
        timeFullIndent.setText("                                                         ");
        timeFull.setText("60 с");


        northPanel.add(timeStartIndent);
        northPanel.add(timeStart);
        northPanel.add(timeQuarterIndent);
        northPanel.add(timeQuarter);
        northPanel.add(timeHalfIndent);
        northPanel.add(timeHalf);
        northPanel.add(timeThreeQuartersIndent);
        northPanel.add(timeThreeQuarters);
        northPanel.add(timeFullIndent);
        northPanel.add(timeFull);


//        graphsPanel.add(new ChartPanel(chart2), BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                frmTwo.enable();
                mwt.closeRT();
//                System.exit(0);
            }
        });

        final JButton run = new JButton(START);
        run.setPreferredSize(new Dimension(120, 25));

        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    timer.stop();

                    int nChecks = checksPanel.getComponentCount();
                    for (int i = 0; i < nChecks; i++) {
                        checksPanel.getComponent(i).setEnabled(true);
                    }

                    run.setText(START);
                } else {
                    System.out.println("Component count: " + graphsPanel.getComponentCount());

                    switch (graphsPanel.getComponentCount()){
                        case 1:
                            graphsPanel.remove(graphsPanel.getComponent(0));
                            break;
                        case 2:
                            graphsPanel.remove(graphsPanel.getComponent(0));
                            graphsPanel.remove(graphsPanel.getComponent(0));
                            break;
                        case 3:
                            graphsPanel.remove(graphsPanel.getComponent(0));
                            graphsPanel.remove(graphsPanel.getComponent(0));
                            graphsPanel.remove(graphsPanel.getComponent(0));
                        default:
                            break;
                    }

                    if (Speed.isSelected()) {
                        graphsSpeed = addAnalogGraph("Швидкість", dataset, 0);
                        graphsPanel.add(graphsSpeed);
                    }
                    if (Pres1.isSelected() || Pres2.isSelected()) {
                        graphsPressure = addAnalogGraph("Тиск", dataset1, 1);
                        graphsPanel.add(graphsPressure);
                    }
                    if (Dig1.isSelected() || Dig2.isSelected() || Dig3.isSelected() || Dig4.isSelected() || Dig5.isSelected() ||
                        Dig6.isSelected() || Dig7.isSelected() || Dig8.isSelected() || Dig9.isSelected() || Dig10.isSelected() ||
                        Dig11.isSelected() || Dig12.isSelected() || Dig13.isSelected() || Dig14.isSelected() || Dig15.isSelected() ||
                        Dig16.isSelected() || Dig17.isSelected() || Dig18.isSelected() || Dig19.isSelected() || Dig20.isSelected() ||
                        Dig21.isSelected() || Dig22.isSelected() || Dig23.isSelected() || Dig24.isSelected() || Dig25.isSelected() ||
                        Dig26.isSelected() || Dig27.isSelected() || Dig28.isSelected() || Dig29.isSelected() || Dig30.isSelected() ||
                        Dig31.isSelected() || Dig32.isSelected() || Dig33.isSelected() || Dig34.isSelected() || Dig35.isSelected() ||
                        Dig36.isSelected() || Dig37.isSelected()) {

                        graphsDigital = addDigitalGraph("Цифрові сигнали", datasetDigitals);
                        graphsPanel.add(graphsDigital);
                    }
                    graphsPanel.repaint();

                    int nChecks = checksPanel.getComponentCount();
                    for (int i = 0; i < nChecks; i++) {
                        checksPanel.getComponent(i).setEnabled(false);
                    }

                    timer.start();
                    run.setText(STOP);
                }
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);

 //       btnPanel.add(combo);
        this.add(btnPanel, BorderLayout.SOUTH);


        timer = new Timer(/*FAST / 25 * 1*/250, new ActionListener() {
            float[] newData = new float[1];
            float[] newData1 = new float[2];
            float[] newDataDigits = new float[38];
            int k = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

 //               for (int j = 0; j < 4; j++) {
                    byte[] d1 = mwt.readRT();

                k++;

                    if (d1.length > 1) {

                        if (d1.length == 3 && d1[0] == -1 && d1[1] == -1 && d1[2] == -1 && run.getText().equals("Стоп")) {
                            timer.stop();
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо зчитати дані .\n" +
                                    "Перевірте підключення пристрою і повторіть операцію.");
                            mwt.closeRT();
                            DTSCTest.this.dispose();
//                            System.exit(0);
                        } else if (d1.length > 5){


                            byte[] speed = new byte[4];
                            byte[] presOne = new byte[4];
                            byte[] presTwo = new byte[4];
                            byte[] num = new byte[2];

                            speed[0] = d1[5];
                            speed[1] = d1[6];
                            speed[2] = d1[7];
                            speed[3] = d1[8];

                            presOne[0] = d1[9];
                            presOne[1] = d1[10];
                            presOne[2] = d1[11];
                            presOne[3] = d1[12];

                            presTwo[0] = d1[13];
                            presTwo[1] = d1[14];
                            presTwo[2] = d1[15];
                            presTwo[3] = d1[16];

                            num[0] = d1[17];
                            num[1] = d1[18];

                            byte[] digits = new byte[5];

                            digits[0] = d1[0];
                            digits[1] = d1[1];
                            digits[2] = d1[2];
                            digits[3] = d1[3];
                            digits[4] = d1[4];

                            boolean[] digitalSet1 = appendAllDigits(digits);

                            for (int i = 0; i < /*37*/digitalSet1.length - 2; i++) {
                                if (digitalSet1[i] == false) {
                                    System.out.print(0);
                                    newDataDigits[i] = Float.NaN;
                                } else {
                                    System.out.print(1);
                                    newDataDigits[i] = i + 1;
                                }
                            }
                            System.out.println();


                            double speedDouble = 16000000.0 / (double) mwt.mergeFourBytes(speed);
                            double numDouble = mwt.mergeTwoBytes(num);
                            double presOneDouble = mwt.mergeFourBytes(presOne) / numDouble;
                            double presTwoDouble = mwt.mergeFourBytes(presTwo) / numDouble;

//                        newData[0] = (float) speedDouble * 0.18f;
//                        newData1[0] = (float) presOneDouble / 1024 * 10;
//                        newData1[1] = (float) presTwoDouble / 1024 * 10;

//                            newData[0] = (float) speedDouble * (float) frmTwo.coeffSpeed;
//                            newData1[0] = (float) ((float) (Math.round((double) ((presOneDouble * 0.0032258 - frmTwo.coeffPresOneUmin * 0.641026) * (frmTwo.coeffPresOnePmax / ((frmTwo.coeffPresOneUmax - frmTwo.coeffPresOneUmin) * 0.641026)) * 10.0))) / 10.0);
//                            newData1[1] = (float) ((float) (Math.round((double) ((presTwoDouble * 0.0032258 - frmTwo.coeffPresTwoUmin * 0.641026) * (frmTwo.coeffPresTwoPmax / ((frmTwo.coeffPresTwoUmax - frmTwo.coeffPresTwoUmin) * 0.641026)) * 10.0))) / 10.0);

                            newData[0] = (float) speedDouble * 0.18f * Float.parseFloat(frmTwo.coeffSpeedRead.getText().replaceAll(",", "."));
                            newData1[0] = (float) ((float) (Math.round((double) ((presOneDouble * 0.0032258 - Float.parseFloat(frmTwo.textReadCoefOneUmin.getText().replaceAll(",", ".")) * 0.641026) * (Float.parseFloat(frmTwo.textReadCoefOnePmax.getText().replaceAll(",", ".")) / ((Float.parseFloat(frmTwo.textReadCoefOneUmax.getText().replaceAll(",", ".")) - Float.parseFloat(frmTwo.textReadCoefOneUmin.getText().replaceAll(",", "."))) * 0.641026)) * 10.0))) / 10.0);
                            newData1[1] = (float) ((float) (Math.round((double) ((presTwoDouble * 0.0032258 - Float.parseFloat(frmTwo.textReadCoefTwoUmin.getText().replaceAll(",", ".")) * 0.641026) * (Float.parseFloat(frmTwo.textReadCoefTwoPmax.getText().replaceAll(",", ".")) / ((Float.parseFloat(frmTwo.textReadCoefTwoUmax.getText().replaceAll(",", ".")) - Float.parseFloat(frmTwo.textReadCoefTwoUmin.getText().replaceAll(",", "."))) * 0.641026)) * 10.0))) / 10.0);

//                            newData1[1] = (float) ((float) (Math.round((double) ((presTwoDouble * 0.0032258 - frmTwo.coeffPresTwoUmin * 0.641026) * (frmTwo.coeffPresTwoPmax / ((frmTwo.coeffPresTwoUmax - frmTwo.coeffPresTwoUmin) * 0.641026)) * 10.0))) / 10.0);


                        }
                    }


//                    for (int i = 0; i < 38; i++) {
//                        newDataDigits[i] = i + 1;
//                    }

//                dataset.addValue(0, COUNT, newData[0]);
//                Date currentDate = new Date();
//                dataset.setTimeBase(new Millisecond());



                    //                for (int i = 0; i < 250; i++) {
//                    if (k % 4 == 0) {
                        dataset.advanceTime();
                        dataset1.advanceTime();
                        datasetDigitals.advanceTime();
//                    }
//                }
                    dataset.appendData(newData);
                    dataset1.appendData(newData1);
                    datasetDigitals.appendData(newDataDigits);
 //               }
            }
        });
    }

    static boolean[] bits(byte b) {
        int n = 8;
        final boolean[] set = new boolean[n];
        while (--n >= 0) {
            set[n] = (b & 0x80) != 0;
            b <<= 1;
        }
        return set;
    }

    static boolean[] appendAllDigits(byte[] bytes) {
        int n = 40;
        final boolean[] set = new boolean[n];
        for (int i = 0; i < 8; i++) {
            set[i] = bits(bytes[0])[i];
        }
        for (int i = 8; i < 16; i++) {
            set[i] = bits(bytes[1])[i - 8];
        }
        for (int i = 16; i < 24; i++) {
            set[i] = bits(bytes[2])[i - 16];
        }
        for (int i = 24; i < 32; i++) {
            set[i] = bits(bytes[3])[i - 24];
        }
        for (int i = 32; i < 40; i++) {
            set[i] = bits(bytes[4])[i - 32];
        }

        return set;
    }

    private float randomValue() {
        return (float) (random.nextGaussian() * MINMAX / 3);
    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue();
        }
        return a;
    }

    private float[] gaussianData1() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue() + 1;
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-MINMAX, MINMAX);
        return result;
    }

    private JFreeChart createChart2(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        domain.setFixedAutoRange(60);
//        domain.setRange(Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis() + 30000);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-2, 1100);
        return result;
    }

    private JPanel addCheckboxes(){
        JPanel checksPanel = new JPanel(new GridLayout(40, 1));
        checksPanel.setPreferredSize(new Dimension(140, 400));
        checksPanel.setAutoscrolls(true);

        Speed = new JCheckBox();
        Speed.setSelected(true);
        Speed.setText("Швидкість");
        Speed.setPreferredSize(new Dimension(-1, 70));
        Speed.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Speed);

        Pres1 = new JCheckBox();
        Pres1.setSelected(true);
        Pres1.setText("Тиск НМ");
        Pres1.setPreferredSize(new Dimension(-1, 70));
        Pres1.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Pres1);

        Pres2 = new JCheckBox();
        Pres2.setSelected(true);
        Pres2.setText("Тиск ГМ");
        Pres2.setPreferredSize(new Dimension(-1, 70));
        Pres2.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Pres2);

        Dig1 = new JCheckBox();
        Dig1.setSelected(true);
        Dig1.setText("АРШ ввім.");
        Dig1.setPreferredSize(new Dimension(-1, 70));
        Dig1.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig1);

        Dig2 = new JCheckBox();
        Dig2.setSelected(true);
        Dig2.setText("АЛС ввім.");
        Dig2.setPreferredSize(new Dimension(-1, 70));
        Dig2.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig2);

        Dig3 = new JCheckBox();
        Dig3.setSelected(true);
        Dig3.setText("Радіост. ввім.");
        Dig3.setPreferredSize(new Dimension(-1, 70));
        Dig3.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig3);

        Dig4 = new JCheckBox();
        Dig4.setSelected(true);
        Dig4.setText("ЕПК ввімк.");
        Dig4.setPreferredSize(new Dimension(-1, 70));
        Dig4.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig4);

        Dig5 = new JCheckBox();
        Dig5.setSelected(true);
        Dig5.setText("РП відновл");
        Dig5.setPreferredSize(new Dimension(-1, 70));
        Dig5.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig5);

        Dig6 = new JCheckBox();
        Dig6.setSelected(true);
        Dig6.setText("Рух від КАХ");
        Dig6.setPreferredSize(new Dimension(-1, 70));
        Dig6.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig6);

        Dig7 = new JCheckBox();
        Dig7.setSelected(true);
        Dig7.setText("Ввімк. ВАХ");
        Dig7.setPreferredSize(new Dimension(-1, 70));
        Dig7.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig7);

        Dig8 = new JCheckBox();
        Dig8.setSelected(true);
        Dig8.setText("Ввімк. ВАД");
        Dig8.setPreferredSize(new Dimension(-1, 70));
        Dig8.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig8);

        Dig9 = new JCheckBox();
        Dig9.setSelected(true);
        Dig9.setText("Ввімк. ОВТ");
        Dig9.setPreferredSize(new Dimension(-1, 70));
        Dig9.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig9);

        Dig10 = new JCheckBox();
        Dig10.setSelected(true);
        Dig10.setText("Зачин. дверей");
        Dig10.setPreferredSize(new Dimension(-1, 70));
        Dig10.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig10);

        Dig11 = new JCheckBox();
        Dig11.setSelected(true);
        Dig11.setText("КБ(ПБ)");
        Dig11.setPreferredSize(new Dimension(-1, 70));
        Dig11.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig11);

        Dig12 = new JCheckBox();
        Dig12.setSelected(true);
        Dig12.setText("Відсутня част.");
        Dig12.setPreferredSize(new Dimension(-1, 70));
        Dig12.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig12);

        Dig13 = new JCheckBox();
        Dig13.setSelected(true);
        Dig13.setText("Доп.шв.0 км/г");
        Dig13.setPreferredSize(new Dimension(-1, 70));
        Dig13.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig13);

        Dig14 = new JCheckBox();
        Dig14.setSelected(true);
        Dig14.setText("Доп.шв. 40 км/г");
        Dig14.setPreferredSize(new Dimension(-1, 70));
        Dig14.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig14);

        Dig15 = new JCheckBox();
        Dig15.setSelected(true);
        Dig15.setText("Доп.шв. 60 км/г");
        Dig15.setPreferredSize(new Dimension(-1, 70));
        Dig15.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig15);

        Dig16 = new JCheckBox();
        Dig16.setSelected(true);
        Dig16.setText("Доп.шв. 70 км/г");
        Dig16.setPreferredSize(new Dimension(-1, 70));
        Dig16.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig16);

        Dig17 = new JCheckBox();
        Dig17.setSelected(true);
        Dig17.setText("Доп.шв. 80 км/г");
        Dig17.setPreferredSize(new Dimension(-1, 70));
        Dig17.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig17);

        Dig18 = new JCheckBox();
        Dig18.setSelected(true);
        Dig18.setText("Стоян. гальмо");
        Dig18.setPreferredSize(new Dimension(-1, 70));
        Dig18.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig18);

        Dig19 = new JCheckBox();
        Dig19.setSelected(true);
        Dig19.setText("Ввімк.бл.перетв.");
        Dig19.setPreferredSize(new Dimension(-1, 70));
        Dig19.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig19);

        Dig20 = new JCheckBox();
        Dig20.setSelected(true);
        Dig20.setText("КРП");
        Dig20.setPreferredSize(new Dimension(-1, 70));
        Dig20.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig20);

        Dig21 = new JCheckBox();
        Dig21.setSelected(true);
        Dig21.setText("1 пп");
        Dig21.setPreferredSize(new Dimension(-1, 70));
        Dig21.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig21);

        Dig22 = new JCheckBox();
        Dig22.setSelected(true);
        Dig22.setText("2 пп");
        Dig22.setPreferredSize(new Dimension(-1, 70));
        Dig22.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig22);

        Dig23 = new JCheckBox();
        Dig23.setSelected(true);
        Dig23.setText("3 пп.");
        Dig23.setPreferredSize(new Dimension(-1, 70));
        Dig23.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig23);

        Dig24 = new JCheckBox();
        Dig24.setSelected(true);
        Dig24.setText("4 пп.");
        Dig24.setPreferredSize(new Dimension(-1, 70));
        Dig24.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig24);

        Dig25 = new JCheckBox();
        Dig25.setSelected(true);
        Dig25.setText("5 пп.");
        Dig25.setPreferredSize(new Dimension(-1, 70));
        Dig25.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig25);

        Dig26 = new JCheckBox();
        Dig26.setSelected(true);
        Dig26.setText("6 пп.");
        Dig26.setPreferredSize(new Dimension(-1, 70));
        Dig26.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig26);

        Dig27 = new JCheckBox();
        Dig27.setSelected(true);
        Dig27.setText("Провід Ф7");
        Dig27.setPreferredSize(new Dimension(-1, 70));
        Dig27.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig27);

        Dig28 = new JCheckBox();
        Dig28.setSelected(true);
        Dig28.setText("8 пп");
        Dig28.setPreferredSize(new Dimension(-1, 70));
        Dig28.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig28);

        Dig29 = new JCheckBox();
        Dig29.setSelected(true);
        Dig29.setText("18 пп");
        Dig29.setPreferredSize(new Dimension(-1, 70));
        Dig29.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig29);

        Dig30 = new JCheckBox();
        Dig30.setSelected(true);
        Dig30.setText("20 пп");
        Dig30.setPreferredSize(new Dimension(-1, 70));
        Dig30.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig30);

        Dig31 = new JCheckBox();
        Dig31.setSelected(true);
        Dig31.setText("25 пп");
        Dig31.setPreferredSize(new Dimension(-1, 70));
        Dig31.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig31);

        Dig32 = new JCheckBox();
        Dig32.setSelected(true);
        Dig32.setText("34 пп");
        Dig32.setPreferredSize(new Dimension(-1, 70));
        Dig32.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig32);

        Dig33 = new JCheckBox();
        Dig33.setSelected(true);
        Dig33.setText("48 пп");
        Dig33.setPreferredSize(new Dimension(-1, 70));
        Dig33.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig33);

        Dig34 = new JCheckBox();
        Dig34.setSelected(true);
        Dig34.setText("АРШ");
        Dig34.setPreferredSize(new Dimension(-1, 70));
        Dig34.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig34);

        Dig35 = new JCheckBox();
        Dig35.setSelected(true);
        Dig35.setText("ДАУ-АРШ");
        Dig35.setPreferredSize(new Dimension(-1, 70));
        Dig35.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig35);

        Dig36 = new JCheckBox();
        Dig36.setSelected(true);
        Dig36.setText("ВУ");
        Dig36.setPreferredSize(new Dimension(-1, 70));
        Dig36.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig36);

        Dig37 = new JCheckBox();
        Dig37.setSelected(true);
        Dig37.setText("Вибір напрямку");
        Dig37.setPreferredSize(new Dimension(-1, 70));
        Dig37.setMinimumSize(new Dimension(-1, 70));
        checksPanel.add(Dig37);

        return checksPanel;
    }

    /////////////// CREATE ANALOG GRAPH //////////////////////////////////////////////////////
    private ChartPanel addAnalogGraph(String name, final XYDataset dataset, int signalType) {
        final JFreeChart chartAn2;

        if (signalType == 0) {
            chartAn2 = ChartFactory.createTimeSeriesChart(name + "\n \n \n \n \n ", "", "", dataset, true, true, false);
        }else {
            chartAn2 = ChartFactory.createTimeSeriesChart(name + "\n \n \n \n \n ", "", "", dataset, true, true, false);
        }
        ChartPanel chPAn2 = new ChartPanel(chartAn2, true);

        chPAn2.setMouseZoomable(false);

        chPAn2.setLayout(new GridLayout());

        chartAn2.removeLegend();
        chartAn2.getTitle().setPosition(RectangleEdge.LEFT);

        final XYPlot plot = chartAn2.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        domain.setFixedAutoRange(240000);
//        domain.setRange(Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis() + 30000);
//        ValueAxis range = plot.getRangeAxis();
//        range.setRange(-2, 1100);


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

///////////DATE AXIS RANGE//////////////
        DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);
////        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, 0) + timeSpan * 1000);
////        dateAxis.setRange(jdsAn2.getXValue(0, 0), jdsAn2.getXValue(0, jdsAn2.getItemCount() - 1));
////        dateAxis.setRange(jdsAn2.getXValue(0, 0), ts.getTime());
//        dateAxis.setRange(0, COUNT);


        xyPlot.setDomainAxisLocation(0, AxisLocation.TOP_OR_LEFT);
        xyPlot.setBackgroundPaint(Color.WHITE);
        xyPlot.setDomainGridlinePaint(Color.GRAY);
        xyPlot.setRangeGridlinePaint(Color.GRAY);

//        int width = scrollGraphs.getWidth() - 30;
        int width = 970;


        boolean flagAxis = false;
        if (!flagAxis) {
            flagAxis = true;
//            dateAxis.setVisible(true);
            dateAxis.setVisible(false);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            int heightSpeed = 0;
            if (signalType == 0)
                heightSpeed = 130/* + 20*/;
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
                heightSpeed = 100/* + 20*/;
            else
                heightSpeed = 100;

            chPAn2.setPreferredSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumSize(new Dimension(width, heightSpeed));
            chPAn2.setMinimumSize(new Dimension(width, heightSpeed));
            chPAn2.setMaximumDrawWidth(width);


            chPAn2.setMinimumDrawWidth(100);
            chPAn2.setMaximumDrawHeight(heightSpeed);
            chPAn2.setMinimumDrawHeight(10);
        }
//
        range.setAutoRange(false);
//        chPAn2.getChart().getPlot().setBackgroundPaint(Color.white);
        chPAn2.setAlignmentX(Component.LEFT_ALIGNMENT);
        chPAn2.setAlignmentY(Component.LEFT_ALIGNMENT);

        return chPAn2;
    }

    /////////////// CREATE DIGITAL GRAPH /////////////////////////////////////////////////////
    private ChartPanel addDigitalGraph(String name, final XYDataset dataset) {

        final int digSigsNumber = digSigsCalc();

        String tkLabels[] = makeTickLabels(digSigsNumber);

        JFreeChart chAllDigits2 = ChartFactory.createTimeSeriesChart(name + "", "", "", dataset);
        final ChartPanel chartAllDigits2 = new ChartPanel(chAllDigits2, true);

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
            tkLabels[0] = String.format("%0$-41s","");
            heightDigits = (digSigsNumber + 1) * 17 + 75;
        }

        int width = 882;

        chartAllDigits2.setPreferredSize(new Dimension(width + 135 - 47, heightDigits));
        chartAllDigits2.setMaximumSize(new Dimension(width + 135 - 47, heightDigits));
        chartAllDigits2.setMinimumSize(new Dimension(width + 135 - 47, heightDigits));
        chartAllDigits2.setMaximumDrawWidth(width + 135 - 47);

//        chartAllDigits2.setPreferredSize(new Dimension(width + 47, heightDigits));
//        chartAllDigits2.setMaximumSize(new Dimension(width + 47, heightDigits));
//        chartAllDigits2.setMinimumSize(new Dimension(width + 47, heightDigits));
//        chartAllDigits2.setMaximumDrawWidth(width + 47);

        chartAllDigits2.setMinimumDrawWidth(100);
        chartAllDigits2.setMaximumDrawHeight(heightDigits);
        chartAllDigits2.setMinimumDrawHeight(10);

        XYPlot xyPlot = (XYPlot) chAllDigits2.getPlot();


        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setDomainCrosshairStroke(new BasicStroke(2));
        xyPlot.setDomainCrosshairPaint(Color.blue);

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

        dateAxis.setPositiveArrowVisible(true);
//        dateAxis.setRange(tsStart.getTime(), tsEnd.getTime());
        dateAxis.setAutoRange(true);
//        dateAxis.setFixedAutoRange(600);
        dateAxis.setFixedAutoRange(240000);

        JFreeChart chart7 = chartAllDigits2.getChart();
        XYPlot localXYPlot = (XYPlot) chart7.getPlot();

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

        //chartAllDigits2.setAlignmentX((float) 0.0);
//        chartAllDigits2.setAlignmentX(Component.LEFT_ALIGNMENT);

        chartAllDigits2.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartAllDigits2.setAlignmentY(Component.LEFT_ALIGNMENT);

        return chartAllDigits2;
    }

    private int digSigsCalc() {
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

        System.out.println("Dig sigs number = " + digSigsNumber);

        return digSigsNumber;
    }

    private String[] makeTickLabels(int tickNumber) {
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
        tickLabels[0]                          = String.format("%0$-33s","");

        return tickLabels;
    }

    public void start() {
        timer.start();
    }

    public static void main(final String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                DTSCTest demo = new DTSCTest();
//                demo.pack();
//                RefineryUtilities.centerFrameOnScreen(demo);
//                demo.setVisible(true);
//                demo.start();
//            }
//        });
    }
}
