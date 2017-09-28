package eu.shareonbazaar.dev.bazaar.people;

import eu.shareonbazaar.dev.bazaar.model.people.PeopleJSON;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

interface PeopleService {
    @GET("api/users")
    Observable<PeopleJSON> getUsers(@Header("token") String token);
}
