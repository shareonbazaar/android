package eu.shareonbazaar.dev.bazaar.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utility.ViewPagerAdapter;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        initializeViewPager(viewPager, toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_main);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initializeViewPager(ViewPager viewPager, Toolbar toolbar) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        final String PEOPLE_TITLE = "PEOPLE";
        String PROFILE_TITLE = "PROFILE";
        String WALLET_TITLE = "WALLET";

        adapter.addFragment(new PeopleFragment(), PEOPLE_TITLE);
        adapter.addFragment(new ProfileFragment(), PROFILE_TITLE);
        adapter.addFragment(new WalletFragment(), WALLET_TITLE);
        viewPager.setAdapter(adapter);
    }
}
