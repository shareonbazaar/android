package eu.shareonbazaar.dev.bazaar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Languages {
    @SerializedName("ar")
    @Expose
    public String ar;
    @SerializedName("de")
    @Expose
    public String de;
    @SerializedName("en")
    @Expose
    public String en;

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
