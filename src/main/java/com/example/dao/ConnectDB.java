package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    String jdbcUrl = "jdbc:mysql://192.168.132.101:3306/test?useUnicode=true&characterEncoding=UTF-8";
    String className = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(jdbcUrl,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }
        return  connection;
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
            System.out.println("连接已关闭");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("连接关闭失败");
        }
    }
}
