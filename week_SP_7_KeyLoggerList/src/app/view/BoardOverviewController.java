package app.view;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;

/**
 *
 * @author Mark Drozd
 */
public class BoardOverviewController extends Observable
{
    

   @FXML
   private KeyLogList keyLogList=new KeyLogList();
   
   
   @FXML
   private KeyPressedIndicator keyPressed=new KeyPressedIndicator();
   
   
    @FXML
    private void initialize() {
        this.addObserver(keyLogList);
        this.addObserver(keyPressed);
    }    

    @Override
    public void notifyObservers(Object message) {
        this.setChanged();
        super.notifyObservers(message);
        
    }
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }
    
}
