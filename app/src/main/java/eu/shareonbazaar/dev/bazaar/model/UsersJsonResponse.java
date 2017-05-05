package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersJsonResponse {
    @SerializedName("users")
    @Expose
    public ArrayList<User> users = new ArrayList<>();
    @SerializedName("error")
    @Expose
    public String error;

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
}
