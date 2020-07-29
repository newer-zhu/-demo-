package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 判断是否注册
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 判断是否激活
     * @param code
     * @return
     */
    public boolean active(String code);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);
}
