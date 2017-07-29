package main;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import javafx.util.converter.ByteStringConverter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DM on 22.10.2016.
 */
public class MpusbWrapperTwo {
    public static int MP_WRITE = 0;
    public static int MP_READ = 1;
    private static String INVALID_HANDLE_VALUE = "";

    public static int myOutPipe;
    public static int myInPipe;
    public static String out_pipe = "\\MCHP_EP1";
    public static String in_pipe = "\\MCHP_EP1";
    public static String vid_pid_norm = "vid_04D8&pid_000C";
//    FormTwo formTwo = FormTwo.getInstance();

    private DecimalFormat df = new DecimalFormat("0.00");

    public interface Mpusbapi extends Library {

        public int MPUSBGetDLLVersion();

        public int MPUSBGetDeviceCount(String pVID_PID);

        public Integer MPUSBOpen(int instance,
                                 String pVID_PID,
                                 String pEP,
                                 long dwDir,
                                 long dwReserved);

        public Integer MPUSBRead(Integer handle,
                             byte[] pData,
                             long dwLen,
                             //IntByReference pLength,
                             Integer pLength,
                             long dwMilliseconds);

        public Integer MPUSBWrite(Integer handle,
                              byte[] pData,
                              long dwLen,
                              Integer pLength,
                              //IntByReference pLength,
                              //Pointer pLength,
                                  long dwMilliseconds);

        public int MPUSBReadInt(int handle,
                                String pData,
                                long dwLen,
                                String pLength,
                                long dwMilliseconds);


        public Boolean MPUSBClose(int handle);

    }

//    public byte toByte(int number) {
//        int tmp = number & 0xff
//        return (tmp & 0x80) == 0 ? tmp : tmp - 256;
//    }
//
//  public static Mpusbapi lib = (Mpusbapi) Native.loadLibrary("c:\\\\mpusbapi", Mpusbapi.class);
//    public static Mpusbapi lib = (Mpusbapi) Native.loadLibrary("mpusbapi", Mpusbapi.class);

//  Paths.get(".").toAbsolutePath().normalize().toString() + "\\\\mpusbapi"


    public static String mpusbapiPath = Paths.get(".").toAbsolutePath().normalize().toString() + "\\mpusbapi";

    public static String mpusbapiJavaPath = mpusbapiPath.replaceAll("\\\\", "/");

    public static Mpusbapi lib = (Mpusbapi) Native.loadLibrary(mpusbapiJavaPath, Mpusbapi.class);

//    public static Mpusbapi lib ;

    public static boolean readDLL() {
        try {
            lib = (Mpusbapi) Native.loadLibrary(mpusbapiJavaPath, Mpusbapi.class);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Ok");
        } catch (Exception dllEx) {
            System.out.println(dllEx);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Couldn't load library");
            return false;
        }
        return true;
    }


    public static boolean writeUsbCurrentDate(int[] commands) {
        int result;
        byte[] test = new byte[64];

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);

        test[0] = (byte) (0x60 & (0xff));
        test[1] = (byte) ((DecimalToBCD(year)[1]) & (0xff));
        test[2] = (byte) ((DecimalToBCD(month + 1)[0]) & (0xff));
        test[3] = (byte) ((DecimalToBCD(day)[0]) & (0xff));
        test[4] = (byte) ((DecimalToBCD(dayWeek)[0] - 1) & (0xff));
        test[5] = (byte) ((DecimalToBCD(hour)[0]) & (0xff));
        test[6] = (byte) ((DecimalToBCD(minute)[0]) & (0xff));
        test[7] = (byte) ((DecimalToBCD(sec)[0]) & (0xff));

//        test[0] = (byte) (0x60 & (0xff));
//        test[1] = (byte) ((0x01) & (0xff));
//        test[2] = (byte) ((0x02) & (0xff));
//        test[3] = (byte) ((0x03) & (0xff));
//        test[4] = (byte) ((0x04) & (0xff));
//        test[5] = (byte) ((0x05) & (0xff));
//        test[6] = (byte) ((0x06) & (0xff));
//        test[7] = (byte) ((0x07) & (0xff));


        Integer len = new Integer(test.length);
        System.out.println("len = " + len);

