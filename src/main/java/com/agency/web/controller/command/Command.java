package com.agency.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Command {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
