
    
package circlemoves;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Mark Drozd
 */
public class DemoCircle extends JPanel implements ActionListener
{
    private Ellipse2D.Float trajectory=new Ellipse2D.Float();
    
    private Timer timer;
    private double centerX;
    private double centerY;
    private double leftUpperCornerX;
    private double leftUpperCornerY;
    private double radius;
    private double angularSpeed;
    private double angle;
    private double dangle;
    private int delay;
    
    BufferedImage picture;
    
    
    public DemoCircle(BufferedImage picture)
    {
        this.setPreferredSize(new Dimension(500,500));
        this.setSize(500, 500);
        this.picture=picture;
        
        
    
        centerX=this.getWidth()/2;
        centerY=this.getHeight()/2;
        leftUpperCornerX=30;
        leftUpperCornerY=30;
        radius=this.getHeight()/2-30;
        delay=20;
        angle=0;
        dangle=5;
        angularSpeed=dangle/delay;
        
        trajectory.setFrameFromCenter(centerX, centerY, leftUpperCornerX, leftUpperCornerY);
        
        timer = new Timer(delay, this);
        timer.setInitialDelay(0);
        timer.start();
    }
    
    
    public void refreshSizes()
    {
        trajectory.setFrameFromCenter(centerX, centerY, leftUpperCornerX, leftUpperCornerY);
        dangle=angularSpeed*delay;
    }
    
    
    
    public void setImage(BufferedImage image)
    {
        picture=image;
    }
    
  public void step() 
  {
         angle+=dangle;
  }
  
   public void render(int x, int y, Graphics2D g2) 
   {
        g2.setColor(Color.BLUE);
        g2.draw(trajectory);
        g2.drawImage(picture, x-picture.getWidth()/2, y-picture.getHeight()/2, picture.getWidth(), picture.getHeight(), this);
  }

    
    @Override
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);

        double rad=Math.toRadians(angle);
        int x=((Double)(centerX+radius*Math.sin(rad))).intValue();
        int y=((Double)(centerY-radius*Math.cos(rad))).intValue();
        this.step();
        
        render(x, y, g2);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        repaint();
    }

    
    
    public static void main(String[] args) throws IOException 
    {
        JFrame frame = new JFrame("TimerBasedAnimation");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage image=ImageIO.read(new File("ball.png"));
        DemoCircle panel=new DemoCircle(image);
        
        
        JMenu fileMenu;
        JMenuBar menuBar;
        JMenuItem open;
       
        menuBar=new JMenuBar();
        fileMenu=new JMenu("File");
        open=new JMenuItem("Open...");
        fileMenu.add(open);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        
        open.addActionListener((ActionEvent e) -> 
        {
              FileFilter imageFilter = new FileNameExtensionFilter(
                             "Image files", ImageIO.getReaderFileSuffixes());
            JFileChooser jfc=new JFileChooser("C:\\Users\\drozd\\Documents\\NetBeansProjects\\week_SP_3_Timer");
            jfc.setFileFilter(imageFilter);
            int rVal=jfc.showOpenDialog(frame);
            if (rVal==JFileChooser.APPROVE_OPTION)
            {
                BufferedImage img=null;
                try
                {
                    img=ImageIO.read(jfc.getSelectedFile());
                }
                
                 catch (IOException ex) {
                     ex=null;
                        return;
                    }
                
                panel.setImage(img);
            }
        });
        
        
        String[] arr=new String[2];
        arr[0]="clockwise";
        arr[1]="!clockwise";
        JComboBox comboBox=new JComboBox(arr);
        comboBox.addActionListener((ActionEvent e) -> {
            switch(comboBox.getSelectedItem().toString()) 
            {
                case "clockwise":
                    if (panel.dangle<=0)
                    {
                       // panel.dangle=Math.abs(panel.dangle);
                        panel.angularSpeed=Math.abs(panel.angularSpeed);
                    }
                    break;
                
                case "!clockwise":
                    if (panel.dangle>=0)
                    {
                       // panel.dangle=-Math.abs(panel.dangle);
                        panel.angularSpeed=-Math.abs(panel.angularSpeed);
                    }
                    break;
            }
            
             panel.refreshSizes();
        });
        
        
        
        
        JSlider sliderSpeed=new JSlider(0,55,55);
        sliderSpeed.setPreferredSize(new Dimension(200,40));
        sliderSpeed.setMajorTickSpacing(5);
        sliderSpeed.setPaintTicks(true);
        sliderSpeed.setPaintLabels(true);
        sliderSpeed.addChangeListener((ChangeEvent e) -> {
            if (!sliderSpeed.getValueIsAdjusting())
            {
                int newSpeed=sliderSpeed.getValue();
                panel.angularSpeed=newSpeed/panel.radius;
                if (comboBox.getSelectedItem().toString().equals("!clockwise"))
                    panel.angularSpeed=-Math.abs(panel.angularSpeed);
                panel.refreshSizes();
                    
            }
        });
    
        panel.setMinimumSize(new Dimension(100,100));
         frame.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                double r=panel.radius;
             //   panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
                panel.centerX=panel.getWidth()/2;
                panel.centerY=panel.getHeight()/2;
                if (panel.getWidth()<panel.getHeight())
                    panel.radius=panel.centerX;
                else panel.radius=panel.centerY;
                panel.radius-=panel.radius*0.12;
                panel.angularSpeed*=r/panel.radius;
                
                panel.leftUpperCornerX=panel.centerX-panel.radius;
                panel.leftUpperCornerY=panel.centerY-panel.radius;
                panel.refreshSizes();
            }
        });

         
         
        
        frame.add(panel, BorderLayout.CENTER);
        frame.add(comboBox, BorderLayout.NORTH);
        
        frame.add(sliderSpeed, BorderLayout.SOUTH);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
