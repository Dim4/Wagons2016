package main;

import jPicUsb.*;

/**
 * Created by DM on 21.10.2016.
 */
public class JPicUsbExample {
    public JPicUsbExample() {
//        try {
//            iface.load();
//            iface.set_vidpid("vid_04d&pid_000c");
//            iface.Open(0, "vid_04D8&pid_000c", "\\MCHP_EP1", 0, 0);
//            System.out.println(Integer.toString(iface.GetDeviceCount("vid_04d&pid_000c")));
//            System.out.println("Some text for test");
//        } catch (Exception e) {}

        //initComponents();
    }

    public static void main(String[] args) {
        //JPicUsbExample jPic = new JPicUsbExample();

        try {
            iface.load();
            System.in.read();
            iface.set_vidpid("vid_04d&pid_000c");
            iface.Open(0, "vid_04D8&pid_000c", "\\MCHP_EP1", 0, 0);
            System.out.println(Integer.toString(iface.GetDeviceCount("vid_04d&pid_000c")));
            System.out.println("Some text for test");
        } catch (Exception e) {}

    }
}
