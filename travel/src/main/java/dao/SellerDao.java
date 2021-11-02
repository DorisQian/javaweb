package dao;

import domain.Seller;

public interface SellerDao {
    /**
     * 查询商家信息
     * @return
     */
    public Seller findSeller(int sid);
}
