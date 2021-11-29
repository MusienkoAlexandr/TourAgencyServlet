package com.agency.web.controller.listener;

import com.agency.web.controller.command.CommandContainer;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class ContextListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        CommandContainer.init();

        ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
        Set<String> loggedUsers = Collections.newSetFromMap(map);
        sce.getServletContext().setAttribute("loggedUsers", loggedUsers);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
