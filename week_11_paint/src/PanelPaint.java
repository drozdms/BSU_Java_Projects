import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Mark Drozd
 */
public class PanelPaint extends JPanel 

{
//    private Point prevCords,
//                  curCords;
    
    private int prevXCord, prevYCord,
            curXCord, curYCord;
    private Image image;
    private Color curColor;
    private ArrayList<Curve> curveList;
    private Curve curCurve;
    private int curStroke;
    private Graphics2D graphics;
    
    
    
    
    public PanelPaint()
    {
       curColor=Color.BLACK;
       curStroke=2;
       curveList=new ArrayList();
       
        addMouseListener(new MouseAdapter()
        {
            
            
            @Override
            public void mouseClicked(MouseEvent e)
            { 
               paintComponent((Graphics) graphics);
               prevXCord=e.getX();
               prevYCord=e.getY();
               if (graphics!=null)
                    graphics.drawLine(prevXCord,prevYCord,prevXCord,prevYCord);
               repaint();
               curveList.add(new Curve(prevXCord, prevYCord, curColor, curStroke));
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {
               paintComponent((Graphics) graphics);
               prevXCord=e.getX();
               prevYCord=e.getY();
               if (graphics!=null)
                    graphics.drawLine(prevXCord,prevYCord,prevXCord,prevYCord);
               repaint();
               curCurve=new Curve(e.getX(), e.getY(), curColor, curStroke);
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                curveList.add(curCurve);
            }
            
            
        });
        
        
        
        addMouseMotionListener(new MouseMotionAdapter()
        {
           @Override
           public void mouseDragged(MouseEvent e)
           {
               curXCord=e.getX();
               curYCord=e.getY();
               if (graphics!=null)
                {
                    graphics.drawLine(prevXCord,prevYCord,curXCord,curYCord);
                    repaint();
                    
                    prevXCord=curXCord;
                    prevYCord=curYCord;
                }
               
               curCurve.add(curXCord, curYCord);
               
               
               
           }
            
            
        });
        
        
    }
    
    
    
    
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        
     
         if (image==null)
         {
                image=createImage(getSize().width, getSize().height);
                graphics=(Graphics2D) image.getGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setStroke(new BasicStroke(curStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                clear();
         }
         
          
         graphics.setColor(curColor);
         graphics.setStroke(new BasicStroke(curStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
         g.drawImage(image, 0, 0, null);
    }
    
    public void setCurrentColor(Color color)
    {
        curColor=color;
    }
    
    
    public Color getCurrentColor()
    {
        return this.curColor;
    }
    
    
     public void setCurrentStroke(int width)
    {
        curStroke=width;
    }
        
    
    public void clear()
    {
        
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }
    
    
    public ArrayList<String> convertToText()
    {
        if (curveList==null)
            return null;
        ArrayList<String> picture=new ArrayList();
        for (Curve i: curveList)
        {
           picture.add(("Color "+((Integer)i.color.getRGB()).toString()+" Stroke "+((Integer)i.stroke).toString()));
           for (Point j: i.pointList)
           {
               picture.add(((Integer)j.x).toString()+" "+((Integer)j.y).toString());
           }
        }
        
        return picture;
    }
    
    
    public void updatePicture(ArrayList<Curve> curves)
    {
        curveList=curves;
        int prevStroke=curStroke;
        Color prevColor=curColor;
        clear();
        for (Curve i: curveList)
        {
            curStroke=i.stroke;
            curColor=i.color;
            for (int j=0; j<i.pointList.size()-1; ++j)
            {
                Point point=i.pointList.get(j);
                Point nextPoint=i.pointList.get(j+1);
                paintComponent((Graphics)graphics);
                graphics.drawLine(point.x,point.y,nextPoint.x,nextPoint.y);
              
                
            }
            if (i.pointList.size()==1)
            {
                 curStroke=i.stroke;
                 curColor=i.color;
                int x=i.pointList.get(0).x;
                int y=i.pointList.get(0).y;
                paintComponent((Graphics)graphics);
                graphics.drawLine(x,y,x,y);
               
            }
            
        }
        
        
        curStroke=prevStroke;
        curColor=prevColor;
        
    }
    
    
    
    
    public BufferedImage getImage()
    {
        BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
    
    
    public void setImage(Image image)
    {
        this.image=image;
        
        clear();
        repaint();
       graphics=(Graphics2D)image.getGraphics();
    }
        
}