        result = lib.MPUSBWrite(myOutPipe, test, test.length, len, 1000);
        System.out.println("write = " + result);
        if (result != 1) return false;
        return true;
    }

    public static boolean writeUsbWagon(int[] commands, FormTwo parentFrame) {
        int result;
        byte[] test = new byte[64];


//        int carNo = Integer.parseInt(parentFrame.carNoText.getText());

        byte[] carNoBytes = new byte[2];
        //carNoBytes = toByteArray(carNo);
        carNoBytes = parentFrame.carNoText.getText().getBytes();


        test[0] = (byte) (0x80 & (0xff));
//        test[1] = (byte) ((DecimalToBCD(carNo)[0]) & (0xff));
//
//        if (carNo < 100)
//            test[2] = 0;
//        else
//            test[2] = (byte) ((DecimalToBCD(carNo)[1]) & (0xff));
//
//        System.out.println("Test1 = " + test[1]);
//        System.out.println("Test2 = " + test[2]);

        System.out.println("Array length = " + carNoBytes.length);
        System.out.println("carNo Zero = " + carNoBytes[0]);


        if (carNoBytes.length == 5) {
            test[1] = (byte) (carNoBytes[0] & (0xff));
            test[2] = (byte) (carNoBytes[1] & (0xff));
            test[3] = (byte) (carNoBytes[2] & (0xff));
            test[4] = (byte) (carNoBytes[3] & (0xff));
            test[5] = (byte) (carNoBytes[4] & (0xff));
        } else if (carNoBytes.length == 4) {
            test[1] = (byte) (0);
            test[2] = (byte) (carNoBytes[0] & (0xff));
            test[3] = (byte) (carNoBytes[1] & (0xff));
            test[4] = (byte) (carNoBytes[2] & (0xff));
            test[5] = (byte) (carNoBytes[3] & (0xff));
        } else if (carNoBytes.length == 3) {
            test[1] = (byte) (0);
            test[2] = (byte) (0);
            test[3] = (byte) (carNoBytes[0] & (0xff));
            test[4] = (byte) (carNoBytes[1] & (0xff));
            test[5] = (byte) (carNoBytes[2] & (0xff));
        } else if (carNoBytes.length == 2) {
            test[1] = (byte) (0);
            test[2] = (byte) (0);
            test[3] = (byte) (0);
            test[4] = (byte) (carNoBytes[0] & (0xff));
            test[5] = (byte) (carNoBytes[1] & (0xff));
        } else if (carNoBytes.length == 1) {
            test[1] = 0;
            test[2] = 0;
            test[3] = 0;
            test[4] = 0;
            test[5] = (byte) (carNoBytes[0] & (0xff));
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Couldn't write car number");
        }

        Integer len = new Integer(test.length);
        System.out.println("len = " + len);

        result = lib.MPUSBWrite(myOutPipe, test, test.length, len, 1000);
        System.out.println("write = " + result);
        if (result != 1) {
            System.out.println("Couldn't write data...");
            return false;
        }
        return true;
    }

    public static boolean writeUsbCoefficients(int[] commands, FormTwo parentFrame) {
        int result;

        double coeffOnePmax = Double.parseDouble(parentFrame.textWriteCoefOnePmax.getText());
        double coeffOneUmin = Double.parseDouble(parentFrame.textWriteCoefOneUmin.getText());
        double coeffOneUmax = Double.parseDouble(parentFrame.textWriteCoefOneUmax.getText());

        double coeffTwoPmax = Double.parseDouble(parentFrame.textWriteCoefTwoPmax.getText());
        double coeffTwoUmin = Double.parseDouble(parentFrame.textWriteCoefTwoUmin.getText());
        double coeffTwoUmax = Double.parseDouble(parentFrame.textWriteCoefTwoUmax.getText());

        double coeffThree = Double.parseDouble(parentFrame.coeffSpeedWrite.getText());//Double.parseDouble(parentFrame.coefPresTwoText.getText());

        System.out.println("coeffOn Pmax = " + coeffOnePmax);
        System.out.println("coeffOn Pmax = " + coeffOneUmin);
        System.out.println("coeffOn Pmax = " + coeffOneUmax);

        System.out.println("coeffOn Pmax = " + coeffTwoPmax);
        System.out.println("coeffOn Pmax = " + coeffTwoUmin);
        System.out.println("coeffOn Pmax = " + coeffTwoUmax);


        byte[] coeffOnePmaxBytes = new byte[4];
        byte[] coeffOneUminBytes = new byte[4];
        byte[] coeffOneUmaxBytes = new byte[4];

        byte[] coeffTwoPmaxBytes = new byte[4];
        byte[] coeffTwoUminBytes = new byte[4];
        byte[] coeffTwoUmaxBytes = new byte[4];

        byte[] coeffThreeBytes = new byte[4];

        coeffOnePmaxBytes = toByteArray(coeffOnePmax);
        coeffOneUminBytes = toByteArray(coeffOneUmin);
        coeffOneUmaxBytes = toByteArray(coeffOneUmax);

        coeffTwoPmaxBytes = toByteArray(coeffTwoPmax);
        coeffTwoUminBytes = toByteArray(coeffTwoUmin);
        coeffTwoUmaxBytes = toByteArray(coeffTwoUmax);

        coeffThreeBytes = toByteArray(coeffThree);

        System.out.println(coeffOnePmaxBytes[0]);
        System.out.println(coeffOnePmaxBytes[1]);
        System.out.println(coeffOnePmaxBytes[2]);
        System.out.println(coeffOnePmaxBytes[3]);



        //Long y = ;

//        System.out.println("Double One = " + coeffOne );

//        for (int i = 0; i < coeffOneBytes.length; i++) {
//            System.out.println("CoeffOne = " + (coeffOneBytes[i] & (0xff)));
//        }

//        System.out.println("CoeffDouble = " + toDouble(coeffOneBytes));

        byte[] test = new byte[64];

        test[0] = (byte) (0x90 & (0xff));

        test[1] = (byte) (coeffOnePmaxBytes[0]);
        test[2] = (byte) (coeffOnePmaxBytes[1]);
        test[3] = (byte) (coeffOnePmaxBytes[2]);
        test[4] = (byte) (coeffOnePmaxBytes[3]);

        test[5] = (byte) (coeffOneUminBytes[0]);
        test[6] = (byte) (coeffOneUminBytes[1]);
        test[7] = (byte) (coeffOneUminBytes[2]);
        test[8] = (byte) (coeffOneUminBytes[3]);

        test[9] = (byte) (coeffOneUmaxBytes[0]);
        test[10] = (byte) (coeffOneUmaxBytes[1]);
        test[11] = (byte) (coeffOneUmaxBytes[2]);
        test[12] = (byte) (coeffOneUmaxBytes[3]);

        test[13] = (byte) (coeffTwoPmaxBytes[0]);
        test[14] = (byte) (coeffTwoPmaxBytes[1]);
        test[15] = (byte) (coeffTwoPmaxBytes[2]);
        test[16] = (byte) (coeffTwoPmaxBytes[3]);

        test[17] = (byte) (coeffTwoUminBytes[0]);
        test[18] = (byte) (coeffTwoUminBytes[1]);
        test[19] = (byte) (coeffTwoUminBytes[2]);
        test[20] = (byte) (coeffTwoUminBytes[3]);

        test[21] = (byte) (coeffTwoUmaxBytes[0]);
        test[22] = (byte) (coeffTwoUmaxBytes[1]);
        test[23] = (byte) (coeffTwoUmaxBytes[2]);
        test[24] = (byte) (coeffTwoUmaxBytes[3]);

        test[25] = (byte) (coeffThreeBytes[0] );
        test[26] = (byte) (coeffThreeBytes[1] );
        test[27] = (byte) (coeffThreeBytes[2] );
        test[28] = (byte) (coeffThreeBytes[3] );

        Integer len = new Integer(test.length);
        System.out.println("len = " + len);

        result = lib.MPUSBWrite(myOutPipe, test, test.length, len, 1000);
        System.out.println("write = " + result);
        if (result != 1) {
            System.out.println("Couldn't write data...");
            return false;
        }
        return true;
    }


    public static boolean writeUsb(int[] commands) {
        int result;
        byte[] test = new byte[64];

//        test[0] = (byte) (0x71 & (0xff));
//        test[0] = (byte) (0x11 & (0xff));
//        test[1] =  (byte) (0x1 & (0xff) );

        test[0] = (byte) commands[0];
        test[1] = (byte) commands[1];
        System.out.println(test[0]);
        System.out.println(test[1]);


        Integer len = new Integer(test.length);
        System.out.println("len = " + len);

        result = lib.MPUSBWrite(myOutPipe, test, test.length, len, 1000);
        System.out.println("write = " + result);
        if (result != 1) return false;
//        System.out.println("MyOutPipe = " + myOutPipe);
//        System.out.println("len = " + len);
        return true;
    }

    public static byte[] getData(int n, Frame frame, FormTwo parentFrm) {
        String text = "";
        int res = -999;
        byte[] temp = new byte[n];
//        12582912
        int m = n / 16384;
        int bytesToRead = n / m;
        byte[] temp2 = new byte[m];
        Integer pLength = new Integer(n);

        System.out.println("Trying to read...");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < bytesToRead; i++) {
            res = lib.MPUSBRead(myInPipe, temp2, m, pLength, 1000);
            parentFrm.progressBar2.setValue(i * m);
            parentFrm.dpb.setValue(i * m);
            try {
                baos.write(temp2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        temp = baos.toByteArray();

        System.out.println("\npLength = " + pLength);


        try {
            FileOutputStream fos = new FileOutputStream(new File("out6.bin"));
            fos.write(temp);
        } catch (Exception e) {e.printStackTrace(); return null;}

        if (res != 1) {
            return null;
        }

        System.out.println("read = " + res);
        System.out.println("pLength = " + pLength);
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            temp[i] &= 0xff;
        }

        return temp;
    }

    public static byte[] getDataRT(int n) {
        String text = "";
        int res = -999;
        byte[] temp = new byte[n];
//        12582912
//        int m = n / 16384;
//        int bytesToRead = n / m;
        int m = n;
        int bytesToRead = m;

        byte[] temp2 = new byte[m];
        Integer pLength = new Integer(n);

//        System.out.println("Trying to read...");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        for (int i = 0; i < bytesToRead; i++) {
            res = lib.MPUSBRead(myInPipe, temp2, m, pLength, 1000);
//            parentFrm.progressBar2.setValue(i * m);
//            parentFrm.dpb.setValue(i * m);
            try {
                baos.write(temp2);

            } catch (IOException e) {
                e.printStackTrace();
            }
//        }

        temp = baos.toByteArray();

//        System.out.println("\npLength = " + pLength);

//        try {
//            FileOutputStream fos = new FileOutputStream(new File("out6.bin"));
//            fos.write(temp);
//        } catch (Exception e) {e.printStackTrace(); return null;}

        if (res != 1) {
            return null;
        }

//        System.out.println("read = " + res);
//        System.out.println("pLength = " + pLength);
//        System.out.println(text);

        for (int i = 0; i < n; i++) {
            temp[i] &= 0xff;
        }
        return temp;
    }


    public static byte[] getWagon(int n) {
        String text = "";
        byte[] temp = new byte[n];
        Integer pLength = new Integer(n);

        System.out.println("Trying to read...");
        int res = lib.MPUSBRead(myInPipe, temp, n, pLength, 1000);
        System.out.println("\npLength = " + pLength);


        if (res != 1) {
            return null;
        }

        System.out.println("read = " + res);
        System.out.println("pLength = " + pLength);
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            temp[i] &= 0xff;
        }

        byte[] coef1Bytes = new byte[4];
        byte[] carNo = new byte[2];
        coef1Bytes[0] = temp[2];
        coef1Bytes[1] = temp[3];
        coef1Bytes[2] = temp[4];
        coef1Bytes[3] = temp[5];

        carNo[0] = temp[1];
        carNo[1] = temp[2];

        for (int i = 0; i < temp.length; i++) {
            System.out.println("Read " + temp[i]);

        }
        System.out.println(mergeTwoBytes(carNo));

        return temp;
    }

    public static byte[] getLog(int n) {
        String text = "";
        byte[] temp = new byte[n];
        Integer pLength = new Integer(n);

        System.out.println("Trying to read...");
        int res = lib.MPUSBRead(myInPipe, temp, n, pLength, 1000);
        System.out.println("\npLength = " + pLength);


        if (res != 1) {
            return null;
        }

        System.out.println("read = " + res);
        System.out.println("pLength = " + pLength);
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            temp[i] &= 0xff;
        }

