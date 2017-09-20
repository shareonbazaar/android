package eu.shareonbazaar.dev.bazaar.bookmark;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.model.people.PeopleJSON;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookmarkService {
    @GET("api/users")
    Observable<PeopleJSON> getUsers(@Header("token") String token);
    @POST("api/users")
    Observable<Authentication> getUser(@Header("token") String token);
}
