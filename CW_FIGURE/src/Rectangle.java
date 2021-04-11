
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
public class Rectangle extends Figure 
{

    public Rectangle()
    {
        super();
        cordThird=new Point(-1,-1);
        cordFourth=new Point(-1,-1);
    }
    
    public Rectangle(Point f, Point s)
    {
        super(f,s);
         cordThird=new Point(cordSecond.x,cordFirst.y);
        cordFourth=new Point(cordFirst.x,cordSecond.y);
    }
    
    
    @Override
    public Integer area() 
    {
        Double a=Math.sqrt((cordFirst.x-cordThird.x)*(cordFirst.x-cordThird.x)+(cordFirst.y-cordThird.y)*(cordFirst.y-cordThird.y));
        Double b=Math.sqrt((cordSecond.x-cordThird.x)*(cordSecond.x-cordThird.x)+(cordSecond.y-cordThird.y)*(cordSecond.y-cordThird.y));
        Double area=a*b;
        return area.intValue();
    }
    
    
    @Override
    public String toString()
    {
        return "Rectangle "+((Integer)cordFirst.x).toString()+" "+((Integer)cordFirst.y).toString()+" "
               +((Integer)cordSecond.x).toString()+" "+((Integer)cordSecond.y).toString()+" "
                +((Integer)cordThird.x).toString()+" "+((Integer)cordThird.y).toString()+" "
                +((Integer)cordFourth.x).toString()+" "+((Integer)cordFourth.y).toString()+" Area: "+
                this.area().toString();
        
        
    }
    
    
    
    protected Point cordThird;
    protected Point cordFourth;
    
    
    

}
