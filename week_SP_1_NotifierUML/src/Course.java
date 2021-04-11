
import java.util.HashSet;
import java.util.Set;


public class Course 
{
   
    Course(String name, Set<Student> students)
    {
        this.name=name;
        this.students=students;
    }

    public Set<Postgraduate> getPostGraduates(String nameOfSupervisor)
    {
        Set<Postgraduate> postgraduates=new HashSet();
        students.forEach((student)->
        {
            if (student instanceof Postgraduate)
                if (((Postgraduate) student).getSupervisor().name.equals(nameOfSupervisor))
                    postgraduates.add((Postgraduate) student);
        });
        
        return postgraduates;
    }
    
    
    
    
    private Set<Student> students;
    private String name;

}