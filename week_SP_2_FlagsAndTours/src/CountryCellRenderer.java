
import java.awt.Component; 
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mark Drozd
 */
public class CountryCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               boolean hasFocus,
                                               int row,
                                               int column)
    {
        JLabel label= (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setIcon(Countries.FLAGS.get((String)value));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        return label;       
    }

}
