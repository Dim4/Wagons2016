package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by DM on 24.10.2016.
 */
public class ReadBinFile {
    public static int percent;
    private static boolean flag = false;
    public static java.sql.Timestamp timeStamp;

    private static DecimalFormat df = new DecimalFormat("0.00");


    public byte[] getBytesFromFileServiceData(File file, FormTwo parentFrame) throws IOException{
        long length = file.length();

        if (length > Integer.MAX_VALUE) throw new IOException("File is too large!");
        if (length < 1) throw new IOException("Couldn't open file...");
//        int blockSize = 17;
        int blockSize = 33;

        byte[] bytes = new byte[blockSize];
        int offset = 0;
        int numRead = 0;

        InputStream is = new BufferedInputStream(new FileInputStream(file));
        is.mark(0);
        boolean startBytes = true;

        try{
            is.reset();
            is.skip(8192);

            is.mark(0);
            is.read(bytes, offset, blockSize);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            int picDateTime[] = new int[7];
            java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            byte[] coefPr1Bytes = new byte[8];
            byte[] coefPr2Bytes = new byte[8];

            byte[] carNumberBytes = new byte[5];

            byte[] coeffOnePmaxBytes = new byte[8];
            byte[] coeffOneUminBytes = new byte[8];
            byte[] coeffOneUmaxBytes = new byte[8];

            byte[] coeffTwoPmaxBytes = new byte[8];
            byte[] coeffTwoUminBytes = new byte[8];
            byte[] coeffTwoUmaxBytes = new byte[8];

            byte[] coefSpeedBytes = new byte[8];

            carNumberBytes[0] = bytes[0];
            carNumberBytes[1] = bytes[1];
            carNumberBytes[2] = bytes[2];
            carNumberBytes[3] = bytes[3];
            carNumberBytes[4] = bytes[4];

            if (carNumberBytes[0] == 0) carNumberBytes[0] = 48;
            if (carNumberBytes[1] == 0) carNumberBytes[1] = 48;
            if (carNumberBytes[2] == 0) carNumberBytes[2] = 48;
            if (carNumberBytes[3] == 0) carNumberBytes[3] = 48;

            coeffOnePmaxBytes[0] = bytes[5];
            coeffOnePmaxBytes[1] = bytes[6];
            coeffOnePmaxBytes[2] = bytes[7];
            coeffOnePmaxBytes[3] = bytes[8];

            coeffOneUminBytes[0] = bytes[9];
            coeffOneUminBytes[1] = bytes[10];
            coeffOneUminBytes[2] = bytes[11];
            coeffOneUminBytes[3] = bytes[12];

            coeffOneUmaxBytes[0] = bytes[13];
            coeffOneUmaxBytes[1] = bytes[14];
            coeffOneUmaxBytes[2] = bytes[15];
            coeffOneUmaxBytes[3] = bytes[16];

            coeffTwoPmaxBytes[0] = bytes[17];
            coeffTwoPmaxBytes[1] = bytes[18];
            coeffTwoPmaxBytes[2] = bytes[19];
            coeffTwoPmaxBytes[3] = bytes[20];

            coeffTwoUminBytes[0] = bytes[21];
            coeffTwoUminBytes[1] = bytes[22];
            coeffTwoUminBytes[2] = bytes[23];
            coeffTwoUminBytes[3] = bytes[24];

            coeffTwoUmaxBytes[0] = bytes[25];
            coeffTwoUmaxBytes[1] = bytes[26];
            coeffTwoUmaxBytes[2] = bytes[27];
            coeffTwoUmaxBytes[3] = bytes[28];

            coefSpeedBytes[0] = bytes[29];
            coefSpeedBytes[1] = bytes[30];
            coefSpeedBytes[2] = bytes[31];
            coefSpeedBytes[3] = bytes[32];

            double coeffOne = toDouble(coefPr1Bytes);
            double coeffTwo = toDouble(coefPr2Bytes);
            double coeffSpeed = toDouble(coefSpeedBytes);
            parentFrame.carNo = new String(carNumberBytes);

            double coeffOnePmax = toDouble(coeffOnePmaxBytes);
            double coeffOneUmin = toDouble(coeffOneUminBytes);
            double coeffOneUmax = toDouble(coeffOneUmaxBytes);

            double coeffTwoPmax = toDouble(coeffTwoPmaxBytes);
            double coeffTwoUmin = toDouble(coeffTwoUminBytes);
            double coeffTwoUmax = toDouble(coeffTwoUmaxBytes);

            System.out.println("CoeffOne = " + coeffOne);
            System.out.println("CoeffTwo = " + coeffTwo);
            System.out.println("CoeffSpeed = " + coeffSpeed);

            coeffSpeed *= 100.0;
            coeffSpeed = Math.round(coeffSpeed);
            coeffSpeed /= 100.0;

            coeffOne *= 100.0;
            coeffOne = Math.round(coeffOne);
            coeffOne /= 100.0;

            double coeffP = 55.12635;
            coeffP = Math.round(coeffP * 100.0) / 100.0;
            System.out.println("coeffP = " + coeffP);

            coeffP *= 100.0;
            System.out.println("coeffP2_100 = " + coeffP);

            coeffP = Math.round(coeffP);
            System.out.println("coeffP2_100_round = " + coeffP);

            coeffP /= 100.0;
            System.out.println("coeffP2 = " + coeffP);

            coeffTwo *= 100.0;
            coeffTwo = Math.round(coeffTwo);
            coeffTwo /= 100.0;

            parentFrame.coefPresOneRead.setText(Double.toString(coeffOne));
            parentFrame.coefPresTwoRead.setText(Double.toString(coeffTwo));
//            parentFrame.coeffSpeedRead.setText(Double.toString(coeffSpeed));
            parentFrame.coeffSpeedRead.setText(df.format(coeffSpeed));

            parentFrame.carNoTwoText.setText(parentFrame.carNo);
//            parentFrame.carNoTwoText.setText(Integer.toString(carNo));
//            parentFrame.carNo = carNo;

//            /**********************************************/
            double coeffOneOld = coeffOne;
            double coeffTwoOld = coeffTwo;
            double coeffSpeedOld = coeffSpeed;

            parentFrame.coeffPresOne *= coeffOne;
            parentFrame.coeffPresTwo *= coeffTwo;
            parentFrame.coeffSpeed *= coeffSpeed;

            parentFrame.coeffPresOnePmax = coeffOnePmax;
            parentFrame.coeffPresOneUmin = coeffOneUmin;
            parentFrame.coeffPresOneUmax = coeffOneUmax;

            parentFrame.coeffPresTwoPmax = coeffTwoPmax;
            parentFrame.coeffPresTwoUmin = coeffTwoUmin;
            parentFrame.coeffPresTwoUmax = coeffTwoUmax;

//            System.out.println("coeffPresOnePmax = " + coeffOnePmax);
//            System.out.println("coeffPresOneUmin = " + coeffOneUmin);
//            System.out.println("coeffPresOneUmax = " + coeffOneUmax);
//
//            System.out.println("coeffPresTwoPmax = " + coeffTwoPmax);
//            System.out.println("coeffPresTwoUmin = " + coeffTwoUmin);
//            System.out.println("coeffPresTwoUmax = " + coeffTwoUmax);

            coeffOne = parentFrame.coeffPresOne;
            coeffTwo = parentFrame.coeffPresTwo;
            coeffSpeed = parentFrame.coeffSpeed;
//            /*********************************************/

            String strUrl = "jdbc:derby:wagons";
            try {
                Connection conn5;
                conn5 = DriverManager.getConnection(strUrl);

                PreparedStatement pstm = conn5.prepareStatement("INSERT INTO APP.SETTINGSINFO (CURRENTTIMESTAMP, CARNUMBER, COEFPRESONE, COEFPRESTWO, COEFSPEED) " +
                        "VALUES (?, ?, ?, ?, ?)");
                pstm.setTimestamp(1, timestamp);
                pstm.setString(2, parentFrame.carNo);
                pstm.setDouble(3, coeffOneOld);
                pstm.setDouble(4, coeffTwoOld);
                pstm.setDouble(5, coeffSpeedOld);

                pstm.executeUpdate();

                System.out.println("SQL");

                conn5.close();
            } catch (Exception ex) {ex.printStackTrace();}
        } finally {
            is.close();
        }
        return bytes;
    }

    public static byte[] getBytesFromFileServiceDataTwo(File file, FormTwo parentFrame) throws IOException{
        long length = file.length();

        if (length > Integer.MAX_VALUE) throw new IOException("File is too large!");
        if (length < 1) throw new IOException("Couldn't open file...");
//        int blockSize = 17;
        int blockSize = 33;

        byte[] bytes = new byte[blockSize];
        int offset = 0;
        int numRead = 0;

        InputStream is = new BufferedInputStream(new FileInputStream(file));
        is.mark(0);
        boolean startBytes = true;

        try{
            is.reset();
            is.skip(8192);

            is.mark(0);
            is.read(bytes, offset, blockSize);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            int picDateTime[] = new int[7];
            java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            byte[] coefPr1Bytes = new byte[8];
            byte[] coefPr2Bytes = new byte[8];

            byte[] carNumberBytes = new byte[5];

            byte[] coeffOnePmaxBytes = new byte[8];
            byte[] coeffOneUminBytes = new byte[8];
            byte[] coeffOneUmaxBytes = new byte[8];

            byte[] coeffTwoPmaxBytes = new byte[8];
            byte[] coeffTwoUminBytes = new byte[8];
            byte[] coeffTwoUmaxBytes = new byte[8];

            byte[] coefSpeedBytes = new byte[8];

            carNumberBytes[0] = bytes[0];
            carNumberBytes[1] = bytes[1];
            carNumberBytes[2] = bytes[2];
            carNumberBytes[3] = bytes[3];
            carNumberBytes[4] = bytes[4];

            if (carNumberBytes[0] == 0) carNumberBytes[0] = 48;
            if (carNumberBytes[1] == 0) carNumberBytes[1] = 48;
            if (carNumberBytes[2] == 0) carNumberBytes[2] = 48;
            if (carNumberBytes[3] == 0) carNumberBytes[3] = 48;

            coeffOnePmaxBytes[0] = bytes[5];
            coeffOnePmaxBytes[1] = bytes[6];
            coeffOnePmaxBytes[2] = bytes[7];
            coeffOnePmaxBytes[3] = bytes[8];

            coeffOneUminBytes[0] = bytes[9];
            coeffOneUminBytes[1] = bytes[10];
            coeffOneUminBytes[2] = bytes[11];
            coeffOneUminBytes[3] = bytes[12];

            coeffOneUmaxBytes[0] = bytes[13];
            coeffOneUmaxBytes[1] = bytes[14];
            coeffOneUmaxBytes[2] = bytes[15];
            coeffOneUmaxBytes[3] = bytes[16];

            coeffTwoPmaxBytes[0] = bytes[17];
            coeffTwoPmaxBytes[1] = bytes[18];
            coeffTwoPmaxBytes[2] = bytes[19];
            coeffTwoPmaxBytes[3] = bytes[20];

            coeffTwoUminBytes[0] = bytes[21];
            coeffTwoUminBytes[1] = bytes[22];
            coeffTwoUminBytes[2] = bytes[23];
            coeffTwoUminBytes[3] = bytes[24];

            coeffTwoUmaxBytes[0] = bytes[25];
            coeffTwoUmaxBytes[1] = bytes[26];
            coeffTwoUmaxBytes[2] = bytes[27];
            coeffTwoUmaxBytes[3] = bytes[28];

            coefSpeedBytes[0] = bytes[29];
            coefSpeedBytes[1] = bytes[30];
            coefSpeedBytes[2] = bytes[31];
            coefSpeedBytes[3] = bytes[32];

            double coeffOne = toDouble(coefPr1Bytes);
            double coeffTwo = toDouble(coefPr2Bytes);
            double coeffSpeed = toDouble(coefSpeedBytes);
            parentFrame.carNo = new String(carNumberBytes);

            double coeffOnePmax = toDouble(coeffOnePmaxBytes);
            double coeffOneUmin = toDouble(coeffOneUminBytes);
            double coeffOneUmax = toDouble(coeffOneUmaxBytes);

            double coeffTwoPmax = toDouble(coeffTwoPmaxBytes);
            double coeffTwoUmin = toDouble(coeffTwoUminBytes);
            double coeffTwoUmax = toDouble(coeffTwoUmaxBytes);

//            System.out.println("CoeffOne = " + coeffOne);
//            System.out.println("CoeffTwo = " + coeffTwo);
//            System.out.println("CoeffSpeed = " + coeffSpeed);

            coeffSpeed *= 100.0;
            coeffSpeed = Math.round(coeffSpeed);
            coeffSpeed /= 100.0;

            coeffOne *= 100.0;
            coeffOne = Math.round(coeffOne);
            coeffOne /= 100.0;

            double coeffP = 55.12635;
            coeffP = Math.round(coeffP * 100.0) / 100.0;
//            System.out.println("coeffP = " + coeffP);

            coeffP *= 100.0;
//            System.out.println("coeffP2_100 = " + coeffP);

            coeffP = Math.round(coeffP);
//            System.out.println("coeffP2_100_round = " + coeffP);

            coeffP /= 100.0;
//            System.out.println("coeffP2 = " + coeffP);

            coeffTwo *= 100.0;
            coeffTwo = Math.round(coeffTwo);
            coeffTwo /= 100.0;

            parentFrame.coefPresOneRead.setText(Double.toString(coeffOne));
            parentFrame.coefPresTwoRead.setText(Double.toString(coeffTwo));
//            parentFrame.coeffSpeedRead.setText(Double.toString(coeffSpeed));
            parentFrame.coeffSpeedRead.setText(df.format(coeffSpeed));

            parentFrame.carNoTwoText.setText(parentFrame.carNo);
//            parentFrame.carNoTwoText.setText(Integer.toString(carNo));
//            parentFrame.carNo = carNo;

//            /**********************************************/
            double coeffOneOld = coeffOne;
            double coeffTwoOld = coeffTwo;
            double coeffSpeedOld = coeffSpeed;

            parentFrame.coeffPresOne *= coeffOne;
            parentFrame.coeffPresTwo *= coeffTwo;
            parentFrame.coeffSpeed *= coeffSpeed;

            parentFrame.coeffPresOnePmax = coeffOnePmax;
            parentFrame.coeffPresOneUmin = coeffOneUmin;
            parentFrame.coeffPresOneUmax = coeffOneUmax;

            parentFrame.coeffPresTwoPmax = coeffTwoPmax;
            parentFrame.coeffPresTwoUmin = coeffTwoUmin;
            parentFrame.coeffPresTwoUmax = coeffTwoUmax;

//            System.out.println("coeffPresOnePmax = " + coeffOnePmax);
//            System.out.println("coeffPresOneUmin = " + coeffOneUmin);
//            System.out.println("coeffPresOneUmax = " + coeffOneUmax);
//
//            System.out.println("coeffPresTwoPmax = " + coeffTwoPmax);
//            System.out.println("coeffPresTwoUmin = " + coeffTwoUmin);
//            System.out.println("coeffPresTwoUmax = " + coeffTwoUmax);

            coeffOne = parentFrame.coeffPresOne;
            coeffTwo = parentFrame.coeffPresTwo;
            coeffSpeed = parentFrame.coeffSpeed;

    /*********************************************/
            parentFrame.coeffSpeed = coeffSpeedOld;
    /*********************************************/



//            /*********************************************/

//            String strUrl = "jdbc:derby:wagons";
//            try {
//                conn = DriverManager.getConnection(strUrl);
//
//                PreparedStatement pstm = conn.prepareStatement("INSERT INTO APP.SETTINGSINFO (CURRENTTIMESTAMP, CARNUMBER, COEFPRESONE, COEFPRESTWO, COEFSPEED) " +
//                        "VALUES (?, ?, ?, ?, ?)");
//                pstm.setTimestamp(1, timestamp);
//                pstm.setString(2, parentFrame.carNo);
//                pstm.setDouble(3, coeffOneOld);
//                pstm.setDouble(4, coeffTwoOld);
//                pstm.setDouble(5, coeffSpeedOld);
//
//                pstm.executeUpdate();
//
//                System.out.println("SQL");

//                conn.close();
//            } catch (Exception ex) {ex.printStackTrace();}
        } finally {
            is.close();
        }
        return bytes;
    }


