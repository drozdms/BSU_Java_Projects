
/**
 *
 * @author Mark Drozd
 */

import javax.swing.*;


public class TabbedPaneFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    public TabbedPaneFrame()
    {
        super("Tabbed Pane Frame");
      
        setBounds(100,100,1000,600);
        
        tabbedPane=new JTabbedPane();
        // add my panes to tabbedPane
      
        
        
        tabbedPane.addTab("Pane 1", new ListPanel());
        tabbedPane.addTab("Pane 2", new ButtonPanel());
        tabbedPane.addTab("Pane 3", new RadioButtonPanel());
       
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    public static void main(String[] args) 
    {
        TabbedPaneFrame app=new TabbedPaneFrame();
        app.setVisible(true);
        
    }

}
