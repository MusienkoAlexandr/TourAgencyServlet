package com.agency.web.model.dao.impl;

import com.agency.web.model.dao.ObjectMapper;
import com.agency.web.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TourMapper implements ObjectMapper<Tour> {

    @Override
    public Tour extractFromResultSet(ResultSet rs) throws SQLException {
        Tour.TourBuilder tourBuilder = Tour.createBuilder();
        tourBuilder.setId(rs.getLong("tour_id"))
                .setTitle(rs.getString("title"))
                .setDescription(rs.getString("description"))
                .setTourType(Tour.TourType.valueOf(rs.getString("tour_type").toUpperCase()))
                .setPrice(rs.getBigDecimal("price"))
                .setRoomType(Tour.RoomType.valueOf(rs.getString("room_type").toUpperCase()))
                .setStartDate(LocalDate.parse(rs.getString("start_date")))
                .setPersons(rs.getInt("persons"))
                .setNights(rs.getInt("nights"))
                .setStatus(Tour.Status.valueOf(rs.getString("status").toUpperCase()))
                .setBurning(rs.getBoolean("burning"));
        return tourBuilder.getTour();
    }

}
