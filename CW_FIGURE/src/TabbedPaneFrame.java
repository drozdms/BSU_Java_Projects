
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


/**
 *
 * @author Mark Drozd
 */
public class TabbedPaneFrame extends JFrame 
{

    private ArrayList<Rectangle> list;
    
    private JTabbedPane tabbedPane;
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JMenuItem open;
    private JMenu dataMenu;
    private JMenuItem byArea;
    private JMenuItem points;
    private JMenuItem search;
    private PaneFirst paneFirst;
    private PaneSecond paneSecond;
    private PaneThird paneThird;
    
    
    public TabbedPaneFrame()
    {
        super("Tabbed Pane Frame");
        list=new ArrayList();
        setBounds(100,100,1000,600);
        
        
        try 
        {
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            SAXParser saxParser=saxParserFactory.newSAXParser();
            MyHandler handler=new MyHandler();
            saxParser.parse(new File("input.xml"), handler);
             list=handler.getCollection();
        } 
        catch (ParserConfigurationException | SAXException | IOException ex)
        {
            // something's gone wrong
        }
        
        tabbedPane=new JTabbedPane();
        // add my panes to tabbedPane
      
        menuBar=new JMenuBar();
        dataMenu=new JMenu("Data...");
        fileMenu=new JMenu("File...");
        byArea=new JMenuItem("By Area...");
        points=new JMenuItem("Points...");
        search=new JMenuItem("Search...");
        open=new JMenuItem("Open...");
        fileMenu.add(open);
        dataMenu.add(byArea);
        dataMenu.add(points);
        dataMenu.add(search);
        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        this.setJMenuBar(menuBar);
        
        
        paneFirst=new PaneFirst();
        paneSecond=new PaneSecond();
        paneThird=new PaneThird();
        open.addActionListener((ActionEvent e)->
        {
            String str=((Integer)list.size()).toString();
            JOptionPane.showMessageDialog(this, str);
        });
        
        
        byArea.addActionListener((ActionEvent e)->
        {
            
            
            paneSecond.setModelFromArray(list);
        });
        
        points.addActionListener((ActionEvent e)->{
            
            paneThird.setModelFromArray(list);
        });
        
        
        
        search.addActionListener((ActionEvent e)->
        {
            
            Rectangle r=Methods.searchSpecial(list);
            if (r!=null)
                JOptionPane.showMessageDialog(this, r.toString());
            
            
        });
        
        
        
        tabbedPane.addTab("Pane 1", paneFirst);
        tabbedPane.addTab("Pane 2", paneSecond);
        tabbedPane.addTab("Pane 3", paneThird);
        //tabbedPane.addTab("Pane 3", new RadioButtonPanel());
       
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
