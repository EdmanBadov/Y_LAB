package org.example;

import org.example.models.Booking;
import org.example.models.User;
import org.example.models.Workspace;
import org.example.out.BookingActions;
import org.example.out.UserActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingActionsTest {

    private BookingActions bookingActions;
    private UserActions userActions;

    @BeforeEach
    public void setUp() {
        userActions = mock(UserActions.class);
        bookingActions = new BookingActions(userActions);
    }

    @Test
    public void testCreateBooking() {
        User user = new User();
        Workspace workspace = new Workspace();
        when(userActions.getCurrentUser()).thenReturn(user);

        String result = bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));
        assertEquals(BookingActions.BOOKED, result);
    }

    @Test
    public void testCreateBookingWorkspaceAlreadyBooked() {
        User user = new User();
        Workspace workspace = new Workspace();
        when(userActions.getCurrentUser()).thenReturn(user);

        bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));
        String result = bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(11, 0), LocalTime.of(13, 0));
        assertEquals(BookingActions.WORKSPACE_ALREADY_BOOKED, result);
    }


    @Test
    public void testViewBooking() {
        String result = bookingActions.viewBooking(1);
        assertEquals(BookingActions.BOOKING_NOT_FOUND, result);
    }



    @Test
    public void testDeleteBooking() {
        User user = new User();
        Workspace workspace = new Workspace();
        bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));

        String result = bookingActions.deleteBooking(1);
        assertEquals(BookingActions.BOOKING_REMOVED, result);
    }

    @Test
    public void testViewBookingsFilteredByUser() {
        User user = new User();
        user.setEmail("test@example.com");
        Workspace workspace = new Workspace();
        bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));

        List<String> result = bookingActions.viewBookingsFilteredByUser("test@example.com");
        assertFalse(result.isEmpty());
    }

    @Test
    public void testViewBookingsFilteredByType() {
        User user = new User();
        Workspace workspace = new Workspace();
        workspace.setWorkspaceType("Conference Room");
        bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));

        List<String> result = bookingActions.viewBookingsFilteredByType("Конференц-зал");
        assertFalse(result.isEmpty());
    }

    @Test
    public void testViewBookingsFilteredByDate() {
        User user = new User();
        Workspace workspace = new Workspace();
        bookingActions.createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));

        List<String> result = bookingActions.viewBookingsFilteredByDate(LocalDate.of(2023, 6, 22));
        assertFalse(result.isEmpty());
    }
}
