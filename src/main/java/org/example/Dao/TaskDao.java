package org.example.Dao;

import org.example.Entities.Task;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TaskDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    public Task createTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        hibernateTemplate.save(task);
        return task;
    }

    @Transactional
    public void updateTask(Task task) {
        hibernateTemplate.update(task);
    }

    @Transactional
    public void deleteTask(Task task) {
        hibernateTemplate.delete(task);
    }

    public Task viewTaskById(int id) {
        return hibernateTemplate.get(Task.class, id);
    }

    public int markComplete(Task task) {
        hibernateTemplate.save(task);
        return task.getId();
    }

    public List<Task> getAllTasks() {
        return hibernateTemplate.loadAll(Task.class);
    }
}
