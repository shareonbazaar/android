package eu.shareonbazaar.dev.bazaar.bookmark;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.BookmarkAdapter;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import io.reactivex.disposables.Disposable;

public class BookmarkFragment extends Fragment implements BookmarkContract.View,
        BookmarkAdapter.BookmarkAdapterClickListener{

    private BookmarkContract.Presenter mPresenter;
    private BookmarkPresenter mBookmarkPresenter;
    private Disposable mDisposable;

    @BindView(R.id.rv_bookmarks)
    RecyclerView mBookmarkContainer;
    @BindView(R.id.ll_bookmark_network_error)
    LinearLayout errorContainer;
    @BindView(R.id.pb_loading_bookmarks)
    ProgressBar mProgressBar;


    public BookmarkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, rootView);

        mBookmarkContainer.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBookmarkContainer.setLayoutManager(mLayoutManager);

        mBookmarkPresenter = new BookmarkPresenter(this);
        mPresenter.start(getActivity().getApplicationContext());

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mDisposable != null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }

    @OnClick(R.id.tv_retry_bookmark_fetch)
    public void retryBookmarkFetch(){
        hideErrorMessage();
        mBookmarkPresenter.fetchBookmarks();
    }

    @Override
    public void setPresenter(BookmarkContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showBookmarks(ArrayList<User> users) {
        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this);
        bookmarkAdapter.setUserData(users);
        mBookmarkContainer.setAdapter(bookmarkAdapter);
    }

    @Override
    public void showBookmarkDetails() {

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String error) {
        errorContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        errorContainer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onItemClicked(User user) {

    }
}
