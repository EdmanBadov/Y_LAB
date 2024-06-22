package org.example.out;

import org.example.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserActions {

    public static final String USER_REGISTERED = "Пользователь зарегистрирован";
    public static final String USER_EXISTS = "Такой пользователь уже существует";
    public static final String USER_LOGGED_IN = "Вход";
    public static final String USER_LOGGED_OUT = "Выход";
    public static final String ERROR = "Неправильная почта или пароль";

    private static User currentUser = null;
    private Map<String, User> users = new HashMap<>();
    private User admin = new User("admin", "admin", "admin123", 1, true);

    public UserActions() {
        users.put("admin", admin);
        currentUser = admin;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public String viewAllUsers() {
        StringBuilder allUsers = new StringBuilder();
        for (User user : users.values()) {
            allUsers.append(user.getSurname()).append("\n");
        }
        return allUsers.toString();
    }

    public String register(String surname, String email, String password, int age, boolean isAdmin) {
        if (!users.containsKey(email)) {
            User newUser = new User(surname, email, password, age, isAdmin);
            users.put(email, newUser);
            return USER_REGISTERED;
        } else {
            return USER_EXISTS;
        }
    }

    public String login(String email, String password) {
        User user = users.get(email);

        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            currentUser = user;
            return USER_LOGGED_IN;
        } else {
            return ERROR;
        }
    }

    public String logout() {
        currentUser = admin;
        return USER_LOGGED_OUT;
    }

    public User getAdmin() {
        return admin;
    }
}
