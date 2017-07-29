package main;

/**
 * Created by DM on 17.01.2017.
 */
/* This example tutorial demonstrates how to draw a Pie chart in a PDF document using iText Java API, and JFreeChart Java API */
import java.io.*;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.data.general.DefaultPieDataset; /* We will use DefaultPieDataset to define the data for the Pie Chart */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
public class PDFPieChartExample {
    public static void main(String[] args){
        try {
                /* We will define the data for the Pie Chart Using the Code below */
                /* Declare dataset object using the code below */
            DefaultPieDataset myPiedataset = new DefaultPieDataset();
                /* Define Values for the Pie Chart - Programming Languages Percentage Difficulty */
            myPiedataset.setValue("Java", 12.9);
            myPiedataset.setValue("C++", 37.9);
            myPiedataset.setValue("C", 86.5);
            myPiedataset.setValue("VB", 80.5);
            myPiedataset.setValue("Shell Script", 19.5);
                /* With the dataset defined for Pie Chart, we can invoke a method in ChartFactory object to create Pie Chart and Return a JFreeChart object*/
                /* This method returns a JFreeChart object back to us */
                /* We specify the chart title, dataset, legend, tooltip and URLs in this method as input */
            JFreeChart PDFPieChart=ChartFactory.createPieChart("Programming - Pie Chart Example",myPiedataset,true,true,false);
                /* We have a Pie chart object, and now need to find a procedure to insert it into PDF using iText */
            int width=640; /* Width of our chart */
            int height=480; /* Height of our chart */
            Document PieChart=new Document(new Rectangle(width,height)); /* Create a New Document Object for PDF */
                /* Create PDF Writer Object that will physically write the PDF file to File Output Stream */
            PdfWriter writer=PdfWriter.getInstance(PieChart,new FileOutputStream("c:/Add_Pie_Chart_Using_JFreeChart.pdf"));
                /* Ready with document objects, open the document object to push contents */
            PieChart.open();
                /* Add some Metadata to identify document later */
            PieChart.addTitle("How to Add a Pie Chart to a PDF file using iText");
            PieChart.addAuthor("Thinktibits");
            PieChart.addKeywords("iText,PieChart,JFreeChart,PDF,Example Tutorial");
                /* Get Direct Content of the PDF document for writing */
            PdfContentByte Add_Chart_Content= writer.getDirectContent();
                /* Create a template using the PdfContent Byte object */
            PdfTemplate template_Chart_Holder=Add_Chart_Content.createTemplate(width,height);
                /* Create a 2D graphics object and Rectangle object as before to write on the template */
//            Graphics2D Graphics_Chart=template_Chart_Holder.createGraphics(width,height,new DefaultFontMapper());
            Graphics2D Graphics_Chart=new PdfGraphics2D(template_Chart_Holder, width, height);
//                    template_Chart_Holder.createGraphics(width,height,new DefaultFontMapper());
  //          new PdfGraphics2D(tp, width, height);
            Rectangle2D Chart_Region=new Rectangle2D.Double(0,0,540,380);
                /* Invoke the draw method passing the Graphics and Rectangle 2D object to draw the piechart */
            PDFPieChart.draw(Graphics_Chart,Chart_Region);
            Graphics_Chart.dispose();
                /* Add template to PdfContentByte and then to the PDF document */
            Add_Chart_Content.addTemplate(template_Chart_Holder,0,0);
                /* Close the Document, writer will create a beautiful Pie chart inside the PDF document */
            PieChart.close();
        }
        catch (Exception i)
        {
            System.out.println(i);
        }
    }
}
