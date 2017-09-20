package eu.shareonbazaar.dev.bazaar.network.skillcategory;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.skill.SkillCategory;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillCategoryService {
    @GET("api/categories")
    Call<ArrayList<SkillCategory>> getSkills();
}
