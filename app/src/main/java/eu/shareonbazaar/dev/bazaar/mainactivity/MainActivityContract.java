package eu.shareonbazaar.dev.bazaar.mainactivity;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;
import eu.shareonbazaar.dev.bazaar.model.login.Authentication;

public interface MainActivityContract {

    interface View extends BaseView<Presenter>{
        void initializeFragments();
        void initialCurrentUserDetails(Authentication authentication);
        void showCurrentUserImage();
        void showSearchDialog();
        void showCurrentUserDetailsUi();
    }

    interface Presenter extends BasePresenter{
        void fetchCurrentUser();
        void checkCurrentUserJson(Authentication authentication);
        void connectionError();
    }
}
