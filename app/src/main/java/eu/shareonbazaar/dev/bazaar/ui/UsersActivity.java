package eu.shareonbazaar.dev.bazaar.ui;

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
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION_CODES.M;

public class UsersActivity extends AppCompatActivity {

    private PeopleFragment peopleFragment;
    private BookmarkFragment bookmarkFragment;
    private WalletFragment walletFragment;
    private FragmentManager fragmentManager;

    private static final String AUTHENTICATION_OBJECT = "Personal profile";
    private Authentication authentication;
    private Target target;

    MenuItem menuItem;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
//    @BindView(R.id.user_profile_image)
//    ImageView userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        peopleFragment = new PeopleFragment();
        bookmarkFragment = new BookmarkFragment();
        walletFragment = new WalletFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container, peopleFragment)
                .commit();

        if(savedInstanceState != null){
            Log.d("SAVED", "Im here");
            authentication = savedInstanceState.getParcelable(AUTHENTICATION_OBJECT);
            initializeUserImage();
        }else{
            fetchUser();
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

    private void fetchUser(){
        SharedPreference sharedPreference = new SharedPreference(this);
        String token = sharedPreference.retrieveToken("TOKEN");
        UserService service = RetrofitTemplate.retrofit.create(UserService.class);
        service.getUser(token)
                .enqueue(new Callback<Authentication>() {

                    @Override
                    public void onResponse(Call<Authentication> call,
                                           Response<Authentication> response) {
                        authentication = response.body();
                        Log.d("FETCH", "onResponse: " + authentication.getError());
                        initializeUserImage();
                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Log.d("LOG_TAG", t.getMessage());
                    }
                });
    }

    private void initializeUserImage(){
        String userImageUrl = authentication.getLoggedInUser()
                .getUserProfile().getUserImageUrl();

        Log.d("Image URL", userImageUrl);

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

        /*userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersActivity.this, PersonalProfileActivity.class);
                intent.putExtra(AUTHENTICATION_OBJECT, authentication);
                startActivity(intent);
            }
        });*/
    }

    public void showSearchDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SearchDialog dialog = new SearchDialog();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, dialog)
                .addToBackStack(null).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(AUTHENTICATION_OBJECT, authentication);
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
//                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem menuItem = menu.findItem(R.id.action_profile_image);

        Picasso.with(getApplicationContext())
                .load(R.drawable.ic_account_circle_24dp)
                .transform(new RoundImageTransformation())
                .error(R.drawable.ic_account_circle_24dp)
                .into(new Target()
                {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
                    {
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        menuItem.setIcon(d);
                        *//*ab.setDisplayShowHomeEnabled(true);
                        ab.setDisplayHomeAsUpEnabled(true);*//*
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable)
                    {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable)
                    {
                    }
                });

        return true;
    }*/
}
