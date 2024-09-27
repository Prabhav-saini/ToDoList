package org.example.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.Dao.TaskDao;
import org.example.Dao.UserDao;
import org.example.Entities.Task;
import org.example.Entities.User;
import org.example.Services.TaskService;
import org.example.Services.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.example.Entities", "org.example.Dao"})
public class ToDoConfig {
    @Bean
    public Task getTask() {
        return new Task(getUser());
    }

    @Bean
    public User getUser() {
        return new User();
    }

    @Bean
    public TaskDao getTaskDao() {
        return new TaskDao();
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao();
    }

    @Bean
    public TaskService getTaskService() {
        return new TaskService(getTaskDao());
    }

    @Bean
    UserService getUserService() {
        return new UserService(getUserDao());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("org.example.Entities");
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());
        return hibernateTemplate;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/todolist");
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
