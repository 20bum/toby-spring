import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

/*
UserDaoTest의 문제
- 수동 확인 작업의 번거로움
        : 결과를 확인하는 일은 사람의 책임이므로 완전 자동 테스트 방법이라 말할수 없다.
- 실행 작업의 번거로움
        : DAO가 여러개가 되고 그에 대한 main()메소드도 그만큼 있다면, 전체기능을 테스트하기위해 main메소드를 무수히많이 실행해야함.
 */
public class UserDaoTest {

    /*
    - 자바에서 가장 손쉽게 실행 가능한 main() 메소드를 이요한다.
    - 테스트할 대상인 UserDao의 오브젝트를 가져와 메소드를 호출한다.
    - 테스트에 사용할 입력 값(User)을 직접 코드에서 만들어 넣어준다.
    - 테스트의 결과를 콘솔에 출력해준다.
    - 각 단계의 작업이 에러없이 끝나면 콘솔에 성공 메시지로 출력한다.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship1");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
