import java.util.Comparator;



/**
 *
 * @author Mark Drozd
 */

class StringComparator implements Comparator<String>
{

    public static int compareStrings(String o1, String o2)
    {
        return o1.compareToIgnoreCase(o2);
    }
    
    @Override
    public int compare(String o1, String o2) 
    {
        return compareStrings(o1,o2);
    }
    
}
