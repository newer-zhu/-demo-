package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid查询总条数
     * @return
     */
    public int getTotalCount(int cid, String rname);

    /**
     * 根据参数查找页面显示集合
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据rid查询一个
     * @param rid
     * @return
     */
    public Route findOne(int rid);


}
