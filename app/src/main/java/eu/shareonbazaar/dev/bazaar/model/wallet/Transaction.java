package eu.shareonbazaar.dev.bazaar.model.wallet;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Transaction implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("_messages")
    @Expose
    private ArrayList<Message> messages;
    /*@SerializedName("loc")
    @Expose
    private Location location;*/
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("request_type")
    @Expose
    private String requestType;
    @SerializedName("_participants")
    @Expose
    private ArrayList<Participant> participants;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    protected Transaction(Parcel in) {
        id = in.readString();
        service = in.readParcelable(Service.class.getClassLoader());
        messages = in.createTypedArrayList(Message.CREATOR);
        // location = in.readParcelable(Location.class.getClassLoader());
        status = in.readString();
        requestType = in.readString();
        participants = in.createTypedArrayList(Participant.CREATOR);
        createdAt = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /*public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(service, flags);
        dest.writeTypedList(messages);
        // dest.writeParcelable(location, flags);
        dest.writeString(status);
        dest.writeString(requestType);
        dest.writeTypedList(participants);
        dest.writeString(createdAt);
    }
}
