package com.agency.web.model.service;


import com.agency.web.model.dao.DaoFactory;
import com.agency.web.model.dao.TourDao;
import com.agency.web.model.dao.UserDao;
import com.agency.web.model.dto.PagedContent;
import com.agency.web.model.entity.Tour;
import com.agency.web.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TourService {

    private static final Logger LOG = LogManager.getLogger(TourService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public PagedContent<Tour> getTourCatalog(Map<String, String[]> criteria) {
        try (TourDao tourDao = daoFactory.getTourDao()) {
            PagedContent<Tour> result = tourDao.findAllByCriteria(criteria);
            LOG.debug(result.toString());
            return result;
        }
    }

}
