package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
        private static final String url =  "jdbc:mysql://localhost:3306/testeJDBC";
        private static final String user = "root";
        private static final String password = "12345";


        public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(url, user, password);
        }
}
