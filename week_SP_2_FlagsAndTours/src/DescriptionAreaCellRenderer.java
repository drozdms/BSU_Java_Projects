import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Mark Drozd
 */
public class DescriptionAreaCellRenderer extends JTextArea implements TableCellRenderer
{
    public DescriptionAreaCellRenderer() 
    {
      this.setLineWrap(true);
      this.setWrapStyleWord(true);
      this.setEditable(true);
      
   }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               boolean hasFocus,
                                               int row,
                                               int column)
    {
        this.setText((String)value);
        return this;
            
    }

}

