package com.springbook.test.junit;

import com.springbook.user.dao.*;
import com.springbook.user.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.datasource.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import javax.sql.*;
import java.sql.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTestUsedTestDB {
    @Autowired
    UserDao dao; // 스프링에서 직접 DI

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        this.user1 = new User("elling", "halland", "halland9");
        this.user2 = new User("lamin", "yamal", "lamin19");
        this.user3 = new User("leo", "messi", "messi10");
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
