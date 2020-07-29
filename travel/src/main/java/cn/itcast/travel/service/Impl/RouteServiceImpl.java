package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao dao = new RouteDaoImpl();
    RouteImgDao imgDao = new RouteImgDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();
    FavoriteDao favDao = new FavoriteDaoImpl();
    @Override
    public pageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        pageBean<Route> pBean = new pageBean<>();
        //设置当前的页数
        pBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pBean.setPageSize(pageSize);
        //设置总条数
        int totalCount = dao.getTotalCount(cid,rname);
        pBean.setTotalCount(totalCount);
        //设置单页显示的集合
        int start = (currentPage-1) * pageSize;
        List<Route> list = dao.findByPage(cid,start,pageSize,rname);
        pBean.setList(list);
        //设置总页数
        int totalPage = totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize + 1;
        pBean.setTotalPage(totalPage);
        return pBean;
    }

    @Override
    public Route findOne(String rid) {
        int id = Integer.parseInt(rid);
        Route reRoute = dao.findOne(id);

        //设置 线路的图片集合
        List<RouteImg> img = imgDao.findImg(id);
        reRoute.setRouteImgList(img);

        //设置商家信息
        Seller seller = sellerDao.findSeller(reRoute.getSid());
        reRoute.setSeller(seller);

//        设置此线路被多少人收藏
        int count = favDao.countFavorite(id);
        reRoute.setCount(count);
        return reRoute;
    }
}
