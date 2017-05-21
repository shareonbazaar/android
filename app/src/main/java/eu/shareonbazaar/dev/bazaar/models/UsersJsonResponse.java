package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersJsonResponse implements Parcelable{
    @SerializedName("users")
    @Expose
    public ArrayList<User> users = new ArrayList<>();
    @SerializedName("error")
    @Expose
    public String error;

    public UsersJsonResponse(){

    }

    protected UsersJsonResponse(Parcel in) {
        users = in.readArrayList(null);
        error = in.readString();
    }

    public static final Creator<UsersJsonResponse> CREATOR = new Creator<UsersJsonResponse>() {
        @Override
        public UsersJsonResponse createFromParcel(Parcel in) {
            return new UsersJsonResponse(in);
        }

        @Override
        public UsersJsonResponse[] newArray(int size) {
            return new UsersJsonResponse[size];
        }
    };

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeList(users);
    }
}
