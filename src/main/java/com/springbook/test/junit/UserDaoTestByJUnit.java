package com.springbook.test.junit;

import com.springbook.user.dao.*;
import com.springbook.user.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.dao.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import javax.sql.*;
import java.sql.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링의 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = "/applicationContext.xml") // 테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트의 위치 지정
@DirtiesContext // 테스트 메소드에서 애플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 테스트 컨텍스트 프레임워크에 알려준다.
public class UserDaoTestByJUnit {
//    @Autowired
//    private ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 값이 주입
//
//    private UserDao dao; // setUp() 메소드에서 만드는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴스 변수로 선언
    private User user1;
    private User user2;
    private User user3;
    @Autowired
    UserDao dao;

    @Before
    public void setUp() {
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        this.dao = context.getBean("userDao", UserDao.class);
//
        this.user1 = new User("elling", "halland", "halland9");
        this.user2 = new User("lamin", "yamal", "lamin19");
        this.user3 = new User("leo", "messi", "messi10");
//        System.out.println(this.context);
//        System.out.println(this);

        DataSource dataSource = new SingleConnectionDataSource(
                // 테스트에서 UserDao가 사용할 DataSource 오브젝트를 직접 생성한다.
                "jdbc:mysql://localhost/testdb", "spring", "book", true
        );
        dao.setDataSource(dataSource);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userSelect1 = dao.get(user1.getId());
        assertThat(userSelect1.getName(), is(user1.getName()));
        assertThat(userSelect1.getPassword(), is(user1.getPassword()));

        User userSelect2 = dao.get(user2.getId());
        assertThat(userSelect2.getName(), is(user2.getName()));
        assertThat(userSelect2.getPassword(), is(user2.getPassword()));

    }

    @Test
    public void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class) // 테스트 중에 발생할 것으로 기대하는 예외 클래스를 지정할 수 있다.
    public void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }


}
