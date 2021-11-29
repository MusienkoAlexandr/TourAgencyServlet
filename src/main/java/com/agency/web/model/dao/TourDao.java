package com.agency.web.model.dao;

import com.agency.web.model.dto.PagedContent;
import com.agency.web.model.entity.Tour;

import java.util.List;
import java.util.Map;

public interface TourDao extends Dao<Tour> {

    PagedContent<Tour> findAllByCriteria(Map<String, String[]> criteria);

}
