package springbook.user.dao;

/*
1-4. 제어의 역전(IoC)
UserDaoTest 는 UserDao의 기능이 잘동작하는지 테스트하려고 만든것.

팩토리: 관심사의 분리를 통해 구현클래스의 오브젝트를 만드는것과,
만들어진 두 개의 오브젝트가 연결돼서 사용할 수 있도록 관계를 맺어주는 것
 */
public class DaoFactory {
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

}
