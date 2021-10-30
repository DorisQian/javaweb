package dao;

import domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    void save(User user);

    /**
     * 根据激活码查询激活对象
     * @param code
     * @return
     */
    User findByCode(String code);
    /**
     * 修改激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
