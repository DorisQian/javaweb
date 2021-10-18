package jdbc;

import util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 1、通过键盘录入用户名密码
 * 2、判断是否登录成功
 */
public class jdbcDemo9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        boolean flag = new jdbcDemo9().login2(username, password);
        if (flag) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

    }

    /**
     * 判断值与库中是否匹配，返回boolean
     *
     * @param username username
     * @param password password
     * @return
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = ("select * from user where username=\'" + username + "\' and password=\'" + password + '\'');
            System.out.println(sql);
            rs = statement.executeQuery(sql);
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(rs, statement, conn);
        }
        return false;
    }

    /**
     * 使用prepareStatement
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login2(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        Connection conn = null;
        PreparedStatement presta = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = ("select * from user where username = ? and password = ?");
            presta = conn.prepareStatement(sql);
            presta.setString(1, username);
            presta.setString(2, password);
            rs = presta.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(rs, presta, conn);
        }
        return false;
    }
}
