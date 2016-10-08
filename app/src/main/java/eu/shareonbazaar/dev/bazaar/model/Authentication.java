package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("token")
    @Expose
    private String error;
    @SerializedName("token")
    @Expose
    private int status;

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}