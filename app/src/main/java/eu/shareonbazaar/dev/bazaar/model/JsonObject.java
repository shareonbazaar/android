package eu.shareonbazaar.dev.bazaar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonObject implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private Languages label;

    private JsonObject(){

    }

    protected JsonObject(Parcel in) {
        id = in.readString();
        label = in.readParcelable(Languages.class.getClassLoader());
    }

    public static final Creator<JsonObject> CREATOR = new Creator<JsonObject>() {
        @Override
        public JsonObject createFromParcel(Parcel in) {
            return new JsonObject(in);
        }

        @Override
        public JsonObject[] newArray(int size) {
            return new JsonObject[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Languages getLabel() {
        return label;
    }

    public void setLabel(Languages label) {
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(label, flags);
    }
}
