package dao;

import domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @return
     */
    public int findTotalCount(int cid, String rName);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rName);

    /**
     * 根据rid查询路线详情
     * @param rid
     * @return
     */
    public Route findByOne(int rid);

}
