package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Criteria;
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
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sqlRequest = "CREATE TABLE IF NOT EXISTS Users (id  BIGINT NOT NULL AUTO_INCREMENT, " +
                "name varchar (100) NOT NULL, lastName varchar (100) NOT NULL, " +
                "age TINYINT NOT NULL, PRIMARY KEY (id))";

        Query query = session.createSQLQuery(sqlRequest);
        query.executeUpdate();
        transaction.commit();

    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sqlRequest = "DROP TABLE IF  EXISTS Users";

        session.createSQLQuery(sqlRequest).executeUpdate();
        transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        User user = new User(name,lastName,age);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();

        // System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List <User> users = session.createQuery("from User ").getResultList();
       for(User user: users)
        System.out.println(user);

        return users;
    }
    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User ").executeUpdate();
        transaction.commit();

    }
}
