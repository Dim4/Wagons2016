package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.xy.WindDataset;


public class DBChartExample {
//    private static DatabaseOperations databaseOperations;

//    private static final int N = 30;
//    private static final Random r = new Random();

//    private void display() {
//        JFrame f = new JFrame("JDBCTest");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JDBCXYDataset jds = createDataset();
////        JFreeChart chart = ChartFactory.createScatterPlot("Inventory",
////                "Date", "Count", jds, PlotOrientation.VERTICAL, true, true, false);
//
//        JFreeChart chart = ChartFactory.createTimeSeriesChart("Wagon info",
//                "Date", "Count",  jds);
//
//
////        XYPlot plot = chart.getXYPlot();
////
////        plot.setDomainAxis(new DateAxis("Date"));
//        f.add(new ChartPanel(chart));
//        f.pack();
//        f.setLocationRelativeTo(null);
//        f.setVisible(true);
//        for (int i = 0; i < jds.getItemCount(); i++) {
//            System.out.println(new Date(jds.getX(0, i).longValue()));
//        }
//    }
//
//    private JDBCXYDataset createDataset() {
//        try {
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:derby:wagons");
////            Connection conn = databaseOperations.dbConnection;
//            Statement st = conn.createStatement();
////            st.execute("create table inventory(when date, n1 integer, n2 integer)");
////            PreparedStatement ps = conn.prepareStatement(
////                    "insert into inventory values (?, ?, ?)");
////            Calendar c = Calendar.getInstance();
////            for (int i = 0; i < N; i++) {
////                ps.setDate(1, new Date(c.getTimeInMillis()));
////                ps.setInt(2, N / 3 + r.nextInt(N / 3));
////                ps.setInt(3, N / 2 + r.nextInt(N / 3));
////                ps.execute();
////                c.add(Calendar.MONTH, 1);
////            }
//            JDBCXYDataset jds = new JDBCXYDataset(conn);
//            jds.executeQuery("select wid, speed, pressureinbrakeline, pressureinpumpingmain from app.maintable");
//            return jds;
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.err);
//        }
//        return null;
//    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new DBChartExample().display();
//            }
//        });
//    }
}
