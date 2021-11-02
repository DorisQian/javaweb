package dao.impl;

import dao.FavoriteDao;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Date;

public class FavoriteImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findByUidAndRid(int rid, int uid) {
        String sql = "select count(1) from tab_favorite where rid=? and uid=?";
        return template.queryForObject(sql, Integer.class, rid, uid);
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(1) from tab_favorite where rid=?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }
}
