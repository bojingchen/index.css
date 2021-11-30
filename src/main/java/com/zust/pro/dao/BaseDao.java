package com.zust.pro.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 86178
 */
public class BaseDao {

    public static Connection getCon() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ks?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
            String userName = "root";
            String pwd = "cbjcbj";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int executeUpdate(Connection connection, PreparedStatement pstm,
                                    String sql, Object[] params) throws Exception{
        //params是一个参数
        //Object方法不能从0开始
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i+1, params[i]);
        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

    public static ResultSet executeQuery(Connection connection, PreparedStatement pstm, String sql, ResultSet rs, Object[] params) throws Exception{
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i+1,params[i]);
        }
        rs = pstm.executeQuery();
        return rs;
    }
    public static boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet rs){
        boolean flag = true;
        if(rs != null){
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(pstm != null){
            try {
                pstm.close();
                pstm = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
