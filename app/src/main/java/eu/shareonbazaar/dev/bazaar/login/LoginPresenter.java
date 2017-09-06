package eu.shareonbazaar.dev.bazaar.login;

import android.content.Context;
import android.support.annotation.NonNull;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.TOKEN;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View mLoginView;
    private Context mContext;

    public LoginPresenter(@NonNull LoginContract.View loginView){
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        mContext = context;
    }

    @Override
    public boolean checkEmailValidity(String email) {
        // TODO: Implement a better validation login
        return email.contains("@");
    }

    @Override
    public boolean checkPasswordStrength(String password) {
        // TODO: Implement a better validation login
        return password.length() > 4;
    }

    @Override
    public void loginWithEmail(String email, String password) {
        LoginService service = ConnectionSetup.retrofit.create(LoginService.class);
        service.loginUser(email, password)
                .enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        authenticateUser(response.body());
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        connectionError();
                    }
                });
    }

    @Override
    public void loginWithGooglePlus() {

    }

    @Override
    public void loginWithFacebook() {

    }

    @Override
    public void authenticateUser(Authentication authentication) {
        int status = authentication.getStatus();
        String error = authentication.getError();
        String token = authentication.getToken();


        if(status == 200){
            SharedPreference sharedPreference = new SharedPreference(mContext);
            sharedPreference.saveToken(TOKEN, token);

            //TODO: Save user data
            //loginSuccess(authentication);
            mLoginView.showAllUsersUi();
        }else{
            //Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void connectionError() {

    }
}
