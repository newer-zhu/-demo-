package cn.itcast.travel.service.Impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
private UserDao userdao = new UserDaoImpl();

    @Override//注册用户
    public boolean register(User user) {
        User existUser = userdao.findUserByName(user.getUsername());
        if (existUser != null){
            //用户名存在
            return false;
        }
        //保存用户信息
        user.setCode(UuidUtil.getUuid());//设置唯一激活码
        user.setStatus("N");
        userdao.save(user);
        //发送激活邮件
        String content = "<a href='http://localhost/travel/User/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userdao.findUserByCode(code);
        if (user != null){
            userdao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User rUser = userdao.findUserByUP(user.getUsername(),user.getPassword());
        return rUser;
    }
}
