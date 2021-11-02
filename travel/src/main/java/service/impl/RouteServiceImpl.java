package service.impl;

import dao.RouteDao;
import dao.RouteImgDao;
import dao.SellerDao;
import dao.impl.RouteDaoImpl;
import dao.impl.RouteImgDaoImpl;
import dao.impl.SellerDaoImpl;
import domain.PageBean;
import domain.Route;
import domain.RouteImg;
import domain.Seller;
import service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();


    @Override
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize, String rName) {
        PageBean<Route> pb = new PageBean<Route>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid, rName);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);


        int start = pageSize * (currentPage - 1);
        List<Route> page = routeDao.findByPage(cid, start, pageSize, rName);
        pb.setList(page);
        return pb;
    }

    @Override
    public Route findOne(int rid) {
        Route route = routeDao.findByOne(rid);
        List<RouteImg> routeImg = routeImgDao.findRouteImg(rid);
        route.setRouteImgList(routeImg);
        Seller seller = sellerDao.findSeller(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
