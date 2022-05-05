package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

/*
UserDao의 관심사항
1. DB 연결을 위한 커넥션을 어떻게 가져올까
2. DB에 보낼 Statement를 만들고 실행하는 것
3. 사용한 리소스(Statement, Connection)를 닫아주는것
 */
public class UserDao {

    /*
    1-3. DAO의 확장
        - DB커넥션과 관련된 부분을 별도의 클래스로 분리하여, UserDao가 이용하게 한다.
        - 자유로운 확장이 가능하게 하려면 두가지 문제가 있다.
            문제점: 1. makeNewConnection()의 이름이 변경이될때 UserDao 내에서 커넥션을 가져오는 코드를 일일이 변경해줘야한다.
                   2. DB 커넥션을 제공하는 클래스가 어떤것인지 UserDao가 구체적으로 알고 있어야한다는것.
        - 커넥션을 가져오는 부분을 interface로 분리
        - ConnectionMaker 오브젝트를 클라이언트에서 생성해서 런타임 의존관계를 맺어준다.
     */

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

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
        Connection c = connectionMaker.makeConnection();

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

