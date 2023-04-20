package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.CustomerModel;
import model.ReservationModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {

    @FXML
    private TableView<?> reservationTbl;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colCustomer;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colChair;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtChairCount;

    @FXML
    private JFXTextField txtCustomer;


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtCustomer.clear();
        txtChairCount.clear();
        txtContact.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Boolean isDeleted = ReservationModel.delete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Is Deleted").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Is not Delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtCustomer.getText();
        String  chairCount = txtChairCount.getText();
        String contact = txtContact.getText();

        try {
            Boolean isAdded = CustomerModel.add(id,name,chairCount,contact);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is Added").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer is not Added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        getAll();
    }

    void getAll(){

    }

    private void setCellValueFactory() {
    }
}
