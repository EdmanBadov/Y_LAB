package org.example;

import org.example.models.User;
import org.example.out.UserActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserActionsTest {

    private UserActions userActions;

    @BeforeEach
    public void setUp() {
        userActions = new UserActions();
    }

    @Test
    public void testRegisterNewUser() {
        String result = userActions.register("testUser", "test@example.com", "password123", 25, false);
        assertEquals(UserActions.USER_REGISTERED, result);
    }

    @Test
    public void testRegisterExistingUser() {
        userActions.register("testUser", "test@example.com", "password123", 25, false);
        String result = userActions.register("testUser", "test@example.com", "password123", 25, false);
        assertEquals(UserActions.USER_EXISTS, result);
    }

    @Test
    public void testLoginSuccess() {
        userActions.register("testUser", "test@example.com", "password123", 25, false);
        String result = userActions.login("test@example.com", "password123");
        assertEquals(UserActions.USER_LOGGED_IN, result);
    }

    @Test
    public void testLoginFailure() {
        String result = userActions.login("nonexistent@example.com", "wrongpassword");
        assertEquals(UserActions.ERROR, result);
    }

    @Test
    public void testLogout() {
        userActions.register("testUser", "test@example.com", "password123", 25, false);
        userActions.login("test@example.com", "password123");
        String result = userActions.logout();
        assertEquals(UserActions.USER_LOGGED_OUT, result);
    }

    @Test
    public void testViewAllUsers() {
        userActions.register("testUser1", "test1@example.com", "password123", 25, false);
        userActions.register("testUser2", "test2@example.com", "password123", 30, false);
        String result = userActions.viewAllUsers();
        assertTrue(result.contains("testUser1"));
        assertTrue(result.contains("testUser2"));
    }

    @Test
    public void testGetUserByEmail() {
        userActions.register("testUser", "test@example.com", "password123", 25, false);
        User user = userActions.getUserByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("testUser", user.getSurname());
    }

    @Test
    public void testGetCurrentUser() {
        assertEquals("admin", userActions.getCurrentUser().getSurname());
    }
}
