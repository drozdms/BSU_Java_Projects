package app.view;

import app.MatcherDemo;
import java.text.SimpleDateFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author Mark Drozd
 */
public class StringCheckerOverviewController 
{
    private MatcherDemo mainApp;
    ObservableList<String> templateStatusList=
            FXCollections.observableArrayList("Natural number", "Integer", "Double",
            "Date", "Time", "Email");
    private SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
    
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button button;
    
    @FXML
    private TextArea area;
    
    @FXML
    private void initialize() {
        dateFormat.setLenient(false);
        comboBox.setValue("Natural number");
        comboBox.setItems(templateStatusList);
        
        comboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                areaChanged(area.getText());
        });
        
        area.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) 
            {
               areaChanged(newValue);
                
            }
        });
        
        
    }
   
    
  private void areaChanged(Object newValue)
  {
        switch (comboBox.getValue()) {
            case "Natural number":

                if ((newValue.toString()).matches("[+]?[1-9][0-9]*"))
                {
                    setButtonGreen();
                }
                else
                     setButtonRed();
                break;

            case "Integer":
                if ((newValue.toString()).matches("[+-]?\\d+"))
                    setButtonGreen();
                else
                     setButtonRed();
                break;
            case "Double":
                if ((newValue.toString()).matches("[-+]?(([0-9]+(\\.)?(([0-9]+)?))|([0-9]*(\\.)?(([0-9]+)+)))([eE][-+]?[0-9]+)?"))
                   setButtonGreen();
                else
                    setButtonRed();
                break;
            case "Date":
                if (newValue.toString().matches("((((0[1-9])|([12][0-9]))\\.((0[1-9])|(1[0-2])))|((30)\\.((0[13456789])|(1[0-2])))|((31)\\.((0[13578])|(1[02]))))\\.(\\d{4})"))
                        setButtonGreen();
                else
                    setButtonRed();
                break;
            case "Time":
                if ((newValue.toString()).matches("((([0-1][0-9])|(2[0-3]))(:([0-5][0-9])){1,2})"))
                   setButtonGreen();
                else
                    setButtonRed();
                break;
                
            case "Email":
                if ((newValue.toString()).matches("[A-Za-z0-9_]+(\\.[A-Za-z0-9_]+)*@[a-z]+\\.[a-z]{2,3}"))
                     setButtonGreen();
                else
                    setButtonRed();
                break;
        }
  }
    
  private void setButtonRed()
  {
      button.setStyle("-fx-background-color: #e70000;"
              + "-fx-background-radius: 100; -fx-border-radius:100;");
  }
  
  private void setButtonGreen()
  {
      button.setStyle("-fx-background-color: #34c700;"
              + "-fx-background-radius: 100; -fx-border-radius:100;");
  }
    
  public void setMainApp(MatcherDemo mainApp) {
    this.mainApp = mainApp;
  }
    
}
