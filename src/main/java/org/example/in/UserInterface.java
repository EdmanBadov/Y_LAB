package org.example.in;

import org.example.out.UserActions;

public class UserInterface {

    private UserActions userActions;

    public UserInterface(UserActions userActions) {
        this.userActions = userActions;
    }

    public void register(String surname, String password, String email, int age) {
        userActions.register(surname, email, password, age, false);
    }

    public void login(String email, String password) {
        userActions.login(email, password);
    }

    public void viewAllUsers() {
        userActions.viewAllUsers();
    }

    public void logout() {
        userActions.logout();
    }
}
