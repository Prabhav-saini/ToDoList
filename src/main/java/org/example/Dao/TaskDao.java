package org.example.Dao;

import org.example.Entities.Task;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public int createTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        hibernateTemplate.save(task);
        return task.getId();
    }

    public int updateTask(Task task) {
        hibernateTemplate.save(task);
        return task.getId();
    }

    public int deleteTask(Task task) {
        hibernateTemplate.save(task);
        return task.getId();
    }

    public int viewTask(Task task) {
        hibernateTemplate.save(task);
        return task.getId();
    }

    public int markComplete(Task task) {
        hibernateTemplate.save(task);
        return task.getId();
    }

}
