package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Roman", "Vasulov", (byte) 22);
        userDaoHibernate.saveUser("Albert", "Socov", (byte) 12);
        userDaoHibernate.saveUser("Andrey", "Alishev", (byte) 32);
        userDaoHibernate.saveUser("Caren", "Sergev", (byte) 45);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
    }
}
