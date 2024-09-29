package org.example.Dao;

import org.example.Entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    public User createUser(User user) {
        hibernateTemplate.save(user);
        return user;
    }

    @Transactional
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    public User readUserByEmail(String userEmail) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder(); // Criteria Builder

        CriteriaQuery<User> criteriaQuery = criteria.createQuery(User.class); // Criteria query
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root).where(criteria.equal(root.get("email"), userEmail)); // Build query with where clause

        return session.createQuery(criteriaQuery).uniqueResult(); // Execute query
    }

    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }

    public List<User> readAllUsers() {
        return hibernateTemplate.loadAll(User.class);
    }
}
