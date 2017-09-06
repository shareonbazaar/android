package eu.shareonbazaar.dev.bazaar.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.shareonbazaar.dev.bazaar.model.LoggedInUser;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUser;

public class Authentication implements Parcelable{

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private CurrentUser currentUser;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private int status;


    private Authentication(Parcel in) {
        token = in.readString();
        currentUser = in.readParcelable(LoggedInUser.class.getClassLoader());
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

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeParcelable(currentUser, flags);
        dest.writeString(error);
        dest.writeInt(status);
    }
}