package eu.shareonbazaar.dev.bazaar.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillObject {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private Label label;

}
