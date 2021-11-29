package com.agency.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class LogoutCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.trace("Command execution");
        HttpSession session = request.getSession();
        Set<String> loggedUsers = (Set<String>) session.getServletContext()
                .getAttribute("loggedUsers");
        loggedUsers.remove((String) session.getAttribute("login"));
        LOG.debug("Current logged in users: " + loggedUsers);
        session.invalidate();
        return "redirect:/main";

    }

}
