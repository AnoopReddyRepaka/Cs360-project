package com.example.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class History implements Initializable {

    @FXML
    private TableView<Pizza> tableView;
    @FXML
    private TableColumn<Pizza, String> type;
    @FXML
    private TableColumn<Pizza, String> toopings;
    @FXML
    private TableColumn<Pizza, String> pickupTime;
    @FXML
    private TableColumn<Pizza, String> status;

    ObservableList<Pizza> pizzas = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        toopings.setCellValueFactory(new PropertyValueFactory<>("toopings"));
        pickupTime.setCellValueFactory(new PropertyValueFactory<>("pickupTime"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(pizzas);

        for (Pizza pizza : Handler.pizzas) {
            if (pizza.getStudentID().equals(Handler.students.get(Handler.currentSTDIndex).getId())) {
                pizzas.add(pizza);
            }
        }
    }

    @FXML
    private void tableViewMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnMainMenuAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

}
