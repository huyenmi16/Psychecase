package com.example.psychecare.models;

public class Doctor {
    private int id;
    private String image;
    private String name;
    private String specialty;
    private String gender;
    private int experience;
    private String phoneNumber;
    private double consultationFee;
    private boolean isFavorite;

    public Doctor() {
    }

    public Doctor(int id, String image, String name, String specialty, String gender, int experience, String phoneNumber, double consultationFee, boolean isFavorite) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.specialty = specialty;
        this.gender = gender;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
        this.consultationFee = consultationFee;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    // Constructors, getters, and setters
}
