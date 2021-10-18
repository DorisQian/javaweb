package jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtilsPool;

/**
 * JDBCTemplate 入门
 */

public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //创建对象，依赖于数据源datasource 连接池
        //JdbcTemplate 自己会创建连接并释放
        JdbcTemplate template = new JdbcTemplate(JDBCUtilsPool.getDataSource());
        //调用方法
        String sql = "update Student set s_sex = '女' where s_id =?";
        int count = template.update(sql,01);
        System.out.println(count);
    }
}
