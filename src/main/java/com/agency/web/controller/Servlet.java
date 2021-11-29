package com.agency.web.controller;

import com.agency.web.controller.command.CommandContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class Servlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(Servlet.class);

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path = request.getServletPath();
        String command = (String) request.getAttribute("command");
        LOG.trace("Start processing GET-request on path " + path + " with command " + command);
        String page = CommandContainer.get(command).execute(request, response);
        if (page.matches("redirect:/.+")) {
            page = page.replaceAll("redirect:/", "");
            LOG.debug("REDIRECT TO PAGE: " + page);
            response.sendRedirect(page);
        } else {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    public void destroy() {
    }
}