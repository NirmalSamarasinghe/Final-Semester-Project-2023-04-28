package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Supplier;
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
import model.CustomerModel;
import model.SupplierModel;
import model.tm.CustomerTM;
import model.tm.SupplierTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private TableView<SupplierTM> supplierTbl;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String companyName = txtCompanyName.getText();

        try {
            Boolean isSaved = SupplierModel.save(id, name, contact, companyName);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Added").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier is not Added").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtCompanyName.clear();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Boolean isDeleted = SupplierModel.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Is Deleted").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Is not Deleted").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Supplier supplier = SupplierModel.search(id);
            if (supplier != null) {
                txtName.setText(supplier.getSupplier_name());
                txtContact.setText(supplier.getSupplier_contact());
                txtCompanyName.setText(supplier.getSupplier_company_name());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        getAll();
    }

    void getAll() {
        try {
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
            List<Supplier> supplierList = SupplierModel.getAll();

            for(Supplier supplier: supplierList) {

                obList.add(new SupplierTM(supplier.getSupplier_id(),
                        supplier.getSupplier_name(),supplier.getSupplier_contact(),
                        supplier.getSupplier_company_name()

                ));
            }
            supplierTbl.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }


    }

    void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("supplier_contact"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("supplier_company_name"));
    }
}
