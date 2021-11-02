package service;

import domain.PageBean;
import domain.Route;

/**
 * 线路Service
 */
public interface RouteService {
    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rName 模糊查找
     * @return
     */
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize, String rName);

    /**
     *  根据id查询路线详细信息
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
