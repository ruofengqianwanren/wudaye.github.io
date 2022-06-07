package com.hspedu.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 吴辉
 * @version 1.0
 */
@SuppressWarnings("all")
public class Jdbc02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1.注册驱动
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        //2.得到连接
        String url = "jdbc:mysql://localhost:3306/wuhui";
        String user = "root";
        String password = "123456";
        DriverManager.registerDriver(driver);//注册Driver驱动
        Connection connect = DriverManager.getConnection(url, user, password);
        //System.out.println(connect);
        //3.执行sql
        String sql = "insert into actor values(null,'吴辉','男','1970-11-11','110')";//添加数据
        Statement statement = connect.createStatement();
        statement.executeUpdate(sql);
        //int rows = statement.executeUpdate(sql);//executeUpdate方法，用来执行sql语句，返回一个int值
        //如果返回的是1表示执行sql语句成功，0表示未执行成功
        //System.out.println(rows > 0 ? "成功":"失败");
        //4.关闭连接资源
        statement.close();
        connect.close();

    }
}
