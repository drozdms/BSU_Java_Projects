
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark Drozd
 */
public class ComparatorByPerimeter implements Comparator<Triangle>
{

    @Override
    public int compare(Triangle o1, Triangle o2) {
        return o2.perimeter().compareTo(o1.perimeter());
    }

}
