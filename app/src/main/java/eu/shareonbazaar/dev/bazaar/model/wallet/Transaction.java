package eu.shareonbazaar.dev.bazaar.model.wallet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Transaction {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("_messages")
    @Expose
    private ArrayList<Message> messages;
    @SerializedName("loc")
    @Expose
    private Location location;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("request_type")
    @Expose
    private String requestType;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
}
