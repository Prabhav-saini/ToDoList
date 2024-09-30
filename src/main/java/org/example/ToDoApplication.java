package org.example;

import org.example.Configuration.ToDoConfig;
import org.example.Entities.User;
import org.example.Services.TaskService;
import org.example.Services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ToDoApplication {

    final static String message = "TO DO LIST MANAGER";
    final static String subtitle = "An Event Driven Application To Manage Tasks For An Organization";

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ToDoConfig.class);
        applicationBranding(); // Printing Application Branding
        openMenu(context); // Main Menu Method
    }

    private static void openMenu(ApplicationContext context) {
        boolean exit = false;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("*************************** MAIN MENU ***************************");

            System.out.println("Press 1 To Manage Tasks");
            System.out.println("Press 2 To Manage Users");
            System.out.println("Press 3 To Exit Program : ");

            try {
                int choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        taskManager(context, br);
                        break;
                    case 2:
                        userManager(context, br);
                        break;
                    case 3:
                        System.out.println("*************************** THANK YOU FOR USING TO DO LIST MANAGER ***************************");
                        System.out.println("           *************************** SEE YOU SOON !! ***************************");
                        exit = true;
                        break;
                    default:
                        System.out.println("!!! INVALID INPUT !!!");
                        System.out.println("Please Choose From Given Values On Screen!");

                }
            } catch (Exception e) {
                System.out.println("System Failure");
                e.printStackTrace();
            }
        } while (!exit);
    }

    private static void taskManager(ApplicationContext context, BufferedReader br) {
        boolean exit = false;

        do {
            System.out.println("*************************** TASK MANAGER PORTAL ***************************");
            System.out.println("Press 1 To Create A Task");
            System.out.println("Press 2 To Update A Task");
            System.out.println("Press 3 To Delete A Task");
            System.out.println("Press 4 To View A Task");
            System.out.println("Press 5 To View All Tasks");
            System.out.println("Press 6 To Go Back To Main Menu : ");

            try {
                int choice = Integer.parseInt(br.readLine());
                TaskService taskService = context.getBean(TaskService.class);

                switch (choice) {
                    case 1:
                        // create task
                    case 2:
                        // update task
                    case 3:
                        // delete task
                    case 4:
                        // view task
                    case 5:
                        // view all tasks
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("!!! INVALID INPUT !!!");
                        System.out.println("Please Choose From Given Values On Screen");

                }
            } catch (Exception e) {
                System.out.println("System Failure At Task Manager Level");
                e.printStackTrace();
            }
        } while (!exit);

    }

    private static void userManager(ApplicationContext context, BufferedReader br) {
        boolean exit = false;

        do {
            System.out.println("*************************** USER MANAGER PORTAL ***************************");
            System.out.println("Press 1 To Create A user");
            System.out.println("Press 2 To Update A user");
            System.out.println("Press 3 To Delete A user");
            System.out.println("Press 4 To View A user");
            System.out.println("Press 5 To View All user");
            System.out.println("Press 6 To Go Back To Main Menu : ");

            try {
                int choice = Integer.parseInt(br.readLine());
                UserService userService = context.getBean(UserService.class);
                switch (choice) {
                    case 1:
                        User user = userService.prepareUserToCreate(br);
                        User createdUser = userService.createUser(user);
                        System.out.println(String.format("User created with Id: %s And Email: %s", createdUser.getId(), createdUser.getEmail()));
                        break;

                    case 2:
                        System.out.println("Enter Email_Id Of User You Want To Update : ");
                        String UpdateResponse = userService.updateUser(br.readLine(), br);
                        System.out.println(UpdateResponse);
                        break;
                    case 3:
                        System.out.println("Enter Email_Id Of User You Want To Delete : ");
                        String deletionResponse = userService.deleteUser(br.readLine());
                        System.out.println(deletionResponse);
                        break;
                    case 4:
                        System.out.println("Enter Email_id Of User You Want To View : ");
                        userService.readUser(br.readLine());
                        break;
                    case 5:
                        userService.readAllUsers();
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("!!! INVALID INPUT !!!");
                        System.out.println("Please Choose From Given Values On Screen");

                }
            } catch (Exception e) {
                System.out.println("System Failure At User Manager Level");
                e.printStackTrace();
            }
        } while (!exit);

    }

    // Method for application branding
    private static void applicationBranding() {
        char borderSymbol = '*';
        int borderLength = 80;


        printBorder(borderLength, borderSymbol);
        System.out.printf("%" + (borderLength / 2 + message.length() / 2) + "s%n", "* " + message + " *");
        System.out.printf("%" + (borderLength / 2 + subtitle.length() / 2) + "s%n", subtitle);
        printBorder(borderLength, borderSymbol);
    }

    // Method to print the border
    private static void printBorder(int length, char symbol) {
        System.out.println(String.format("%" + length + "s", "").replace(" ", String.valueOf(symbol)));
    }


}