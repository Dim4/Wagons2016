package main;

/**
 * Created by DM on 18.10.2016.
 */
// package jfreecharts;

/**
 *
 * @author sshankar
 */

         import java.awt.BasicStroke;
         import java.awt.BorderLayout;
         import java.awt.Color;
         import java.awt.event.ActionEvent;
         import java.awt.event.ActionListener;
         import java.awt.Stroke;
         import java.io.File;
         import java.sql.ResultSet;
         import java.sql.Statement;
         import java.text.SimpleDateFormat;
         import javax.swing.JButton;
         import javax.swing.JFileChooser;
         import javax.swing.JFrame;
         import javax.swing.JOptionPane;
         import javax.swing.JPanel;
         import org.jfree.chart.axis.DateAxis;
         import org.jfree.chart.axis.DateTickMarkPosition;
         import org.jfree.chart.axis.NumberAxis;
         import org.jfree.chart.ChartFactory;
         import org.jfree.chart.ChartPanel;
         import org.jfree.chart.ChartUtilities;
         import org.jfree.chart.JFreeChart;
         import org.jfree.chart.plot.XYPlot;
         import org.jfree.chart.renderer.xy.XYItemRenderer;
         import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
         import org.jfree.data.category.DefaultCategoryDataset;
         import org.jfree.data.time.Second;
         import org.jfree.data.time.TimeSeries;
         import org.jfree.data.time.TimeSeriesCollection;
         import org.jfree.data.xy.XYDataset;
         import org.jfree.ui.ApplicationFrame;
         import org.jfree.ui.RefineryUtilities;

/**
 * A demo.
 *
 */

public class TimeSeriesChart extends ApplicationFrame {
    private static DatabaseOperations databaseOperations = new DatabaseOperations();


    /**
     * A demo.
     *
     * @param title  the frame title.
     */
    public TimeSeriesChart(final String title) throws Exception {

        super(title);
        //final XYDataset dataset = createDataset();
 /*       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        databaseOperations.connectDB();
        Statement stmt = databaseOperations.dbConnection.createStatement();
        try {
            ResultSet querySet = stmt.executeQuery("select WID, speed from app.MAINTABLE");
            while (querySet.next()) {
                String wid = querySet.getString(0);
                float speed = querySet.getFloat(1);
                dataset.addValue(speed, "wid", wid);
            }
            final JFreeChart chart = createChart(dataset);

        }

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        chartPanel.setMouseZoomable(true, false);
        this.add(chartPanel, BorderLayout.CENTER);

        JPanel customPanel = new JPanel();
        JButton saveButton = new JButton("Save Graph as Image");
*/
//        saveButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try{
//                    //Code to display save dialog box
//                    String graph_file_name = "";
//                    final JFrame frame = new JFrame();
//                    JFileChooser chooser = new JFileChooser();
//
//                    //Code to select files of .jpg extention only
//                    ExampleFileFilter filter = new ExampleFileFilter();
//                    filter.addExtension("jpg");
//                    filter.setDescription("JPEG image");
//                    chooser.setFileFilter(filter);
//                    int choice = 0;
//                    do{
//                        choice = chooser.showSaveDialog(frame);
//                    }while(choice != JFileChooser.APPROVE_OPTION);
//
//                    graph_file_name = chooser.getSelectedFile().getAbsolutePath();
//
//                    //code to find if extension is already given with file name or not
//                    String fileName = graph_file_name;
//                    String ext      = "";
//
//                    int mid= fileName.lastIndexOf(".");
//                    if(mid > 0){
//                        ext   = fileName.substring(mid+1,fileName.length());
//                    }
//
//                    if(!(ext != null && !ext.trim().isEmpty() && ext.length() != 0)) {
//                        graph_file_name = graph_file_name+".jpg";
//                    }
//
//                    // Save as JPEG
//                    ChartUtilities.saveChartAsJPEG(new File(graph_file_name), chart, 800, 600);
//                    JOptionPane.showMessageDialog(null,"Image Saved Successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
//
//                }catch(Exception ex){
//                    JOptionPane.showMessageDialog(null,"Please try again", "Information", JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
//        });

//        customPanel.add(saveButton);
//        this.add(customPanel, BorderLayout.SOUTH);

    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Sample Chart",
                "Date",
                "Value",
                dataset,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.LIGHT_GRAY);//light gray

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(false);

        final XYItemRenderer xyitemrenderer  = plot.getRenderer();
        if (xyitemrenderer  instanceof XYLineAndShapeRenderer) {
            final XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyitemrenderer;
            renderer.setBaseShapesFilled(true);
            renderer.setBaseShapesVisible(true);
            renderer.setShapesVisible( true );
            renderer.setDrawOutlines( true );

            //sets the joint level size means the dot size which join two points on a graph 2f,3f
            Stroke stroke = new BasicStroke(
                    3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
            renderer.setBaseOutlineStroke(stroke);

        }


        final DateAxis xaxis = (DateAxis) plot.getDomainAxis();
        //Change domain axis lable by 180/4=45 degree
        //axis.setLabelAngle(Math.PI / 4.0);

        //Sets the Tick Labels means domain value labels by 90 degree
        xaxis.setVerticalTickLabels(true);

        //Try to play with it. It will work only in case of  horizontal tick mark
        xaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);

        //set date format
        xaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss MM-dd-yy"));

        final NumberAxis yaxis = (NumberAxis) plot.getRangeAxis();
        //To set range values integer only. Default are float
        yaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @return the dataset.
     */
    private XYDataset createDataset() {

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.setDomainIsPointsInTime(true);

        final TimeSeries s1 = new TimeSeries("Series 1", Second.class);
        s1.add(new Second(0, 0, 0, 7, 7, 2012), 12);
        s1.add(new Second(0, 30, 2, 7, 7, 2012), 31);
        s1.add(new Second(0, 15, 12, 7, 7, 2012), 80);

        final TimeSeries s2 = new TimeSeries("Series 2", Second.class);
        s2.add(new Second(0, 20, 3, 7, 7, 2012), 50);
        s2.add(new Second(0, 40, 6, 7, 7, 2012), 63);
        s2.add(new Second(0, 25, 12, 7, 7, 2012), 77);

        final TimeSeries s3 = new TimeSeries("Series 2", Second.class);
        s3.add(new Second(0, 10, 1, 7, 7, 2012), 52);
        s3.add(new Second(0, 20, 12, 7, 7, 2012), 66);
        s3.add(new Second(0, 35, 9, 7, 7, 2012), 79);

        dataset.addSeries(s1);
        dataset.addSeries(s2);
        dataset.addSeries(s3);

        return dataset;
    }


    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

//        final TimeSeriesChart demo = new TimeSeriesChart("Time Series Chart");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
    }

}