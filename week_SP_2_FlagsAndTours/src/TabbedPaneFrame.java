import javax.swing.JFrame;
import javax.swing.JTabbedPane;


/**
 *
 * @author Mark Drozd
 */
public class TabbedPaneFrame extends JFrame 
{
    private JTabbedPane tabbedPane;
    private PaneCapitals paneCapitals;
    private PaneTableTours paneTableTours;
    private PaneStudentTree paneStudentTree;
    
    public TabbedPaneFrame()
    {
        super("Capitals and Tours");
        setBounds(100,100,1000,600);
        tabbedPane=new JTabbedPane();
        paneCapitals=new PaneCapitals();
        paneTableTours=new PaneTableTours();
        paneStudentTree=new PaneStudentTree();
        tabbedPane.addTab("Capitals", paneCapitals);
        tabbedPane.addTab("Tours", paneTableTours);
        tabbedPane.addTab("Students",paneStudentTree);
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
