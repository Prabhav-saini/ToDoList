package org.example;

import org.example.Configuration.ToDoConfig;
import org.example.Dao.TaskDao;
import org.example.Entities.Task;
import org.example.Entities.User;
import org.example.Services.TaskService;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class ToDoApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ToDoConfig.class);
        TaskService taskService = context.getBean(TaskService.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User("String_firstName", "String lastName", "8267978774", true);
        userService.boot();
        User cretaedUser = userService.createUser(user);

        Task newTask = new Task("String title", "String description", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Task.Status.TODO, Task.Priority.LOW, cretaedUser);
        int r = taskService.createTask(newTask);
        System.out.println(r);
    }
}