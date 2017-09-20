package eu.shareonbazaar.dev.bazaar.bookmark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import eu.shareonbazaar.dev.bazaar.model.login.Authentication;
import eu.shareonbazaar.dev.bazaar.model.people.PeopleJSON;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import eu.shareonbazaar.dev.bazaar.network.ConnectionSetup;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.TOKEN;

class BookmarkPresenter implements BookmarkContract.Presenter{

    private Context mContext;
    private BookmarkContract.View mBookmarkView;

    BookmarkPresenter(@NonNull BookmarkContract.View bookmarkView){
        mBookmarkView = bookmarkView;
        mBookmarkView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        mContext = context;
        fetchBookmarks();
    }

    @Override
    public void fetchBookmarks() {
        mBookmarkView.showProgressBar();
        SharedPreference sharedPreference = new SharedPreference(mContext);
        String token = sharedPreference.retrieveToken(TOKEN);
        BookmarkService service = ConnectionSetup.retrofit.create(BookmarkService.class);
        Disposable disposable = Observable.zip(service.getUser(token), service.getUsers(token), this::filter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::validateBookmarks, this::bookmarkFetchError);

        mBookmarkView.hideProgressBar();
        mBookmarkView.setDisposable(disposable);
    }

    private ArrayList<User> filter(Authentication authentication, PeopleJSON peopleJSON){
        ArrayList<String> bookmarkIds = authentication.getCurrentUser().getBookmarks();
        ArrayList<User> users = peopleJSON.getUsers();
        ArrayList<User> result = new ArrayList<>();

        for(User user: users) {
            String userId = user.getId();

            for(String id : bookmarkIds){
                if(userId.equals(id)){
                    result.add(user);
                }
            }
        }

        return result;
    }

    @Override
    public void validateBookmarks(ArrayList<User> users) {
        if(users.isEmpty() || users.size() <= 0){
            mBookmarkView.showBookmarks(new ArrayList<>());
        }else {
            mBookmarkView.showBookmarks(users);
        }
    }

    @Override
    public void bookmarkFetchError(Throwable error) {
        mBookmarkView.showErrorMessage("Data retrieval failed!");
    }
}