//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//        int picDateTimeLogOne[] = new int[7];
//
//        byte[] coef1Bytes = new byte[4];
//        byte[] carNo = new byte[2];
//        coef1Bytes[0] = temp[2];
//        coef1Bytes[1] = temp[3];
//        coef1Bytes[2] = temp[4];
//        coef1Bytes[3] = temp[5];
//
//        carNo[0] = temp[1];
//        carNo[1] = temp[2];
//
//        for (int i = 0; i < temp.length; i++) {
//            System.out.println("Read " + temp[i]);
//
//        }
//        System.out.println(mergeTwoBytes(carNo));

        return temp;
    }


    static int bcd2dec(int bcd) {
        return (bcd != 0) ? (bcd2dec(bcd >> 4) * 10 + (bcd & 0x0f)) : 0;
    }

    public boolean setTime(int[] commands) {
        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
                return false;
            }

            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);

            if (!writeUsbCurrentDate(commands)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані до пристрою. \n" +
                        "Перевірте підключення і повторіть операцію.");
                return false;
            }

            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій. \n" +
                        "Повторіть операцію зчитування");
                return false;
            }
            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

        }

    public boolean setWagonNumber(int[] commands, FormTwo parentFrame) {
        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
                return false;
            }

            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);

            if (!writeUsbWagon(commands, parentFrame)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані до пристрою. \n" +
                        "Перевірте підключення і повторіть операцію.");
                return false;
            }

            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій. \n" +
                        "Повторіть операцію зчитування");
                return false;
            }
            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

//    private boolean skip64(byte[] bytes) {
//        for (int i = 0; i < bytes.length) {
//
//        }
//        return
//    }

//    static java.sql.Date getDateTime(int[] data) {
    static Date getDateTime(int[] data) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        data[0] += 2000;
        System.out.println("Data[0] = " + data[0]);
        System.out.println("Data[1] = " + data[1]);
        System.out.println("Data[2] = " + data[2]);
        System.out.println("Data[3] = " + data[3]);
        System.out.println("Data[4] = " + data[4]);
        System.out.println("Data[5] = " + data[5]);
        System.out.println("Data[6] = " + data[6]);



//        java.util.Calendar cal = Calendar.getInstance();
//        java.util.Date utilDate = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        Date utilDate = new Date();

        cal.setTime(utilDate);
        cal.set(Calendar.MONTH, data[1] - 1);
        cal.set(Calendar.YEAR, data[0]);
        cal.set(Calendar.DAY_OF_MONTH, data[2]);
        cal.set(Calendar.HOUR_OF_DAY, data[3]);
        cal.set(Calendar.MINUTE, data[4]);
        cal.set(Calendar.SECOND, data[5]);
        cal.set(Calendar.MILLISECOND, data[6]);

        System.out.println("Time: " + cal.getTime().getTime());
        System.out.println("Time: " + cal.getTime());
        System.out.println("Time: " + dateFormat.format(cal.getTime()));
        String time = dateFormat.format(cal.getTime());
        System.out.println("String time = " + time);

//        java.sql.Date sqlDateOne = new java.sql.Date(dateFormat.format(cal.getTime().getTime()));
//        Date sqlDateOne = new Date(cal.getTime().getTime());
        Date sqlDateOne = null;
        try {
            sqlDateOne = dateFormat.parse(time.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Date = " + sqlDateOne);


        return sqlDateOne;
//        return null;
    }

    public boolean readLog(int[] commands, FormTwo parentFrame) {
        parentFrame.textAreaLogTime.setText("");
        parentFrame.textAreaLogCarNo.setText("");
        parentFrame.textAreaLogCoeffs.setText("");

        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
                return false;
            }
            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);


            if (!writeUsb(commands)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані до пристрою. \n" +
                        "Перевірте підключення і повторіть операцію.");
                return false;
            }

            int memorySize = 1920;

            byte[] d1 = getLog(memorySize);
            if (d1 == null) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо прочитати дані з пристрою.\n" +
                        "Повторно підключіть пристрій і повторіть зчитування.");
                return false;
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Дані прочитано");

            }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int picDateTimeLogOne[] = new int[7];
        int picDateTimeLogNew[] = new int[7];


            byte[] carNoBytesOld = new byte[5];
            byte[] carNoBytesNew = new byte[5];

            byte[] coeffOneBytesOldPmax = new byte[8];
            byte[] coeffOneBytesOldUmin = new byte[8];
            byte[] coeffOneBytesOldUmax = new byte[8];

            byte[] coeffOneBytesNewPmax = new byte[8];
            byte[] coeffOneBytesNewUmin = new byte[8];
            byte[] coeffOneBytesNewUmax = new byte[8];

            byte[] coeffTwoBytesOldPmax = new byte[8];
            byte[] coeffTwoBytesOldUmin = new byte[8];
            byte[] coeffTwoBytesOldUmax = new byte[8];

            byte[] coeffTwoBytesNewPmax = new byte[8];
            byte[] coeffTwoBytesNewUmin = new byte[8];
            byte[] coeffTwoBytesNewUmax = new byte[8];