    public byte[] getBytesFromFile(File file, Frame frame, FormTwo parentFrm) throws IOException {

        percent = 0;
        parentFrm.progressBar2.setValue(percent);
        parentFrm.dpb.setValue(percent);

        long length = file.length();

        if (length > Integer.MAX_VALUE) throw new IOException("File is too large!");
        int blockSize = 340;
        byte[] bytes = new byte[blockSize];
        int offset = 0;
        int numRead = 0;

        InputStream is = new BufferedInputStream(new FileInputStream(file));
        is.mark(0);

        boolean startBytes = true;
        boolean isFirstRow = true;
        try {

            java.sql.Date previousDateTime = null;
            java.sql.Timestamp previousTimeStamp = null;

            while (offset < length && (numRead = is.read(bytes, offset, blockSize)) >= 0) {
 //               System.out.println("PREVIOUS DATE: " + previousTimeStamp);
                percent += 340;
                parentFrm.progressBar2.setValue(percent);
                parentFrm.dpb.setValue(percent);
//                System.out.println("percent = " + percent);
                //offset += blockSize;
//                java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
                //is.mark(0);
                if (startBytes) {
                    is.reset();
                    for (int i = 0; i < 36; i++) {
                        is.skip(4096);
                    }
//                    is.skip(8192);
//                    is.skip(8192);

//                    is.skip(24576);
//                    is.skip(8192);
                    is.mark(0);
                    is.read(bytes, offset, blockSize);
                    startBytes = false;
                }
////////////////////////////////////////////////////////////////////////////////////////////
               // byte[] bytes1 = new byte[340 * 2];

                if (searchEndData(bytes)) break;

                if (unusedData(bytes)) {
                    is.reset();
                    is.skip(16);
                    is.mark(0);
                    is.read(bytes, offset, blockSize);
//                    System.out.println("We've got unused data!");
                }
///////////////////////////////////////////////////////////////////////////////////////////
//                is.reset();
//                bytes = searchStart(bytes, is);
//
//                if (flag) {
//                    //delete last rows
//                    String strUrl = "jdbc:derby:wagons";
//                    try {
//                        Connection connDel1;
//                        connDel1 = DriverManager.getConnection(strUrl);
//
//                        PreparedStatement pstmDel1 = connDel1.prepareStatement("DELETE FROM APP.PRESSURE WHERE TIMESTAMP =  " +
//                                "(SELECT MAX(TIMESTAMP) FROM APP.PRESSURE LIMIT 1)");
//
//                        pstmDel1.executeUpdate();
//                        connDel1.close();
//                    } catch (Exception ex) {ex.printStackTrace();}
//
//
//                    flag = false;
//                }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
                //is.skip(blockSize);
                //if (bytes[0] == -1) break;

//                if (bytes[0] == -1 && bytes[1] == -1 && bytes[2] == -1 && bytes[3] == -1 && bytes[4] == -1 && bytes[5] == -1 &&
//                        bytes[6] == -1 && bytes[7] == -1 && bytes[8] == -1 && bytes[9] == -1 && bytes[10] == -1 && bytes[11] == -1 &&
//                        bytes[12] == -1 && bytes[13] == -1 && bytes[14] == -1 && bytes[15] == -1 && bytes[16] == -1 && bytes[17] == -1 && bytes[18] == -1) break;// &&
////                        bytes[18] == -1 && bytes[19] == -1 && bytes[20] == -1 && bytes[21] == -1 && bytes[22] == -1 && bytes[23] == -1) break;

//                System.out.println("Bytes0 = " + bytes[0]);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

                int picDateTime[] = new int[7];
                int picDateTime2[] = new int [7];
                int picDateTime3[] = new int [7];
                int picDateTime4[] = new int [7];
                int picDateTime5[] = new int [7];
                int picDateTime6[] = new int [7];
                int picDateTime7[] = new int [7];
                int picDateTime8[] = new int [7];
                int picDateTime9[] = new int [7];
                int picDateTime10[] = new int [7];
                int picDateTime11[] = new int [7];
                int picDateTime12[] = new int [7];

                try {
                    picDateTime[0] = bcd2dec(bytes[0]);
                } catch (Exception e) {
                    is.reset();
                }
                picDateTime[1] = bcd2dec(bytes[1]);
                picDateTime[2] = bcd2dec(bytes[2]);
                picDateTime[3] = bcd2dec(bytes[3]);
                picDateTime[4] = bcd2dec(bytes[4]);
                picDateTime[5] = bcd2dec(bytes[5]);
                picDateTime[6] = 0;                     // Milliseconds

                for (int i = 0; i < 6; i++) {
                    picDateTime2[i] = picDateTime[i];
                    picDateTime3[i] = picDateTime[i];
                    picDateTime4[i] = picDateTime[i];
                    picDateTime5[i] = picDateTime[i];
                    picDateTime6[i] = picDateTime[i];
                    picDateTime7[i] = picDateTime[i];
                    picDateTime8[i] = picDateTime[i];
                    picDateTime9[i] = picDateTime[i];
                    picDateTime10[i] = picDateTime[i];
                    picDateTime11[i] = picDateTime[i];
                    picDateTime12[i] = picDateTime[i];
                }
                picDateTime2[6] = 100;
                picDateTime3[6] = 200;
                picDateTime4[6] = 250;
                picDateTime5[6] = 300;
                picDateTime6[6] = 400;
                picDateTime7[6] = 500;
                picDateTime8[6] = 600;
                picDateTime9[6] = 700;
                picDateTime10[6] = 750;
                picDateTime11[6] = 800;
                picDateTime12[6] = 900;

                java.sql.Date dateTime = getDateTime(picDateTime);
//                System.out.println(dateFormat.format(dateTime));
//                java.sql.Timestamp timeStamp = new java.sql.Timestamp(dateTime.getTime());
                timeStamp = new java.sql.Timestamp(dateTime.getTime());
                java.sql.Timestamp timeStamp1_1 = new java.sql.Timestamp(dateTime.getTime());
                java.sql.Timestamp timeStamp1_2 = new java.sql.Timestamp(dateTime.getTime());

                java.sql.Timestamp timeStamp0_0 = new java.sql.Timestamp(dateTime.getTime());

//                if (!isFirstRow)
//                    System.out.println("\n\n\n DIFFERENCE IS: " + (timeStamp0_0.getTime() - previousTimeStamp.getTime()) + "\n \n ");


                if (!isFirstRow && (timeStamp0_0.getTime() - previousTimeStamp.getTime() > 5000L)) {
                    java.sql.Timestamp newTimeStamp = new java.sql.Timestamp(timeStamp.getTime() - 5000L);

//                    System.out.println("\n\n\n\n NEW TIME STAMP: " + newTimeStamp + "\n\n\n\n\n");

                    String strUrl = "jdbc:derby:wagons";
                    try {
                        Connection conn0;
                        conn0 = DriverManager.getConnection(strUrl);

                        PreparedStatement pstm = conn0.prepareStatement("INSERT INTO APP.PRESSURE (TIMESTAMP, PRESSUREONE, PRESSURETWO) " +
                                "VALUES (?, ?, ?)");

                        pstm.setTimestamp(1, newTimeStamp);
                        pstm.setNull(2, Types.INTEGER);
                        pstm.setNull(3, Types.INTEGER);

                        pstm.executeUpdate();
                        conn0.close();
                    } catch (Exception ex) {ex.printStackTrace();}

                    try {
                        Connection conn05;
                        conn05 = DriverManager.getConnection(strUrl);

                        PreparedStatement pstm = conn05.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
                                "VALUES (?, ?)");

                        pstm.setTimestamp(1, newTimeStamp);
                        pstm.setNull(2, Types.DOUBLE);

                        pstm.executeUpdate();
                        conn05.close();
                    } catch (Exception ex) {ex.printStackTrace();}

                    try {
                        Connection conn06;
                        conn06 = DriverManager.getConnection(strUrl);

                        PreparedStatement pstmDigits1 = conn06.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");

                        pstmDigits1.setTimestamp(1, newTimeStamp);
                        pstmDigits1.setNull(2, Types.BOOLEAN);
                        pstmDigits1.setNull(3, Types.BOOLEAN);
                        pstmDigits1.setNull(4, Types.BOOLEAN);
                        pstmDigits1.setNull(5, Types.BOOLEAN);
                        pstmDigits1.setNull(6, Types.BOOLEAN);
                        pstmDigits1.setNull(7, Types.BOOLEAN);
                        pstmDigits1.setNull(8, Types.BOOLEAN);
                        pstmDigits1.setNull(9, Types.BOOLEAN);
                        pstmDigits1.setNull(10, Types.BOOLEAN);
                        pstmDigits1.setNull(11, Types.BOOLEAN);
                        pstmDigits1.setNull(12, Types.BOOLEAN);
                        pstmDigits1.setNull(13, Types.BOOLEAN);
                        pstmDigits1.setNull(14, Types.BOOLEAN);
                        pstmDigits1.setNull(15, Types.BOOLEAN);
                        pstmDigits1.setNull(16, Types.BOOLEAN);
                        pstmDigits1.setNull(17, Types.BOOLEAN);
                        pstmDigits1.setNull(18, Types.BOOLEAN);
                        pstmDigits1.setNull(19, Types.BOOLEAN);
                        pstmDigits1.setNull(20, Types.BOOLEAN);
                        pstmDigits1.setNull(21, Types.BOOLEAN);
                        pstmDigits1.setNull(22, Types.BOOLEAN);
                        pstmDigits1.setNull(23, Types.BOOLEAN);
                        pstmDigits1.setNull(24, Types.BOOLEAN);
                        pstmDigits1.setNull(25, Types.BOOLEAN);
                        pstmDigits1.setNull(26, Types.BOOLEAN);
                        pstmDigits1.setNull(27, Types.BOOLEAN);
                        pstmDigits1.setNull(28, Types.BOOLEAN);
                        pstmDigits1.setNull(29, Types.BOOLEAN);
                        pstmDigits1.setNull(30, Types.BOOLEAN);
                        pstmDigits1.setNull(31, Types.BOOLEAN);
                        pstmDigits1.setNull(32, Types.BOOLEAN);
                        pstmDigits1.setNull(33, Types.BOOLEAN);
                        pstmDigits1.setNull(34, Types.BOOLEAN);
                        pstmDigits1.setNull(35, Types.BOOLEAN);
                        pstmDigits1.setNull(36, Types.BOOLEAN);
                        pstmDigits1.setNull(37, Types.BOOLEAN);
                        pstmDigits1.setNull(38, Types.BOOLEAN);
                        pstmDigits1.executeUpdate();
                        conn06.close();
                    } catch (Exception ex) {ex.printStackTrace();}

               }


                java.sql.Date dateTime2 = getDateTime(picDateTime2);
//                System.out.println(dateFormat.format(dateTime2));
                java.sql.Timestamp timeStamp2 = new java.sql.Timestamp(dateTime2.getTime());

                java.sql.Date dateTime3 = getDateTime(picDateTime3);
//                System.out.println(dateFormat.format(dateTime3));
                java.sql.Timestamp timeStamp3 = new java.sql.Timestamp(dateTime3.getTime());

                java.sql.Date dateTime4 = getDateTime(picDateTime4);
//                System.out.println(dateFormat.format(dateTime4));
                java.sql.Timestamp timeStamp4 = new java.sql.Timestamp(dateTime4.getTime());

                java.sql.Date dateTime5 = getDateTime(picDateTime5);
//                System.out.println(dateFormat.format(dateTime5));
                java.sql.Timestamp timeStamp5 = new java.sql.Timestamp(dateTime5.getTime());

                java.sql.Date dateTime6 = getDateTime(picDateTime6);
//                System.out.println(dateFormat.format(dateTime6));
                java.sql.Timestamp timeStamp6 = new java.sql.Timestamp(dateTime6.getTime());

                java.sql.Date dateTime7 = getDateTime(picDateTime7);
//                System.out.println(dateFormat.format(dateTime7));
                java.sql.Timestamp timeStamp7 = new java.sql.Timestamp(dateTime7.getTime());
                java.sql.Timestamp timeStamp7_2 = new java.sql.Timestamp(dateTime7.getTime());

                java.sql.Date dateTime8 = getDateTime(picDateTime8);
//                System.out.println(dateFormat.format(dateTime8));
                java.sql.Timestamp timeStamp8 = new java.sql.Timestamp(dateTime8.getTime());

                java.sql.Date dateTime9 = getDateTime(picDateTime9);
//                System.out.println(dateFormat.format(dateTime9));
                java.sql.Timestamp timeStamp9 = new java.sql.Timestamp(dateTime9.getTime());

                java.sql.Date dateTime10 = getDateTime(picDateTime10);
//                System.out.println(dateFormat.format(dateTime10));
                java.sql.Timestamp timeStamp10 = new java.sql.Timestamp(dateTime10.getTime());

                java.sql.Date dateTime11 = getDateTime(picDateTime11);
//                System.out.println(dateFormat.format(dateTime11));
                java.sql.Timestamp timeStamp11 = new java.sql.Timestamp(dateTime11.getTime());

                java.sql.Date dateTime12 = getDateTime(picDateTime12);
//                System.out.println(dateFormat.format(dateTime12));
                java.sql.Timestamp timeStamp12 = new java.sql.Timestamp(dateTime12.getTime());

                if (isFirstRow)
                    isFirstRow = false;

                previousTimeStamp = timeStamp0_0;
//                System.out.println("Previous data: " + previousTimeStamp);

                int pres1;
                int pres2;
                int speed = 0;

                byte[] pressure1 = new byte[2];
                byte[] pressure2 = new byte[2];

                byte[] digitalSpeed1_1 = new byte[4];
                byte[] digitalSpeed2_1 = new byte[4];
                byte[] digitalSpeed3_1 = new byte[4];
                byte[] digitalSpeed4_1 = new byte[4];

                pressure1[0] = bytes[336];
                pressure1[1] = bytes[337];

                pressure2[0] = bytes[338];
                pressure2[1] = bytes[339];

//                digitalSpeed1_1[0] = bytes[16];
//                digitalSpeed1_1[1] = bytes[17];
//                digitalSpeed1_1[2] = bytes[18];
//                digitalSpeed1_1[3] = bytes[19];
//
//                digitalSpeed2_1[0] = bytes[35];
//                digitalSpeed2_1[1] = bytes[36];
//                digitalSpeed2_1[2] = bytes[37];
//                digitalSpeed2_1[3] = bytes[38];
//
//                digitalSpeed3_1[0] = bytes[49];
//                digitalSpeed3_1[1] = bytes[50];
//                digitalSpeed3_1[2] = bytes[51];
//                digitalSpeed3_1[3] = bytes[52];
//
//                digitalSpeed4_1[0] = bytes[68];
//                digitalSpeed4_1[1] = bytes[69];
//                digitalSpeed4_1[2] = bytes[70];
//                digitalSpeed4_1[3] = bytes[71];

                pres1 = mergeTwoBytes(pressure1);
                pres2 = mergeTwoBytes(pressure2);

                byte[] bytesForDigitalSet1 = new byte[5];
                byte[] bytesForDigitalSet2 = new byte[5];
                byte[] bytesForDigitalSet3 = new byte[5];
                byte[] bytesForDigitalSet4 = new byte[5];
                byte[] bytesForDigitalSet5 = new byte[5];
                byte[] bytesForDigitalSet6 = new byte[5];
                byte[] bytesForDigitalSet7 = new byte[5];
                byte[] bytesForDigitalSet8 = new byte[5];
                byte[] bytesForDigitalSet9 = new byte[5];
                byte[] bytesForDigitalSet10 = new byte[5];

//                bytesForDigitalSet1[0] = bytes[6];
//                bytesForDigitalSet1[1] = bytes[7];
//                bytesForDigitalSet1[2] = bytes[8];
//                bytesForDigitalSet1[3] = bytes[9];
//                bytesForDigitalSet1[4] = bytes[10];
//
//                bytesForDigitalSet2[0] = bytes[11];
//                bytesForDigitalSet2[1] = bytes[12];
//                bytesForDigitalSet2[2] = bytes[13];
//                bytesForDigitalSet2[3] = bytes[14];
//                bytesForDigitalSet2[4] = bytes[15];
//
//                bytesForDigitalSet3[0] = bytes[20];
//                bytesForDigitalSet3[1] = bytes[21];
//                bytesForDigitalSet3[2] = bytes[22];
//                bytesForDigitalSet3[3] = bytes[23];
//                bytesForDigitalSet3[4] = bytes[24];
//
//                bytesForDigitalSet4[0] = bytes[25];
//                bytesForDigitalSet4[1] = bytes[26];
//                bytesForDigitalSet4[2] = bytes[27];
//                bytesForDigitalSet4[3] = bytes[28];
//                bytesForDigitalSet4[4] = bytes[29];
//
//                bytesForDigitalSet5[0] = bytes[30];
//                bytesForDigitalSet5[1] = bytes[31];
//                bytesForDigitalSet5[2] = bytes[32];
//                bytesForDigitalSet5[3] = bytes[33];
//                bytesForDigitalSet5[4] = bytes[34];
//
//                bytesForDigitalSet6[0] = bytes[39];
//                bytesForDigitalSet6[1] = bytes[40];
//                bytesForDigitalSet6[2] = bytes[41];
//                bytesForDigitalSet6[3] = bytes[42];
//                bytesForDigitalSet6[4] = bytes[43];
//
//                bytesForDigitalSet7[0] = bytes[44];
//                bytesForDigitalSet7[1] = bytes[45];
//                bytesForDigitalSet7[2] = bytes[46];
//                bytesForDigitalSet7[3] = bytes[47];
//                bytesForDigitalSet7[4] = bytes[48];
//
//                bytesForDigitalSet8[0] = bytes[53];
//                bytesForDigitalSet8[1] = bytes[54];
//                bytesForDigitalSet8[2] = bytes[55];
//                bytesForDigitalSet8[3] = bytes[56];
//                bytesForDigitalSet8[4] = bytes[57];
//
//                bytesForDigitalSet9[0] = bytes[58];
//                bytesForDigitalSet9[1] = bytes[59];
//                bytesForDigitalSet9[2] = bytes[60];
//                bytesForDigitalSet9[3] = bytes[61];
//                bytesForDigitalSet9[4] = bytes[62];
//
//                bytesForDigitalSet10[0] = bytes[63];
//                bytesForDigitalSet10[1] = bytes[64];
//                bytesForDigitalSet10[2] = bytes[65];
//                bytesForDigitalSet10[3] = bytes[66];
//                bytesForDigitalSet10[4] = bytes[67];
//
//                boolean[] digitalSet1 = appendAllDigits(bytesForDigitalSet1);
//                boolean[] digitalSet2 = appendAllDigits(bytesForDigitalSet2);
//                boolean[] digitalSet3 = appendAllDigits(bytesForDigitalSet3);
//                boolean[] digitalSet4 = appendAllDigits(bytesForDigitalSet4);
//                boolean[] digitalSet5 = appendAllDigits(bytesForDigitalSet5);
//                boolean[] digitalSet6 = appendAllDigits(bytesForDigitalSet6);
//                boolean[] digitalSet7 = appendAllDigits(bytesForDigitalSet7);
//                boolean[] digitalSet8 = appendAllDigits(bytesForDigitalSet8);
//                boolean[] digitalSet9 = appendAllDigits(bytesForDigitalSet9);
//                boolean[] digitalSet10 = appendAllDigits(bytesForDigitalSet10);

//                System.out.print(bcd2dec(bytes[0]) + " ");
//                System.out.print(bcd2dec(bytes[1]) + " ");
//                System.out.print(bcd2dec(bytes[2]) + " ");
//                System.out.print(bcd2dec(bytes[3]) + " ");
//                System.out.print(bcd2dec(bytes[4]) + " ");
//                System.out.println(bcd2dec(bytes[5]));





//                System.out.println("pressure1 = " + pres1);
//                System.out.println("pressure2 = " + pres2);
//                System.out.println("speed1 = " + (mergeFourBytes(digitalSpeed1_1)));
//                System.out.println("speed2 = " + (mergeFourBytes(digitalSpeed2_1)));
//                System.out.println("speed3 = " + (mergeFourBytes(digitalSpeed3_1)));
//                System.out.println("speed4 = " + (mergeFourBytes(digitalSpeed4_1)));





//                System.out.println("Bits for digitalSet1 = " + Arrays.toString(digitalSet1));
//                System.out.println("Bits for digitalSet2 = " + Arrays.toString(digitalSet2));
//                System.out.println("Bits for digitalSet3 = " + Arrays.toString(digitalSet3));
//                System.out.println("Bits for digitalSet4 = " + Arrays.toString(digitalSet4));
//                System.out.println("Bits for digitalSet5 = " + Arrays.toString(digitalSet5));
//                System.out.println("Bits for digitalSet6 = " + Arrays.toString(digitalSet6));
//                System.out.println("Bits for digitalSet7 = " + Arrays.toString(digitalSet7));
//                System.out.println("Bits for digitalSet8 = " + Arrays.toString(digitalSet8));
//                System.out.println("Bits for digitalSet9 = " + Arrays.toString(digitalSet9));
//                System.out.println("Bits for digitalSet10 = " + Arrays.toString(digitalSet10));

                //try to update database
                String strUrl = "jdbc:derby:wagons";
                try {
                    Connection conn2;
                    conn2 = DriverManager.getConnection(strUrl);

                    PreparedStatement pstm = conn2.prepareStatement("INSERT INTO APP.PRESSURE (TIMESTAMP, PRESSUREONE, PRESSURETWO) " +
                            "VALUES (?, ?, ?)");
                    pstm.setTimestamp(1, timeStamp);

//                    pres1 *= 100;
//                    pres1 = Math.round(pres1);
//                    pres1 /= 100;
//
//                    pres2 *= 100;
//                    pres2 = Math.round(pres2);
//                    pres2 /= 100;


                    pstm.setInt(2, pres1);
                    pstm.setInt(3, pres2);

                    pstm.executeUpdate();
//                    System.out.println("SQL");

//                    String insertNew = "INSERT INTO APP.PRESSURESTWO (TIMESTAMP, PRESSUREONE, PRESSURETWO) " +
//                            "VALUES (" + (java.sql.Date)dateTime + "," + pres1 + "," + pres2 + ")";
//                    st2.executeUpdate(insertNew);
//
                    conn2.close();
                } catch (Exception ex) {ex.printStackTrace();}
//                    catch(SQLException se) {
//                        if (se.getSQLState().equals("23505")) continue;
//                    }

                try{
                    Connection conn3;
                    conn3 = DriverManager.getConnection(strUrl);

                    //Inserting digital inputs and speed for each of 5 seconds
//                    for (int i = 0; i < 5; i++) {
//Inserting speed 4 times per second

//                        timeStamp = new Timestamp(timeStamp.getTime() + 1 * 1000L * i);
//                        timeStamp4 = new Timestamp(timeStamp4.getTime() + 1 * 1000L * i);
//                        timeStamp7 = new Timestamp(timeStamp7.getTime() + 1 * 1000L * i);
//                        timeStamp10 = new Timestamp(timeStamp10.getTime() + 1 * 1000L * i);

                        for (int j = 0; j < 5; j++) {

                            digitalSpeed1_1[0] = bytes[16 + j*66];
                            digitalSpeed1_1[1] = bytes[17 + j*66];
                            digitalSpeed1_1[2] = bytes[18 + j*66];
                            digitalSpeed1_1[3] = bytes[19 + j*66];

                            digitalSpeed2_1[0] = bytes[35 + j*66];
                            digitalSpeed2_1[1] = bytes[36 + j*66];
                            digitalSpeed2_1[2] = bytes[37 + j*66];
                            digitalSpeed2_1[3] = bytes[38 + j*66];

                            digitalSpeed3_1[0] = bytes[49 + j*66];
                            digitalSpeed3_1[1] = bytes[50 + j*66];
                            digitalSpeed3_1[2] = bytes[51 + j*66];
                            digitalSpeed3_1[3] = bytes[52 + j*66];

                            digitalSpeed4_1[0] = bytes[68 + j*66];
                            digitalSpeed4_1[1] = bytes[69 + j*66];
                            digitalSpeed4_1[2] = bytes[70 + j*66];
                            digitalSpeed4_1[3] = bytes[71 + j*66];

//                            int speed1 = (int)(16000000L / mergeFourBytes(digitalSpeed1_1));
//                            int speed2 = (int)(16000000L / mergeFourBytes(digitalSpeed2_1));
//                            int speed3 = (int)(16000000L / mergeFourBytes(digitalSpeed3_1));
//                            int speed4 = (int)(16000000L / mergeFourBytes(digitalSpeed4_1));

                            double speed1 = (16000000.0 / (double)mergeFourBytes(digitalSpeed1_1));
                            double speed2 = (16000000.0 / (double)mergeFourBytes(digitalSpeed2_1));
                            double speed3 = (16000000.0 / (double)mergeFourBytes(digitalSpeed3_1));
                            double speed4 = (16000000.0 / (double)mergeFourBytes(digitalSpeed4_1));

//                            double speed1 = (128000000.0 / (double)mergeFourBytes(digitalSpeed1_1));
//                            double speed2 = (128000000.0 / (double)mergeFourBytes(digitalSpeed2_1));
//                            double speed3 = (128000000.0 / (double)mergeFourBytes(digitalSpeed3_1));
//                            double speed4 = (128000000.0 / (double)mergeFourBytes(digitalSpeed4_1));


//                            speed1 = (speed1 < 0.01 ? 0.0 : speed1 / 5.5 );
//                            speed2 = (speed2 < 0.01 ? 0.0 : speed2 / 5.5 );
//                            speed3 = (speed3 < 0.01 ? 0.0 : speed3 / 5.5 );
//                            speed4 = (speed4 < 0.01 ? 0.0 : speed4 / 5.5 );

                            speed1 = (speed1 < 0.01 ? 0.0 : speed1 * parentFrm.coeffSpeed);
                            speed2 = (speed2 < 0.01 ? 0.0 : speed2 * parentFrm.coeffSpeed);
                            speed3 = (speed3 < 0.01 ? 0.0 : speed3 * parentFrm.coeffSpeed);
                            speed4 = (speed4 < 0.01 ? 0.0 : speed4 * parentFrm.coeffSpeed);


//                            System.out.println("Speed 1 = " + String.format("%.12f", speed1));
//                            System.out.println("Speed 2 = " + String.format("%.12f", speed2));
//                            System.out.println("Speed 3 = " + String.format("%.12f", speed3));
//                            System.out.println("Speed 4 = " + String.format("%.12f", speed4));



//                            System.out.println(String.format("%.12f", speed10));
//                            System.out.println("Speed 20 = " + speed20);
//                            System.out.println("Speed 30 = " + speed30);
//                            System.out.println("Speed 40 = " + speed40);

                            PreparedStatement pstmSpeed1 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
                                    "VALUES (?, ?)");
                            pstmSpeed1.setTimestamp(1, timeStamp1_1);
                            pstmSpeed1.setDouble(2, speed1);

                            pstmSpeed1.executeUpdate();

                            PreparedStatement pstmSpeed2 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
                                    "VALUES (?, ?)");
                            pstmSpeed2.setTimestamp(1, timeStamp4);
                            pstmSpeed2.setDouble(2, speed2);

                            pstmSpeed2.executeUpdate();

                            PreparedStatement pstmSpeed3 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
                                    "VALUES (?, ?)");
                            pstmSpeed3.setTimestamp(1, timeStamp7);
                            pstmSpeed3.setDouble(2, speed3);

                            pstmSpeed3.executeUpdate();

                            PreparedStatement pstmSpeed4 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
                                    "VALUES (?, ?)");
                            pstmSpeed4.setTimestamp(1, timeStamp10);
                            pstmSpeed4.setDouble(2, speed4);

