package jdbc;

import util.JDBCUtils;

import java.sql.*;
import java.util.*;

/**
 * 定义一个方法，查询student表的数据将其封装成对象，让后装在集合，返回
 */
public class jdbcDemo8 {
    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Student> list = new jdbcDemo8().findAll2();
        System.out.println(list);
    }

    /**
     * 查询所有student对象
     *
     * @return list
     */
    public List<Student> findAll() {

        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;
        List<Student> list = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/question?useUnicode=true&characterEncoding=UTF-8&userSSL=false;", "root", "123456");
            statement = conn.createStatement();
            String sql = "select * from Student";
            set = statement.executeQuery(sql);
            //游标向下移动一行，取第一行数据 boolean next() 判断是否是最后一行，是返回false
            Student stu = null;
            list = new ArrayList<Student>();
            while (set.next()) {
                //获取数据 getxxx()
                int id = set.getInt(1);
                String s_name = set.getString("s_name");
                String s_sex = set.getString("s_sex");
                stu = new Student();
                stu.setId(id);
                stu.setName(s_name);
                stu.setSex(s_sex);
                //装载集合
                list.add(stu);
            }
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
        return list;
    }

    /**
     * 演示JDBC工具类
     *
     * @return list
     */
    public List<Student> findAll2() {

        Connection conn = null;
        Statement statement = null;
        ResultSet set = null;
        List<Student> list = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "select * from Student";
            set = statement.executeQuery(sql);
            //游标向下移动一行，取第一行数据 boolean next() 判断是否是最后一行，是返回false
            Student stu = null;
            list = new ArrayList<Student>();
            while (set.next()) {
                //获取数据 getxxx()
                int id = set.getInt(1);
                String s_name = set.getString("s_name");
                String s_sex = set.getString("s_sex");
                stu = new Student();
                stu.setId(id);
                stu.setName(s_name);
                stu.setSex(s_sex);
                //装载集合
                list.add(stu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(set, statement, conn);
        }
        return list;
    }
}
