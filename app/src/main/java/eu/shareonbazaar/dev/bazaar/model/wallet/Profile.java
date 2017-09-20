package eu.shareonbazaar.dev.bazaar.model.wallet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile implements Parcelable {
    @SerializedName("picture")
    @Expose
    private String imageUrl;
    @SerializedName("hometown")
    @Expose
    private String hometown;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private String name;

    protected Profile(Parcel in) {
        imageUrl = in.readString();
        hometown = in.readString();
        location = in.readString();
        status = in.readString();
        gender = in.readString();
        name = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(hometown);
        dest.writeString(location);
        dest.writeString(status);
        dest.writeString(gender);
        dest.writeString(name);
    }
}
