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

    public void boot() {
        // Define the message and border symbol
        String message = "TO DO LIST MANAGER";
        char borderSymbol = '*';

        // Calculate the length for the border
        int borderLength = message.length() + 4; // 4 for padding

        // Print the top border
        printBorder(borderLength, borderSymbol);
        // Print the message with padding
        System.out.println(borderSymbol + " " + message + " " + borderSymbol);
        // Print the bottom border
        printBorder(borderLength, borderSymbol);
    }

    // Method to print the border
    private static void printBorder(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }
}
