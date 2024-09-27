package org.example.Configuration;

import org.example.Entities.Task;
import org.example.Entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example"})
public class ToDoConfig {
    @Bean
    public Task getTask() {
        return new Task(getUser());
    }

    @Bean
    public User getUser() {
        return new User();
    }
}
