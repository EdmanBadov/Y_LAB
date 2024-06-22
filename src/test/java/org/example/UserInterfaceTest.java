package org.example;

import org.example.in.UserInterface;
import org.example.out.UserActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserInterfaceTest {

    private UserActions userActions;
    private UserInterface userInterface;

    @BeforeEach
    public void setUp() {
        userActions = mock(UserActions.class);
        userInterface = new UserInterface(userActions);
    }

    @Test
    public void testRegister() {
        userInterface.register("Badov", "password123", "doe@example.com", 30);
        verify(userActions).register("badov", "doe@example.com", "password123", 30, false);
    }

    @Test
    public void testLogin() {
        userInterface.login("badov@example.com", "password123");
        verify(userActions).login("badov@example.com", "password123");
    }

    @Test
    public void testViewAllUsers() {
        userInterface.viewAllUsers();
        verify(userActions).viewAllUsers();
    }

    @Test
    public void testLogout() {
        userInterface.logout();
        verify(userActions).logout();
    }
}
