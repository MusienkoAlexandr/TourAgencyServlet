package com.agency.web.model.dao;

import com.agency.web.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{
    Optional<User> findByLogin(String login);
}
