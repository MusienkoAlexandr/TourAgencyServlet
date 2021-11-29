package com.agency.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterPageCommand extends Command {

    private static final Logger LOG = LogManager.getLogger(RegisterPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Command execution");
        return "/register.jsp";
    }
}
