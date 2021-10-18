package jdbc;

import util.JDBCUtilsPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo {
    public static void main(String[] args) {
        /**
         * 完成添加操作
         */
        //获取连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtilsPool.getConnection();
            String sql = "insert into Student values (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,9);
            pstmt.setString(2,"test");
            pstmt.setString(3,"1111");
            pstmt.setString(4,"女");
            int i = pstmt.executeUpdate();
            System.out.println(i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtilsPool.close(pstmt,conn);
        }

    }
}
