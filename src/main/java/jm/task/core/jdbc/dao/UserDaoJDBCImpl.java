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

    }

    public void dropUsersTable() {
        Connection con = null;
        Statement stmt;
        try {
            String sql = "DROP TABLE users;";
            stmt = con.createStatement();

            stmt.execute(sql);
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

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement ppStatement = null;
        String request = "INSERT INTO users(name, lastName, age) values(?, ?, ?);";
    }

    public void removeUserById(long id) {
        //String sql = "SELECT * FROM pp1_4.users;";
        //return users.stream().filter(user -> users.getId() == id).findFirst.orElse(null);

        PreparedStatement ppStatement = null;
        String query = "DELETE FROM users where id=?;";

        try {
            //подготовка запроса
            ppStatement = con.prepareStatement(query);
            ppStatement.setLong(1, user.getId());
            ppStatement.executeUpdate(); //execute запроса с нужным id
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
        Connection con = null;
        Statement stmt;
        try {
            //поднимаем стейтмент, удаляем все строки из таблицы
            String query = "DELETE FROM users;";
            stmt = con.createStatement();
            stmt.execute(query);

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
}
