
package week_9_series;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Mark Drozd
 */
public class SeriesFrame extends JFrame {

    private Series series;
    private JLabel sumLabel;
    private ButtonGroup radioButtonGroup;
    private ButtonGroup chooseFileGroup;
    private JRadioButton output1Check;
    private JRadioButton output2Check;
    private JRadioButton linearCheck;
    private JRadioButton expCheck;
    private JSlider slider;
    private JButton clearFileButton;
    
    private JSlider sliderLinearTemplate;
    private JPanel sliderPanel;
    private JFormattedTextField fieldForNumberOfElements;
    private JFormattedTextField fieldForFirstElement;
    private JTextArea fieldForDif;
    
    
    private JTextArea field;
    
    private int curNumberOfElements;
    private double curDif;
    private double curFirst;
    
    
    private File outputFile1;
    private File outputFile2;
    private File curOutputFile;
    
    
    public SeriesFrame()
    {
        super("Series");
        GridBagLayout layout= new GridBagLayout();
        setLayout(layout);
        
        curNumberOfElements=5;
        curDif=2;
        curFirst=-1;
        
        setBounds(100,100,900,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        sliderPanel=new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sliderLinearTemplate=new JSlider(-50,50,0);
        sliderLinearTemplate.setMajorTickSpacing(10);
        sliderLinearTemplate.setPaintTicks(true);
        sliderLinearTemplate.setPaintLabels(true);
        slider=sliderLinearTemplate;
        addSlider(sliderLinearTemplate, "Difference");
        
        
        
        series=new Linear(curFirst, curDif, curNumberOfElements);
        sumLabel=new JLabel("Sum: "+series.sum());
        Font font = new Font("Hobbiton", Font.BOLD,16);   
        sumLabel.setFont(font);
        JPanel radioButtonPanel = new JPanel();
        radioButtonGroup=new ButtonGroup();
        linearCheck=new JRadioButton("Linear", true);
        expCheck=new JRadioButton("Exponential");
        radioButtonGroup.add(linearCheck);
        radioButtonGroup.add(expCheck);
        linearCheck.addItemListener(radioButtonListener);
        expCheck.addItemListener(radioButtonListener);
        radioButtonPanel.add(linearCheck);
        radioButtonPanel.add(expCheck);
        
        chooseFileGroup=new ButtonGroup();
        output1Check=new JRadioButton("output_1.txt", true);
        output2Check=new JRadioButton("output_2.txt", false);
        outputFile1=new File("output_1.txt");
        outputFile2=new File("output_2.txt");
        font = new Font("Morris Roman", Font.PLAIN,16);  
        output1Check.setFont(font);
        output2Check.setFont(font);
        curOutputFile=outputFile1;
        chooseFileGroup.add(output1Check);
        chooseFileGroup.add(output2Check);
        output1Check.addItemListener(chooseFileListener);
        output2Check.addItemListener(chooseFileListener);
        JPanel chooseFilePanel=new JPanel();
        chooseFilePanel.add(output1Check);
        chooseFilePanel.add(output2Check);
        linearCheck.setFont(font);
        expCheck.setFont(font);
        
        
        
        clearFileButton=new JButton("clear File");
        clearFileButton.setFont(font);
        clearFileButton.addActionListener((ActionEvent e) -> {
            try 
            {
                PrintWriter pw = new PrintWriter(curOutputFile);
                pw.close();
            }
            catch(FileNotFoundException exc)            
            {
            }
        });
        
        
        
        
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumIntegerDigits(3);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        fieldForNumberOfElements = new JFormattedTextField(formatter);
        fieldForNumberOfElements.setValue(curNumberOfElements);
        fieldForNumberOfElements.addKeyListener(new KeyAdapter()
        {
            String value;

            @Override
            public void keyPressed(KeyEvent e) {
                value=fieldForNumberOfElements.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
               if (value.compareTo(fieldForNumberOfElements.getText())!=0)
                   curNumberOfElements=Integer.parseInt(fieldForNumberOfElements.getText());
                updateFields();
            }
        });
        fieldForNumberOfElements.setColumns(3);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Number of elements: "));
        panel.add(fieldForNumberOfElements);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        
        
        
        
        format =DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(new Double(-10000000));
        formatter.setMaximum(new Double(10000000));
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
       
        fieldForFirstElement= new JFormattedTextField(formatter);
        fieldForFirstElement.addKeyListener(new KeyListener()
        {
            
            String value;

            @Override
            public void keyTyped(KeyEvent e) 
            {}

            @Override
            public void keyPressed(KeyEvent e) {
                value=fieldForFirstElement.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
               if (value.compareTo(fieldForFirstElement.getText())!=0)
                   try 
                   {
                       curFirst=Double.parseDouble(fieldForFirstElement.getText());
                   }
               catch(NumberFormatException exc)
               {
                   try
                   {
                         NumberFormat nf=NumberFormat.getInstance();
                         curFirst=nf.parse(fieldForFirstElement.getText()).doubleValue();
                   }
                   catch(ParseException ex)
                   {
                       
                   }
               }
                updateFields();
            }
        });
        fieldForFirstElement.setColumns(10);
        fieldForFirstElement.setValue(curFirst);
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("1st element: "));
        panel2.add(fieldForFirstElement);
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        
        
        
       
        fieldForDif=new JTextArea();
        fieldForDif.addKeyListener(new KeyListener()
        {
            
            String value;

            @Override
            public void keyTyped(KeyEvent e) 
            {}

            @Override
            public void keyPressed(KeyEvent e) {
                value=fieldForDif.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
               if (value.compareTo(fieldForDif.getText())!=0)
                   try 
                   {
                       curDif=Double.parseDouble(fieldForDif.getText());
                       updateFields();
                   }
                    catch(NumberFormatException exc)
                    {
                        fieldForDif.setText(((Double)curDif).toString());
                    }
            }
        });
        fieldForDif.setText(((Double)curDif).toString());
        
        
        
        
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Dif: "));
        panel3.add(fieldForDif);
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        field=new JTextArea(7,40);
        field.setLineWrap(true);
        field.setEditable(false);
        field.append(series.toString());
        JScrollPane scrollPane=new JScrollPane(field, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);
        add(radioButtonPanel, new GBC(0,0,1,1).setAnchor(GBC.WEST));
        add(scrollPane, new GBC(1,1,1,1).setAnchor(GBC.WEST));
        add(sliderPanel, new GBC(1,0,1,1).setAnchor(GBC.WEST));
        add(panel, new GBC(2,0,1,1).setAnchor(GBC.WEST));
        add(panel2, new GBC(2,1));
        add(panel3, new GBC(2,3).setAnchor(GBC.WEST));
        add(sumLabel, new GBC(0,1).setAnchor(GBC.WEST));
        add(chooseFilePanel, new GBC(0,5).setAnchor(GBC.WEST));
        add(clearFileButton, new GBC(1,5).setAnchor(GBC.WEST));
    }
    
    
    
   
    
