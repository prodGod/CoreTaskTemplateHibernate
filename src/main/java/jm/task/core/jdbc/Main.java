package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Roman", "Vasulov", (byte) 22);
        userService.saveUser("Alexander", "Kushnir", (byte) 12);
        userService.saveUser("Viktoria", "Andreeva", (byte) 38);
        userService.saveUser("Alina", "Treuglova", (byte) 34);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
