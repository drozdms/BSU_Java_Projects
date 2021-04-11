
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Mark Drozd
 */
public class Triangle extends Figure
{
    
    
    private Integer distance(Point f, Point s)
    {
        Double dist=Math.sqrt((f.x-s.x)*(f.x-s.x)+(f.y-s.y)*(f.y-s.y));
        return (Integer) dist.intValue();
    }
    
    public Integer getMaxSide()
    {
        
        ArrayList<Integer> arr=new ArrayList();
        arr.add(distance(second, third));
        arr.add(distance(first, second));
        arr.add(distance(first, third));
        return Collections.max(arr);
    }

    Triangle()
    {
        super();
        third=new Point(0,0);
    }
    
    
    Triangle(Point f, Point s, Point t)
    {
        super(f,s);
        third=(Point)t.clone();
    }
    
    
    @Override
    public Integer perimeter()      
    {
        return distance(first,second)+distance(second,third)+distance(first,third);
    }
    
    
    @Override
    public String toString()
    {
        return "Triangle (" + ((Integer)first.x).toString() + " " +((Integer)first.y).toString() + 
                "), (" + ((Integer)second.x).toString()+ " " + ((Integer)second.y).toString() + 
                "), (" + ((Integer)third.x).toString() + " " + ((Integer)third.y).toString() + "); Perimeter "+perimeter().toString();
    }
    
    
    public Point getFirst()
    {
        return first;
    }
     public Point getSecond()
    {
        return second;
    }
      public Point getThird()
    {
        return third;
    }
    
    
    private Point third;

}
