
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
public class Methods 
{
    
    
    static void sortByArea (ArrayList<Rectangle> list)
    {
        Collections.sort(list, new ComparatorByArea());
    }
    
    
    static Rectangle min(ArrayList<Rectangle> list)
    {
        return Collections.min(list, new ComparatorByArea());
    }
    
    
    static Rectangle searchSpecial(ArrayList<Rectangle> list)
    {
        Rectangle minRect=Methods.min(list);
        
        Integer areaToFind=minRect.area();
        
        Iterator<Rectangle> i=list.iterator();
        Rectangle rect=new Rectangle();
        
        
        while(i.hasNext())
        {
            Rectangle temp=i.next();
            Integer area=temp.area();
            if (temp.area()==areaToFind*2)
                return temp;
        }
        
        return null;
            
    }
    
    
    

}
