package com.example.badapp;

import java.io.Serializable;

public class Doctor extends Users implements Serializable {

    private String doctorID;
    public int resourceID;
    private String specialty;
    private String patientCount;
    private String experienceYears;
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
    public String getPatientCount() {
        return patientCount;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public String getLocation() {
        return location;
    }

    private String location;

    // Constructor to initialize the member variables
    public Doctor (){

    }

    public Doctor(String fullname, String email, String birthdate, String phone,String password, String gender, String role){
        super(fullname, email, birthdate, phone,password, gender, role);
    }
    public void getSelected(boolean e){

    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setPatientCount(String patientCount) {
        this.patientCount = patientCount;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

