package week_10_button;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
/**
 *
 * @author Mark Drozd
 */
public class ButtonFrame extends JFrame {

    private JButton button;
    private JTextField field;
    
    
    private StringBuilder buttonText;
    
    public ButtonFrame()
    {
        super("Button Frame");
        setLayout(null);
        setBounds(100,100,1000,600);
        BufferedImage myImage=null;
        
        try
        {
             myImage = ImageIO.read(new File("LOTRmap.jpg"));
        }
        catch(IOException e)
        {
            
        }
        
        this.setContentPane(new ImagePanel(myImage));
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        field=new JTextField();
        field.setSize(300, 40);
        field.setBackground(Color.CYAN);
        buttonText=new StringBuilder("Button");
        button=new JButton(buttonText.toString());
        button.setSize(100, 50);
        add(button, BorderLayout.CENTER);
        add(field, BorderLayout.SOUTH);
        button.setLocation(this.getWidth()/2-button.getWidth()/2,this.getHeight()/2-button.getHeight()/2);
        field.setLocation(this.getWidth()/2-field.getWidth()/2,this.getHeight()-field.getHeight()-30);
        field.setEnabled(false);
        
        Font font=new Font("Hobbiton", Font.BOLD, 18);
        
        field.setFont(font);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setDisabledTextColor(Color.BLACK);
        
        this.addMouseMotionListener(mouseMotionAdapter);
        button.addMouseMotionListener(mouseMotionAdapter);
        field.addMouseMotionListener(mouseMotionAdapter);
        button.addMouseMotionListener(new MouseMotionAdapter()
        {
           
           @Override
           public void mouseDragged(MouseEvent e)
           {
               if (e.isControlDown())
                     button.setLocation(e.getXOnScreen()-ButtonFrame.this.getX()-ButtonFrame.this.getRootPane().getX(), 
                        e.getYOnScreen()-ButtonFrame.this.getY()-ButtonFrame.this.getRootPane().getY());
               
               mouseMotionAdapter.mouseMoved(e);
           }
        });
        
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                button.setLocation(e.getXOnScreen()-ButtonFrame.this.getX()-ButtonFrame.this.getRootPane().getX(), 
                        e.getYOnScreen()-ButtonFrame.this.getY()-ButtonFrame.this.getRootPane().getY());
            }
        });
        
        
        button.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                if ((int)e.getKeyChar()==KeyEvent.VK_BACK_SPACE)
                {
                    if (buttonText.length()!=0)
                    {
                        buttonText.delete(buttonText.length()-1, buttonText.length());
                        button.setText(buttonText.toString());
                    }
                }
                 
                else 
                {
                  buttonText.append(e.getKeyChar());
                  button.setText(buttonText.toString());
                }
            }
            
        });
    }
    
   
    private MouseMotionAdapter mouseMotionAdapter=new MouseMotionAdapter()
    {
         Integer Xpos;
         Integer Ypos;
            
         @Override
         public void mouseMoved(MouseEvent e)
         {
            Xpos=e.getXOnScreen()-ButtonFrame.this.getX()-ButtonFrame.this.getRootPane().getX();
            Ypos=e.getYOnScreen()-ButtonFrame.this.getY()-ButtonFrame.this.getRootPane().getY();
            String s=Xpos.toString()+"   "+Ypos.toString();
            field.setText(s);
         }
         
          @Override
         public void mouseDragged(MouseEvent e)
         {
             mouseMoved(e);
         }
         
         
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    
    {
        ButtonFrame app = new ButtonFrame();
        
       // app.pack();
        app.setVisible(true);
    }

}



