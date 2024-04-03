package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util;
    private Connection connection;

    private PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() {
        util = new Util();

        String sql = "CREATE TABLE `mydb`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))";
        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        util = new Util();

        String sql = "DROP TABLE `mydb`.`users`;";
        try {
            connection = util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        util = new Util();
        connection = util.getConnection();
        String sql = "INSERT INTO `users`" +
                "(name,lastName,age)" +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        util = new Util();
        connection = util.getConnection();
        String sql = "DELETE FROM `users` WHERE ID = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Пользователь с ID " + id + " удален и базы данных");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        util = new Util();
        connection = util.getConnection();
        List<User> listOfUsers = new ArrayList<>();

        String sql = "SELECT * FROM users;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getLong("id"));
                user.setName(resultset.getString("name"));
                user.setLastName(resultset.getString("lastName"));
                user.setAge(resultset.getByte("age"));
                listOfUsers.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        util = new Util();
        connection = util.getConnection();
        String sql = "TRUNCATE TABLE `users`;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }
}
