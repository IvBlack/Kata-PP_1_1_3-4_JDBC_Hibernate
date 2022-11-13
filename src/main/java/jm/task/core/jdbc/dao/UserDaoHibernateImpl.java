package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    //завернем часть транзакций в метод
    private void executeSession(String sql) {
        Session ssId = Util.linkThroughHibernate().openSession();
        ssId.beginTransaction();
        ssId.createSQLQuery(sql).executeUpdate();
        ssId.getTransaction().commit();
    }

    @Override
    public void createUsersTable() {
        Session ssId = null;
        try {
            String sql = "CREATE TABLE `pp1_4`.`users` (`id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL," + "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT NOT NULL," + "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB," + "DEFAULT CHARACTER SET = utf8;";
            //используем кастомную функцию
            executeSession(sql);
        } catch (Exception ex) {
        } finally {
            try {
                ssId.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session ssId = null;
        try {
            String sql = "DROP TABLE users;";
            executeSession(sql);
        } catch (Exception ex) {
        } finally {
            try {
                ssId.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session ssId = null;
        try {
            ssId = Util.linkThroughHibernate().openSession();
            ssId.beginTransaction();
            //странно, что нельзя передать (User user)
            ssId.save(new User(name, lastName, age));
            ssId.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            try {
                ssId.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Session ssId = null;
        try {
            String sql = String.format("DELETE FROM users WHERE id=%d;", id);
            executeSession(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                ssId.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session ssId = null;
        List<User> userList = new ArrayList<>();
        try {
            //String sql = "SELECT * FROM users;";
            Query query = ssId.createSQLQuery("SELECT * FROM users;");
            ((NativeQuery<?>) query).addEntity(User.class);
            userList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                ssId.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session ssId = null;
        try {
            String sql = "DELETE FROM users;";
            ssId = Util.linkThroughHibernate().openSession();
            ssId.beginTransaction();
            ssId.createSQLQuery(sql);
            ssId.getTransaction().commit();
        } catch (Exception e) {
            if (ssId != null) {
                try {
                    //если транзакция падает по причинам
                    ssId.getTransaction().rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                ssId.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
