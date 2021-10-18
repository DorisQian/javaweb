package jdbc;

import java.sql.*;

/**
 * ResultSet DQL
 */
public class jdbcDemo6 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/question?useUnicode=true&characterEncoding=UTF-8&userSSL=false;", "root", "123456");
            statement = conn.createStatement();
            String sql = "select * from Student";
            set = statement.executeQuery(sql);
            //游标向下移动一行，取第一行数据 boolean next() 判断是否是最后一行，是返回false
            set.next();
            //获取数据 getxxx()
            int id = set.getInt(1);
            String s_name = set.getString("s_name");
            System.out.println("id:" + id + " name:" + s_name);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            if (set != null) {
                try {
                    set.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    conn.close();
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