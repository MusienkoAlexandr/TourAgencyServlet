package com.agency.web.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger LOG = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands = new HashMap<>();

    public static void init() {

        LOG.debug("Initializing");
        commands.put("login", new LoginPageCommand());
        commands.put("login_command", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterPageCommand());
        commands.put("register_command", new RegisterCommand());
        commands.put("tours", new ToursPageCommand());
    }

    public static Command get(String commandName) {
        return commands.getOrDefault(commandName, new MainPageCommand());
    }
}
