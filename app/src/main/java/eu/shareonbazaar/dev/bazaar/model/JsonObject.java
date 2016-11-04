package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Larry on 9/19/2016.
 */
public class JsonObject {
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("label")
    @Expose
    public Languages label;

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

}
