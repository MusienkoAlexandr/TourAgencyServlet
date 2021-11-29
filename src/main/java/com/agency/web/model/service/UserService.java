package com.agency.web.model.service;

import com.agency.web.model.dao.DaoFactory;
import com.agency.web.model.dao.UserDao;
import com.agency.web.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public  Optional<User> authenticate(String login, String password) {

        try (UserDao userDao = daoFactory.getUserDao()) {
            Optional<User> result = userDao.findByLogin(login);
            result = result.filter((u) -> u.getPassword().equals(password));
            return result;
        }

    }

}
