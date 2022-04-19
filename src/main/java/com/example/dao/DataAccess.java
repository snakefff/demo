package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataAccess {

    @Autowired
    DataSource dataSource;

    @Resource
    private JdbcTemplate jdbcTemplate;


    void contextLoads() throws SQLException {
        String sql = "select * from student ";
        List<Map<String,Object>> students= jdbcTemplate.queryForList(sql);
      /*  System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();*/
        System.out.println(students.toString());

    }

    public static void main(String[] args) throws SQLException {
        new DataAccess().contextLoads();
    }
}
