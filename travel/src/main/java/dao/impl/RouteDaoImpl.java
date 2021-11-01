package dao.impl;

import dao.RouteDao;
import domain.Route;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rName) {
        String sql = "select count(1) from tab_route where 1=1 ";
        List param = new ArrayList();
        if (cid !=0){
            sql += "and cid=? ";
            param.add(cid);
        }
        if (rName != null && rName.length() > 0){
            sql += "and rname like ? ";
            param.add("%"+rName+"%");
        }
        return template.queryForObject(sql, Integer.class, param.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rName) {
        String sql = "select * from tab_route where 1 = 1 ";
        List param = new ArrayList();
        if (cid !=0){
            sql += "and cid=? ";
            param.add(cid);
        }
        if (rName != null && rName.length() > 0){
            sql += "and rname like ? ";
            param.add("%"+rName+"%");
        }
        sql += " limit ? , ? ";
        param.add(start);
        param.add(pageSize);
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), param.toArray());
    }
}
