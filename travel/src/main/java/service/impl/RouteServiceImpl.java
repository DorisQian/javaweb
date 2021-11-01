package service.impl;

import dao.RouteDao;
import dao.impl.RouteDaoImpl;
import domain.PageBean;
import domain.Route;
import service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize) {
        PageBean<Route> pb = new PageBean<Route>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);


        int start = pageSize * (currentPage - 1);
        List<Route> page = routeDao.findByPage(cid, start, pageSize);
        pb.setList(page);
        return pb;
    }

//    @Override
//    public Route findOne(int cid) {
//        return null;
//    }
}