//            byte[] coeffTwoBytesOld = new byte[8];
            byte[] coeffSpeedBytesOld = new byte[8];

//            byte[] coeffOneBytesNew = new byte[8];
//            byte[] coeffTwoBytesNew = new byte[8];
            byte[] coeffSpeedBytesNew = new byte[8];

            for (int i = 0; i < 10; i++) {
                System.out.println("d1[0] = " + d1[0]);
                if (d1[64 * i] == -1) {
 //                   JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Немає даних");
                    break;
                }
                else {

                    picDateTimeLogOne[0] = bcd2dec(d1[0 + 64 * i]);
                    picDateTimeLogOne[1] = bcd2dec(d1[1 + 64 * i]);
                    picDateTimeLogOne[2] = bcd2dec(d1[2 + 64 * i]);
                    picDateTimeLogOne[3] = bcd2dec(d1[3 + 64 * i]);
                    picDateTimeLogOne[4] = bcd2dec(d1[4 + 64 * i]);
                    picDateTimeLogOne[5] = bcd2dec(d1[5 + 64 * i]);
                    picDateTimeLogOne[6] = 0;

                    picDateTimeLogNew[0] = bcd2dec(d1[6 + 64 * i]);
                    picDateTimeLogNew[1] = bcd2dec(d1[7 + 64 * i]);
                    picDateTimeLogNew[2] = bcd2dec(d1[8 + 64 * i]);
                    picDateTimeLogNew[3] = bcd2dec(d1[9 + 64 * i]);
                    picDateTimeLogNew[4] = bcd2dec(d1[10 + 64 * i]);
                    picDateTimeLogNew[5] = bcd2dec(d1[11 + 64 * i]);
                    picDateTimeLogNew[6] = 0;

//                    java.sql.Date dateTimeOld = getDateTime(picDateTimeLogOne);
//                    java.sql.Date dateTimeNew = getDateTime(picDateTimeLogNew);
                    Date dateTimeOld = getDateTime(picDateTimeLogOne);
                    Date dateTimeNew = getDateTime(picDateTimeLogNew);


                    parentFrame.textAreaLogTime.append("Вихідний час:   " + dateFormat.format(dateTimeOld) + "\t Новий час:   " + dateFormat.format(dateTimeNew) + "\n");
                }
            }

            for (int i = 10; i < 20; i++) {
                if (d1[64 * i] == -1) break;
                else {
                    picDateTimeLogOne[0] = bcd2dec(d1[0 + 64 * i]);
                    picDateTimeLogOne[1] = bcd2dec(d1[1 + 64 * i]);
                    picDateTimeLogOne[2] = bcd2dec(d1[2 + 64 * i]);
                    picDateTimeLogOne[3] = bcd2dec(d1[3 + 64 * i]);
                    picDateTimeLogOne[4] = bcd2dec(d1[4 + 64 * i]);
                    picDateTimeLogOne[5] = bcd2dec(d1[5 + 64 * i]);
                    picDateTimeLogOne[6] = 0;

                    carNoBytesOld[0] = d1[6 + 64 * i];
                    carNoBytesOld[1] = d1[7 + 64 * i];
                    carNoBytesOld[2] = d1[8 + 64 * i];
                    carNoBytesOld[3] = d1[9 + 64 * i];
                    carNoBytesOld[4] = d1[10 + 64 * i];

                    carNoBytesNew[0] = d1[11 + 64 * i];
                    carNoBytesNew[1] = d1[12 + 64 * i];
                    carNoBytesNew[2] = d1[13 + 64 * i];
                    carNoBytesNew[3] = d1[14 + 64 * i];
                    carNoBytesNew[4] = d1[15 + 64 * i];

                    if (carNoBytesOld[0] == 0) carNoBytesOld[0] = 48;
                    if (carNoBytesOld[1] == 0) carNoBytesOld[1] = 48;
                    if (carNoBytesOld[2] == 0) carNoBytesOld[2] = 48;
                    if (carNoBytesOld[3] == 0) carNoBytesOld[3] = 48;

                    if (carNoBytesNew[0] == 0) carNoBytesNew[0] = 48;
                    if (carNoBytesNew[1] == 0) carNoBytesNew[1] = 48;
                    if (carNoBytesNew[2] == 0) carNoBytesNew[2] = 48;
                    if (carNoBytesNew[3] == 0) carNoBytesNew[3] = 48;

                    Date dateTimeOld = getDateTime(picDateTimeLogOne);
                    String carNoOld = new String(carNoBytesOld);
                    String carNoNew = new String(carNoBytesNew);

                    parentFrame.textAreaLogCarNo.append("Час запису:   " + dateFormat.format(dateTimeOld) + "\t Вихідний номер:   " + carNoOld + "\t Новий номер:   " + carNoNew + "\n");
                }
            }

            for (int i = 20; i < 30; i++) {
                if (d1[64 * i] == -1) break;
                else {
                    picDateTimeLogOne[0] = bcd2dec(d1[0 + 64 * i]);
                    picDateTimeLogOne[1] = bcd2dec(d1[1 + 64 * i]);
                    picDateTimeLogOne[2] = bcd2dec(d1[2 + 64 * i]);
                    picDateTimeLogOne[3] = bcd2dec(d1[3 + 64 * i]);
                    picDateTimeLogOne[4] = bcd2dec(d1[4 + 64 * i]);
                    picDateTimeLogOne[5] = bcd2dec(d1[5 + 64 * i]);
                    picDateTimeLogOne[6] = 0;

                    coeffOneBytesOldPmax[0] = d1[6 + 64 * i];
                    coeffOneBytesOldPmax[1] = d1[7 + 64 * i];
                    coeffOneBytesOldPmax[2] = d1[8 + 64 * i];
                    coeffOneBytesOldPmax[3] = d1[9 + 64 * i];

                    coeffOneBytesNewPmax[0] = d1[10 + 64 * i];
                    coeffOneBytesNewPmax[1] = d1[11 + 64 * i];
                    coeffOneBytesNewPmax[2] = d1[12 + 64 * i];
                    coeffOneBytesNewPmax[3] = d1[13 + 64 * i];

                    coeffOneBytesOldUmin[0] = d1[14 + 64 * i];
                    coeffOneBytesOldUmin[1] = d1[15 + 64 * i];
                    coeffOneBytesOldUmin[2] = d1[16 + 64 * i];
                    coeffOneBytesOldUmin[3] = d1[17 + 64 * i];

                    coeffOneBytesNewUmin[0] = d1[18 + 64 * i];
                    coeffOneBytesNewUmin[1] = d1[19 + 64 * i];
                    coeffOneBytesNewUmin[2] = d1[20 + 64 * i];
                    coeffOneBytesNewUmin[3] = d1[21 + 64 * i];

                    coeffOneBytesOldUmax[0] = d1[22 + 64 * i];
                    coeffOneBytesOldUmax[1] = d1[23 + 64 * i];
                    coeffOneBytesOldUmax[2] = d1[24 + 64 * i];
                    coeffOneBytesOldUmax[3] = d1[25 + 64 * i];

                    coeffOneBytesNewUmax[0] = d1[26 + 64 * i];
                    coeffOneBytesNewUmax[1] = d1[27 + 64 * i];
                    coeffOneBytesNewUmax[2] = d1[28 + 64 * i];
                    coeffOneBytesNewUmax[3] = d1[29 + 64 * i];

                    /////////////////////////////////////////

                    coeffTwoBytesOldPmax[0] = d1[30 + 64 * i];
                    coeffTwoBytesOldPmax[1] = d1[31 + 64 * i];
                    coeffTwoBytesOldPmax[2] = d1[32 + 64 * i];
                    coeffTwoBytesOldPmax[3] = d1[33 + 64 * i];

                    coeffTwoBytesNewPmax[0] = d1[34 + 64 * i];
                    coeffTwoBytesNewPmax[1] = d1[35 + 64 * i];
                    coeffTwoBytesNewPmax[2] = d1[36 + 64 * i];
                    coeffTwoBytesNewPmax[3] = d1[37 + 64 * i];

                    coeffTwoBytesOldUmin[0] = d1[38 + 64 * i];
                    coeffTwoBytesOldUmin[1] = d1[39 + 64 * i];
                    coeffTwoBytesOldUmin[2] = d1[40 + 64 * i];
                    coeffTwoBytesOldUmin[3] = d1[41 + 64 * i];

                    coeffTwoBytesNewUmin[0] = d1[42 + 64 * i];
                    coeffTwoBytesNewUmin[1] = d1[43 + 64 * i];
                    coeffTwoBytesNewUmin[2] = d1[44 + 64 * i];
                    coeffTwoBytesNewUmin[3] = d1[45 + 64 * i];

                    coeffTwoBytesOldUmax[0] = d1[46 + 64 * i];
                    coeffTwoBytesOldUmax[1] = d1[47 + 64 * i];
                    coeffTwoBytesOldUmax[2] = d1[48 + 64 * i];
                    coeffTwoBytesOldUmax[3] = d1[49 + 64 * i];

                    coeffTwoBytesNewUmax[0] = d1[50 + 64 * i];
                    coeffTwoBytesNewUmax[1] = d1[51 + 64 * i];
                    coeffTwoBytesNewUmax[2] = d1[52 + 64 * i];
                    coeffTwoBytesNewUmax[3] = d1[53 + 64 * i];

                    /////////////////////////////////////////

                    coeffSpeedBytesOld[0] = d1[54 + 64 * i];
                    coeffSpeedBytesOld[1] = d1[55 + 64 * i];
                    coeffSpeedBytesOld[2] = d1[56 + 64 * i];
                    coeffSpeedBytesOld[3] = d1[57 + 64 * i];

                    coeffSpeedBytesNew[0] = d1[58 + 64 * i];
                    coeffSpeedBytesNew[1] = d1[59 + 64 * i];
                    coeffSpeedBytesNew[2] = d1[60 + 64 * i];
                    coeffSpeedBytesNew[3] = d1[61 + 64 * i];

                    Date dateTimeOld = getDateTime(picDateTimeLogOne);

                    double coeffOneOldPmax = toDouble(coeffOneBytesOldPmax);
                    double coeffOneOldUmin = toDouble(coeffOneBytesOldUmin);
                    double coeffOneOldUmax = toDouble(coeffOneBytesOldUmax);

                    double coeffOneNewPmax = toDouble(coeffOneBytesNewPmax);
                    double coeffOneNewUmin = toDouble(coeffOneBytesNewUmin);
                    double coeffOneNewUmax = toDouble(coeffOneBytesNewUmax);

                    double coeffTwoOldPmax = toDouble(coeffTwoBytesOldPmax);
                    double coeffTwoOldUmin = toDouble(coeffTwoBytesOldUmin);
                    double coeffTwoOldUmax = toDouble(coeffTwoBytesOldUmax);

                    double coeffTwoNewPmax = toDouble(coeffTwoBytesNewPmax);
                    double coeffTwoNewUmin = toDouble(coeffTwoBytesNewUmin);
                    double coeffTwoNewUmax = toDouble(coeffTwoBytesNewUmax);

                    double coeffSpeedOld = toDouble(coeffSpeedBytesOld);
                    double coeffSpeedNew = toDouble(coeffSpeedBytesNew);



                    parentFrame.textAreaLogCoeffs.append("Час запису: \t" + dateFormat.format(dateTimeOld) +
                            "\n \t P1max вихідний   " + df.format(coeffOneOldPmax) + "\t \t P1max новий   " + df.format(coeffOneNewPmax) +
                            "\n \t U1min вихідний   " + df.format(coeffOneOldUmin) + "\t \t U1min новий   " + df.format(coeffOneNewUmin) +
                            "\n \t U1max вихідний   " + df.format(coeffOneOldUmax) + "\t \t U1max новий   " + df.format(coeffOneNewUmax) + "\n" +

                            "\n \t P2max вихідний   " + df.format(coeffTwoOldPmax) + "\t \t P2max новий   " + df.format(coeffTwoNewPmax) +
                            "\n \t U2min вихідний   " + df.format(coeffTwoOldUmin) + "\t \t U2min новий   " + df.format(coeffTwoNewUmin) +
                            "\n \t U2max вихідний   " + df.format(coeffTwoOldUmax) + "\t \t U2max новий   " + df.format(coeffTwoNewUmax) + "\n" +

                            "\n \t Швидкість вихідна   " + df.format(coeffSpeedOld) + "\t \t Швидкість нова   " + df.format(coeffSpeedNew) +
                            "\n______________________________________________________________________" +

                            " \n ");
                }
            }

            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій. \n" +
                        "Повторіть операцію зчитування");
                return false;
            }
            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean readWagonNumber(int[] commands, FormTwo parentFrame) {
        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
                return false;
            }
            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);


            if (!writeUsb(commands)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані до пристрою. \n" +
                        "Перевірте підключення і повторіть операцію.");
                return false;
            }

