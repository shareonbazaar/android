package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable{

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
    public ArrayList<JsonObject> skills = new ArrayList<>();
    @SerializedName("interests")
    @Expose
    public List<JsonObject> interests = new ArrayList<>();
    @SerializedName("aboutMe")
    @Expose
    public String aboutMe;

    public User(){

    }

    protected User(Parcel in) {
        name = in.readString();
        id = in.readString();
        picture = in.readString();
        hometown = in.readString();
        location = in.readString();
        status = in.readString();
        gender = in.readString();
        aboutMe = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public ArrayList<JsonObject> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<JsonObject> skills) {
        this.skills = skills;
    }

    public List<JsonObject> getInterests() {
        return interests;
    }

    public void setInterests(List<JsonObject> interests) {
        this.interests = interests;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(picture);
        dest.writeString(hometown);
        dest.writeString(location);
        dest.writeString(status);
        dest.writeString(gender);
        dest.writeString(aboutMe);
    }
}