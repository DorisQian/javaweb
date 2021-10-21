package com.test;

import com.dao.UserDao;
import com.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("admin");
        loginUser.setPassword("1234");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);

    }
}
