package com.springbook.test;

import com.springbook.user.dao.*;
import com.springbook.user.domain.*;
import org.springframework.context.annotation.*;

import java.sql.*;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        // DAO 사용 코드
        dao.delete("whiteship");
        User user = new User();
        user.setId("whiteship");
        user.setName("해인");
        user.setPassword("married");
        dao.add(user);

        // 커넥션 횟수 조회
        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("커넥션 횟수 : " + ccm.getCount());

    }
}
