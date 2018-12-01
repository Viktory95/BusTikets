package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {

    public void onNewTicket() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("newTicket.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void onBackTicket() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("backTicket.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
