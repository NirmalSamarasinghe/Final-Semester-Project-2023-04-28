package model;

import dto.Customer;
import dto.Employee;
import util.CrudUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public static List<Employee> getAll() throws SQLException {
        String  sql = "SELECT * FROM employee";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Employee> data = new ArrayList<>();
        while (resultSet.next()){
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    public static Boolean save(String id, String name, String address, String contact) throws SQLException {
        String sql = "INSERT INTO employee (employee_id,employee_name,employee_address,employee_contact)VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,id,name,address,contact);
    }

    public static Employee searchCustomerId(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        List<Employee> data = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if (resultSet.next()){
            String employee_id = resultSet.getString(1);
            String employee_name = resultSet.getString(2);
            String employee_address = resultSet.getString(3);
            String employee_contact = resultSet.getString(4);
            return new Employee(employee_id,employee_name,employee_address,employee_contact);
        }
        return null;
    }

    public static Boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM employee WHERE employee_id = ?";

        return CrudUtil.execute(sql,id);
    }


    public static Boolean update(String id, String name, String address, String contact) throws SQLException {
        String sql ="UPDATE employee SET employee_name =?,employee_address =?,employee_contact =? WHERE employee_id =?";
        return CrudUtil.execute(sql,name,address,contact,id);
    }
}
