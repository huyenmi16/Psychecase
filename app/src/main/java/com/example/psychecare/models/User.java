package com.example.psychecare.models;



public class User {
    private int id;
    private String phone;
    private String name;
    private String email;
    private String password;

    // Constructor
    public User(int id, String phone, String name, String email, String password) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
