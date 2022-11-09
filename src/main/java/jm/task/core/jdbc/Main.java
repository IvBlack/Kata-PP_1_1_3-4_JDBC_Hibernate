package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Jordan", "Lameson", (byte)5);
        userService.saveUser("Iv", "Black", (byte)5);
        userService.saveUser("Lesli", "Deen", (byte)5);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
