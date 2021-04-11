
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
public class PaneSecond extends JPanel 
{
    
    private DefaultListModel<Rectangle> list;
    
    private JList<Rectangle> listForDisplay;
   
    
    
    
    public PaneSecond()
    {
        super(new BorderLayout());
        
        
        list=new DefaultListModel();
        
        
        listForDisplay=new JList();
        listForDisplay.setModel(list);
       
        add(listForDisplay, BorderLayout.CENTER);
        
        
         listForDisplay.setPreferredSize(new Dimension(450, this.getHeight()));
         
    
    }
    
    
    
    
    public void setModelFromArray(ArrayList<Rectangle> list)
    {
        this.list.clear();
        ArrayList<Rectangle> copyList=(ArrayList<Rectangle>) list.clone();
        Methods.sortByArea(copyList);
        copyList.forEach((i) -> {
            this.list.addElement(i);
        });

    }

}
