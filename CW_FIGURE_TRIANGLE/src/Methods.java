
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;


/**
 *
 * @author Mark Drozd
 */
public class Methods 
{
    static void show(ArrayList<Triangle> list)
    {
        if (list!=null)
             list.forEach((i) -> {
                    System.out.println(i);
            });
        else System.out.println("List is empty");
    }
    
  
    static void sortBySides (ArrayList<Triangle> list)
    {
        Collections.sort(list, new ComparatorBySides());
    }
    
    
     static void sortByPerimeter (ArrayList<Triangle> list)
    {
        Collections.sort(list, new ComparatorByPerimeter());
    }
     
        static Triangle max(ArrayList<Triangle> list)
    {
        return Collections.max(list, new ComparatorByPerimeter());
    }
     
     
     static Triangle searchSpecial(ArrayList<Triangle> list)
    {
        Triangle maxTr=Methods.max(list);
        
        Integer perimeter=maxTr.perimeter();
        
        Iterator<Triangle> i=list.iterator();
        Triangle tr=new Triangle();
        
        
        while(i.hasNext())
        {
            Triangle temp=i.next();
            Integer per=temp.perimeter();
            if (Objects.equals(temp.perimeter(), perimeter))
                return temp;
        }
        
        return null;
            
    }
    
    

}
