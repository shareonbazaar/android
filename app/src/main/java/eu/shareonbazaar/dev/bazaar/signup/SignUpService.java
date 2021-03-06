package eu.shareonbazaar.dev.bazaar.signup;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpService {
    @FormUrlEncoded
    @POST("api/signup")
    Call<Authentication> signUpUser(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("confirmPassword") String confirmPassword,
                                    @Field("first_name") String first_name,
                                    @Field("last_name") String last_name);
}
