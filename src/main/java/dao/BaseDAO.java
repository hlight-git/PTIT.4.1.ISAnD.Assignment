package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/isand?useSSL=false";
    private static final String jdbcUser = "root";
    private static final String jdbcPass = "Hung001201023360.";

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }
}