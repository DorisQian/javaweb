package dao;

import domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据路线id查图片
     * @param rid
     * @return
     */
    public List<RouteImg> findRouteImg(int rid);
}
