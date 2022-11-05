package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;

public class Util {

    //создадим  объект java.sql.Connection для коннекта с БД
    public static Connection link() {
        Connection con = null;

       //данные подключения вынесены в db.properties
        Properties data = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("db.properties"))) {
            data.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String url = data.getProperty("url");
        String username = data.getProperty("login");
        String password = data.getProperty("pass");

        //коннект к БД
        try {
            con = DriverManager.getConnection("url", "login", "pass");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
