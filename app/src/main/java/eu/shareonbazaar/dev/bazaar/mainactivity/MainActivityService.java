package eu.shareonbazaar.dev.bazaar.mainactivity;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MainActivityService {
    @POST("api/users")
    Call<Authentication> getUser(@Header("token") String token);
}
