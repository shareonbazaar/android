package eu.shareonbazaar.dev.bazaar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Interest implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private Language languages;

    protected Interest(Parcel in) {
        id = in.readString();
        languages = in.readParcelable(Language.class.getClassLoader());
    }

    public static final Creator<Interest> CREATOR = new Creator<Interest>() {
        @Override
        public Interest createFromParcel(Parcel in) {
            return new Interest(in);
        }

        @Override
        public Interest[] newArray(int size) {
            return new Interest[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(languages, flags);
    }
}
