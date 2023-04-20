package model;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean check(String userName, String password) throws SQLException {
        String sql ="SELECT * FROM user WHERE user_name =?";
        ResultSet resultSet=CrudUtil.execute(sql,userName);

        if(resultSet.next()){
            if(resultSet.getString(2).equals(password)){
                return true;
            }
        }
        return false;
    }
}
