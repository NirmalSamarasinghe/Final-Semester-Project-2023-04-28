package model;

import db.DB_connection;
import dto.Customer;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static Boolean add(String customerId, String customerName, String customerAddress, String customerContact) throws SQLException {
        String sql = "INSERT INTO customer(cust_id,cust_name,cust_address,cust_contact)VALUES (?,?,?,?)";
       return CrudUtil.execute(sql,customerId,customerName,customerAddress,customerContact);

    }

    public static List<Customer> getAll() throws SQLException {
        String sql ="SELECT * FROM customer";
        ResultSet resultSet=CrudUtil.execute(sql);
        List<Customer> data= new ArrayList<>();

        while (resultSet.next()){

            data.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return data;
    }

    public static Customer search(String id) throws SQLException {
        String sql ="SELECT * FROM customer WHERE cust_id =?";
        ResultSet resultSet =CrudUtil.execute(sql,id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4
                    ));


        }   return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE cust_id =?";
        return CrudUtil.execute(sql,id);
    }

    public static Boolean update(String id, String name, String address, String contact) throws SQLException {
        String sql = "UPDATE customer SET  cust_name=? , cust_address =? , cust_contact=? WHERE cust_id=?";
    return CrudUtil.execute(sql,name,address,contact,id);
    }

        public static List<String> getIds() throws SQLException {
            Connection con = DB_connection.getInstance().getConnection();
            String sql = "SELECT * FROM Customer";

            List<String> ids = new ArrayList<>();

            ResultSet resultSet = con.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
            return ids;
        }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT * FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static int getCount() throws SQLException {
        String sql = "SELECT cust_id FROM customer";
        int count = 0;
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            count++;
        }return count;
    }

}
