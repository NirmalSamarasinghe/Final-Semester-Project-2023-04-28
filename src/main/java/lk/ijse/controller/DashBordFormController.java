package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javafx.util.Duration;
import lombok.SneakyThrows;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.ReservationModel;

import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBordFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate11;

    @FXML
    private Label lblTime11;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblDate1;

    @FXML
    private Label lblCusCount;

    @FXML
    private Label lblDate111;

    @FXML
    private Label lblResCount;
    @FXML
    private BarChart<String, Number> chart;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
       AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
       pane.getChildren().clear();
       pane.getChildren().add(anchorPane);

    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);

    }

    public void btnTemOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/Item_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTypeChart();
        loadDateandTime();
        loadCustomerCount();
        loadOrderCount();
        loadReservation();

    }

    private void loadTypeChart() throws SQLException {
        XYChart.Series<String, Number>[] series1 = new XYChart.Series[4];

        series1[0] = new XYChart.Series<>();
        String type = "coffee";
        series1[0].getData().add(new XYChart.Data<>("", ItemModel.getTypes(type)));
        series1[0].setName(type);

        series1[1] = new XYChart.Series<>();
        String type1 = "beverage";
        series1[1].getData().add(new XYChart.Data<>("", ItemModel.getTypes1(type1)));
        series1[1].setName(type1);

        series1[2] = new XYChart.Series<>();
        String type2 = "food";
        series1[2].getData().add(new XYChart.Data<>("", ItemModel.getTypes2(type2)));
        series1[2].setName(type2);

        series1[3] = new XYChart.Series<>();
        String type3 = "cake";
        series1[3].getData().add(new XYChart.Data<>("", ItemModel.getTypes3(type3)));
        series1[3].setName(type3);

        chart.getData().addAll(series1);
    }

    private void loadReservation() throws SQLException {
        int count = ReservationModel.getCount();
        if (count<10){
           lblResCount.setText("0"+count);
        }else {
           lblResCount.setText(String.valueOf(count));
        }

    }

    private void loadOrderCount() {
        try {
            int count = OrderModel.getCount();
            if (count<10){
                lblOrderCount.setText("0"+count);
            }else {
                lblOrderCount.setText(String.valueOf(count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadCustomerCount() {
        try {
            int count = CustomerModel.getCount();
            if (count<10){
                lblCusCount.setText("0"+count);
            }else {
                lblCusCount.setText(String.valueOf(count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sign Up");
    }

    public void btnDashbordOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBord_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);

    }
    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+":"+time.getMinute()+":"+time.getSecond());
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
