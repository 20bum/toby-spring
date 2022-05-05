package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:D:\\workspace\\workspace-h2\\toby-spring;AUTO_SERVER=true",
                "sa",
                ""
        );
        return c;
    }
}
