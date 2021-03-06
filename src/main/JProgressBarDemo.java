package main;

/**
 * Created by DM on 21.11.2016.
 */

import java.awt.BorderLayout;
        import java.awt.Container;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.concurrent.TimeUnit;
        import java.util.concurrent.atomic.AtomicBoolean;
        import java.util.concurrent.locks.LockSupport;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JProgressBar;

/**
 *
 * @author adyliu(imxylz@gmail.com)
 * @since 2012-12-28
 */
public class JProgressBarDemo {

    public static void main(String[] args) {
        final JProgressBar pbFile = new JProgressBar();
        pbFile.setValue(0);
        pbFile.setMaximum(100);
        pbFile.setStringPainted(true);
        pbFile.setBorder(BorderFactory.createTitledBorder("Download file"));

        JFrame theFrame = new JFrame("ProgressBar Demo");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = theFrame.getContentPane();
        contentPane.add(pbFile, BorderLayout.SOUTH);
        final JButton btnDownload = new JButton("Download");
        contentPane.add(btnDownload);
        final AtomicBoolean running = new AtomicBoolean(false);
        btnDownload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                running.set(!running.get());
                btnDownload.setText(running.get() ? "Pause" : "Continue");
                if (running.get()) {
                    new Thread() {
                        public void run() {
                            //download file in a thread
                            int v = 0;
                            while (running.get() && v < pbFile.getMaximum()) {
                                pbFile.setValue(++v);
                                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
                            }
                        }
                    }.start();
                }
            }
        });
        theFrame.setSize(300, 150);
        theFrame.setLocationRelativeTo(null);
        theFrame.setVisible(true);
    }

}
