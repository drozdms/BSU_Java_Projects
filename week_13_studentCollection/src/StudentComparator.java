import java.util.Comparator;
/**
 *
 * @author Mark Drozd
 */

class StudentComparator implements Comparator<Student>
{

    @Override
    public int compare(Student o1, Student o2) 
    {
        return StringComparator.compareStrings(o1.getName(), o2.getName());
    }
    
}
