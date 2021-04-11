package seconds;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Demo extends JPanel implements ActionListener 
{
  private Ellipse2D.Float ring=new Ellipse2D.Float();
  private Line2D.Float arrow=new Line2D.Float();
  
  private double centerX;
  private double centerY;
  private double leftUpperCornerX;
  private double leftUpperCornerY;
  private double radius;
  private Stroke stroke;
  private int angle=0;

  private boolean initialize = true;

  Timer timer;
  public Demo() 
  {

    
    centerX=this.getWidth()/2;
    centerY=this.getHeight()/2;
    leftUpperCornerX=30;
    leftUpperCornerY=30;
    radius=this.getHeight()/2-40;
    timer = new Timer(1000, this);
    timer.setInitialDelay(0);
    timer.start();
    stroke=new BasicStroke(4);
    
    this.addComponentListener(new ComponentAdapter()
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            
            centerX=e.getComponent().getWidth()/2;
            centerY=e.getComponent().getHeight()/2;
            if (e.getComponent().getWidth()<e.getComponent().getHeight())
                radius=centerX;
            else radius=centerY;
            radius-=radius*0.12;
            
            leftUpperCornerX=centerX-radius;
            leftUpperCornerY=centerY-radius;
            radius-=radius*0.1;
            setXY();
        }
    });
  }

  public void setXY() 
  {
    ring.setFrameFromCenter(centerX, centerY, leftUpperCornerX, leftUpperCornerY);
    arrow.setLine(centerX, centerY, centerX+radius*Math.sin(Math.toRadians(angle)), centerY-radius*Math.cos(Math.toRadians(angle)));
  }


  public void step() 
  {
    angle+=6;
    if (angle == 360)
        angle =0;
    double rad=Math.toRadians(angle);
    arrow.setLine(centerX, centerY, centerX+radius*Math.sin(rad), centerY-radius*Math.cos(rad));
  }

  public void render(Graphics2D g2) {
    g2.setColor(Color.BLUE);
    g2.setStroke(stroke);
    g2.draw(ring);
    g2.setColor(Color.ORANGE);
    g2.draw(arrow);
  }

  @Override
  public void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    g2.setRenderingHints(rh);
    Dimension size = getSize();

    if (initialize) {
      setXY();
      initialize = false;
    }
    this.step();
    render(g2);
  }

  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("TimerBasedAnimation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new Demo());
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}