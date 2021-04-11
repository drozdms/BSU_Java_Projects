import java.awt.Point;



/**
 *
 * @author Mark Drozd
 */
public abstract class Figure 
{
    
    
    public abstract Integer perimeter();
    
    
    
    Figure()
    {
        first=new Point(-1,-1);
        second = new Point(0,-1);
    }
    
    
    Figure(Point f, Point s)
    {
        first=(Point) f.clone();
        second=(Point) s.clone();
    }
    
    
    @Override
    public String toString()
    {
        return "Figure " + first.toString() + " " + second.toString();
    }

    
    
    
    protected Point first;
    protected Point second;
}
