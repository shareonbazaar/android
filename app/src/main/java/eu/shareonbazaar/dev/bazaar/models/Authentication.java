package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication implements Parcelable{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private LoggedInUser loggedInUser;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private int status;


    protected Authentication(Parcel in) {
        token = in.readString();
        loggedInUser = in.readParcelable(LoggedInUser.class.getClassLoader());
        error = in.readString();
        status = in.readInt();
    }

    public static final Creator<Authentication> CREATOR = new Creator<Authentication>() {
        @Override
        public Authentication createFromParcel(Parcel in) {
            return new Authentication(in);
        }

        @Override
        public Authentication[] newArray(int size) {
            return new Authentication[size];
        }
    };

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeParcelable(loggedInUser, flags);
        dest.writeString(error);
        dest.writeInt(status);
    }
}