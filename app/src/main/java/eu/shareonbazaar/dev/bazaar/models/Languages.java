package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Languages implements Parcelable{
    @SerializedName("ar")
    @Expose
    private String ar;
    @SerializedName("de")
    @Expose
    private String de;
    @SerializedName("en")
    @Expose
    private String en;

    public Languages(){

    }

    protected Languages(Parcel in) {
        ar = in.readString();
        de = in.readString();
        en = in.readString();
    }

    public static final Creator<Languages> CREATOR = new Creator<Languages>() {
        @Override
        public Languages createFromParcel(Parcel in) {
            return new Languages(in);
        }

        @Override
        public Languages[] newArray(int size) {
            return new Languages[size];
        }
    };

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ar);
        dest.writeString(de);
        dest.writeString(en);
    }
}
