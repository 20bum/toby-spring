package springbook.user.dao;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
//        userDao.setConnectionMaker(connectionMaker());
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:D:\\workspace\\workspace-h2\\toby-spring;AUTO_SERVER=true");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

}
