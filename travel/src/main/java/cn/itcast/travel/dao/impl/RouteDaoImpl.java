package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        List params = new ArrayList();
        if (cid != 0){
            sql += " and cid = ? ";
            params.add(cid);
        }
        if (rname != null && rname.length()>0 && !"undefined".equalsIgnoreCase(rname)  && !"null".equalsIgnoreCase(rname)){
            sql += " and rname like ? ";
            params.add("%"+rname+"%");
        }
        int sum = template.queryForObject(sql, Integer.class, params.toArray());
        return sum;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1=1 ";
        //params储存参数
        ArrayList<Object> params = new ArrayList<>();
        if (cid != 0){
            sql += " and cid = ? ";
            params.add(cid);
        }
        if (rname != null && rname.length()>0 && !"undefined".equalsIgnoreCase(rname) && !"null".equalsIgnoreCase(rname)){
            sql += " and rname like ?";
            params.add("%"+rname+"%");
        }
        sql += " limit ? , ? ";
        params.add(start);
        params.add(pageSize);
        //最后要把param转换为数组
        List<Route> route = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        return route;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
