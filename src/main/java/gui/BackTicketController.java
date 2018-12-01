package gui;

import dbutils.Validate;
import entities.RacesEntity;
import entities.TicketsEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigInteger;

public class BackTicketController {
    @FXML
    TextField ticketNumField;

    public void onBack(){
        try {
            TicketsEntity ticketsEntity = Validate.deleteTicket(new BigInteger(ticketNumField.getText()));
            RacesEntity racesEntity = Validate.getRace(ticketsEntity.getRaceId());
            racesEntity.setEmptyPlaces(racesEntity.getEmptyPlaces() + "," + ticketsEntity.getPlace());
            Validate.updateRace(racesEntity);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.primaryStage);
            alert.setTitle("Сумма для возврата");
            alert.setHeaderText("Сумма для возврата");
            alert.setContentText(String.valueOf(ticketsEntity.getPrice()));

            alert.showAndWait();
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.primaryStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неверный номер билета");
            alert.setContentText("Введите существующий номер билета");

            alert.showAndWait();
        }
    }

    public void onMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
