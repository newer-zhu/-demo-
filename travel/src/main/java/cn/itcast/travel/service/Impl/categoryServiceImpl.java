package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.categoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

public class categoryServiceImpl implements CategoryService {
    CategoryDao dao = new categoryDaoImpl();
    @Override
    public List<Category> findAll() {
        List<Category> cs ;
        Jedis jedis = null;
        Set<Tuple> categorys = null;
        //从redis中获取数据
        //获取jedis客户端
        try{
            jedis = JedisUtil.getJedis();
            //sortedset排序
            categorys = jedis.zrangeWithScores("category", 0, -1);
            //将set存入list
            System.out.println("从redis中查询");
            cs = new ArrayList<>();
            for (Tuple item : categorys){
                Category category = new Category();
                category.setCid((int) item.getScore());
                category.setCname(item.getElement());
                cs.add(category);
            }
            System.out.println(cs);
        }catch (Exception e){
            //从数据库中查询
            System.out.println("从数据库中查询");
            cs = dao.findAll();
            System.out.println(cs);
            //存到redis中
            for (int i=0; i<cs.size(); i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
            System.out.println(cs);
        }

        return  cs;
    }
}
