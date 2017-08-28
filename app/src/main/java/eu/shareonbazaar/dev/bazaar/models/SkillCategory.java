package eu.shareonbazaar.dev.bazaar.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SkillCategory implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_skills")
    @Expose
    private ArrayList<SkillObject> skillObject;
    @SerializedName("label")
    @Expose
    private Label label;

    protected SkillCategory(Parcel in) {
        id = in.readString();
        label = in.readParcelable(Label.class.getClassLoader());
    }

    public static final Creator<SkillCategory> CREATOR = new Creator<SkillCategory>() {
        @Override
        public SkillCategory createFromParcel(Parcel in) {
            return new SkillCategory(in);
        }

        @Override
        public SkillCategory[] newArray(int size) {
            return new SkillCategory[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SkillObject> getSkillObject() {
        return skillObject;
    }

    public void setSkillObject(ArrayList<SkillObject> skillObject) {
        this.skillObject = skillObject;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(label, flags);
    }
}
