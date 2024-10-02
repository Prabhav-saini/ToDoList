package org.example.Services;

import org.example.Dao.UserDao;
import org.example.Entities.User;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    public enum UserError {
        NULL_USER("!!! User Can Not Be Null !!!"),
        INVALID_EMAIL("!!! Email Is Not Valid !!!"),
        NULL_FIRST_NAME("!!! First Name Can Not Be Empty !!!"),
        NULL_LAST_NAME("!!! Last Name Can Not Be Empty !!!"),
        VALID_USER("Valid"),
        INVALID_NUMBER("!!! Mobile Number Is Invalid !!!");

        private final String msg;

        UserError(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
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
            String userValidationResponse = validateUser(user);
            if (userValidationResponse.equalsIgnoreCase("Valid")) {
                user.setId(existingUser.getId());
                user.setUpdatedAt(LocalDateTime.now());
                userDao.updateUser(user);
                return String.format("User Is SuccessFully Updated With Email : %s", user.getEmail());
            }
            return userValidationResponse;
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
            if (userEmail.matches(EMAIL_REGEX)) {
                System.out.println("This User Does Not Exist Do You Want To create New User With This Email_Id? Y/N");
                String input = br.readLine();
                char choice = input.charAt(0);
                switch (choice) {
                    case 'Y':
                        System.out.println("creating And Assigning New User to This Task");
                        System.out.println("Please Update Other Details Of New User Later");
                        newUser = createUser(new User(null, null, null, false, userEmail));
                        break;
                    case 'N':
                        System.out.println("Please Enter Correct Email_Id");
                }
            }
        }
        return newUser;
    }

    public User prepareUserToCreate(BufferedReader br) throws IOException {
        User user = new User();
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

    public User prepareUserToUpdate(BufferedReader br, User user) throws IOException {
        System.out.println("Enter First Name Of User : ");
        user.setFirstName(br.readLine());
        System.out.println("Enter Last Name Of User : ");
        user.setLastName(br.readLine());
        System.out.println("Enter Mobile Number Of User(OPTIONAL) : ");
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

    public String validateUser(User user) {
        if (Objects.isNull(user)) {
            return UserError.NULL_USER.getMsg();
        }

        if (StringUtils.isEmpty(user.getFirstName())) {
            return UserError.NULL_FIRST_NAME.getMsg();
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            return UserError.NULL_LAST_NAME.getMsg();
        }

        if (StringUtils.isEmpty(user.getEmail()) || !user.getEmail().matches(EMAIL_REGEX)) {
            return UserError.INVALID_EMAIL.getMsg();
        }

        if (user.getMobileNumber() == null || !user.getMobileNumber().matches("\\d{10}")) {
            return UserError.INVALID_NUMBER.getMsg();
        }

        return UserError.VALID_USER.getMsg();
    }
}
