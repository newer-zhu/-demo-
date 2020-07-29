package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据rid和uid查找收藏实体类
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByRid_Uid(int rid, int uid);

    /**
     * 计数收藏的人数
     * @param rid
     * @return
     */
    int countFavorite(int rid);

    /**
     * 添加进收藏
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);
}
