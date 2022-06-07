package com.hspedu.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Jdbc05 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //通过Properties对象获取刚刚自己写好的配置文件[mysql.properties]的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //根据key关键字获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        //1.注册驱动
        Class.forName(driver);
        //相当于Class.forName("com.mysql.jdbc.Driver");
        //当前驱动版本较低，一定要写上这句话，
        //用最新版本的mysql驱动器时，可以不用写，但是还是建议写上这句话
        //Class.forName("com.mysql.jdbc.Driver");//当前驱动版本较低，一定要写上这句话，
        //用最新版本的mysql驱动器时，可以不用写，但是还是建议写上这句话
        //2.得到连接
        Connection connect = DriverManager.getConnection(url, user, password);
        //System.out.println(connection);
        //3.执行sql语句
        String sql = "insert into actor values(null,'吴辉','男','1970-11-11','1555555')";//添加数据
        Statement statement = connect.createStatement();
        statement.executeUpdate(sql);
        //4.关闭连接资源
        connect.close();
        statement.close();
    }
}
