package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Larry on 9/19/2016.
 */
public class JsonObject {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("label")
    @Expose
    public String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String id) {
        this.label = label;
    }

}
