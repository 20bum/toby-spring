package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {

    /*
    JDBC를 이용하는 작업의 일반적인 순서
    1. DB연결을 위한 Connection을 가져온다.
    2. SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
    3. 만들어진 Statement를 실행한다.
    4. 조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트(User)에 옮겨준다.
    5. JDBC API가 만들어내는 예외를 잡아서 직접 처리하거나, 메소드 밖으로 던진다.
     */

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:D:\\workspace\\workspace-h2\\toby-spring;AUTO_SERVER=true",
                "sa",
                ""
        );

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:D:\\workspace\\workspace-h2\\toby-spring;AUTO_SERVER=true",
                "sa",
                ""
        );

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }


}
