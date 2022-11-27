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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Cheff implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        toopings.setCellValueFactory(new PropertyValueFactory<>("toopings"));
        pickupTime.setCellValueFactory(new PropertyValueFactory<>("pickupTime"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(pizzas);

        for (Pizza pizza : Handler.pizzas) {
            if (pizza.getStatus().equalsIgnoreCase("READY to COOK")
                    || pizza.getStatus().equalsIgnoreCase("COOKING")) {
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

    @FXML
    private void btnReadyAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedIndex() != -1) {
            Pizza selectedPizza = tableView.getSelectionModel().getSelectedItem();
            for (Pizza pizz : Handler.pizzas) {
                if (pizz.getOrderId().equals(selectedPizza.getOrderId())) {
                    pizz.setStatus("READY");
		new Alert(Alert.AlertType.INFORMATION,"email is sent to user!").showAndWait();
break;
                }
            }
            pizzas.remove(selectedPizza);
            Handler.savePizzas();
        } else {
            new Alert(Alert.AlertType.ERROR, "select pizza from table").showAndWait();
        }
    }

    @FXML
    private void btnStartPreparingAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedIndex() != -1) {
            Pizza selectedPizza = tableView.getSelectionModel().getSelectedItem();
            for (Pizza pizz : Handler.pizzas) {
                if (pizz.getOrderId().equals(selectedPizza.getOrderId())) {
                    pizz.setStatus("COOKING");
                    selectedPizza.setStatus("COOKING");
                    tableView.refresh();
break;
                }
            }
            Handler.savePizzas();
        } else {
            new Alert(Alert.AlertType.ERROR, "select pizza from table").showAndWait();
        }
    }

}
