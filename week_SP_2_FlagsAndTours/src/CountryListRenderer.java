
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Mark Drozd
 */
public class CountryListRenderer extends DefaultListCellRenderer
{
    Font font = new Font("Aniron", Font.BOLD, 14);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) 
        {
            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (isSelected)
                label.setText((String)value+", "+Countries.CAPITALS.get((String)value));
            label.setIcon(Countries.FLAGS.get((String)value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
        
        

}
