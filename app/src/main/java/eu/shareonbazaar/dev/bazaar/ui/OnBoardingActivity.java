package eu.shareonbazaar.dev.bazaar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.OnBoardingAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    private ImageView[] mIndicators;
    private ViewPager mViewPager;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        Button signup = (Button)findViewById(R.id.onboarding_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, RegisterActivity.class));
                finish();
            }
        });

        TextView signin = (TextView)findViewById(R.id.onboarding_signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                finish();
            }
        });

        OnBoardingAdapter OnBoardingAdapter= new OnBoardingAdapter(getSupportFragmentManager());

        ImageView mFirstIndicator = (ImageView) findViewById(R.id.intro_indicator_0);
        ImageView mSecondIndicator = (ImageView) findViewById(R.id.intro_indicator_1);
        ImageView mThirdIndicator = (ImageView) findViewById(R.id.intro_indicator_2);
        ImageView mFourthIndicator = (ImageView) findViewById(R.id.intro_indicator_3);

        mIndicators = new ImageView[]{mFirstIndicator, mSecondIndicator,
                mThirdIndicator, mFourthIndicator};

        mViewPager = (ViewPager) findViewById(R.id.vp_section_container);
        mViewPager.setAdapter(OnBoardingAdapter);
        mViewPager.setCurrentItem(currentPage);

        updateIndicators(currentPage);
        addPageChangeListener();
        addPageTransformer();
    }

    private void addPageChangeListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updateIndicators(currentPage);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    private void addPageTransformer(){
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                // page.setRotation(position * 12);
                final float normalizedPosition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedPosition / 2 + 0.5f);
                page.setScaleY(normalizedPosition / 2 + 0.5f);
            }
        });
    }

    private void updateIndicators(int position) {
        int defaultWidthInPixel = 14;
        int heightInPixel = 10;
        int adjustedWidthInPixel = 32;

        int defaultWidthInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                defaultWidthInPixel, getResources().getDisplayMetrics());
        int heightInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                heightInPixel, getResources().getDisplayMetrics());
        int adjustedWidthInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                adjustedWidthInPixel, getResources().getDisplayMetrics());


        for (int i = 0; i < mIndicators.length; i++) {
            if( i == position){
                mIndicators[i].setBackgroundResource(R.drawable.indicator_selected);
                mIndicators[i].setLayoutParams(new LinearLayout.LayoutParams(
                        adjustedWidthInDp, heightInDp));
            }else {
                mIndicators[i].setBackgroundResource(R.drawable.indicator_unselected);
                mIndicators[i].setLayoutParams(new LinearLayout.LayoutParams(
                        defaultWidthInDp, heightInDp));
            }
        }
    }
}
