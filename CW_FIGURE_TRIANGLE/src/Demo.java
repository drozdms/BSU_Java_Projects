
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Mark Drozd
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner scanner;
        ArrayList<Triangle> list=new ArrayList();
        try 
        {
            scanner=new Scanner(new File("triangles.txt"));
            scanTriangleListFromFile(scanner, list);
            
            
            Methods.show(list);
            System.out.println("Amount " +list.size());
            
            
            System.out.println("Sort By Sides: ");
            
            Methods.sortBySides(list);
            Methods.show(list);
            
            
             System.out.println("Sort By Perimeters: ");
            Methods.sortByPerimeter(list);
            Methods.show(list);
            
            
            System.out.println("Search special: " + Methods.searchSpecial(list));
            
            
            Map<Point, Integer> map= new HashMap<>();
            list.forEach((i) -> {
                Point first=i.getFirst();
                Point second=i.getSecond();
                Point third=i.getThird();
                if (map.containsKey(first))
                {
                    Integer ii=map.get(first);
                    ii++;
                    map.put(first, ii);
                }
                else map.put(first, 1);
                if (map.containsKey(second))
                {
                    Integer ii=map.get(second);
                    ii++;
                    map.put(second, ii);
                }
                else map.put(second, 1);
                if (map.containsKey(third))
                {
                    Integer ii=map.get(third);
                    ii++;
                    map.put(third, ii);
                }
                else map.put(third, 1);
            });
            
            Point tentwen=new Point(10,20);
            Point twenten=new Point(20,10);
            
            System.out.println(map.get(tentwen));
            System.out.println(map.get(twenten));
            
            
             System.out.println("Point set: ");
            
            Set<Point> set=map.keySet();
            set.forEach((i) -> {
                System.out.println(i.x+" "+i.y);
            });
            
            
        }
        
         catch (FileNotFoundException exc)
         {
            
             
         }
    }

    
    
    static void scanTriangleListFromFile(Scanner scanner, ArrayList<Triangle> list)
    {
        
        
        while (scanner.hasNext())
        {
            try
            {
                Point first=new Point(scanner.nextInt(), scanner.nextInt());
                Point second=new Point(scanner.nextInt(), scanner.nextInt());
                Point third=new Point(scanner.nextInt(), scanner.nextInt());
                Triangle tr=new Triangle(first, second, third);
               
                list.add(tr);
                
            }
            
            catch(InputMismatchException e)
            {
                
                scanner.next();   // skip this token
                
                // continue to scan
            }
            
               
            catch (NoSuchElementException exc)
            {
                // scanner.hasNext()=false
            }
            
        
         }
        
    }
    
}
