package web.servlet;

import domain.PageBean;
import domain.Route;
import domain.User;
import service.FavoriteService;
import service.RouteService;
import service.impl.FavoriteServiceImpl;
import service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String cidStr = request.getParameter("cid");
        String pageSizeStr = request.getParameter("pageSize");
        String rName = request.getParameter("rName");

        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        PageBean<Route> routePageBean = routeService.queryPage(cid, currentPage, pageSize, rName);
        writeValue(routePageBean, response);
    }

    /**
     * 查询路线详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid = Integer.parseInt(ridStr);
        Route route = routeService.findOne(rid);
        writeValue(route,response);
    }

    /**
     * 收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid ;
        if (user != null){
            uid = user.getUid();
        }else{
            uid = 0;
        }
        Boolean flag = favoriteService.isFavorite(Integer.parseInt(rid), uid);
        writeValue(flag, response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return;
        } else {
            int uid = user.getUid();
            favoriteService.add(Integer.parseInt(rid), uid);
        }
    }
}