//            int memorySize = 17;
            int memorySize = 33;


            byte[] d1 = getWagon(memorySize);
            if (d1 == null) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо прочитати дані з пристрою.\n" +
                        "Повторно підключіть пристрій і повторіть зчитування.");
                return false;
            }

            byte[] carNoBytes = new byte[5];

            byte[] coeffOnePmaxBytes = new byte[8];
            byte[] coeffOneUminBytes = new byte[8];
            byte[] coeffOneUmaxBytes = new byte[8];

            byte[] coeffTwoPmaxBytes = new byte[8];
            byte[] coeffTwoUminBytes = new byte[8];
            byte[] coeffTwoUmaxBytes = new byte[8];

            byte[] coeffSpeedBytes = new byte[8];

//            int[] carNoInts = new int[2];
//            carNoInts[0] = bcd2dec(d1[0]);
//            carNoInts[1] = bcd2dec(d1[1]);
//
//            int carNo = carNoInts[0] * 100 + carNoInts[1];

            carNoBytes[0] = d1[0];
            carNoBytes[1] = d1[1];
            carNoBytes[2] = d1[2];
            carNoBytes[3] = d1[3];
            carNoBytes[4] = d1[4];

            if (carNoBytes[0] == 0) carNoBytes[0] = 48;
            if (carNoBytes[1] == 0) carNoBytes[1] = 48;
            if (carNoBytes[2] == 0) carNoBytes[2] = 48;
            if (carNoBytes[3] == 0) carNoBytes[3] = 48;

            //String carNo = Arrays.toString(carNoBytes);
            String carNo = new String(carNoBytes);

            System.out.println("Car no = " + carNo);

