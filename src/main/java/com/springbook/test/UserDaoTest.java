package com.springbook.test;

import com.springbook.user.dao.*;
import com.springbook.user.domain.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

import java.io.*;
import java.sql.*;

public class UserDaoTest {
    // 테스트
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // Annotation을 이용한 DI
        //ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        // XML을 이용한 DI
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
        //NoUseDataSourceUserDao dao = context.getBean("userDao", NoUseDataSourceUserDao.class);

        dao.delete("whiteship");

        User user = new User();

        user.setId("whiteship");
        user.setName("해인");
        user.setPassword("married");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User userSelect = dao.get(user.getId());

        if (!user.getName().equals(userSelect.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(userSelect.getPassword())) {
            System.out.println("테스트실패 (password)");
        } else {
            System.out.println("조회 테스트 성공");
        }
    }
}
