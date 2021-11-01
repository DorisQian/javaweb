package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Category;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import service.CategoryService;
import util.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //缓存获取 按照id顺序
        Jedis jedis = JedisUtil.getJedis();
        //使用sortedset排序查询
        //Set<String> category = jedis.zrange("category", 0, -1);//0 -1 查询所有
        //查询排序的权重(分数)和那么，存储时排序存的是score和key
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        if (category == null || category.size() == 0) {
            cs = categoryDao.findAll();
            //存储到redis中，key为category
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            //将set存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : category) {
                Category ca = new Category();
                ca.setCname(tuple.getElement());
                ca.setCid((int) tuple.getScore());
                cs.add(ca);
            }
        }
        return cs;
    }
}
