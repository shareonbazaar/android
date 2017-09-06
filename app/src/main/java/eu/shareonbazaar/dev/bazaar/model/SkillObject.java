package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillObject {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("language")
    @Expose
    private Language language;

}
