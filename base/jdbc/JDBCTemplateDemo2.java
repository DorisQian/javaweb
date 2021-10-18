package jdbc;

import com.alibaba.druid.support.json.JSONUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.JDBCUtilsPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCTemplateDemo2 {
    //Junit单元测试，让方法独立执行

    private static JdbcTemplate template = new JdbcTemplate(JDBCUtilsPool.getDataSource());

    /**
     * 修改数据
     */
    @Test
    public void test1() {
        //获取JDBCTemplate对象
        String sql = "update Student set s_sex = '女' where s_id =?";
        int count = template.update(sql, 01);
        System.out.println(count);
    }

    /**
     * 添加一条记录
     */
    @Test
    public void test2() {
        String sql = "insert into Student values (?,?,?,?)";
        int count = template.update(sql, 9, "test", "1111", "女");
        System.out.println(count);
    }

    /**
     * 删除记录
     */
    @Test
    public void test3() {
        String sql = "Delete from Student where s_id=?";
        int count = template.update(sql, 9);
        System.out.println(count);
    }

    /**
     * 查询id是1，并封装成map集合
     * 这个方法查询结果长度只能是1
     */
    @Test
    public void test4() {
        String sql = "select * from Student where s_id=?";
        Map<String, Object> map = template.queryForMap(sql, 1, 2);
        System.out.println(map);
        //{s_id=01, s_name=赵雷, s_birth=1990-01-01, s_sex=男}
    }

    /**
     * 查询所有记录，封装成list,每一个是map，最后封装成list
     */
    @Test
    public void test5() {
        String sql = "select * from Student";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
        System.out.println(list);
    }

    /**
     * 查询所有记录，封装成Student对象(javabean对象)的list集合 自己实现
     */
    @Test
    public void test6() {
        String sql = "select * from Student";
        List<Student> list = template.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                int id = rs.getInt("s_id");
                String name = rs.getString("s_name");
                String sex = rs.getString("s_sex");
                student.setId(id);
                student.setName(name);
                student.setSex(sex);
                return student;
            }
        });

        for (Student student : list) {
            System.out.println(student);
        }
        System.out.println(list);
    }

    /**
     * 查询所有记录，BeanPropertyRowMapper自动封装成Student对象(javabean对象)的list集合
     */
    @Test
    public void test7() {
        String sql = "select * from Student";
        List<Student2> list = template.query(sql, new BeanPropertyRowMapper<Student2>(Student2.class));
        for (Student2 student : list) {
            System.out.println(student);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void test8(){
        String sql = "select count(1) from Student";
        Long count = template.queryForObject(sql, long.class);
        System.out.println(count);
    }

}
