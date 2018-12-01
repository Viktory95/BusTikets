package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Vi on 25.11.2018.
 */
public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public  void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Bus tickets");
        runStage(primaryStage);
    }

    public static void runStage(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
