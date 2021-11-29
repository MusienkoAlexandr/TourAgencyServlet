package com.agency.web.controller.filter;

import com.agency.web.controller.Servlet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class CommandMappingFilter extends HttpFilter {

    private static final Logger LOG = LogManager.getLogger(CommandMappingFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            request.setAttribute("command", request.getParameter("command"));
        }
        if (request.getMethod().equals("GET")) {
            request.setAttribute("command", request.getServletPath().replace("/", ""));
        }
        chain.doFilter(request, response);
    }
}
