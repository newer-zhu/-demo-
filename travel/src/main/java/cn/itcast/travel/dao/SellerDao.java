package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
     * 查询商家
     * @param id
     * @return
     */
    Seller findSeller(int id);
}
