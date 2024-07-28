package com.springbook.user.dao;

import com.springbook.user.domain.*;
import org.springframework.dao.*;

import javax.sql.*;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 사용자 정보 생성
     *
     * @param user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();
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
    public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);


        ResultSet rs = ps.executeQuery();

        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users where id = ?");
        ps.setString(1, id);

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM USERS");
        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM USERS");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }
}













