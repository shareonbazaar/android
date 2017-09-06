package eu.shareonbazaar.dev.bazaar.model.skill;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.shareonbazaar.dev.bazaar.model.Language;

public class Skill implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private Language languages;

    protected Skill(Parcel in) {
        id = in.readString();
        languages = in.readParcelable(Language.class.getClassLoader());
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
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
