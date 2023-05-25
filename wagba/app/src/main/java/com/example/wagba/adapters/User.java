package com.example.wagba.adapters;


import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    private String email;

    private String phoneNumber;
    private String fullName;

    public User(@NonNull String email, String fullName, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;

    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}