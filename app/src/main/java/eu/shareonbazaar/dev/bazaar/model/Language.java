package eu.shareonbazaar.dev.bazaar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language implements Parcelable{
    @SerializedName("ar")
    @Expose
    private String arabic;
    @SerializedName("de")
    @Expose
    private String dutch;
    @SerializedName("en")
    @Expose
    private String english;

    protected Language(Parcel in) {
        arabic = in.readString();
        dutch = in.readString();
        english = in.readString();
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getDutch() {
        return dutch;
    }

    public void setDutch(String dutch) {
        this.dutch = dutch;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(arabic);
        dest.writeString(dutch);
        dest.writeString(english);
    }
}
