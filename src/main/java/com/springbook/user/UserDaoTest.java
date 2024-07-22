package com.springbook.user;

import com.springbook.user.dao.*;
import com.springbook.user.domain.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

import java.io.*;
import java.sql.*;

public class UserDaoTest {
    // 테스트
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // Annotation을 이용한 DI
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        // XML을 이용한 DI
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
//        NoUseDataSourceUserDao dao = context.getBean("userDao", NoUseDataSourceUserDao.class);

        dao.delete("whiteship");

        User user = new User();

        user.setId("whiteship");
        user.setName("해인");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User userSelect = dao.get(user.getId());
        System.out.println(userSelect.getName());
        System.out.println(userSelect.getPassword());

        System.out.println(userSelect.getId() + " 조회 성공");
    }
}
