package eu.shareonbazaar.dev.bazaar.login;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("api/login")
    Call<Authentication> loginUser(@Field("email") String email,
                                   @Field("password") String password);
}
