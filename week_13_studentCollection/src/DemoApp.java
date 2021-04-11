import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
/**
 *
 * @author Mark Drozd
 */
public class DemoApp extends JFrame 
{
    private JList listCollectionDisplay;
     private JList listLosersDisplay;
    private JTextArea fieldToWrite;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JLabel label;
    private JMenuItem openFile;
    private JMenuItem openXMLFile;
    private JMenuItem saveXMLFile;
    private JButton buttonUpdateLosersInfo;
    private JButton addStudent;
    private StudentCollection studentCollection;
    public DemoApp()
    {
        super("Student Collection");
        setBounds(100,100,1000,500);
        setLayout(new BorderLayout());
        studentCollection=new StudentCollection();
        try
        {
        
            Scanner scanner = new Scanner (new File("students.txt"));
            scanStudentListFromFile(scanner, studentCollection);
        }
        
        catch (IOException exc)
        {  }
        
        Font font=new Font("Morris Roman", Font.PLAIN, 18);
        
        listCollectionDisplay=new JList(studentCollection.toArray());
        listLosersDisplay=new JList();
        listCollectionDisplay.setPreferredSize(new Dimension(280, this.getHeight()));
        listLosersDisplay.setPreferredSize(new Dimension(280, this.getHeight()));
        listCollectionDisplay.setFont(font);
        listLosersDisplay.setFont(font);
        add(listCollectionDisplay, BorderLayout.WEST);
        add(listLosersDisplay, BorderLayout.EAST);
        
        fieldToWrite=new JTextArea();
        fieldToWrite.setLineWrap(true);
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(6,1));
        panel.setPreferredSize(new Dimension(200, this.getHeight()));
        fieldToWrite.setPreferredSize(new Dimension(100,30));
        panel.add(fieldToWrite);
        
        add(panel, BorderLayout.CENTER);
        
        buttonUpdateLosersInfo=new JButton("search for losers");
        buttonUpdateLosersInfo.setPreferredSize(new Dimension(40,40));
        buttonUpdateLosersInfo.addActionListener((ActionEvent e) -> 
        {
            String info=fieldToWrite.getText();
            Scanner scanner = new Scanner(info);
            Integer term=0;
            ArrayList<String> subjects=new ArrayList();
            if (scanner.hasNextInt())
                term=scanner.nextInt();
            while (scanner.hasNext())
                subjects.add(scanner.next());
            
            Collections.sort(subjects, new StringComparator());
            ArrayList<String> listString=new ArrayList();
            
            StudentCollection losers=studentCollection.getSubjectLosers(term, subjects);
            StudentCollection.sort(losers);
            
            Iterator<String> i=subjects.iterator();
            while (i.hasNext())
                {
                        Iterator<Student> it=losers.iterator();
                        String str=i.next();
                        
                            while (it.hasNext())
                            {
                                Student student=it.next();
                                listString.add(str+" "+student.toString()+" " + student.getSubjectMark(term, str));
                            }
                }
            
            
            listLosersDisplay.setListData(listString.toArray());
            
            
        });
        
        panel.add(buttonUpdateLosersInfo);
        
        
        
        
        addStudent=new JButton("add student");
        addStudent.addActionListener((ActionEvent e)->
        {
            String info = (String)JOptionPane.showInputDialog(
                                        this,
                                        "Add student info:\n"
                                        + "number, last name, term,\n"
                                        + "subject, mark\n",
                                        "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        null,
                                        "ham");
            
            if (info==null)
                return;
             label.setText("");
            Scanner scanner = new Scanner(info);
           
            if (scanner.hasNext())
            {
                
                
                try
                {
                    Integer number=scanner.nextInt();
                    String lastName=scanner.next();
                    Integer term=scanner.nextInt();
                    String subject=scanner.next();
                    Integer mark=scanner.nextInt();
                    Student student = new Student(number, lastName);
                    student.addMark(term, subject, mark);
                    
                    studentCollection.add(student);
                    listCollectionDisplay.setListData(studentCollection.toArray());
                    buttonUpdateLosersInfo.doClick();
                    
                }
            
                catch(Exception exc)
                {
                    label.setText("Invalid input info");
                }
                
            }
            
            else 
                label.setText(("No input data found"));

            
            
        });
        
