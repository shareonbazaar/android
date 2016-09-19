package eu.shareonbazaar.dev.bazaar.network;

import eu.shareonbazaar.dev.bazaar.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UserService {

    @GET("/api/users")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options);
    /*@GET("/api/")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options);*/

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") String id);
}
