package com.springbook.user.dao;

import org.springframework.context.annotation.*;

@Configuration
public class CountingDaoFactory {

//    @Bean
//    public UserDao userDao() {
//        return new UserDao(connectionMaker());
//    }

    @Bean
    public NoUseDataSourceUserDao userDao() {
        NoUseDataSourceUserDao userDao = new NoUseDataSourceUserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    // 모든 DAO는 여전히 connectionMaker()에서 만들어지는 오브젝트를 DI 받는다.
    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
