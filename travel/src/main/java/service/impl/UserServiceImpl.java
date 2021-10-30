package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;
import util.MailUtils;
import util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if (u != null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
        String content = "<a href='http://localhost:8080/travel/user/active?code='" + user.getCode() + "> 点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");

        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public Boolean active(String code) {
        //根据激活码查询用户是否存在
         User user = userDao.findByCode(code);
         if (user != null){
             userDao.updateStatus(user);
             return true;
         } else{
             return false;
         }
    }

    /**
     * 用户登录
     * @param user
     */
    @Override
    public User login(User user) {
        User u = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return u;
    }

}
