package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoggedInUser implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String userId;
    @SerializedName("email")
    @Expose
    private String userEmail;
    @SerializedName("profile")
    @Expose
    private LoggedInUserProfile userProfile;
    @SerializedName("coins")
    @Expose
    private int userCoins;
    @SerializedName("aboutMe")
    @Expose
    private String userDescription;
    @SerializedName("_skills")
    @Expose
    public ArrayList<JsonObject> skills;
    @SerializedName("_interests")
    @Expose
    public List<JsonObject> interests;

    protected LoggedInUser(Parcel in) {
        userId = in.readString();
        userEmail = in.readString();
        userProfile = in.readParcelable(LoggedInUserProfile.class.getClassLoader());
        userCoins = in.readInt();
        userDescription = in.readString();
        skills = in.createTypedArrayList(JsonObject.CREATOR);
        interests = in.createTypedArrayList(JsonObject.CREATOR);
    }

    public static final Creator<LoggedInUser> CREATOR = new Creator<LoggedInUser>() {
        @Override
        public LoggedInUser createFromParcel(Parcel in) {
            return new LoggedInUser(in);
        }

        @Override
        public LoggedInUser[] newArray(int size) {
            return new LoggedInUser[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LoggedInUserProfile getUserProfile() {
        return userProfile;
    }

    public int getUserCoins() {
        return userCoins;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public ArrayList<JsonObject> getSkills() {
        return skills;
    }

    public List<JsonObject> getInterests() {
        return interests;
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
