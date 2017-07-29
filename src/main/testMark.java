package main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by DM on 25.10.2016.
 */
public class testMark {
    public static void main(String[] args) throws Exception {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream("f://test.txt"));
            System.out.println("Char: " + (char)is.read());
            System.out.println("Char: " + (char)is.read());
            System.out.println("Char: " + (char)is.read());
 //           System.out.println(is.markSupported());

            is.mark(0);
//            is.skip(1);
//            System.out.println(is.markSupported());

            System.out.println("Char: " + (char)is.read());
//            is.reset();
            System.out.println("Char: " + (char)is.read());
            System.out.println("Char: " + (char)is.read());
//            System.out.println(is.markSupported());

            if (is.markSupported()) {
                is.reset();
                System.out.println("Char: " + (char)is.read());
                System.out.println("Char: " + (char)is.read());
         //       System.out.println("Char: " + (char)is.read());
            }
        }catch (Exception e) {}
        finally {
            if (is != null) is.close();
        }
    }

}
