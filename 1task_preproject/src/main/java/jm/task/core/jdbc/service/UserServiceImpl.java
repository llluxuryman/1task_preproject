package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao UserJDBCimpl = new UserDaoJDBCImpl();
    public void createUsersTable() {
        UserJDBCimpl.createUsersTable();
    }

    public void dropUsersTable() {
        UserJDBCimpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserJDBCimpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserJDBCimpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserJDBCimpl.getAllUsers();
    }

    public void cleanUsersTable() {
        UserJDBCimpl.cleanUsersTable();
    }
}
