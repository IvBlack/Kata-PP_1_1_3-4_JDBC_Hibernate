package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        util.link();

        /*
        String query = "SELECT * from users";
        try {
            User user = new User();
            Statement st = util.getConnection().createStatement();
            //отдаем в statement запрос к БД
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */


    }
}
