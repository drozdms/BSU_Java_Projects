package app.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Mark Drozd
 */
public class KeyLogList extends ListView<String> implements Observer 
{
    ObservableList<String> keyLogList;
      Calendar calendar;
      SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SSS");
    
    public KeyLogList() {
        keyLogList=FXCollections.observableArrayList();
        this.setItems(keyLogList);
        calendar = Calendar.getInstance();
        
    }

    @Override
    public void update(Observable o, Object arg) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        keyLogList.add("'"+(String)arg+"' "+sdf.format(calendar.getTime()));
        this.setItems(keyLogList);
    }

}
