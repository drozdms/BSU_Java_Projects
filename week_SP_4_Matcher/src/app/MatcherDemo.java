package app;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MatcherDemo extends Application {

    private Stage primaryStage;
    private TabPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Regular Expressions");
        
        initRootLayout();

        showStringCheckerOverview();
    }

    public void initRootLayout() throws IOException {
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MatcherDemo.class.getResource("view/TabbedPane.fxml"));
            rootLayout = (TabPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void showStringCheckerOverview() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MatcherDemo.class.getResource("view/StringCheckerOverview.fxml"));
            AnchorPane stringCheckerOverview = (AnchorPane) loader.load();
            rootLayout.getTabs().get(0).setContent(stringCheckerOverview);
            app.view.StringCheckerOverviewController controllerFirst = loader.getController();
            controllerFirst.setMainApp(this);
            FXMLLoader lloader=new FXMLLoader();
            lloader.setLocation(MatcherDemo.class.getResource("view/DateListOverview.fxml"));
            AnchorPane dateListOverview=(AnchorPane)lloader.load();
            rootLayout.getTabs().get(1).setContent(dateListOverview);
            app.view.DateListOverviewController controllerSecond=lloader.getController();
            controllerSecond.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}