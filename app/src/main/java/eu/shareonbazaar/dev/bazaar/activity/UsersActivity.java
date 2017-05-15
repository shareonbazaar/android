package eu.shareonbazaar.dev.bazaar.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utility.ViewPagerAdapter;

public class UsersActivity extends AppCompatActivity {

    public static final String PEOPLE_TITLE = "PEOPLE";
    public static final String BOOKMARKS_TITLE = "BOOKMARKS";
    public static final String WALLET_TITLE = "WALLET";

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

    private void initializeViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PeopleFragment(), PEOPLE_TITLE);
        adapter.addFragment(new WalletFragment(), WALLET_TITLE);
        adapter.addFragment(new BookmarkFragment(), BOOKMARKS_TITLE);
        viewPager.setAdapter(adapter);
    }

}
