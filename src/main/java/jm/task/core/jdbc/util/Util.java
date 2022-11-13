package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static SessionFactory ssFactory;

    private final static String login = "root";
    private final static String pass = "yourpasswd";
    private final static String url = "jdbc:mysql://localhost/pp1_4?serverTimezone=Europe/Moscow&useSSl=false";

    public static Connection link() {
        //инициализируем подключение
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, login, pass);
            System.out.println("Connection done.");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed.");
        }
        return con;
    }

    /*
    public void freeConnection(Connection con) {
        if (con == null) {
            return;
        }
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    */

    public static SessionFactory linkThroughHibernate() {
        try {
            Configuration cfg = new Configuration();
            //Properties settings = new Properties();

            //засетим настройки в окружение
            cfg.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            cfg.setProperty(Environment.URL,
                    "jdbc:mysql://localhost/pp1_4?serverTimezone=Europe/Moscow&useSSl=false");
            cfg.setProperty(Environment.USER, "root");
            cfg.setProperty(Environment.PASS, "yourpasswd");
            cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLInnoDBDialect");

            cfg.addAnnotatedClass(User.class);

            ServiceRegistry svReg = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();

            ssFactory = cfg.configure().buildSessionFactory(svReg);
        } catch (Throwable ex) {
            System.err.println("Session failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return ssFactory;
    }
}
