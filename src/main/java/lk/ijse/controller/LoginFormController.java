package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.UserModel;

import java.io.IOException;
import java.sql.SQLException;


public class LoginFormController {

    public AnchorPane root;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        try {
            boolean isValid = UserModel.check(userName,password);
            if(isValid){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBord_form.fxml"));
                Scene scene=new Scene(anchorPane);
                Stage stage= (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("DashBord");

            }else {
                new Alert(Alert.AlertType.ERROR,"Incorrect User name or Password !!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }
    public void btnsignUpOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/signUp_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sign Up");

    }
}