//            carNoBytes[0] = d1[0];
//            carNoBytes[1] = d1[1];
//
//            coeffOneBytes[0] = (byte)(d1[5] & 0xFF);
//            coeffOneBytes[1] = (byte)(d1[6] & 0xFF);
//
//            coeffOneBytes[0] = (byte)(63 & 0xFF);
//            coeffOneBytes[1] = (byte)(-16 & 0xFF);
//
//            coeffOneBytes[2] = (byte)(0 & 0xFF);
//            coeffOneBytes[3] = (byte)(0 & 0xFF);
//
//            System.out.println("Cast1 = " + (coeffOneBytes[0] & 0xFF));
//            System.out.println("Cast2 = " + (coeffOneBytes[1] & 0xFF));
//            System.out.println("Casted: " + toDouble(coeffOneBytes));

            coeffOnePmaxBytes[0] = d1[5];
            coeffOnePmaxBytes[1] = d1[6];
            coeffOnePmaxBytes[2] = d1[7];
            coeffOnePmaxBytes[3] = d1[8];

            coeffOneUminBytes[0] = d1[9];
            coeffOneUminBytes[1] = d1[10];
            coeffOneUminBytes[2] = d1[11];
            coeffOneUminBytes[3] = d1[12];

            coeffOneUmaxBytes[0] = d1[13];
            coeffOneUmaxBytes[1] = d1[14];
            coeffOneUmaxBytes[2] = d1[15];
            coeffOneUmaxBytes[3] = d1[16];

            coeffTwoPmaxBytes[0] = d1[17];
            coeffTwoPmaxBytes[1] = d1[18];
            coeffTwoPmaxBytes[2] = d1[19];
            coeffTwoPmaxBytes[3] = d1[20];

            coeffTwoUminBytes[0] = d1[21];
            coeffTwoUminBytes[1] = d1[22];
            coeffTwoUminBytes[2] = d1[23];
            coeffTwoUminBytes[3] = d1[24];

            coeffTwoUmaxBytes[0] = d1[25];
            coeffTwoUmaxBytes[1] = d1[26];
            coeffTwoUmaxBytes[2] = d1[27];
            coeffTwoUmaxBytes[3] = d1[28];

            coeffSpeedBytes[0] = d1[29];
            coeffSpeedBytes[1] = d1[30];
            coeffSpeedBytes[2] = d1[31];
            coeffSpeedBytes[3] = d1[32];


//            System.out.println("CoeffOneBytes = " + toDouble(coeffOneBytes));
//
//            int carNo = mergeTwoBytes(carNoBytes);
            double coeffOnePmax = toDouble(coeffOnePmaxBytes);
            double coeffOneUmin = toDouble(coeffOneUminBytes);
            double coeffOneUmax = toDouble(coeffOneUmaxBytes);

            double coeffTwoPmax = toDouble(coeffTwoPmaxBytes);
            double coeffTwoUmin = toDouble(coeffTwoUminBytes);
            double coeffTwoUmax = toDouble(coeffTwoUmaxBytes);

            double coeffSpeed = toDouble(coeffSpeedBytes);


//            parentFrame.textReadCoefOneUmax.setText(Double.toString(coeffOneUmax));
//            parentFrame.textReadCoefOneUmin.setText(Double.toString(coeffOneUmin));
//            parentFrame.textReadCoefOnePmax.setText(Double.toString(coeffOnePmax));
//
//            parentFrame.textReadCoefTwoUmax.setText(Double.toString(coeffTwoUmax));
//            parentFrame.textReadCoefTwoUmin.setText(Double.toString(coeffTwoUmin));
//            parentFrame.textReadCoefTwoPmax.setText(Double.toString(coeffTwoPmax));
//
//            parentFrame.coeffSpeedRead.setText(Double.toString(coeffSpeed));

            parentFrame.textReadCoefOneUmax.setText(df.format(coeffOneUmax));
            parentFrame.textReadCoefOneUmin.setText(df.format(coeffOneUmin));
            parentFrame.textReadCoefOnePmax.setText(df.format(coeffOnePmax));

            parentFrame.textReadCoefTwoUmax.setText(df.format(coeffTwoUmax));
            parentFrame.textReadCoefTwoUmin.setText(df.format(coeffTwoUmin));
            parentFrame.textReadCoefTwoPmax.setText(df.format(coeffTwoPmax));

            parentFrame.coeffSpeedRead.setText(df.format(coeffSpeed));


            parentFrame.carNoTwoText.setText(carNo);

//            parentFrame.carNoText.setText(carNo);

//            System.out.println(d1[0]);
//            System.out.println(d1[1]);
//            System.out.println(d1[2]);
//            System.out.println(d1[3]);

//            String.format("%x", DecimalToBCD(dayWeek)[0]);
//            System.out.println(String.format("%d", (int)(d1[0]) & 0xFF));
//            System.out.println(String.format("%d", (int)(d1[1]) & 0xFF));
//            System.out.println(String.format("%d", (int)(d1[2]) & 0xFF));
//            System.out.println(String.format("%d", (int)(d1[3]) & 0xFF));
//            System.out.println(String.format("%x", d1[4]));
//            System.out.println(String.format("%x", d1[5]));
//            System.out.println(String.format("%x", d1[6]));
//            System.out.println(String.format("%x", d1[7]));
//            System.out.println(String.format("%x", d1[8]));
//            System.out.println(String.format("%x", d1[9]));

            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій. \n" +
                        "Повторіть операцію зчитування");
                return false;
            }
            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean setCoefficients(int[] commands, FormTwo parentFrame) {
        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
                return false;
            }

            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);

            if (!writeUsbCoefficients(commands, parentFrame)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані до пристрою. \n" +
                        "Перевірте підключення і повторіть операцію.");
                return false;
            }

            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій. \n" +
                        "Повторіть операцію зчитування");
                return false;
            }
            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean readData(int[] commands, Frame frame, FormTwo parentFrm) {
//        viewProgressBarOne();
//        FormTwo.getInstance().readDeviceLbl.setText("TEXT");
//        FormTwo.getInstance().repaint();
//        formTwo.readDeviceLbl.setText("Some text");
        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
//                formTwo.setReadDeviceLbl("Помилка передачі даних");
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
//                formTwo.setReadDeviceLbl("Помилка передачі даних");
                return false;
            }

            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);

 //           if (myOutPipe == -1 && myInPipe == -1) return false;
            if (!writeUsb(commands)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані у пристрій.\n" +
                        "Повторіть операцію.");
                return false;
            }

