package com.danilketov.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Worker {

    @SerializedName("f_name")
    private String firstName;

    @SerializedName("l_name")
    private String lastName;

    private String birthday;

    @SerializedName("avatr_url")
    private String avatarUrl;

    private List<Specialty> specialty;

    public Worker(String firstName, String lastName, String birthday, String avatarUrl, List<Specialty> specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.specialty = specialty;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Specialty> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<Specialty> specialty) {
        this.specialty = specialty;
    }
}
