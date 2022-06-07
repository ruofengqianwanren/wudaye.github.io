package com.hspedu.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author 吴辉
 * @version 1.0
 */
@SuppressWarnings("all")
public class PreparedStatement_ {
    //PreparedStatement 对sql语句预处理
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //对输入的账号密码与数据库的数据进行比较，查询账号密码是否正确
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = scanner.nextLine();
        System.out.println("请输入密码");
        String pwd= scanner.nextLine();
        //1.引入配置文件mysql02.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql02.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        //2.注册驱动
        Class.forName(driver);
        //3.建立连接
        Connection con = DriverManager.getConnection(url, user, password);
        //4.写sql语句
        String sql = "select name,pwd from admin where name=? and pwd=?";
        //5.对sql语句预处理
        //.prepareStatement(sql);方法，返回一个PreparedStatement对象，
        // 所以用PreparedStatement preparedStatement来接收
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        //对 ? 赋值
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,pwd);
        //6.执行sql语句中的select语句用executeQuery方法
        //  执行sql语句中的dml(即更新)语句，包括update,insert,delete语句 用executeUpdate方法
        ResultSet resultSet = preparedStatement.executeQuery();//查询后返回一个ResuletSet结果集
        if(resultSet.next()){//如果结果集里有元素，就表明根据输入的账号密码确实找到了数据库里相同的数据，返回true，否则false
            System.out.println("账号密码正确");
        }else {
            System.out.println("账号密码错误");
        }
        //7.关闭资源
        resultSet.close();
        preparedStatement.close();
        con.close();
    }
}