                            pstmSpeed4.executeUpdate();
//                            System.out.println("Speed SQL");

                            timeStamp1_1.setTime(timeStamp1_1.getTime() + 1 * 1000L * 1);
                            timeStamp4.setTime(timeStamp4.getTime() + 1 * 1000L * 1);
                            timeStamp7.setTime(timeStamp7.getTime() + 1 * 1000L * 1);
                            timeStamp10.setTime(timeStamp10.getTime() + 1 * 1000L * 1);

                        }
//Inserting digital inputs 10 times per second
//                        for (int k = 0; k < 10; k++) {
//
//                        }
//                    }
                    conn3.close();
                } catch (Exception ex) {ex.printStackTrace();}


                try {
                    Connection conn4;
                    conn4 = DriverManager.getConnection(strUrl);
//                    System.out.println("Connection for Digits start...");

                    for (int j = 0; j < 5; j++) {

//                        System.out.println("Connection for Digits continue...");

                        bytesForDigitalSet1[0] = bytes[6 + j*66];
                        bytesForDigitalSet1[1] = bytes[7 + j*66];
                        bytesForDigitalSet1[2] = bytes[8 + j*66];
                        bytesForDigitalSet1[3] = bytes[9 + j*66];
                        bytesForDigitalSet1[4] = bytes[10 + j*66];

                        bytesForDigitalSet2[0] = bytes[11 + j*66];
                        bytesForDigitalSet2[1] = bytes[12 + j*66];
                        bytesForDigitalSet2[2] = bytes[13 + j*66];
                        bytesForDigitalSet2[3] = bytes[14 + j*66];
                        bytesForDigitalSet2[4] = bytes[15 + j*66];

                        bytesForDigitalSet3[0] = bytes[20 + j*66];
                        bytesForDigitalSet3[1] = bytes[21 + j*66];
                        bytesForDigitalSet3[2] = bytes[22 + j*66];
                        bytesForDigitalSet3[3] = bytes[23 + j*66];
                        bytesForDigitalSet3[4] = bytes[24 + j*66];

                        bytesForDigitalSet4[0] = bytes[25 + j*66];
                        bytesForDigitalSet4[1] = bytes[26 + j*66];
                        bytesForDigitalSet4[2] = bytes[27 + j*66];
                        bytesForDigitalSet4[3] = bytes[28 + j*66];
                        bytesForDigitalSet4[4] = bytes[29 + j*66];

                        bytesForDigitalSet5[0] = bytes[30 + j*66];
                        bytesForDigitalSet5[1] = bytes[31 + j*66];
                        bytesForDigitalSet5[2] = bytes[32 + j*66];
                        bytesForDigitalSet5[3] = bytes[33 + j*66];
                        bytesForDigitalSet5[4] = bytes[34 + j*66];

                        bytesForDigitalSet6[0] = bytes[39 + j*66];
                        bytesForDigitalSet6[1] = bytes[40 + j*66];
                        bytesForDigitalSet6[2] = bytes[41 + j*66];
                        bytesForDigitalSet6[3] = bytes[42 + j*66];
                        bytesForDigitalSet6[4] = bytes[43 + j*66];

                        bytesForDigitalSet7[0] = bytes[44 + j*66];
                        bytesForDigitalSet7[1] = bytes[45 + j*66];
                        bytesForDigitalSet7[2] = bytes[46 + j*66];
                        bytesForDigitalSet7[3] = bytes[47 + j*66];
                        bytesForDigitalSet7[4] = bytes[48 + j*66];

                        bytesForDigitalSet8[0] = bytes[53 + j*66];
                        bytesForDigitalSet8[1] = bytes[54 + j*66];
                        bytesForDigitalSet8[2] = bytes[55 + j*66];
                        bytesForDigitalSet8[3] = bytes[56 + j*66];
                        bytesForDigitalSet8[4] = bytes[57 + j*66];

                        bytesForDigitalSet9[0] = bytes[58 + j*66];
                        bytesForDigitalSet9[1] = bytes[59 + j*66];
                        bytesForDigitalSet9[2] = bytes[60 + j*66];
                        bytesForDigitalSet9[3] = bytes[61 + j*66];
                        bytesForDigitalSet9[4] = bytes[62 + j*66];

                        bytesForDigitalSet10[0] = bytes[63 + j*66];
                        bytesForDigitalSet10[1] = bytes[64 + j*66];
                        bytesForDigitalSet10[2] = bytes[65 + j*66];
                        bytesForDigitalSet10[3] = bytes[66 + j*66];
                        bytesForDigitalSet10[4] = bytes[67 + j*66];

                        boolean[] digitalSet1 = appendAllDigits(bytesForDigitalSet1);
                        boolean[] digitalSet2 = appendAllDigits(bytesForDigitalSet2);
                        boolean[] digitalSet3 = appendAllDigits(bytesForDigitalSet3);
                        boolean[] digitalSet4 = appendAllDigits(bytesForDigitalSet4);
                        boolean[] digitalSet5 = appendAllDigits(bytesForDigitalSet5);
                        boolean[] digitalSet6 = appendAllDigits(bytesForDigitalSet6);
                        boolean[] digitalSet7 = appendAllDigits(bytesForDigitalSet7);
                        boolean[] digitalSet8 = appendAllDigits(bytesForDigitalSet8);
                        boolean[] digitalSet9 = appendAllDigits(bytesForDigitalSet9);
                        boolean[] digitalSet10 = appendAllDigits(bytesForDigitalSet10);


// Insert FIRST appearance of digits
                        PreparedStatement pstmDigits1 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits1.setTimestamp(1, timeStamp1_2);
                        pstmDigits1.setBoolean(2, digitalSet1[7]);
                        pstmDigits1.setBoolean(3, digitalSet1[6]);
                        pstmDigits1.setBoolean(4, digitalSet1[5]);
                        pstmDigits1.setBoolean(5, digitalSet1[4]);
                        pstmDigits1.setBoolean(6, digitalSet1[3]);
                        pstmDigits1.setBoolean(7, digitalSet1[2]);
                        pstmDigits1.setBoolean(8, digitalSet1[1]);
                        pstmDigits1.setBoolean(9, digitalSet1[32]);
                        pstmDigits1.setBoolean(10, digitalSet1[33]);
                        pstmDigits1.setBoolean(11, digitalSet1[34]);
                        pstmDigits1.setBoolean(12, digitalSet1[21]);
                        pstmDigits1.setBoolean(13, digitalSet1[20]);
                        pstmDigits1.setBoolean(14, digitalSet1[19]);
                        pstmDigits1.setBoolean(15, digitalSet1[18]);
                        pstmDigits1.setBoolean(16, digitalSet1[17]);
                        pstmDigits1.setBoolean(17, digitalSet1[31]);
                        pstmDigits1.setBoolean(18, digitalSet1[30]);
                        pstmDigits1.setBoolean(19, digitalSet1[29]);
                        pstmDigits1.setBoolean(20, digitalSet1[28]);
                        pstmDigits1.setBoolean(21, digitalSet1[16]);
                        pstmDigits1.setBoolean(22, digitalSet1[13]);
                        pstmDigits1.setBoolean(23, digitalSet1[12]);
                        pstmDigits1.setBoolean(24, digitalSet1[11]);
                        pstmDigits1.setBoolean(25, digitalSet1[10]);
                        pstmDigits1.setBoolean(26, digitalSet1[23]);
                        pstmDigits1.setBoolean(27, digitalSet1[9]);
                        pstmDigits1.setBoolean(28, digitalSet1[8]);
                        pstmDigits1.setBoolean(29, digitalSet1[0]);
                        pstmDigits1.setBoolean(30, digitalSet1[36]);
                        pstmDigits1.setBoolean(31, digitalSet1[35]);
                        pstmDigits1.setBoolean(32, digitalSet1[27]);
                        pstmDigits1.setBoolean(33, digitalSet1[26]);
                        pstmDigits1.setBoolean(34, digitalSet1[25]);
                        pstmDigits1.setBoolean(35, digitalSet1[24]);
                        pstmDigits1.setBoolean(36, digitalSet1[15]);
                        pstmDigits1.setBoolean(37, digitalSet1[14]);
                        pstmDigits1.setBoolean(38, digitalSet1[22]);

                        pstmDigits1.executeUpdate();

// Insert SECOND appearance of digits
                        PreparedStatement pstmDigits2 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits2.setTimestamp(1, timeStamp2);
                        pstmDigits2.setBoolean(2, digitalSet2[7]);
                        pstmDigits2.setBoolean(3, digitalSet2[6]);
                        pstmDigits2.setBoolean(4, digitalSet2[5]);
                        pstmDigits2.setBoolean(5, digitalSet2[4]);
                        pstmDigits2.setBoolean(6, digitalSet2[3]);
                        pstmDigits2.setBoolean(7, digitalSet2[2]);
                        pstmDigits2.setBoolean(8, digitalSet2[1]);
                        pstmDigits2.setBoolean(9, digitalSet2[32]);
                        pstmDigits2.setBoolean(10, digitalSet2[33]);
                        pstmDigits2.setBoolean(11, digitalSet2[34]);
                        pstmDigits2.setBoolean(12, digitalSet2[21]);
                        pstmDigits2.setBoolean(13, digitalSet2[20]);
                        pstmDigits2.setBoolean(14, digitalSet2[19]);
                        pstmDigits2.setBoolean(15, digitalSet2[18]);
                        pstmDigits2.setBoolean(16, digitalSet2[17]);
                        pstmDigits2.setBoolean(17, digitalSet2[31]);
                        pstmDigits2.setBoolean(18, digitalSet2[30]);
                        pstmDigits2.setBoolean(19, digitalSet2[29]);
                        pstmDigits2.setBoolean(20, digitalSet2[28]);
                        pstmDigits2.setBoolean(21, digitalSet2[16]);
                        pstmDigits2.setBoolean(22, digitalSet2[13]);
                        pstmDigits2.setBoolean(23, digitalSet2[12]);
                        pstmDigits2.setBoolean(24, digitalSet2[11]);
                        pstmDigits2.setBoolean(25, digitalSet2[10]);
                        pstmDigits2.setBoolean(26, digitalSet2[23]);
                        pstmDigits2.setBoolean(27, digitalSet2[9]);
                        pstmDigits2.setBoolean(28, digitalSet2[8]);
                        pstmDigits2.setBoolean(29, digitalSet2[0]);
                        pstmDigits2.setBoolean(30, digitalSet2[36]);
                        pstmDigits2.setBoolean(31, digitalSet2[35]);
                        pstmDigits2.setBoolean(32, digitalSet2[27]);
                        pstmDigits2.setBoolean(33, digitalSet2[26]);
                        pstmDigits2.setBoolean(34, digitalSet2[25]);
                        pstmDigits2.setBoolean(35, digitalSet2[24]);
                        pstmDigits2.setBoolean(36, digitalSet2[15]);
                        pstmDigits2.setBoolean(37, digitalSet2[14]);
                        pstmDigits2.setBoolean(38, digitalSet2[22]);

                        pstmDigits2.executeUpdate();

// Insert THIRD appearance of digits
                        PreparedStatement pstmDigits3 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits3.setTimestamp(1, timeStamp3);
                        pstmDigits3.setBoolean(2, digitalSet3[7]);
                        pstmDigits3.setBoolean(3, digitalSet3[6]);
                        pstmDigits3.setBoolean(4, digitalSet3[5]);
                        pstmDigits3.setBoolean(5, digitalSet3[4]);
                        pstmDigits3.setBoolean(6, digitalSet3[3]);
                        pstmDigits3.setBoolean(7, digitalSet3[2]);
                        pstmDigits3.setBoolean(8, digitalSet3[1]);
                        pstmDigits3.setBoolean(9, digitalSet3[32]);
                        pstmDigits3.setBoolean(10, digitalSet3[33]);
                        pstmDigits3.setBoolean(11, digitalSet3[34]);
                        pstmDigits3.setBoolean(12, digitalSet3[21]);
                        pstmDigits3.setBoolean(13, digitalSet3[20]);
                        pstmDigits3.setBoolean(14, digitalSet3[19]);
                        pstmDigits3.setBoolean(15, digitalSet3[18]);
                        pstmDigits3.setBoolean(16, digitalSet3[17]);
                        pstmDigits3.setBoolean(17, digitalSet3[31]);
                        pstmDigits3.setBoolean(18, digitalSet3[30]);
                        pstmDigits3.setBoolean(19, digitalSet3[29]);
                        pstmDigits3.setBoolean(20, digitalSet3[28]);
                        pstmDigits3.setBoolean(21, digitalSet3[16]);
                        pstmDigits3.setBoolean(22, digitalSet3[13]);
                        pstmDigits3.setBoolean(23, digitalSet3[12]);
                        pstmDigits3.setBoolean(24, digitalSet3[11]);
                        pstmDigits3.setBoolean(25, digitalSet3[10]);
                        pstmDigits3.setBoolean(26, digitalSet3[23]);
                        pstmDigits3.setBoolean(27, digitalSet3[9]);
                        pstmDigits3.setBoolean(28, digitalSet3[8]);
                        pstmDigits3.setBoolean(29, digitalSet3[0]);
                        pstmDigits3.setBoolean(30, digitalSet3[36]);
                        pstmDigits3.setBoolean(31, digitalSet3[35]);
                        pstmDigits3.setBoolean(32, digitalSet3[27]);
                        pstmDigits3.setBoolean(33, digitalSet3[26]);
                        pstmDigits3.setBoolean(34, digitalSet3[25]);
                        pstmDigits3.setBoolean(35, digitalSet3[24]);
                        pstmDigits3.setBoolean(36, digitalSet3[15]);
                        pstmDigits3.setBoolean(37, digitalSet3[14]);
                        pstmDigits3.setBoolean(38, digitalSet3[22]);

                        pstmDigits3.executeUpdate();

// Insert FOURTH appearance of digits
                        PreparedStatement pstmDigits4 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits4.setTimestamp(1, timeStamp5);
                        pstmDigits4.setBoolean(2, digitalSet4[7]);
                        pstmDigits4.setBoolean(3, digitalSet4[6]);
                        pstmDigits4.setBoolean(4, digitalSet4[5]);
                        pstmDigits4.setBoolean(5, digitalSet4[4]);
                        pstmDigits4.setBoolean(6, digitalSet4[3]);
                        pstmDigits4.setBoolean(7, digitalSet4[2]);
                        pstmDigits4.setBoolean(8, digitalSet4[1]);
                        pstmDigits4.setBoolean(9, digitalSet4[32]);
                        pstmDigits4.setBoolean(10, digitalSet4[33]);
                        pstmDigits4.setBoolean(11, digitalSet4[34]);
                        pstmDigits4.setBoolean(12, digitalSet4[21]);
                        pstmDigits4.setBoolean(13, digitalSet4[20]);
                        pstmDigits4.setBoolean(14, digitalSet4[19]);
                        pstmDigits4.setBoolean(15, digitalSet4[18]);
                        pstmDigits4.setBoolean(16, digitalSet4[17]);
                        pstmDigits4.setBoolean(17, digitalSet4[31]);
                        pstmDigits4.setBoolean(18, digitalSet4[30]);
                        pstmDigits4.setBoolean(19, digitalSet4[29]);
                        pstmDigits4.setBoolean(20, digitalSet4[28]);
                        pstmDigits4.setBoolean(21, digitalSet4[16]);
                        pstmDigits4.setBoolean(22, digitalSet4[13]);
                        pstmDigits4.setBoolean(23, digitalSet4[12]);
                        pstmDigits4.setBoolean(24, digitalSet4[11]);
                        pstmDigits4.setBoolean(25, digitalSet4[10]);
                        pstmDigits4.setBoolean(26, digitalSet4[23]);
                        pstmDigits4.setBoolean(27, digitalSet4[9]);
                        pstmDigits4.setBoolean(28, digitalSet4[8]);
                        pstmDigits4.setBoolean(29, digitalSet4[0]);
                        pstmDigits4.setBoolean(30, digitalSet4[36]);
                        pstmDigits4.setBoolean(31, digitalSet4[35]);
                        pstmDigits4.setBoolean(32, digitalSet4[27]);
                        pstmDigits4.setBoolean(33, digitalSet4[26]);
                        pstmDigits4.setBoolean(34, digitalSet4[25]);
                        pstmDigits4.setBoolean(35, digitalSet4[24]);
                        pstmDigits4.setBoolean(36, digitalSet4[15]);
                        pstmDigits4.setBoolean(37, digitalSet4[14]);
                        pstmDigits4.setBoolean(38, digitalSet4[22]);
                        pstmDigits4.executeUpdate();

// Insert FIFTH appearance of digits
                        PreparedStatement pstmDigits5 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits5.setTimestamp(1, timeStamp6);
                        pstmDigits5.setBoolean(2, digitalSet5[7]);
                        pstmDigits5.setBoolean(3, digitalSet5[6]);
                        pstmDigits5.setBoolean(4, digitalSet5[5]);
                        pstmDigits5.setBoolean(5, digitalSet5[4]);
                        pstmDigits5.setBoolean(6, digitalSet5[3]);
                        pstmDigits5.setBoolean(7, digitalSet5[2]);
                        pstmDigits5.setBoolean(8, digitalSet5[1]);
                        pstmDigits5.setBoolean(9, digitalSet5[32]);
                        pstmDigits5.setBoolean(10, digitalSet5[33]);
                        pstmDigits5.setBoolean(11, digitalSet5[34]);
                        pstmDigits5.setBoolean(12, digitalSet5[21]);
                        pstmDigits5.setBoolean(13, digitalSet5[20]);
                        pstmDigits5.setBoolean(14, digitalSet5[19]);
                        pstmDigits5.setBoolean(15, digitalSet5[18]);
                        pstmDigits5.setBoolean(16, digitalSet5[17]);
                        pstmDigits5.setBoolean(17, digitalSet5[31]);
                        pstmDigits5.setBoolean(18, digitalSet5[30]);
                        pstmDigits5.setBoolean(19, digitalSet5[29]);
                        pstmDigits5.setBoolean(20, digitalSet5[28]);
                        pstmDigits5.setBoolean(21, digitalSet5[16]);
                        pstmDigits5.setBoolean(22, digitalSet5[13]);
                        pstmDigits5.setBoolean(23, digitalSet5[12]);
                        pstmDigits5.setBoolean(24, digitalSet5[11]);
                        pstmDigits5.setBoolean(25, digitalSet5[10]);
                        pstmDigits5.setBoolean(26, digitalSet5[23]);
                        pstmDigits5.setBoolean(27, digitalSet5[9]);
                        pstmDigits5.setBoolean(28, digitalSet5[8]);
                        pstmDigits5.setBoolean(29, digitalSet5[0]);
                        pstmDigits5.setBoolean(30, digitalSet5[36]);
                        pstmDigits5.setBoolean(31, digitalSet5[35]);
                        pstmDigits5.setBoolean(32, digitalSet5[27]);
                        pstmDigits5.setBoolean(33, digitalSet5[26]);
                        pstmDigits5.setBoolean(34, digitalSet5[25]);
                        pstmDigits5.setBoolean(35, digitalSet5[24]);
                        pstmDigits5.setBoolean(36, digitalSet5[15]);
                        pstmDigits5.setBoolean(37, digitalSet5[14]);
                        pstmDigits5.setBoolean(38, digitalSet5[22]);

                        pstmDigits5.executeUpdate();

// Insert SIXTH appearance of digits
                        PreparedStatement pstmDigits6 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits6.setTimestamp(1, timeStamp7_2);
                        pstmDigits6.setBoolean(2, digitalSet6[7]);
                        pstmDigits6.setBoolean(3, digitalSet6[6]);
                        pstmDigits6.setBoolean(4, digitalSet6[5]);
                        pstmDigits6.setBoolean(5, digitalSet6[4]);
                        pstmDigits6.setBoolean(6, digitalSet6[3]);
                        pstmDigits6.setBoolean(7, digitalSet6[2]);
                        pstmDigits6.setBoolean(8, digitalSet6[1]);
                        pstmDigits6.setBoolean(9, digitalSet6[32]);
                        pstmDigits6.setBoolean(10, digitalSet6[33]);
                        pstmDigits6.setBoolean(11, digitalSet6[34]);
                        pstmDigits6.setBoolean(12, digitalSet6[21]);
                        pstmDigits6.setBoolean(13, digitalSet6[20]);
                        pstmDigits6.setBoolean(14, digitalSet6[19]);
                        pstmDigits6.setBoolean(15, digitalSet6[18]);
                        pstmDigits6.setBoolean(16, digitalSet6[17]);
                        pstmDigits6.setBoolean(17, digitalSet6[31]);
                        pstmDigits6.setBoolean(18, digitalSet6[30]);
                        pstmDigits6.setBoolean(19, digitalSet6[29]);
                        pstmDigits6.setBoolean(20, digitalSet6[28]);
                        pstmDigits6.setBoolean(21, digitalSet6[16]);
                        pstmDigits6.setBoolean(22, digitalSet6[13]);
                        pstmDigits6.setBoolean(23, digitalSet6[12]);
                        pstmDigits6.setBoolean(24, digitalSet6[11]);
                        pstmDigits6.setBoolean(25, digitalSet6[10]);
                        pstmDigits6.setBoolean(26, digitalSet6[23]);
                        pstmDigits6.setBoolean(27, digitalSet6[9]);
                        pstmDigits6.setBoolean(28, digitalSet6[8]);
                        pstmDigits6.setBoolean(29, digitalSet6[0]);
                        pstmDigits6.setBoolean(30, digitalSet6[36]);
                        pstmDigits6.setBoolean(31, digitalSet6[35]);
                        pstmDigits6.setBoolean(32, digitalSet6[27]);
                        pstmDigits6.setBoolean(33, digitalSet6[26]);
                        pstmDigits6.setBoolean(34, digitalSet6[25]);
                        pstmDigits6.setBoolean(35, digitalSet6[24]);
                        pstmDigits6.setBoolean(36, digitalSet6[15]);
                        pstmDigits6.setBoolean(37, digitalSet6[14]);
                        pstmDigits6.setBoolean(38, digitalSet6[22]);

                        pstmDigits6.executeUpdate();

// Insert SEVENTH appearance of digits
                        PreparedStatement pstmDigits7 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits7.setTimestamp(1, timeStamp8);
                        pstmDigits7.setBoolean(2, digitalSet7[7]);
                        pstmDigits7.setBoolean(3, digitalSet7[6]);
                        pstmDigits7.setBoolean(4, digitalSet7[5]);
                        pstmDigits7.setBoolean(5, digitalSet7[4]);
                        pstmDigits7.setBoolean(6, digitalSet7[3]);
                        pstmDigits7.setBoolean(7, digitalSet7[2]);
                        pstmDigits7.setBoolean(8, digitalSet7[1]);
                        pstmDigits7.setBoolean(9, digitalSet7[32]);
                        pstmDigits7.setBoolean(10, digitalSet7[33]);
                        pstmDigits7.setBoolean(11, digitalSet7[34]);
                        pstmDigits7.setBoolean(12, digitalSet7[21]);
                        pstmDigits7.setBoolean(13, digitalSet7[20]);
                        pstmDigits7.setBoolean(14, digitalSet7[19]);
                        pstmDigits7.setBoolean(15, digitalSet7[18]);
                        pstmDigits7.setBoolean(16, digitalSet7[17]);
                        pstmDigits7.setBoolean(17, digitalSet7[31]);
                        pstmDigits7.setBoolean(18, digitalSet7[30]);
                        pstmDigits7.setBoolean(19, digitalSet7[29]);
                        pstmDigits7.setBoolean(20, digitalSet7[28]);
                        pstmDigits7.setBoolean(21, digitalSet7[16]);
                        pstmDigits7.setBoolean(22, digitalSet7[13]);
                        pstmDigits7.setBoolean(23, digitalSet7[12]);
                        pstmDigits7.setBoolean(24, digitalSet7[11]);
                        pstmDigits7.setBoolean(25, digitalSet7[10]);
                        pstmDigits7.setBoolean(26, digitalSet7[23]);
                        pstmDigits7.setBoolean(27, digitalSet7[9]);
                        pstmDigits7.setBoolean(28, digitalSet7[8]);
                        pstmDigits7.setBoolean(29, digitalSet7[0]);
                        pstmDigits7.setBoolean(30, digitalSet7[36]);
                        pstmDigits7.setBoolean(31, digitalSet7[35]);
                        pstmDigits7.setBoolean(32, digitalSet7[27]);
                        pstmDigits7.setBoolean(33, digitalSet7[26]);
                        pstmDigits7.setBoolean(34, digitalSet7[25]);
                        pstmDigits7.setBoolean(35, digitalSet7[24]);
                        pstmDigits7.setBoolean(36, digitalSet7[15]);
                        pstmDigits7.setBoolean(37, digitalSet7[14]);
                        pstmDigits7.setBoolean(38, digitalSet7[22]);

                        pstmDigits7.executeUpdate();

// Insert EIGHTS appearance of digits
                        PreparedStatement pstmDigits8 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits8.setTimestamp(1, timeStamp9);
                        pstmDigits8.setBoolean(2, digitalSet8[7]);
                        pstmDigits8.setBoolean(3, digitalSet8[6]);
                        pstmDigits8.setBoolean(4, digitalSet8[5]);
                        pstmDigits8.setBoolean(5, digitalSet8[4]);
                        pstmDigits8.setBoolean(6, digitalSet8[3]);
                        pstmDigits8.setBoolean(7, digitalSet8[2]);
                        pstmDigits8.setBoolean(8, digitalSet8[1]);
                        pstmDigits8.setBoolean(9, digitalSet8[32]);
                        pstmDigits8.setBoolean(10, digitalSet8[33]);
                        pstmDigits8.setBoolean(11, digitalSet8[34]);
                        pstmDigits8.setBoolean(12, digitalSet8[21]);
                        pstmDigits8.setBoolean(13, digitalSet8[20]);
                        pstmDigits8.setBoolean(14, digitalSet8[19]);
                        pstmDigits8.setBoolean(15, digitalSet8[18]);
                        pstmDigits8.setBoolean(16, digitalSet8[17]);
                        pstmDigits8.setBoolean(17, digitalSet8[31]);
                        pstmDigits8.setBoolean(18, digitalSet8[30]);
                        pstmDigits8.setBoolean(19, digitalSet8[29]);
                        pstmDigits8.setBoolean(20, digitalSet8[28]);
                        pstmDigits8.setBoolean(21, digitalSet8[16]);
                        pstmDigits8.setBoolean(22, digitalSet8[13]);
                        pstmDigits8.setBoolean(23, digitalSet8[12]);
                        pstmDigits8.setBoolean(24, digitalSet8[11]);
                        pstmDigits8.setBoolean(25, digitalSet8[10]);
                        pstmDigits8.setBoolean(26, digitalSet8[23]);
                        pstmDigits8.setBoolean(27, digitalSet8[9]);
                        pstmDigits8.setBoolean(28, digitalSet8[8]);
                        pstmDigits8.setBoolean(29, digitalSet8[0]);
                        pstmDigits8.setBoolean(30, digitalSet8[36]);
                        pstmDigits8.setBoolean(31, digitalSet8[35]);
                        pstmDigits8.setBoolean(32, digitalSet8[27]);
                        pstmDigits8.setBoolean(33, digitalSet8[26]);
                        pstmDigits8.setBoolean(34, digitalSet8[25]);
                        pstmDigits8.setBoolean(35, digitalSet8[24]);
                        pstmDigits8.setBoolean(36, digitalSet8[15]);
                        pstmDigits8.setBoolean(37, digitalSet8[14]);
                        pstmDigits8.setBoolean(38, digitalSet8[22]);

                        pstmDigits8.executeUpdate();

// Insert NINTH appearance of digits
                        PreparedStatement pstmDigits9 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits9.setTimestamp(1, timeStamp11);
                        pstmDigits9.setBoolean(2, digitalSet9[7]);
                        pstmDigits9.setBoolean(3, digitalSet9[6]);
                        pstmDigits9.setBoolean(4, digitalSet9[5]);
                        pstmDigits9.setBoolean(5, digitalSet9[4]);
                        pstmDigits9.setBoolean(6, digitalSet9[3]);
                        pstmDigits9.setBoolean(7, digitalSet9[2]);
                        pstmDigits9.setBoolean(8, digitalSet9[1]);
                        pstmDigits9.setBoolean(9, digitalSet9[32]);
                        pstmDigits9.setBoolean(10, digitalSet9[33]);
                        pstmDigits9.setBoolean(11, digitalSet9[34]);
                        pstmDigits9.setBoolean(12, digitalSet9[21]);
                        pstmDigits9.setBoolean(13, digitalSet9[20]);
                        pstmDigits9.setBoolean(14, digitalSet9[19]);
                        pstmDigits9.setBoolean(15, digitalSet9[18]);
                        pstmDigits9.setBoolean(16, digitalSet9[17]);
                        pstmDigits9.setBoolean(17, digitalSet9[31]);
                        pstmDigits9.setBoolean(18, digitalSet9[30]);
                        pstmDigits9.setBoolean(19, digitalSet9[29]);
                        pstmDigits9.setBoolean(20, digitalSet9[28]);
                        pstmDigits9.setBoolean(21, digitalSet9[16]);
                        pstmDigits9.setBoolean(22, digitalSet9[13]);
                        pstmDigits9.setBoolean(23, digitalSet9[12]);
                        pstmDigits9.setBoolean(24, digitalSet9[11]);
                        pstmDigits9.setBoolean(25, digitalSet9[10]);
                        pstmDigits9.setBoolean(26, digitalSet9[23]);
                        pstmDigits9.setBoolean(27, digitalSet9[9]);
                        pstmDigits9.setBoolean(28, digitalSet9[8]);
                        pstmDigits9.setBoolean(29, digitalSet9[0]);
                        pstmDigits9.setBoolean(30, digitalSet9[36]);
                        pstmDigits9.setBoolean(31, digitalSet9[35]);
                        pstmDigits9.setBoolean(32, digitalSet9[27]);
                        pstmDigits9.setBoolean(33, digitalSet9[26]);
                        pstmDigits9.setBoolean(34, digitalSet9[25]);
                        pstmDigits9.setBoolean(35, digitalSet9[24]);
                        pstmDigits9.setBoolean(36, digitalSet9[15]);
                        pstmDigits9.setBoolean(37, digitalSet9[14]);
                        pstmDigits9.setBoolean(38, digitalSet9[22]);

                        pstmDigits9.executeUpdate();

//                        PreparedStatement pstmDigits15 = conn4.prepareStatement("DELETE MAX TIMESTAMP from APP.DIGITS ");


// Insert TENTH appearance of digits
                        PreparedStatement pstmDigits10 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                                " ?, ?, ?, ?, ?, ?, ?, ? )");
                        pstmDigits10.setTimestamp(1, timeStamp12);
                        pstmDigits10.setBoolean(2, digitalSet10[7]);
                        pstmDigits10.setBoolean(3, digitalSet10[6]);
                        pstmDigits10.setBoolean(4, digitalSet10[5]);
                        pstmDigits10.setBoolean(5, digitalSet10[4]);
                        pstmDigits10.setBoolean(6, digitalSet10[3]);
                        pstmDigits10.setBoolean(7, digitalSet10[2]);
                        pstmDigits10.setBoolean(8, digitalSet10[1]);
                        pstmDigits10.setBoolean(9, digitalSet10[32]);
                        pstmDigits10.setBoolean(10, digitalSet10[33]);
                        pstmDigits10.setBoolean(11, digitalSet10[34]);
                        pstmDigits10.setBoolean(12, digitalSet10[21]);
                        pstmDigits10.setBoolean(13, digitalSet10[20]);
                        pstmDigits10.setBoolean(14, digitalSet10[19]);
                        pstmDigits10.setBoolean(15, digitalSet10[18]);
                        pstmDigits10.setBoolean(16, digitalSet10[17]);
                        pstmDigits10.setBoolean(17, digitalSet10[31]);
                        pstmDigits10.setBoolean(18, digitalSet10[30]);
                        pstmDigits10.setBoolean(19, digitalSet10[29]);
                        pstmDigits10.setBoolean(20, digitalSet10[28]);
                        pstmDigits10.setBoolean(21, digitalSet10[16]);
                        pstmDigits10.setBoolean(22, digitalSet10[13]);
                        pstmDigits10.setBoolean(23, digitalSet10[12]);
                        pstmDigits10.setBoolean(24, digitalSet10[11]);
                        pstmDigits10.setBoolean(25, digitalSet10[10]);
                        pstmDigits10.setBoolean(26, digitalSet10[23]);
                        pstmDigits10.setBoolean(27, digitalSet10[9]);
                        pstmDigits10.setBoolean(28, digitalSet10[8]);
                        pstmDigits10.setBoolean(29, digitalSet10[0]);
                        pstmDigits10.setBoolean(30, digitalSet10[36]);
                        pstmDigits10.setBoolean(31, digitalSet10[35]);
                        pstmDigits10.setBoolean(32, digitalSet10[27]);
                        pstmDigits10.setBoolean(33, digitalSet10[26]);
                        pstmDigits10.setBoolean(34, digitalSet10[25]);
                        pstmDigits10.setBoolean(35, digitalSet10[24]);
                        pstmDigits10.setBoolean(36, digitalSet10[15]);
                        pstmDigits10.setBoolean(37, digitalSet10[14]);
                        pstmDigits10.setBoolean(38, digitalSet10[22]);

                        pstmDigits10.executeUpdate();

//                        System.out.println("Connection for Digits end...");

//////////////////////////////////////////////////////////////////////////////////////////

                        timeStamp1_2.setTime(timeStamp1_2.getTime() + 1 * 1000L * 1);
                        timeStamp2.setTime(timeStamp2.getTime() + 1 * 1000L * 1);
                        timeStamp3.setTime(timeStamp3.getTime() + 1 * 1000L * 1);
                        timeStamp5.setTime(timeStamp5.getTime() + 1 * 1000L * 1);
                        timeStamp6.setTime(timeStamp6.getTime() + 1 * 1000L * 1);
                        timeStamp7_2.setTime(timeStamp7_2.getTime() + 1 * 1000L * 1);
                        timeStamp8.setTime(timeStamp8.getTime() + 1 * 1000L * 1);
                        timeStamp9.setTime(timeStamp9.getTime() + 1 * 1000L * 1);
                        timeStamp11.setTime(timeStamp11.getTime() + 1 * 1000L * 1);
                        timeStamp12.setTime(timeStamp12.getTime() + 1 * 1000L * 1);

//                        System.out.println("Connection for Digits Timestamp changed...");
//
//                        System.out.println("TimeStamp1 for Digits: " + timeStamp1_1);
//                        System.out.println("TimeStamp1 for Digits: " + timeStamp1_2);
                    }

                    is.mark(0);
                } catch (Exception ex) {ex.printStackTrace();}
        }
        } finally {is.close();}
        parentFrm.progressBar2.setValue(12570624);
        return bytes;
    }

    public static java.sql.Date getDateTime(int[] data) {
        data[0] += 2000;
        java.util.Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = new java.util.Date();
        cal.setTime(utilDate);
        cal.set(Calendar.YEAR, data[0]);
        cal.set(Calendar.MONTH, data[1] - 1);
        cal.set(Calendar.DAY_OF_MONTH, data[2]);
        cal.set(Calendar.HOUR_OF_DAY, data[3]);
        cal.set(Calendar.MINUTE, data[4]);
        cal.set(Calendar.SECOND, data[5]);
        cal.set(Calendar.MILLISECOND, data[6]);

        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        System.out.println(dateFormat.format(utilDate));
//        System.out.println(dateFormat.format(sqlDate));

        return sqlDate;

    }


