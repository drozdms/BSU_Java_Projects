import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeEvent;
import java.io.FileWriter;
import java.io.File;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mark Drozd
 */
public class FramePaint extends JFrame
{
    
    
    
    private JButton buttonColorChooser;
    private JPanel colorShowPanel;
    private JButton buttonOpenFile;
    private JButton buttonSaveAsImage;
    private JButton buttonOpenAsImage;
    private JSlider sliderStroke;
    
    public FramePaint()
    {
        super("My Paint Application");
        setLayout(null);
        setBounds(100,100,1000,600);
        PanelPaint panelPaint=new PanelPaint();
        panelPaint.setPreferredSize(new Dimension(getSize().width-50, getSize().height));
        JScrollPane scrollPane=new JScrollPane(panelPaint, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(5, 5, getSize().width-20, getSize().height*4/5);
        
        add(scrollPane, BorderLayout.CENTER);
        
        colorShowPanel=new JPanel();
         add(colorShowPanel);
        colorShowPanel.setBounds(5, getSize().height-100, 20, 20);
        colorShowPanel.setBackground(panelPaint.getCurrentColor());
        
        
        buttonColorChooser=new JButton("choose color");
        
       
        buttonColorChooser.addActionListener((ActionEvent e) -> {
            JColorChooser jcc=new JColorChooser();
            class ColorChosen implements ActionListener
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panelPaint.setCurrentColor(jcc.getColor());
                    colorShowPanel.setBackground(panelPaint.getCurrentColor());
                }
                
            }
            JDialog colorChooserDialog=JColorChooser.createDialog(null, "Choose a color", true, jcc, new ColorChosen(), null);
            colorChooserDialog.setVisible(true);
        });
        
        add(buttonColorChooser);
        buttonColorChooser.setBounds(5, getSize().height-80, 100, 30);
        
        
        
        
       
        
        buttonOpenFile=new JButton("open file");
        buttonOpenFile.addActionListener((ActionEvent e) -> 
        {
            
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_11_paint\\images");
           
            int rVal=jfc.showOpenDialog(FramePaint.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                File file = jfc.getSelectedFile();
                 ArrayList<Curve> listCurve=new ArrayList();
                 try
                 {
                     Scanner scanner = new Scanner(file);
                     
                     while (scanner.hasNext())
                     {
                       
                         Curve curCurve=new Curve();
                         if ("Color".equals(scanner.next()))
                             curCurve.color=new Color(scanner.nextInt());
                         if ("Stroke".equals(scanner.next()))
                             curCurve.stroke=scanner.nextInt();
                         while (scanner.hasNextInt())
                         {
                             int x=scanner.nextInt();
                             int y=scanner.nextInt();
                             curCurve.pointList.add(new Point(x,y));
                         }
                         
                         listCurve.add(curCurve);
                         
                         
                     }
                 } 
                 catch (FileNotFoundException ex) 
                 {
                     // WRONG
                     ex=null;
                     return;
                 }
                 
                 
                 panelPaint.updatePicture(listCurve);
                 
                 
            }
        });
        
        add(buttonOpenFile);
        buttonOpenFile.setBounds(610, getSize().height-90, 100, 30);
        
        
        
         buttonSaveAsImage=new JButton("save image");
         buttonSaveAsImage.addActionListener((ActionEvent e) -> {
              
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_11_paint\\images");
           
            int rVal=jfc.showSaveDialog(FramePaint.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    File outputfile = new File(jfc.getSelectedFile()+".png");
                    ImageIO.write(panelPaint.getImage(), "png", outputfile);
                }
                
                catch (IOException ex) {}
                
                
                try(FileWriter fw = new FileWriter(jfc.getSelectedFile()+".txt"); BufferedWriter out = new BufferedWriter(fw))
                {
                     ArrayList<String> picture=panelPaint.convertToText();
                     for (String i: picture)
                     {
                         out.write(i);
                         out.newLine();
                     }
                } 
                catch (Exception ex) {}
            }
        });
        
        add(buttonSaveAsImage);
        buttonSaveAsImage.setBounds(730, getSize().height-90, 100, 30);    
        
        JRadioButton button;
        
        buttonOpenAsImage=new JButton("open image");
         buttonOpenAsImage.addActionListener((ActionEvent e) -> {
              FileFilter imageFilter = new FileNameExtensionFilter(
                             "Image files", ImageIO.getReaderFileSuffixes());
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_11_paint\\images");
            jfc.setFileFilter(imageFilter);
            int rVal=jfc.showOpenDialog(FramePaint.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                BufferedImage img=null;
                try
                {
                    img=ImageIO.read(jfc.getSelectedFile());
                }
                
                 catch (IOException ex) {
                     ex=null;
                        return;
                    }
                
                panelPaint.setImage(img);
            }
        });
        
        add(buttonOpenAsImage);
        buttonOpenAsImage.setBounds(850, getSize().height-90, 100, 30); 
     
        sliderStroke=new JSlider(0,100,2);
        sliderStroke.setPreferredSize(new Dimension(200,40));
        sliderStroke.setMajorTickSpacing(20);
        sliderStroke.setPaintTicks(true);
        sliderStroke.setPaintLabels(true);
        sliderStroke.addChangeListener((ChangeEvent e) -> {
            if (!sliderStroke.getValueIsAdjusting())
                panelPaint.setCurrentStroke(sliderStroke.getValue());
        });
        
        
        JPanel sliderPanel=new JPanel();
        sliderPanel.add(new JLabel("set stroke"));
        sliderPanel.add(sliderStroke);
        add(sliderPanel);
        
        sliderPanel.setBounds(200, getSize().height-110, 200, 200);
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    
    
    
    
}
