package eu.shareonbazaar.dev.bazaar.signup;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;

public interface SignUpContract {
    interface View extends BaseView<Presenter> {
        void showRegistrationError(String error);
    }

    interface Presenter extends BasePresenter {
        void registerUser(String firstName, String lastName, String email,
                          String password, String confirmPassword);
        void registrationFailed(String error);
    }
}