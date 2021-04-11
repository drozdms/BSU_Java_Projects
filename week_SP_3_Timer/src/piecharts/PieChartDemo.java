package piecharts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChartDemo extends ApplicationFrame 
{
    
    
   private static DefaultPieDataset dataset;
   
   public PieChartDemo( String title ) {
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Box office 2017",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
 
    //  PiePlot pl=(PiePlot)chart.getPlot();
    //  pl.setLabelGenerator(null);
      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(dataset); 
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
      
      
      int n=0;
      Scanner scanner;
       try
       {
           scanner=new Scanner(new File("input.txt"));
       } 
       catch (FileNotFoundException ex) 
       {
           JOptionPane.showMessageDialog(null, "Dataset not found!");
           return;
       }
      
     
       
       try
       {
           String str=scanner.nextLine();
           n=Integer.parseInt(str);
           if (n<=0)
               throw new NoSuchElementException();
       }
       catch(NumberFormatException | NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Amount of parameters is invalid!");
           return;
       }
       
         String[] strArr = new String[n];
       
       for (int i=0; i < n; ++i) {
           try
           {
              String str=scanner.nextLine();
               strArr[i]=str;
           }
           catch(NoSuchElementException e)
           {
               JOptionPane.showMessageDialog(null, "No values found!");
               return;
           }
       }
       
       long[] valArr=new long[n];
       dataset = new DefaultPieDataset( );
       for (int i=0; i<n; ++i)
       {
           try
           {
               String str=scanner.nextLine();
               valArr[i]=Long.parseLong(str);
               if (valArr[i]<0)
                   throw new NumberFormatException();
                dataset.setValue(strArr[i], valArr[i]);
           }
           catch(NumberFormatException | NoSuchElementException e)
           {
               JOptionPane.showMessageDialog(null, "Invalid box office value!");
               return;
           }
       }
       
       
      PieChartDemo demo = new PieChartDemo( "Box Office" );  
      demo.setSize( 560 , 367 );  
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
   }
}