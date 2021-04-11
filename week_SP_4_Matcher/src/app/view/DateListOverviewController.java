/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.MatcherDemo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author drozd
 */
public class DateListOverviewController {

    private MatcherDemo mainApp;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");
    ObservableList<String> dateList=FXCollections.observableArrayList("Natural number", "Integer", "Double",
            "Date", "Time", "Email");
     
    @FXML
    private TextArea area;
    
    @FXML
    private ListView<String> list=new ListView<>(dateList);
    
    @FXML
    private Button generateButton;

    //private Pattern pattern=Pattern.compile("(\\d{2})\\.(\\d{2})\\.(\\d{4})");
    
    private Pattern pattern=Pattern.compile("((((0[1-9])|([12][0-9]))\\.((0[1-9])|(1[0-2])))|((30)\\.((0[13456789])|(1[0-2])))|((31)\\.((0[13578])|(1[02]))))\\.(\\d{4})");
    
    
    @FXML
    private void initialize() {
        
        dateFormat.setLenient(false);
       // dateList.clear();
        dateList.add("dasad");
        list.setItems(dateList);
        generateButton.setOnAction((ActionEvent event) -> {
            String text=area.getText();
            Matcher matcher=pattern.matcher(text);
            dateList.clear();
            while (!matcher.hitEnd())
            {
                if (matcher.find())
                    dateList.add(matcher.group());
            }
            list.setItems(dateList);
        });
    }    
    
    
    public void setMainApp(MatcherDemo mainApp) {
         this.mainApp = mainApp;
    }
    
}
