package cn.itcast.travel.service;

public interface FavoriteService {
    /**
     * 判断是否被收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid, int uid);

    /**
     * 加入收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
