package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author drozd
 */
public class KeyLoggerDemo extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Key Logger");
        initRootLayout();
        initializeBoard();
    }

    public void initRootLayout() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KeyLoggerDemo.class.getResource("view/MainPane.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void initializeBoard() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KeyLoggerDemo.class.getResource("view/BoardOverview.fxml"));
            AnchorPane boardOverview = (AnchorPane) loader.load();
            rootLayout.getChildren().add(boardOverview);
            app.view.BoardOverviewController controller = loader.getController();
            primaryStage.getScene().setOnKeyPressed(e->{
                 controller.notifyObservers(e.getCode().toString());
            });
    }

    public static void main(String[] args) {
        launch(args);
        
    }
    
}
