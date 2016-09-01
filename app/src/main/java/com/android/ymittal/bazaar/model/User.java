package com.android.ymittal.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("hometown")
    @Expose
    public String hometown;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("coins")
    @Expose
    public Integer coins;
    @SerializedName("skills")
    @Expose
    public List<String> skills = new ArrayList<String>();
    @SerializedName("interests")
    @Expose
    public List<String> interests = new ArrayList<String>();
    @SerializedName("aboutMe")
    @Expose
    public String aboutMe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}