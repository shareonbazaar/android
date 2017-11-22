package eu.shareonbazaar.dev.bazaar.peopledetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.shareonbazaar.dev.bazaar.R;
import eu.shareonbazaar.dev.bazaar.adapters.CustomJsonAdapter;
import eu.shareonbazaar.dev.bazaar.adapters.ViewPagerAdapter;
import eu.shareonbazaar.dev.bazaar.model.people.User;
import eu.shareonbazaar.dev.bazaar.model.skill.Skill;
import eu.shareonbazaar.dev.bazaar.utilities.RoundImageTransformation;

public class PeopleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tl_people_toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_profile_user_image)
    ImageView profileImage;
    @BindView(R.id.tv_profile_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_profile_location)
    TextView locationTextView;
    @BindView(R.id.vp_bio_review)
    ViewPager viewPager;
    @BindView(R.id.tl_bio_review)
    TabLayout tabLayout;
//    @BindView(R.id.tv_profile_about)
//    TextView aboutMeTextView;
//    @BindView(R.id.rv_skills_list)
//    RecyclerView skillsList;
//    @BindView(R.id.rv_interest_list)
//    RecyclerView interestList;

    private CustomJsonAdapter customJsonAdapter;
    public static final String TOKEN = "TOKEN";
    public static final String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        skillsList.setHasFixedSize(true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        Intent parentIntent = getIntent();
        User user = parentIntent.getExtras().getParcelable("User");
        initView(user);
        Toast.makeText(getApplicationContext(), "Api needed here", Toast.LENGTH_SHORT).show();
    }

    private void initView(User user){
        String emptyFieldPlaceholder = "Not available";
        if (user != null) {
            String imageUrl = user.getPicture();
            Picasso.with(getApplicationContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.image_placeholder_detail)
                    .error(R.drawable.image_placeholder_detail_error)
                    .transform(new RoundImageTransformation())
                    .into(profileImage);
            String userName = user.getName();
            userNameTextView.setText(userName);
            String location = (user.getLocation().length() > 0) ? user.getLocation() : emptyFieldPlaceholder;
            locationTextView.setText(location);
            String hometown = (user.getHometown().length() > 0) ? user.getHometown() : emptyFieldPlaceholder;
            // hometownTextView.setText(hometown);
            String aboutMe = (user.getAboutMe() != null) ? user.getAboutMe() : emptyFieldPlaceholder;
//            aboutMeTextView.setText(aboutMe);

            ArrayList<Skill> skills = user.getSkills();
            Log.d("SIZE", ">" + user.getSkills().size());
            /*LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            customJsonAdapter = new CustomJsonAdapter();
            customJsonAdapter.setJsonData(skills);
            skillsList.setLayoutManager(layoutManager);
            skillsList.setAdapter(customJsonAdapter);*/
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutMeFragment(), "About Me");
        adapter.addFragment(new ReviewFragment(), "Reviews");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.people_details_menu, menu);
        return true;
    }
}
