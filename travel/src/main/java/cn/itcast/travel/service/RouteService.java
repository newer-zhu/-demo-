package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

public interface RouteService {
    /**
     * 查询一页要显示的线路
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    public pageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据rid查询一条线路
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
