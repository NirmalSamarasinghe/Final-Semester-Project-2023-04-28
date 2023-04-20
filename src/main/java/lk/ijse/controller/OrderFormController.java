package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import db.DB_connection;
import dto.Customer;
import dto.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.PlaceOrderModel;
import model.tm.CartDTO;
import model.tm.PlaceOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    @FXML
    public TableColumn colBtnAction;
    @FXML
    private AnchorPane OrederPane;

    @FXML
    private ComboBox<String> comCustId;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustName;

    @FXML
    private ComboBox<String> comItemId;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblTotal;
    @FXML
    private JFXButton btnAddtoCart;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        setOrderDate();
        loadCustomerIds();
        loadItemCodes();
        generateNextOrderId();

    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colBtnAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String nextId = OrderModel.generateNextOrderId();
            lblOrderId.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadItemCodes() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> item = ItemModel.getCodes();
            for (String itemCodes : item) {
                observableList.add(itemCodes);
            }
            comItemId.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> customer = CustomerModel.getCodes();
            for (String customerId : customer) {
                observableList.add(customerId);
            }
            comCustId.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblTotal.setText(String.valueOf(netTotal));
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddtoCart.fire();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String oId = lblOrderId.getText();
        String cusId = comCustId.getValue();
        String total = lblTotal.getText();

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            PlaceOrderTM tm = obList.get(i);

            CartDTO cartDTO = new CartDTO(tm.getItemId(), tm.getQty(), tm.getUnitPrice());
            cartDTOList.add(cartDTO);
        }

        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(oId, cusId, total, cartDTOList);
            if (isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = comItemId.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }

        PlaceOrderTM tm = new PlaceOrderTM(code, description, qty, unitPrice, total, btnRemove);

        obList.add(tm);
        tblOrderCart.setItems(obList);
        calculateNetTotal();

        txtQty.setText("");
    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {

            int index = tblOrderCart.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                new Alert(Alert.AlertType.ERROR, "Please select row first !!!").show();
            } else {

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (result.orElse(no) == yes) {


                    obList.remove(index);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                }
            }

        });
    }


    @FXML
    void cmbCustIdOnAction(ActionEvent event) {
        try {
            String custId = comCustId.getSelectionModel().getSelectedItem();
            Customer customer = CustomerModel.search(custId);
            lblCustName.setText(customer.getCus_name());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        try {
            String item_id = comItemId.getSelectionModel().getSelectedItem();
            Item item = ItemModel.searchById(item_id);
            lblDescription.setText(item.getItem_name());
            lblQtyOnHand.setText(item.getItem_qty());
            lblUnitPrice.setText(String.valueOf(item.getItem_price()));

            txtQty.requestFocus();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnReoprtOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("F:\\Final Project 1st Semester\\New folder\\Movin Bird\\src\\main\\resources\\report\\Cherry.jrxml");
        String sql = "SELECT * FROM orders";

        JRDesignQuery updateQuary = new JRDesignQuery();
        updateQuary.setText(sql);

        jasperDesign.setQuery(updateQuary);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DB_connection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint, false);
    }
}



