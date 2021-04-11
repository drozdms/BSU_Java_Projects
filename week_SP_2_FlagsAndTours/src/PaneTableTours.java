import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Mark Drozd
 */




public class PaneTableTours extends JPanel
{
    final String[][] toursInfo=
    {{"israel", "Massada", "499.20"},
     {"belarus", "Mir, Nesvizh", "299.00"},
     {"usa", "Washington DC", "1489.99"},
            {"scotland", "A tour through beautiful green landscapes, stony rocks. Unforgettable autumn breeze!", "1229.99"},
            {"norway", "Famous fjords!", "2876.99"},
            {"italy", "Ancient Rome", "389.29"},
            {"germany", "Great counstructions of Nazis", "999.99"}};
    
    //,
       //     {"brazil", "Rio", "1189.99"}
    final Object[] countryList=Countries.CAPITALS.keySet().toArray();
    
    private JTable table;
    private JButton addTour;
    private JButton deleteTours;
     TableRowSorter<TableModel> sorter;
    private ToursTableModel tableModel;
    public PaneTableTours()
    {
       super(new BorderLayout());
       ToursTableModel.FORMATTER =new DecimalFormat("#0.00");  
       tableModel=new ToursTableModel(scanToursInfo(toursInfo));
       
       table=new JTable(tableModel);
       table.getColumn("Country").setCellRenderer(new CountryCellRenderer());
       table.getColumn("Tour description").setCellRenderer(new DescriptionAreaCellRenderer());
       table.setRowSelectionAllowed(false);
       table.setRowHeight(50);
       
     //  table.setAutoCreateRowSorter(true);
       
       sorter=new TableRowSorter<>(table.getModel());
       table.setRowSorter(sorter);
       
       sorter.setSortable(2, true);
       addTour=new JButton("add tour");
       deleteTours=new JButton("delete");
       JPanel panel=new JPanel();
       panel.add(addTour, BorderLayout.EAST);
       panel.add(deleteTours, BorderLayout.WEST);
       add(panel, BorderLayout.NORTH);
       add(new JScrollPane(table), BorderLayout.CENTER);
       
       
       JComboBox comboBox=new JComboBox(countryList);
       
       sorter.setComparator(2, (String o1, String o2) 
               -> ((Double)Double.parseDouble(o1)).compareTo(Double.parseDouble(o2)));
       
       addTour.addActionListener(
               (ActionEvent e) -> 
               {
                    JOptionPane pane=new JOptionPane(null,
                    JOptionPane.PLAIN_MESSAGE, 
                            JOptionPane.OK_CANCEL_OPTION);  
                    
                    JDialog diag =pane.createDialog(this, "add a tour:");
                    diag.setLayout(new FlowLayout(FlowLayout.LEFT));
                    JPanel jpanel=new JPanel(new BorderLayout());
                    JTextArea area=new JTextArea(3,25);
                    area.setLineWrap(true);
                    area.setWrapStyleWord(true);
                    JScrollPane scrollPane=new JScrollPane(area);
                    jpanel.add(new Label("Describe a tour:"), BorderLayout.NORTH);
                    jpanel.add(scrollPane, BorderLayout.CENTER);
                    JFormattedTextField field=new JFormattedTextField(NumberFormat.getNumberInstance());
                    field.setColumns(7);
                    diag.add(comboBox);
                    diag.add(jpanel);
                    diag.add(field);
                    diag.add(pane);
                    diag.pack();
                    diag.setVisible(true);
                    
                    
                    
                    Object value=pane.getValue();
                    int c=-1;
                    if (value==null)
                        c=JOptionPane.CLOSED_OPTION;
                    else c=Integer.parseInt(value.toString());
                    if (c==JOptionPane.YES_OPTION)
                    {
                        String country=comboBox.getSelectedItem().toString();
                        String description=area.getText();
                        Double price=0.;
                        if (field.getValue()!=null)
                            price=Double.parseDouble(field.getValue().toString());
                        tableModel.addTour(new Tour(country, description, price));
                    }
                            
               });
       
       
       deleteTours.addActionListener((ActionEvent e)->
       {
           tableModel.deleteSelected();
       });
       
       
       
    }
    
    
    private ArrayList<Tour> scanToursInfo(final String[][] toursInfo)
    {
        ArrayList<Tour> list=new ArrayList<>();
        for (int i=0; i<toursInfo.length; ++i)
        {
            String country=toursInfo[i][0];
            String description=toursInfo[i][1];
            double price;
            try
            {
                price=Double.parseDouble(toursInfo[i][2]);
            }
            catch(NumberFormatException e)
            {
                price=0.00;
            }
            
            list.add(new Tour(country, description, price));
        }
        
        return list;
    }
    
    
    
}
