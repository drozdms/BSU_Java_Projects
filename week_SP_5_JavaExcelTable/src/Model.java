import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mark Drozd
 */
public class Model extends DefaultTableModel
{
    public static final int ROW_COUNT=8;
    public static final int COLUMN_COUNT=10;
    private SimpleDateFormat dateFormat2=new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat dateFormat1=new SimpleDateFormat("(dd.MM.yy)|(dd.MM.yyyy)");
    private Pattern patternOperand=Pattern.compile("[+-]");
    private Pattern patternMinMax=Pattern.compile("(макс)|(мин)");
    private Pattern patternDate=Pattern.compile("(\\d{2}\\.\\d{2}\\.(\\d{4}))");
    private Pattern patternCell=Pattern.compile("[A-H][1-8]");
    private Pattern patternNumber=Pattern.compile("\\d+");
    ArrayList<ArrayList<ArrayList<String>>> cellsDependentFrom;
    ArrayList<ArrayList<ArrayList<String>>> cellsFathers;
    ArrayList<ArrayList<String>> cells;
    ArrayList<ArrayList<Date>> dates;
    Calendar calendar;
    
    public Model(Object[] columnNames, int rows)
    {
        
        super(columnNames, rows);
        setRowCount(ROW_COUNT);
        setColumnCount(COLUMN_COUNT);
        calendar=GregorianCalendar.getInstance();
        dateFormat1.setLenient(false);
        dateFormat2.setLenient(false);
        cellsDependentFrom=new ArrayList(ROW_COUNT);
        cellsFathers=new ArrayList();
        cells=new ArrayList(ROW_COUNT);
        dates=new ArrayList(ROW_COUNT);
        for (int i=0; i<ROW_COUNT; ++i)
        {
            ArrayList<ArrayList<String>> array1=new ArrayList<>(COLUMN_COUNT);
            ArrayList<ArrayList<String>> array2=new ArrayList<>(COLUMN_COUNT);
            ArrayList<String> array=new ArrayList<>(COLUMN_COUNT);
            ArrayList<Date> arDates=new ArrayList<>(COLUMN_COUNT);
            for (int k=0; k<COLUMN_COUNT; ++k)
            {
                array1.add(new ArrayList<>());
                array2.add(new ArrayList<>());
                array.add(" ");
                arDates.add(null);
            }
            cellsDependentFrom.add(array2);
            cellsFathers.add(array1);
            cells.add(array);
            dates.add(arDates);
        }
    }
    
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) 
    {
        updateCell(value, rowIndex, columnIndex);
    
    }
    
    
    
    public void updateCell(Object value, int rowIndex, int columnIndex)
    {
        if (((String)value==null) || (((String)value).equals("")))
                return;
        Date date=null;
        
        String thisCell=(char)('A'+columnIndex)+((Integer)(rowIndex+1)).toString();
        
        cellsFathers.get(rowIndex).get(columnIndex).forEach((s)->{
                int r=(int)s.charAt(1)-(int)'1';
                int c=(int)s.charAt(0)-(int)'A';
                cellsDependentFrom.get(r).get(c).remove(thisCell);
            });
            
        cellsFathers.get(rowIndex).get(columnIndex).clear();
        
        if (((String)value).matches("^(\\s)*((\\d{2}\\.\\d{2}\\.((\\d{2})|(\\d{4})))|([A-H][1-8]))((\\s)*[+-](\\s)*(\\d+))?(\\s)*$"))
        {
            Matcher operand=patternOperand.matcher((String)value);
            Matcher cell=patternCell.matcher((String)value);
            Boolean operand_found=operand.find();
            Boolean cell_found=cell.find();
            
            
            if (!cell_found)
            {
                 try
                {
                    date=dateFormat1.parse((String)value);
                }
                catch(ParseException exc)
                {
                    try
                    {
                        date=dateFormat2.parse((String)value);
                    }
                    catch(ParseException ex)
                    {
                         JOptionPane.showMessageDialog(null, "Date is wrong. Returning previous value...");
                        return;
                    }
                }
            }
            
            else
            {
                String s=cell.group();
                int c=(int)s.charAt(0)-(int)'A';      // possible dad
                int r=(int)s.charAt(1)-(int)'1';
                date=dates.get(r).get(c);
               
                if (doesPathExist(thisCell, s))        // possibility of ancestry
                {
                    JOptionPane.showMessageDialog(null, "Cycle detected. Returning previous value...");
                    return;
                }
                cellsFathers.get(rowIndex).get(columnIndex).add(s);
                cellsDependentFrom.get(r).get(c).add(thisCell);
            }
            
            
            
            if (operand_found && date!=null)
            {
                Matcher number=patternNumber.matcher((String)value);
                number.find(operand.end());
                calendar.setTime(date);
                calendar.add(Calendar.DATE, Integer.parseInt(operand.group()+number.group()));
                dates.get(rowIndex).set(columnIndex, calendar.getTime());
                
            }
            else 
                dates.get(rowIndex).set(columnIndex, date);
                
            
            cells.get(rowIndex).set(columnIndex, (String)value);
        }
        
        else if (((String)value).matches("^(\\s)*$"))
        {
           dates.get(rowIndex).set(columnIndex, null);   
            
        }
        else if (((String)value).matches("(\\s)*(мин|макс)[(]((\\d{2}\\.\\d{2}\\.\\d{4})|([A-H][1-8]))(,(((\\d{2}\\.\\d{2}\\.(\\d{4})))|([A-H][1-8])))*[)](\\s)*"))
        {
            
            Matcher minMax=patternMinMax.matcher((String)value);
            minMax.find();
            Matcher cell=patternCell.matcher((String)value);
            
                
            Matcher dateMatcher=patternDate.matcher((String)value);
            
            ArrayList<Date> ar=new ArrayList<>();
            Set<String> newFathers=new HashSet<>();
            while (!cell.hitEnd())
            {
                cell.find();
                if (cell.hitEnd())
                    break;
                String s=cell.group();
                int c=(int)s.charAt(0)-(int)'A';      // possible dad
                int r=(int)s.charAt(1)-(int)'1';
                date=dates.get(r).get(c);
               
                if (doesPathExist(thisCell, s))        // possibility of ancestry
                {
                    JOptionPane.showMessageDialog(null, "Cycle detected. Returning previous value...");
                    newFathers.forEach((ss)->
                    {
                        int cc=(int)ss.charAt(0)-(int)'A';      
                         int rr=(int)ss.charAt(1)-(int)'1';
                         cellsFathers.get(rowIndex).get(columnIndex).remove(ss);
                         cellsDependentFrom.get(rr).get(cc).remove(thisCell);
                    });
                    return;
                }
                
                ar.add(date);
                newFathers.add(s);
                cellsFathers.get(rowIndex).get(columnIndex).add(s);
                cellsDependentFrom.get(r).get(c).add(thisCell);
            }
            
            while (!dateMatcher.hitEnd())
            {
                dateMatcher.find();
                if (dateMatcher.hitEnd())
                    break;
                try
                {
                    date=dateFormat2.parse(dateMatcher.group());
                }
                catch(ParseException exc)
                {
                   JOptionPane.showMessageDialog(null, "Date is wrong. Returning previous value...");
                   return;
                }
                
                ar.add(date);
                
            }
            
            switch (minMax.group())
            {
                case "мин":
                    dates.get(rowIndex).set(columnIndex, Collections.min(ar));
                    break;
                case "макс":
                    dates.get(rowIndex).set(columnIndex, Collections.max(ar));
                    break;
                
            }
            cells.get(rowIndex).set(columnIndex, (String)value);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Wrong input. Returning previous value...");
                    return;
        }
        
        
        for (int i=0; i<cellsDependentFrom.get(rowIndex).get(columnIndex).size(); ++i)
        {
            String s=cellsDependentFrom.get(rowIndex).get(columnIndex).get(i);
                int r=(int)s.charAt(1)-(int)'1';
                int c=(int)s.charAt(0)-(int)'A';
                updateCell(cells.get(r).get(c), r, c);
                
        }
        
            this.fireTableDataChanged();
    
    }
    
    boolean doesPathExist(String c1, String c2)
    {
        if (c1.equals(c2))
            return true;
        return cellsDependentFrom.get((int)c1.charAt(1)-(int)'1').get((int)c1.charAt(0)-(int)'A').
                stream().anyMatch((obj)->
                {
                    if (obj.equals(c2))
                        return true;
                    return doesPathExist(obj, c2);
                });
    }
    
    @Override
    public Object getValueAt(int r, int c) 
    {
        if (dates.get(r).get(c)==null)
            return " ";
        return dateFormat2.format(dates.get(r).get(c));
        
    }
    
     @Override
    public String getColumnName(int column) 
    {
        return ((Character)((char)('A'+column))).toString();
    }
     @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

}