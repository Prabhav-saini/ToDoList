package org.example.Services;

import org.example.Dao.UserDao;
import org.example.Entities.Task;
import org.example.Entities.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional // This can manage the transaction for createTask
    public User createUser(User user) {
        return userDao.createUser(user);
    }

}
