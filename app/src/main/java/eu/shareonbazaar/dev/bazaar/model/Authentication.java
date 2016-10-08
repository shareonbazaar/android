package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
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

    public void setError(String error) {
        this.error = error;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}