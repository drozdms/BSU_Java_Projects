import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Mark Drozd
 */
public class StudentCollection extends ArrayList<Student>
{
    public StudentCollection()
    {
        super();
    }
  
    
    public static void sort(StudentCollection o)
    {
       if (o==null)
           return;
       Collections.sort(o, new StudentComparator());
    }
    
    StudentCollection getSubjectLosers(Integer course, ArrayList<String> subjects)
    {
        if (subjects==null)
            return null;
        if (course>4 | course < 1)
            return null;
        StudentCollection losers=new StudentCollection();
          
        
        Iterator<Student> it = this.iterator();
        while (it.hasNext()) outerLoop:
        {
            
           Student student=it.next();
           Iterator<String> i=subjects.iterator();
           while (i.hasNext())
           {
              Integer mark=student.getSubjectMark(course, i.next());
              if (mark == null || mark >= 4)
                  break outerLoop;          
           }
           
           losers.add(student);  
        }
        
       
        
        
        return losers;
    }
    
    
    
    
    @Override
    public boolean add(Student o)
    {
        if (this.contains(o))
        {
            this.get(this.indexOf(o)).updateInfo(o);
            return true;
        }
        super.add(o);
        return true;
    }
    
    
    
    
    public ArrayList<String> toXML()
    {
        ArrayList<String> text=new ArrayList();
        text.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        text.add("<students>");
        Iterator<Student> it=this.iterator();
        
        
        while (it.hasNext())
        {
            Student student=it.next();
            ArrayList<String> studentToXML=student.toXML();
            studentToXML.forEach((i) -> {
                text.add(i);
            });
            
            
        }
        
        text.add("</students>");        
        return text;
        
    }
    
    
    
  
    
}



