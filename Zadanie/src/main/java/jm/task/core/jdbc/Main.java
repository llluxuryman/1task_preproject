package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Миахил", "Шкапа", (byte) 21);
        userService.saveUser("Елизавета", "Давыдова", (byte) 20);
        userService.saveUser("Буч", "-", (byte) 6);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            Util.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
