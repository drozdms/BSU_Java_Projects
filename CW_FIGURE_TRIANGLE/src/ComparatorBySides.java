
import java.util.Comparator;



/**
 *
 * @author Mark Drozd
 */
public class ComparatorBySides implements Comparator<Triangle>
{

    @Override
    public int compare(Triangle o1, Triangle o2) 
    {
        return o1.getMaxSide().compareTo(o2.getMaxSide());
    }

}