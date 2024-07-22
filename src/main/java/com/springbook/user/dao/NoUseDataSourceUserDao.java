package com.springbook.user.dao;

import com.springbook.user.domain.*;

import java.sql.*;

public class NoUseDataSourceUserDao {

    // 인터페이스를 통해 오브젝트에 접근하므로 구체적인 클래스 정보를 알 필요가 없다.
    // 초기에 설정하면 사용 중에는 바뀌지 않는 읽기전용 인스턴스 변수
    private ConnectionMaker connectionMaker;

    // 매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수. 심각한 문제가 발생한다.(사용하지말자)
//	private Connection c;
//	private User user;

//    // 의존관계 주입을 이용하는 UserDao 생성자
//    public UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }
//
//    // 의존관계 검색을 이용하는 UserDao 생성자
//    public UserDao() {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
//    }

    // 수정자 메소드 DI 방식
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    /**
     * 사용자 정보 생성
     *
     * @param user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection(); // 인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀌어도 메소드 이름이 변경될 걱정은 없다.
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    /**
     * 아이디를 가지고 사용자 정보를 읽어오기
     *
     * @param id
     * @return user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users where id = ?");
        ps.setString(1, id);

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}













