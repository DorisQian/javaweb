package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DDL 创建表 不常用
 */
public class jdbcDemo5 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/question?useUnicode=true&characterEncoding=UTF-8&userSSL=false;", "root", "123456");
            statement = conn.createStatement();
            String sql = "create table student (id int, name varchar (10))";
            int count = statement.executeUpdate(sql);
            System.out.println(count); // 0

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
