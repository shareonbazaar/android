package eu.shareonbazaar.dev.bazaar.currentuser;


import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUser;
import eu.shareonbazaar.dev.bazaar.model.login.Authentication;

public class CurrentUserContract {
    interface View extends BaseView<Presenter> {
        void showCurrentUser();
        void showEditUi();
    }

    interface Presenter extends BasePresenter {

    }
}
