package com.agency.web.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tour {

    public enum TourType {
        VACATION, EXCURSION, SHOPPING;
    }

    public enum RoomType {
        SINGLE, DOUBLE, STUDIO, PRESIDENT, APARTMENT, VILLA;
    }

    public enum Status {
        ACTIVE, REMOVED;
    }

    private long id;
    private String title;
    private String description;
    private TourType tourType;
    private BigDecimal price;
    private RoomType roomType;
    private LocalDate startDate;
    private int persons;
    private int nights;
    private boolean isBurning;
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public boolean isBurning() {
        return isBurning;
    }

    public void setBurning(boolean burning) {
        isBurning = burning;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tourType=" + tourType +
                ", price=" + price +
                ", roomType=" + roomType +
                ", startDate=" + startDate +
                ", persons=" + persons +
                ", nights=" + nights +
                ", isBurning=" + isBurning +
                ", status=" + status +
                '}';
    }

    public static TourBuilder createBuilder() {
        return new TourBuilder();
    }

    public static class TourBuilder {

        private final Tour tour;

        private TourBuilder() {
            tour = new Tour();
        }

        public TourBuilder setId(long id) {
            tour.setId(id);
            return this;
        }

        public TourBuilder setTitle(String title) {
            tour.setTitle(title);
            return this;
        }

        public TourBuilder setDescription(String description) {
            tour.setDescription(description);
            return this;
        }

        public TourBuilder setTourType(TourType tourType) {
            tour.setTourType(tourType);
            return this;
        }

        public TourBuilder setPrice(BigDecimal price) {
            tour.setPrice(price);
            return this;
        }

        public TourBuilder setRoomType(RoomType roomType) {
            tour.setRoomType(roomType);
            return this;
        }

        public TourBuilder setStartDate(LocalDate startDate) {
            tour.setStartDate(startDate);
            return this;
        }

        public TourBuilder setPersons(int persons) {
            tour.setPersons(persons);
            return this;
        }

        public TourBuilder setNights(int nights) {
            tour.setNights(nights);
            return this;
        }

        public TourBuilder setBurning(boolean burning) {
            tour.setBurning(burning);
            return this;
        }

        public TourBuilder setStatus(Status status) {
            tour.setStatus(status);
            return this;
        }

        public Tour getTour() {
            return tour;
        }
    }
}
