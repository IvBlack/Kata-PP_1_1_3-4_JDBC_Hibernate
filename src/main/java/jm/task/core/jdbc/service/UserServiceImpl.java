package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao mineDao = new UserDaoJDBCImpl();

    //вызываем методы слоя DAO через объект
    public void createUsersTable() {
        mineDao.createUsersTable();
    }

    public void dropUsersTable() {
        mineDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        mineDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        mineDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return mineDao.getAllUsers();
    }

    public void cleanUsersTable() {
        mineDao.cleanUsersTable();
    }
}
