package com.agency.web.controller.command;

import com.agency.web.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.trace("Command execution");
        User user = User.createBuilder().setLogin(request.getParameter("login"))
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .getUser();
        return "redirect:/main";
    }

}
