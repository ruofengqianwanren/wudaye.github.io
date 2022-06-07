package com.hspedu.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 吴辉
 * @version 1.0
 */
@SuppressWarnings("all")
public class JdbcExercise01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql01.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        //1.注册驱动
        Class.forName(driver);
        //2.建立连接
        Connection con = DriverManager.getConnection(url, user, password);
        //4.编写sql语句
        String sql = "select id,name,sex,borndate,phone from actor01";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        //statement.executeUpdate(sql);//执行更新语句
        //执行给定的sql语句，该语句返回单个ResultSet对象
        //5.使用while取出数据
        while (resultSet.next()){//.next方法返回一个boolean值，找到返回true，否则false，
            //该方法第一次会指向结果集第一行的前面，执行一次就往下移动一行
            int id = resultSet.getInt("id");//将查询完的结果集中的id列第一个元素返回给id
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            Date borndate = resultSet.getDate("borndate");
            String phone = resultSet.getString("phone");
            System.out.println(id + "\t"+name+" \t"+sex+"\t"+borndate+"\t"+phone);
        }
        //6.关闭连接资源
        resultSet.close();
        preparedStatement.close();
        con.close();
    }
}
