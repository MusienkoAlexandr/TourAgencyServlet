package com.agency.web.controller.command;

import com.agency.web.model.entity.User;
import com.agency.web.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Set;


public class LoginCommand extends Command{

    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);
    private String page;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.trace("Command execution");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        LOG.debug("Authenticating user with login: '" + login + "'");

        Optional<User> result = new UserService().authenticate(login, password);
        result.ifPresentOrElse(user -> loginSuccess(user, request), () -> loginFail(request));
        return page;

    }

    private void loginSuccess(User user, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("login", user.getLogin());
        session.setAttribute("role", user.getRole());

        Set<String> loggedUsers = (Set<String>) session.getServletContext().getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());
        LOG.debug("Current logged in users: " + loggedUsers);
        session.setAttribute("success", "Successful login as " + user.getLogin());
        page = (user.getStatus().equals(User.Status.BANNED)) ? "redirect:/banned" :  "redirect:/main";
        LOG.debug(user.getRole().toString());
    }

    private void loginFail(HttpServletRequest request) {

        request.getSession().setAttribute("error", "Wrong password or login");
        page = "redirect:/login";

    }
}
