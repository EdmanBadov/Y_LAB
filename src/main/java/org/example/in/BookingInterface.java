package org.example.in;

import java.util.ArrayList;
import java.util.List;
import org.example.out.BookingActions;
import org.example.out.UserActions;
import org.example.out.WorkspaceActions;
import org.example.models.Booking;
import org.example.models.User;
import org.example.models.Workspace;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingInterface {

    public static UserActions userActions = new UserActions();
    public static BookingActions bookingActions;
    public WorkspaceActions workspaceActions;

    private static final String CHOOSE_WORKSPACE = "Выберите рабочее место:";
    private static final String CHOOSE_BOOKING_DATE = "Выберите дату бронирования (гггг-мм-дд): ";
    private static final String CHOOSE_START_TIME = "Выберите время начала бронирования (чч-мм): ";
    private static final String CHOOSE_END_TIME = "Выберите время конца бронирования (чч-мм): ";
    private static final String ENTER_BOOKING_ID = "Введите Id бронирования: ";
    private static final String ALL_BOOKINGS = "Все бронирования: ";
    private static final String AVAILABLE_TIMES = "Свободное время: ";

    public BookingInterface(BookingActions bookingActions, UserActions userActions, WorkspaceActions workspaceActions) {
        this.bookingActions = bookingActions;
        this.userActions = userActions;
        this.workspaceActions = workspaceActions;
    }

    public void createBooking(int workspaceId, String dateString, String startTimeString, String endTimeString) {
        User user = userActions.getCurrentUser();
        Workspace workspace = workspaceActions.getWorkspaceById(workspaceId);
        LocalDate bookingDate = LocalDate.parse(dateString);
        LocalTime bookingTimeStart = LocalTime.parse(startTimeString);
        LocalTime bookingTimeEnd = LocalTime.parse(endTimeString);
        bookingActions.createBooking(user, workspace, bookingDate, bookingTimeStart, bookingTimeEnd);
    }

    public void updateBooking(int bookingId, String dateString, String startTimeString, String endTimeString) {
        Booking booking = bookingActions.getBookingById(bookingId);
        Workspace workspace = booking.getWorkspace();
        LocalDate bookingDate = LocalDate.parse(dateString);
        LocalTime bookingTimeStart = LocalTime.parse(startTimeString);
        LocalTime bookingTimeEnd = LocalTime.parse(endTimeString);
        bookingActions.updateBooking(bookingId, workspace, bookingDate, bookingTimeStart, bookingTimeEnd);
    }

    public void viewAllBookings() {
        bookingActions.viewAllBooking();
    }

    public void viewBookingsById(int bookingId) {
        bookingActions.viewBooking(bookingId);
    }

    public void deleteBookingsById(int bookingId) {
        bookingActions.deleteBooking(bookingId);
    }

    public void viewBookingsFilteredByUser(String email) {
        bookingActions.viewBookingsFilteredByUser(email);
    }

    public void viewBookingsFilteredByType(String type) {
        bookingActions.viewBookingsFilteredByType(type);
    }

    public void viewBookingsFilteredByDate(String dateString) {
        LocalDate bookingDate = LocalDate.parse(dateString);
        bookingActions.viewBookingsFilteredByDate(bookingDate);
    }

    public List<LocalTime[]> viewAvailableTimes(int workspaceId, String dateString) {
        Workspace workspace = workspaceActions.getWorkspaceById(workspaceId);
        LocalDate bookingDate = LocalDate.parse(dateString);
        List<Booking> bookingsOnDate = bookingActions.getBookingsByWorkspaceAndDate(workspace, bookingDate);

        LocalTime startOfDay = LocalTime.MIN;
        LocalTime endOfDay = LocalTime.MAX;
        List<LocalTime[]> availableTimes = new ArrayList<>();
        LocalTime currentStartTime = startOfDay;

        for (Booking booking : bookingsOnDate) {
            LocalTime bookingStart = booking.getBookingTimeStart();
            LocalTime bookingEnd = booking.getBookingTimeEnd();

            if (currentStartTime.isBefore(bookingStart)) {
                availableTimes.add(new LocalTime[]{currentStartTime, bookingStart.minusMinutes(1)});
            }
            currentStartTime = bookingEnd.plusMinutes(1);
        }

        if (currentStartTime.isBefore(endOfDay)) {
            availableTimes.add(new LocalTime[]{currentStartTime, endOfDay});
        }

        return availableTimes;
    }
}
