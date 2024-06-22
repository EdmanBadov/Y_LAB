package org.example.models;

public class User {
    private String surname;
    private String email;
    private String password;
    private int age;
    private boolean admin;

    public User(String surname, String email, String password, int age, boolean admin) {
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.admin = admin;
    }

    public User() {

    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
