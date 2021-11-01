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
     * @return
     */
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize);

    /**
     *  根据id查询路线信息
     * @param cid
     * @return
     */
    //public Route findOne(int cid);
}
