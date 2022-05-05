package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

/*
UserDao의 관심사항
1. DB 연결을 위한 커넥션을 어떻게 가져올까
2. DB에 보낼 Statement를 만들고 실행하는 것
3. 사용한 리소스(Statement, Connection)를 닫아주는것
 */
public abstract class UserDao {

    /*
    1-2.관심사의 분리
        - 중복코드(Connection)의 메소드 추출: getConnection()
        - 서브클래스에서 UserDao를 상속받아 추상 메서드인 getConnection 메서드를 각자 구현할수도 있다
            문제점: 1. 슈퍼클래스의 변경이 있을때 서브클래스도 같이 수정해야 할 수 있다.
                   2. 다른 Dao가 생긴다면 Dao마다 getConnection 메서드가 중복될것이다.
     */

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

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
        Connection c = getConnection();

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

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

}

