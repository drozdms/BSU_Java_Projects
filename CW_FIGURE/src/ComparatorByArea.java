
import java.util.Comparator;



/**
 *
 * @author Mark Drozd
 */
public class ComparatorByArea implements Comparator<Rectangle>
{

    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        return o1.area().compareTo(o2.area());
    }

}
