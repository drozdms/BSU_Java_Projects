import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Mark Drozd
 */
public class RadioButtonPanel extends JPanel
{
    ButtonGroup buttonGroup;
    BufferedImage myImage;
    final String[] fellowship={"Frodo", "Sam", "Gandalf", "Aragorn", "Legolas", "Merry", "Pippin",
                     "Gimli", "Boromir"};
    
    public RadioButtonPanel()
    {
        super();
        myImage=null;
        
        try
        {
             myImage = ImageIO.read(new File("background.jpg"));
        }
        catch(IOException e)        {}
        buttonGroup=new ButtonGroup();
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
          
        ImageIcon okIcon=new ImageIcon("ok.png");
        ImageIcon defaultIcon=new ImageIcon("defaultIcon.png");
        ImageIcon rolloverIcon=new ImageIcon("rolloverIcon.png");
        ImageIcon pressedIcon=new ImageIcon("pressedIcon.png");
        Font font=new Font("Ringbearer", Font.BOLD, 18);
        
        for (int i=0; i<9; ++i)
        {
            JRadioButton button=new JRadioButton(fellowship[i]);
            buttonGroup.add(button);
            button.setIcon(defaultIcon);   
            button.setSelectedIcon(okIcon);
            button.setPressedIcon(pressedIcon);
            button.setRolloverIcon(rolloverIcon);
            button.setFont(font);
            button.setForeground(Color.WHITE);
            panel.add(button);
        }
        
       panel.setOpaque(false);
       add(panel);
      
        
    }
    
    public void paintComponent(Graphics g) 
        {
            g.drawImage(myImage, 0, 0, null);
        }

   
}
