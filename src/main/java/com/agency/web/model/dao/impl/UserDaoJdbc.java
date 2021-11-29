package com.agency.web.model.dao.impl;


import com.agency.web.model.dao.UserDao;
import com.agency.web.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoJdbc.class);
    private final Connection connection;

    public UserDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                UserMapper mapper = new UserMapper();
                if (rs.next()) {
                    LOG.debug("Found user by login '" + login + "'");
                    user = mapper.extractFromResultSet(rs);
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
