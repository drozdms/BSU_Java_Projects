import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Mark Drozd
 */
public class PaneCapitals extends JPanel
{
    private JList list;
    public PaneCapitals()
    {
       super(new BorderLayout());
       list=new JList(Countries.CAPITALS.keySet().toArray());
       list.setCellRenderer(new CountryListRenderer());
       add(new JScrollPane(list), BorderLayout.CENTER);
    }
}
