package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ItemFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane pane1;



    @FXML
    void btnCoffeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/coffee_form.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);

    }

    @FXML
    void btnFoodOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/food_form.fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(root);

    }

}

