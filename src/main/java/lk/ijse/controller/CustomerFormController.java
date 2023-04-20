package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import db.DB_connection;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CustomerModel;
import model.tm.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import regs.Regx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    @FXML
    private TableView<CustomerTM> customerTable;
    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerContact;

    @FXML
    private TableColumn<?, ?> colRemove;
    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerContact;

    @FXML
    private JFXTextField txtCustomerAddress;


    @FXML
    private AnchorPane root;

    @FXML
    void customerAddressOnkeyReleased(KeyEvent event) {

    }

    @FXML
    void customerContactOnkeyReleased(KeyEvent event) {

    }

    @FXML
    void customerIdOnkeyReleased(KeyEvent event) {
        if (!txtCustomerId.getText().matches(Regx.getCustomerIdPattern().pattern())){
            txtCustomerId.setStyle("-fx-text-fill: red");
        }else txtCustomerId.setStyle("-fx-text-fill: black");

    }

    @FXML
    void customerNameOnkeyReleased(KeyEvent event) {

    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerContact = txtCustomerContact.getText();

        try {
            Boolean isAdded = CustomerModel.add(customerId,customerName,customerAddress,customerContact);
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
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();

        try {
            boolean isDeleted =CustomerModel.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"customer is Deleted").show();
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String contact = txtCustomerContact.getText();

        try {
            Boolean isUpdated = CustomerModel.update(id,name,address,contact);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"is Update").show();
                getAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void getAll() {
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<Customer> cusList = CustomerModel.getAll();

            for(Customer customer : cusList) {

                Button btnRemove = new Button("Remove");
                btnRemove.setCursor(Cursor.HAND);

                obList.add(new CustomerTM(
                        customer.getCus_id(),
                        customer.getCus_name(),
                        customer.getCus_address(),
                        customer.getCus_contact(),btnRemove
                ));
            }
            customerTable.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }

    }

    void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("cus_address"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("cus_contact"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        try {
            Customer customer = CustomerModel.search(id);
            if (customer != null) {
                txtCustomerName.setText(customer.getCus_name());
                txtCustomerAddress.setText(customer.getCus_address());
                txtCustomerContact.setText(customer.getCus_contact());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
    }
}
