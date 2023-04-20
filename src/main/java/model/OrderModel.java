package model;

import db.DB_connection;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderModel {

    public static String generateNextOrderId() throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            String id = resultSet.getString(1);
            return splitOrderId(id);
        }
        return splitOrderId(null);
    }

    public static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null){
            String[] strings = currentOrderId.split("O0");
            int index = Integer.parseInt(strings[1]);
            index++;
            return "O0"+index;
        }
        return "O001";
    }

    public static boolean add(String oId, LocalDate date, String cusId, String total) throws SQLException {
        String sql = "INSERT INTO orders(order_id,order_date,cust_id,order_payment) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,oId,date,cusId,total);

    }

    public static int getCount() throws SQLException {
        String sql = "SELECT order_id FROM orders";
        int count = 0;
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            count++;
        }return count;
    }
}
