package model;

import db.DB_connection;
import dto.Item;
import model.tm.CartDTO;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Item searchById(String item_id) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_id = ?";

        ResultSet resultSet = CrudUtil.execute(sql, item_id);

        if (resultSet.next()) {
            return new Item(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getDouble(3), resultSet.getString(4));
        }
        return null;
    }

    public static boolean update(List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO dto : cartDTOList) {
            if (!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(CartDTO dto) throws SQLException {
        String sql = "UPDATE item SET item_qty = (item_qty - ?) WHERE item_id = ?";
        return CrudUtil.execute(sql, dto.getQty(), dto.getItem_id());


    }

    public static Integer getTypes(String type) throws SQLException {
        String sql = "SELECT * FROM item WHERE typ=?";
        ResultSet resultSet = CrudUtil.execute(sql, type);
        int count =0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    public static Integer getTypes1(String type) throws SQLException {
        String sql = "SELECT * FROM item WHERE typ=?";
        ResultSet resultSet = CrudUtil.execute(sql, type);
        int count =0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    public static Integer getTypes2(String type2) throws SQLException {
        String sql = "SELECT * FROM item WHERE typ=?";
        ResultSet resultSet = CrudUtil.execute(sql, type2);
        int count =0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    public static Integer getTypes3(String type3) throws SQLException {
        String sql = "SELECT * FROM item WHERE typ=?";
        ResultSet resultSet = CrudUtil.execute(sql, type3);
        int count =0;
        while (resultSet.next()){
            count++;
        }return count;
    }
}

