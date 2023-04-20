package model;

import model.tm.CartDTO;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModel {
    public static boolean add(String oId, List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO dto : cartDTOList) {
            if (!save(oId, dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, CartDTO dto) throws SQLException {
        String sql = "INSERT INTO orders_details(order_id, order_qty,item_id) VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, oId, dto.getQty(), dto.getItem_id());
    }
}
