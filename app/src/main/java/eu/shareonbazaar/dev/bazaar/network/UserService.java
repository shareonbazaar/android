package eu.shareonbazaar.dev.bazaar.network;

import java.util.List;
import java.util.Map;

import eu.shareonbazaar.dev.bazaar.login.Token;
import eu.shareonbazaar.dev.bazaar.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UserService {

    @GET("/api/users")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options);

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @FormUrlEncoded
    @POST("api/login")
    Call<Token> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/signup")
    Call<Token> signupUser(@Field("email") String email, @Field("password") String password);
}
