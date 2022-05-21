import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

/*
2-2. UserDaoTest 개선

 */
public class UserDaoTest_bak {

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

        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공");
        }
    }
}
