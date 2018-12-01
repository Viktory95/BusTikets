package gui;

import dbutils.Validate;
import entities.RacesEntity;
import entities.TicketsEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewTicketController implements Initializable {

    @FXML
    DatePicker raceDate;

    @FXML
    ComboBox raceNumComboBox;

    @FXML
    TextField nameField;

    @FXML
    TextField surnameField;

    @FXML
    TextField surname2Field;

    @FXML
    DatePicker birthdayDate;

    @FXML
    TextField birthdayNumField;

    @FXML
    TextField passSeriaField;

    @FXML
    TextField passNumField;

    @FXML
    TextField raceNumField;

    @FXML
    ComboBox placeComboBox;

    @FXML
    TextField priceWithoutNDSField;

    @FXML
    TextField priceWithNDSField;

    @FXML
    Button submitButton;

    public void onPrint() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currDate = LocalDate.now();
        LocalDate birthday = birthdayDate.getValue() == null ? LocalDate.now() : birthdayDate.getValue();
        if ((Period.between(currDate, birthday).getYears() >= 14
                && ("".equals(passNumField.getText()) || "".equals(passSeriaField)))
                || (Period.between(currDate, birthday).getYears() < 14
                && "".equals(birthdayNumField.getText()))
                || "".equals(nameField.getText())
                || "".equals(surnameField.getText())
                || "".equals(surname2Field.getText())
                || "".equals(placeComboBox.getValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.primaryStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Данные введены неверно");
            alert.setContentText("Заполните обязательные для заполнения поля.");

            alert.showAndWait();
        } else {
            RacesEntity racesEntity = (RacesEntity) raceNumComboBox.getSelectionModel().getSelectedItem();
            TicketsEntity ticketsEntity = TicketsEntity.newTicketEntityBuilder()
                    .setBirthday(birthday.atStartOfDay().toString())
                    .setName(nameField.getText())
                    .setSurname(surnameField.getText())
                    .setSurname2(surname2Field.getText())
                    .setPassNum(new BigInteger(passNumField.getText()))
                    .setPassSeria(new BigInteger(passSeriaField.getText()))
                    .setPlace(placeComboBox.getValue().toString())
                    .setBirthdayNum(birthdayNumField.getText())
                    .setRaceNum(raceNumComboBox.getValue().toString())
                    .setPrice(new Double(priceWithNDSField.getText()))
                    .setRaceId(racesEntity.getRaceId())
                    .build();
            racesEntity.setEmptyPlaces(racesEntity.getEmptyPlaces().replace("," + placeComboBox.getValue().toString(), ""));
            Validate.updateRace(racesEntity);
            Validate.saveTicket(ticketsEntity);
            changeRace();
            try {
                Files.write(Paths.get("tickets/" + ticketsEntity.getTicketId() + ".txt"), ticketsEntity.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void changeDate() {

        raceNumComboBox.getItems().clear();

        raceNumComboBox.getItems().addAll(Validate.getRaces(raceDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }

    public void changeRace() {
        RacesEntity racesEntity = (RacesEntity) raceNumComboBox.getSelectionModel().getSelectedItem();
        raceNumField.setText(racesEntity.getRaceName() + ", " + racesEntity.getBusNum());
        priceWithoutNDSField.setText(racesEntity.getPrice().toString());
        double price = racesEntity.getPrice().longValue() * 1.18;
        priceWithNDSField.setText(String.valueOf(price));

        String[] emptyPlaces = racesEntity.getEmptyPlaces().split(",");
        placeComboBox.getItems().clear();
        placeComboBox.getItems().addAll(emptyPlaces);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalDate localDate = LocalDate.now();

        raceDate.setValue(localDate);

        raceNumComboBox.getItems().clear();

        raceNumComboBox.getItems().addAll(Validate.getRaces(raceDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }
}