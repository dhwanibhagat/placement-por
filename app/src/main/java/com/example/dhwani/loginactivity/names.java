package com.example.dhwani.loginactivity;

/**
 * Created by Dhwani on 3/20/2018.
 */

public class names {
    private String UID;
    private String fullname;
    private String email;
    private String password;

    private String c_date;

    public names(String UID, String fullname, String email, String password, String c_date) {
        this.UID = UID;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.c_date = c_date;
    }

    public String getUID() {
        return UID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getC_date() {
        return c_date;
    }
}
