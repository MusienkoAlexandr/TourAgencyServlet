package com.agency.web.controller.command;

import com.agency.web.model.dto.PagedContent;
import com.agency.web.model.entity.Tour;
import com.agency.web.model.service.TourService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ToursPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PagedContent<Tour> result = new TourService().getTourCatalog(request.getParameterMap());
        request.setAttribute("tours", result.getContentList());
        request.setAttribute("total_pages", result.getTotalPageNumber());
        return "/tours.jsp";
    }
}
