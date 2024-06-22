package org.example.out;

import org.example.models.Booking;
import org.example.models.User;
import org.example.models.Workspace;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingActions {
    public static final String WORKSPACE_ALREADY_BOOKED = "Рабочее место уже забронировано";
    public static final String BOOKED = "Забронировано";
    public static final String BOOKING_NOT_FOUND = "Бронирование не найдено";
    public static final String BOOKINGS_NOT_FOUND = "Бронирований не найдено";
    public static final String YOU_HAVE_NO_ROOT = "У вас недостаточно прав, чтобы изменить это бронирование";
    public static final String BOOKING_REMOVED = "Бронирование удалено";
    public static final String BOOKING_UPDATED = "Бронирование обновлено";

    private Map<Integer, Booking> bookings = new HashMap<>();
    private int bookingId = 0;

    private UserActions userActions;

    public BookingActions(UserActions userActions) {
        this.userActions = userActions;
    }

    public String createBooking(User user, Workspace workspace, LocalDate bookingDate,
                                LocalTime bookingTimeStart, LocalTime bookingTimeEnd) {
        for (Booking booking : bookings.values()) {
            if (booking.getWorkspace() != null && booking.getWorkspace().equals(workspace) && booking.getBookingDate().equals(bookingDate)) {
                if (!(bookingTimeEnd.isBefore(booking.getBookingTimeStart()) || bookingTimeStart.isAfter(booking.getBookingTimeEnd()))) {
                    return WORKSPACE_ALREADY_BOOKED;
                }
            }
        }

        Booking newBooking = new Booking(++bookingId, user, workspace, bookingDate, bookingTimeStart, bookingTimeEnd);
        bookings.put(bookingId, newBooking);
        return BOOKED;
    }

    public List<String> viewAllBooking() {
        List<String> bookingInfos = new ArrayList<>();
        if (bookings.isEmpty()) {
            bookingInfos.add(BOOKINGS_NOT_FOUND);
        } else {
            for (Booking booking : bookings.values()) {
                String email = booking.getUser().getEmail();
                User user = userActions.getUserByEmail(email);
                if (user != null) {
                    bookingInfos.add(getBookingInfo(booking));
                } else {
                    bookingInfos.add("User not found for booking ID: " + booking.getId());
                }
            }
        }
        return bookingInfos;
    }

    public String viewBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            return getBookingInfo(booking);
        } else {
            return BOOKING_NOT_FOUND;
        }
    }

    public List<String> viewBookingsFilteredByUser(String email) {
        List<String> bookingInfos = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getUser().getEmail().equals(email)) {
                bookingInfos.add(getBookingInfo(booking));
            }
        }
        if (bookingInfos.isEmpty()) {
            bookingInfos.add(BOOKING_NOT_FOUND);
        }
        return bookingInfos;
    }

    public List<String> viewBookingsFilteredByType(String type) {
        List<String> bookingInfos = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getWorkspace().getWorkspaceType().equals(type)) {
                bookingInfos.add(getBookingInfo(booking));
            }
        }
        if (bookingInfos.isEmpty()) {
            bookingInfos.add(BOOKING_NOT_FOUND);
        }
        return bookingInfos;
    }

    public List<String> viewBookingsFilteredByDate(LocalDate date) {
        List<String> bookingInfos = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getBookingDate().equals(date)) {
                bookingInfos.add(getBookingInfo(booking));
            }
        }
        if (bookingInfos.isEmpty()) {
            bookingInfos.add(BOOKING_NOT_FOUND);
        }
        return bookingInfos;
    }

    public String updateBooking(int id, Workspace workspace, LocalDate bookingDate,
                                LocalTime bookingTimeStart, LocalTime bookingTimeEnd) {
        Booking bookingToUpdate = getBookingById(id);

        if (bookingToUpdate == null) {
            return BOOKING_NOT_FOUND;
        }

        if (!(bookingToUpdate.getUser().equals(userActions.getCurrentUser()) ||
                bookingToUpdate.getUser().equals(userActions.getAdmin()))) {
            return YOU_HAVE_NO_ROOT;
        }

        LocalTime originalStartTime = bookingToUpdate.getBookingTimeStart();
        LocalTime originalEndTime = bookingToUpdate.getBookingTimeEnd();

        bookingToUpdate.setBookingTimeStart(null);
        bookingToUpdate.setBookingTimeEnd(null);

        for (Booking booking : bookings.values()) {
            if (booking.getWorkspace().equals(workspace) && booking.getBookingDate().equals(bookingDate)) {
                LocalTime otherStartTime = booking.getBookingTimeStart();
                LocalTime otherEndTime = booking.getBookingTimeEnd();
                if (otherStartTime != null && otherEndTime != null) {
                    if (!(bookingTimeEnd.isBefore(otherStartTime) || bookingTimeStart.isAfter(otherEndTime))) {
                        bookingToUpdate.setBookingTimeStart(originalStartTime);
                        bookingToUpdate.setBookingTimeEnd(originalEndTime);
                        return WORKSPACE_ALREADY_BOOKED;
                    }
                }
            }
        }

        bookingToUpdate.setBookingDate(bookingDate);
        bookingToUpdate.setBookingTimeStart(bookingTimeStart);
        bookingToUpdate.setBookingTimeEnd(bookingTimeEnd);
        return BOOKING_UPDATED;
    }

    public List<Booking> getBookingsByWorkspaceAndDate(Workspace workspace, LocalDate date) {
        List<Booking> bookingsOnDate = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getWorkspace().equals(workspace) && booking.getBookingDate().equals(date)) {
                bookingsOnDate.add(booking);
            }
        }
        bookingsOnDate.sort(Comparator.comparing(Booking::getBookingTimeStart));
        return bookingsOnDate;
    }

    public String deleteBooking(int bookingId) {
        Booking removedBooking = getBookingById(bookingId);
        bookings.remove(bookingId);
        if (removedBooking != null) {
            return BOOKING_REMOVED;
        } else {
            return BOOKING_NOT_FOUND;
        }
    }

    public Booking getBookingById(int id) {
        return bookings.get(id);
    }

    private String getBookingInfo(Booking booking) {
        User user = userActions.getUserByEmail(booking.getUser().getEmail());
        return "User: " + (user != null ? user.getSurname() : "Unknown") +
                "\nWorkspace: " + booking.getWorkspace() +
                "\nBooking date: " + booking.getBookingDate() +
                "\nBooking start time: " + booking.getBookingTimeStart() +
                "\nBooking end time: " + booking.getBookingTimeEnd() +
                "\n";
    }
}
