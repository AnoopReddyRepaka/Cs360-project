package com.example.test;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class StudentController implements Initializable {

    @FXML
    private ComboBox<String> cbType;
    @FXML
    private ComboBox<String> cbPickupTime;
    @FXML
    private CheckBox chkMushroom;
    @FXML
    private CheckBox chkOnion;
    @FXML
    private CheckBox chkOlives;
    @FXML
    private CheckBox chkExtraCheese;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateData();
    }

    @FXML
    private void btnStudentAction(ActionEvent event) {
        if (cbType.getSelectionModel().getSelectedIndex() != -1) {
            if (toopingsSelected()) {
                if (cbPickupTime.getSelectionModel().getSelectedIndex() != -1) {
                    validateStudent();
                    if (Handler.currentSTDIndex != -1) {

                        String toopings = "";
                        if (chkMushroom.isSelected()) {
                            toopings += "Mushroom, ";
                        }
                        if (chkOnion.isSelected()) {
                            toopings += "Onion, ";
                        }
                        if (chkOlives.isSelected()) {
                            toopings += "Olives, ";
                        }
                        if (chkExtraCheese.isSelected()) {
                            toopings += "Extra Cheese";
                        }

                        String orderId = "1001";
                        if (!Handler.pizzas.isEmpty()) {
                            orderId = String.valueOf(Integer.parseInt(Handler.pizzas
                                    .get(Handler.pizzas.size() - 1)
                                    .getOrderId()) + 1);
                        }

                        Pizza pizza = new Pizza(orderId, Handler.students.get(Handler.currentSTDIndex).getId(),
                                cbType.getSelectionModel().getSelectedItem(),
                                toopings, cbPickupTime.getSelectionModel().getSelectedItem(),
                                "ACCEPTED");
                        Handler.pizzas.add(pizza);
                        new Alert(Alert.AlertType.INFORMATION, "Successfull").showAndWait();
                        clear();
                        Handler.savePizzas();

                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "select pickupTime").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "select toopings").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "select type").showAndWait();
        }
    }

    @FXML
    private void btnHistoryAction(ActionEvent event) throws IOException {

        validateStudent();
        if (Handler.currentSTDIndex != -1) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentHistory.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        clear();

    }

    @FXML
    private void btnMainMenuAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    private void validateStudent() {

        TextInputDialog dialog = new TextInputDialog("enter student ID here");
        dialog.setTitle("Validate Student ID");
        dialog.setHeaderText("Enter your ID:");
        dialog.setContentText("Student ID:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent((String id) -> {
            boolean flag = false;
            String typedID = id;
            if (typedID != null) {
                for (int i = 0; i < Handler.students.size(); i++) {
                    if (Handler.students.get(i).getId().equals(typedID)) {
                        Handler.currentSTDIndex = i;
                        flag = true;
                    }
                }
                if (!flag) {
                    new Alert(Alert.AlertType.ERROR, "invalid student id").showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "student id required").showAndWait();
            }

        });
    }

    private void clear() {
        cbType.getSelectionModel().select(-1);
        chkMushroom.setSelected(false);
        chkOnion.setSelected(false);
        chkOlives.setSelected(false);
        chkExtraCheese.setSelected(false);
        cbPickupTime.getSelectionModel().select(-1);
        Handler.currentSTDIndex = -1;
    }

    private void populateData() {
        cbType.getItems().add("peperoni");
        cbType.getItems().add("vegetable");
        cbType.getItems().add("cheese");

        int currentHour = Integer.parseInt(
                new SimpleDateFormat("HH").format(new Date()));

        for (int i = currentHour + 1; i < 23; i++) {
            cbPickupTime.getItems().add(i + ":00");
            cbPickupTime.getItems().add(i + ":30");
        }
    }

    private boolean toopingsSelected() {
        return chkMushroom.isSelected() || chkOnion.isSelected()
                || chkOlives.isSelected() || chkExtraCheese.isSelected();
    }

}
