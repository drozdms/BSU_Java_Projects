
/**
 *
 * @author Mark Drozd
 */
public class Student 

{
    
    
    public Student(String course, String group, String surname)
    {    
        this.course=course;
        this.group=group;
        this.surname=surname;
    }
    
    
    public String getName()
    {
        return surname;
    }
    
    public String getCourse()
    {
        return course;
    }
    
    public String getGroup()
    {
        return group;
    }
    
    @Override
    public String toString()
    {
        return surname;
    }
    
    
    
    
    private String course;
    private String group;
    private String surname;

}
