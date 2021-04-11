import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
/**
 *
 * @author Mark Drozd
 */
public class ButtonPanel extends JPanel
{

    public static final int N=6;
    Font font=new Font("Ringbearer", Font.BOLD, 18);
    public ButtonPanel()
    {
        super(new GridLayout(N,N));
        for (int i=0; i<N*N; i++)
        {
            JButton button=new JButton(Integer.toString(i+1));
            button.addMouseListener(mouseListener);
            button.setFont(font);
            add(button);
        }

    }
    
    MouseListener mouseListener=new MouseAdapter()
    {
        private String oldText;
        private Color oldColor;
        boolean elseMousePressed;
        @Override
        public void mousePressed(MouseEvent e)
        {
            
            if (e.getButton()==MouseEvent.BUTTON1 && !elseMousePressed)
            {
                JButton button=(JButton)e.getSource();
                oldText=button.getText();
                button.setText("Pressed");
            }
            
            else elseMousePressed=true;
            
            

        }
        
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (e.getButton()==MouseEvent.BUTTON1)
            {
               ((JButton)e.getSource()).setText(oldText);
            }
            else elseMousePressed=false;
        }
        
        
        @Override
        public void mouseEntered(MouseEvent e)
        {
            JButton button=(JButton)e.getSource();
            oldColor=button.getBackground();
            button.setBackground(Color.ORANGE);
        }
        
        @Override
        public void mouseExited(MouseEvent e)
        {
             ((JButton)e.getSource()).setBackground(oldColor);
        }

    };
    
    
    
}
