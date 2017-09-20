package eu.shareonbazaar.dev.bazaar.bookmark;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.base.BasePresenter;
import eu.shareonbazaar.dev.bazaar.base.BaseView;

public interface BookmarkContract {

    interface View extends BaseView<Presenter>{
        void showBookmarks();
        void showBookmarkDetails();
        void onRequestUser();
    }

    interface Presenter extends BasePresenter{
        void fetchCurrentUser();
        void getBookmarkFromId(ArrayList<String> bookmarks);
        void bookmarkFetchError(String error);
        void handleRequestUser();
    }
}
