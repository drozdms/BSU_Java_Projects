import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Mark Drozd
 */
public class View extends JTable
{
    static final Font ANIRON_FONT = new Font("Aniron", Font.BOLD, 13);
    
    public View(Model model)
    {
        super(model);
        setRowSelectionAllowed(false);
        this.setCellSelectionEnabled(true);
        this.setRowHeight(50);
        this.setShowGrid(true);
        this.setDefaultEditor(Object.class, new CellEditor(new JTextField()));
    }
    
    
    public void setDefaultModel(Model model)
    {
        this.setModel(model);
    }
    
    public class CellEditor extends DefaultCellEditor 
    {
      public CellEditor(JTextField field)
      {
          super(field);
      }

      @Override
      public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
          return super.getTableCellEditorComponent(table, ((Model)View.this.getModel()).cells.get(row).get(column), isSelected, row, column);
      }

    }
}
