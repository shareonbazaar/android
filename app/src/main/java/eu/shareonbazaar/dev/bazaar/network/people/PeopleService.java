package eu.shareonbazaar.dev.bazaar.network.people;

import eu.shareonbazaar.dev.bazaar.model.people.PeopleJSON;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PeopleService {
    @GET("api/users")
    Call<PeopleJSON> getUsers(@Header("token") String token);

}
