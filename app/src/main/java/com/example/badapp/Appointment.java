package com.example.badapp;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String patientName, patientEmail, appointmentDate, appointmentType, note, time, appointmentID;
    private Doctor doctor;

    public Appointment(){

    }

{}    public Appointment(String patientName,  String appointmentDate, String appointmentType, String note, String time, String appointmentID) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.note = note;
        this.time = time;
        this.appointmentID = appointmentID;
    }

    public Appointment(String name, String email, String date, String typeAppointment, String note, String time, String id) {
        this.patientName = name;
        this.patientEmail = email;
        this.appointmentDate = date;
        this.appointmentType = typeAppointment;
        this.note = note;
        this.time = time;
        this.appointmentID = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointment(String patientName, String patientEmail, String appointmentDate, String appointmentType, String note, String time, String appointmentID, Doctor doctor) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.note = note;
        this.time = time;
        this.appointmentID = appointmentID;
        this.doctor = doctor;
    }

    public Appointment(String patientName, String appointmentDate, String appointmentType, String note, String time) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.note = note;
        this.time = time;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getNote() {
        return note;
    }

    public String getTime() {
        return time;
    }
}
