package eu.shareonbazaar.dev.bazaar.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.TOKEN;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    private MainActivityContract.View mMainView;
    private Context mContext;

    public MainActivityPresenter(@NonNull MainActivityContract.View mainView){
        mMainView = mainView;
    }

    @Override
    public void start(Context context) {
        mContext = context;
        fetchCurrentUser();
    }

    @Override
    public void fetchCurrentUser() {
        SharedPreference sharedPreference = new SharedPreference(mContext);
        String token = sharedPreference.retrieveToken(TOKEN);
        MainActivityService service = ConnectionSetup.retrofit.create(MainActivityService.class);
        service.getUser(token)
                .enqueue(new Callback<Authentication>() {

                    @Override
                    public void onResponse(Call<Authentication> call,
                                           Response<Authentication> response) {
                        checkCurrentUserJson(response.body());
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        connectionError();
                        Log.d("FAILED", "Failed fetching");
                    }
                });
    }

    @Override
    public void checkCurrentUserJson(Authentication authentication) {
        // TODO: Check for error authentication.getError()
        if(authentication.getError() == null){
            mMainView.initializeCurrentUserDetails(authentication.getCurrentUser());
        }
    }

    @Override
    public void connectionError() {

    }
}
