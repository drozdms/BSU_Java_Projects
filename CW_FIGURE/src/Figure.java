
import java.awt.Point;


/**
 *
 * @author Mark Drozd
 */
public abstract class Figure 
{
    public abstract Integer area();
    
    public Figure()
    {
        cordFirst=new Point(-1,-1);
        cordSecond=new Point(-1,-1);
    }
    public Figure(Point f, Point s)
    {
        cordFirst=new Point();
        cordFirst=(Point) f.clone();
        cordSecond=(Point) s.clone();
    }
    
    
    @Override
    public String toString()
    {
        return "Figure "+cordFirst.toString()+" "+cordSecond.toString();
    }

    
       protected Point cordFirst;
       protected Point cordSecond;
}
