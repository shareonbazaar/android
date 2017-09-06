package eu.shareonbazaar.dev.bazaar.model.skill;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.Language;

public class SkillCategory implements Parcelable{
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_skills")
    @Expose
    private ArrayList<Skill> skills;
    @SerializedName("label")
    @Expose
    private Language languages;

    protected SkillCategory(Parcel in) {
        id = in.readString();
        skills = in.createTypedArrayList(Skill.CREATOR);
        languages = in.readParcelable(Language.class.getClassLoader());
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

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeTypedList(skills);
        dest.writeParcelable(languages, flags);
    }
}
