package main;

import com.sun.jna.Library;
import com.sun.jna.Native;

import java.math.BigInteger;
import java.util.Arrays;


/**
 * Created by DM on 18.10.2016.
 */
public class MpusbapiWrapper {
    public interface Mpusbapi extends Library {
        public int MPUSBGetDLLVersion();
        public long MPUSBGetDeviceCount(String pVID_PID);
        public Integer MPUSBOpen(int instance,
                             String pVID_PID,
                             String pEP,
                             long dwDir,
                             long dwReserved);
        public int MPUSBRead(int handle,
                             byte[] pData,
                             long dwLen,
                             long pLength,
                             long dwMilliseconds);

        public int MPUSBWrite(int handle,
                              byte[] pData,
                              long dwLen,
                              long pLength,
                              long dwMilliseconds);

        public Boolean MPUSBClose(Long handle);
        //public


    }

    public static void main(String[] args) {
        try {
            Mpusbapi lib = (Mpusbapi) Native.loadLibrary("mpusbapi", Mpusbapi.class);

            System.out.println(lib.MPUSBGetDLLVersion());
            System.out.println(lib.MPUSBGetDeviceCount("vid_04D8&pid_000c"));
            int handle = (lib.MPUSBOpen(0, "vid_04D8&pid_000c", "\\MCHP_EP1", 0, 0));
            System.out.println(Integer.toHexString(handle));
            //System.out.println(handle);

            //System.out.println(lib.MPUSBClose(handle));
            //long hadle2 = lib.MPUSBOpen(0, "vid_04D8&pid_000C", "\\\\MCHP_")

            int n = 20;
            byte[] temp = new byte[n];
            int res = lib.MPUSBRead(handle, temp, n, 0, 100);
            System.out.println(Arrays.toString(temp));

//            System.out.println(lib.MPUSBRead());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
