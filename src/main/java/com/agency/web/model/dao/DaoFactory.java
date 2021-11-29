package com.agency.web.model.dao;

public abstract class DaoFactory {

    private static DaoFactory instance;

    public abstract UserDao getUserDao();

    public abstract TourDao getTourDao();

    public abstract OrderDao getOrderDao();


    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    instance = new MySqlDaoFactory();
                }
            }
        }
        return instance;
    }


}
