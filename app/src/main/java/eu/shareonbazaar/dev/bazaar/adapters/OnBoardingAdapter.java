package eu.shareonbazaar.dev.bazaar.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.shareonbazaar.dev.bazaar.ui.OnBoardingFragment;

public class OnBoardingAdapter extends FragmentPagerAdapter {
    private int sectionCount = 4;

    public OnBoardingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return OnBoardingFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return sectionCount;
    }
}
