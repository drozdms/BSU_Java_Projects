
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Mark Drozd
 */
public class Controller implements TableModelListener
{
    private Model model;
    private View view;
    
    
    public Controller(Model model, View view)
    {
        this.model=model;
        this.view=view;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
      //  
    }
}
