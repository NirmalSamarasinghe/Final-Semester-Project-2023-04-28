package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EmployeeModel;
import model.tm.EmployeeTM;
import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeeFormController implements Initializable {

    @FXML
    public JFXTextField txtId;
    @FXML
    public JFXTextField txtAddress;
    @FXML
    public JFXTextField txtName;
    @FXML
    public JFXTextField txtContact;
    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpAddress;

    @FXML
    private TableColumn<?, ?> colEmpContact;

    @FXML
    private TableView<EmployeeTM> employeeTbl;


    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
           Employee employee = EmployeeModel.searchCustomerId(id);
           if (employee != null){
               txtId.setText(employee.getEmployee_id());
               txtName.setText(employee.getEmployee_name());
               txtAddress.setText(employee.getEmployee_address());
               txtContact.setText(employee.getEmployee_contact());
           }else {
               new Alert(Alert.AlertType.ERROR,"Employee not founded").show();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
        void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            Boolean isDeleted = EmployeeModel.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Is Deleted").show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();

    }
        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();

            try {
                Boolean isSaved = EmployeeModel.save(id,name,address,contact);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Is Saved").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Is not Saved").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();

            try {
                Boolean isUpdated  = EmployeeModel.update(id,name,address,contact);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Is Updated").show();
                    getAll();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Is not Updated").show();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        getAll();

    }
    private void getAll()  {
         ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();
         try {
             List<Employee> employeeList = EmployeeModel.getAll();
             for (Employee employee:employeeList) {
                 observableList.add(new EmployeeTM(
                         employee.getEmployee_id(),
                         employee.getEmployee_name(),
                         employee.getEmployee_address(),
                         employee.getEmployee_contact()
                 ));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
        employeeTbl.setItems(observableList);
    }
    void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("employee_address"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("employee_contact"));
    }
}