//            int memorySize = 4194304;
            int memorySize = 12582912;
//            int memorySize = 4;
            parentFrm.progressBar2.setMaximum(memorySize);
            parentFrm.dpb.setMaximum(memorySize);

            byte[] d1 = getData(memorySize, frame, parentFrm);
            if (d1 == null) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо прочитати дані з пристрою.\n" +
                        "Повторно підключіть пристрій і повторіть зчитування.");
                return false;
            }

//            System.out.println(bcd2dec(d1[0]));
//            System.out.println(bcd2dec(d1[1]));
//            System.out.println(bcd2dec(d1[2]));
//            System.out.println(bcd2dec(d1[3]));
//            System.out.println(bcd2dec(d1[4]));
//            System.out.println(bcd2dec(d1[5]));


            //System.out.println(Arrays.toString(d1));

//            byte d1 = (byte) 0xFF;
//            d1 = (byte) (d1 >>> 1);
//            System.out.println(Integer.toBinaryString(d1));
//            boolean p1 = ((d1 & 0x01) != 0);
//            boolean p2 = ((d1 & 0x02) != 0);
//            boolean p3 = ((d1 & 0x04) != 0);
//            boolean p4 = ((d1 & 0x08) != 0);
//            boolean p5 = ((d1 & 0x10) != 0);
//            boolean p6 = ((d1 & 0x20) != 0);
//            boolean p7 = ((d1 & 0x40) != 0);
//            boolean p8 = ((d1 & 0x80) != 0);
//
//            System.out.println(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7 + " " + p8);


            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій.");
//                formTwo.setReadDeviceLbl("Помилка передачі даних");
                return false;
            }

//            viewProgressbar();


            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean openRT() {
//        byte[] d1;
        int[] commands = new int[2];
        commands[0] = 0x77;
        commands[1] = 0x00;

        try {
            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount(vid_pid_norm));
            if (lib.MPUSBGetDeviceCount(vid_pid_norm) < 1) {
//                formTwo.setReadDeviceLbl("Помилка передачі даних");
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Не виявлено підключених пристроїв.\n" +
                        "Підключіть пристрій і повторіть операцію.");
                return false;
            }

            int selection = 0; // Selects connected device. 0 - we have only one connected device
            myOutPipe = lib.MPUSBOpen(selection, vid_pid_norm, out_pipe, 0, 0);
            myInPipe = lib.MPUSBOpen(selection, vid_pid_norm, in_pipe, 1, 0);

            if (myInPipe <= 0 || myOutPipe <= 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо відкрити пристрій.\n" +
                        "Перевірте підключення.");
//                formTwo.setReadDeviceLbl("Помилка передачі даних");
                return false;
            }

            System.out.println("MyInPipe = " + myInPipe);
            System.out.println("MyOutPipe = " + myOutPipe);

            //           if (myOutPipe == -1 && myInPipe == -1) return false;
            if (!writeUsb(commands)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо записати дані у пристрій.\n" +
                        "Повторіть операцію.");
                return false;
            }

//            int memorySize = 4194304;
//            int memorySize = 64;
//            int memorySize = 4;
//            parentFrm.progressBar2.setMaximum(memorySize);
//            parentFrm.dpb.setMaximum(memorySize);

//            byte[] d1 = getDataRT(memorySize);
//            if (d1 == null) {
//                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо прочитати дані з пристрою.\n" +
//                        "Повторно підключіть пристрій і повторіть зчитування.");
//                return false;
//            }


//            System.out.println(bcd2dec(d1[0]));
//            System.out.println(bcd2dec(d1[1]));
//            System.out.println(bcd2dec(d1[2]));
//            System.out.println(bcd2dec(d1[3]));
//            System.out.println(bcd2dec(d1[4]));
//            System.out.println(bcd2dec(d1[5]));


            //System.out.println(Arrays.toString(d1));

//            byte d1 = (byte) 0xFF;
//            d1 = (byte) (d1 >>> 1);
//            System.out.println(Integer.toBinaryString(d1));
//            boolean p1 = ((d1 & 0x01) != 0);
//            boolean p2 = ((d1 & 0x02) != 0);
//            boolean p3 = ((d1 & 0x04) != 0);
//            boolean p4 = ((d1 & 0x08) != 0);
//            boolean p5 = ((d1 & 0x10) != 0);
//            boolean p6 = ((d1 & 0x20) != 0);
//            boolean p7 = ((d1 & 0x40) != 0);
//            boolean p8 = ((d1 & 0x80) != 0);
//
//            System.out.println(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7 + " " + p8);

