package eu.shareonbazaar.dev.bazaar.mainactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.bookmark.BookmarkFragment;
import eu.shareonbazaar.dev.bazaar.currentuser.CurrentUserDetailsActivity;
import eu.shareonbazaar.dev.bazaar.model.currentuser.CurrentUser;
import eu.shareonbazaar.dev.bazaar.people.PeopleFragment;
import eu.shareonbazaar.dev.bazaar.searchfilter.SearchDialog;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.wallet.WalletFragment;

import static eu.shareonbazaar.dev.bazaar.utilities.Constants.CURRENT_USER;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private PeopleFragment peopleFragment;
    private BookmarkFragment bookmarkFragment;
    private WalletFragment walletFragment;
    private FragmentManager fragmentManager;

    private CurrentUser mCurrentUser;

   Target target;
    private MenuItem menuItem;

    private MainActivityContract.Presenter mPresenter;
    private MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mainActivityPresenter = new MainActivityPresenter(this);

        if(savedInstanceState != null){
            mCurrentUser = savedInstanceState.getParcelable(CURRENT_USER);
            showCurrentUserImage();
        }else{
            initializeFragments();
            mainActivityPresenter.start(getApplicationContext());
            // mainActivityPresenter.fetchCurrentUser();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_people:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, peopleFragment)
                            .commit();
                    return true;
                case R.id.navigation_bookmarks:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, bookmarkFragment)
                            .commit();
                    return true;
                case R.id.navigation_wallet:
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_fragment_container, walletFragment)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    public void initializeFragments() {
        peopleFragment = new PeopleFragment();
        bookmarkFragment = new BookmarkFragment();
        walletFragment = new WalletFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container, peopleFragment)
                .commit();
    }

    @Override
    public void initializeCurrentUserDetails(CurrentUser currentUser) {
        mCurrentUser = currentUser;
        showCurrentUserImage();
    }

    @Override
    public void showCurrentUserImage() {
        String userImageUrl = mCurrentUser.getUserProfile().getUserImageUrl();

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                menuItem.setIcon(d);

                Log.d("SUCCESS", "Success loading bitmap.");
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("ERROR", "Error loading bitmap.");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("PREPARE", "Prepare loading bitmap.");
            }
        };

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        int px = (int) (24 * density);

        Picasso.with(this)
                .load(userImageUrl)
                .transform(new RoundImageTransformation())
                .error(R.drawable.ic_account_circle_24dp)
                .resize(px, px)
                .centerCrop()
                .into(target);
    }

    @Override
    public void showSearchDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SearchDialog dialog = new SearchDialog();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, dialog)
                .addToBackStack(null).commit();
    }

    @Override
    public void showCurrentUserDetailsUi() {
        Intent intent = new Intent(MainActivity.this, CurrentUserDetailsActivity.class);
        intent.putExtra(CURRENT_USER, mCurrentUser);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_USER, mCurrentUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menuItem = menu.findItem(R.id.action_profile_image);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                showSearchDialog();
                return true;
            case R.id.action_profile_image:
                showCurrentUserDetailsUi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