    ItemListener radioButtonListener=new ItemListener()
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange()==ItemEvent.SELECTED)
                    {
                        if (linearCheck.isSelected())
                            series=new Linear(curFirst, curDif, curNumberOfElements);
                        else
                            series=new Exponential(curFirst, curDif, curNumberOfElements);
                        updateFields();
                    }
        }
    };
    
     ItemListener chooseFileListener=new ItemListener()
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange()==ItemEvent.SELECTED)
                    {
                        if (output1Check.isSelected())
                            curOutputFile=outputFile1;
                        else
                          curOutputFile=outputFile2;
                    }
        }
    };
    
    
    
    private void updateFields()
    {
        series.setFirstElement(curFirst);
        series.setNumberofElements(curNumberOfElements);
        series.setDif(curDif);
        fieldForDif.setText(((Double)curDif).toString());
        slider.setValue((int)curDif);
        if ((int)curDif>50 || (int)curDif<-50)
            slider.setForeground(Color.red);
        else 
             slider.setForeground(Color.black);      
        field.setText(series.toString());
        updateSum();
        try
        {
            series.toFile(curOutputFile);
        }
        catch(IOException exc)
        {
            
        }
    }
    
    private void updateSum()
    {
        Double sum=series.sum();
        String s=sum.toString();
        if (s.length()<20)
        {
                sumLabel.setText("Sum: "+series.sum());
                sumLabel.setForeground(Color.black);
        }
        else 
        {
            sumLabel.setText("Sum: LARGE");
            sumLabel.setForeground(Color.red);
        }
    }
    
    ChangeListener sliderListener = new ChangeListener()
    {
        @Override
        public void stateChanged(ChangeEvent event)
        {
            series.setDif(slider.getValue());
            updateSum();
            
            
            if (!slider.getValueIsAdjusting())
            {
                curDif=slider.getValue();
                updateFields();
            }
        }
        
    };
    
    
    private void addSlider(JSlider slider, String description)
    {
        slider.addChangeListener(sliderListener);
        JPanel panel=new JPanel();
        panel.add(new JLabel(description));
        panel.add(slider);
       
        sliderPanel.add(panel);
    }
    
    
    public static void main(String[] args) throws IOException 
    {
        
        SeriesFrame app=new SeriesFrame();
        app.pack();
        app.setVisible(true);
    }

}
