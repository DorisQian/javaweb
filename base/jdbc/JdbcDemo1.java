package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/question", "root", "123456");
        //定义sql语句
        String sql  = "update Student set s_sex = '女' where s_id =01";
        // 获取执行sql的对象 statement
        Statement statement = conn.createStatement();
        //执行sql
        int count = statement.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        //释放资源
        statement.close();
        conn.close();

    }
}

/*
事务
conn.setAutoCommit(false);最开始设置事务管理
conn.commit();
catch中进行回滚 conn.rollback()  前提 conn != null
 */
