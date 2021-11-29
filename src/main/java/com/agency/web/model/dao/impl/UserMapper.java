package com.agency.web.model.dao.impl;

import com.agency.web.model.dao.ObjectMapper;
import com.agency.web.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User.UserBuilder userBuilder = User.createBuilder();
        userBuilder.setId(rs.getLong("user_id"))
                .setLogin(rs.getString("login"))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"))
                .setRole(User.Role.valueOf(rs.getString("role").toUpperCase()))
                .setStatus(User.Status.valueOf(rs.getString("status").toUpperCase()));
        return userBuilder.getUser();
    }
}
