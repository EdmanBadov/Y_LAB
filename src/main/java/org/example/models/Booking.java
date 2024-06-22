package org.example.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private int id;
    private User user;
    private Workspace workspace;
    private LocalDate bookingDate;
    private LocalTime bookingTimeStart;
    private LocalTime bookingTimeEnd;

    public Booking() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTimeStart() {
        return bookingTimeStart;
    }

    public void setBookingTimeStart(LocalTime bookingTimeStart) {
        this.bookingTimeStart = bookingTimeStart;
    }

    public LocalTime getBookingTimeEnd() {
        return bookingTimeEnd;
    }

    public void setBookingTimeEnd(LocalTime bookingTimeEnd) {
        this.bookingTimeEnd = bookingTimeEnd;
    }

    public Booking(int id, User user, Workspace workspace, LocalDate bookingDate, LocalTime bookingTimeStart, LocalTime bookingTimeEnd) {
        this.id = id;
        this.user = user;
        this.workspace = workspace;
        this.bookingDate = bookingDate;
        this.bookingTimeStart = bookingTimeStart;
        this.bookingTimeEnd = bookingTimeEnd;
    }
}
