package com.episen.ms.model;

public class User {

    private String username;

    private String email;

    private int age;

    public User(String username, String email, int age, String role) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    };

    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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



}
