package eu.shareonbazaar.dev.bazaar.login;

import android.content.Context;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;
import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import retrofit2.Response;

public interface LoginContract {

    interface View extends BaseView<Presenter>{
        void displayConnectionError(String error);
        void displayEmailError(String error);
        void displayPasswordError(String error);
        void displayAuthenticationError(String error);
        void hideLoadingProgress();
        void displayLoadingProgress();
        void showAllUsersUi();
        void showSignupUi();
        void showRetrieveUserUi();
    }

    interface Presenter extends BasePresenter{
        boolean checkEmailValidity(String email);
        boolean checkPasswordStrength(String password);
        void loginWithEmail(String email, String password);
        void loginWithGooglePlus();
        void loginWithFacebook();
        void authenticateUser(Response<Authentication> response);
        void connectionError();
    }
}
