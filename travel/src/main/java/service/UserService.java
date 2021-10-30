package service;

import domain.User;

public interface UserService {
    Boolean register(User user);

    Boolean active(String code);

    User login(User user);
}
