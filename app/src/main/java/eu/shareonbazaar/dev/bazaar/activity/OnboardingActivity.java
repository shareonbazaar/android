package eu.shareonbazaar.dev.bazaar.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.login.LoginActivity;
import eu.shareonbazaar.dev.bazaar.login.RegisterActivity;
import eu.shareonbazaar.dev.bazaar.utility.ViewPagerAdapter;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        dotsLayout=(LinearLayout)findViewById(R.id.layoutDots);
        layouts = new int[]{R.layout.fragment_board_1,R.layout.fragment_board_2};
        addDots(0);
        initializeViewPager(viewPager);
        viewPager.addOnPageChangeListener(viewListener);


        Button signup = (Button)findViewById(R.id.onboarding_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this, RegisterActivity.class));
            }
        });

        TextView signin = (TextView)findViewById(R.id.onboarding_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
            }
        });
    }

    private void addDots(int position)
    {

        TextView[] dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i = 0; i< dots.length; i++)
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
        adapter.addFragment(new Board_1_Fragment(), "");
        adapter.addFragment(new Board_2_Fragment(), "");
        viewPager.setAdapter(adapter);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}
