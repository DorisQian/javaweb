package service.impl;

import dao.FavoriteDao;
import dao.impl.FavoriteImpl;
import service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteImpl();

    @Override
    public Boolean isFavorite(int rid, int uid) {
        int favoriteCount = favoriteDao.findByUidAndRid(rid, uid);
        if (favoriteCount == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void add(int rid, int uid) {
        favoriteDao.add(rid, uid);
    }
}
