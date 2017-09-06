package eu.shareonbazaar.dev.bazaar.model.people;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PeopleJSON implements Parcelable{
    @SerializedName("users")
    @Expose
    public ArrayList<User> users = new ArrayList<>();
    @SerializedName("error")
    @Expose
    public String error;

    protected PeopleJSON(Parcel in) {
        users = in.createTypedArrayList(User.CREATOR);
        error = in.readString();
    }

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

    public static final Creator<PeopleJSON> CREATOR = new Creator<PeopleJSON>() {
        @Override
        public PeopleJSON createFromParcel(Parcel in) {
            return new PeopleJSON(in);
        }

        @Override
        public PeopleJSON[] newArray(int size) {
            return new PeopleJSON[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(users);
        dest.writeString(error);
    }
}
