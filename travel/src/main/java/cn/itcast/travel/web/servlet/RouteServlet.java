package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.Impl.FavoriteServiceImpl;
import cn.itcast.travel.service.Impl.RouteServiceImpl;
import cn.itcast.travel.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favService = new FavoriteServiceImpl();

    /**
     * 查询整页的展示的数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");

        //处理数据
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1;//不传递参数默认第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 5;//初始值是5
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        pageBean<Route> pBean = service.pageQuery(cid, currentPage, pageSize,rname);

        writeValue(pBean, response);

    }

    /**
     * 根据id查询详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String rid = request.getParameter("rid");
        RouteService service = new RouteServiceImpl();
        Route route = service.findOne(rid);
        writeValue(route,response);

    }

    /**
     * 判断是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {

        String rid = request.getParameter("rid");
        User user =(User) request.getSession().getAttribute("User");
        int uid = 0;
        if (user != null){
            uid = user.getUid();
        }else {
            uid = 0;
        }
        boolean flag = favService.isFavorite(rid, uid);

        writeValue(flag,response);
    }

    /**
     * 将线路加入收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String rid = request.getParameter("rid");
        User user =(User) request.getSession().getAttribute("User");
        int uid = 0;
        if (user != null){
            uid = user.getUid();
        }else {
            return;
        }

        favService.add(rid,uid);
    }

}
