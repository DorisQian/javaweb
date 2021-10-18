package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * insert语句
 */
public class jdbcDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
//            Class.forName("com.mysql.jc.jdbc.Driver");
            String sql = "insert into Student value (09, 'test', '1111','女')";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/question?useUnicode=true&characterEncoding=UTF-8&userSSL=false;", "root", "123456");
            statement = conn.createStatement();
            int count = statement.executeUpdate(sql); //影响的行数
            System.out.println(count);
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //避免空指针异常
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
