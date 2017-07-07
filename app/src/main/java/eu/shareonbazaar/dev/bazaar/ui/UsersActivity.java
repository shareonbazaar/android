package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.ViewPagerAdapter;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.network.RetrofitTemplate;
import eu.shareonbazaar.dev.bazaar.network.UserService;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;
import eu.shareonbazaar.dev.bazaar.utilities.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    public static final String PEOPLE_TITLE = "PEOPLE";
    public static final String BOOKMARKS_TITLE = "BOOKMARKS";
    public static final String WALLET_TITLE = "WALLET";
    private static final String AUTHENTICATION_OBJECT = "Personal profile";
    private Authentication authentication;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager_main)
    ViewPager viewPager;
    @BindView(R.id.tabs_main)
    TabLayout tabLayout;
    @BindView(R.id.user_profile_image)
    ImageView userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initializeViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        if(savedInstanceState != null){
            Log.d("SAVED", "Im here");
            authentication = savedInstanceState.getParcelable(AUTHENTICATION_OBJECT);
            initializeUserImage();
        }else{
            fetchUser();
        }
    }

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

        Picasso.with(this)
                .load(userImageUrl)
                .transform(new RoundImageTransformation())
                .error(R.drawable.ic_account_circle_24dp)
                .into(userProfile);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersActivity.this, PersonalProfileActivity.class);
                intent.putExtra(AUTHENTICATION_OBJECT, authentication);
                startActivity(intent);
            }
        });
    }

    private void initializeViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PeopleFragment(), PEOPLE_TITLE);
        adapter.addFragment(new WalletFragment(), WALLET_TITLE);
        adapter.addFragment(new BookmarkFragment(), BOOKMARKS_TITLE);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(AUTHENTICATION_OBJECT, authentication);
    }
}
