package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao dao = new FavoriteDaoImpl();

    /**
     * 判断是否被收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        boolean flag = false;
        Favorite favorite = dao.findByRid_Uid(Integer.parseInt(rid), uid);
        if (favorite == null){
            flag = false;
        }else {
            flag = true;
        }

        return flag;
    }

    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid),uid);
    }
}
