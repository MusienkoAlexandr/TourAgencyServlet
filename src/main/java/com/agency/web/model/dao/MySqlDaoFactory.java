package com.agency.web.model.dao;

import com.agency.web.model.dao.impl.TourDaoJdbc;
import com.agency.web.model.dao.impl.UserDaoJdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class MySqlDaoFactory extends DaoFactory {

    private DataSource dataSource;
    public MySqlDaoFactory() {
        try {
            dataSource = (DataSource)(new InitialContext()).lookup("java:comp/env/jdbc/MyConnectionPool");
        } catch (NamingException e) {

            e.printStackTrace();
        }
    }
    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbc(getConnection());
    }

    @Override
    public TourDao getTourDao() {
        return new TourDaoJdbc(getConnection());
    }

    @Override
    public OrderDao getOrderDao() {
        return null;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
