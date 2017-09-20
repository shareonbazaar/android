package eu.shareonbazaar.dev.bazaar.bookmark;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import io.reactivex.disposables.Disposable;

public interface BookmarkContract {

    interface View extends BaseView<Presenter>{
        void showBookmarks(ArrayList<User> users);
        void showBookmarkDetails();
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage(String error);
        void hideErrorMessage();
        void setDisposable(Disposable disposable);
    }

    interface Presenter extends BasePresenter{
        void fetchBookmarks();
        void validateBookmarks(ArrayList<User> users);
        void bookmarkFetchError(Throwable error);
    }
}
