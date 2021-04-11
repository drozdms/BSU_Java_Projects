import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author Mark Drozd
 */
public class Curve 
{
   Color color;
   int stroke;
   ArrayList<Point> pointList;
   
   public Curve(int x0, int y0, Color color, int stroke)
   {
       pointList=new ArrayList();
       pointList.add(new Point(x0, y0));
       this.color=color;
       this.stroke=stroke;
   }
   
   
   public Curve()
   {
       pointList=new ArrayList();
       color=Color.BLACK;
       stroke=0;
   }
   public void add(int x0, int y0)
   {
       pointList.add(new Point(x0,y0));
   }
   
   
}
