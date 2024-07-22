package com.springbook.user.dao;

import java.sql.*;

public class CountingConnectionMaker implements ConnectionMaker {
    int count = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.count++;
        return realConnectionMaker.makeConnection();
    }

    public int getCount() {
        return this.count;
    }
}
