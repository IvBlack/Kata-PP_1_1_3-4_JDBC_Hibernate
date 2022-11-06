package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;

public class Util {

    /*
    //попытка использовать вынесенный файл настроек утонула в Exceptions
    Properties data = new Properties();

    String url = data.getProperty("url");
    String username = data.getProperty("login");
    String password = data.getProperty("pass");

    try (InputStream in = Files.newInputStream(Paths.get("db.properties"))) {
            data.load(in);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    */

    private final static String login = "root";
    private final static String pass = "yourpasswd";
    private final static String url = "jdbc:mysql://localhost/pp1_4?serverTimezone=Europe/Moscow&useSSl=false";

    public Connection link() {
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
}
