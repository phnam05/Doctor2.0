package com.example.badapp;

public class Users {

    private String fullname, email, birthdate, password, gender, role, userID, phone;


    public Users(String fullname, String email, String birthdate, String phone, String password, String gender, String role) {
        this.fullname = fullname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public String getName() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public Users() {

    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
