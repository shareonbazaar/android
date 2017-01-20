package eu.shareonbazaar.dev.bazaar.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.utility.ViewPagerAdapter;

import static eu.shareonbazaar.dev.bazaar.activity.UsersActivity.PEOPLE_TITLE;
import static eu.shareonbazaar.dev.bazaar.activity.UsersActivity.PROFILE_TITLE;
import static eu.shareonbazaar.dev.bazaar.activity.UsersActivity.WALLET_TITLE;

public class OnboardingActivity extends AppCompatActivity {

    private TextView[] dots;
    private LinearLayout dotsLayout;
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        initializeViewPager(viewPager);
    }

    private void addDots(int position)
    {

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);
    }

    private void initializeViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PeopleFragment(), "");
        adapter.addFragment(new ProfileFragment(), "");
        adapter.addFragment(new WalletFragment(), "");
        viewPager.setAdapter(adapter);
    }
}
