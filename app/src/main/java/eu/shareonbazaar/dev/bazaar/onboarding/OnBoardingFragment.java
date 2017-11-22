package eu.shareonbazaar.dev.bazaar.onboarding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.shareonbazaar.dev.bazaar.R;


public class OnBoardingFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private int[] sectionHeader = new int[]{
            R.string.section_1_header,
            R.string.section_2_header,
            R.string.section_3_header,
            R.string.section_4_header,
    };

    private int[] sectionDescription = new int[]{
            R.string.section_1_description,
            R.string.section_2_description,
            R.string.section_3_description,
            R.string.section_4_description,
    };

    private int[] imageResources = new int[]{
            R.drawable.bazaar_logo,
            R.drawable.skills,
            R.drawable.timebanking,
            R.drawable.network
    };

    public OnBoardingFragment() {
    }

    public static OnBoardingFragment newInstance(int sectionNumber) {
        OnBoardingFragment fragment = new OnBoardingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onboarding, container, false);
        TextView mSectionHeader = (TextView) rootView.findViewById(R.id.section_label);
        mSectionHeader.setText(getString(sectionHeader[getArguments().getInt(ARG_SECTION_NUMBER) - 1]));

        TextView mSectionDescription = (TextView) rootView.findViewById(R.id.section_description);
        mSectionDescription.setText(getString(sectionDescription[getArguments().getInt(ARG_SECTION_NUMBER) - 1]));

        ImageView mBoardImage = (ImageView) rootView.findViewById(R.id.section_img);
        int imageResource = imageResources[getArguments().getInt(ARG_SECTION_NUMBER) - 1];
        Picasso.with(getActivity())
                .load(imageResource)
                .fit()
                /*.resize(180, 180)
                .onlyScaleDown()*/
                .into(mBoardImage);

        return rootView;
    }
}
