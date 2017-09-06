package eu.shareonbazaar.dev.bazaar.model.wallet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.shareonbazaar.dev.bazaar.model.Language;


public class Service implements Parcelable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private Language languages;

    protected Service(Parcel in) {
        id = in.readString();
        languages = in.readParcelable(Language.class.getClassLoader());
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
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
