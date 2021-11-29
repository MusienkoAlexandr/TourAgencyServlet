package com.agency.web.model.dao.impl;

import com.agency.web.model.dao.TourDao;
import com.agency.web.model.dto.PagedContent;
import com.agency.web.model.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TourDaoJdbc implements TourDao {

    private static final Logger LOG = LogManager.getLogger(TourDaoJdbc.class);
    private final Connection connection;
    private static final int PAGE_SIZE = 2;

    public TourDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Tour> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Tour> findAll() {
        return null;
    }

    @Override
    public void create(Tour entity) {

    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public PagedContent<Tour> findAllByCriteria(Map<String, String[]> criteria) {

        PagedContent<Tour> result = new PagedContent<>();
        List<Tour> tourList = new ArrayList<>();
        StatementCriteriaBuilder builder = new StatementCriteriaBuilder(connection, criteria);
        try (ResultSet rs = builder.withPrice().withTourType()
                .withPersons().withRoomType()
                .withTitle().withOrder()
                .createStatement()
                .executeQuery()) {
            TourMapper mapper = new TourMapper();
            rs.next();
            tourList.add(mapper.extractFromResultSet(rs));
            result.setTotalPageNumber(rs.getInt("total_count")/PAGE_SIZE + 1);
            while (rs.next()) {
                tourList.add(mapper.extractFromResultSet(rs));
            }
            result.setContentList(tourList);


        } catch (SQLException exception) {
            LOG.debug(exception.getMessage());
            throw new RuntimeException();
        }
        return result;

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static class StatementCriteriaBuilder {

        private final StringBuilder filterBuilder;
        private final StringBuilder orderBuilder;
        private final Map<String, String[]> criteria;
        private final List<Object> parameters;
        private final Connection connection;

        public StatementCriteriaBuilder(Connection connection, Map<String, String[]> criteria) {
            this.filterBuilder = new StringBuilder("SELECT *, COUNT(*) OVER() AS total_count FROM tours WHERE status = 'active' ");
            this.orderBuilder = new StringBuilder("ORDER BY burning DESC");
            this.connection = connection;
            this.criteria = criteria;
            this.parameters = new ArrayList<>();
        }

        public StatementCriteriaBuilder withPrice() {

            if (criteria.containsKey("min_price")) {
                filterBuilder.append("AND price >= ? ");
                parameters.add(new BigDecimal(criteria.get("min_price")[0]));
            }
            if (criteria.containsKey("max_price")) {
                filterBuilder.append("AND price <= ? ");
                parameters.add(new BigDecimal(criteria.get("max_price")[0]));
            }
            return this;

        }

        public StatementCriteriaBuilder withTourType() {

            if (criteria.containsKey("tour_type")) {
                String[] tourType = criteria.get("tour_type");
                filterBuilder.append("AND tour_type in (?");
                parameters.add(tourType[0]);
                for (int i = 1; i < tourType.length; i++) {
                    filterBuilder.append(", ?");
                    parameters.add(tourType[i]);
                }
                filterBuilder.append(") ");
            }
            return this;

        }

        public StatementCriteriaBuilder withPersons() {

            if (criteria.containsKey("min_persons")) {
                filterBuilder.append("AND persons >= ? ");
                parameters.add(Integer.valueOf(criteria.get("min_persons")[0]));
            }
            if (criteria.containsKey("max_persons")) {
                filterBuilder.append("AND persons <= ? ");
                parameters.add(Integer.valueOf(criteria.get("max_persons")[0]));

            }
            return this;

        }

        public StatementCriteriaBuilder withRoomType() {

            if (criteria.containsKey("room_type")) {
                String[] roomType = criteria.get("room_type");
                filterBuilder.append("AND room_type in (?");
                parameters.add(roomType[0]);
                for (int i = 1; i < roomType.length; i++) {
                    filterBuilder.append(", ?");
                    parameters.add(roomType[i]);
                }
                filterBuilder.append(") ");

            }
            return this;

        }

        public StatementCriteriaBuilder withTitle() {

            if (criteria.containsKey("title")) {
                filterBuilder.append("AND title LIKE ? ");
                parameters.add("%" + criteria.get("title")[0] + "%");
            }
            return this;
        }

        public StatementCriteriaBuilder withOrder() {

            if (criteria.containsKey("sort-by")) {
                orderBuilder.append(", ")
                        .append(criteria.get("sort-by")[0])
                        .append(" ")
                        .append(criteria.get("sort-order")[0].toUpperCase());

            }
            return this;

        }

        public PreparedStatement createStatement() {

            String sql = filterBuilder.append(orderBuilder).append(" LIMIT ?, ?").toString();
            int page = Integer.parseInt(criteria.getOrDefault("page", new String[]{"1"})[0]);
            parameters.add((page - 1) * PAGE_SIZE);
            parameters.add(PAGE_SIZE);
            PreparedStatement ps;
            LOG.debug(sql);
            LOG.debug(parameters);
            try {
                ps = connection.prepareStatement(sql);
                for (int i = 0; i < parameters.size(); i++ )  {
                    ps.setObject(i + 1, parameters.get(i));
                }
                LOG.debug(ps.toString());
                return ps;
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }

    }
}
