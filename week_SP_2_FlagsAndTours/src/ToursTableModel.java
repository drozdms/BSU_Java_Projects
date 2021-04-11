
import java.awt.Label;
import java.text.NumberFormat;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ToursTableModel extends AbstractTableModel
{
    private ArrayList<Tour> tours;
    private double currentResultingPrice;
    private ArrayList<Boolean> data;
    static public NumberFormat FORMATTER;

    public ToursTableModel(ArrayList<Tour> tours)
    {
        
        this.tours = tours;
        data=new ArrayList();
        currentResultingPrice=0.00;
        for (int i=0; i<tours.size();++i)
        {
            data.add(false);
        }
        
    }

    @Override
    public String getColumnName(int column) 
    {
        switch (column)
        {
            case 0:
                return "Country";
            case 1:
                return "Tour description";
            case 2:
                return "Price, $";
            case 3:
                return "Choose:";
        }
        return null;
    }

    @Override
    public int getRowCount() 
    {
        return tours.size()+1;
    }
    @Override
    public int getColumnCount() 
    {
        return 4;
    }
    
    
    
    @Override
    public boolean isCellEditable(int row, int column) 
    {
        return !(row==0 & column<3);
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) 
    {
        switch (columnIndex) 
        {
            case 3:
                if (rowIndex==0)
                {
                    currentResultingPrice=0.00;
                    if ((Boolean)value==true)
                    {
                        for (int i=0; i<data.size();++i)
                            data.set(i, true);
                        tours.forEach((tour)->{currentResultingPrice+= tour.getPrice();});
                    }
                    else
                        for (int i=0; i<data.size();++i)
                            data.set(i, false);
                    this.fireTableDataChanged();
                }
                else
                {
                    data.set(rowIndex-1, (Boolean)value);
                    if ((Boolean)value==true)
                        currentResultingPrice+=tours.get(rowIndex-1).getPrice();
                    else currentResultingPrice-=tours.get(rowIndex-1).getPrice();
                    this.fireTableRowsUpdated(0, 0);
                }   fireTableCellUpdated(rowIndex, columnIndex);
                break;
            case 0:
                tours.get(rowIndex-1).setCountry(((Label)value).getText());
                break;
            case 1:
                tours.get(rowIndex-1).setDescription((String)value);
                break;
            case 2:
            {
                if (data.get(rowIndex-1))
                    currentResultingPrice-=tours.get(rowIndex-1).getPrice();
                try
                {
                    tours.get(rowIndex-1).setPrice(Double.parseDouble((String)value));
                }
                catch (NumberFormatException e)
                {}
                if (data.get(rowIndex-1))
                {
                    currentResultingPrice+=tours.get(rowIndex-1).getPrice();
                     this.fireTableRowsUpdated(0, 0);
                }
                break;
            }
            default:
                break;
        }
    }
    @Override
    public Object getValueAt(int r, int c) 
    {
        switch (c){
            case 0:
                if (r==0)
                    return "All";
                return tours.get(r-1).getCountry();
            case 1:
                if (r==0)
                    return null;
                return tours.get(r-1).getDescription();
            case 2:
                if (r==0)
                    return FORMATTER.format(currentResultingPrice);
                return FORMATTER.format(tours.get(r-1).getPrice());
            case 3:
            {
                if (r==0)
                {
                    return data.stream().allMatch(Boolean.TRUE::equals);
                }
                return data.get(r-1);
            }
            
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return Label.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Boolean.class;
        }
        return String.class;
    }
    
    
    public void addTour(Tour tour)
    {
        tours.add(tour);
        data.add(true);
        this.getRowCount();
        this.fireTableRowsInserted(tours.size(), tours.size());
        this.setValueAt(true, tours.size(), 3);
    }
    
    
    public void deleteSelected()
    {
        for (int i=0; i<data.size(); ++i)
        {
            if (data.get(i))
            {
                currentResultingPrice-=tours.get(i).getPrice();
                tours.remove(i);
                data.remove(i);
                i--;
            }
        }
        
        this.fireTableDataChanged();
    }
}

