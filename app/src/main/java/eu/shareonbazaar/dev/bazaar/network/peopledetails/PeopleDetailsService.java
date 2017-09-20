package eu.shareonbazaar.dev.bazaar.network.peopledetails;

import eu.shareonbazaar.dev.bazaar.model.people.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface PeopleDetailsService {
    @GET("api/users/{id}")
    Call<User> getUser(@Header("token") String token, @Path("id") String id);
}
