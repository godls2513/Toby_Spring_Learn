package com.springbook.user.dao;

import org.springframework.context.annotation.*;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class NoUseDataSourceDaoFactory {

//    // 생성자를 이용한 DI
//    @Bean // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
//    public UserDao userDao() {
//        // 팩토리의 메소드는 UserDao 타입의 오브젝트를 어떻게 만들고, 어떻게 준비시킬지 결정한다.
//        return new UserDao(connectionMaker()); // 다른 Dao가 추가되어도 ConnectionMaker 인터페이스의 구체클래스의 오브젝트 생성이 중복되지 않는다.
//    }

    // 수정자(setter) 메소드 DI를 사용하는 팩토리 메소드
    @Bean // -------------------------------------------> <bean
    public NoUseDataSourceUserDao userDao() { // ----------------------> id="userDao"
        NoUseDataSourceUserDao userDao = new NoUseDataSourceUserDao(); // ------------> class="springbook.user.dao.NoUseDataSourceUserDao">
        userDao.setConnectionMaker(connectionMaker()); // <property name="connectionMaker" ref="connectionMaker"/></bean>
        return userDao;
    }

    @Bean // ---------------------------------------------> <bean
    public ConnectionMaker connectionMaker() { // --------> id="connectionMaker"
        // 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성코드
        return new DConnectionMaker(); // ----------------> class="springbook.user.dao.DconnectionMaker"/>
    }
}
