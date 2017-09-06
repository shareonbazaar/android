package eu.shareonbazaar.dev.bazaar.model.currentuser;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.Interest;
import eu.shareonbazaar.dev.bazaar.model.skill.Skill;

public class CurrentUser implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String userId;
    @SerializedName("email")
    @Expose
    private String userEmail;
    @SerializedName("profile")
    @Expose
    private CurrentUserProfile userProfile;
    @SerializedName("coins")
    @Expose
    private int userCoins;
    @SerializedName("aboutMe")
    @Expose
    private String userDescription;
    @SerializedName("_skills")
    @Expose
    private ArrayList<Skill> skills;
    @SerializedName("_interests")
    @Expose
    private ArrayList<Interest> interests;

    private CurrentUser(Parcel in) {
        userId = in.readString();
        userEmail = in.readString();
        userProfile = in.readParcelable(CurrentUserProfile.class.getClassLoader());
        userCoins = in.readInt();
        userDescription = in.readString();
        skills = in.createTypedArrayList(Skill.CREATOR);
        interests = in.createTypedArrayList(Interest.CREATOR);
    }

    public static final Creator<CurrentUser> CREATOR = new Creator<CurrentUser>() {
        @Override
        public CurrentUser createFromParcel(Parcel in) {
            return new CurrentUser(in);
        }

        @Override
        public CurrentUser[] newArray(int size) {
            return new CurrentUser[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public CurrentUserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(CurrentUserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public int getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(int userCoins) {
        this.userCoins = userCoins;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userEmail);
        dest.writeParcelable(userProfile, flags);
        dest.writeInt(userCoins);
        dest.writeString(userDescription);
        dest.writeTypedList(skills);
        dest.writeTypedList(interests);
    }
}
