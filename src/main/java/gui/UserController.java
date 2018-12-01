package gui;

import dbutils.Validate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserController {

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    public void onLogin() throws IOException {

        if(Validate.checkUser(loginField.getText(), passwordField.getText()) != null){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }

    }
}