//            boolean myInPipeClose;
//            boolean myOutPipeClose;
//            myInPipeClose = lib.MPUSBClose(myInPipe);
//            myOutPipeClose = lib.MPUSBClose(myOutPipe);
//
//            if (!myInPipeClose || !myOutPipeClose) {
//                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Couldn't close device properly");
//                return false;
//            }
//
//            System.out.println(myInPipeClose);
//            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean closeRT() {
        try{
            boolean myInPipeClose;
            boolean myOutPipeClose;
            myInPipeClose = lib.MPUSBClose(myInPipe);
            myOutPipeClose = lib.MPUSBClose(myOutPipe);

            if (!myInPipeClose || !myOutPipeClose) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо коректно закрити пристрій.");
    //                formTwo.setReadDeviceLbl("Помилка передачі даних");
                return false;
            }

            System.out.println(myInPipeClose);
            System.out.println(myOutPipeClose);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public byte[] readRT(){
        byte[] d1;
        int memorySize = 64;
        d1 = getDataRT(memorySize);
        if (d1 == null) {
//            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Неможливо прочитати дані з пристрою.\n" +
//                    "Повторно підключіть пристрій і повторіть зчитування.");
            return new byte[]{-1, -1, -1};

        }

        byte[] speed = new byte[4];
        speed[0] = d1[5];
        speed[1] = d1[6];
        speed[2] = d1[7];
        speed[3] = d1[8];

        double speedDouble = 16000000.0 / (double)mergeFourBytes(speed);

//        System.out.println("speed = " + speedDouble);

//        System.out.println("Digs1 = " + d1[0]);
//        System.out.println("Digs2 = " + d1[1]);
//        System.out.println("Digs3 = " + d1[2]);
//        System.out.println("Digs4 = " + d1[3]);
//        System.out.println("Digs5 = " + d1[4]);

        String s1 = String.format("%8s", Integer.toBinaryString(d1[0] & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(d1[1] & 0xFF)).replace(' ', '0');
        String s3 = String.format("%8s", Integer.toBinaryString(d1[2] & 0xFF)).replace(' ', '0');
        String s4 = String.format("%8s", Integer.toBinaryString(d1[3] & 0xFF)).replace(' ', '0');
        String s5 = String.format("%8s", Integer.toBinaryString(d1[4] & 0xFF)).replace(' ', '0');

//        System.out.print(s1);
//        System.out.print(s2);
//        System.out.print(s3);
//        System.out.print(s4);
//        System.out.println(s5);

        byte[] digits = new byte[5];

        digits[0] = d1[0];
        digits[1] = d1[1];
        digits[2] = d1[2];
        digits[3] = d1[3];
        digits[4] = d1[4];


//        Long digitals = mergeFiveBytes(digits);
//        String s11 = String.format("%8s", Long.toBinaryString(digitals & 0xFF)).replace(' ', '0');
//        System.out.println(s11);

//        System.out.println(d1[0]);
//        System.out.println(d1[1]);
//        System.out.println(d1[2]);
//        System.out.println(d1[3]);
//        System.out.println(d1[4]);
//        System.out.println(d1[5]);

//        System.out.println(bcd2dec(d1[0]));
//        System.out.println(bcd2dec(d1[1]));
//        System.out.println(bcd2dec(d1[2]));
//        System.out.println(bcd2dec(d1[3]));
//        System.out.println(bcd2dec(d1[4]));
//        System.out.println(bcd2dec(d1[5]));


        //System.out.println(Arrays.toString(d1));

//            byte d1 = (byte) 0xFF;
//            d1 = (byte) (d1 >>> 1);
//            System.out.println(Integer.toBinaryString(d1));
//            boolean p1 = ((d1 & 0x01) != 0);
//            boolean p2 = ((d1 & 0x02) != 0);
//            boolean p3 = ((d1 & 0x04) != 0);
//            boolean p4 = ((d1 & 0x08) != 0);
//            boolean p5 = ((d1 & 0x10) != 0);
//            boolean p6 = ((d1 & 0x20) != 0);
//            boolean p7 = ((d1 & 0x40) != 0);
//            boolean p8 = ((d1 & 0x80) != 0);
//
//            System.out.println(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " " + p6 + " " + p7 + " " + p8);


        return d1;
    }


    public static byte[] DecimalToBCD(long num) {
        int digits = 0;

        long temp = num;
        while (temp != 0) {
            digits++;
            temp /= 10;
        }

        int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;

        byte bcd[] = new byte[byteLen];

        for (int i = 0; i < digits; i++) {
            byte tmp = (byte) (num % 10);

            if (i % 2 == 0) {
                bcd[i / 2] = tmp;
            } else {
                bcd[i / 2] |= (byte) (tmp << 4);
            }

            num /= 10;
        }

        for (int i = 0; i < byteLen / 2; i++) {
            byte tmp = bcd[i];
            bcd[i] = bcd[byteLen - i - 1];
            bcd[byteLen - i - 1] = tmp;
        }

        return bcd;
    }


    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static double toDouble(byte[] bytes) {
        double result;
        try {
            result = ByteBuffer.wrap(bytes).getDouble();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SOme exception");
            return 0.0;
        }
        return result;
//        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static int mergeTwoBytes(byte[] bytes) {
        return (int)(((long)bytes[0] << 8)  | ((long)bytes[1]) & 0xff );
    }

    public static long mergeFourBytes(byte[] bytes) {
        return (long)((((long)(bytes[0] & 0xffL) << 24)) | ((long)(bytes[1] & 0xffL)) << 16 | ((long)(bytes[2] & 0xffL)) << 8 | ((long)(bytes[3] & 0xffL)));
    }

    public static long mergeFiveBytes(byte[] bytes) {
        return (long)((((long)(bytes[0] & 0xffL) << 32))|(((long)(bytes[0] & 0xffL) << 24)) | ((long)(bytes[1] & 0xffL)) << 16 | ((long)(bytes[2] & 0xffL)) << 8 | ((long)(bytes[3] & 0xffL)));
    }



//    public static void viewProgressbar() {
//
//        JFrame parentFrame = new JFrame();
//        parentFrame.setSize(500, 150);
//        JLabel jl = new JLabel();
//        jl.setText("Count : 0");
//
//        parentFrame.add(BorderLayout.CENTER, jl);
//        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        parentFrame.setVisible(false);
//
//
//
//        final JDialog dlg = new JDialog(parentFrame, "Progress Dialog", true);
////        final JDialog dlg = new JDialog();
//        JProgressBar dpb = new JProgressBar(0, 50);
//        dlg.add(BorderLayout.CENTER, dpb);
//        dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
//        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        dlg.setSize(300, 75);
//        dlg.setLocationRelativeTo(parentFrame);
//
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                dlg.setVisible(true);
//            }
//        });
//        t.start();
//        for (int i = 0; i <= 50; i++) {
//            //jl.setText("Count : " + i);
//            dpb.setValue(i);
//            if(dpb.getValue() == 50){
//                dlg.setVisible(true);
////                System.exit(0);
//
//            }
//            try {
//                Thread.sleep(25);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        dlg.setVisible(true);
//
//
//    }

//    public static void viewProgressBarOne() {
//
//        final JDialog dlgProgress = new JDialog(FormTwo.getInstance(), "Please wait...", true);//true means that the dialog created is modal
//        JLabel lblStatus = new JLabel("Working..."); // this is just a label in which you can indicate the state of the processing
//
//        JProgressBar pbProgress = new JProgressBar(0, 100);
//        pbProgress.setIndeterminate(true); //we'll use an indeterminate progress bar
//
//        dlgProgress.add(BorderLayout.NORTH, lblStatus);
////        dlgProgress.add(BorderLayout.CENTER, dpb);
//        dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // prevent the user from closing the dialog
//        dlgProgress.setSize(300, 90);
//
//        SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
////                longProcessingTask();
//                return null;
//            }
//
//            @Override
//            protected void done() {
//                dlgProgress.dispose();//close the modal dialog
//            }
//        };
//
//        sw.execute(); // this will start the processing on a separate thread
//        dlgProgress.setVisible(true); //this will block user input as long as the processing task is working
//
//
//    }


    public static void main(String[] args) {
//        readData();
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int minute = cal.get(Calendar.MINUTE);
//        int sec = cal.get(Calendar.SECOND);
//        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
//
//        System.out.println(dayWeek);
//        System.out.println(month);
//        System.out.println(day);
//        System.out.println(hour);

//        System.out.println(String.format("%x", DecimalToBCD(year)[1]));
//        System.out.println(String.format("%x", DecimalToBCD(month + 1)[0]));
//        System.out.println(String.format("%x", DecimalToBCD(day)[0]));
//        System.out.println(String.format("%x", DecimalToBCD(hour)[0]));
//        System.out.println(String.format("%x", DecimalToBCD(minute)[0]));
//        System.out.println(String.format("%x", DecimalToBCD(sec)[0]));
//        System.out.println(String.format("%x", DecimalToBCD(dayWeek)[0]));

    }

}
