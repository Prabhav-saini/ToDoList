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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserDao userDao;
    @Autowired
    private SessionFactory sessionFactory;

    final String userDoesNotExist = "User Does Not Exist With This Email_Id, Please Try Again With Correct Email_Id";

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userDao.createUser(user);
    }

    @Transactional
    public String updateUser(String userEmail, BufferedReader br) throws IOException {
        User existingUser = userDao.readUserByEmail(userEmail);
        if (Objects.isNull(existingUser)) {
            return userDoesNotExist;
        } else {
            User user = prepareUserToUpdate(br, existingUser);
            user.setId(existingUser.getId());
            user.setUpdatedAt(LocalDateTime.now());
            userDao.updateUser(user);
            return String.format("User Is SuccessFully Updated With Email : %s", user.getEmail());
        }
    }

    @Transactional
    public void readUser(String userEmail) {
        User user = userDao.readUserByEmail(userEmail);
        System.out.println(user.toString());
    }

    @Transactional
    public User getUserIfExistOrCreate(String userEmail, BufferedReader br) throws IOException {
        User user = userDao.readUserByEmail(userEmail);
        User newUser = null;
        if (Objects.nonNull(user)) {
            newUser = user;
        } else {
            System.out.println("This User Does Not Exist Do You Want To create New User With This Email_Id? Y/N");
            String input = br.readLine();
            char choice = input.charAt(0);
            switch (choice) {
                case 'Y':
                    System.out.println("creating And Assigning New User to This Task");
                    System.out.println("Please Update Other Details Of New USer Later");
                    newUser = createUser(new User(null, null, null, false, userEmail));
                    break;
                case 'N':
                    System.out.println("Please Enter Correct Email_Id");
            }
        }
        return newUser;
    }

    public User prepareUserToCreate(BufferedReader br) throws IOException {
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

    public User prepareUserToUpdate(BufferedReader br, User user) throws IOException {
        System.out.println("Enter First Name Of User : ");
        user.setFirstName(br.readLine());
        System.out.println("Enter Last Name Of User : ");
        user.setLastName(br.readLine());
        System.out.println("Enter Mobile Number Of User : ");
        user.setMobileNumber(br.readLine());
        System.out.println("Is This User A Manager? true/false : ");
        user.setManager(Boolean.parseBoolean(br.readLine()));
        System.out.println("Enter Email_Id of User : ");
        user.setEmail(br.readLine());
        return user;
    }

    @Transactional
    public String deleteUser(String userEmail) {
        User user = userDao.readUserByEmail(userEmail);
        if (Objects.nonNull(user)) {
            userDao.deleteUser(user);
            return String.format("User Deleted Successfully With Email_Id : %s", userEmail);
        } else {
            return userDoesNotExist;
        }
    }

    @Transactional
    public void readAllUsers() {
        List<User> users = userDao.readAllUsers();
        users.stream().map(User::toString)
                .forEach(System.out::println);
    }
}
