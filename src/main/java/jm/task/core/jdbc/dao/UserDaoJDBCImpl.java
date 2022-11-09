package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends  Util implements UserDao {
    Connection con = link();
    User user = new User();

    //конструктор
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection con = null;
        Statement stmt = null;
        try {
            String request = "CREATE TABLE `users` (`id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastname` VARCHAR(45) NOT NULL,\n" +
                            "  `age` TINYINT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`))\n" +
                    "DEFAULT CHARACTER SET = utf8;";

            con = Util.link();
            stmt = con.createStatement();

            stmt.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Statement stmt;
        try {
            con = Util.link();
            String request = "DROP TABLE users;";
            stmt = con.createStatement();

            stmt.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //preparedStatement падают на тестах, почему-то
        //реализация в формате python f-строк
        Connection con = null;
        Statement stmt;
        try {
            String request = String.format("INSERT INTO users(name, lastname, age) values('%s', '%s', %d);", name, lastName, age);

            con = Util.link();
            stmt = con.createStatement();

            stmt.execute(request);
            System.out.println("Query OK.");
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        //return users.stream().filter(user -> users.getId() == id).findFirst.orElse(null);

        Statement stmt;
        String query = String.format("DELETE FROM users WHERE id=%d;", id);
        try {
            con = Util.link();
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users;";
        Statement stmt = null;
        List<User> userList = new ArrayList<>();
        User temp; //промежуточный буфер для юзера

        try {
            con = Util.link();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query) ;

            //бежим по модели в цикле, собираем юзера
            while(rs.next()) {
                temp = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                temp.setId(rs.getLong(1));
                userList.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        con = Util.link();
        Statement stmt = null;
        try {
            //поднимаем стейтмент, удаляем все строки из таблицы
            String query = "DELETE FROM users;";
            stmt = con.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (con != null) { con.close(); }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
