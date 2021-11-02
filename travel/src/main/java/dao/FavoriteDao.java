package dao;


public interface FavoriteDao {
    /**
     * 查询用户是否收藏
     * @param uid
     * @param rid
     * @return
     */
    public int findByUidAndRid(int rid, int uid);

    /**
     * 查询收藏次数
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * 增加收藏
     * @param rid
     * @param uid
     */
    public void add(int rid, int uid);
}
