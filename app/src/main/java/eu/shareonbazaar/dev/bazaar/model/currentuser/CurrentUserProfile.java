package eu.shareonbazaar.dev.bazaar.model.currentuser;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentUserProfile implements Parcelable{
    @SerializedName("picture")
    @Expose
    private String userImageUrl;
    @SerializedName("hometown")
    @Expose
    private String userHometown;
    @SerializedName("location")
    @Expose
    private String userLocation;
    @SerializedName("status")
    @Expose
    private String userStatus;
    @SerializedName("gender")
    @Expose
    private String userGender;
    @SerializedName("name")
    @Expose
    private String userName;

    private CurrentUserProfile(Parcel in) {
        userImageUrl = in.readString();
        userHometown = in.readString();
        userLocation = in.readString();
        userStatus = in.readString();
        userGender = in.readString();
        userName = in.readString();
    }

    public static final Creator<CurrentUserProfile> CREATOR = new Creator<CurrentUserProfile>() {
        @Override
        public CurrentUserProfile createFromParcel(Parcel in) {
            return new CurrentUserProfile(in);
        }

        @Override
        public CurrentUserProfile[] newArray(int size) {
            return new CurrentUserProfile[size];
        }
    };

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getUserHometown() {
        return userHometown;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userImageUrl);
        dest.writeString(userHometown);
        dest.writeString(userLocation);
        dest.writeString(userStatus);
        dest.writeString(userGender);
        dest.writeString(userName);
    }
}
