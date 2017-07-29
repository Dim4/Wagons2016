package main;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;

import static main.FormTwo.columnsJointTable;
import static main.FormTwo.databaseInfoJointTable;

/**
 * Created by DM on 09.01.2017.
 */
public class JointTableClass {
    private DefaultTableModel resultTableModel;
    private java.sql.Timestamp ts15;
    private java.sql.Timestamp ts17;
    private String stringStart;

    public String getStringStart() {
        return stringStart;
    }

    public Timestamp getTs15() {
        return ts15;
    }

    public Timestamp getTs17() {
        return ts17;
    }

    public JointTableClass(String spinnerStartStr, String spinnerEndStr, FormTwo parentFrame) {
        int fetchSize = 100;
        Object[] tempRowJointTableDigits = null;

        if (tempRowJointTableDigits != null) System.out.println("OBJECT[] IS NOT NULL!!!!!!!!!!!!-----");

        try {
//            int ofDigits = ofst * 10;
//            int rwsDigits = rws * 10;
//            int ofSpeed = ofst * 4 + 1;
//            int rwsSpeed = rws * 4 + 1;
////////////////////////////// Creating data for Joint Table////////////////////////////////////

            String selectAllDigits = "SELECT * FROM APP.DIGITS " +
                    "left join APP.PRESSURE on APP.DIGITS.TIMESTAMP = APP.PRESSURE.TIMESTAMP" +
                    " left join app.speed on app.digits.timestamp = app.speed.timestamp " +
                    " where APP.DIGITS.TIMESTAMP between '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerEndStr + "'";
                    //"OFFSET " + ofDigits + " ROWS FETCH NEXT " + rwsDigits + " ROWS ONLY";

            String selectAllPressures = "SELECT * FROM APP.PRESSURE";
            String selectAllSpeed = "SELECT * FROM APP.SPEED " +
                    " where APP.SPEED.TIMESTAMP between '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerEndStr + "'";
//                    "OFFSET " + ofSpeed + " ROWS FETCH NEXT " + rwsSpeed + " ROWS ONLY";
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
            resultTableModel = new DefaultTableModel(databaseInfoJointTable, columnsJointTable);

            int numOfColJointTableDigits;

            ResultSetMetaData metaDataJointTableDigits = null;
            ResultSetMetaData metaDataJointTableSpeed = null;

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            numOfColJointTableDigits = metaDataJointTableDigits.getColumnCount();
            System.out.println("Num of columns in joint table: " + numOfColJointTableDigits);

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            if (resultTableModel.getRowCount() > 0) {
                resultTableModel.setNumRows(0);

            }


            System.out.println("rows joint table = " + rowsJointTableDigits.getFetchSize());
            System.out.println("Start draw table");
            String presTableOneStr;
            String presTableTwoStr;
            while (rowsJointTableDigits.next()) {
                java.sql.Timestamp ts = rowsJointTableDigits.getTimestamp(1);

                if (ts.getNanos() % 1000000000 == 0 && Integer.toString(rowsJointTableDigits.getInt(40)) != null){
                    if (rowsJointTableDigits.getString(40) != null) {

//                        double presTableOne = (Math.round((double)((rowsJointTableDigits.getInt(40) * /*(double)coefPresOne **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
//                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * /*(double)coefPresTwo **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
                        double presTableOne = (Math.round((double)((rowsJointTableDigits.getInt(40) * 0.0032258 - parentFrame.coeffPresOneUmin * 0.641026) * (parentFrame.coeffPresOnePmax / ((parentFrame.coeffPresOneUmax - parentFrame.coeffPresOneUmin)*0.641026)) * 10.0))) / 10.0;
//                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * /*(double)coefPresTwo **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * 0.0032258 - parentFrame.coeffPresTwoUmin * 0.641026) * (parentFrame.coeffPresTwoPmax / ((parentFrame.coeffPresTwoUmax - parentFrame.coeffPresTwoUmin)*0.641026)) * 10.0))) / 10.0;

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
                            Double.toString(Math.round(rowsJointTableDigits.getDouble(43))),

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

                //System.out.println(tempRowJointTableDigits);
            }
            System.gc();
            System.out.println("Stop drawing general table");

///////////*****************SPEEDS************************************////////////////////////////////////
            st2.setFetchSize(fetchSize);
            ResultSet rowsJointTableSpeed = st2.executeQuery(selectAllSpeed);
            int numOfColJointTableSpeed;
            metaDataJointTableSpeed = rowsJointTableSpeed.getMetaData();
            numOfColJointTableSpeed = metaDataJointTableSpeed.getColumnCount();

            System.out.println("Num of columns in joint table speed: " + numOfColJointTableSpeed);

            boolean isFirst = true;
            Object[] tempRowJointTableSpeed = null;
            while (rowsJointTableSpeed.next()) {
                ts15 = rowsJointTableSpeed.getTimestamp(1);
                if (isFirst) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    stringStart  = dateFormat.format(ts15);
                    ts17 = ts15;
//                    labelStart.setText(stringStart);
//                    textFieldStart.setText(stringStart);
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
//                    System.out.println(tempRowJointTableSpeed);
                }

            }
//            if (flagDelete) {
//                resultTableModel.removeRow(2);
//                flagDelete = false;
//            }
            rowsJointTableDigits.close();
            st1.close();
            st2.close();
            connButtonJointTable.close();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }
        System.gc();

    }

    public JointTableClass(String spinnerStartStr, String spinnerEndStr, FormTwo parentFrame, Connection conn) {
        int fetchSize = 100;
        DefaultTableModel resultTableModelOne;
//        Object[] tempRowJointTableDigits = null;
//        if (tempRowJointTableDigits != null) System.out.println("OBJECT[] IS NOT NULL!!!!!!!!!!!!-----");

        try {
////////////////////////////// Creating data for Joint Table////////////////////////////////////

            String selectAll = "SELECT * FROM APP.ALLDATA " +
                    " where APP.ALLDATA.TIMESTAMPCOL between '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerStartStr + "' and '" + parentFrame.comboDate.getSelectedItem() + " " + spinnerEndStr + "'";

            Statement st1 = conn.createStatement();

            st1.setFetchSize(fetchSize);
            ResultSet rowsJointTableDigits;
            System.out.println("Start joint table query...");


            rowsJointTableDigits = st1.executeQuery(selectAll);

            System.out.println("End joint table query...");
            System.out.println("rowsJointTableDigits = " + rowsJointTableDigits.getFetchSize());
            resultTableModelOne = new DefaultTableModel(databaseInfoJointTable, columnsJointTable);

            int numOfColJointTableDigits;

            ResultSetMetaData metaDataJointTableDigits = null;
            ResultSetMetaData metaDataJointTableSpeed = null;

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            numOfColJointTableDigits = metaDataJointTableDigits.getColumnCount();
            System.out.println("Num of columns in joint table: " + numOfColJointTableDigits);

            metaDataJointTableDigits = rowsJointTableDigits.getMetaData();
            if (resultTableModelOne.getRowCount() > 0) {
                resultTableModelOne.setNumRows(0);
            }


            System.out.println("rows joint table = " + rowsJointTableDigits.getFetchSize());
            System.out.println("Start draw table");
            String presTableOneStr;
            String presTableTwoStr;
            while (rowsJointTableDigits.next()) {
                Object[] tempRowJointTableDigits = null;
                java.sql.Timestamp ts = rowsJointTableDigits.getTimestamp(1);

                if (ts.getNanos() % 1000000000 == 0 /*&& !Integer.toString(rowsJointTableDigits.getInt(3)).equals("null")*/ &&
                        rowsJointTableDigits.getObject(2) != (null)/* != null*/
                        /* (rowsJointTableDigits.getObject(2) != null)*/){
                    if (rowsJointTableDigits.getString(3) != null) {

//                        double presTableOne = (Math.round((double)((rowsJointTableDigits.getInt(40) * /*(double)coefPresOne **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
//                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * /*(double)coefPresTwo **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
                        double presTableOne = (Math.round((double)((rowsJointTableDigits.getInt(3) * 0.0032258 - parentFrame.coeffPresOneUmin * 0.641026) * (parentFrame.coeffPresOnePmax / ((parentFrame.coeffPresOneUmax - parentFrame.coeffPresOneUmin)*0.641026)) * 10.0))) / 10.0;
//                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(41) * /*(double)coefPresTwo **/ 0.01258 - 1.24995) * 10.0))) / 10.0;
                        double presTableTwo = (Math.round((double)((rowsJointTableDigits.getInt(4) * 0.0032258 - parentFrame.coeffPresTwoUmin * 0.641026) * (parentFrame.coeffPresTwoPmax / ((parentFrame.coeffPresTwoUmax - parentFrame.coeffPresTwoUmin)*0.641026)) * 10.0))) / 10.0;

//                        presTableOne = (presTableOne != -0.3 && presTableOne < 0.0) ? 0.0 : presTableOne;
//                        presTableTwo = (presTableTwo != -0.3 && presTableTwo < 0.0) ? 0.0 : presTableTwo;
                        presTableOne = (rowsJointTableDigits.getInt(3) != -55 && presTableOne < 0.0) ? 0.0 : presTableOne;
                        presTableTwo = (rowsJointTableDigits.getInt(4) != -55 && presTableTwo < 0.0) ? 0.0 : presTableTwo;

                        presTableOneStr = Double.toString(presTableOne);
                        presTableTwoStr = Double.toString(presTableTwo);

//                        if (presTableOne == -0.3) presTableOneStr = "-";
//                        if (presTableTwo == -0.3) presTableTwoStr = "-";
                        if (rowsJointTableDigits.getInt(3) == -55) presTableOneStr = "-";
                        if (rowsJointTableDigits.getInt(4) == -55) presTableTwoStr = "-";

                    }
                    else {
                        presTableOneStr = "-";
                        presTableTwoStr = "-";
                    }
                    tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                            Double.toString(Math.round(rowsJointTableDigits.getDouble(2))),

                            presTableOneStr,
                            presTableTwoStr,
                            rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
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
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38), rowsJointTableDigits.getInt(39),
                            rowsJointTableDigits.getInt(40), rowsJointTableDigits.getInt(41)
                    };

                }
                else if (ts.getNanos() % 500000000 == 0 &&  rowsJointTableDigits.getObject(2) != (null)){
                    tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                            //rowsJointTableDigits.getString(43),
                            Double.toString(Math.round(rowsJointTableDigits.getDouble(2))),
                            "-",
                            "-",
                            rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
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
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38), rowsJointTableDigits.getInt(39),
                            rowsJointTableDigits.getInt(40), rowsJointTableDigits.getInt(41)
                    };

                }
                else if (ts.getNanos() % 250000000 == 0 && rowsJointTableDigits.getObject(2) != (null)) {

//                    if ((ts15.getNanos() % 500000000 != 0) && (ts15.getNanos() % 1000000000 != 0)){
                        tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                                //new DecimalFormat("#0.00").format(rowsJointTableSpeed.getDouble(2) / 5.5),
                                //rowsJointTableSpeed.getString(2),
                                Double.toString(Math.round(rowsJointTableDigits.getDouble(2))),
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
//                    }


                }                  //   250000000
                else if((rowsJointTableDigits.getObject(2) != null)
                        /*ts.getNanos() % 950000000 != 0 &&
                        ts.getNanos() % 955000000 != 0 &&
                        ts.getNanos() % 960000000 != 0 &&
                        ts.getNanos() % 965000000 != 0 &&
                        ts.getNanos() % 970000000 != 0 &&
                        ts.getNanos() % 975000000 != 0 &&
                        ts.getNanos() % 980000000 != 0 &&
                        ts.getNanos() % 985000000 != 0 &&
                        ts.getNanos() % 990000000 != 0 &&
                        ts.getNanos() % 995000000 != 0*/
                ){
//                    System.out.println("TIME IS: " + ts + " " + rowsJointTableDigits.getInt(40));
                    tempRowJointTableDigits = new Object[]{rowsJointTableDigits.getTimestamp(1),
                            "-",
                            "-",
                            "-",
                            rowsJointTableDigits.getInt(5), rowsJointTableDigits.getInt(6),
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
                            rowsJointTableDigits.getInt(37), rowsJointTableDigits.getInt(38), rowsJointTableDigits.getInt(39),
                            rowsJointTableDigits.getInt(40), rowsJointTableDigits.getInt(41)
                    };

                } else continue;
                // rowsJointTableDigits.close();
//                rowsJointTableDigits = null;



                // Adds the row of data to the end of the model
                resultTableModelOne.addRow(tempRowJointTableDigits);



//                System.gc();

                //System.out.println(tempRowJointTableDigits);
            }
//            rowsJointTableDigits.close();
            resultTableModel = resultTableModelOne;
            System.gc();
            System.out.println("Stop drawing general table");

///////////*****************SPEEDS************************************////////////////////////////////////

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }
        System.gc();

    }

    public DefaultTableModel getJointTable() {
        return resultTableModel;
    }
}
