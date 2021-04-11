import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Mark Drozd
 */
public class ListPanel extends JPanel 
{
    
    private JPanel operatingPanel;
    private JButton moveToRight;
    private JButton moveToLeft;
    
    private DefaultListModel listFirst;
    private DefaultListModel listSecond;
    
    private JList listFirstDisplay;
    private JList listSecondDisplay;
    
    

    
    private final Object[] arrayFirst={123, 8.2, "This","is", "List"};
    private final Object[] arraySecond={"Sshsh", 43, 9.312, "List", "Of", "Objects"};
    
    public ListPanel()
    {
        super(new BorderLayout());
        Font font=new Font("Times New Roman", Font.PLAIN, 22);
        operatingPanel=new JPanel();
        moveToRight=new JButton(">");
        moveToLeft=new JButton("<");
        moveToRight.addActionListener(buttonMoveListener);
        moveToLeft.addActionListener(buttonMoveListener);
        
        listFirst=new DefaultListModel();
        listSecond=new DefaultListModel();
        
        for (Object i: arrayFirst)
        {
            listFirst.addElement(i);
        }
        for (Object i: arraySecond)
        {
            listSecond.addElement(i);
        }
        
        listFirstDisplay=new JList();
        listSecondDisplay=new JList();
        listSecondDisplay.setFont(font);
        listFirstDisplay.setFont(font);
        listFirstDisplay.setModel(listFirst);
        listSecondDisplay.setModel(listSecond);
       
        add(listFirstDisplay, BorderLayout.WEST);
        add(listSecondDisplay, BorderLayout.EAST);
        
         listFirstDisplay.setPreferredSize(new Dimension(450, this.getHeight()));
         listSecondDisplay.setPreferredSize(new Dimension(450, this.getHeight()));
        add(operatingPanel, BorderLayout.CENTER);
        
        operatingPanel.setLayout(new BorderLayout());
        operatingPanel.add(moveToRight, BorderLayout.NORTH);
        operatingPanel.add(moveToLeft, BorderLayout.SOUTH);
        
        listFirstDisplay.setCellRenderer(myListRenderer);
        listSecondDisplay.setCellRenderer(myListRenderer);
    
    }
    
    
    
    ActionListener buttonMoveListener=new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            if (e.getSource()==moveToRight)
            {
                
                java.util.List listOfSelected=listFirstDisplay.getSelectedValuesList();
                listOfSelected.forEach((i) -> {
                    listSecond.addElement(i);
                    listFirst.removeElement(i);
               });
                
               
            }
            
            
            else  if (e.getSource()==moveToLeft)
            {
                
                java.util.List listOfSelected=listSecondDisplay.getSelectedValuesList();
                listOfSelected.forEach((i) -> {
                    listFirst.addElement(i);
                    listSecond.removeElement(i);
               });
                
               
            }
            
             listFirstDisplay.setModel(listFirst);
             listSecondDisplay.setModel(listSecond);
        }
        
    };
    
    
    
    DefaultListCellRenderer myListRenderer=new DefaultListCellRenderer() 
    {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
            {
                 Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                 JLabel label = (JLabel) component;
                 if (isSelected)
                    label.setBackground(Color.RED);
                 return label;
            }
    };
    
    
}




