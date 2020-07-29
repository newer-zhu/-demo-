package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    //根据名字找用户
    public User findUserByName(String username);

    //保存用户
    public void save(User user);

    //更新激活状态
    public void updateStatus(User user);

    //根据code查找用户
    public User findUserByCode(String code);

    //根据名字密码找用户
    public User findUserByUP(String username,String password);
}
