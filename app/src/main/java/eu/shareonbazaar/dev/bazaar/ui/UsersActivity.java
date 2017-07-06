package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.ViewPagerAdapter;
import eu.shareonbazaar.dev.bazaar.models.Authentication;
import eu.shareonbazaar.dev.bazaar.models.LoggedInUser;
import eu.shareonbazaar.dev.bazaar.models.LoggedInUserProfile;
import eu.shareonbazaar.dev.bazaar.models.User;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;

public class UsersActivity extends AppCompatActivity {

    public static final String PEOPLE_TITLE = "PEOPLE";
    public static final String BOOKMARKS_TITLE = "BOOKMARKS";
    public static final String WALLET_TITLE = "WALLET";

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

        Intent parentIntent = getIntent();
        Authentication authentication = parentIntent.getExtras().getParcelable("Personal profile");
        String userImageUrl = authentication.getLoggedInUser()
                .getUserProfile().getUserImageUrl();

        Picasso.with(this)
                .load(userImageUrl)
                .transform(new RoundImageTransformation())
                .into(userProfile);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Work in progress!...", Toast.LENGTH_LONG).show();
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

}
