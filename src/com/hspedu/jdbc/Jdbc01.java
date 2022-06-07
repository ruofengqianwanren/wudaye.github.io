package com.hspedu.jdbc;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.Properties;

/**
 * @author 吴辉
 * @version 1.0
 */
public class Jdbc01 {
    public static void main(String[] args) throws SQLException {

        //1.注册驱动
        Driver driver = new Driver();//创建driver(即驱动程序)对象
        //2.得到连接
        //(1)jdbc:mysql:// 规定好的格式，表示协议，通过jdbc的方式连接mysql
        //(2)localhost 可以是本地主机名称，或者远程主机的ip地址
        //(3)3306 表示mysql监听的端口号
        //(4)wuhui 表示要连接的mysql数据库的名称
        String url = "jdbc:mysql://localhost:3306/wuhui";
        //将用户名和密码放入到Properties 对象中
        Properties properties = new Properties();
        properties.setProperty("user","root");//用户名
        properties.setProperty("password","123456");//密码
        Connection connect = driver.connect(url, properties);//Connection(即网络连接)，
                                                            // 用来连接java和mysql，起到桥梁的作用
        //3.执行sql
        //String sql = "insert into actor values(null,'刘德华','男','1970-11-11','110')";//添加数据
        //String sql ="update actor set name ='周星驰'where id =1";//修改数据
        String sql = "delete from actor where id = 1";
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);//executeUpdate方法，用来执行sql语句，返回一个int值
        //如果返回的是1表示执行sql语句成功，0表示未执行成功
        System.out.println(rows > 0 ? "成功":"失败");
        //4.关闭连接资源
        statement.close();
        connect.close();
    }

}
