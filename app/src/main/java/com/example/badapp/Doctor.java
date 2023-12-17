package com.example.badapp;

import java.io.Serializable;

public class Doctor  implements Serializable {

    private String doctorID;
    public int resourceID;
    private String specialty;
    private int patientCount;
    private int experienceYears;
    private String fullname, email, birthdate,phone,password, gender, role;
    private boolean isSelected; // This is for UI state, not a Firestore field

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getSpecialty() {
        return specialty;
    }

    public void setId(String id){
        this.doctorID = id;
    }
    public int getPatientCount() {
        return patientCount;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getLocation() {
        return location;
    }

    private String location;

    // Constructor to initialize the member variables
    public Doctor (){

    }
    public Doctor(String name, String specialty, int patientCount, int experienceYears, String phoneNumber, String email, String location) {
        this.fullname = name;
        this.specialty = specialty;
        this.patientCount = patientCount;
        this.experienceYears = experienceYears;
        this.phone = phoneNumber;
        this.email = email;
        this.location = location;
    }


    public Doctor(String fullname, String email, String birthdate, String phone, String password, String gender, String role) {
        this.fullname = fullname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public void getSelected(boolean e){

    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public String getName() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

