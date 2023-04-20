package model;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationModel {
    public static Boolean add(String id, String name, String chairCount, String contact) throws SQLException {
        String sql = "INSERT INTO customer(reservation_id,reservation_customerName,reservation_chair_count,reservation_contact)VALUES (?,?,?,?,?)";

        return CrudUtil.execute(sql,id,name,chairCount,contact);

    }
    public static Boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM table_reservation WHERE id =?";
        return CrudUtil.execute(sql, id);
    }

    public static int getCount() throws SQLException {
        String sql = "SELECT reservation_id FROM table_reservation";
        int count = 0;
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            count++;
        }return count;

    }
}
