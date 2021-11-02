package service;

public interface FavoriteService {
    /**
     * 用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public Boolean isFavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    public void add(int rid, int uid);

}