//    static int bcd2dec(int bcd) {
//        return (bcd != 0) ? (bcd2dec(bcd >> 4) * 10 + (bcd & 0x0f)) : 0;
//    }
    static int bcd2dec(int bcd) {
        return (int)((bcd & 0xF) + (((int) bcd & 0xF0) >> 4) * 10);
    }

    static int mergeTwoBytes(byte[] bytes) {
        return (int)(((long)bytes[0] << 8)  | ((long)bytes[1]) & 0xff );
    }

    static long mergeFourBytes(byte[] bytes) {
//        return (long)((((long)(bytes[0] ) << 24)) | ((long)(bytes[1] & 0xff)) << 16 | ((long)bytes[2] & 0xff) << 8 | ((long)bytes[3] & 0xff));
        return (long)((((long)(bytes[0] & 0xffL) << 24)) | ((long)(bytes[1] & 0xffL)) << 16 | ((long)(bytes[2] & 0xffL)) << 8 | ((long)(bytes[3] & 0xffL)));

        //return 4294967295L;
        //return 0xFFFFFFFFL;
    }

    static byte[] byteCoefficientToSend(double value) {
        value = 1213512.5d;
        final byte[] floatFourBytes = new byte[4];
        long double32Bits = Double.doubleToLongBits(value);
        floatFourBytes[0] = (byte) ((double32Bits >> 24) & 0xff);
        floatFourBytes[1] = (byte) ((double32Bits >> 16) & 0xff);
        floatFourBytes[2] = (byte) ((double32Bits >> 8) & 0xff);
        floatFourBytes[3] = (byte) ((double32Bits >> 0) & 0xff);
        System.out.println(floatFourBytes[0]);
        System.out.println(floatFourBytes[1]);
        System.out.println(floatFourBytes[2]);
        System.out.println(floatFourBytes[3]);

        return floatFourBytes;
    }

    static double getCoefficient(byte[] bytes) {
        return Double.longBitsToDouble((long)    ((bytes[0] & 0xFFL) << 24)
                                                | ((bytes[1] & 0xFFL) << 16)
                                                | ((bytes[2] & 0xFFL) << 8)
                                                | ((bytes[3] & 0xFFL) << 0)
        );
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

    private static boolean searchEndData(byte[] bytes) {
        boolean result = false;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] != -1) {result = false; break;}
            else result = true;
        }
        return result;
    }

    private static boolean unusedData(byte[] bytes) {
        boolean result = false;
        for (int i = 0; i < 16; i++) {
            if (bytes[i] != -1) {result = false; break;}
            else result = true;
        }
        return result;
    }



    static byte[] searchStart(byte[] bytes, InputStream is) throws IOException {
            int blockSize = 340;
            byte[] newBytes = new byte[blockSize];
            is.mark(0);
            for (int i = 0; i < blockSize; i++) {
               // try {
                    int picDateTime[] = new int[7];
                    picDateTime[0] = bcd2dec(bytes[0]);
                    System.out.println("PicDateTime[0] = " + picDateTime[0]);
                    System.out.println("Bytes[0] = " + bytes[0]);
                    System.out.println("NewBytes[0] = " + newBytes[0]);

                if (picDateTime[0] != 16) {
                        flag = true;
                        is.reset();
                        is.skip(1);
                        is.mark(0);
                        is.read(bytes, 0, blockSize);
                        if (bytes[0] == -1 && bytes[1] == -1 && bytes[2] == -1 && bytes[3] == -1 && bytes[4] == -1 && bytes[5] == -1) {
                            newBytes = bytes;
                            break;
                        }
                        else

                        continue;
                    }
                    else {
//                    picDateTime[1] = bcd2dec(bytes[1]);
//                    picDateTime[2] = bcd2dec(bytes[2]);
//                    picDateTime[3] = bcd2dec(bytes[3]);
//                    picDateTime[4] = bcd2dec(bytes[4]);
//                    picDateTime[5] = bcd2dec(bytes[5]);
//                    picDateTime[6] = 0;

                        is.reset();
                        is.skip(blockSize);
                        is.mark(0);
                        newBytes = bytes;
                        System.out.println("New bytes[0] = " + newBytes[0]);
                        System.out.println("picDateTime[0] = " + picDateTime[0]);
                        break;
                    }
         //       } catch (Exception e) {
//                    is.reset();
//                    is.skip(1);
//                    is.read(bytes, 0, blockSize);
             //       continue;
            //    }
            }
            return newBytes;
        }

    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }


    private static void addParticles(Connection newDBConn, java.sql.Timestamp newTimeStamp, double speed,
                                     int pressOne, int pressTwo, boolean[] digitalSet1) throws SQLException {

        PreparedStatement pstm = newDBConn.prepareStatement("INSERT INTO APP.ALLDATA (TIMESTAMPCOL, SPEED, PRESSONE, PRESSTWO, " +
                "DIG1, DIG2," +
                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ? )");

        pstm.setTimestamp(1, newTimeStamp);
        pstm.setDouble(2, speed);
        pstm.setInt(3, pressOne);
        pstm.setInt(4, pressTwo);
        pstm.setBoolean(5, digitalSet1[7]);
        pstm.setBoolean(6, digitalSet1[6]);
        pstm.setBoolean(7, digitalSet1[5]);
        pstm.setBoolean(8, digitalSet1[4]);
        pstm.setBoolean(9, digitalSet1[3]);
        pstm.setBoolean(10, digitalSet1[2]);
        pstm.setBoolean(11, digitalSet1[1]);
        pstm.setBoolean(12, digitalSet1[32]);
        pstm.setBoolean(13, digitalSet1[33]);
        pstm.setBoolean(14, digitalSet1[34]);
        pstm.setBoolean(15, digitalSet1[21]);
        pstm.setBoolean(16, digitalSet1[20]);
        pstm.setBoolean(17, digitalSet1[19]);
        pstm.setBoolean(18, digitalSet1[18]);
        pstm.setBoolean(19, digitalSet1[17]);
        pstm.setBoolean(20, digitalSet1[31]);
        pstm.setBoolean(21, digitalSet1[30]);
        pstm.setBoolean(22, digitalSet1[29]);
        pstm.setBoolean(23, digitalSet1[28]);
        pstm.setBoolean(24, digitalSet1[16]);
        pstm.setBoolean(25, digitalSet1[13]);
        pstm.setBoolean(26, digitalSet1[12]);
        pstm.setBoolean(27, digitalSet1[11]);
        pstm.setBoolean(28, digitalSet1[10]);
        pstm.setBoolean(29, digitalSet1[23]);
        pstm.setBoolean(30, digitalSet1[9]);
        pstm.setBoolean(31, digitalSet1[8]);
        pstm.setBoolean(32, digitalSet1[0]);
        pstm.setBoolean(33, digitalSet1[36]);
        pstm.setBoolean(34, digitalSet1[35]);
        pstm.setBoolean(35, digitalSet1[27]);
        pstm.setBoolean(36, digitalSet1[26]);
        pstm.setBoolean(37, digitalSet1[25]);
        pstm.setBoolean(38, digitalSet1[24]);
        pstm.setBoolean(39, digitalSet1[15]);
        pstm.setBoolean(40, digitalSet1[14]);
        pstm.setBoolean(41, digitalSet1[22]);

        pstm.executeUpdate();
        pstm.close();
    }

    private static void addNewRow(byte[] bytes, Connection newDBConn, int[] picDateTime,
                                  boolean isFirstRow, java.sql.Timestamp previousTimeStamp,
                                  java.sql.Date dateTime, FormTwo parentFrm) throws SQLException {
        java.sql.Timestamp newTimestamp = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int picDateTime010[] = new int [7];
        int picDateTime020[] = new int [7];
        int picDateTime025[] = new int [7];
        int picDateTime030[] = new int [7];
        int picDateTime040[] = new int [7];
        int picDateTime050[] = new int [7];
        int picDateTime060[] = new int [7];
        int picDateTime070[] = new int [7];
        int picDateTime075[] = new int [7];
        int picDateTime080[] = new int [7];
        int picDateTime090[] = new int [7];

        picDateTime010[0] = picDateTime[0] - 2000;
        picDateTime020[0] = picDateTime[0] - 2000;
        picDateTime025[0] = picDateTime[0] - 2000;
        picDateTime030[0] = picDateTime[0] - 2000;
        picDateTime040[0] = picDateTime[0] - 2000;
        picDateTime050[0] = picDateTime[0] - 2000;
        picDateTime060[0] = picDateTime[0] - 2000;
        picDateTime070[0] = picDateTime[0] - 2000;
        picDateTime075[0] = picDateTime[0] - 2000;
        picDateTime080[0] = picDateTime[0] - 2000;
        picDateTime090[0] = picDateTime[0] - 2000;

        for (int i2 = 1; i2 < 6; i2++) {
            picDateTime010[i2] = picDateTime[i2];
            picDateTime020[i2] = picDateTime[i2];
            picDateTime025[i2] = picDateTime[i2];
            picDateTime030[i2] = picDateTime[i2];
            picDateTime040[i2] = picDateTime[i2];
            picDateTime050[i2] = picDateTime[i2];
            picDateTime060[i2] = picDateTime[i2];
            picDateTime070[i2] = picDateTime[i2];
            picDateTime075[i2] = picDateTime[i2];
            picDateTime080[i2] = picDateTime[i2];
            picDateTime090[i2] = picDateTime[i2];
        }

        picDateTime010[6] = 100;
        picDateTime020[6] = 200;
        picDateTime025[6] = 250;
        picDateTime030[6] = 300;
        picDateTime040[6] = 400;
        picDateTime050[6] = 500;
        picDateTime060[6] = 600;
        picDateTime070[6] = 700;
        picDateTime075[6] = 750;
        picDateTime080[6] = 800;
        picDateTime090[6] = 900;



//                java.sql.Date dateTime = getDateTime(picDateTime);
//                System.out.println("IN subloop: " + dateFormat.format(dateTime));
        timeStamp = new java.sql.Timestamp(dateTime.getTime());

//        java.sql.Timestamp timeStamp1_1 = new java.sql.Timestamp(dateTime.getTime());
//        java.sql.Timestamp timeStamp1_2 = new java.sql.Timestamp(dateTime.getTime());
//
//        java.sql.Timestamp timeStamp0_0 = new java.sql.Timestamp(dateTime.getTime());

        java.sql.Timestamp timeStamp1_1 = new java.sql.Timestamp(timeStamp.getTime());
        java.sql.Timestamp timeStamp1_2 = new java.sql.Timestamp(timeStamp.getTime());

        java.sql.Timestamp timeStamp0_0 = new java.sql.Timestamp(timeStamp.getTime());



//                if (!isFirstRow)
//                    System.out.println("\n\n\n DIFFERENCE IS: " + (timeStamp0_0.getTime() - previousTimeStamp.getTime()) + "\n \n ");

        // create NULL values in order to skip unnecessary lines on graphs
        long timeDifference = timeStamp0_0.getTime() - previousTimeStamp.getTime();
        if (!isFirstRow && (timeDifference > 5000L)) {
            int times;
            if (timeDifference > 20000L) times = 20; else times = 1;
            for (int j3 = times; j3 > 0; j3--) {
                java.sql.Timestamp newTimeStamp = new java.sql.Timestamp(timeStamp.getTime() - 1000L * j3 + 0 * j3 * 100L);

//                System.out.println("\n\n\n\n NEW TIME STAMP: " + newTimeStamp + "\nTIME STAMP: " + timeStamp + "\n\n\n\n");

                PreparedStatement pstm = newDBConn.prepareStatement("INSERT INTO APP.ALLDATA (TIMESTAMPCOL, SPEED, PRESSONE, PRESSTWO, " +
                        "DIG1, DIG2," +
                        "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
                        "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
                        "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                        " ?, ?, ?, ?, ?, ?, ?, ? )");

                pstm.setTimestamp(1, newTimeStamp);
                pstm.setNull(2, Types.DOUBLE);
                pstm.setNull(3, Types.INTEGER);
                pstm.setNull(4, Types.INTEGER);
                pstm.setNull(5, Types.BOOLEAN);
                pstm.setNull(6, Types.BOOLEAN);
                pstm.setNull(7, Types.BOOLEAN);
                pstm.setNull(8, Types.BOOLEAN);
                pstm.setNull(9, Types.BOOLEAN);
                pstm.setNull(10, Types.BOOLEAN);
                pstm.setNull(11, Types.BOOLEAN);
                pstm.setNull(12, Types.BOOLEAN);
                pstm.setNull(13, Types.BOOLEAN);
                pstm.setNull(14, Types.BOOLEAN);
                pstm.setNull(15, Types.BOOLEAN);
                pstm.setNull(16, Types.BOOLEAN);
                pstm.setNull(17, Types.BOOLEAN);
                pstm.setNull(18, Types.BOOLEAN);
                pstm.setNull(19, Types.BOOLEAN);
                pstm.setNull(20, Types.BOOLEAN);
                pstm.setNull(21, Types.BOOLEAN);
                pstm.setNull(22, Types.BOOLEAN);
                pstm.setNull(23, Types.BOOLEAN);
                pstm.setNull(24, Types.BOOLEAN);
                pstm.setNull(25, Types.BOOLEAN);
                pstm.setNull(26, Types.BOOLEAN);
                pstm.setNull(27, Types.BOOLEAN);
                pstm.setNull(28, Types.BOOLEAN);
                pstm.setNull(29, Types.BOOLEAN);
                pstm.setNull(30, Types.BOOLEAN);
                pstm.setNull(31, Types.BOOLEAN);
                pstm.setNull(32, Types.BOOLEAN);
                pstm.setNull(33, Types.BOOLEAN);
                pstm.setNull(34, Types.BOOLEAN);
                pstm.setNull(35, Types.BOOLEAN);
                pstm.setNull(36, Types.BOOLEAN);
                pstm.setNull(37, Types.BOOLEAN);
                pstm.setNull(38, Types.BOOLEAN);
                pstm.setNull(39, Types.BOOLEAN);
                pstm.setNull(40, Types.BOOLEAN);
                pstm.setNull(41, Types.BOOLEAN);

                pstm.executeUpdate();
                pstm.close();
            }
        }
        //END create NULL values in order to skip unnecessary lines on graphs


        java.sql.Date dateTime010 = getDateTime(picDateTime010);
//                System.out.println(dateFormat.format(dateTime2));
        java.sql.Timestamp timeStamp010 = new java.sql.Timestamp(dateTime010.getTime());

        java.sql.Date dateTime020 = getDateTime(picDateTime020);
//                System.out.println(dateFormat.format(dateTime3));
        java.sql.Timestamp timeStamp020 = new java.sql.Timestamp(dateTime020.getTime());

        java.sql.Date dateTime025 = getDateTime(picDateTime025);
//                System.out.println(dateFormat.format(dateTime4));
        java.sql.Timestamp timeStamp025 = new java.sql.Timestamp(dateTime025.getTime());

        java.sql.Date dateTime030 = getDateTime(picDateTime030);
//                System.out.println(dateFormat.format(dateTime5));
        java.sql.Timestamp timeStamp030 = new java.sql.Timestamp(dateTime030.getTime());

        java.sql.Date dateTime040 = getDateTime(picDateTime040);
//                System.out.println(dateFormat.format(dateTime6));
        java.sql.Timestamp timeStamp040 = new java.sql.Timestamp(dateTime040.getTime());

        java.sql.Date dateTime050 = getDateTime(picDateTime050);
//                System.out.println(dateFormat.format(dateTime7));
        java.sql.Timestamp timeStamp050 = new java.sql.Timestamp(dateTime050.getTime());
        java.sql.Timestamp timeStamp050_2 = new java.sql.Timestamp(dateTime050.getTime());

        java.sql.Date dateTime060 = getDateTime(picDateTime060);
//                System.out.println(dateFormat.format(dateTime8));
        java.sql.Timestamp timeStamp060 = new java.sql.Timestamp(dateTime060.getTime());

        java.sql.Date dateTime070 = getDateTime(picDateTime070);
//                System.out.println(dateFormat.format(dateTime9));
        java.sql.Timestamp timeStamp070 = new java.sql.Timestamp(dateTime070.getTime());

        java.sql.Date dateTime075 = getDateTime(picDateTime075);
//                System.out.println(dateFormat.format(dateTime10));
        java.sql.Timestamp timeStamp075 = new java.sql.Timestamp(dateTime075.getTime());

        java.sql.Date dateTime080 = getDateTime(picDateTime080);
//                System.out.println(dateFormat.format(dateTime11));
        java.sql.Timestamp timeStamp080 = new java.sql.Timestamp(dateTime080.getTime());

        java.sql.Date dateTime090 = getDateTime(picDateTime090);
//                System.out.println(dateFormat.format(dateTime12));
        java.sql.Timestamp timeStamp090 = new java.sql.Timestamp(dateTime090.getTime());

//        if (isFirstRow)
//            isFirstRow = false;

        int pres1;
        int pres2;
        int speed = 0;

        byte[] pressure1 = new byte[2];
        byte[] pressure2 = new byte[2];

        byte[] digitalSpeed0_0 = new byte[4];
        byte[] digitalSpeed0_25 = new byte[4];
        byte[] digitalSpeed0_5 = new byte[4];
        byte[] digitalSpeed0_75 = new byte[4];

        pressure1[0] = bytes[336];
        pressure1[1] = bytes[337];

        pressure2[0] = bytes[338];
        pressure2[1] = bytes[339];

        pres1 = mergeTwoBytes(pressure1);
        pres2 = mergeTwoBytes(pressure2);

        byte[] bytesForDigitalSet1 = new byte[5];
        byte[] bytesForDigitalSet2 = new byte[5];
        byte[] bytesForDigitalSet3 = new byte[5];
        byte[] bytesForDigitalSet4 = new byte[5];
        byte[] bytesForDigitalSet5 = new byte[5];
        byte[] bytesForDigitalSet6 = new byte[5];
        byte[] bytesForDigitalSet7 = new byte[5];
        byte[] bytesForDigitalSet8 = new byte[5];
        byte[] bytesForDigitalSet9 = new byte[5];
        byte[] bytesForDigitalSet10 = new byte[5];

//        System.out.println("pressure1 = " + pres1);
//        System.out.println("pressure2 = " + pres2);
//        System.out.println("speed1 = " + (mergeFourBytes(digitalSpeed1_1)));
//        System.out.println("speed2 = " + (mergeFourBytes(digitalSpeed2_1)));
//        System.out.println("speed3 = " + (mergeFourBytes(digitalSpeed3_1)));
//        System.out.println("speed4 = " + (mergeFourBytes(digitalSpeed4_1)));

        for (int j = 0; j < 5; j++) {
            //decipher data for speed
            digitalSpeed0_0[0] = bytes[16 + j*66];
            digitalSpeed0_0[1] = bytes[17 + j*66];
            digitalSpeed0_0[2] = bytes[18 + j*66];
            digitalSpeed0_0[3] = bytes[19 + j*66];

            digitalSpeed0_25[0] = bytes[35 + j*66];
            digitalSpeed0_25[1] = bytes[36 + j*66];
            digitalSpeed0_25[2] = bytes[37 + j*66];
            digitalSpeed0_25[3] = bytes[38 + j*66];

            digitalSpeed0_5[0] = bytes[49 + j*66];
            digitalSpeed0_5[1] = bytes[50 + j*66];
            digitalSpeed0_5[2] = bytes[51 + j*66];
            digitalSpeed0_5[3] = bytes[52 + j*66];

            digitalSpeed0_75[0] = bytes[68 + j*66];
            digitalSpeed0_75[1] = bytes[69 + j*66];
            digitalSpeed0_75[2] = bytes[70 + j*66];
            digitalSpeed0_75[3] = bytes[71 + j*66];

//            double speed0_0 =  (double)mergeFourBytes(digitalSpeed0_0);
//            double speed0_25 = (double)mergeFourBytes(digitalSpeed0_25);
//            double speed0_5 =  (double)mergeFourBytes(digitalSpeed0_5);
//            double speed0_75 = (double)mergeFourBytes(digitalSpeed0_75);


            double speed0_0 = (16000000.0 / (double)mergeFourBytes(digitalSpeed0_0));
            double speed0_25 = (16000000.0 / (double)mergeFourBytes(digitalSpeed0_25));
            double speed0_5 = (16000000.0 / (double)mergeFourBytes(digitalSpeed0_5));
            double speed0_75 = (16000000.0 / (double)mergeFourBytes(digitalSpeed0_75));

            speed0_0 = (speed0_0 < 0.01 ? 0.0 : speed0_0 * 0.18 * parentFrm.coeffSpeed);
            speed0_25 = (speed0_25 < 0.01 ? 0.0 : speed0_25 * 0.18 * parentFrm.coeffSpeed);
            speed0_5 = (speed0_5 < 0.01 ? 0.0 : speed0_5 * 0.18 * parentFrm.coeffSpeed);
            speed0_75 = (speed0_75 < 0.01 ? 0.0 : speed0_75 * 0.18 * parentFrm.coeffSpeed);

            //end decipher data for speed

            //decipher data for digital signals

            bytesForDigitalSet1[0] = bytes[6 + j*66];
            bytesForDigitalSet1[1] = bytes[7 + j*66];
            bytesForDigitalSet1[2] = bytes[8 + j*66];
            bytesForDigitalSet1[3] = bytes[9 + j*66];
            bytesForDigitalSet1[4] = bytes[10 + j*66];

            bytesForDigitalSet2[0] = bytes[11 + j*66];
            bytesForDigitalSet2[1] = bytes[12 + j*66];
            bytesForDigitalSet2[2] = bytes[13 + j*66];
            bytesForDigitalSet2[3] = bytes[14 + j*66];
            bytesForDigitalSet2[4] = bytes[15 + j*66];

            bytesForDigitalSet3[0] = bytes[20 + j*66];
            bytesForDigitalSet3[1] = bytes[21 + j*66];
            bytesForDigitalSet3[2] = bytes[22 + j*66];
            bytesForDigitalSet3[3] = bytes[23 + j*66];
            bytesForDigitalSet3[4] = bytes[24 + j*66];

            bytesForDigitalSet4[0] = bytes[25 + j*66];
            bytesForDigitalSet4[1] = bytes[26 + j*66];
            bytesForDigitalSet4[2] = bytes[27 + j*66];
            bytesForDigitalSet4[3] = bytes[28 + j*66];
            bytesForDigitalSet4[4] = bytes[29 + j*66];

            bytesForDigitalSet5[0] = bytes[30 + j*66];
            bytesForDigitalSet5[1] = bytes[31 + j*66];
            bytesForDigitalSet5[2] = bytes[32 + j*66];
            bytesForDigitalSet5[3] = bytes[33 + j*66];
            bytesForDigitalSet5[4] = bytes[34 + j*66];

            bytesForDigitalSet6[0] = bytes[39 + j*66];
            bytesForDigitalSet6[1] = bytes[40 + j*66];
            bytesForDigitalSet6[2] = bytes[41 + j*66];
            bytesForDigitalSet6[3] = bytes[42 + j*66];
            bytesForDigitalSet6[4] = bytes[43 + j*66];

            bytesForDigitalSet7[0] = bytes[44 + j*66];
            bytesForDigitalSet7[1] = bytes[45 + j*66];
            bytesForDigitalSet7[2] = bytes[46 + j*66];
            bytesForDigitalSet7[3] = bytes[47 + j*66];
            bytesForDigitalSet7[4] = bytes[48 + j*66];

            bytesForDigitalSet8[0] = bytes[53 + j*66];
            bytesForDigitalSet8[1] = bytes[54 + j*66];
            bytesForDigitalSet8[2] = bytes[55 + j*66];
            bytesForDigitalSet8[3] = bytes[56 + j*66];
            bytesForDigitalSet8[4] = bytes[57 + j*66];

            bytesForDigitalSet9[0] = bytes[58 + j*66];
            bytesForDigitalSet9[1] = bytes[59 + j*66];
            bytesForDigitalSet9[2] = bytes[60 + j*66];
            bytesForDigitalSet9[3] = bytes[61 + j*66];
            bytesForDigitalSet9[4] = bytes[62 + j*66];

            bytesForDigitalSet10[0] = bytes[63 + j*66];
            bytesForDigitalSet10[1] = bytes[64 + j*66];
            bytesForDigitalSet10[2] = bytes[65 + j*66];
            bytesForDigitalSet10[3] = bytes[66 + j*66];
            bytesForDigitalSet10[4] = bytes[67 + j*66];

            boolean[] digitalSet000 = appendAllDigits(bytesForDigitalSet1);
            boolean[] digitalSet010 = appendAllDigits(bytesForDigitalSet2);
            boolean[] digitalSet020 = appendAllDigits(bytesForDigitalSet3);
            boolean[] digitalSet030 = appendAllDigits(bytesForDigitalSet4);
            boolean[] digitalSet040 = appendAllDigits(bytesForDigitalSet5);
            boolean[] digitalSet050 = appendAllDigits(bytesForDigitalSet6);
            boolean[] digitalSet060 = appendAllDigits(bytesForDigitalSet7);
            boolean[] digitalSet070 = appendAllDigits(bytesForDigitalSet8);
            boolean[] digitalSet080 = appendAllDigits(bytesForDigitalSet9);
            boolean[] digitalSet090 = appendAllDigits(bytesForDigitalSet10);
            //end decipher data for digital signals


            //ADD DATA TO DATABASE
            if (j == 0)
                addParticles(newDBConn, timeStamp1_1, speed0_0, pres1, pres2, digitalSet000);
            else
                addParticles(newDBConn, timeStamp1_1, speed0_0, -55, -55, digitalSet000);
            addParticles(newDBConn, timeStamp010, -55.5, -55, -55, digitalSet010);
            addParticles(newDBConn, timeStamp020, -55.5, -55, -55, digitalSet020);
            addParticles(newDBConn, timeStamp025, speed0_25, -55, -55, digitalSet020);
            addParticles(newDBConn, timeStamp030, -55.5, -55, -55, digitalSet030);
            addParticles(newDBConn, timeStamp040, -55.5, -55, -55, digitalSet040);
            addParticles(newDBConn, timeStamp050, speed0_5, -55, -55, digitalSet050);
            addParticles(newDBConn, timeStamp060, -55.5, -55, -55, digitalSet060);
            addParticles(newDBConn, timeStamp070, -55.5, -55, -55, digitalSet070);
            addParticles(newDBConn, timeStamp075, speed0_75, -55, -55, digitalSet070);
            addParticles(newDBConn, timeStamp080, -55.5, -55, -55, digitalSet080);
            addParticles(newDBConn, timeStamp090, -55.5, -55, -55, digitalSet090);

            //END ADD DATA TO DATABASE

            timeStamp1_1.setTime(timeStamp1_1.getTime() + 1 * 1000L * 1);
            timeStamp1_2.setTime(timeStamp1_2.getTime() + 1 * 1000L * 1);

            timeStamp010.setTime(timeStamp010.getTime() + 1 * 1000L * 1);
            timeStamp020.setTime(timeStamp020.getTime() + 1 * 1000L * 1);

            timeStamp025.setTime(timeStamp025.getTime() + 1 * 1000L * 1);

            timeStamp030.setTime(timeStamp030.getTime() + 1 * 1000L * 1);
            timeStamp040.setTime(timeStamp040.getTime() + 1 * 1000L * 1);

            timeStamp050.setTime(timeStamp050.getTime() + 1 * 1000L * 1);

            timeStamp050_2.setTime(timeStamp050_2.getTime() + 1 * 1000L * 1);

            timeStamp060.setTime(timeStamp060.getTime() + 1 * 1000L * 1);
            timeStamp070.setTime(timeStamp070.getTime() + 1 * 1000L * 1);

            timeStamp075.setTime(timeStamp075.getTime() + 1 * 1000L * 1);

            timeStamp080.setTime(timeStamp080.getTime() + 1 * 1000L * 1);
            timeStamp090.setTime(timeStamp090.getTime() + 1 * 1000L * 1);
        }
    }

    private static java.sql.Timestamp addToDB(byte[] bytes, boolean isFirstRow, java.sql.Timestamp prevTimeStamp, FormTwo parentFrm, File file) {
        java.sql.Timestamp newTimestamp = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int picDateTime[] = new int[7];

        try {
            picDateTime[0] = bcd2dec(bytes[0]);
        } catch (Exception e) {

        }
        picDateTime[1] = bcd2dec(bytes[1]);
        picDateTime[2] = bcd2dec(bytes[2]);
        picDateTime[3] = bcd2dec(bytes[3]);
        picDateTime[4] = bcd2dec(bytes[4]);
        picDateTime[5] = bcd2dec(bytes[5]);
        picDateTime[6] = 0;                     // Milliseconds

        java.sql.Date dateTime = getDateTime(picDateTime);
//        System.out.println("IN first loop: " + dateFormat.format(dateTime));
        newTimestamp = new java.sql.Timestamp(dateTime.getTime());

        java.util.Date oldDate = new java.util.Date(prevTimeStamp.getTime());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        String newDateStr = dateFormat2.format(dateTime);
        String oldDateStr = dateFormat2.format(oldDate);

//        System.out.println("\nNew Date = " + newDateStr);
//        System.out.println("Old Date = " + oldDateStr);
        String strUrl = null;
        String tableName = "ALLDATA";
//        if (!newDateStr.equals(oldDateStr)){

//                conn = DriverManager.getConnection(strUrl);
//
//                PreparedStatement pstm = conn.prepareStatement("INSERT INTO APP.SETTINGSINFO (CURRENTTIMESTAMP, CARNUMBER, COEFPRESONE, COEFPRESTWO, COEFSPEED) " +
//                        "VALUES (?, ?, ?, ?, ?)");
//                pstm.setTimestamp(1, timestamp);
//                pstm.setString(2, parentFrame.carNo);
//                pstm.setDouble(3, coeffOneOld);
//                pstm.setDouble(4, coeffTwoOld);
//                pstm.setDouble(5, coeffSpeedOld);
//
//                pstm.executeUpdate();
//
//                System.out.println("SQL");


        String createTableStr = "CREATE TABLE APP.ALLDATA (timestampCol TIMESTAMP, SPEED FLOAT (52), PRESSONE INT, PRESSTWO INT," +
                "DIG1 BOOLEAN, DIG2 BOOLEAN, DIG3 BOOLEAN, DIG4 BOOLEAN, DIG5 BOOLEAN, " +
                "DIG6 BOOLEAN, DIG7 BOOLEAN, DIG8 BOOLEAN, DIG9 BOOLEAN, DIG10 BOOLEAN," +
                "DIG11 BOOLEAN, DIG12 BOOLEAN, DIG13 BOOLEAN, DIG14 BOOLEAN, DIG15 BOOLEAN, " +
                "DIG16 BOOLEAN, DIG17 BOOLEAN, DIG18 BOOLEAN, DIG19 BOOLEAN, DIG20 BOOLEAN," +
                "DIG21 BOOLEAN, DIG22 BOOLEAN, DIG23 BOOLEAN, DIG24 BOOLEAN, DIG25 BOOLEAN, " +
                "DIG26 BOOLEAN, DIG27 BOOLEAN, DIG28 BOOLEAN, DIG29 BOOLEAN, DIG30 BOOLEAN," +
                "DIG31 BOOLEAN, DIG32 BOOLEAN, DIG33 BOOLEAN, DIG34 BOOLEAN, DIG35 BOOLEAN, " +
                "DIG36 BOOLEAN, DIG37 BOOLEAN)";
        String createIndexStr = "CREATE UNIQUE INDEX allData_timestampCol_uindex ON APP.ALLDATA (timestampCol)";

        String createSettingsInfoTableStr = "CREATE TABLE APP.SETTINGSINFO" +
                " (CURRENTTIMESTAMP TIMESTAMP, CARNUMBER INT, COEFSPEED FLOAT (52)," +
                " PRESSONEPMAX FLOAT (52), PRESSONEUMIN FLOAT (52), PRESSONEUMAX FLOAT (52)," +
                " PRESSTWOPMAX FLOAT (52), PRESSTWOUMIN FLOAT (52), PRESSTWOUMAX FLOAT (52))";
        String createSettingsInfoIndexStr = "CREATE UNIQUE INDEX SETTINGSINFO_CURRENTTIMESTAMP_uindex ON APP.SETTINGSINFO (CURRENTTIMESTAMP)";


//        } else {
//            strUrl = "jdbc:derby:/mybackups/20301/" + newDateStr + ";create=true";
//        }

        try {


            try {
                getBytesFromFileServiceDataTwo(file, parentFrm);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            strUrl = "jdbc:derby:/mybackups/20301/" + parentFrm.carNo + "/" + newDateStr + ";create=true";
            strUrl = "jdbc:derby:/mybackups/" + parentFrm.carNo + "/" + newDateStr + ";create=true";

            Connection newDbConn = DriverManager.getConnection(strUrl);
            newDbConn.setAutoCommit(false);
            DatabaseMetaData dbmd = newDbConn.getMetaData();

            ResultSet rsOne = dbmd.getTables(null, "APP", "SETTINGSINFO", null);
            if (!rsOne.next()) {
                Statement stmtOne = newDbConn.createStatement();
                stmtOne.executeUpdate(createSettingsInfoTableStr);
                stmtOne.executeUpdate(createSettingsInfoIndexStr);
                stmtOne.close();

                java.sql.Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                PreparedStatement pstmOne = newDbConn.prepareStatement("INSERT INTO APP.SETTINGSINFO (CURRENTTIMESTAMP, CARNUMBER, COEFSPEED," +
                        " PRESSONEPMAX, PRESSONEUMIN, PRESSONEUMAX," +
                        " PRESSTWOPMAX, PRESSTWOUMIN, PRESSTWOUMAX)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmOne.setTimestamp(1, currentTimestamp);
                pstmOne.setString(2, parentFrm.carNo);
                pstmOne.setDouble(3, parentFrm.coeffSpeed);
                pstmOne.setDouble(4, parentFrm.coeffPresOnePmax);
                pstmOne.setDouble(5, parentFrm.coeffPresOneUmin);
                pstmOne.setDouble(6, parentFrm.coeffPresOneUmax);
                pstmOne.setDouble(7, parentFrm.coeffPresTwoPmax);
                pstmOne.setDouble(8, parentFrm.coeffPresTwoUmin);
                pstmOne.setDouble(9, parentFrm.coeffPresTwoUmax);

//                parentFrm.textReadCoefOnePmax.setText(String.valueOf(parentFrm.coeffPresOnePmax));
//                parentFrm.textReadCoefOneUmin.setText(String.valueOf(parentFrm.coeffPresOneUmin));
//                parentFrm.textReadCoefOneUmax.setText(String.valueOf(parentFrm.coeffPresOneUmax));
//
//                parentFrm.textReadCoefTwoPmax.setText(String.valueOf(parentFrm.coeffPresTwoPmax));
//                parentFrm.textReadCoefTwoUmin.setText(String.valueOf(parentFrm.coeffPresTwoUmin));
//                parentFrm.textReadCoefTwoUmax.setText(String.valueOf(parentFrm.coeffPresTwoUmax));

                parentFrm.textReadCoefOnePmax.setText(df.format(parentFrm.coeffPresOnePmax));
                parentFrm.textReadCoefOneUmin.setText(df.format(parentFrm.coeffPresOneUmin));
                parentFrm.textReadCoefOneUmax.setText(df.format(parentFrm.coeffPresOneUmax));

                parentFrm.textReadCoefTwoPmax.setText(df.format(parentFrm.coeffPresTwoPmax));
                parentFrm.textReadCoefTwoUmin.setText(df.format(parentFrm.coeffPresTwoUmin));
                parentFrm.textReadCoefTwoUmax.setText(df.format(parentFrm.coeffPresTwoUmax));


                pstmOne.executeUpdate();
            }



            ResultSet rs = dbmd.getTables(null, "APP", tableName.toUpperCase(), null);

            if (rs.next()) {
//                System.out.println("Table already exist");
            } else {
                Statement stmt = newDbConn.createStatement();
                stmt.executeUpdate(createTableStr);
                stmt.executeUpdate(createIndexStr);
                stmt.close();
            }

            addNewRow(bytes, newDbConn, picDateTime, isFirstRow, prevTimeStamp, dateTime, parentFrm);
            newDbConn.setAutoCommit(true);
            newDbConn.close();

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }

//            System.out.println("Combo date 0 = " + dateFormat2.format(comboDates));

//        Calendar c = Calendar.getInstance();
//        comboDate.addItem(dateFormat2.format(comboDates));

        return newTimestamp;

    }

    public byte[] getBytesFromFileTwo(File file, Frame frame, FormTwo parentFrm) throws IOException {
        percent = 0;
        parentFrm.progressBar2.setValue(percent);
        parentFrm.dpb.setValue(percent);

        boolean isFirstRow = true;

        long length = file.length();

        if (length > Integer.MAX_VALUE) throw new IOException("File is too large!");

        byte[] allBytes = new byte[12582912 - (36 * 4096)];
        int blSize = 12582912 - (36 * 4096); //12435456

        InputStream is = new BufferedInputStream(new FileInputStream(file));

        for (int i = 0; i < 36; i++) {
            is.skip(4096);
        }

        is.read(allBytes, 0, blSize);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int picDateTime[] = new int[7];

        try {
            picDateTime[0] = bcd2dec(allBytes[0]);
        } catch (Exception e) {
            is.reset();
        }
        picDateTime[1] = bcd2dec(allBytes[1]);
        picDateTime[2] = bcd2dec(allBytes[2]);
        picDateTime[3] = bcd2dec(allBytes[3]);
        picDateTime[4] = bcd2dec(allBytes[4]);
        picDateTime[5] = bcd2dec(allBytes[5]);
        picDateTime[6] = 0;                     // Milliseconds

        java.sql.Date dateTime = getDateTime(picDateTime);
//        System.out.println(dateFormat.format(dateTime));
        java.sql.Timestamp prevTimeStamp = new java.sql.Timestamp(dateTime.getTime());



        // Main loop - each block of 1000h at a time
        for (int j = 0; j < 3036; j++) {
            /// block size = 1000h = 4096 bytes
            int jj = j * 4096;
            for (int k = 0; k < 12; k++)
            {
                ///block size = 340 bytes contains 5 sec record
                int kk = k * 340;
                int blockSize = 340;
                byte[] bytes = new byte[blockSize];
                for (int i = 0; i < 340; i++) {
                        bytes[i] = allBytes[jj + kk + i];
                    percent = jj + kk + i;
                    parentFrm.progressBar2.setValue(percent);
                    parentFrm.dpb.setValue(percent);
//                        System.out.println("bytes[" + i + "] = " + bytes[i] + "  allBytes[" + ((jj + kk) + i) + "]= " + allBytes[(jj + kk) + i]);
                }
                if (bytes[0] == -1) break;
                else {

                    prevTimeStamp = addToDB(bytes, isFirstRow, prevTimeStamp, parentFrm, file);
//                    System.out.println(prevTimeStamp);
                    if (isFirstRow) isFirstRow = false;
                }

            }

        }
        //  Main loop - each block of 1000h at a time


//        JFileChooser fc = new JFileChooser();
//        fc.setCurrentDirectory(new java.io.File("/mybackups/")); // start at application current directory
//        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = fc.showOpenDialog(parentFrm);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            File yourFolder = fc.getSelectedFile();
//            System.out.println("File folder: " + yourFolder);
//        }


//        is.read(allBytes);

//
//        is.mark(0);
//
//        boolean startBytes = true;
//        boolean isFirstRow = true;
//        try {
//
//            java.sql.Date previousDateTime = null;
//            java.sql.Timestamp previousTimeStamp = null;
//
//            while (offset < length && (numRead = is.read(bytes, offset, blockSize)) >= 0) {
//                System.out.println("PREVIOUS DATA: " + previousTimeStamp);
//                percent += 340;
//                parentFrm.progressBar2.setValue(percent);
//                parentFrm.dpb.setValue(percent);
//                System.out.println("percent = " + percent);
//
//                if (startBytes) {
//                    is.reset();
//                    for (int i = 0; i < 36; i++) {
//                        is.skip(4096);
//                    }
//
//                    is.mark(0);
//                    is.read(bytes, offset, blockSize);
//                    startBytes = false;
//                }
//////////////////////////////////////////////////////////////////////////////////////////////
//
//                if (searchEndData(bytes)) break;
//
//                if (unusedData(bytes)) {
//                    is.reset();
//                    is.skip(16);
//                    is.mark(0);
//                    is.read(bytes, offset, blockSize);
////                    System.out.println("We've got unused data!");
//                }
//
//                System.out.println("Bytes0 = " + bytes[0]);
//
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//                int picDateTime[] = new int[7];
//                int picDateTime2[] = new int [7];
//                int picDateTime3[] = new int [7];
//                int picDateTime4[] = new int [7];
//                int picDateTime5[] = new int [7];
//                int picDateTime6[] = new int [7];
//                int picDateTime7[] = new int [7];
//                int picDateTime8[] = new int [7];
//                int picDateTime9[] = new int [7];
//                int picDateTime10[] = new int [7];
//                int picDateTime11[] = new int [7];
//                int picDateTime12[] = new int [7];
//
//                try {
//                    picDateTime[0] = bcd2dec(bytes[0]);
//                } catch (Exception e) {
//                    is.reset();
//                }
//                picDateTime[1] = bcd2dec(bytes[1]);
//                picDateTime[2] = bcd2dec(bytes[2]);
//                picDateTime[3] = bcd2dec(bytes[3]);
//                picDateTime[4] = bcd2dec(bytes[4]);
//                picDateTime[5] = bcd2dec(bytes[5]);
//                picDateTime[6] = 0;                     // Milliseconds
//
//                for (int i = 0; i < 6; i++) {
//                    picDateTime2[i] = picDateTime[i];
//                    picDateTime3[i] = picDateTime[i];
//                    picDateTime4[i] = picDateTime[i];
//                    picDateTime5[i] = picDateTime[i];
//                    picDateTime6[i] = picDateTime[i];
//                    picDateTime7[i] = picDateTime[i];
//                    picDateTime8[i] = picDateTime[i];
//                    picDateTime9[i] = picDateTime[i];
//                    picDateTime10[i] = picDateTime[i];
//                    picDateTime11[i] = picDateTime[i];
//                    picDateTime12[i] = picDateTime[i];
//                }
//                picDateTime2[6] = 100;
//                picDateTime3[6] = 200;
//                picDateTime4[6] = 250;
//                picDateTime5[6] = 300;
//                picDateTime6[6] = 400;
//                picDateTime7[6] = 500;
//                picDateTime8[6] = 600;
//                picDateTime9[6] = 700;
//                picDateTime10[6] = 750;
//                picDateTime11[6] = 800;
//                picDateTime12[6] = 900;
//
//                java.sql.Date dateTime = getDateTime(picDateTime);
//                System.out.println(dateFormat.format(dateTime));
//                timeStamp = new java.sql.Timestamp(dateTime.getTime());
//                java.sql.Timestamp timeStamp1_1 = new java.sql.Timestamp(dateTime.getTime());
//                java.sql.Timestamp timeStamp1_2 = new java.sql.Timestamp(dateTime.getTime());
//
//                java.sql.Timestamp timeStamp0_0 = new java.sql.Timestamp(dateTime.getTime());
//
//                if (!isFirstRow)
//                    System.out.println("\n\n\n DIFFERENCE IS: " + (timeStamp0_0.getTime() - previousTimeStamp.getTime()) + "\n \n ");
//
//
//                if (!isFirstRow && (timeStamp0_0.getTime() - previousTimeStamp.getTime() > 5000L)) {
//                    java.sql.Timestamp newTimeStamp = new java.sql.Timestamp(timeStamp.getTime() - 5000L);
//
//                    System.out.println("\n\n\n\n NEW TIME STAMP: " + newTimeStamp + "\n\n\n\n\n");
//
//                    String strUrl = "jdbc:derby:wagons";
//                    try {
//                        Connection conn0;
//                        conn0 = DriverManager.getConnection(strUrl);
//
//                        PreparedStatement pstm = conn0.prepareStatement("INSERT INTO APP.PRESSURE (TIMESTAMP, PRESSUREONE, PRESSURETWO) " +
//                                "VALUES (?, ?, ?)");
//
//                        pstm.setTimestamp(1, newTimeStamp);
//                        pstm.setNull(2, Types.INTEGER);
//                        pstm.setNull(3, Types.INTEGER);
//
//                        pstm.executeUpdate();
//                        conn0.close();
//                    } catch (Exception ex) {ex.printStackTrace();}
//
//                    try {
//                        Connection conn05;
//                        conn05 = DriverManager.getConnection(strUrl);
//
//                        PreparedStatement pstm = conn05.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
//                                "VALUES (?, ?)");
//
//                        pstm.setTimestamp(1, newTimeStamp);
//                        pstm.setNull(2, Types.DOUBLE);
//
//                        pstm.executeUpdate();
//                        conn05.close();
//                    } catch (Exception ex) {ex.printStackTrace();}
//
//                    try {
//                        Connection conn06;
//                        conn06 = DriverManager.getConnection(strUrl);
//
//                        PreparedStatement pstmDigits1 = conn06.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//
//                        pstmDigits1.setTimestamp(1, newTimeStamp);
//                        pstmDigits1.setNull(2, Types.BOOLEAN);
//                        pstmDigits1.setNull(3, Types.BOOLEAN);
//                        pstmDigits1.setNull(4, Types.BOOLEAN);
//                        pstmDigits1.setNull(5, Types.BOOLEAN);
//                        pstmDigits1.setNull(6, Types.BOOLEAN);
//                        pstmDigits1.setNull(7, Types.BOOLEAN);
//                        pstmDigits1.setNull(8, Types.BOOLEAN);
//                        pstmDigits1.setNull(9, Types.BOOLEAN);
//                        pstmDigits1.setNull(10, Types.BOOLEAN);
//                        pstmDigits1.setNull(11, Types.BOOLEAN);
//                        pstmDigits1.setNull(12, Types.BOOLEAN);
//                        pstmDigits1.setNull(13, Types.BOOLEAN);
//                        pstmDigits1.setNull(14, Types.BOOLEAN);
//                        pstmDigits1.setNull(15, Types.BOOLEAN);
//                        pstmDigits1.setNull(16, Types.BOOLEAN);
//                        pstmDigits1.setNull(17, Types.BOOLEAN);
//                        pstmDigits1.setNull(18, Types.BOOLEAN);
//                        pstmDigits1.setNull(19, Types.BOOLEAN);
//                        pstmDigits1.setNull(20, Types.BOOLEAN);
//                        pstmDigits1.setNull(21, Types.BOOLEAN);
//                        pstmDigits1.setNull(22, Types.BOOLEAN);
//                        pstmDigits1.setNull(23, Types.BOOLEAN);
//                        pstmDigits1.setNull(24, Types.BOOLEAN);
//                        pstmDigits1.setNull(25, Types.BOOLEAN);
//                        pstmDigits1.setNull(26, Types.BOOLEAN);
//                        pstmDigits1.setNull(27, Types.BOOLEAN);
//                        pstmDigits1.setNull(28, Types.BOOLEAN);
//                        pstmDigits1.setNull(29, Types.BOOLEAN);
//                        pstmDigits1.setNull(30, Types.BOOLEAN);
//                        pstmDigits1.setNull(31, Types.BOOLEAN);
//                        pstmDigits1.setNull(32, Types.BOOLEAN);
//                        pstmDigits1.setNull(33, Types.BOOLEAN);
//                        pstmDigits1.setNull(34, Types.BOOLEAN);
//                        pstmDigits1.setNull(35, Types.BOOLEAN);
//                        pstmDigits1.setNull(36, Types.BOOLEAN);
//                        pstmDigits1.setNull(37, Types.BOOLEAN);
//                        pstmDigits1.setNull(38, Types.BOOLEAN);
//                        pstmDigits1.executeUpdate();
//                        conn06.close();
//                    } catch (Exception ex) {ex.printStackTrace();}
//                }
//
//
//                java.sql.Date dateTime2 = getDateTime(picDateTime2);
//                System.out.println(dateFormat.format(dateTime2));
//                java.sql.Timestamp timeStamp2 = new java.sql.Timestamp(dateTime2.getTime());
//
//                java.sql.Date dateTime3 = getDateTime(picDateTime3);
//                System.out.println(dateFormat.format(dateTime3));
//                java.sql.Timestamp timeStamp3 = new java.sql.Timestamp(dateTime3.getTime());
//
//                java.sql.Date dateTime4 = getDateTime(picDateTime4);
//                System.out.println(dateFormat.format(dateTime4));
//                java.sql.Timestamp timeStamp4 = new java.sql.Timestamp(dateTime4.getTime());
//
//                java.sql.Date dateTime5 = getDateTime(picDateTime5);
//                System.out.println(dateFormat.format(dateTime5));
//                java.sql.Timestamp timeStamp5 = new java.sql.Timestamp(dateTime5.getTime());
//
//                java.sql.Date dateTime6 = getDateTime(picDateTime6);
//                System.out.println(dateFormat.format(dateTime6));
//                java.sql.Timestamp timeStamp6 = new java.sql.Timestamp(dateTime6.getTime());
//
//                java.sql.Date dateTime7 = getDateTime(picDateTime7);
//                System.out.println(dateFormat.format(dateTime7));
//                java.sql.Timestamp timeStamp7 = new java.sql.Timestamp(dateTime7.getTime());
//                java.sql.Timestamp timeStamp7_2 = new java.sql.Timestamp(dateTime7.getTime());
//
//                java.sql.Date dateTime8 = getDateTime(picDateTime8);
//                System.out.println(dateFormat.format(dateTime8));
//                java.sql.Timestamp timeStamp8 = new java.sql.Timestamp(dateTime8.getTime());
//
//                java.sql.Date dateTime9 = getDateTime(picDateTime9);
//                System.out.println(dateFormat.format(dateTime9));
//                java.sql.Timestamp timeStamp9 = new java.sql.Timestamp(dateTime9.getTime());
//
//                java.sql.Date dateTime10 = getDateTime(picDateTime10);
//                System.out.println(dateFormat.format(dateTime10));
//                java.sql.Timestamp timeStamp10 = new java.sql.Timestamp(dateTime10.getTime());
//
//                java.sql.Date dateTime11 = getDateTime(picDateTime11);
//                System.out.println(dateFormat.format(dateTime11));
//                java.sql.Timestamp timeStamp11 = new java.sql.Timestamp(dateTime11.getTime());
//
//                java.sql.Date dateTime12 = getDateTime(picDateTime12);
//                System.out.println(dateFormat.format(dateTime12));
//                java.sql.Timestamp timeStamp12 = new java.sql.Timestamp(dateTime12.getTime());
//
//                if (isFirstRow)
//                    isFirstRow = false;
//
//                previousTimeStamp = timeStamp0_0;
//                System.out.println("Previous data: " + previousTimeStamp);
//
//                int pres1;
//                int pres2;
//                int speed = 0;
//
//                byte[] pressure1 = new byte[2];
//                byte[] pressure2 = new byte[2];
//
//                byte[] digitalSpeed1_1 = new byte[4];
//                byte[] digitalSpeed2_1 = new byte[4];
//                byte[] digitalSpeed3_1 = new byte[4];
//                byte[] digitalSpeed4_1 = new byte[4];
//
//                pressure1[0] = bytes[336];
//                pressure1[1] = bytes[337];
//
//                pressure2[0] = bytes[338];
//                pressure2[1] = bytes[339];
//
//                pres1 = mergeTwoBytes(pressure1);
//                pres2 = mergeTwoBytes(pressure2);
//
//                byte[] bytesForDigitalSet1 = new byte[5];
//                byte[] bytesForDigitalSet2 = new byte[5];
//                byte[] bytesForDigitalSet3 = new byte[5];
//                byte[] bytesForDigitalSet4 = new byte[5];
//                byte[] bytesForDigitalSet5 = new byte[5];
//                byte[] bytesForDigitalSet6 = new byte[5];
//                byte[] bytesForDigitalSet7 = new byte[5];
//                byte[] bytesForDigitalSet8 = new byte[5];
//                byte[] bytesForDigitalSet9 = new byte[5];
//                byte[] bytesForDigitalSet10 = new byte[5];
//
//                System.out.println("pressure1 = " + pres1);
//                System.out.println("pressure2 = " + pres2);
//                System.out.println("speed1 = " + (mergeFourBytes(digitalSpeed1_1)));
//                System.out.println("speed2 = " + (mergeFourBytes(digitalSpeed2_1)));
//                System.out.println("speed3 = " + (mergeFourBytes(digitalSpeed3_1)));
//                System.out.println("speed4 = " + (mergeFourBytes(digitalSpeed4_1)));
//
//
//                //try to update database
//                String strUrl = "jdbc:derby:wagons";
//                try {
//                    Connection conn2;
//                    conn2 = DriverManager.getConnection(strUrl);
//
//                    PreparedStatement pstm = conn2.prepareStatement("INSERT INTO APP.PRESSURE (TIMESTAMP, PRESSUREONE, PRESSURETWO) " +
//                            "VALUES (?, ?, ?)");
//                    pstm.setTimestamp(1, timeStamp);
//
//                    pstm.setInt(2, pres1);
//                    pstm.setInt(3, pres2);
//
//                    pstm.executeUpdate();
//                    System.out.println("SQL");
//
//                    conn2.close();
//                } catch (Exception ex) {ex.printStackTrace();}
//
//                try{
//                    Connection conn3;
//                    conn3 = DriverManager.getConnection(strUrl);
//
//                    for (int j = 0; j < 5; j++) {
//
//                        digitalSpeed1_1[0] = bytes[16 + j*66];
//                        digitalSpeed1_1[1] = bytes[17 + j*66];
//                        digitalSpeed1_1[2] = bytes[18 + j*66];
//                        digitalSpeed1_1[3] = bytes[19 + j*66];
//
//                        digitalSpeed2_1[0] = bytes[35 + j*66];
//                        digitalSpeed2_1[1] = bytes[36 + j*66];
//                        digitalSpeed2_1[2] = bytes[37 + j*66];
//                        digitalSpeed2_1[3] = bytes[38 + j*66];
//
//                        digitalSpeed3_1[0] = bytes[49 + j*66];
//                        digitalSpeed3_1[1] = bytes[50 + j*66];
//                        digitalSpeed3_1[2] = bytes[51 + j*66];
//                        digitalSpeed3_1[3] = bytes[52 + j*66];
//
//                        digitalSpeed4_1[0] = bytes[68 + j*66];
//                        digitalSpeed4_1[1] = bytes[69 + j*66];
//                        digitalSpeed4_1[2] = bytes[70 + j*66];
//                        digitalSpeed4_1[3] = bytes[71 + j*66];
//
//                        double speed1 = (16000000.0 / (double)mergeFourBytes(digitalSpeed1_1));
//                        double speed2 = (16000000.0 / (double)mergeFourBytes(digitalSpeed2_1));
//                        double speed3 = (16000000.0 / (double)mergeFourBytes(digitalSpeed3_1));
//                        double speed4 = (16000000.0 / (double)mergeFourBytes(digitalSpeed4_1));
//
//                        speed1 = (speed1 < 0.01 ? 0.0 : speed1 * parentFrm.coeffSpeed);
//                        speed2 = (speed2 < 0.01 ? 0.0 : speed2 * parentFrm.coeffSpeed);
//                        speed3 = (speed3 < 0.01 ? 0.0 : speed3 * parentFrm.coeffSpeed);
//                        speed4 = (speed4 < 0.01 ? 0.0 : speed4 * parentFrm.coeffSpeed);
//
//                        System.out.println("Speed 1 = " + String.format("%.12f", speed1));
//                        System.out.println("Speed 2 = " + String.format("%.12f", speed2));
//                        System.out.println("Speed 3 = " + String.format("%.12f", speed3));
//                        System.out.println("Speed 4 = " + String.format("%.12f", speed4));
//
//                        PreparedStatement pstmSpeed1 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
//                                "VALUES (?, ?)");
//                        pstmSpeed1.setTimestamp(1, timeStamp1_1);
//                        pstmSpeed1.setDouble(2, speed1);
//
//                        pstmSpeed1.executeUpdate();
//
//                        PreparedStatement pstmSpeed2 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
//                                "VALUES (?, ?)");
//                        pstmSpeed2.setTimestamp(1, timeStamp4);
//                        pstmSpeed2.setDouble(2, speed2);
//
//                        pstmSpeed2.executeUpdate();
//
//                        PreparedStatement pstmSpeed3 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
//                                "VALUES (?, ?)");
//                        pstmSpeed3.setTimestamp(1, timeStamp7);
//                        pstmSpeed3.setDouble(2, speed3);
//
//                        pstmSpeed3.executeUpdate();
//
//                        PreparedStatement pstmSpeed4 = conn3.prepareStatement("INSERT INTO APP.SPEED (TIMESTAMP, SPEED) " +
//                                "VALUES (?, ?)");
//                        pstmSpeed4.setTimestamp(1, timeStamp10);
//                        pstmSpeed4.setDouble(2, speed4);
//
//                        pstmSpeed4.executeUpdate();
//                        System.out.println("Speed SQL");
//
//                        timeStamp1_1.setTime(timeStamp1_1.getTime() + 1 * 1000L * 1);
//                        timeStamp4.setTime(timeStamp4.getTime() + 1 * 1000L * 1);
//                        timeStamp7.setTime(timeStamp7.getTime() + 1 * 1000L * 1);
//                        timeStamp10.setTime(timeStamp10.getTime() + 1 * 1000L * 1);
//
//                    }
//                    conn3.close();
//                } catch (Exception ex) {ex.printStackTrace();}
//
//
//                try {
//                    Connection conn4;
//                    conn4 = DriverManager.getConnection(strUrl);
//                    System.out.println("Connection for Digits start...");
//
//                    for (int j = 0; j < 5; j++) {
//
//                        System.out.println("Connection for Digits continue...");
//
//                        bytesForDigitalSet1[0] = bytes[6 + j*66];
//                        bytesForDigitalSet1[1] = bytes[7 + j*66];
//                        bytesForDigitalSet1[2] = bytes[8 + j*66];
//                        bytesForDigitalSet1[3] = bytes[9 + j*66];
//                        bytesForDigitalSet1[4] = bytes[10 + j*66];
//
//                        bytesForDigitalSet2[0] = bytes[11 + j*66];
//                        bytesForDigitalSet2[1] = bytes[12 + j*66];
//                        bytesForDigitalSet2[2] = bytes[13 + j*66];
//                        bytesForDigitalSet2[3] = bytes[14 + j*66];
//                        bytesForDigitalSet2[4] = bytes[15 + j*66];
//
//                        bytesForDigitalSet3[0] = bytes[20 + j*66];
//                        bytesForDigitalSet3[1] = bytes[21 + j*66];
//                        bytesForDigitalSet3[2] = bytes[22 + j*66];
//                        bytesForDigitalSet3[3] = bytes[23 + j*66];
//                        bytesForDigitalSet3[4] = bytes[24 + j*66];
//
//                        bytesForDigitalSet4[0] = bytes[25 + j*66];
//                        bytesForDigitalSet4[1] = bytes[26 + j*66];
//                        bytesForDigitalSet4[2] = bytes[27 + j*66];
//                        bytesForDigitalSet4[3] = bytes[28 + j*66];
//                        bytesForDigitalSet4[4] = bytes[29 + j*66];
//
//                        bytesForDigitalSet5[0] = bytes[30 + j*66];
//                        bytesForDigitalSet5[1] = bytes[31 + j*66];
//                        bytesForDigitalSet5[2] = bytes[32 + j*66];
//                        bytesForDigitalSet5[3] = bytes[33 + j*66];
//                        bytesForDigitalSet5[4] = bytes[34 + j*66];
//
//                        bytesForDigitalSet6[0] = bytes[39 + j*66];
//                        bytesForDigitalSet6[1] = bytes[40 + j*66];
//                        bytesForDigitalSet6[2] = bytes[41 + j*66];
//                        bytesForDigitalSet6[3] = bytes[42 + j*66];
//                        bytesForDigitalSet6[4] = bytes[43 + j*66];
//
//                        bytesForDigitalSet7[0] = bytes[44 + j*66];
//                        bytesForDigitalSet7[1] = bytes[45 + j*66];
//                        bytesForDigitalSet7[2] = bytes[46 + j*66];
//                        bytesForDigitalSet7[3] = bytes[47 + j*66];
//                        bytesForDigitalSet7[4] = bytes[48 + j*66];
//
//                        bytesForDigitalSet8[0] = bytes[53 + j*66];
//                        bytesForDigitalSet8[1] = bytes[54 + j*66];
//                        bytesForDigitalSet8[2] = bytes[55 + j*66];
//                        bytesForDigitalSet8[3] = bytes[56 + j*66];
//                        bytesForDigitalSet8[4] = bytes[57 + j*66];
//
//                        bytesForDigitalSet9[0] = bytes[58 + j*66];
//                        bytesForDigitalSet9[1] = bytes[59 + j*66];
//                        bytesForDigitalSet9[2] = bytes[60 + j*66];
//                        bytesForDigitalSet9[3] = bytes[61 + j*66];
//                        bytesForDigitalSet9[4] = bytes[62 + j*66];
//
//                        bytesForDigitalSet10[0] = bytes[63 + j*66];
//                        bytesForDigitalSet10[1] = bytes[64 + j*66];
//                        bytesForDigitalSet10[2] = bytes[65 + j*66];
//                        bytesForDigitalSet10[3] = bytes[66 + j*66];
//                        bytesForDigitalSet10[4] = bytes[67 + j*66];
//
//                        boolean[] digitalSet1 = appendAllDigits(bytesForDigitalSet1);
//                        boolean[] digitalSet2 = appendAllDigits(bytesForDigitalSet2);
//                        boolean[] digitalSet3 = appendAllDigits(bytesForDigitalSet3);
//                        boolean[] digitalSet4 = appendAllDigits(bytesForDigitalSet4);
//                        boolean[] digitalSet5 = appendAllDigits(bytesForDigitalSet5);
//                        boolean[] digitalSet6 = appendAllDigits(bytesForDigitalSet6);
//                        boolean[] digitalSet7 = appendAllDigits(bytesForDigitalSet7);
//                        boolean[] digitalSet8 = appendAllDigits(bytesForDigitalSet8);
//                        boolean[] digitalSet9 = appendAllDigits(bytesForDigitalSet9);
//                        boolean[] digitalSet10 = appendAllDigits(bytesForDigitalSet10);
//
//
//// Insert FIRST appearance of digits
//                        PreparedStatement pstmDigits1 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits1.setTimestamp(1, timeStamp1_2);
//                        pstmDigits1.setBoolean(2, digitalSet1[7]);
//                        pstmDigits1.setBoolean(3, digitalSet1[6]);
//                        pstmDigits1.setBoolean(4, digitalSet1[5]);
//                        pstmDigits1.setBoolean(5, digitalSet1[4]);
//                        pstmDigits1.setBoolean(6, digitalSet1[3]);
//                        pstmDigits1.setBoolean(7, digitalSet1[2]);
//                        pstmDigits1.setBoolean(8, digitalSet1[1]);
//                        pstmDigits1.setBoolean(9, digitalSet1[32]);
//                        pstmDigits1.setBoolean(10, digitalSet1[33]);
//                        pstmDigits1.setBoolean(11, digitalSet1[34]);
//                        pstmDigits1.setBoolean(12, digitalSet1[21]);
//                        pstmDigits1.setBoolean(13, digitalSet1[20]);
//                        pstmDigits1.setBoolean(14, digitalSet1[19]);
//                        pstmDigits1.setBoolean(15, digitalSet1[18]);
//                        pstmDigits1.setBoolean(16, digitalSet1[17]);
//                        pstmDigits1.setBoolean(17, digitalSet1[31]);
//                        pstmDigits1.setBoolean(18, digitalSet1[30]);
//                        pstmDigits1.setBoolean(19, digitalSet1[29]);
//                        pstmDigits1.setBoolean(20, digitalSet1[28]);
//                        pstmDigits1.setBoolean(21, digitalSet1[16]);
//                        pstmDigits1.setBoolean(22, digitalSet1[13]);
//                        pstmDigits1.setBoolean(23, digitalSet1[12]);
//                        pstmDigits1.setBoolean(24, digitalSet1[11]);
//                        pstmDigits1.setBoolean(25, digitalSet1[10]);
//                        pstmDigits1.setBoolean(26, digitalSet1[23]);
//                        pstmDigits1.setBoolean(27, digitalSet1[9]);
//                        pstmDigits1.setBoolean(28, digitalSet1[8]);
//                        pstmDigits1.setBoolean(29, digitalSet1[0]);
//                        pstmDigits1.setBoolean(30, digitalSet1[36]);
//                        pstmDigits1.setBoolean(31, digitalSet1[35]);
//                        pstmDigits1.setBoolean(32, digitalSet1[27]);
//                        pstmDigits1.setBoolean(33, digitalSet1[26]);
//                        pstmDigits1.setBoolean(34, digitalSet1[25]);
//                        pstmDigits1.setBoolean(35, digitalSet1[24]);
//                        pstmDigits1.setBoolean(36, digitalSet1[15]);
//                        pstmDigits1.setBoolean(37, digitalSet1[14]);
//                        pstmDigits1.setBoolean(38, digitalSet1[22]);
//
//                        pstmDigits1.executeUpdate();
//
//// Insert SECOND appearance of digits
//                        PreparedStatement pstmDigits2 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits2.setTimestamp(1, timeStamp2);
//                        pstmDigits2.setBoolean(2, digitalSet2[7]);
//                        pstmDigits2.setBoolean(3, digitalSet2[6]);
//                        pstmDigits2.setBoolean(4, digitalSet2[5]);
//                        pstmDigits2.setBoolean(5, digitalSet2[4]);
//                        pstmDigits2.setBoolean(6, digitalSet2[3]);
//                        pstmDigits2.setBoolean(7, digitalSet2[2]);
//                        pstmDigits2.setBoolean(8, digitalSet2[1]);
//                        pstmDigits2.setBoolean(9, digitalSet2[32]);
//                        pstmDigits2.setBoolean(10, digitalSet2[33]);
//                        pstmDigits2.setBoolean(11, digitalSet2[34]);
//                        pstmDigits2.setBoolean(12, digitalSet2[21]);
//                        pstmDigits2.setBoolean(13, digitalSet2[20]);
//                        pstmDigits2.setBoolean(14, digitalSet2[19]);
//                        pstmDigits2.setBoolean(15, digitalSet2[18]);
//                        pstmDigits2.setBoolean(16, digitalSet2[17]);
//                        pstmDigits2.setBoolean(17, digitalSet2[31]);
//                        pstmDigits2.setBoolean(18, digitalSet2[30]);
//                        pstmDigits2.setBoolean(19, digitalSet2[29]);
//                        pstmDigits2.setBoolean(20, digitalSet2[28]);
//                        pstmDigits2.setBoolean(21, digitalSet2[16]);
//                        pstmDigits2.setBoolean(22, digitalSet2[13]);
//                        pstmDigits2.setBoolean(23, digitalSet2[12]);
//                        pstmDigits2.setBoolean(24, digitalSet2[11]);
//                        pstmDigits2.setBoolean(25, digitalSet2[10]);
//                        pstmDigits2.setBoolean(26, digitalSet2[23]);
//                        pstmDigits2.setBoolean(27, digitalSet2[9]);
//                        pstmDigits2.setBoolean(28, digitalSet2[8]);
//                        pstmDigits2.setBoolean(29, digitalSet2[0]);
//                        pstmDigits2.setBoolean(30, digitalSet2[36]);
//                        pstmDigits2.setBoolean(31, digitalSet2[35]);
//                        pstmDigits2.setBoolean(32, digitalSet2[27]);
//                        pstmDigits2.setBoolean(33, digitalSet2[26]);
//                        pstmDigits2.setBoolean(34, digitalSet2[25]);
//                        pstmDigits2.setBoolean(35, digitalSet2[24]);
//                        pstmDigits2.setBoolean(36, digitalSet2[15]);
//                        pstmDigits2.setBoolean(37, digitalSet2[14]);
//                        pstmDigits2.setBoolean(38, digitalSet2[22]);
//
//                        pstmDigits2.executeUpdate();
//
//// Insert THIRD appearance of digits
//                        PreparedStatement pstmDigits3 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits3.setTimestamp(1, timeStamp3);
//                        pstmDigits3.setBoolean(2, digitalSet3[7]);
//                        pstmDigits3.setBoolean(3, digitalSet3[6]);
//                        pstmDigits3.setBoolean(4, digitalSet3[5]);
//                        pstmDigits3.setBoolean(5, digitalSet3[4]);
//                        pstmDigits3.setBoolean(6, digitalSet3[3]);
//                        pstmDigits3.setBoolean(7, digitalSet3[2]);
//                        pstmDigits3.setBoolean(8, digitalSet3[1]);
//                        pstmDigits3.setBoolean(9, digitalSet3[32]);
//                        pstmDigits3.setBoolean(10, digitalSet3[33]);
//                        pstmDigits3.setBoolean(11, digitalSet3[34]);
//                        pstmDigits3.setBoolean(12, digitalSet3[21]);
//                        pstmDigits3.setBoolean(13, digitalSet3[20]);
//                        pstmDigits3.setBoolean(14, digitalSet3[19]);
//                        pstmDigits3.setBoolean(15, digitalSet3[18]);
//                        pstmDigits3.setBoolean(16, digitalSet3[17]);
//                        pstmDigits3.setBoolean(17, digitalSet3[31]);
//                        pstmDigits3.setBoolean(18, digitalSet3[30]);
//                        pstmDigits3.setBoolean(19, digitalSet3[29]);
//                        pstmDigits3.setBoolean(20, digitalSet3[28]);
//                        pstmDigits3.setBoolean(21, digitalSet3[16]);
//                        pstmDigits3.setBoolean(22, digitalSet3[13]);
//                        pstmDigits3.setBoolean(23, digitalSet3[12]);
//                        pstmDigits3.setBoolean(24, digitalSet3[11]);
//                        pstmDigits3.setBoolean(25, digitalSet3[10]);
//                        pstmDigits3.setBoolean(26, digitalSet3[23]);
//                        pstmDigits3.setBoolean(27, digitalSet3[9]);
//                        pstmDigits3.setBoolean(28, digitalSet3[8]);
//                        pstmDigits3.setBoolean(29, digitalSet3[0]);
//                        pstmDigits3.setBoolean(30, digitalSet3[36]);
//                        pstmDigits3.setBoolean(31, digitalSet3[35]);
//                        pstmDigits3.setBoolean(32, digitalSet3[27]);
//                        pstmDigits3.setBoolean(33, digitalSet3[26]);
//                        pstmDigits3.setBoolean(34, digitalSet3[25]);
//                        pstmDigits3.setBoolean(35, digitalSet3[24]);
//                        pstmDigits3.setBoolean(36, digitalSet3[15]);
//                        pstmDigits3.setBoolean(37, digitalSet3[14]);
//                        pstmDigits3.setBoolean(38, digitalSet3[22]);
//
//                        pstmDigits3.executeUpdate();
//
//// Insert FOURTH appearance of digits
//                        PreparedStatement pstmDigits4 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits4.setTimestamp(1, timeStamp5);
//                        pstmDigits4.setBoolean(2, digitalSet4[7]);
//                        pstmDigits4.setBoolean(3, digitalSet4[6]);
//                        pstmDigits4.setBoolean(4, digitalSet4[5]);
//                        pstmDigits4.setBoolean(5, digitalSet4[4]);
//                        pstmDigits4.setBoolean(6, digitalSet4[3]);
//                        pstmDigits4.setBoolean(7, digitalSet4[2]);
//                        pstmDigits4.setBoolean(8, digitalSet4[1]);
//                        pstmDigits4.setBoolean(9, digitalSet4[32]);
//                        pstmDigits4.setBoolean(10, digitalSet4[33]);
//                        pstmDigits4.setBoolean(11, digitalSet4[34]);
//                        pstmDigits4.setBoolean(12, digitalSet4[21]);
//                        pstmDigits4.setBoolean(13, digitalSet4[20]);
//                        pstmDigits4.setBoolean(14, digitalSet4[19]);
//                        pstmDigits4.setBoolean(15, digitalSet4[18]);
//                        pstmDigits4.setBoolean(16, digitalSet4[17]);
//                        pstmDigits4.setBoolean(17, digitalSet4[31]);
//                        pstmDigits4.setBoolean(18, digitalSet4[30]);
//                        pstmDigits4.setBoolean(19, digitalSet4[29]);
//                        pstmDigits4.setBoolean(20, digitalSet4[28]);
//                        pstmDigits4.setBoolean(21, digitalSet4[16]);
//                        pstmDigits4.setBoolean(22, digitalSet4[13]);
//                        pstmDigits4.setBoolean(23, digitalSet4[12]);
//                        pstmDigits4.setBoolean(24, digitalSet4[11]);
//                        pstmDigits4.setBoolean(25, digitalSet4[10]);
//                        pstmDigits4.setBoolean(26, digitalSet4[23]);
//                        pstmDigits4.setBoolean(27, digitalSet4[9]);
//                        pstmDigits4.setBoolean(28, digitalSet4[8]);
//                        pstmDigits4.setBoolean(29, digitalSet4[0]);
//                        pstmDigits4.setBoolean(30, digitalSet4[36]);
//                        pstmDigits4.setBoolean(31, digitalSet4[35]);
//                        pstmDigits4.setBoolean(32, digitalSet4[27]);
//                        pstmDigits4.setBoolean(33, digitalSet4[26]);
//                        pstmDigits4.setBoolean(34, digitalSet4[25]);
//                        pstmDigits4.setBoolean(35, digitalSet4[24]);
//                        pstmDigits4.setBoolean(36, digitalSet4[15]);
//                        pstmDigits4.setBoolean(37, digitalSet4[14]);
//                        pstmDigits4.setBoolean(38, digitalSet4[22]);
//                        pstmDigits4.executeUpdate();
//
//// Insert FIFTH appearance of digits
//                        PreparedStatement pstmDigits5 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits5.setTimestamp(1, timeStamp6);
//                        pstmDigits5.setBoolean(2, digitalSet5[7]);
//                        pstmDigits5.setBoolean(3, digitalSet5[6]);
//                        pstmDigits5.setBoolean(4, digitalSet5[5]);
//                        pstmDigits5.setBoolean(5, digitalSet5[4]);
//                        pstmDigits5.setBoolean(6, digitalSet5[3]);
//                        pstmDigits5.setBoolean(7, digitalSet5[2]);
//                        pstmDigits5.setBoolean(8, digitalSet5[1]);
//                        pstmDigits5.setBoolean(9, digitalSet5[32]);
//                        pstmDigits5.setBoolean(10, digitalSet5[33]);
//                        pstmDigits5.setBoolean(11, digitalSet5[34]);
//                        pstmDigits5.setBoolean(12, digitalSet5[21]);
//                        pstmDigits5.setBoolean(13, digitalSet5[20]);
//                        pstmDigits5.setBoolean(14, digitalSet5[19]);
//                        pstmDigits5.setBoolean(15, digitalSet5[18]);
//                        pstmDigits5.setBoolean(16, digitalSet5[17]);
//                        pstmDigits5.setBoolean(17, digitalSet5[31]);
//                        pstmDigits5.setBoolean(18, digitalSet5[30]);
//                        pstmDigits5.setBoolean(19, digitalSet5[29]);
//                        pstmDigits5.setBoolean(20, digitalSet5[28]);
//                        pstmDigits5.setBoolean(21, digitalSet5[16]);
//                        pstmDigits5.setBoolean(22, digitalSet5[13]);
//                        pstmDigits5.setBoolean(23, digitalSet5[12]);
//                        pstmDigits5.setBoolean(24, digitalSet5[11]);
//                        pstmDigits5.setBoolean(25, digitalSet5[10]);
//                        pstmDigits5.setBoolean(26, digitalSet5[23]);
//                        pstmDigits5.setBoolean(27, digitalSet5[9]);
//                        pstmDigits5.setBoolean(28, digitalSet5[8]);
//                        pstmDigits5.setBoolean(29, digitalSet5[0]);
//                        pstmDigits5.setBoolean(30, digitalSet5[36]);
//                        pstmDigits5.setBoolean(31, digitalSet5[35]);
//                        pstmDigits5.setBoolean(32, digitalSet5[27]);
//                        pstmDigits5.setBoolean(33, digitalSet5[26]);
//                        pstmDigits5.setBoolean(34, digitalSet5[25]);
//                        pstmDigits5.setBoolean(35, digitalSet5[24]);
//                        pstmDigits5.setBoolean(36, digitalSet5[15]);
//                        pstmDigits5.setBoolean(37, digitalSet5[14]);
//                        pstmDigits5.setBoolean(38, digitalSet5[22]);
//
//                        pstmDigits5.executeUpdate();
//
//// Insert SIXTH appearance of digits
//                        PreparedStatement pstmDigits6 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits6.setTimestamp(1, timeStamp7_2);
//                        pstmDigits6.setBoolean(2, digitalSet6[7]);
//                        pstmDigits6.setBoolean(3, digitalSet6[6]);
//                        pstmDigits6.setBoolean(4, digitalSet6[5]);
//                        pstmDigits6.setBoolean(5, digitalSet6[4]);
//                        pstmDigits6.setBoolean(6, digitalSet6[3]);
//                        pstmDigits6.setBoolean(7, digitalSet6[2]);
//                        pstmDigits6.setBoolean(8, digitalSet6[1]);
//                        pstmDigits6.setBoolean(9, digitalSet6[32]);
//                        pstmDigits6.setBoolean(10, digitalSet6[33]);
//                        pstmDigits6.setBoolean(11, digitalSet6[34]);
//                        pstmDigits6.setBoolean(12, digitalSet6[21]);
//                        pstmDigits6.setBoolean(13, digitalSet6[20]);
//                        pstmDigits6.setBoolean(14, digitalSet6[19]);
//                        pstmDigits6.setBoolean(15, digitalSet6[18]);
//                        pstmDigits6.setBoolean(16, digitalSet6[17]);
//                        pstmDigits6.setBoolean(17, digitalSet6[31]);
//                        pstmDigits6.setBoolean(18, digitalSet6[30]);
//                        pstmDigits6.setBoolean(19, digitalSet6[29]);
//                        pstmDigits6.setBoolean(20, digitalSet6[28]);
//                        pstmDigits6.setBoolean(21, digitalSet6[16]);
//                        pstmDigits6.setBoolean(22, digitalSet6[13]);
//                        pstmDigits6.setBoolean(23, digitalSet6[12]);
//                        pstmDigits6.setBoolean(24, digitalSet6[11]);
//                        pstmDigits6.setBoolean(25, digitalSet6[10]);
//                        pstmDigits6.setBoolean(26, digitalSet6[23]);
//                        pstmDigits6.setBoolean(27, digitalSet6[9]);
//                        pstmDigits6.setBoolean(28, digitalSet6[8]);
//                        pstmDigits6.setBoolean(29, digitalSet6[0]);
//                        pstmDigits6.setBoolean(30, digitalSet6[36]);
//                        pstmDigits6.setBoolean(31, digitalSet6[35]);
//                        pstmDigits6.setBoolean(32, digitalSet6[27]);
//                        pstmDigits6.setBoolean(33, digitalSet6[26]);
//                        pstmDigits6.setBoolean(34, digitalSet6[25]);
//                        pstmDigits6.setBoolean(35, digitalSet6[24]);
//                        pstmDigits6.setBoolean(36, digitalSet6[15]);
//                        pstmDigits6.setBoolean(37, digitalSet6[14]);
//                        pstmDigits6.setBoolean(38, digitalSet6[22]);
//
//                        pstmDigits6.executeUpdate();
//
//// Insert SEVENTH appearance of digits
//                        PreparedStatement pstmDigits7 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits7.setTimestamp(1, timeStamp8);
//                        pstmDigits7.setBoolean(2, digitalSet7[7]);
//                        pstmDigits7.setBoolean(3, digitalSet7[6]);
//                        pstmDigits7.setBoolean(4, digitalSet7[5]);
//                        pstmDigits7.setBoolean(5, digitalSet7[4]);
//                        pstmDigits7.setBoolean(6, digitalSet7[3]);
//                        pstmDigits7.setBoolean(7, digitalSet7[2]);
//                        pstmDigits7.setBoolean(8, digitalSet7[1]);
//                        pstmDigits7.setBoolean(9, digitalSet7[32]);
//                        pstmDigits7.setBoolean(10, digitalSet7[33]);
//                        pstmDigits7.setBoolean(11, digitalSet7[34]);
//                        pstmDigits7.setBoolean(12, digitalSet7[21]);
//                        pstmDigits7.setBoolean(13, digitalSet7[20]);
//                        pstmDigits7.setBoolean(14, digitalSet7[19]);
//                        pstmDigits7.setBoolean(15, digitalSet7[18]);
//                        pstmDigits7.setBoolean(16, digitalSet7[17]);
//                        pstmDigits7.setBoolean(17, digitalSet7[31]);
//                        pstmDigits7.setBoolean(18, digitalSet7[30]);
//                        pstmDigits7.setBoolean(19, digitalSet7[29]);
//                        pstmDigits7.setBoolean(20, digitalSet7[28]);
//                        pstmDigits7.setBoolean(21, digitalSet7[16]);
//                        pstmDigits7.setBoolean(22, digitalSet7[13]);
//                        pstmDigits7.setBoolean(23, digitalSet7[12]);
//                        pstmDigits7.setBoolean(24, digitalSet7[11]);
//                        pstmDigits7.setBoolean(25, digitalSet7[10]);
//                        pstmDigits7.setBoolean(26, digitalSet7[23]);
//                        pstmDigits7.setBoolean(27, digitalSet7[9]);
//                        pstmDigits7.setBoolean(28, digitalSet7[8]);
//                        pstmDigits7.setBoolean(29, digitalSet7[0]);
//                        pstmDigits7.setBoolean(30, digitalSet7[36]);
//                        pstmDigits7.setBoolean(31, digitalSet7[35]);
//                        pstmDigits7.setBoolean(32, digitalSet7[27]);
//                        pstmDigits7.setBoolean(33, digitalSet7[26]);
//                        pstmDigits7.setBoolean(34, digitalSet7[25]);
//                        pstmDigits7.setBoolean(35, digitalSet7[24]);
//                        pstmDigits7.setBoolean(36, digitalSet7[15]);
//                        pstmDigits7.setBoolean(37, digitalSet7[14]);
//                        pstmDigits7.setBoolean(38, digitalSet7[22]);
//
//                        pstmDigits7.executeUpdate();
//
//// Insert EIGHTS appearance of digits
//                        PreparedStatement pstmDigits8 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits8.setTimestamp(1, timeStamp9);
//                        pstmDigits8.setBoolean(2, digitalSet8[7]);
//                        pstmDigits8.setBoolean(3, digitalSet8[6]);
//                        pstmDigits8.setBoolean(4, digitalSet8[5]);
//                        pstmDigits8.setBoolean(5, digitalSet8[4]);
//                        pstmDigits8.setBoolean(6, digitalSet8[3]);
//                        pstmDigits8.setBoolean(7, digitalSet8[2]);
//                        pstmDigits8.setBoolean(8, digitalSet8[1]);
//                        pstmDigits8.setBoolean(9, digitalSet8[32]);
//                        pstmDigits8.setBoolean(10, digitalSet8[33]);
//                        pstmDigits8.setBoolean(11, digitalSet8[34]);
//                        pstmDigits8.setBoolean(12, digitalSet8[21]);
//                        pstmDigits8.setBoolean(13, digitalSet8[20]);
//                        pstmDigits8.setBoolean(14, digitalSet8[19]);
//                        pstmDigits8.setBoolean(15, digitalSet8[18]);
//                        pstmDigits8.setBoolean(16, digitalSet8[17]);
//                        pstmDigits8.setBoolean(17, digitalSet8[31]);
//                        pstmDigits8.setBoolean(18, digitalSet8[30]);
//                        pstmDigits8.setBoolean(19, digitalSet8[29]);
//                        pstmDigits8.setBoolean(20, digitalSet8[28]);
//                        pstmDigits8.setBoolean(21, digitalSet8[16]);
//                        pstmDigits8.setBoolean(22, digitalSet8[13]);
//                        pstmDigits8.setBoolean(23, digitalSet8[12]);
//                        pstmDigits8.setBoolean(24, digitalSet8[11]);
//                        pstmDigits8.setBoolean(25, digitalSet8[10]);
//                        pstmDigits8.setBoolean(26, digitalSet8[23]);
//                        pstmDigits8.setBoolean(27, digitalSet8[9]);
//                        pstmDigits8.setBoolean(28, digitalSet8[8]);
//                        pstmDigits8.setBoolean(29, digitalSet8[0]);
//                        pstmDigits8.setBoolean(30, digitalSet8[36]);
//                        pstmDigits8.setBoolean(31, digitalSet8[35]);
//                        pstmDigits8.setBoolean(32, digitalSet8[27]);
//                        pstmDigits8.setBoolean(33, digitalSet8[26]);
//                        pstmDigits8.setBoolean(34, digitalSet8[25]);
//                        pstmDigits8.setBoolean(35, digitalSet8[24]);
//                        pstmDigits8.setBoolean(36, digitalSet8[15]);
//                        pstmDigits8.setBoolean(37, digitalSet8[14]);
//                        pstmDigits8.setBoolean(38, digitalSet8[22]);
//
//                        pstmDigits8.executeUpdate();
//
//// Insert NINTH appearance of digits
//                        PreparedStatement pstmDigits9 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits9.setTimestamp(1, timeStamp11);
//                        pstmDigits9.setBoolean(2, digitalSet9[7]);
//                        pstmDigits9.setBoolean(3, digitalSet9[6]);
//                        pstmDigits9.setBoolean(4, digitalSet9[5]);
//                        pstmDigits9.setBoolean(5, digitalSet9[4]);
//                        pstmDigits9.setBoolean(6, digitalSet9[3]);
//                        pstmDigits9.setBoolean(7, digitalSet9[2]);
//                        pstmDigits9.setBoolean(8, digitalSet9[1]);
//                        pstmDigits9.setBoolean(9, digitalSet9[32]);
//                        pstmDigits9.setBoolean(10, digitalSet9[33]);
//                        pstmDigits9.setBoolean(11, digitalSet9[34]);
//                        pstmDigits9.setBoolean(12, digitalSet9[21]);
//                        pstmDigits9.setBoolean(13, digitalSet9[20]);
//                        pstmDigits9.setBoolean(14, digitalSet9[19]);
//                        pstmDigits9.setBoolean(15, digitalSet9[18]);
//                        pstmDigits9.setBoolean(16, digitalSet9[17]);
//                        pstmDigits9.setBoolean(17, digitalSet9[31]);
//                        pstmDigits9.setBoolean(18, digitalSet9[30]);
//                        pstmDigits9.setBoolean(19, digitalSet9[29]);
//                        pstmDigits9.setBoolean(20, digitalSet9[28]);
//                        pstmDigits9.setBoolean(21, digitalSet9[16]);
//                        pstmDigits9.setBoolean(22, digitalSet9[13]);
//                        pstmDigits9.setBoolean(23, digitalSet9[12]);
//                        pstmDigits9.setBoolean(24, digitalSet9[11]);
//                        pstmDigits9.setBoolean(25, digitalSet9[10]);
//                        pstmDigits9.setBoolean(26, digitalSet9[23]);
//                        pstmDigits9.setBoolean(27, digitalSet9[9]);
//                        pstmDigits9.setBoolean(28, digitalSet9[8]);
//                        pstmDigits9.setBoolean(29, digitalSet9[0]);
//                        pstmDigits9.setBoolean(30, digitalSet9[36]);
//                        pstmDigits9.setBoolean(31, digitalSet9[35]);
//                        pstmDigits9.setBoolean(32, digitalSet9[27]);
//                        pstmDigits9.setBoolean(33, digitalSet9[26]);
//                        pstmDigits9.setBoolean(34, digitalSet9[25]);
//                        pstmDigits9.setBoolean(35, digitalSet9[24]);
//                        pstmDigits9.setBoolean(36, digitalSet9[15]);
//                        pstmDigits9.setBoolean(37, digitalSet9[14]);
//                        pstmDigits9.setBoolean(38, digitalSet9[22]);
//
//                        pstmDigits9.executeUpdate();
//
//// Insert TENTH appearance of digits
//                        PreparedStatement pstmDigits10 = conn4.prepareStatement("INSERT INTO APP.DIGITS (TIMESTAMP, DIG1, DIG2," +
//                                "DIG3, DIG4, DIG5, DIG6, DIG7, DIG8, DIG9, DIG10, DIG11, DIG12, DIG13, DIG14, DIG15, DIG16," +
//                                "DIG17, DIG18, DIG19, DIG20, DIG21, DIG22, DIG23, DIG24, DIG25, DIG26, DIG27, DIG28, DIG29," +
//                                "DIG30, DIG31, DIG32, DIG33, DIG34, DIG35, DIG36, DIG37) " +
//                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
//                                " ?, ?, ?, ?, ?, ?, ?, ? )");
//                        pstmDigits10.setTimestamp(1, timeStamp12);
//                        pstmDigits10.setBoolean(2, digitalSet10[7]);
//                        pstmDigits10.setBoolean(3, digitalSet10[6]);
//                        pstmDigits10.setBoolean(4, digitalSet10[5]);
//                        pstmDigits10.setBoolean(5, digitalSet10[4]);
//                        pstmDigits10.setBoolean(6, digitalSet10[3]);
//                        pstmDigits10.setBoolean(7, digitalSet10[2]);
//                        pstmDigits10.setBoolean(8, digitalSet10[1]);
//                        pstmDigits10.setBoolean(9, digitalSet10[32]);
//                        pstmDigits10.setBoolean(10, digitalSet10[33]);
//                        pstmDigits10.setBoolean(11, digitalSet10[34]);
//                        pstmDigits10.setBoolean(12, digitalSet10[21]);
//                        pstmDigits10.setBoolean(13, digitalSet10[20]);
//                        pstmDigits10.setBoolean(14, digitalSet10[19]);
//                        pstmDigits10.setBoolean(15, digitalSet10[18]);
//                        pstmDigits10.setBoolean(16, digitalSet10[17]);
//                        pstmDigits10.setBoolean(17, digitalSet10[31]);
//                        pstmDigits10.setBoolean(18, digitalSet10[30]);
//                        pstmDigits10.setBoolean(19, digitalSet10[29]);
//                        pstmDigits10.setBoolean(20, digitalSet10[28]);
//                        pstmDigits10.setBoolean(21, digitalSet10[16]);
//                        pstmDigits10.setBoolean(22, digitalSet10[13]);
//                        pstmDigits10.setBoolean(23, digitalSet10[12]);
//                        pstmDigits10.setBoolean(24, digitalSet10[11]);
//                        pstmDigits10.setBoolean(25, digitalSet10[10]);
//                        pstmDigits10.setBoolean(26, digitalSet10[23]);
//                        pstmDigits10.setBoolean(27, digitalSet10[9]);
//                        pstmDigits10.setBoolean(28, digitalSet10[8]);
//                        pstmDigits10.setBoolean(29, digitalSet10[0]);
//                        pstmDigits10.setBoolean(30, digitalSet10[36]);
//                        pstmDigits10.setBoolean(31, digitalSet10[35]);
//                        pstmDigits10.setBoolean(32, digitalSet10[27]);
//                        pstmDigits10.setBoolean(33, digitalSet10[26]);
//                        pstmDigits10.setBoolean(34, digitalSet10[25]);
//                        pstmDigits10.setBoolean(35, digitalSet10[24]);
//                        pstmDigits10.setBoolean(36, digitalSet10[15]);
//                        pstmDigits10.setBoolean(37, digitalSet10[14]);
//                        pstmDigits10.setBoolean(38, digitalSet10[22]);
//
//                        pstmDigits10.executeUpdate();
//
//                        System.out.println("Connection for Digits end...");
//
////////////////////////////////////////////////////////////////////////////////////////////
//
//                        timeStamp1_2.setTime(timeStamp1_2.getTime() + 1 * 1000L * 1);
//                        timeStamp2.setTime(timeStamp2.getTime() + 1 * 1000L * 1);
//                        timeStamp3.setTime(timeStamp3.getTime() + 1 * 1000L * 1);
//                        timeStamp5.setTime(timeStamp5.getTime() + 1 * 1000L * 1);
//                        timeStamp6.setTime(timeStamp6.getTime() + 1 * 1000L * 1);
//                        timeStamp7_2.setTime(timeStamp7_2.getTime() + 1 * 1000L * 1);
//                        timeStamp8.setTime(timeStamp8.getTime() + 1 * 1000L * 1);
//                        timeStamp9.setTime(timeStamp9.getTime() + 1 * 1000L * 1);
//                        timeStamp11.setTime(timeStamp11.getTime() + 1 * 1000L * 1);
//                        timeStamp12.setTime(timeStamp12.getTime() + 1 * 1000L * 1);
//
//                        System.out.println("Connection for Digits Timestamp changed...");
//
//                        System.out.println("TimeStamp1 for Digits: " + timeStamp1_1);
//                        System.out.println("TimeStamp1 for Digits: " + timeStamp1_2);
//                    }
//
//                    is.mark(0);
//                } catch (Exception ex) {ex.printStackTrace();}
//            }
//        } finally {is.close();}
//        parentFrm.progressBar2.setValue(12570624);
        System.gc();
        return allBytes;
    }

    public static void main(String[] args) throws IOException {
//        File file = new File("out12.bin");
//        byte[] d = getBytesFromFile(file);
//        System.out.println(byteCoefficientToSend(5.0)[0]);
//        System.out.println(byteCoefficientToSend(5.0)[1]);
//        System.out.println(byteCoefficientToSend(5.0)[2]);
//        System.out.println(byteCoefficientToSend(5.0)[3]);
//        System.out.println(getCoefficient(byteCoefficientToSend(2.5)));

//        byte[] coeffOneBytes = new byte[4];
//        coeffOneBytes[0] = (byte)(63 );
//        coeffOneBytes[1] = (byte)(240);
//        coeffOneBytes[2] = (byte)(0 );
//        coeffOneBytes[3] = (byte)(0 );
//
//
        System.out.println(toByteArray(0.1818)[0] );
        System.out.println(toByteArray(0.1818)[1] );
        System.out.println(toByteArray(0.1818)[2] );
        System.out.println(toByteArray(0.1818)[3] );

        System.out.println("\n");

        System.out.println(toByteArray(5.0)[0] );
        System.out.println(toByteArray(5.0)[1] );
        System.out.println(toByteArray(5.0)[2] );
        System.out.println(toByteArray(5.0)[3] );

//        System.out.println("");
//
//        System.out.println(coeffOneBytes[0]);
//        System.out.println(coeffOneBytes[1]);
//        System.out.println(coeffOneBytes[2]);
//        System.out.println(coeffOneBytes[3]);
//
//        if (coeffOneBytes[0] == toByteArray(1.0)[0]) System.out.println("true...");
//        if (coeffOneBytes[1] == toByteArray(1.0)[1]) System.out.println("true...");
//        if (coeffOneBytes[2] == toByteArray(1.0)[2]) System.out.println("true...");
//        if (coeffOneBytes[3] == toByteArray(1.0)[3]) System.out.println("true...");
//        System.out.println(coeffOneBytes.length);
//        System.out.println(toByteArray(1.0).length);
//
//        System.out.println(toDouble(toByteArray(1.0)));
//
//        System.out.println(toDouble(coeffOneBytes));




//        String string = "123";
//        byte[] b = string.getBytes();
//
//        System.out.println("byte size = " + b.length);
//        for (int i = 0; i < b.length; i++) {
//            System.out.println("b[" + i + "]= " + b[i]);
//        }
//
//        String newString = new String(b, "UTF-8");
//
//        System.out.println("String = " + newString);
//        System.out.println("Sting as Number = " + Integer.parseInt(newString));


    }
}
