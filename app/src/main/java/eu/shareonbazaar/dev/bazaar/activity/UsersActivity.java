package eu.shareonbazaar.dev.bazaar.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utility.ViewPagerAdapter;

/**
 * Activity to encapsulate all major functionaly of this app
 */
public class UsersActivity extends AppCompatActivity {

    public static final String PEOPLE_TITLE = "PEOPLE";
    public static final String PROFILE_TITLE = "PROFILE";
    public static final String WALLET_TITLE = "WALLET";

    /**
     * Sets up toolbar and ViewPager (tabs)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        initializeViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_main);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Initializes ViewPager by adding fragments with defined heading
     * @param viewPager
     */
    private void initializeViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PeopleFragment(), PEOPLE_TITLE);
        adapter.addFragment(new ProfileFragment(), PROFILE_TITLE);
        adapter.addFragment(new WalletFragment(), WALLET_TITLE);
        viewPager.setAdapter(adapter);
    }
}
