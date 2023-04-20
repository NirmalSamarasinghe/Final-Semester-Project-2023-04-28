package model;

import db.DB_connection;
import model.tm.CartDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderModel {

    public static boolean placeOrder(String oId, String cusId, String total, List<CartDTO> cartDTOList) throws SQLException {
        Connection con = null;

        try {
            con = DB_connection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isAdded = OrderModel.add(oId, LocalDate.now(), cusId, total);

            if (isAdded) {
                boolean isSaved = OrderDetailsModel.add(oId, cartDTOList);

                if (isSaved) {
                    boolean isUpdated = ItemModel.update(cartDTOList);
                    if (isUpdated) {
                        con.commit();
                        return true;

                    }
                }


            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }


    }
}