        panel.add(addStudent);
        
        
        label=new JLabel("");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.RED);
        panel.add(label, BorderLayout.CENTER);
        
        
        menuBar=new JMenuBar();
        menuFile=new JMenu("File...");
        openFile=new JMenuItem("Open TEXT...");
        openXMLFile=new JMenuItem("Open XML...");
        saveXMLFile=new JMenuItem("Save XML...");
        menuFile.add(openFile);
        menuFile.add(openXMLFile);
        menuFile.add(saveXMLFile);
        menuBar.add(menuFile);
        this.setJMenuBar(menuBar);
        
        openFile.addActionListener((ActionEvent e)->
        {
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_13_studentCollection\\");
            int rVal=jfc.showOpenDialog(DemoApp.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                File file = jfc.getSelectedFile();
                try
                {
                    label.setText("");
                    Scanner scanner = new Scanner (file);
                    scanStudentListFromFile(scanner, studentCollection);
                    
                }
        
                catch (IOException exc)
                {
                    label.setText("NO FILE FOUND");
                }
            
                 
                 
                listCollectionDisplay.setListData(studentCollection.toArray());
                buttonUpdateLosersInfo.doClick();
                   
            }
            
        });
        
        openXMLFile.addActionListener((ActionEvent e)->
        {
             JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_13_studentCollection\\");
            int rVal=jfc.showOpenDialog(DemoApp.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                File file = jfc.getSelectedFile();
                try
                {
                    label.setText("");
                    SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
                    SAXParser saxParser=saxParserFactory.newSAXParser();
                    MyHandler handler=new MyHandler();
                    saxParser.parse(file, handler);
                    if (handler.isInvalid())
                        label.setText("The XML file is inappropriate. The file was scanned only partially.");
                    studentCollection=handler.getCollection();
                    listCollectionDisplay.setListData(studentCollection.toArray());
                }
        
                 catch (ParserConfigurationException | SAXException | IOException ex) 
                 {
                     label.setText("Error parsing the file");
                 } 
            
                 buttonUpdateLosersInfo.doClick();
                   
            }
            
            
        });
        
        
        saveXMLFile.addActionListener((ActionEvent e)->
        {
             JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_13_studentCollection\\");
           
            int rVal=jfc.showSaveDialog(DemoApp.this);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                
                
                try(FileWriter fw = new FileWriter(jfc.getSelectedFile()+".xml"); BufferedWriter out = new BufferedWriter(fw))
                {
                    label.setText("");
                     ArrayList<String> text=studentCollection.toXML();
                     for (String i: text)
                     {
                         out.write(i);
                         out.newLine();
                     }
                } 
                catch (Exception ex) 
                {
                    label.setText("Error writing to file");
                }
                
            }
            
            
        });
        
        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
    }
    
    
    
    void scanStudentListFromFile(Scanner scanner, StudentCollection list)
    {
        
        
        list.clear();
        while (scanner.hasNext())
        {
            try
            {
                Integer number=scanner.nextInt();
                String surname=scanner.next();
                Integer term =scanner.nextInt();
                String subject = scanner.next();
                Integer mark=scanner.nextInt();
                Student student=new Student(number, surname);
                student.addMark(term, subject, mark);
                list.add(student);
                
            }
            
            catch(InputMismatchException e)
            {
                
                scanner.next();   // skip this token
                label.setText("The file is inappropriate. The file was scanned only partially.");
                // continue to scan
            }
            
            
            catch (NoSuchElementException exc)
            {
                // scanner.hasNext()=false
            }
            
        }
    }
    
    
    
    
    public static void main(String[] args) 
    {
        DemoApp app=new DemoApp();
        app.setVisible(true);
    }

}


