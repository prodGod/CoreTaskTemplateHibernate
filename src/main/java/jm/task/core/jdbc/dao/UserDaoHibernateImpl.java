package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlRequest = "CREATE TABLE IF NOT EXISTS Users (id  BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name varchar (100) NOT NULL, lastName varchar (100) NOT NULL, " +
                    "age TINYINT NOT NULL, PRIMARY KEY (id))";
            session.createSQLQuery(sqlRequest).executeUpdate();
            transaction.commit();
        }
    }


    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlRequest = "DROP TABLE IF  EXISTS Users";
            session.createSQLQuery(sqlRequest).executeUpdate();
            transaction.commit();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User ").getResultList();
            return users;
        }
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User ").executeUpdate();
            transaction.commit();
        }
    }
}
