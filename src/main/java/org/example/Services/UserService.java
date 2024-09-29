package org.example.Services;

import org.example.Dao.UserDao;
import org.example.Entities.User;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;

@Service
public class UserService {
    private final UserDao userDao;
    @Autowired
    private SessionFactory sessionFactory;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional // This can manage the transaction for createTask
    public User createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        return userDao.createUser(user);
    }

    public User prepareUser(BufferedReader br) throws IOException {
        System.out.println("Enter First Name Of User : ");
        String fName = br.readLine();
        System.out.println("Enter Last Name Of User : ");
        String lName = br.readLine();
        System.out.println("Enter Mobile Number Of User : ");
        String mob = br.readLine();
        System.out.println("Is This User A Manager? true/false : ");
        String isManager = br.readLine();
        System.out.println("Enter Email_Id of User : ");
        String email = br.readLine();
        return new User(fName, lName, mob, Boolean.parseBoolean(isManager), email);
    }

}
