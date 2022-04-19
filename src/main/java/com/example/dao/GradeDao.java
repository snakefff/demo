package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GradeDao {
    ConnectDB connectDB = null;
    Connection connection = null;

    public GradeDao(){
        this.connectDB = new ConnectDB();
    }

    public void insert(){
        connectDB.getConnection();
        String sql = "insert into Grande(student_id,subject_id,school_year,term,exam_type,grade,ranking) values (3,2,2022,'2022',1,118.5,2)";

        try {
            connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
