package com.example.psychecare.models;

public class Appointment {
    private int id;
    private String doctorName;
    private String doctorImage;
    private String appointmentDate;
    private String appointmentTime;
    private String userName;
    private String userPhoneNumber;
    private String userEmail;
    private String note;
    private String consultationFee;
    private String issue;

    public Appointment() {
    }

    public Appointment(int id, String doctorName, String doctorImage, String appointmentDate, String appointmentTime, String userName, String userPhoneNumber, String userEmail, String note, String consultationFee,String issue) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorImage = doctorImage;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.note = note;
        this.consultationFee = consultationFee;
        this.issue= issue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}