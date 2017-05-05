package eu.shareonbazaar.dev.bazaar.network;

import java.util.List;
import java.util.Map;

import eu.shareonbazaar.dev.bazaar.model.Authentication;
import eu.shareonbazaar.dev.bazaar.model.User;
import eu.shareonbazaar.dev.bazaar.model.UsersJsonResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UserService {

    @GET("/api/users")
    Call<UsersJsonResponse> getUsers(@Header("token") String token);

    @GET("api/users/{id}")
    Call<User> getUser(@Header("token") String token, @Path("id") String id);

    @FormUrlEncoded
    @POST("api/login")
    Call<Authentication> loginUser(@Field("email") String email,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("api/signup")
    Call<Authentication> signupUser(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("confirmPassword") String confirmPassword,
                                    @Field("first_name") String first_name,
                                    @Field("last_name") String last_name);
}
