package model;

import dto.Supplier;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static Boolean save(String id, String name, String contact, String companyName) throws SQLException {
        String sql = "INSERT INTO supplier(supplier_id,supplier_name,supplier_contact,supplier_company_name)VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,id,name,contact,companyName);
    }

    public static Boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM supplier WHERE supplier_id =?";
        return CrudUtil.execute(sql,id);
    }

    public static Supplier search(String id) throws SQLException {
        String sql= "SELECT * FROM supplier WHERE supplier_id = ?";
        ResultSet resultSet =CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4
                    ));
        }
        return null;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Supplier> sup_list= new ArrayList<>();

        while (resultSet.next()){

            sup_list.add(new Supplier(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4)));
        }
        return sup_list;
    }
}
