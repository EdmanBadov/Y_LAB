package org.example;

import static org.mockito.Mockito.*;

import org.example.in.BookingInterface;
import org.example.out.BookingActions;
import org.example.out.UserActions;
import org.example.out.WorkspaceActions;
import org.example.models.Booking;
import org.example.models.User;
import org.example.models.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingInterfaceTest {

    private BookingActions bookingActions;
    private UserActions userActions;
    private WorkspaceActions workspaceActions;
    private BookingInterface bookingInterface;

    @BeforeEach
    public void setUp() {
        bookingActions = mock(BookingActions.class);
        userActions = mock(UserActions.class);
        workspaceActions = mock(WorkspaceActions.class);
        bookingInterface = new BookingInterface(bookingActions, userActions, workspaceActions);
    }

    @Test
    public void testCreateBooking() {
        User user = new User();
        when(userActions.getCurrentUser()).thenReturn(user);
        Workspace workspace = new Workspace();
        when(workspaceActions.getWorkspaceById(1)).thenReturn(workspace);

        bookingInterface.createBooking(1, "2023-06-22", "10:00", "12:00");

        verify(bookingActions).createBooking(user, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));
    }

    @Test
    public void testUpdateBooking() {
        Workspace workspace = new Workspace();
        Booking booking = new Booking();
        booking.setWorkspace(workspace);
        when(bookingActions.getBookingById(1)).thenReturn(booking);

        bookingInterface.updateBooking(1, "2023-06-22", "10:00", "12:00");

        verify(bookingActions).updateBooking(1, workspace, LocalDate.of(2023, 6, 22), LocalTime.of(10, 0), LocalTime.of(12, 0));
    }

    @Test
    public void testViewAllBookings() {
        bookingInterface.viewAllBookings();

        verify(bookingActions).viewAllBooking();
    }

    @Test
    public void testViewBookingsById() {
        bookingInterface.viewBookingsById(1);

        verify(bookingActions).viewBooking(1);
    }

    @Test
    public void testDeleteBookingsById() {
        bookingInterface.deleteBookingsById(1);

        verify(bookingActions).deleteBooking(1);
    }

    @Test
    public void testViewBookingsFilteredByUser() {
        bookingInterface.viewBookingsFilteredByUser("user@example.com");

        verify(bookingActions).viewBookingsFilteredByUser("user@example.com");
    }

    @Test
    public void testViewBookingsFilteredByType() {
        bookingInterface.viewBookingsFilteredByType("Конференц-зал");

        verify(bookingActions).viewBookingsFilteredByType("Конференц-зал");
    }

    @Test
    public void testViewBookingsFilteredByDate() {
        bookingInterface.viewBookingsFilteredByDate("2023-06-22");

        verify(bookingActions).viewBookingsFilteredByDate(LocalDate.of(2023, 6, 22));
    }

    @Test
    public void testViewAvailableTimes() {
        Workspace workspace = new Workspace();
        when(workspaceActions.getWorkspaceById(1)).thenReturn(workspace);
        List<Booking> bookings = new ArrayList<>();
        when(bookingActions.getBookingsByWorkspaceAndDate(workspace, LocalDate.of(2023, 6, 22))).thenReturn(bookings);

        List<LocalTime[]> availableTimes = bookingInterface.viewAvailableTimes(1, "2023-06-22");

        verify(workspaceActions).getWorkspaceById(1);
        verify(bookingActions).getBookingsByWorkspaceAndDate(workspace, LocalDate.of(2023, 6, 22));
    }
}
